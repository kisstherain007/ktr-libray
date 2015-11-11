package com.ktr.utils.task;

/**
 * Created by kisstherain on 2015/11/3.
 *
 * 异步任务管理
 */
public interface IAsyncWorkTaskManager {

    public void addTask(AsyncWorkTask task);

    public void removeTask(String taskId, boolean cancelIfRunning);

    public void removeAllTask(boolean cancelIfRunning);

    public int getTaskCount(String taskId);
}
