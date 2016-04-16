package com.diygreen.android5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.diygreen.android5.newwidget1.MaterialThemeActivity;
import com.diygreen.android5.newwidget1.RippleDrawableActivity;
import com.diygreen.android5.newwidget1.ToolbarActivity;
import com.diygreen.android5.newwidget2.CardViewActivity;
import com.diygreen.android5.newwidget2.MaterialDialogActivity;
import com.diygreen.android5.newwidget2.PaletteActivity;

public class Android5NewWidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android5newwidget);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rippledrawable:
                overlay(RippleDrawableActivity.class);
                break;
           case R.id.btn_toolbar:
                overlay(ToolbarActivity.class);
                break;
           case R.id.btn_materialtheme:
                overlay(MaterialThemeActivity.class);
                break;
           case R.id.btn_materialdialog:
                overlay(MaterialDialogActivity.class);
                break;
           case R.id.btn_palette:
                overlay(PaletteActivity.class);
                break;
           case R.id.btn_cardview:
                overlay(CardViewActivity.class);
                break;
        }
    }

    private void overlay(Class<? extends Activity> clazz) {
        Intent intent = new Intent(Android5NewWidgetActivity.this, clazz);
        startActivity(intent);
    }
}
