package com.ledt.task;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.jimmy.common.listener.OnTaskFinishedListener;
import com.ledt.Api;
import com.ledt.MainActivity;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

import static com.dou361.dialogui.DialogUIUtils.showToast;

/**
 * Created by Lenovo on 2018/10/11.
 */

public class PostTask{
    private Context context;
    SharedPreferences sp=null;
    private BuildBean dialog;
    private String who;
    private ContactInterface callBack;
    //调用此方法就表示有人联系你了，注册到你这来
    public void setCallBack(String who,String question,ContactInterface callBack) {
        this.who = who;
        System.out.println("你说：当前联系到我的人是"+who+"，问题是"+question);
        this.callBack =callBack;

    }
    public void PostTask(final Context context, final ContactInterface callBack) {
        this.context = context;
        this.callBack =callBack;
        DialogUIUtils.init(context);
        sp=context.getSharedPreferences("Userinfo", context.MODE_PRIVATE);
        RequestParams params;
        params = new RequestParams((HttpCycleContext) context);
        params.addFormDataPart("username","test");
        params.addFormDataPart("password","654654");
        HttpRequest.post(Api.Login,params,500,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.i("onSuccess: ",jsonObject.toString() );
                callBack.callBackByTel(jsonObject.toString());
//                onSStart();
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog= DialogUIUtils.showLoading(context, "加载中...", false, false, true, true);
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dialog.dismiss();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                Log.i("onSuccess: ","失败");
                Toast.makeText(context,"请求超时",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onSStart(){
        Log.i("onSuccess: ","我是用来返回的");
    }

}
