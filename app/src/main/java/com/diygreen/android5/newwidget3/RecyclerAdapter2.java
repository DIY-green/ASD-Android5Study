package com.diygreen.android5.newwidget3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diygreen.android5.R;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.viewHolder> {

    private ArrayList<String> items = new ArrayList<>();
    private OnRVItemClickListener mListener;

    public RecyclerAdapter2(ArrayList<String> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnRVItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview,
                viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, final int position) {
        String info = items.get(position);
        View view = viewHolder.itemView;
        TextView textView = (TextView) view.findViewById(R.id.tv_info);
        textView.setText(info);
        //手动更改高度，不同位置的高度有所不同
        textView.setHeight(100 + (position % 3) * 30);
        if (mListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(View itemView) {
            super(itemView);
        }
    }
}