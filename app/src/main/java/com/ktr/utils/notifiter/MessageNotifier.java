package com.ktr.utils.notifiter;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.ktr.ktr_libray.R;

/**
 * Created by kisstherain on 2015/9/29.
 */
public class MessageNotifier extends Notifier {

    public MessageNotifier(Context context) {
        super(context);
    }

    public void notifyStatus(){

        String title = "title";
        String content = "content";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentText(content);

        notify(PublishMessage, 0, builder);
    }

    public void notify(int request, int status, NotificationCompat.Builder builder) {
        Notification notification = builder.build();
        notify(request, notification);
    }
}
