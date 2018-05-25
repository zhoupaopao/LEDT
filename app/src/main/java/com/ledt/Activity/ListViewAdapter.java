package com.ledt.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ledt.R;
import com.ledt.imageabout.AsyncImageLoader;
import com.ledt.imageabout.FileCache;
import com.ledt.imageabout.MemoryCache;

import java.io.File;

/**
 * Created by 29423 on 2017/11/10 0010.
 */

class ListViewAdapter extends BaseAdapter{
    private Activity mActivity;
    private String[] data;
    private static LayoutInflater inflater=null;
    private AsyncImageLoader imageLoader;


    public ListViewAdapter(Activity mActivity, String[] d) {
        this.mActivity=mActivity;
        data=d;
        inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MemoryCache mcache=new MemoryCache();//内存缓存
        File sdCard = android.os.Environment.getExternalStorageDirectory();//获得SD卡
        File cacheDir = new File(sdCard, "jereh_cache" );//缓存根目录
        FileCache fcache=new FileCache(mActivity, cacheDir, "news_img");//文件缓存
        imageLoader = new AsyncImageLoader(mActivity, mcache,fcache);
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if(convertView==null){
                convertView = inflater.inflate(R.layout.item_list_imageandtext, null);
                vh=new ViewHolder();
                vh.tvTitle=(TextView)convertView.findViewById(R.id.text);
                vh.ivImg=(ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }
        vh.tvTitle.setText("aaaaaa"+position);
        vh.ivImg.setTag(data[position]);
        //异步加载图片，先从一级缓存、再二级缓存、最后网络获取图片
        Bitmap bmp = imageLoader.loadBitmap(vh.ivImg, data[position]);
        if(bmp == null) {
            vh.ivImg.setImageResource(R.drawable.default_big);
        } else {
            vh.ivImg.setImageBitmap(bmp);
        }
        return convertView;
    }
    private class ViewHolder{
        TextView tvTitle;
        ImageView ivImg;
    }
    public void destroy() {
        imageLoader.destroy();
    }
}
