/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package pan.view.remote;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;
import pan.view.R;
import pan.view.ui.UIActivity;

/**
 * Created by panhongchao on 16/1/20.
 */
public class NotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendSpecialNotification();
    }

    private void sendNotification() {
        Intent intent = new Intent(this, UIActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setTicker("hello")
                .setContentTitle("Notification Title")
                .setContentText("This is the notification message")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis()).getNotification();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }

    private void sendSpecialNotification() {
        int sId = 10;
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this, UIActivity.class);
        intent.putExtra("sid", "" + sId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        System.out.println(pendingIntent);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.pending);
        remoteViews.setTextViewText(R.id.msg, "chapter_5: " + sId);
        remoteViews.setImageViewResource(R.id.icon, R.mipmap.ic_launcher);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this,
                0, new Intent(this, UIActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        notification.contentView = remoteViews;
        notification.contentIntent = pendingIntent;
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(sId, notification);
    }
}
