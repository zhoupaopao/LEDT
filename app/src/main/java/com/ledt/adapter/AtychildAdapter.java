package com.ledt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ledt.Bean.MessageBean;

import java.util.List;

/**
 * Created by 13126 on 2017/9/8.
 */

public class AtychildAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<MessageBean.messageBean> ais;

    public AtychildAdapter(Context context, List<MessageBean.messageBean> ais)
    {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.ais = ais;

    }

    @Override
    public int getCount() {
        return ais.size();
    }

    @Override
    public Object getItem(int position) {
        return ais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private static class ViewHolder{
        TextView label;
        TextView Qty;
    }
}
