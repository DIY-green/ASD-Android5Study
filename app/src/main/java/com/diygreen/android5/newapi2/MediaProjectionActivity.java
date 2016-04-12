package com.diygreen.android5.newapi2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.diygreen.android5.DIYApp;
import com.diygreen.android5.R;

public class MediaProjectionActivity extends AppCompatActivity {

    private String TAG = "MediaProjectionActivity";

    private int REQUEST_MEDIA_PROJECTION = 1;
    private int result = 0;
    private Intent intent = null;
    private MediaProjectionManager mMediaProjectionManager;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.float_layout);

        // 获取MediaProjectionManager类实例
        mMediaProjectionManager = (MediaProjectionManager) getApplication().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        startIntent();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startIntent() {
        if (intent != null && result != 0) {
            ((DIYApp) getApplication()).setmResult(result);
            ((DIYApp) getApplication()).setmIntent(intent);
            Intent intent = new Intent(getApplicationContext(), CaptureScreenService.class);
            startService(intent);
            Log.i(TAG, "start service CaptureScreenService");
        } else {
            // 利用MediaProjectionManager类实例的功能函数createScreenCaptureIntent()生成intent，为接下来的的抓取屏幕做准备
            startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), REQUEST_MEDIA_PROJECTION);
            ((DIYApp) getApplication()).setMediaProjectionManager(mMediaProjectionManager);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MEDIA_PROJECTION) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            } else if (data != null && resultCode != 0) {
                // 在onActivityResult()中获取resultCode和resultData
                result = resultCode;
                intent = data;
                ((DIYApp) getApplication()).setmResult(resultCode);
                ((DIYApp) getApplication()).setmIntent(data);
                Intent intent = new Intent(getApplicationContext(), CaptureScreenService.class);
                startService(intent);
                Log.i(TAG, "start service CaptureScreenService");
                finish();
            }
        }
    }
}
