package com.ktr.net.vollery;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ktr.net.vollery.UtilEntity.BitmapLruCache;


/**
 * Created by zhoubo on 2015/1/9.
 *
 * 网络请求Vollery 管理类
 */
public class VolleryRequestManager {

    /**数据请求队列*/
    private static RequestQueue mRequestQueue;

    /**图片请求*/
    private static ImageLoader mImageLoader;

    private static VolleryRequestManager ourInstance = new VolleryRequestManager();

    public static VolleryRequestManager getInstance() {
        return ourInstance;
    }

    private VolleryRequestManager() {
    }

    /**
     * VooleryManager 初始化参数
     * @param context
     */
   public void init(Context context){

       mRequestQueue = Volley.newRequestQueue(context);

       int appTotalMemorySize = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

       int cacheImageLoaderSize = 1024 * 1024 * appTotalMemorySize / 8;

       mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheImageLoaderSize));
   }

    /**
     * 获取请求队列
     * @return
     */
    public RequestQueue getRequestQueue(){

        if (mRequestQueue != null){

            return mRequestQueue;
        }else {

            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * 添加请求
     * @param request
     * @param tag
     */
    public void addRequest(Request<?> request, Object tag){

        if (tag != null) {

            request.setTag(tag);
        }

        mRequestQueue.add(request);
    }

    /**
     * 取消请求
     * @param tag
     */
    public void cancelAll(Object tag){

        mRequestQueue.cancelAll(tag);
    }
    
    /**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means that no memory caching is used. This is useful
	 * for images that you know that will be show only once.
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader() {
		if (mImageLoader != null) {
			return mImageLoader;
		} else {
			throw new IllegalStateException("ImageLoader not initialized");
		}
	}
}
