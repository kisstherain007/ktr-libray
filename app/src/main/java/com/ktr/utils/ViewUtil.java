package com.ktr.utils;


import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

/**
 *
 */
public class ViewUtil {


    public static float getCenterX(View v) {
        return (v.getLeft() + v.getRight()) / 2f;
    }

    public static float getCenterY(View v) {
        return (v.getTop() + v.getBottom()) / 2f;
    }

    public static void setupWebView(WebView webview) {
        webview.setVerticalScrollbarOverlay(true);
        WebSettings settings = webview.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
    }

    public static boolean isVisible(View v) {
        return (v.getVisibility() == View.VISIBLE);
    }

    public static void setVisible(View v) {
        setVisibility(v, View.VISIBLE);
    }

    public static void setInvisible(View v) {
        setVisibility(v, View.INVISIBLE);
    }

    public static void setGone(View v) {
        setVisibility(v, View.GONE);
    }

    private static void setVisibility(View v, int visibility) {
        if (v.getVisibility() != visibility) {
            v.setVisibility(visibility);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 获取Listview的高度，然后设置ViewPager的高度
     * @param listView
     * @return
     */
    public static int setListViewHeightBasedOnChildren1(ListView listView) {
        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
        return params.height;
    }
}