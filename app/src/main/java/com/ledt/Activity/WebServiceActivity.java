package com.ledt.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ledt.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by Lenovo on 2018/6/14.
 */

public class WebServiceActivity extends Activity {
    private static String SOAP_ACTION = "http://WebXml.com.cn/getRegionProvince";
    private static String NAMESPACE = "http://WebXml.com.cn/";
    private static String METHOD_NAME = "getRegionProvince";
    private static String URL = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?WSDL";
    Button userweb;
    SoapObject result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);
        initView();
        initListener();
    }

    private void initView() {
        userweb = findViewById(R.id.userweb);
    }

    private void initListener() {
        userweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownLoadTask().execute();
            }
        });
    }

    class DownLoadTask extends AsyncTask<Void,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            result=getInformation();//在子线程中请求webservice
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            StringBuilder builder = new StringBuilder();
            //解析返回的数据
            for(int i=0;i<result.getPropertyCount();i++){
                builder.append(result.getProperty(i));
            }
            Toast.makeText(WebServiceActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private SoapObject getInformation(){
        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        try{
            HttpTransportSE transportSE=new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);
            SoapObject result=(SoapObject)envelope.bodyIn; //获取到返回的结果，并强制转换成SoapObject对象
            SoapObject test = (SoapObject)result.getProperty(0); //该对象中还嵌套了一个SoapObject对象，需要使用getProperty(0)把这个对象提取出来
            return test;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
