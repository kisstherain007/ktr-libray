package com.ktr.baseabstract.fragmentmanager;


import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by zhoubo on 2015/1/10.
 *
 * 视图控制器Activity管理类
 */
public class AppUIManager {

    public static Stack<Activity> activityStack;

    private static AppUIManager ourInstance = new AppUIManager();

    public static AppUIManager getInstance() {
        return ourInstance;
    }

    private AppUIManager() {
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity){

        if(activityStack == null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){

        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){

        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){

        if(activity != null){

            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){

        for (Activity activity : activityStack) {

            if(activity.getClass().equals(cls) ){

                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){

        for (int i = 0, size = activityStack.size(); i < size; i++){

            if (null != activityStack.get(i)){

                activityStack.get(i).finish();
            }
        }

        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
	public void AppExit(Context context) {

        try {

            finishAllActivity();

            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            activityMgr.restartPackage(context.getPackageName());

            System.exit(0);
        } catch (Exception e) {

        }
    }
}
