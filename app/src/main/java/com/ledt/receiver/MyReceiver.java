package com.ledt.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ledt.Service.MyService;

/**
 * Created by Lenovo on 2018/6/6.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context, MyService.class);
        context.startService(intent1);
    }
}