package com.ktr.utils;

import com.ktr.app.KtrApp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by zhoubo on 2014/9/28.
 */
public class ScreenUtil {

	public static double LocViewRate = 3.0 / 5.0;
	
	public static double MyHeaderRate = 1.0 / 4.0;
	public static double MyHeaderRateNew = 3.12 / 10.0;
	public static double homeMyHeaderRate = 1.0 / 3.0;
	public static double cosme_text_Rate = 1.0 / 8.0;
	
    static double scaleValue1 = 36.0;
    static double scaleValue2 = 80.0;
    static double scaleValue3 = 18.0;

    static double palyerScale = 16 / 9;

    public static int SCREEN_PORTRAIT = 0;

    public static int SCREEN_LANDSCAPE = 1;

    public static int SCREEN_UNKNOW_ORI = -1;

    /**
     * 转换DIP为PX
     */
    public static int convertDipToPx(Context context, int dip) {

        float scale = context.getResources().getDisplayMetrics().density;

        int px = (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));

        return px;
    }

    /**
     * 根据屏幕高度重新定义view所占用比例高度
     *
     * @param context
     * @param view
     */
    public static void resetHeightByPhoneScreen(Context context, View view) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int viewHeight = (int) (dm.widthPixels * 15.0 / scaleValue1);

        ViewGroup.LayoutParams galleryParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        galleryParams.height = viewHeight;

        view.setLayoutParams(galleryParams);
    }
    
    public static void resetHeightTW(Context context, View view){

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int viewHeight = (int) (dm.widthPixels / 2);

        ViewGroup.LayoutParams galleryParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        galleryParams.height = viewHeight;

        view.setLayoutParams(galleryParams);
    }
    
    public static void resetHeightTEquelsW(Context context, View view){

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int viewHeight = (int) (dm.widthPixels / (3/2) - 50);

        ViewGroup.LayoutParams galleryParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        galleryParams.height = viewHeight;

        view.setLayoutParams(galleryParams);
    }
    
    /**
     * 根据屏幕高度重新定义view所占用比例高度
     *
     * @param context
     * @param view
     */
    public static void resetHeightByPhoneScreenGoodsDetail(Context context, View view) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int viewHeight = (int) (dm.widthPixels * 15.0 / scaleValue3);

        ViewGroup.LayoutParams galleryParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        galleryParams.height = viewHeight;

        view.setLayoutParams(galleryParams);
    }

    /**
     * 根据屏幕高度重新定义view所占用比例高度
     *
     * @param context
     * @param view
     */
    public static void resetListItemHeightByPhoneScreen(Context context, View view) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int viewHeight = (int) (dm.widthPixels * 14.0 / scaleValue1);

        ViewGroup.LayoutParams galleryParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        galleryParams.height = viewHeight;

        view.setLayoutParams(galleryParams);
    }

    /**
     * 根据屏幕高度重新定义view所占用比例高度
     *
     * @param context
     * @param view
     */
    public static void resetHListItemHeightByPhoneScreen(Context context, View view) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int viewHeight = (int) (dm.widthPixels * 8.0 / scaleValue1);

        ViewGroup.LayoutParams galleryParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        galleryParams.height = viewHeight;

        view.setLayoutParams(galleryParams);
    }

//    /**
//     * 根据屏幕高度重新定义view所占用比例高度
//     * @param context
//     * @param view
//     */
//    public static void resetGridItemHeightByPhoneScreen(Context context, View view){
//
//        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//
//        int viewHeight = (int) ( dm.widthPixels * 14.0 / scaleValue1 );
//
//        ViewGroup.LayoutParams galleryParams = ( ViewGroup.LayoutParams ) view.getLayoutParams();
//        galleryParams.height = viewHeight;
//
//        view.setLayoutParams( galleryParams );
//    }

    /**
     * 根据屏幕宽度重新设置尺寸
     *
     * @param context 上下文
     * @param view    视图
     * @param rate    比率
     */
    public static void resetHeightWithRate(Context context, View view, double rate) {

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int screenHeight = dm.heightPixels;
        int newHeight = 0;
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();

        newHeight = (int) (screenHeight / rate);
        params.width = screenHeight;

        params.height = newHeight;
        view.setLayoutParams(params);
    }
    
    public static void resetHeightWithRateIndexPage(Context context, View view){
    	
    	DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int screenHeight = dm.heightPixels;
        int screenWidth = dm.widthPixels;
        
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();
        params.width = screenWidth / 2;
        params.height = screenHeight / 7;
        view.setLayoutParams(params);
    }
    
    public static void resetHeightWithRateIndexPageFooter(Context context, View view){
    	
    	DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int screenHeight = dm.heightPixels;
        int screenWidth = dm.widthPixels;
        
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();
        params.width = screenWidth;
        params.height = screenHeight / 6;
        view.setLayoutParams(params);
    }
    
    public static void resetHeightWithView(Context context, View view, double rate){
    	
    	DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int screenHeight = dm.heightPixels;
        int screenWidth = dm.widthPixels;
        
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();
        params.width = screenWidth;
        params.height = (int) (screenHeight * rate);
        view.setLayoutParams(params);
    }

    public static void resetPlayerHeight(Context context, View view) {

        double rate = 16.0 / 9.0;

//        double rate = 4.0 / 3.0;

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        int newHeight = 0;
        int newWidth = 0;

        if (screenWidth < screenHeight) {

            newHeight = (int) (screenWidth / rate);
            params.width = screenWidth;
        } else {

            newHeight = screenHeight;
            params.width = screenWidth;
        }

        params.height = newHeight;
        view.setLayoutParams(params);

        Log.e("", "height:" + params.height + "width:" + params.width);
    }

    public static int isLandscape(int rotation) {

        //手机平放时，检测不到有效的角度
        if (rotation == OrientationEventListener.ORIENTATION_UNKNOWN) return SCREEN_UNKNOW_ORI;

        if (((rotation >= 0) && (rotation <= 30)) || (rotation >= 330)) {

            return SCREEN_PORTRAIT;
        } else if (((rotation >= 200) && (rotation <= 280))) {

            return SCREEN_LANDSCAPE;
        }

        return SCREEN_UNKNOW_ORI;
    }

    public static int getScreenWidth() {

        DisplayMetrics dm = KtrApp.getInstance().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeigth() {

        DisplayMetrics dm = KtrApp.getInstance().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
    
    public static int dip2px(int dipValue) {
        float reSize = KtrApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }
}