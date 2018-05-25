package com.ledt.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ledt.R;
/**
 * 测试线程的使用，测试thread handler
 * 测试startactivityforresult的使用
 */

/**
 * Created by 13126 on 2017/8/11.
 */

public class RegistActivity extends Activity {


    private TextView YZM;
    private TextView YZM1;
    private TextView YZM2;
    private TextView YZM3;

    private int time;
    boolean istrue;
    private EditText et1;
    String abc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Intent intent=getIntent();
        Bundle aaa=intent.getExtras();
        abc=aaa.getString("abc");

        initview();
    }

    private void initview() {
        et1= (EditText) findViewById(R.id.et1);
        et1.setText(abc);
        YZM= (TextView) findViewById(R.id.YZM);
        YZM1= (TextView) findViewById(R.id.YZM1);
        YZM2= (TextView) findViewById(R.id.YZM2);
        YZM3= (TextView) findViewById(R.id.YZM3);
        YZM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time=59;
                istrue=true;
                YZM.setClickable(false);
                new Thread(new Theadshow()).start();
            }
        });
        YZM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YZM1.setClickable(false);
                new Thread(new Thread1()).start();
            }
        });
        YZM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YZM2.setClickable(false);
                new Thread(new Thread2()).start();
            }
        });
        YZM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YZM3.setClickable(false);
                new Thread(new Thread2()).start();
            }
        });
    }
    //线程类
    //多弄几个线程，来尝试了解异步线程中的handler+thread
    //还有一种asynctask在图片加载中有尝试
    class Thread1 implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(15000);
                Message msg=new Message();
                msg.what=2;
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class Thread2 implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(30000);
                Message msg=new Message();
                msg.what=3;
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread3 implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(45000);
                Message msg=new Message();
                msg.what=4;
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class Theadshow implements Runnable{

        @Override
        public void run() {
            while(istrue){
                try {
                    Thread.sleep(1000);
                    Message msg=new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //处理接收数据
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(time==0){
                    YZM.setText("获取验证码");
                    YZM.setClickable(true);
                    istrue=false;
                    //界面退出，返回数据
                    Intent intent=new Intent();
                    intent.putExtra("abcc",et1.getText().toString().trim());
                    intent.putExtra("abc3c",et1.getText().toString().trim()+"sdas");
                    setResult(1,intent);
                    finish();
                }else{
                    YZM.setText(time--+"s");
                }
            }else if(msg.what==2){
                YZM1.setText("我已经醒过来了");
            }else if(msg.what==3){
                YZM2.setText("我也已经醒过来了");
            }else if(msg.what==4){
                YZM3.setText("我也已经醒过来了");
            }
        }


    };
}
