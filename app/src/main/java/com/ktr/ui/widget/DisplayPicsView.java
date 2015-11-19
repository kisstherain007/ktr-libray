package com.ktr.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ktr.ktr_libray.R;
import com.ktr.utils.ScreenUtil;
import com.ktr.utils.WLogger;
import com.ktr.utils.animation.AnimatorUtils;
import com.ktr.utils.bitmaploader.BitmapLoader;
import com.ktr.utils.bitmaploader.core.TestImageUrl;

/**
 * Created by kisstherain on 2015/10/31.
 */
public class DisplayPicsView extends ViewGroup {

    public static final String TAG = DisplayPicsView.class.getSimpleName();

    private int gap;

    private Rect[] picRects;

    private int screenWidth;

    private int screenHeight;

    public DisplayPicsView(Context context) {
        super(context);

    }

    public DisplayPicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DisplayPicsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        screenWidth = ScreenUtil.getScreenWidth();
        screenHeight = ScreenUtil.getScreenHeigth();
        gap = getResources().getDimensionPixelSize(R.dimen.gap_pics);
    }

    public void setPics(int size) {

        int mWidth =  Math.round(screenWidth * 1.0f * 1 / 2);
        int mHeight = mWidth;
        int imgW = Math.round((mWidth - 2 * gap) * 1.0f / 3.0f);
        int imgH = imgW;
        LinearLayout.LayoutParams layoutParams = null;
        Rect rect;
        picRects = new Rect[size];
        layoutParams = (LinearLayout.LayoutParams) getLayoutParams();

        if(layoutParams == null){

            layoutParams = new LinearLayout.LayoutParams(mWidth, mHeight);
        }else{
            layoutParams.width = mWidth;
            layoutParams.height = mHeight;
        }

        switch (size){
            case 1:
                rect  = new Rect(0, 0,imgW, imgH);
                picRects[0] = rect;
                break;
            case 2:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                break;
            case 3:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;
                break;
            case 4:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 2 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                break;
            case 5:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 2 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                break;
            case 6:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 2 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap * 2, imgH * 2 + gap);
                picRects[5] = rect;
                break;
            case 7:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 3 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap * 2, imgH * 2 + gap);
                picRects[5] = rect;

                rect = new Rect(0, (imgH + gap) * 2, imgW, imgH * 3 + gap);
                picRects[6] = rect;
                break;
            case 8:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 3 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap * 2, imgH * 2 + gap);
                picRects[5] = rect;

                rect = new Rect(0, (imgH + gap) * 2, imgW, imgH * 3 + gap);
                picRects[6] = rect;
                rect = new Rect(imgW + gap, (imgH + gap) * 2, imgW * 2 + gap, imgH * 3 + gap);
                picRects[7] = rect;
                break;
            case 9:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 3 + gap);

                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap * 2, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap * 2, imgH * 2 + gap);
                picRects[5] = rect;

                rect = new Rect(0, (imgH + gap) * 2, imgW, imgH * 3 + gap);
                picRects[6] = rect;
                rect = new Rect(imgW + gap, (imgH + gap) * 2, imgW * 2 + gap, imgH * 3 + gap);
                picRects[7] = rect;
                rect = new Rect((imgW + gap) * 2, (imgH + gap) * 2, imgW * 3 + gap * 2, imgH * 3 + gap);
                picRects[8] = rect;
                break;
        }

        setLayoutParams(layoutParams);

        displayPics();

        requestLayout();
    }

    private void displayPics() {

        if (picRects == null) return;

        for (int i = 0; i < getChildCount(); i++){

            ImageView imgView = (ImageView) getChildAt(i);

            if(i >= picRects.length){

                getChildAt(i).setVisibility(View.GONE);
            }else{
                imgView.setVisibility(View.VISIBLE);
                BitmapLoader.getInstance().display(imgView, TestImageUrl.imageUrlList.get(i));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (picRects == null) return;

        int count = getChildCount();

        for (int i = 0; i < count; i ++){

            if(i < picRects.length){
                final Rect imgRect = picRects[i];
                final ImageView childView = (ImageView) getChildAt(i);
                childView.layout(imgRect.left, imgRect.top, imgRect.right, imgRect.bottom);
                Log.i(TAG, "left:" + imgRect.left + " top:" + imgRect.top);

                AnimatorSet animSet = new AnimatorSet();
                animSet.playSequentially(AnimatorUtils.together(new AnticipateInterpolator(), AnimatorUtils.of(
                        childView,
                        AnimatorUtils.ofX(0f, imgRect.left),
                        AnimatorUtils.ofY(0f, imgRect.top)
                ).setDuration(500)));
                animSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }
                });
                animSet.start();
            }else{
                break;
            }
        }
    }
}
