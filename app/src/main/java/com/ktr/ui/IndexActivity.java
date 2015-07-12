package com.ktr.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractActivity;
import com.ktr.utils.ToastUtil;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

/**
 * Created by kisstherain on 2015/7/11.
 *
 * 主页面
 */
public class IndexActivity extends AbstractIndexActivity{

    SwipeFlingAdapterView flingContainer;
     ArrayAdapter<String> arrayAdapter;
    ArrayList<String> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity_layout);

        al = new ArrayList<String>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, al );
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

                ToastUtil.showMsg("left");
            }

            @Override
            public void onRightCardExit(Object o) {

                ToastUtil.showMsg("right");
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectRight();
            }
        });
    }
}
