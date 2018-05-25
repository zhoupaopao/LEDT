package com.ledt.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ledt.Activity.NavigationListActivity;
import com.ledt.Bean.CarBrandBean;
import com.ledt.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/5/24.
 */
//对拥有右侧导航栏的列表进行适配
public class SortAdapter extends BaseAdapter {
    ArrayList<CarBrandBean.BrandListBean> brandListBeans;
    LayoutInflater layoutInflater;
    NavigationListActivity mcontext;
    public SortAdapter(NavigationListActivity mcontext, ArrayList<CarBrandBean.BrandListBean>brandListBeans){
        this.mcontext=mcontext;
        this.brandListBeans=brandListBeans;
        this.layoutInflater=LayoutInflater.from(mcontext);
    }
    @Override
    public int getCount() {
        return brandListBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return brandListBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder = null;
        if(view==null){
            viewholder=new Viewholder();
            view=layoutInflater.inflate(R.layout.item_select_city,null);
            viewholder.tvTitle = (TextView) view.findViewById(R.id.tv_city_name);
            viewholder.tvLetter = (TextView) view.findViewById(R.id.tv_catagory);
            view.setTag(viewholder);
        }else{
            viewholder= (Viewholder) view.getTag();
        }
        CarBrandBean.BrandListBean brandListBean=brandListBeans.get(i);

        //这里获取的是ASCII码，所以是int，按理说是A这种的
        int section=getSectionForPosition(i);
        //数组不能乱的，必须是整理好，从上到下的数组
        //当前的数组位置和第一次出现这个东西的数组位置比较，相同就显示
        if (i == getPositionForSection(section)) {
            viewholder.tvLetter.setVisibility(View.VISIBLE);
            viewholder.tvLetter.setText(brandListBean.getInitial());
        } else {
            viewholder.tvLetter.setVisibility(View.GONE);
        }
        Log.i("getView: ", section+"");
        Log.i("getView1: ", getPositionForSection(section)+"");
        viewholder.tvTitle.setText(brandListBean.getBrand_name());
        return view;
    }
    public static class Viewholder{
        TextView tvLetter;
        TextView tvTitle;
    }
    public int getSectionForPosition(int position) {
        return brandListBeans.get(position).getInitial().charAt(0);
    }
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = brandListBeans.get(i).getInitial();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
