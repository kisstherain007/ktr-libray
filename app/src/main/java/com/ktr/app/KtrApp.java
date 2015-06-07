package com.ktr.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * ktr
 */
public class KtrApp extends Application {
	
	private static KtrApp ourInstance = null;
	
	public static KtrApp getInstance() {
		
        return ourInstance;
    }
	
	public KtrApp() {
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		ourInstance = this;

		Fresco.initialize(this);
	}
}
