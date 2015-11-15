package com.ktr.ui.multiMedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ktr.ktr_libray.R;

public class VideoPlayerActivity extends AppCompatActivity {

    public static final String TAG = VideoPlayerActivity.class.getSimpleName();

    VideoPlayerView videoLayout;
    private String oriVideoUrl ="http://bcs.duapp.com/dlna-sample/out_MP4_AVC_AAC_320x240_2013761628.mp4?sign=MBO:C09e40adc8851224375a26cf2c6d12a0:7zwy3HtoM%2B5hXB2%2FlJFN6OkWFCs%3D";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Log.i("ktr:", "onCreate");
        videoLayout = (VideoPlayerView) findViewById(R.id.video_layout);
        MultiMediaPlayTask multiMediaPlayTask = new MultiMediaPlayTask();
        multiMediaPlayTask.addPlayUrl(oriVideoUrl);
        Log.i("ktr:", "addPlayTask");
        videoLayout.addPlayTask(multiMediaPlayTask);

    }
}
