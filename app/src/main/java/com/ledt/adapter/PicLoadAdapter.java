package com.ledt.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ledt.R;
import com.ledt.imageabout.AsyncImageLoader;
import com.ledt.imageabout.FileCache;
import com.ledt.imageabout.MemoryCache;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/8.
 */

public class PicLoadAdapter extends BaseAdapter {
    Context context;
    ArrayList<String>list;
    private static  LayoutInflater inflater=null;
    private AsyncImageLoader imageLoader;
    public PicLoadAdapter(Context context,ArrayList<String>list){
        this.list=list;
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MemoryCache mcache=new MemoryCache();//内存缓存
        File sdCard= Environment.getExternalStorageDirectory();//获取sd卡
        File cacheDir=new File(sdCard,"jereh_cache");//缓存更目录
        FileCache fileCache= new FileCache(context,cacheDir,"news_img");//文件缓存
        imageLoader=new AsyncImageLoader(context,mcache,fileCache);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_pic_list,parent,false);
            viewHolder.pic=convertView.findViewById(R.id.pic);
            viewHolder.textView=convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //在这边对图和文字进行赋值
        String url=list.get(position);
//        String url=urllist[2];
//        String ttxt=urllist[1];
        viewHolder.pic.setTag(url);
        //异步加载图片，先从一级缓存、再二级缓存、最后网络获取图片
        Bitmap bmp = imageLoader.loadBitmap(viewHolder.pic, url);
        if(bmp == null) {
            viewHolder.pic.setImageResource(R.drawable.default_big);
        } else {
            viewHolder.pic.setImageBitmap(bmp);
        }
        return convertView;
    }
    public void addList(ArrayList<String>li){
        list.addAll(li);
    }
    class ViewHolder {
        ImageView pic;
        TextView textView;
    }
}
