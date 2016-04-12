package com.diygreen.android5.newapi2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.diygreen.android5.R;

public class UsageStatsActivity extends AppCompatActivity {

    private TextView mShowResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usagestats);
        //Check if permission enabled
        if (UStats.getUsageStatsList(this).isEmpty()){
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }
        initView();
    }

    private void initView() {
        this.mShowResultTV = (TextView) findViewById(R.id.tv_showresult);
    }

    public void onClick(View v) {
        UStats.printCurrentUsageStatus(this, mShowResultTV);
    }

}
