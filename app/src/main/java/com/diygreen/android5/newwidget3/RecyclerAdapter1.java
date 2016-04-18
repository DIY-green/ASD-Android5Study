package com.diygreen.android5.newwidget3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {

    private List<String> mDataList;
    private OnRVItemClickListener mListener;

    public RecyclerAdapter1(List<String> dataList) {
        mDataList = dataList;
    }

    // 设置 Item 点击监听
    public void setOnItemClickListener(OnRVItemClickListener listener) {
        mListener = listener;
    }

    // 自定义的 ViewHolder，持有每个 Item 的所有 View 控件
    // 必须继承自 RecyclerView.ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

    // 获取Item的数量
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    // 将数据与 View 控件进行绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataList.get(position));
        if (mListener != null) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, position);
                }
            });
        }
    }

    // 创建新 View，被 LayoutManager 所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}