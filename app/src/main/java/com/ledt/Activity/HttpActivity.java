package com.ledt.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.ledt.Api;
import com.ledt.Bean.ChooseAccountBean;
import com.ledt.R;
import com.ledt.adapter.NewSimpleTreeAdapter;
import com.ledt.tree.TreeListViewAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by Lenovo on 2018/5/29.
 */

public class HttpActivity extends Activity {
    Button asynchttp;
    Button okhttp;
    private SharedPreferences sp;
    String token;
    String userid;
    private BuildBean dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();
        initListener();
    }

    private void initView() {
        asynchttp=findViewById(R.id.asynchttp);
        okhttp=findViewById(R.id.okhttp);
        sp=getSharedPreferences("Userinfo",MODE_PRIVATE);
        token=sp.getString("token","");
        userid=sp.getString("userid","");
    }

    private void initListener() {
        okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params=new RequestParams();
                params.addFormDataPart("UserID",userid);
                params.addFormDataPart("TokenString",token);
                //给api中的数据赋值
//        Api.BASE_API_URL="123";
                Log.i("initData: ", Api.GET_CHILD_TREE+"?UserID="+userid+"&TokenString="+token);
                HttpRequest.post(Api.GET_CHILD_TREE,params,new JsonHttpRequestCallback(){
                    @Override
                    protected void onSuccess(Headers headers, JSONObject jsonObject) {
                        super.onSuccess(headers, jsonObject);
                        DialogUIUtils.showToast(jsonObject.toString());
                        Log.i("onSuccess: ", jsonObject.toString());

                    }

                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        DialogUIUtils.showToast("请求失败");
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        dialog=DialogUIUtils.showLoading(HttpActivity.this,"加载中...",true,false,false,true);
                        dialog.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dialog.dialog.dismiss();
                    }
                });
            }
        });
        asynchttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client=new AsyncHttpClient();
                com.loopj.android.http.RequestParams params=new com.loopj.android.http.RequestParams();
                params.put("UserID",userid);
                params.put("TokenString",token);
                params.setContentEncoding("UTF-8");
                client.post(Api.GET_CHILD_TREE, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            String json=new String(bytes,"UTF-8").toString().trim();
                            DialogUIUtils.showToast(json);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });
            }
        });
    }
}
