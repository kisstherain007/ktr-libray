package com.ktr.net.rx;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by succlz123 on 15/9/15.
 */
public class OkHttpClientManager {
    private static final String TAG = "OkHttpClientManager";
    private static com.squareup.okhttp.OkHttpClient sInstance;

    public static com.squareup.okhttp.OkHttpClient getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (sInstance == null) {
                    sInstance = new com.squareup.okhttp.OkHttpClient();
                    //cookie enabled
                    sInstance.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
                    //��������ȡ���ݳ�ʱ
                    sInstance.setReadTimeout(15, TimeUnit.SECONDS);
                    //����������ʱ
                    sInstance.setConnectTimeout(20, TimeUnit.SECONDS);
                    sInstance.interceptors().add(new LoggingInterceptor());
                }
            }
        }
        return sInstance;
    }

    /**
     * see http://stackoverflow.com/questions/24952199/okhttp-enable-logs
     */
    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.v("OkHttp", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.v("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}
