package com.ktr.utils;

import android.util.TypedValue;

import com.ktr.app.KtrApp;

public class Utils {


	public static int dip2px(int dipValue) {
		float reSize = KtrApp.getInstance().getResources().getDisplayMetrics().density;
		return (int) ((dipValue * reSize) + 0.5);
	}

	public static int px2dip(int pxValue) {
		float reSize = KtrApp.getInstance().getResources().getDisplayMetrics().density;
		return (int) ((pxValue / reSize) + 0.5);
	}

	public static float sp2px(int spValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, KtrApp.getInstance().getResources().getDisplayMetrics());
	}

}
