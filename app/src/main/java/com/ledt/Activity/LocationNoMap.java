package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ledt.R;

/**
 * Created by Lenovo on 2018/6/5.
 */

public class LocationNoMap extends CheckPermissionsActivity implements View.OnClickListener,AMapLocationListener{
    Button open_location;
    Button open_xhlocation;
    Button close_location;
    Button close_xhlocation;
    TextView address;
    AMapLocationClient locationClient=null;
    AMapLocationClient locationClient1=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_nomap);
        initView();
        initView1();
        initData();
        initListener();
    }

    private void initView1() {
        locationClient1 = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置签到场景，相当于设置为：
         * option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
         * option.setOnceLocation(false);
         * option.setOnceLocationLatest(false);
         * option.setMockEnable(false);
         * option.setWifiScan(true);
         *
         * 其他属性均为模式属性。
         * 如果要改变其中的属性，请在在设置定位场景之后进行
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
        locationClient1.setLocationOption(option);
        //设置定位监听
        locationClient1.setLocationListener(this);
    }

    private void initView() {
        open_location=findViewById(R.id.open_location);
        open_xhlocation=findViewById(R.id.open_xhlocation);
        close_location=findViewById(R.id.close_location);
        close_xhlocation=findViewById(R.id.close_xhlocation);
        address=findViewById(R.id.address);
        open_location.setOnClickListener(this);
        open_xhlocation.setOnClickListener(this);
        close_location.setOnClickListener(this);
        close_xhlocation.setOnClickListener(this);
        locationClient=new AMapLocationClient(this);
        AMapLocationClientOption option=new AMapLocationClientOption();
        /**
         * 设置签到场景，相当于设置为：
         * option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
         * option.setOnceLocation(true);
         * option.setOnceLocationLatest(true);
         * option.setMockEnable(false);
         * option.setWifiScan(true);
         * option.setGpsFirst(false);
         * 其他属性均为模式属性。
         * 如果要改变其中的属性，请在在设置定位场景之后进行
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        locationClient.setLocationOption(option);
        //设置定位监听
        locationClient.setLocationListener(this);
    }

    private void initData() {

    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_location:
                address.setText("正在获取数据...");
                if(locationClient!=null){
                    locationClient.startLocation();
                }

                break;
            case R.id.open_xhlocation:
                address.setText("正在定位...");
                if(null != locationClient1) {
                    //开始定位
                    locationClient1.startLocation();
                }
                break;
            case R.id.close_location:
                address.setText("");
                break;
            case R.id.close_xhlocation:
                address.setText("定位停止");
                if(null != locationClient1) {
                    locationClient1.stopLocation();
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation.getErrorCode()==AMapLocation.LOCATION_SUCCESS){
            address.setText("签到成功，经纬度为：("+aMapLocation.getLatitude()+","+aMapLocation.getLongitude()+")"+"\n"+"所在城市:"+aMapLocation.getCity());
        }else{
            //可以记录错误信息，或者根据错误错提示用户进行操作，Demo中只是打印日志
            Log.e("AMap", "签到定位失败，错误码：" + aMapLocation.getErrorCode() + ", " + aMapLocation.getLocationDetail());
            //提示错误信息
            address.setText("签到失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != locationClient){
            locationClient.onDestroy();
        }
        if(null != locationClient1){
            locationClient1.onDestroy();
        }
    }
}
