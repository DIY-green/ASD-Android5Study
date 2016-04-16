package com.diygreen.android5.newwidget2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

import com.diygreen.android5.R;

public class CardViewActivity extends AppCompatActivity {

    private CardView mContentCV;
    private SeekBar mValue1SB;
    private SeekBar mValue2SB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        initView();
        initListener();
    }

    private void initView() {
        this.mContentCV = (CardView) this.findViewById(R.id.cv_content);
        this.mValue1SB = (SeekBar) this.findViewById(R.id.sb_value1);
        this.mValue2SB = (SeekBar) this.findViewById(R.id.sb_value2);
    }

    private void initListener() {
        mValue1SB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mContentCV.setCardElevation(i);//shadow
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mValue2SB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mContentCV.setRadius(i);//圆角大小设置
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
