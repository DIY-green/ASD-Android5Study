package com.diygreen.android5;

import android.app.Application;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;

/**
 *
 */
public class DIYApp extends Application {

    private int mResult;
    private Intent mIntent;
    private MediaProjectionManager mMediaProjectionManager;

    public int getmResult(){
        return mResult;
    }

    public Intent getmIntent(){
        return mIntent;
    }

    public MediaProjectionManager getMediaProjectionManager(){
        return mMediaProjectionManager;
    }

    public void setmResult(int result1){
        this.mResult = result1;
    }

    public void setmIntent(Intent intent1){
        this.mIntent = intent1;
    }

    public void setMediaProjectionManager(MediaProjectionManager mMediaProjectionManager){
        this.mMediaProjectionManager = mMediaProjectionManager;
    }
}
