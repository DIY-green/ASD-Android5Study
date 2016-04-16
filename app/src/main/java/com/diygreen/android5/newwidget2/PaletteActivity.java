package com.diygreen.android5.newwidget2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import com.diygreen.android5.R;

public class PaletteActivity extends AppCompatActivity {

    private ImageView mPaletteIV;
    private View mTest1View;
    private View mTest2View;
    private View mTest3View;
    private View mTest4View;
    private View mTest5View;
    private View mTest6View;
    private View mTest7View;
    private View mTest8View;
    private View mTest9View;
    private View mTest10View;
    private View mTest11View;
    private View mTest12View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        initView();
        initData();
    }

    private void initView() {
        mPaletteIV = (ImageView) findViewById(R.id.iv_palette);
        mTest1View = (View) findViewById(R.id.view_test1);
        mTest2View = (View) findViewById(R.id.view_test2);
        mTest3View = (View) findViewById(R.id.view_test3);
        mTest4View = (View) findViewById(R.id.view_test4);
        mTest5View = (View) findViewById(R.id.view_test5);
        mTest6View = (View) findViewById(R.id.view_test6);
        mTest7View = (View) findViewById(R.id.view_test7);
        mTest8View = (View) findViewById(R.id.view_test8);
        mTest9View = (View) findViewById(R.id.view_test9);
        mTest10View = (View) findViewById(R.id.view_test10);
        mTest11View = (View) findViewById(R.id.view_test11);
        mTest12View = (View) findViewById(R.id.view_test12);
    }

    private void initData() {
        initPaletteBySync();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sync:
                initPaletteBySync();
                break;
            case R.id.btn_async:
                initPaletteByAsync();
                break;
        }
    }

    private void initPaletteBySync() {
        mPaletteIV.setImageResource(R.drawable.bg_palette);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_palette);
        Palette palette = new Palette.Builder(bitmap).generate();
        setColorByPalette(palette);
    }

    private void initPaletteByAsync() {
        mPaletteIV.setImageResource(R.mipmap.bg_palette);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_palette);
        new Palette.Builder(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                setColorByPalette(palette);
            }
        });
    }

    private void setColorByPalette(Palette palette) {
        if (palette == null) return;
        //暗鲜艳色
        int darkVibrantColor = palette.getDarkVibrantColor(android.R.color.holo_blue_dark);
        //暗柔和的颜色
        int darkMutedColor = palette.getDarkMutedColor(android.R.color.holo_orange_dark);

        //亮鲜艳色(淡色)
        int lightVibrantColor = palette.getLightVibrantColor(android.R.color.holo_blue_bright);
        //亮柔和色(淡色)
        int lightMutedColor = palette.getLightMutedColor(android.R.color.holo_orange_light);

        //柔和色
        int mutedColor = palette.getMutedColor(android.R.color.holo_red_dark);
        //鲜艳色
        int vibrantColor = palette.getVibrantColor(android.R.color.holo_red_light);

        mTest1View.setBackgroundColor(darkVibrantColor);
        mTest2View.setBackgroundColor(darkMutedColor);
        mTest3View.setBackgroundColor(lightVibrantColor);
        mTest4View.setBackgroundColor(lightMutedColor);
        mTest5View.setBackgroundColor(mutedColor);
        mTest6View.setBackgroundColor(vibrantColor);

        Palette.Swatch swatch1 = palette.getMutedSwatch();
        Palette.Swatch swatch2 = palette.getVibrantSwatch();
        Palette.Swatch swatch3 = palette.getDarkMutedSwatch();
        Palette.Swatch swatch4 = palette.getDarkVibrantSwatch();
        Palette.Swatch swatch5 = palette.getLightMutedSwatch();
        Palette.Swatch swatch6 = palette.getLightVibrantSwatch();
        if (swatch1 != null) {
            mTest7View.setBackgroundColor(swatch1.getPopulation());
            mTest8View.setBackgroundColor(swatch1.getRgb());
            mTest9View.setBackgroundColor(swatch1.getBodyTextColor());
        }
        if (swatch2 != null) {
            mTest10View.setBackgroundColor(swatch2.getPopulation());
            mTest11View.setBackgroundColor(swatch2.getRgb());
            mTest12View.setBackgroundColor(swatch2.getTitleTextColor());
        }
    }
}
