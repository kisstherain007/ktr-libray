package com.ktr.net.vollery.actionInterface;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by Administrator on 2015/1/11.
 */
public interface DataResultInterface {

    public void executeRequest(Request<?> request);

    public Response.ErrorListener errorListener();
}
