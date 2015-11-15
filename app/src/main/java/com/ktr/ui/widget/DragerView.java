package com.ktr.ui.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.ktr.ktr_libray.R;
import com.ktr.utils.ToastUtil;

/**
 * Created by kisstherain on 2015/11/14.
 */
public class DragerView extends AppView {

    private static final String TAG = DragerView.class.getSimpleName();

    private ViewDragHelper mDragger;
    private View dragView;
    private Point mAutoBackOriginPos = new Point();

    private ImageView image_view;

    public DragerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new DragerCallback());
    }

    @Override
    public void onViewCreated(View view) {
    }

    class DragerCallback extends ViewDragHelper.Callback {

        /**
         * 尝试捕获子view，一定要返回true
         * @param View child 尝试捕获的view
         * @param int pointerId 指示器id？
         * 这里可以决定哪个子view可以拖动
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            dragView = child;
            return true;
        }

        /**
         *  处理竖直方向上的拖动
         * @param View child 被拖动到view
         * @param int top 移动到达的y轴的距离
         * @param int dy 建议的移动的y距离
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int buttomBound = getHeight() - dragView.getHeight() - topBound;
            final int newTop = Math.min(Math.max(top, topBound), buttomBound);
            return newTop;
        }

        /**
         * 处理水平方向上的拖动
         * @param View child 被拖动到view
         * @param int left 移动到达的x轴的距离
         * @param int dx 建议的移动的x距离
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - dragView.getWidth() - leftBound;
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredHeight();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
//            mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
//            invalidate();
        }

        /**
         * 当拖拽到状态改变时回调
         * @params 新的状态
         */
        @Override
        public void onViewDragStateChanged(int state) {
            switch (state) {
                case ViewDragHelper.STATE_DRAGGING:  // 正在被拖动

                    break;
                case ViewDragHelper.STATE_IDLE:  // view没有被拖拽或者 正在进行fling/snap

                    break;
                case ViewDragHelper.STATE_SETTLING: // fling完毕后被放置到一个位置

                    break;
            }
            super.onViewDragStateChanged(state);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent");
        final int action = MotionEventCompat.getActionMasked(ev);

        final float x = ev.getX();
        final float y = ev.getY();

        boolean interceptForTap = false;

        switch (action){

            case MotionEvent.ACTION_DOWN:
                interceptForTap = mDragger.isViewUnder(image_view, (int) x, (int) y);
                Log.i(TAG, "onInterceptTouchEvent >>> ACTION_DOWN" + interceptForTap);
                break;
        }
//        if ((action != MotionEvent.ACTION_DOWN)) {
//            Log.i(TAG, "onInterceptTouchEvent >>> ACTION_DOWN");
//            mDragger.cancel();// 相当于调用 processTouchEvent收到ACTION_CANCEL
//            return super.onInterceptTouchEvent(ev);
//        }
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            Log.i(TAG, "onInterceptTouchEvent >>> ACTION_CANCEL || ACTION_UP");
//            mDragger.cancel();// 相当于调用 processTouchEvent收到ACTION_CANCEL
//            return false;
//        }
        /**
         * 检查是否可以拦截touch事件
         * 如果onInterceptTouchEvent可以return true 则这里return true
         */
        Log.i(TAG, "onInterceptTouchEvent >>> shouldInterceptTouchEvent");
        return mDragger.shouldInterceptTouchEvent(ev) || interceptForTap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        Log.i(TAG, "onTouchEvent");
        final int action = event.getAction();

        return true;
    }

    /**
     * 处理拦截到的事件
     * 这个方法会在返回前分发事件
     */
    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = dragView.getLeft();
        mAutoBackOriginPos.y = dragView.getTop();
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        dragView = getChildAt(0);

        image_view = (ImageView) findViewById(R.id.image_view);

        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMsg("v click");
            }
        });
    }
}
