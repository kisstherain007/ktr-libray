package com.ktr.privatemaker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.ktr.baseabstract.adapter.BaseAdapterHelper;
import com.ktr.baseabstract.adapter.QuickAdapter;
import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;
import com.ktr.ui.widget.AutoScrollViewPager;
import com.ktr.ui.widget.ImagePagerAdapter;
import com.ktr.utils.ScreenUtil;
import com.ktr.utils.ViewFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends AbstractFragment {

    ViewFinder viewFinder;
    AutoScrollViewPager viewPager;
    GridView home_gridView;
    List<Integer> datas = new ArrayList<Integer>();

    private List<Integer> imageIdList;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.mipmap.back_up_activi_one);
        imageIdList.add(R.mipmap.back_up_activi_one);
        imageIdList.add(R.mipmap.back_up_activi_one);
        imageIdList.add(R.mipmap.back_up_activi_one);
        datas.add(R.mipmap.back_up_activi_one);
        datas.add(R.mipmap.back_up_activi_one);
        datas.add(R.mipmap.back_up_activi_one);
        datas.add(R.mipmap.back_up_activi_one);
        datas.add(R.mipmap.back_up_activi_one);
        datas.add(R.mipmap.back_up_activi_one);
        viewFinder = new ViewFinder(view);
        home_gridView = viewFinder.find(R.id.home_gridView);
        viewPager = viewFinder.find(R.id.view_pager);
        viewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        home_gridView.setAdapter(new QuickAdapter<Integer>(getActivity(), R.layout.home_grid_item, datas) {
            @Override
            protected void convert(BaseAdapterHelper helper, Integer item) {

                helper.getView(R.id.top_content_layout).setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.getScreenWidth() / 3, ScreenUtil.getScreenWidth() / 3));

                helper.setImageResource(R.id.item_center_imageView, item);
            }

        });
    }

}
