package com.ktr.privatemaker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ktr.baseabstract.adapter.BaseAdapterHelper;
import com.ktr.baseabstract.adapter.QuickAdapter;
import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;
import com.ktr.privatemaker.ui.HomeActivity;
import com.ktr.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisstherain on 2015/9/26.
 */
public class MyFragment extends AbstractFragment {

    ListView setting_listView;
    List<String> datas = new ArrayList<>();

    public static MyFragment newInstance(){

        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        datas.add("主题1");
        datas.add("主题2");
        datas.add("主题3");
        datas.add("主题4");

        setting_listView = (ListView) view.findViewById(R.id.setting_listView);

        setting_listView.setAdapter(new QuickAdapter<String>(getActivity(), R.layout.ktr_listview_item_layout, datas) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {

                helper.setText(R.id.title_textView, item);
            }
        });

        setting_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((HomeActivity)getActivity()).reload(ThemeUtils.getThemeColor(position));
            }
        });
    }
}
