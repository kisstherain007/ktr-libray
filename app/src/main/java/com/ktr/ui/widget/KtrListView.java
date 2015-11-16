package com.ktr.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.ktr.utils.Utils;

/**
 * Created by kisstherain on 2015/11/16.
 */
public class KtrListView extends ListView implements AbsListView.OnScrollListener {

    private static final String TAG = KtrListView.class.getSimpleName();

    int lastFirstVisibleItem = 0;
    int lastTop = 0;
    boolean isMoving = false;

    public KtrListView(Context context) {
        super(context);
    }

    public KtrListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setOnScrollListener(this);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        super.setOnScrollListener(l);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == SCROLL_STATE_IDLE) {
            isMoving = false;
            lastTop = 0;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        Log.i(TAG, "onScroll");

        if (getChildCount() == 0) return;

        if (isMoving) {
            View firstChild = view.getChildAt(0);

            if (firstVisibleItem == 0) {
                if(onToggleToolbarShownListener != null) onToggleToolbarShownListener.toggleToolbarShown(true);
            } else if (firstVisibleItem > lastFirstVisibleItem) {
                if(onToggleToolbarShownListener != null) onToggleToolbarShownListener.toggleToolbarShown(false);
            } else if (firstVisibleItem < lastFirstVisibleItem) {
                if(onToggleToolbarShownListener != null) onToggleToolbarShownListener.toggleToolbarShown(true);
            } else {
                int height = firstChild.getHeight();
                if (height > Utils.dip2px(200)) {
                    if (lastTop == 0) {
                        lastTop = firstChild.getTop();
                    }
                    else {
                        int diffTop = firstChild.getTop() - lastTop;
                        if (Math.abs(diffTop) >= Utils.dip2px(150)) {
                            if(onToggleToolbarShownListener != null) onToggleToolbarShownListener.toggleToolbarShown(diffTop > 0);
                        }
                    }
                }
            }
        }

        lastFirstVisibleItem = firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            isMoving = true;
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        onToggleToolbarShownListener = null;
    }

    OnToggleToolbarShownListener onToggleToolbarShownListener;

    public void setOnToggleToolbarShownListener(OnToggleToolbarShownListener onToggleToolbarShownListener) {
        this.onToggleToolbarShownListener = onToggleToolbarShownListener;
    }

    public interface OnToggleToolbarShownListener{

        void toggleToolbarShown(boolean shown);
    }
}
