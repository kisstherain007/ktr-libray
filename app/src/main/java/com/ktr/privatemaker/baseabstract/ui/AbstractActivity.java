package com.ktr.privatemaker.baseabstract.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktr.ktr_libray.R;
import com.ktr.utils.SupportToolBarHelper;

/**
 * Activity 基类
 * Created by kisstherain on 2015/6/28.
 */
public class AbstractActivity extends AppCompatActivity {

    private SupportToolBarHelper mToolBarHelper;

    public Toolbar mToolbar ;

    private int theme = 0;// 当前界面设置的主题

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 设置主题
        if (theme > 0) setTheme(theme);

        super.onCreate(savedInstanceState);
    }


//    @Override
//    public void setContentView(int layoutResID) {
//
//        mToolBarHelper = new SupportToolBarHelper(this, layoutResID) ;
//        toolbar = mToolBarHelper.getToolBar() ;
//
//        setContentView(mToolBarHelper.getContentView());
//        /*把 toolbar 设置到Activity 中*/
//        setSupportActionBar(toolbar);
//        /*自定义的一些操作*/
//        onCreateCustomToolBar(toolbar) ;
//    }

//    @Override
//    public void setContentView(View view) {
//
//        if(mToolBarHelper == null){
//
//            mToolBarHelper = new SupportToolBarHelper(this, view) ;
//            mToolbar = mToolBarHelper.getToolBar();
//            super.setContentView(mToolBarHelper.getContentView());
//        }else{
//
//            super.setContentView(view);
//        }
//    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

//        rootView = view;
//
//        InjectUtility.initInjectedView(this);

        mToolbar = (Toolbar) findViewById(R.id.comm_toolbar);
        if (mToolbar != null)
            setSupportActionBar(mToolbar);
    }

    public void onCreateCustomToolBar(Toolbar toolbar){
        toolbar.setContentInsetsRelative(0,0);
    }

    public void buildCustomActionBar(){

        buildCustomActionBar(R.layout.actionbar_layout);
    }

    public void buildCustomActionBar(int resourceId){

        View actionBarView = LayoutInflater.from(this).inflate(resourceId, null);

        buildCustomActionBar(actionBarView);
    }

    public void buildCustomActionBar(View actionBarView){

        android.app.ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);// 给左上角图标的左边加上一个返回的图标
        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        ActionBar.LayoutParams acLP = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(actionBarView, acLP);
    }

    public void reload(int theme) {
        this.theme = theme;
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
