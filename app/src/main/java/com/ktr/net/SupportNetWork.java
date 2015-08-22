package com.ktr.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ktr.app.KtrApp;
import com.ktr.net.vollery.UtilEntity.ApiParams;
import com.ktr.net.vollery.VolleryRequestManager;
import com.ktr.net.vollery.actionInterface.DataResultInterface;
import com.ktr.net.vollery.request.MStringRequest;
import com.ktr.utils.NetworkHelper;
import com.ktr.utils.ToastUtil;

import java.util.Map;

/**
 * Created by kisstherain on 2015/8/22.
 */
public class SupportNetWork implements DataResultInterface {

    Context mContext;

    public SupportNetWork(Context context) {

        this.mContext = context;
    }

    private boolean initNetWorkCheck(ApiParams params){

        if (params == null) {

            ToastUtil.showMsg("参数不能为空");

            return false;
        }

        if (!NetworkHelper.isNetworkAvailable(KtrApp.getInstance())) {

//            ToastUtil.showMsg(getResources().getString(R.string.not_availble_net_work));

            return false;
        }

        return true;
    }

    /**
     * @param params 参数
     * @param netWorkResultListener 网络完成回调
     */
    public void executePostRequest(final ApiParams params, final NetWorkResultListener<String> netWorkResultListener){

        if(!initNetWorkCheck(params)) return;

        executeRequest(new MStringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (netWorkResultListener != null) netWorkResultListener.onResultSuccess(0, response);
            }
        }, errorListener()) {

            protected Map<String, String> getParams() {
                return params;
            }
        });
    }

    /**
     *
     * @param params 参数
     * @param clazz  需要解析对象
     * @param netWorkResultListener 网络完成回调
     * @param <T> 需要回传对象
     */
    public <T> void executePostRequest(final ApiParams params, final Class<?> clazz, final NetWorkResultListener<T> netWorkResultListener){

        if(!initNetWorkCheck(params)) return;

        executeRequest(new MStringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(String response) {

                if (netWorkResultListener != null) netWorkResultListener.onResultSuccess(0, (T) getJsonObj(response, clazz));
            }
        }, errorListener()) {

            protected Map<String, String> getParams() {
                return params;
            }
        });
    }

    /**
     * json parse
     * @param json
     * @param pClass
     * @param <T>
     * @return
     */
    public <T> T getJsonObj(String json, Class<?> pClass){

        try {

            return (T) new GsonBuilder().create().fromJson(json, pClass);

        } catch (JsonSyntaxException e) {

            ToastUtil.showMsg("数据解析异常！");

            return null;
        }

    }
    /***
     * 取消网络请求
     */
    public void cancelAll(){

        VolleryRequestManager.getInstance().cancelAll(mContext);
    }

    /**
     * 添加网络请求
     * @param request
     */
    @Override
    public void executeRequest(Request<?> request) {

        VolleryRequestManager.getInstance().addRequest(request, mContext);
    }

    /**
     * NetWork error
     * @return
     */
    @Override
    public Response.ErrorListener errorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                ToastUtil.showMsg(getResources().getString(R.string.error_net_work));
            }
        };

    }
}
