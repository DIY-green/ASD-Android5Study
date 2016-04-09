package com.diygreen.android5;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ActivityTransitionsActivity extends AppCompatActivity {

    private static final int TRANSITION_DURATION = 1000;

    private TextView mShareTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_activitytransitions);
        initView();
    }

    private void initView() {
        this.mShareTV = (TextView) findViewById(R.id.tv_share);
    }

    private void initExplodeTransition() {
        // 设置一个 enter transition
        getWindow().setEnterTransition(new Explode().setDuration(TRANSITION_DURATION));
        // 设置一个 exit transition
        getWindow().setExitTransition(new Explode());
    }

    private void initSlideTransition() {
        // 设置一个 enter transition
        getWindow().setEnterTransition(new Slide().setDuration(TRANSITION_DURATION));
        // 设置一个 exit transition
        getWindow().setExitTransition(new Slide());
    }

    private void initFadeTransition() {
        // 设置一个 enter transition
        getWindow().setEnterTransition(new Fade().setDuration(TRANSITION_DURATION));
        // 设置一个 exit transition
        getWindow().setExitTransition(new Fade());
    }

    private void initShareElementTransition() {
        // 共享元素 Transition 的 enter 效果
        getWindow().setSharedElementEnterTransition(new Explode().setDuration(TRANSITION_DURATION));
        // 共享元素 Transition 的 exit 效果
        getWindow().setSharedElementExitTransition(new Explode().setDuration(TRANSITION_DURATION));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_explode:
                explode();
                break;
            case R.id.btn_slide:
                slide();
                break;
            case R.id.btn_fade:
                fade();
                break;
            case R.id.btn_sharedelements:
                sharedelements();
                break;
        }
    }

    private void explode() {
        initExplodeTransition();
        Intent intent = new Intent(this, ActivityTransitionsSecondActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void slide() {
        initSlideTransition();
        Intent intent = new Intent(this, ActivityTransitionsSecondActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void fade() {
        initFadeTransition();
        Intent intent = new Intent(this, ActivityTransitionsSecondActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void sharedelements() {
        initShareElementTransition();
        Intent intent = new Intent(this, ActivityTransitionsSecondActivity.class);
        // shareView: 需要共享的视图
        // "shareName": 设置的android:transitionName="shareName"
        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(this, mShareTV, "shareName");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
