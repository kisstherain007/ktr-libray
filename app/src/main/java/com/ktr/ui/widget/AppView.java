package com.ktr.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by kisstherain on 2015/8/23.
 */
public abstract class AppView extends RelativeLayout {

    public static final String TAG = AppView.class.getSimpleName();

    public AppView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        Log.i("ktr:", "onFinishInflate");
        if (!isInEditMode()) {
            onViewCreated(this);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        onUnbind();
        super.onDetachedFromWindow();
    }

    public void onUnbind() {
    }

    public abstract void onViewCreated(View view);
}
