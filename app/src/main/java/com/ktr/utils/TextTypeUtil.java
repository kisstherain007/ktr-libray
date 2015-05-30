package com.ktr.utils;

import android.graphics.Typeface;

import com.ktr.app.KtrApp;

public class TextTypeUtil {

	public static Typeface getTextType(){
		
		return Typeface.createFromAsset(KtrApp.getInstance().getAssets(),"fonts/FZZDX.TTF");
	}
}
