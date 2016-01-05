package com.ktr.net.rx;

import com.ktr.weixin.api.Config;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kisstherain on 2016/1/5.
 */
public class RetrofitManager {

    private static Retrofit.Builder sInstance;

    public static Retrofit.Builder getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitManager.class) {
                if (sInstance == null) {
                    sInstance = new Retrofit.Builder();
                }
            }
        }
        return sInstance;
    }

    private static Retrofit getRetrofit(String url) {
        return RetrofitManager.getInstance()
                .client(OkHttpClientManager.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public static Retrofit getWXHotRetrofit(){

        return getRetrofit(Config.HOST_IP);
    }
}
