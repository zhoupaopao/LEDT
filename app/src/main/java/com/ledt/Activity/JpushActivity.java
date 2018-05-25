package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ledt.R;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Lenovo on 2018/5/18.
 */

public class JpushActivity extends Activity implements View.OnClickListener{
    private Button openjpush_b;
    private Button openjpush_bin;
    private Button close_jpush;
    private Button open_jpush;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        openjpush_b=findViewById(R.id.opnejpush_b);
        openjpush_bin=findViewById(R.id.opnejpush_bin);
        close_jpush=findViewById(R.id.close_jpush);
        open_jpush=findViewById(R.id.open_jpush);

    }

    private void initData() {

    }

    private void initListener() {
        openjpush_b.setOnClickListener(this);
        openjpush_bin.setOnClickListener(this);
        close_jpush.setOnClickListener(this);
        open_jpush.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.opnejpush_b:
                JPushInterface.setAlias(this, "zhoub",
                        new TagAliasCallback() {

                            @Override
                            public void gotResult(int responseCode,
                                                  String alias, Set<String> tags) {
                                Log.e("responseCode", responseCode + "time");
                                if (responseCode == 0) {
                                    Log.e("jieguo", "成功");
                                    Toast.makeText(JpushActivity.this,"成功",Toast.LENGTH_SHORT).show();

                                } else if (responseCode == 6002) {
//                            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000* 60);
                                    Log.e("jieguo", "失败");
                                }
                            }
                        });
                break;
            case R.id.opnejpush_bin:
                JPushInterface.setAlias(this, "zhoubin",
                        new TagAliasCallback() {

                            @Override
                            public void gotResult(int responseCode,
                                                  String alias, Set<String> tags) {
                                Log.e("responseCode", responseCode + "time");
                                if (responseCode == 0) {
                                    Log.e("jieguo", "成功");
                                    Toast.makeText(JpushActivity.this,"成功",Toast.LENGTH_SHORT).show();
                                } else if (responseCode == 6002) {
//                            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000* 60);
                                    Log.e("jieguo", "失败");
                                }
                            }
                        });
                break;
            case R.id.close_jpush:
                //关闭推送
                JPushInterface.stopPush(this);
                break;
            case R.id.open_jpush:
                //重新打开推送
                JPushInterface.resumePush(this);
                break;
        }
    }
}
