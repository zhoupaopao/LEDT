package com.ledt.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ledt.receiver.MyReceiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 13126 on 2018/5/28.
 */

public class HorizonService extends Service {
    AlarmManager manager;
    PendingIntent pendingIntent1;
    int ii=0;
    //声明AMapLocationClient类对象
    public AMapLocationClient locationClient = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("打印时间", startId+"onStartCommand: ");

        //代表着开始服务
        //开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Log.i("run: 打印时间", new Date().toString());
                locationClient = new AMapLocationClient(getApplicationContext());
                //设置定位参数
                // 设置定位监听
//                locationClient.setLocationListener(locationListener);
                locationClient.startLocation();
            }
        }).start();
//        开启alarmManager
//        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int five=5000;//这里设置间隔时间是5s
        long triggerAtTime= SystemClock.elapsedRealtime()+five;//获取的是系统启动后的时间
        Intent i=new Intent(this,MyReceiver.class);//跳转到这个广播接收器，让这个广播接收器运行这个服务
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,0,i,0);
        pendingIntent1=pendingIntent;


//        if(startId==5){
//            manager.cancel(pendingIntent);
//        }else{
            //我不需要手机关机运行，但是需要在休眠的时候也运行
        //定时服务
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager= (AlarmManager) getSystemService(ALARM_SERVICE);

        Log.i("打印时间", "onCreate: ");
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopservice会进入ondestory
        Log.i("打印时间", "onDestroy: ");
        manager.cancel(pendingIntent1);
    }

}
