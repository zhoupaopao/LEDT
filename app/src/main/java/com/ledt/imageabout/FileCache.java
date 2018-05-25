package com.ledt.imageabout;

import android.content.Context;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 29423 on 2017/11/10 0010.
 */

public class FileCache {
    private File mCacheDir;

    public FileCache(Context context, File cacheDir, String dir){
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            mCacheDir = new File(cacheDir, dir);
        else
            mCacheDir = context.getCacheDir();// 如何获取系统内置的缓存存储路径
        if(!mCacheDir.exists())
            mCacheDir.mkdirs();
    }

    public File getFile(String url){
        File f=null;
        try {
            String filename = URLEncoder.encode(url,"utf-8");
            f = new File(mCacheDir, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return f;

    }
    public void clear(){
        File[] files = mCacheDir.listFiles();
        for(File f:files)
            f.delete();
    }
}
