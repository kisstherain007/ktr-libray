package com.ktr.app;

import android.app.Application;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.ktr.net.vollery.VolleryRequestManager;
import com.ktr.utils.bitmaploader.BitmapLoader;
import com.ktr.utils.db.DataBaseManager;
import com.ktr.utils.db.table.UserTable;

import java.io.File;

/**
 * ktr
 */
public class KtrApp extends Application {
	
	private static KtrApp ourInstance = null;

	DataBaseManager mDataBaseManger;

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
		BitmapLoader.newInstance(this, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "kisstherainImage" + File.separator);

		Fresco.initialize(this);

		mDataBaseManger = DataBaseManager.getInstance();
		mDataBaseManger.addTable(UserTable.getInstance());
	}
}
