package com.ledt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.ledt.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/4.
 */

public class ScrollAdapter extends BaseRecyclerAdapter<ScrollAdapter.ScrollAdapterViewHolder> {
    private ArrayList<String> list;
    private Context context;

    public ScrollAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ScrollAdapterViewHolder getViewHolder(View view) {
        return new ScrollAdapterViewHolder(view);
    }

    @Override
    public ScrollAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_text, parent, false);
        ScrollAdapterViewHolder viewHolder=new ScrollAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScrollAdapterViewHolder holder, int position, boolean isItem) {
        holder.name.setText(list.get(position));
    }



    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    public class ScrollAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ScrollAdapterViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);


        }
    }
}
