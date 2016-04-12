package com.diygreen.android5;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.diygreen.android5.newapi1.ActivityTransitionsActivity;
import com.diygreen.android5.newapi1.ElevationTranslationZActivity;
import com.diygreen.android5.newapi1.SetClipToOutlineActivity;
import com.diygreen.android5.newapi1.TintActivity;
import com.diygreen.android5.newapi2.HeadsUpActivity;
import com.diygreen.android5.newapi2.JobSchedulerActivity;
import com.diygreen.android5.newapi2.MediaProjectionActivity;
import com.diygreen.android5.newapi2.PdfRendererActivity;
import com.diygreen.android5.newapi2.UsageStatsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_android5api1:
                overlay(ElevationTranslationZActivity.class);
                break;
            case R.id.btn_android5api2:
                overlay(TintActivity.class);
                break;
            case R.id.btn_android5api3:
                overlay(SetClipToOutlineActivity.class);
                break;
            case R.id.btn_android5api4:
                overlay(ActivityTransitionsActivity.class);
                break;
            case R.id.btn_android5api5:
                setDIYTaskDescription();
                break;
            case R.id.btn_android5api6:
                overlay(MediaProjectionActivity.class);
                break;
            case R.id.btn_android5api7:
                overlay(JobSchedulerActivity.class);
                break;
            case R.id.btn_android5api8:
                overlay(HeadsUpActivity.class);
                break;
            case R.id.btn_android5api9:
                overlay(PdfRendererActivity.class);
                break;
            case R.id.btn_android5api10:
                overlay(UsageStatsActivity.class);
                break;
        }
    }

    private void setDIYTaskDescription() {
        String label = "DIY-green";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
        int colorPrimary = Color.parseColor("#666600");
        ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(label, bitmap, colorPrimary);
        setTaskDescription(taskDescription);
        bitmap.recycle();
        bitmap = null;
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }
}
