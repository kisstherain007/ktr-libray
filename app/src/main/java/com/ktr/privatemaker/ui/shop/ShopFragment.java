package com.ktr.privatemaker.ui.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.ui.found.FoundFragment;
import com.ktr.ui.widget.KtrListView;
import com.ktr.ui.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    List<Fragment> childFragments = new ArrayList<>();
    String[] titleArr;

    SlidingTabLayout slidingTabs;
    ViewPager viewPager;
    MyViewPagerAdapter mViewPagerAdapter;

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleArr =  new String[10];
        for (int i = 0; i < 10; i++){

            titleArr[i] = "title" + i;
            if (i == 0){

                childFragments.add(WebFragment.newInstance());
            }else{

                childFragments.add(ShopChildFragment.newInstance());
            }
        }

        slidingTabs = (SlidingTabLayout) view.findViewById(R.id.slidingTabs);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPagerAdapter = new MyViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        slidingTabs.setCustomTabView(R.layout.comm_lay_tab_indicator, android.R.id.text1);
        slidingTabs.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        slidingTabs.setDistributeEvenly(true); //是否填充满屏幕的宽度
        slidingTabs.setViewPager(viewPager);
//        slidingTabs.setOnPageChangeListener(this);
//        slidingTabs.setCurrent(mCurrentPosition);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = childFragments.get(position);
//            ShopChildFragment shopChildFragment = (ShopChildFragment) fragment;
//            if(shopChildFragment.found_listView != null) shopChildFragment.found_listView.setOnToggleToolbarShownListener(new KtrListView.OnToggleToolbarShownListener() {
//                @Override
//                public void toggleToolbarShown(boolean shown) {
//
//                    slidingTabs.setVisibility(shown ? View.VISIBLE : View.GONE);
//                }
//            });
            return fragment;
        }

        @Override
        public int getCount() {
            return childFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleArr[position];
        }
    }
}
