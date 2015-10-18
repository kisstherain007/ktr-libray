package com.ktr.privatemaker.ui.home;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;
import com.ktr.ui.widget.AutoScrollViewPager;
import com.ktr.ui.widget.ImagePagerAdapter;
import com.ktr.ui.widget.KRecyclerAdapter;
import com.ktr.ui.widget.KRecyclerView;
import com.ktr.utils.ViewFinder;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AbstractFragment {

    ViewFinder viewFinder;
    AutoScrollViewPager viewPager;
//    GridView home_gridView;
    KRecyclerView home_recyclerView;
    List<Integer> datas = new ArrayList<Integer>();

    private List<Integer> imageIdList;

    android.support.v4.app.LoaderManager loaderManager;

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
        if (getArguments() != null) {}

        loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, cursorLoaderCallbacks);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        datas.add(0);
        datas.add(0);
        datas.add(0);
        datas.add(0);
        datas.add(0);
        datas.add(0);
        viewFinder = new ViewFinder(view);
//        home_gridView = viewFinder.find(R.id.home_gridView);
        viewPager = viewFinder.find(R.id.view_pager);
        viewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

//        home_gridView.setAdapter(new QuickAdapter<Integer>(getActivity(), R.layout.home_grid_item, datas) {
//            @Override
//            protected void convert(BaseAdapterHelper helper, Integer item) {
//
//                helper.getView(R.id.top_content_layout).setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.getScreenWidth() / 3, ScreenUtil.getScreenWidth() / 3));
//
//                helper.setImageResource(R.id.item_center_imageView, item);
//            }
//        });

        home_recyclerView = viewFinder.find(R.id.home_recyclerView);
        KRecyclerAdapter kRecyclerAdapter = new KRecyclerAdapter(this.getActivity());
        home_recyclerView.setAdapter(kRecyclerAdapter);
//        home_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
//        home_recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        home_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        kRecyclerAdapter.refreshAdapter(datas);
    }

    android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> cursorLoaderCallbacks = new android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {

            CursorLoader cursorLoader = new CursorLoader(getActivity());

            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
