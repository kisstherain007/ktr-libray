package com.ktr.weixin.api;

import com.ktr.net.rx.RetrofitManager;
import com.ktr.weixin.bean.WxHot;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kisstherain on 2016/1/5.
 */
public class WeixinHotAPI {

    private interface ApiManagerService{

        @GET(Config.HOST_IP)
        WxHot getWXHot(@Query("num") String num, @Query("rand") String rand, @Query("word") String word, @Query("page") String page);
    }

//    public static Call<WxHot> getWXHotData(String word, String page){
//
//        RetrofitManager.getWXHotRetrofit().
//    }
}
