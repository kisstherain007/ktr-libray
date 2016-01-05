package com.ktr.utils.bitmaploader;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.ktr.app.KtrApp;
import com.ktr.utils.bitmaploader.core.BitmapCache;
import com.ktr.utils.bitmaploader.core.BitmapLoaderTask;
import com.ktr.utils.bitmaploader.core.BitmapProcess;
import com.ktr.utils.bitmaploader.core.ImageConfig;
import com.ktr.utils.bitmaploader.download.NetDownloader;
import com.ktr.utils.task.TaskException;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kisstherain on 2015/11/19.
 */
public class BitmapLoader {

    public static final String TAG = BitmapLoader.class.getSimpleName();

    private Map<String, WeakReference<ImageLoaderTask>> taskCache;

    private BitmapCache mImageCache;// 图片缓存

    private String imageCachePath;// 图片缓存路径

    private static BitmapLoader ourInstance;

    private BitmapProcess bitmapProcess;

    private Context mContext;

    public static BitmapLoader newInstance(Context context, String imageCachePath) {
        ourInstance = new BitmapLoader(context);
        ourInstance.imageCachePath = imageCachePath;
        return ourInstance;
    }

    private BitmapLoader(Context context) {
        mContext = context;
        init();
    }

    public static BitmapLoader getInstance() {
        return ourInstance;
    }

    private void init() {

        taskCache = new HashMap<String, WeakReference<ImageLoaderTask>>();

        mContext = KtrApp.getInstance();
        int memCacheSize = 1024 * 1024 * ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        memCacheSize = memCacheSize / 3;

        bitmapProcess = new BitmapProcess(imageCachePath);

        mImageCache = new BitmapCache(memCacheSize);
    }

    public void display(ImageView imageView, String url/*, ImageConfig imageConfig*/) {

        if (TextUtils.isEmpty(url)) {
            setImageFaild(imageView);
            return;
        }

        if (bitmapHasBeenSet(imageView, url)) return;

        Bitmap bitmap = mImageCache.getBitmapFromMemCache(url);
        if (bitmap != null && imageView != null) {

            Log.i(TAG, "display:" + "cache");
            imageView.setImageDrawable(new BitmapDrawable(mContext.getResources(), bitmap));
        } else {

            if (!checkTaskExistAndRunning(url)) {

                Log.i(TAG, "display:" + "net");
                ImageLoaderTask imageLoaderTask = new ImageLoaderTask(imageView, url);
                WeakReference<ImageLoaderTask> taskReference = new WeakReference<ImageLoaderTask>(imageLoaderTask);
                taskCache.put(url, taskReference);
                imageLoaderTask.executrOnImageExecutor();
            }
        }
    }

    public class ImageLoaderTask extends BitmapLoaderTask {

        String mImageUrl;
        ImageView mImageView;
        boolean isCompleted = false;

        public ImageLoaderTask(ImageView imageView, String url) {
            this.mImageView = imageView;
            this.mImageUrl = url;
        }

        @Override
        public Bitmap workInBackground(Void... params) throws TaskException {

            try {

                // 图片下载处理
                BitmapBytesAndFlag bitmapBytesAndFlag = doDownload(mImageUrl);

                // 图片压缩处理
//                bitmapProcess.c

                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytesAndFlag.bitmapBytes, 0, bitmapBytesAndFlag.bitmapBytes.length);
                if (bitmap != null) {
                    mImageCache.addBitmapToMemCache(mImageUrl, bitmap);
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onSuccess(Bitmap bitmap) {
            super.onSuccess(bitmap);
            mImageView.setImageDrawable(new BitmapDrawable(mContext.getResources(), bitmap));
        }

        @Override
        protected void onFinished() {
            super.onFinished();
            isCompleted = true;
            taskCache.remove(mImageUrl);
        }
    }

    public BitmapBytesAndFlag doDownload(String imageUrl) throws Exception { // 图片下载或本地缓存读取操作

        byte[] bitmapBytes = null;

        // 本地缓存读取

        // 网络下载
        bitmapBytes = new NetDownloader().downloadBitmap(imageUrl);
        if (bitmapBytes != null) {
            BitmapBytesAndFlag bitmapBytesAndFlag = new BitmapBytesAndFlag();
            bitmapBytesAndFlag.bitmapBytes = bitmapBytes;
            return bitmapBytesAndFlag;
        }

        throw new Exception("download faild");
    }

    public void setImageFaild(ImageView imageView) {

    }

    public boolean bitmapHasBeenSet(ImageView imageView, String url) {

        if (imageView != null){

            Drawable drawable = imageView.getDrawable();

            if (drawable != null) {

                return true;
            } else {

                return false;
            }
        }

       return false;
    }

    public boolean checkTaskExistAndRunning(String url) {

        WeakReference<ImageLoaderTask> imageLoaderTask = taskCache.get(url);

        if (imageLoaderTask != null) {

            ImageLoaderTask oaderTask = imageLoaderTask.get();

            if (oaderTask != null) {

                if (!oaderTask.isCancelled() && !oaderTask.isCompleted && oaderTask.mImageUrl.equals(url)) {

                    return true;
                }
            }
        } else {
            taskCache.remove(url);
        }

        return false;
    }

    public static class BitmapBytesAndFlag {

        public byte[] bitmapBytes;
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        new CacheExecutecTask().execute(CacheExecutecTask.MESSAGE_CLEAR);
    }


    private class CacheExecutecTask extends AsyncTask<Object, Void, Void> {
        public static final int MESSAGE_CLEAR = 0;
        public static final int MESSAGE_HALF_CLEAR = 4;

        @Override
        protected Void doInBackground(Object... params) {
            switch ((Integer) params[0]) {
                case MESSAGE_CLEAR:
                    clearMemCacheInternal();
                    break;
                case MESSAGE_HALF_CLEAR:
                    clearMemHalfCacheInternal();
                    break;
            }
            return null;
        }
    }

    private void clearMemCacheInternal() {
        Log.d(TAG, "clearMemCacheInternal");
        if (mImageCache != null) {
            mImageCache.clearMemCache();
        }
    }

    public void clearMemHalfCacheInternal() {
        if (mImageCache != null) {
            mImageCache.clearMemHalfCache();
        }
    }
}
