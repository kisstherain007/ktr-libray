package com.ktr.ui.multiMedia;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ktr.ktr_libray.R;
import com.ktr.ui.widget.video.proxy.C;
import com.ktr.ui.widget.video.proxy.HttpGetProxy;
import com.ktr.ui.widget.video.proxy.ProxyUtils;

import java.io.File;

/**
 * Created by kisstherain on 2015/11/11.
 */
public class VideoPlayerView extends MultiMediaView implements IMultiMediaTaskManager{

    public static final String TAG = VideoPlayerView.class.getSimpleName();

    ViewDragHelper mDragHelper;
    private MediaController mediaController;
    private HttpGetProxy proxy;
    private long startTimeMills;
    private String proxyUrl;
    VideoView mVideoView;
    Context mContext;
    Handler mHandler;

    public VideoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mHandler = new Handler();

        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
    }

    class DragHelperCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            return false;
        }

    }

    @Override
    public void onViewCreated(View view) {
        mVideoView = (VideoView) view.findViewById(R.id.videoView);
        new File(C.getBufferDir()).mkdirs();//创建预加载文件的文件夹
        ProxyUtils.clearCacheFile(C.getBufferDir());//清除前面的预加载文件

        //初始化VideoView
        mediaController=new MediaController(mContext);
        mVideoView.setMediaController(mediaController);
        mVideoView.setOnPreparedListener(mOnPreparedListener);
        //初始化代理服务器
        proxy = new HttpGetProxy(9980);
        proxy.asynStartProxy();
    }

    @Override
    public void addPlayTask(MultiMediaPlayTask multiMediaPlayTask) {
        Log.e(TAG, multiMediaPlayTask.getCurrentPlayUrl());
//        requestLayout();
//        invalidate();

        proxy.getLocalURL(multiMediaPlayTask.getCurrentPlayUrl(), new HttpGetProxy.OnLocalURLgetListener() {
            @Override
            public String onSuccessResponse(final String[] urls) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        String mp4Url = urls[0];
                        proxyUrl = urls[1];

                        boolean enablePrebuffer = true;//纯粹对比测试
                        if (enablePrebuffer) {//使用预加载
                            try {
                                String prebufferFilePath = proxy.prebuffer(mp4Url,
                                        5 * 1024 * 1024);
                                Log.e(TAG, "预加载文件：" + prebufferFilePath);
                            } catch (Exception ex) {
                                Log.e(TAG, ex.toString());
                                Log.e(TAG, ProxyUtils.getExceptionMessage(ex));
                            }
                            delayToStartPlay.sendEmptyMessageDelayed(0, 8000);//留8000ms预加载
                        } else//不使用预加载
                            delayToStartPlay.sendEmptyMessageDelayed(0, 0);

                        // 一直显示MediaController
                        showController.sendEmptyMessageDelayed(0, 1000);
                    }
                });
                return null;
            }
        });
    }

    private MediaPlayer.OnPreparedListener mOnPreparedListener=new MediaPlayer.OnPreparedListener(){

        @Override
        public void onPrepared(MediaPlayer mp) {
            mVideoView.start();
            long duration=System.currentTimeMillis() - startTimeMills;
            Log.e("duration:",duration+"");
        }
    };

    private Handler delayToStartPlay = new Handler() {
        public void handleMessage(Message msg) {
            startTimeMills=System.currentTimeMillis();
            mVideoView.setVideoPath(proxyUrl);
        }
    };

    private Handler showController = new Handler() {
        public void handleMessage(Message msg) {
            mediaController.show(0);
        }
    };
}
