package com.ktr.weixin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;

public class WeiXinJXFragment extends AbstractFragment {

    public static WeiXinJXFragment newInstance(String param1, String param2) {
        WeiXinJXFragment fragment = new WeiXinJXFragment();
        return fragment;
    }

    public WeiXinJXFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wei_xin_jx, container, false);
    }

}
