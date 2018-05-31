package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.ledt.R;

/**
 * Created by Lenovo on 2018/5/31.
 */


public class MapOverLayoutActivity extends Activity {
    private MapView mapView;
    private BaiduMap baiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapoverlayout);
        initView();

    }

    private void initView() {
        mapView=findViewById(R.id.mapview);
        baiduMap=mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); // 设置为一般地图
        baiduMap.setMyLocationEnabled(true);
    }
}
