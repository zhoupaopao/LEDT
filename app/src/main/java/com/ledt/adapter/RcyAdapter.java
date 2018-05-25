package com.ledt.adapter;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.ledt.R;

import java.util.List;

import library.SlideRecyclerViewBaseAdapter;
import library.SlideTouchView;

/**
 * Created by lingyiyong on 2017/8/18.
 */

public class RcyAdapter extends SlideRecyclerViewBaseAdapter {
    List<Integer> list;

    public RcyAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public int[] getBindOnClickViewsIds() {
        return new int[]{R.id.btn_del};
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tv.setText(String.valueOf(list.get(position)));

        /**
         * must call
         */
        bindSlidePosition(viewHolder.mSlideTouchView, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        Button click;
        SlideTouchView mSlideTouchView;
        public MyViewHolder(View itemView) {
            super(itemView);
            click=itemView.findViewById(R.id.click);
            tv = (TextView) itemView.findViewById(R.id.tv);
            mSlideTouchView = (SlideTouchView) itemView.findViewById(R.id.mSlideTouchView);
            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("onClick: ", "12313: ");
//                    AlertDialog.Builder;
                }
            });
            /**
             * must call
             */
            bindSlideState(mSlideTouchView);
        }
    }
}
