package com.ktr.privatemaker.ui.found;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ktr.baseabstract.adapter.BaseAdapterHelper;
import com.ktr.baseabstract.adapter.BaseQuickAdapter;
import com.ktr.baseabstract.adapter.QuickAdapter;
import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;
import com.ktr.ui.widget.DisplayPicsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisstherain on 2015/11/1.
 */
public class FoundFragment extends AbstractFragment {

    public static final String TAG = FoundFragment.class.getSimpleName();

    ListView found_listView;

    List<FoundEntity> dataList = new ArrayList<>();

    public static FoundFragment newInstance(){

        FoundFragment foundFragment = new FoundFragment();

        return foundFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_found, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();

        found_listView = (ListView) view.findViewById(R.id.found_listView);

        found_listView.setAdapter(new QuickAdapter<FoundEntity>(getActivity(), R.layout.display_pics_view_layout, dataList) {

            @Override
            protected void convert(BaseAdapterHelper helper, FoundEntity item) {

                DisplayPicsView displayPicsView = helper.getView(R.id.dispaly_pics_view);
                displayPicsView.setPics(item.picUrls.length);
            }
        });
    }

    private void initData() {

        for (int i = 0; i < 9; i++){

            FoundEntity foundEntity = new FoundEntity();
//            foundEntity.title = String.format("这是第一条发现内容d%", i);
            foundEntity.picUrls = new String[i + 1];
            dataList.add(foundEntity);
        }
    }

    class FoundEntity{

        String[] picUrls;

        String title;
    }
}
