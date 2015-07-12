package com.ktr.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.ktr.baseabstract.adapter.BaseAdapterHelper;
import com.ktr.baseabstract.adapter.QuickAdapter;
import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisstherain on 2015/7/11.
 */
public class KtrActivity extends AbstractActivity {

    ListView ktr_listView;
    List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ktr_activity_layout);

        ktr_listView = (ListView) findViewById(R.id.ktr_listView);
        datas.add("sdf");
        datas.add("sdf");
        datas.add("sdf");
        datas.add("sdf");
        datas.add("sdf");
        ktr_listView.setAdapter(new QuickAdapter<String>(this, R.layout.ktr_listview_item_layout, datas) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {

                helper.setText(R.id.title_textView, item);
            }
        });
    }
}
