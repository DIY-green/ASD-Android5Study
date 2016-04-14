package com.diygreen.android5.newwidget1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.diygreen.android5.R;

public class ToolbarDrawerLayoutActivity extends AppCompatActivity {

    private FragmentManager mFm;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getFragmentManager();
        setContentView(R.layout.activity_toolbardrawerlayout);

        initToolbar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);

        initLeftMenu();
        initContentView();
    }

    @NonNull
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setLogo(R.mipmap.ic_logo);
        mToolbar.setTitle("主标题");
        mToolbar.setSubtitle("副标题");
        // 这个方法很重要，不能少，让toolbar取代ActionBar
        setSupportActionBar(mToolbar);
    }

    private void initContentView() {
        Fragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title","Menu Item 1");
        fragment.setArguments(bundle);
        mFm.beginTransaction().replace(R.id.fl_containor,fragment).commit();
    }

    private void initLeftMenu() {
        ListView menuList = (ListView) findViewById(R.id.lv_menu);
        menuList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1
                , new String[]{"Menu Item 1","Menu Item 2","Menu Item 3"}));
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new ContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", ((TextView) view).getText().toString());
                fragment.setArguments(bundle);
                mFm.beginTransaction().replace(R.id.fl_containor, fragment).commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    /**
     * 必须重写该方法创建菜单，不然菜单不会显示在Toolbar中
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
