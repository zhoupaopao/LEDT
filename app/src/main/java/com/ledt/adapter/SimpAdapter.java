package com.ledt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ledt.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/1.
 */

public class SimpAdapter extends BaseAdapter {
    Context context;
    ArrayList<String>arrayList;
    public SimpAdapter(Context context, ArrayList<String>arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_tv,null);
            viewHolder.name=convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(arrayList.get(position));

        return convertView;
    }
    public class ViewHolder{
        TextView name;
    }
}
