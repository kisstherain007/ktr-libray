package com.ktr.privatemaker.ui.shop;

import android.os.Bundle;
import android.view.View;

import com.ktr.privatemaker.ui.found.FoundFragment;
import com.ktr.ui.widget.KtrListView;

/**
 * Created by kisstherain on 2015/11/16.
 */
public class ShopChildFragment extends FoundFragment {

    KtrListView.OnToggleToolbarShownListener mOnToggleToolbarShownListener;

    public static ShopChildFragment newInstance(){

        ShopChildFragment shopChildFragment = new ShopChildFragment();

        return shopChildFragment;
    }

    public void setmOnToggleToolbarShownListener(KtrListView.OnToggleToolbarShownListener onToggleToolbarShownListener) {
        this.mOnToggleToolbarShownListener = onToggleToolbarShownListener;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        found_listView.setOnToggleToolbarShownListener(mOnToggleToolbarShownListener);
    }
}
