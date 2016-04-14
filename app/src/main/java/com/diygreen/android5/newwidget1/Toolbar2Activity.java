package com.diygreen.android5.newwidget1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.diygreen.android5.R;

public class Toolbar2Activity extends AppCompatActivity {

    private Toolbar mTitleTB;
    private Menu mToolbarMenu;
    private Button mToolbarDemo2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        initView();
        initListener();
    }

    private void initView() {
        this.mTitleTB = (Toolbar) findViewById(R.id.tb_title);
        this.mToolbarDemo2Btn = (Button) findViewById(R.id.btn_toolbardemo2);
        this.mToolbarDemo2Btn.setVisibility(View.GONE);

        // 使用 setActionBar() 方法，取代原来的 ActionBar
        setActionBar(mTitleTB);
    }

    private void initListener() {
        this.mTitleTB.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.mTitleTB.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changetoolbar:
                changeToolbar();
                break;
        }
    }

    private void changeToolbar() {
        this.mTitleTB.setTitle("TestToolbar");
        this.mTitleTB.setSubtitle("Toolbar2Subtitle");
        this.mTitleTB.setNavigationIcon(R.mipmap.btn_back_lightblue);
        this.mTitleTB.setLogo(R.mipmap.ic_launcher);
        mToolbarMenu.clear();
        getMenuInflater().inflate(R.menu.toolbar_menu, mToolbarMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mToolbarMenu = menu;
        getMenuInflater().inflate(R.menu.toolbar_menu2, menu);
        return true;
    }

}
