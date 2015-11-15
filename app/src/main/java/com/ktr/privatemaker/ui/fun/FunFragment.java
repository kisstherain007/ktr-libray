package com.ktr.privatemaker.ui.fun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;
import com.ktr.utils.ToastUtil;

/**
 * Created by kisstherain on 2015/11/14.
 */
public class FunFragment extends AbstractFragment {

    public static FunFragment newInstance(){
        FunFragment funFragment = new FunFragment();
        return funFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fun, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
