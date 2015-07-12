package com.ktr.baseabstract.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.ktr.baseabstract.loader.AsyncTaskLoaderResult;

/**
 *
 * @author kisstherain
 *
 */
public abstract class AbstractAsyncNetRequestTaskLoader<T> extends AsyncTaskLoader<AsyncTaskLoaderResult<T>> {

    private static final String TAG = "AbstractAsyncNetRequestTaskLoader";

    private AsyncTaskLoaderResult<T> result;

    private Bundle args;


    public AbstractAsyncNetRequestTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (result == null) {

            forceLoad(); // 强制执行 重新加载新数据 执行 loadInBackground
        } else {

            deliverResult(result); // 直接返回已经加载的原始数据
        }
    }

    @Override
    public void deliverResult(AsyncTaskLoaderResult<T> data) {
        super.deliverResult(data);

        if(isReset()){


        }
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();

    }

    @Override
    public AsyncTaskLoaderResult<T> loadInBackground() {

        T data = null;

        Exception exception = null;

        try {

            data = loadData();

        } catch (Exception e) {

            exception = e;
        }

        result = new AsyncTaskLoaderResult<T>();
        result.data = data;
        result.exception = exception;
        result.args = this.args;

        return result;
    }

    public void setArgs(Bundle args) {

        if (result != null) {

            throw new IllegalArgumentException("can't setArgs after loader executes");
        }

        this.args = args;
    }

    protected abstract T loadData() throws Exception;

    protected void onReleaseResources(AsyncTaskLoaderResult<T> result) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }
}
