package com.diygreen.android5.newapi1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.diygreen.android5.R;

public class ElevationTranslationZActivity extends AppCompatActivity {

    private static final String TAG = "ElevationTranslationZ";

    private boolean mFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevationtranslationz);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_testeleavation1:
                changeElevation(v);
                break;
            case R.id.tv_testeleavation2:
                changeElevation(v);
                break;
            case R.id.tv_testeleavation3:
                changeElevation(v);
                break;
            case R.id.tv_testeleavation4:
                changeElevation(v);
                break;
            case R.id.view_test1:
                changeTranslationZ(v);
                break;
        }
        mFlag = !mFlag;
    }

    private void changeElevation(View v) {
        v.setElevation(100.0f);
    }

    private void changeTranslationZ(View v) {
        if (mFlag) {
            v.setTranslationZ(120);
        } else {
            v.setTranslationZ(0);
        }
    }
}
