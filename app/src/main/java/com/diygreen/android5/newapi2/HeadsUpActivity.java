package com.diygreen.android5.newapi2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.diygreen.android5.MainActivity;
import com.diygreen.android5.R;

import java.text.DateFormat;
import java.util.Date;

public class HeadsUpActivity extends AppCompatActivity {

    private Intent mIntent;
    private PendingIntent pendingIntent;
    private PendingIntent pendingIntent1;
    private NotificationManager notificationManager;

    private int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headsup);

        initData();
    }

    private void initData() {
        mIntent = new Intent(this, MainActivity.class);
        //这里需要设置Intent.FLAG_ACTIVITY_NEW_TASK属性
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pendingIntent = PendingIntent.getActivity(this,0, mIntent, 0);
        pendingIntent1 = PendingIntent.getActivity(this,0, mIntent, 0);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                test1();
                break;
            case R.id.btn_test2:
                test2();
                break;
            case R.id.btn_test3:
                test3();
                break;
            case R.id.btn_cleanall:
                cleanAll();
                break;
        }
    }

    private void test1() {
        Notification notification = new NotificationCompat.Builder(this)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.mipmap.ic_logo)
                .setFullScreenIntent(pendingIntent, false)
                .setContentTitle("这是标题")
                .setContentText("这是内容")
                .addAction(R.mipmap.ic_logo, "菜单1", pendingIntent1)
                .build();
        notificationManager.notify(1, notification);
    }

    private void test2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("提醒").setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                //要显示通知栏通知,这个一定要设置
                .setSmallIcon(R.mipmap.ic_logo)
                .setContentText("你有新的消息")
                        //2.3 一定要设置这个参数,否则会报错
                .setContentIntent(pendingIntent)
                        //设置是否显示 action 按键
                .setUsesChronometer(true)
                .addAction(R.mipmap.ic_capturesrceen, "查看", pendingIntent);

        Notification notification = builder.build();
        notificationManager.notify(code++, notification);
    }

    private void test3() {
        createNotification();
//        View view = getLayoutInflater().inflate(R.layout.notification_custom_layout, null);
//
//        view.findViewById(R.id.btn_test1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/zzz40500/HeadsUp"));
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                HeadsUpActivity.this.startActivity(intent);
//                notificationManager.cancel(code);
//            }
//        });
//
//        Notification headsUp1 = new NotificationCompat.Builder(this)
//                .setContentTitle("标题")
//                        //要显示通知栏通知,这个一定要设置
//                .setSmallIcon(R.mipmap.ic_logo)
//                        //2.3 一定要设置这个参数,否则会报错
//                .setContentIntent(pendingIntent)
//                .setContentText("这个是自定义通知")
//                .build();
//        notificationManager.notify(code++, headsUp1);
    }

    private void cleanAll() {
        notificationManager.cancelAll();
    }

    /**
     * Google 提供的示例代码
     */
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Create Intent to launch this Activity again if the notification is clicked.
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        // Sets the ticker text
        builder.setTicker(getResources().getString(R.string.custom_notification));
        // Sets the small icon for the ticker
        builder.setSmallIcon(R.mipmap.ic_stat_custom);
        // Cancel the notification when clicked
        builder.setAutoCancel(true);

        // Build the notification
        Notification notification = builder.build();
        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);

        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = getResources().getString(R.string.collapsed, time);
        contentView.setTextViewText(R.id.textView, text);

        /* Workaround: Need to set the content view here directly on the notification.
         * NotificationCompatBuilder contains a bug that prevents this from working on platform
         * versions HoneyComb.
         * See https://code.google.com/p/android/issues/detail?id=30495
         */
        notification.contentView = contentView;

        // Add a big content view to the notification if supported.
        // Support for expanded notifications was added in API level 16.
        // (The normal contentView is shown when the notification is collapsed, when expanded the
        // big content view set here is displayed.)
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView =
                    new RemoteViews(getPackageName(), R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }

        // START_INCLUDE(notify)
        // Use the NotificationManager to show the notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
    }
}
