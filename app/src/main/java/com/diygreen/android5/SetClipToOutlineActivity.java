package com.diygreen.android5;

import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewOutlineProvider;

public class SetClipToOutlineActivity extends AppCompatActivity {

    private View mViewTest1;
    private View mViewTest2;
    private View mViewTest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcliptooutline);

        initVeiw();
    }

    private void initVeiw() {
        this.mViewTest1 = findViewById(R.id.view_test1);
        this.mViewTest2 = findViewById(R.id.view_test2);
        this.mViewTest3 = findViewById(R.id.view_test3);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clip1:
                clip1();
                break;
            case R.id.btn_clip2:
                clip2();
                break;
            case R.id.btn_clip3:
                clip3();
                break;
        }
    }

    private void clip1() {
        ViewOutlineProvider outlineProvider = new CircleOutlineProvider();
        mViewTest1.setOutlineProvider(outlineProvider);
        mViewTest1.setClipToOutline(true);
    }

    private void clip2() {
        ViewOutlineProvider outlineProvider = new RoundRectOutlineProvider();
        mViewTest2.setOutlineProvider(outlineProvider);
        mViewTest2.setClipToOutline(true);
    }

    private void clip3() {
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int margin = 20;
                int size = view.getHeight();
                outline.setRect(margin, margin, size - margin, size - margin);
            }
        };
        mViewTest3.setOutlineProvider(viewOutlineProvider);
        mViewTest3.setClipToOutline(true);
    }
}
