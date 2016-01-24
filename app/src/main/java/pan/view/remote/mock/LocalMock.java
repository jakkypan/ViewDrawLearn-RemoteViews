/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package pan.view.remote.mock;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import pan.view.R;
import pan.view.ui.UIActivity;

/**
 * Created by panhongchao on 16/1/23.
 */
public class LocalMock extends Activity {
    private static final String TAG = "DemoActivity_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local);
    }

    public void onButtonClick(View v) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg, "msg from process:" + android.os.Process.myPid());
        remoteViews.setImageViewResource(R.id.icon, R.mipmap.ic_launcher);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, new Intent(this, UIActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, UIActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.item_holder, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        Intent intent = new Intent(RemoteMock.REMOTE_ACTION);
        intent.putExtra(RemoteMock.EXTRA_REMOTE_VIEWS, remoteViews);
        sendBroadcast(intent);
    }

}