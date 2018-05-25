package com.ledt.imageabout;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by 29423 on 2017/11/10 0010.
 */

public class MemoryCache {
    private static final int MAX_CACHE_CAPACITY = 30;

    private HashMap<String, SoftReference<Bitmap>> mCacheMap =
            new LinkedHashMap<String, SoftReference<Bitmap>>() {
                private static final long serialVersionUID = 1L;
                //重写LinkedHashMap的方法，当map数量超过规定大小（30），则会清除最早放入的元素
                protected boolean removeEldestEntry(Map.Entry<String,SoftReference<Bitmap>> eldest) {
                    return size() > MAX_CACHE_CAPACITY;
                };
            };
    public Bitmap get(String id){
        if(!mCacheMap.containsKey(id))
            return null;
        SoftReference<Bitmap> ref = mCacheMap.get(id);
        return ref.get();
    }
    public void put(String id, Bitmap bitmap){
        mCacheMap.put(id, new SoftReference<Bitmap>(bitmap));
    }
    public void clear() {
        try {
            for(Map.Entry<String, SoftReference<Bitmap>> entry : mCacheMap.entrySet()) {
                SoftReference<Bitmap> sr = entry.getValue();
                if(null != sr) {
                    Bitmap bmp = sr.get();
                    if(null != bmp)
                        bmp.recycle();
                }
            }
            mCacheMap.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
