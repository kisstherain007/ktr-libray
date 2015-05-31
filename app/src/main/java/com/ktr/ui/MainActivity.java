package com.ktr.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ktr.ktr_libray.R;
import com.ktr.ui.widget.AutoScrollViewPager;
import com.ktr.ui.widget.ImagePagerAdapter;
import com.ktr.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewFinder viewFinder;

    AutoScrollViewPager viewPager;

    private List<Integer> imageIdList;

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
    }

}
