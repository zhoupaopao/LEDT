package com.ledt.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ledt.R;

/**
 * Created by Lenovo on 2018/6/1.
 */

public class RefreshActivity extends Activity implements View.OnClickListener {
    Button jcbb;
    Button sjbb;
    Button zdybb;
    Intent intent=new Intent();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initView();

    }

    private void initView() {
        jcbb=findViewById(R.id.jcbb);
        sjbb=findViewById(R.id.sjbb);
        zdybb=findViewById(R.id.zdybb);
        jcbb.setOnClickListener(this);
        sjbb.setOnClickListener(this);
        zdybb.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jcbb:
                intent.setClass(this,RefreshJCBBActivity.class);
                startActivity(intent);
                break;
            case R.id.sjbb:
                intent.setClass(this,RefreshSJBBActivity.class);
                startActivity(intent);
                break;
            case R.id.zdybb:
                intent.setClass(this,RefreshZDYBBActivity.class);
                startActivity(intent);
                break;
        }
    }
}
