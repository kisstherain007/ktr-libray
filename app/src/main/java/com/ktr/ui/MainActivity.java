package com.ktr.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ktr.ktr_libray.R;
import com.ktr.ui.widget.AutoScrollViewPager;
import com.ktr.ui.widget.ImagePagerAdapter;
import com.ktr.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * 可循环的广告位，和 Fresco 的使用
 */
public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ViewFinder viewFinder;

    AutoScrollViewPager viewPager;

    private List<Integer> imageIdList;

    private SimpleDraweeView mSimpleDraweeView;
    private SimpleDraweeView mSimpleDraweeView2;
    private SimpleDraweeView mSimpleDraweeView3;

    private String imageUri1 = "http://img.ptcms.csdn.net/article/201503/30/5519091be9a85_middle.jpg?_=30474";
    private String imageUri2 = "http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif";
    private String imageUri3 = "http://p5.qhimg.com/t01d0e0384b952ed7e8.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFinder = new ViewFinder(this);

        initView();
    }

    private void initView() {

        viewPager = viewFinder.find(R.id.view_pager);
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.mipmap.banner1);
        imageIdList.add(R.mipmap.banner2);
        imageIdList.add(R.mipmap.banner3);
        imageIdList.add(R.mipmap.banner4);
        viewPager.setAdapter(new ImagePagerAdapter(this, imageIdList).setInfiniteLoop(true));
//        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        Uri uri = Uri.parse(imageUri1);
        Uri uri2 = Uri.parse(imageUri2);
        Uri uri3 = Uri.parse(imageUri3);

        mSimpleDraweeView = viewFinder.find(R.id.frsco_img1);
        DraweeController draweeController1 = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build();
        mSimpleDraweeView.setController(draweeController1);
        mSimpleDraweeView.setOnTouchListener(this);
//      Uri uri2 = Uri.parse(imageUri2);
        DraweeController draweeController2 = Fresco.newDraweeControllerBuilder().setUri(uri2).setAutoPlayAnimations(true).build();
        mSimpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.frsco_img2);
        mSimpleDraweeView2.setController(draweeController2);
        RoundingParams mRoundParams2 = mSimpleDraweeView2.getHierarchy().getRoundingParams();
        mRoundParams2.setRoundAsCircle(true);
        mSimpleDraweeView2.getHierarchy().setRoundingParams(mRoundParams2);
        DraweeController draweeController3 = Fresco.newDraweeControllerBuilder().setUri(uri3).setAutoPlayAnimations(true).build();
        mSimpleDraweeView3 = (SimpleDraweeView) findViewById(R.id.frsco_img3);
        mSimpleDraweeView3.setController(draweeController3);
        RoundingParams mRoundParams3 = mSimpleDraweeView3.getHierarchy().getRoundingParams();
        mRoundParams3.setRoundAsCircle(true);
        mSimpleDraweeView3.getHierarchy().setRoundingParams(mRoundParams3);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mSimpleDraweeView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                return true;
//                break;
            case MotionEvent.ACTION_UP:
                mSimpleDraweeView.clearColorFilter();
                return true;
//                break;
        }
        return super.onTouchEvent(event);
    }
}
