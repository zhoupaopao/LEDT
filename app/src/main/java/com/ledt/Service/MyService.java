package com.ledt.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.ledt.receiver.MyReceiver;

public class MyService extends Service {
    AlarmManager manager;
    PendingIntent pendingIntent1;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("打印时间", "onCreate: ");
        manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("打印时间", "onStartCommand: ");
        //进行耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Log.i("打印时间", "睡醒了 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        long ttime= SystemClock.elapsedRealtime()+5000;//用的是系统启动后的时候
        //开启广播
        Intent intent1=new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent1,0);
        pendingIntent1=pendingIntent;
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,ttime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("打印时间", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("打印时间", "onUnbind: ");
        return super.onUnbind(intent);

    }

}
