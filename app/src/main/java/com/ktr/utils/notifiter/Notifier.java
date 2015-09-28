package com.ktr.utils.notifiter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.ktr.app.KtrApp;

public abstract class Notifier {

	final protected Context context;

	final private NotificationManager notificationManager;

	public static final long[] vibrate = new long[]{ 0, 150, 100, 250 };
	
	public static final int PublishMessage = 1000;

	public Notifier(Context context) {
		this.context = context;
		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	final protected void notify(int id, Notification notification) {
		notificationManager.notify(id, notification);
	}

	final public void cancelNotification(int request) {
		notificationManager.cancel(request);
	}
	
	final public static void cancelAll() {

		NotificationManager notificationManager = (NotificationManager) KtrApp.getInstance().getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
		
		notificationManager.cancel(PublishMessage);
	}
	
}
