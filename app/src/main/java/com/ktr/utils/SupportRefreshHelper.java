package com.ktr.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ktr.ktr_libray.R;

/**
 * Created by kisstherain on 2015/7/11.
 */
public class SupportRefreshHelper {


    /*上下文，创建view的时候需要用到*/
    private Context mContext;

    /*用户定义的view*/
    private View mUserView;

    /*视图构造器*/
    private LayoutInflater mInflater;

    SwipeRefreshLayout swipeRefreshLayout;

    /*base view*/
    private FrameLayout mContentView;

    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);

    }

    public SupportRefreshHelper(Context context, int layoutId){

        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

        initContentView();

        initRefresh();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
        /*初始化refresh*/
    }

    private void initUserView(int layoutId){
        mUserView = mInflater.inflate(layoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        swipeRefreshLayout.addView(mUserView, params);

    }

    private void initRefresh(){

        View rootView = mInflater.inflate(R.layout.refreshlayout, mContentView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);

        // 设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 0);
            }
        });
    }

    public FrameLayout getRefreshLayout() {
        return mContentView;
    }
}
