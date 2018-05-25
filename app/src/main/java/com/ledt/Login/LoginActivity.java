package com.ledt.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ledt.Main.Main;
import com.ledt.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;

/**
 * Created by 13126 on 2017/8/8.
 */

public class LoginActivity extends Activity {
    private EditText name;
    private EditText pwd;
    private Button login;
    private TextView regist;
    private SharedPreferences sp;
    private String url="http://m1api.chetxt.com:8083/Customer.asmx/Jsonp_GetLogin";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setview();
        setListener();
    }



    private void setview() {
        sp=getSharedPreferences("Userinfo",MODE_PRIVATE);
        name= (EditText) findViewById(R.id.ETYHM);
        pwd= (EditText) findViewById(R.id.ETMM);
        regist= (TextView) findViewById(R.id.zc);
        login= (Button) findViewById(R.id.login);
    }
    private void setListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userName password
                String username=name.getText().toString().trim();
                String password=pwd.getText().toString().trim();
                doNetWork(username,password);
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            //测试startActivityForResult的使用
            public void onClick(View v) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("abc","aaaaaaaaaa");
                intent.putExtras(bundle);
                intent.setClass(LoginActivity.this,RegistActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,0);
            }
        });
    }
//接收返回数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 1:Bundle b=data.getExtras();
                String abbc=b.getString("abcc");
                String baba=b.getString("abc3c");
                Toast.makeText(LoginActivity.this,abbc,Toast.LENGTH_LONG).show();
                Toast.makeText(LoginActivity.this,baba,Toast.LENGTH_LONG).show();
                break;
            case 2:Bundle c=data.getExtras();
                String abc=c.getString("abc");
                Toast.makeText(LoginActivity.this,abc,Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void doNetWork(String username, String password) {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",username);
        params.put("password",password);
        params.setContentEncoding("UTF-8");
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {
                    String json=new String(bytes,"UTF-8").trim();
                    JSONTokener jsonparser=new JSONTokener(json);
                    JSONObject ob= (JSONObject) jsonparser.nextValue();
                    if(ob.getInt("Result")==0){
                        Intent intent=new Intent();
                        intent.setClass(LoginActivity.this,Main.class);
                        startActivity(intent);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("parentId",ob.getString("UserID"));
                        editor.putString("token",ob.getString("Token"));
                        editor.commit();
                        JSONObject server=ob.getJSONObject("Server");
                        Log.i("SystemServer", server.getString("SystemServer"));
                        Toast.makeText(LoginActivity.this,server.getString("SystemServer"),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(LoginActivity.this,ob.getString("ErrorMsg"),Toast.LENGTH_LONG).show();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(LoginActivity.this,"网络错误",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void jiexi(String json) {
    }
}
