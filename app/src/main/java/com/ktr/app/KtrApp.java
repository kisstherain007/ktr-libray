package com.ktr.app;

import android.app.Application;

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
	}
}
