package com.ktr.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ktr.ktr_libray.R;
import com.ktr.utils.ScreenUtil;

/**
 * Created by kisstherain on 2015/10/31.
 */
public class DisplayPicsView extends ViewGroup {

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

        setPics(2);
    }

    public void setPics(int size) {

        int mWidth =  Math.round(screenWidth * 1.0f * 4 / 5);
        int mHeight = mWidth;
        int imgW = Math.round((mWidth - 2 * gap) * 1.0f / 3.0f);
        int imgH = imgW;
        LinearLayout.LayoutParams layoutParams = null;
        Rect rect;
        picRects = new Rect[size];
        layoutParams = new LinearLayout.LayoutParams(mWidth, mHeight);

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
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
                picRects[2] = rect;
                break;
            case 4:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 2 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
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
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
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
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
                picRects[2] = rect;
                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap, imgH * 2 + gap);
                picRects[5] = rect;
                break;
            case 7:
//                layoutParams = new LinearLayout.LayoutParams(mWidth, imgH * 3 + gap);
                rect = new Rect(0, 0, imgW, imgH);
                picRects[0] = rect;
                rect = new Rect(imgW + gap, 0, imgW * 2 + gap, imgH);
                picRects[1] = rect;
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap, imgH * 2 + gap);
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
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap, imgH * 2 + gap);
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
                rect = new Rect((imgW + gap) * 2, 0, imgW * 3 + gap, imgH);
                picRects[2] = rect;

                rect = new Rect(0, imgH + gap, imgW, imgH * 2 + gap);
                picRects[3] = rect;
                rect = new Rect(imgW + gap, imgH + gap, imgW * 2 + gap, imgH * 2 + gap);
                picRects[4] = rect;
                rect = new Rect((imgW + gap) * 2, imgH + gap, imgW * 3 + gap, imgH * 2 + gap);
                picRects[5] = rect;

                rect = new Rect(0, (imgH + gap) * 2, imgW, imgH * 3 + gap);
                picRects[6] = rect;
                rect = new Rect(imgW + gap, (imgH + gap) * 2, imgW * 2 + gap, imgH * 3 + gap);
                picRects[7] = rect;
                rect = new Rect((imgW + gap) * 2, (imgH + gap) * 2, imgW * 3 + gap, imgH * 3 + gap);
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
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (picRects == null) return;

        int count = getChildCount();

        for (int i = 0; i < count; i ++){

            if(i < picRects.length){
                Rect imgRect = picRects[i];
                ImageView childView = (ImageView) getChildAt(i);
                childView.layout(imgRect.left, imgRect.top, imgRect.right, imgRect.bottom);
            }else{
                break;
            }
        }
    }
}
