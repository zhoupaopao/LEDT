package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ledt.R;

/**
 * Created by Lenovo on 2019/4/15.
 */

public class SynchronizedActivity extends Activity {
    Button sync;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronized);
        sync=findViewById(R.id.sync);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClick: ", "onClick-start ");
//                SyncThread threadDeom=new SyncThread();
//                Thread thread=new Thread(threadDeom);
//                thread.start();
//                SyncThread threadDeom1=new SyncThread();
//                Thread thread1=new Thread(threadDeom1);
//                thread1.start();
                SyncThread syncThread = new SyncThread();
                Thread thread1 = new Thread(syncThread, "SyncThread1");
                thread1.start();
                Thread thread2 = new Thread(syncThread, "SyncThread2");
                thread2.start();
                Log.i("onClick: ", "onClick-end");
            }
        });
    }
    class ThreadDeom implements Runnable{
        int num=1;
        @Override
        public void run() {
            for(int i=0;i<5;i++){
                synchronized (this){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("onClick: ", "我的车票是"+i);
                }
            }
        }
    }

}

/**
 * 同步线程
 */
class SyncThread implements Runnable {
    private static int count;

    public SyncThread() {
        count = 0;
    }

    public  void run() {
        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}