package com.ledt.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ledt.R;
import com.ledt.Service.Sssservice;

/**
 * Created by Lenovo on 2018/6/6.
 */

public class ServiceActivity extends Activity implements View.OnClickListener {
    //用于体验service的
    //service有两种启动方式
    //bind在调用者destory之后就会销毁，start不会
    //后期可以加入跨进程AIDL
    Button open_service;
    Button close_service;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
    }

    private void initView() {
        open_service=findViewById(R.id.open_service);
        close_service=findViewById(R.id.close_service);
        open_service.setOnClickListener(this);
        close_service.setOnClickListener(this);
        intent=new Intent();
        intent.setClass(this, Sssservice.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_service:
                stopService(intent);
                break;
            case R.id.open_service:
                startService(intent);
                break;
        }
    }
}
