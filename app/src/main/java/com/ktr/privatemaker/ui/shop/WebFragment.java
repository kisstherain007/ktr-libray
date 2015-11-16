package com.ktr.privatemaker.ui.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ktr.ktr_libray.R;
import com.ktr.privatemaker.baseabstract.ui.AbstractFragment;

/**
 * Created by kisstherain on 2015/11/16.
 */
public class WebFragment extends AbstractFragment {

    public static WebFragment newInstance(){

        WebFragment webFragment = new WebFragment();

        return webFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    WebView webView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = (WebView) view.findViewById(R.id.webView);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}
