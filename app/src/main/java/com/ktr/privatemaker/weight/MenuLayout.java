package com.ktr.privatemaker.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ktr.ktr_libray.R;

/**
 * Created by kisstherain on 2015/7/2.
 */
public class MenuLayout extends LinearLayout implements View.OnClickListener{

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;

    TextView title1, title2, title3;

    public MenuLayout(Context context) {
        super(context);
    }

    public MenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        initView();
    }

    private void initView() {

        title1 = (TextView) findViewById(R.id.title1);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);

        title1.setOnClickListener(this);
        title2.setOnClickListener(this);
        title3.setOnClickListener(this);

        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);

        title1.setBackgroundColor(getResources().getColor(R.color.gray_alpha));
    }


    @Override
    public void onClick(View v) {

        title1.setBackgroundColor(getResources().getColor(R.color.white));
        title2.setBackgroundColor(getResources().getColor(R.color.white));
        title3.setBackgroundColor(getResources().getColor(R.color.white));

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);

        switch (v.getId()){

            case R.id.title1:
                layout1.setVisibility(View.VISIBLE);
                title1.setBackgroundColor(getResources().getColor(R.color.gray_alpha));
                break;
            case R.id.title2:
                layout2.setVisibility(View.VISIBLE);
                title2.setBackgroundColor(getResources().getColor(R.color.gray_alpha));
                break;
            case R.id.title3:
                layout3.setVisibility(View.VISIBLE);
                title3.setBackgroundColor(getResources().getColor(R.color.gray_alpha));
                break;
        }
    }
}
