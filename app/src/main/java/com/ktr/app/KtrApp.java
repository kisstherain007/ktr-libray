package com.ktr.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.ktr.net.vollery.VolleryRequestManager;

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

		VolleryRequestManager.getInstance().init(this);

		Fresco.initialize(this);
	}
}
