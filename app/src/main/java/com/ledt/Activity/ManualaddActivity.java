package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ledt.R;

import java.util.zip.Inflater;

/**
 * Created by Lenovo on 2019/5/7.
 */

public class ManualaddActivity extends Activity {
    LinearLayout llall;
    int num=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualadd);
        llall=findViewById(R.id.llall);

    }
    public void addView(View view) {
        TextView child = new TextView(this);
        View view1= LayoutInflater.from(this).inflate(R.layout.list_item,null);
        child.setTextSize(20);
        child.setTextColor(getResources().getColor(R.color.colorAccent));
        // 获取当前的时间并转换为时间戳格式, 并设置给TextView
        child.setText("手动加的"+num);
        num++;
        // 调用一个参数的addView方法
//        llall.addView(child,0);
        llall.addView(view1,0);
    }
}
