package com.ktr.utils.task;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kisstherain on 2015/11/2.
 */
public abstract class AsyncWorkTask<Params, Progress, Result>{

    private static final String TAG = AsyncWorkTask.class.getSimpleName();

    /**
     * 加载图片默认是10个线程
     */
    private static final int CORE_IMAGE_POOL_SIZE = 10;

    private static final int KEEP_ALIVE = 1;

    private TaskException exception;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };

    private static final ThreadFactory sDownloadThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask Wait Download #" + mCount.getAndIncrement());
        }
    };

    private static final ThreadFactory sIoThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask Local IO #" + mCount.getAndIncrement());
        }
    };

//    corePoolSize： 线程池维护线程的最少数量
//    maximumPoolSize：线程池维护线程的最大数量
//    keepAliveTime： 线程池维护线程所允许的空闲时间
//    unit： 线程池维护线程所允许的空闲时间的单位
//    workQueue： 线程池所使用的缓冲队列
//    handler： 线程池对拒绝任务的处理策略
    private static final Executor CANCEL_OPERATION__THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(4, 4, KEEP_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE),
            new ThreadFactory() {
                private final AtomicInteger mCount = new AtomicInteger(1);

                public Thread newThread(Runnable r) {
                    return new Thread(r,
                            "AsyncTask Local Cancel Operation #" + mCount.getAndIncrement());
                }
            });

    /**
     * An {@link java.util.concurrent.Executor} that can be used to execute tasks in parallel.
     */
    private static final Executor IO_THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(4, 10, KEEP_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20), sIoThreadFactory,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * 固定大小为{@link #CORE_IMAGE_POOL_SIZE}的线程池<br/>
     * 无界线程池，可以加载无限个线程
     */
    public static final Executor IMAGE_POOL_EXECUTOR = Executors.newFixedThreadPool(CORE_IMAGE_POOL_SIZE, sThreadFactory);


    public static final Executor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(8, 10, KEEP_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(128) {
        @Override
        public boolean offer(Runnable runnable) {
            return super.offerFirst(runnable); // 添加元素作为新的头节点
        }
    }, sThreadFactory,
            new ThreadPoolExecutor.DiscardOldestPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    if (!e.isShutdown()) {
                        LinkedBlockingDeque<Runnable> deque = (LinkedBlockingDeque) e.getQueue();
                        Runnable runnable = deque.pollLast();
                        if (runnable instanceof FutureTask) {
                            FutureTask futureTask = (FutureTask) runnable;
                            futureTask.cancel(true);
                            CANCEL_OPERATION__THREAD_POOL_EXECUTOR.execute(futureTask);
                        }
                        e.execute(r);
                    }
                }

            }
    );

    private static final Executor WAIT_DOWNLOAD_THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(4, 4, KEEP_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(50) {
        @Override
        public boolean offer(Runnable runnable) {
            return super.offerFirst(runnable);
        }

    }, sDownloadThreadFactory,
            new ThreadPoolExecutor.DiscardOldestPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    if (!e.isShutdown()) {
                        LinkedBlockingDeque<Runnable> deque = (LinkedBlockingDeque) e.getQueue();
                        Runnable runnable = deque.pollLast();
                        if (runnable instanceof FutureTask) {
                            FutureTask futureTask = (FutureTask) runnable;
                            futureTask.cancel(true);
                            CANCEL_OPERATION__THREAD_POOL_EXECUTOR.execute(futureTask);
                        }
                        e.execute(r);
                    }
                }
            }
    );

    /**
     * An {@link Executor} that executes tasks one at a time in serial
     * order.  This serialization is global to a particular process.
     */
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();

    private static final int MESSAGE_POST_RESULT = 0x1;

    private static final int MESSAGE_POST_PROGRESS = 0x2;

    private static final InternalHandler sHandler = new InternalHandler();

    private static volatile Executor sDefaultExecutor = SERIAL_EXECUTOR;

    private final WorkerRunnable<Params, Result> mWorker;

    private final FutureTask<Result> mFuture;

    private volatile Status mStatus = Status.PENDING;

    private final AtomicBoolean mTaskInvoked = new AtomicBoolean();

    private static class SerialExecutor implements Executor {

        final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();

        Runnable mActive;

        public synchronized void execute(final Runnable r) {
            mTasks.addFirst(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (mActive == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }
    }

    /**
     * Indicates the current status of the task. Each status will be set only once
     * during the lifetime of a task.
     */
    public enum Status {
        /**
         * Indicates that the task has not been executed yet.
         */
        PENDING,
        /**
         * Indicates that the task is running.
         */
        RUNNING,
        /**
         * Indicates that {@link AsyncTask#onPostExecute} has finished.
         */
        FINISHED,
    }

    /**
     * @hide Used to force static handler to be created.
     */
    public static void init() {
        sHandler.getLooper();
    }

    /**
     * @hide
     */
    public static void setDefaultExecutor(Executor exec) {
        sDefaultExecutor = exec;
    }

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public AsyncWorkTask(String taskId, IAsyncWorkTaskManager taskManager) {
        this();
        this.taskId = taskId;
        taskManager.addTask(this);
    }

    /**
     * Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    public AsyncWorkTask() {
        mWorker = new WorkerRunnable<Params, Result>() {
            public Result call() throws Exception {
                mTaskInvoked.set(true);

                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                return postResult(doInBackground(mParams));
            }
        };

        mFuture = new FutureTask<Result>(mWorker) {
            @Override
            protected void done() {
                try {
                    final Result result = get();

                    postResultIfNotInvoked(result);
                } catch (InterruptedException e) {
                    android.util.Log.w(TAG, e);
                } catch (ExecutionException e) {
                    throw new RuntimeException("An error occured while executing doInBackground()", e.getCause());
                } catch (CancellationException e) {
                    postResultIfNotInvoked(null);
                } catch (Throwable t) {
                    throw new RuntimeException("An error occured while executing " + "doInBackground()", t);
                }
            }
        };
    }

    private void postResultIfNotInvoked(Result result) {
        final boolean wasTaskInvoked = mTaskInvoked.get();
        if (!wasTaskInvoked) {
            postResult(result);
        }
    }

    private Result postResult(Result result) {
        Message message = sHandler.obtainMessage(MESSAGE_POST_RESULT, new AsyncTaskResult<Result>(this, result));
        message.sendToTarget();
        return result;
    }

    public final Status getStatus() {
        return mStatus;
    }

    /**
     * 线程开始执行
     */
    protected void onPrepare() {

    }

    /**
     * {@link #workInBackground(Object...)} 发生异常
     */
    protected void onFailure(TaskException exception) {

    }

    /**
     * 没有抛出异常，且<tt>Result</tt>不为<tt>Null</tt>
     */
    protected void onSuccess(Result result) {

    }

    protected Params[] getParams() {
        return mWorker.mParams;
    }

    /**
     * 线程结束，不管线程结束是什么状态，都会执行这个方法
     */
    protected void onFinished() {

    }

    /**
     * 异步执行方法
     *
     * @param params
     * @return
     * @throws TaskException
     */
    abstract public Result workInBackground(Params... params) throws TaskException;

    private Result doInBackground(Params... params) {
//        Logger.d(TAG, String.format("%s --->doInBackground()", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run ")));

        try {
            return workInBackground(params);
        } catch (TaskException e) {
            e.printStackTrace();
            exception = e;
        }

        return null;
    }

    final protected void onPreExecute() {
//        Logger.d(TAG, String.format("%s --->onTaskStarted()", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run ")));
        onPrepare();
    }

    final protected void onPostExecute(Result result) {
        if (exception == null) {
//			if (result == null) {
//				Logger.d(TAG, String.format("%s --->resultIsNull()", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run ")));
//				resultIsNull();
//			} else {
//            Logger.d(TAG, String.format("%s --->onTaskSuccess()", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run ")));
            onSuccess(result);
//			}
        }
        else if (exception != null) {
//            Logger.d(
//                    TAG,
//                    String.format("%s --->onTaskFailed(), \nError msg --->", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run "),
//                            exception.getMessage()));
            onFailure(exception);
        }

//        Logger.d(TAG, String.format("%s --->onTaskComplete()", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run ")));
        onFinished();
    }

    protected void onProgressUpdate(Progress... values) {
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    protected void onCancelled() {
//        Logger.d(TAG, String.format("%s --->onTaskComplete()", TextUtils.isEmpty(taskId) ? "run " : (taskId + " run ")));
        onFinished();
    }

    public final boolean isCancelled() {
        return mFuture.isCancelled();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        return mFuture.cancel(mayInterruptIfRunning);
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return mFuture.get();
    }

    public final Result get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return mFuture.get(timeout, unit);
    }


    /**
     * 默认线程池{@link #THREAD_POOL_EXECUTOR}
     *
     * @param params
     * @return
     */
    public final AsyncWorkTask<Params, Progress, Result> execute(Params... params) {
        return executeOnExecutor(THREAD_POOL_EXECUTOR, params);
    }

    /**
     * IO 读取
     * @param params
     */
    public void executeOnIO(Params... params) {
        executeOnExecutor(IO_THREAD_POOL_EXECUTOR, params);
    }

    /**
     * 加载图片的线程池
     *
     * @param params
     * @return
     */
    public final AsyncWorkTask<Params, Progress, Result> executrOnImageExecutor(Params... params) {
        return executeOnExecutor(IMAGE_POOL_EXECUTOR, params);
    }

    /**
     * 连续执行线程，所有的线程都是按照队列一个一个执行下去的
     *
     * @param params
     * @return
     */
    public final AsyncWorkTask<Params, Progress, Result> executeOnSerialExecutor(Params... params) {
        return executeOnExecutor(SERIAL_EXECUTOR, params);
    }

    public void executeOnWaitNetwork(Params... params) {
        executeOnExecutor(WAIT_DOWNLOAD_THREAD_POOL_EXECUTOR, params);
    }

    public final AsyncWorkTask<Params, Progress, Result> executeOnExecutor(Executor exec, Params... params) {
        if (mStatus != Status.PENDING) {
            switch (mStatus) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task:" + " the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task:" + " the task has already been executed "
                            + "(a task can be executed only once)");
            }
        }

        mStatus = Status.RUNNING;

        onPreExecute();

        mWorker.mParams = params;
        exec.execute(mFuture);

        return this;
    }

    public static void execute(Runnable runnable) {
        sDefaultExecutor.execute(runnable);
    }

    protected final void publishProgress(Progress... values) {
        if (!isCancelled()) {
            sHandler.obtainMessage(MESSAGE_POST_PROGRESS, new AsyncTaskResult<Progress>(this, values)).sendToTarget();
        }
    }

    private void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        mStatus = Status.FINISHED;
    }

    private static class InternalHandler extends Handler {
        @SuppressWarnings({ "unchecked" })
        @Override
        public void handleMessage(Message msg) {
            AsyncTaskResult result = (AsyncTaskResult) msg.obj;
            switch (msg.what) {
                case MESSAGE_POST_RESULT:
                    // There is only one result
                    result.mTask.finish(result.mData[0]);
                    break;
                case MESSAGE_POST_PROGRESS:
                    result.mTask.onProgressUpdate(result.mData);
                    break;
            }
        }
    }

    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {

        Params[] mParams;
    }

    private static class AsyncTaskResult<Data> {
        final AsyncWorkTask mTask;
        final Data[] mData;

        AsyncTaskResult(AsyncWorkTask task, Data... data) {
            mTask = task;
            mData = data;
        }
    }
}
