package com.ktr.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.ktr.ktr_libray.R;

/**
 * Created by kisstherain on 2015/10/29.
 */
@Deprecated
public class RefreshListView extends ListView implements AbsListView.OnScrollListener{

    View headerView;

    public RefreshListView(Context context) {
        super(context);

        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context){

        LayoutInflater inflater = LayoutInflater.from(context);
        headerView = inflater.inflate(R.layout.home_grid_item, null);
        measureView(headerView);
        int headerHeight = headerView.getMeasuredHeight();
        topPadding(-headerHeight);
        addHeaderView(headerView);
    }

    private void measureView(View view){

        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if(lp == null){

            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int width = ViewGroup.getChildMeasureSpec(0, 0, lp.width);

        int height;

        int tempHeight = lp.height;

        if(tempHeight > 0){

            height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        }else{

            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }

        view.measure(width, height);
    }

    private void topPadding(int topPadding){

        headerView.setPadding(headerView.getPaddingLeft(), topPadding, headerView.getPaddingRight(), headerView.getPaddingBottom());
        headerView.invalidate();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
