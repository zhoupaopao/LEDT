package com.ledt.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.ledt.Api;
import com.ledt.Bean.ChooseAccountBean;
import com.ledt.R;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by Lenovo on 2018/5/28.
 */

//多级列表

public class ZDActivity extends Activity {
    private String token;
    private String userid;
    private SharedPreferences sp;
    private ListView zd_list;
    private BuildBean dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhedie);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        sp=getSharedPreferences("Userinfo",MODE_PRIVATE);
        token=sp.getString("token","");
        userid=sp.getString("userid","");
        zd_list=findViewById(R.id.zd_list);
    }

    private void initData() {
        //请求整个列表的数据
        RequestParams params=new RequestParams();
        params.addFormDataPart("UserID",userid);
        params.addFormDataPart("TokenString",token);
        //给api中的数据赋值
//        Api.BASE_API_URL="123";
        HttpRequest.post(Api.GET_CHILD_TREE,params,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                DialogUIUtils.showToast("请求成功");
                Log.i("onSuccess: ", jsonObject.toString());
                ChooseAccountBean chooseAccountBean=JSONObject.parseObject(jsonObject.toString(),ChooseAccountBean.class);
                //请求到数据后，先把第一次的数据拿出来
                child(0,chooseAccountBean);
                if(chooseAccountBean.getChildren()==null){
                    DialogUIUtils.showToast("该账户下没有子账户");
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                DialogUIUtils.showToast("请求失败");
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog=DialogUIUtils.showLoading(ZDActivity.this,"加载中...",true,false,false,true);
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dialog.dismiss();
            }
        });
    }

    private void child(int parentid, ChooseAccountBean chooseAccountBean) {
        //讲获取到的数据进行整理，变成适合空间的数据

    }

    private void initListener() {

    }
}
