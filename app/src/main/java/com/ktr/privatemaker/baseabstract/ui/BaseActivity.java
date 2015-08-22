package com.ktr.privatemaker.baseabstract.ui;

import android.os.Bundle;

import com.ktr.net.NetWorkResultListener;
import com.ktr.net.SupportNetWork;
import com.ktr.net.vollery.UtilEntity.ApiParams;

/**
 * Created by kisstherain on 2015/8/22.
 */
public class BaseActivity extends AbstractActivity {

    SupportNetWork supportNetWork;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportNetWork = new SupportNetWork(this);
    }

    public void executeRequest(ApiParams params, NetWorkResultListener<String> netWorkResultListener){

        supportNetWork.executePostRequest(params, netWorkResultListener);
    }

    public <T> void executeRequest(ApiParams params, Class<?> clazz, NetWorkResultListener<T> netWorkResultListener){

        supportNetWork.executePostRequest(params, clazz, netWorkResultListener);
    }
}
