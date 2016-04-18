package com.diygreen.android5.newwidget3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.diygreen.android5.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements OnRVItemClickListener {

    private RadioGroup mLayoutTypeRG;
    private RecyclerView mContentRV;
    private Switch mDecorationSwitch;
    private Switch mLayoutTypeSwitch;

    private int mOrientation;
    private DIYDecoration mDIYDecoration;
    private ArrayList<String> mDataList;
    private RecyclerAdapter1 mAdapter1;
    private RecyclerAdapter2 mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initView();
        initListener();
        changeToType1();
    }

    private void initView() {
        this.mLayoutTypeRG = (RadioGroup) findViewById(R.id.rg_layouttype);
        this.mContentRV = (RecyclerView) findViewById(R.id.rv_content);
        this.mDecorationSwitch = (Switch) findViewById(R.id.switch_decoration);
        this.mLayoutTypeSwitch = (Switch) findViewById(R.id.switch_layouttype);

        mContentRV.setItemAnimator(new DefaultItemAnimator());
        mDIYDecoration = new DIYDecoration(RecyclerViewActivity.this, OrientationHelper.HORIZONTAL);
    }

    private void initListener() {
        this.mLayoutTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_type1:
                        changeToType1();
                        break;
                    case R.id.rb_type2:
                        changeToType2();
                        break;
                    case R.id.rb_type3:
                        changeToType3();
                        break;
                }
            }
        });
        this.mDecorationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDIYDecoration = new DIYDecoration(RecyclerViewActivity.this,
                        isChecked ?
                            OrientationHelper.HORIZONTAL:
                            OrientationHelper.VERTICAL);
            }
        });
        this.mLayoutTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mOrientation = isChecked ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                mDataList.add(0, "DIY-ITEM:NEW");
                if (mAdapter1 != null) {
                    mAdapter1.notifyItemInserted(0);
                }
                if (mAdapter2 != null) {
                    mAdapter2.notifyItemInserted(0);
                }
                break;
            case R.id.btn_delete:
                mDataList.remove(0);
                if (mAdapter1 != null) {
                    mAdapter1.notifyItemRemoved(0);
                }
                if (mAdapter2 != null) {
                    mAdapter2.notifyItemRemoved(0);
                }
                break;
        }
    }

    private void changeToType1() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(mOrientation);
        // 设置布局管理器
        mContentRV.setLayoutManager(layoutManager);
        mContentRV.addItemDecoration(mDIYDecoration);
        mDataList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mDataList.add("DIY-ITEM:" + i);
        }
        mAdapter1 = new RecyclerAdapter1(mDataList);
        mAdapter1.setOnItemClickListener(this);
        mContentRV.setAdapter(mAdapter1);
    }

    private void changeToType2() {
        GridLayoutManager layoutManager = new GridLayoutManager(
                this,
                3,
                mOrientation, // 可以试一下 设置为 HORIZONTAL 的效果
                false); // 可以试一下 设置为 true 的效果
        // 设置布局管理器
        mContentRV.setLayoutManager(layoutManager);
        mContentRV.addItemDecoration(mDIYDecoration);
        mDataList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mDataList.add("DIY-ITEM:" + i);
        }
        mAdapter1 = new RecyclerAdapter1(mDataList);
        mAdapter1.setOnItemClickListener(this);
        mContentRV.setAdapter(mAdapter1);
    }

    private void changeToType3() {
        int spanCount = 2; // 列数为两列
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(
                spanCount,
                mOrientation);
        mContentRV.setLayoutManager(mLayoutManager);
        mContentRV.addItemDecoration(mDIYDecoration);
        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDataList.add("DIY-ITEM:" + i);
        }
        mAdapter2 = new RecyclerAdapter2(mDataList);
        mAdapter2.setOnItemClickListener(this);
        mContentRV.setAdapter(mAdapter2);
    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, mDataList.get(position), Toast.LENGTH_SHORT).show();
    }
}
