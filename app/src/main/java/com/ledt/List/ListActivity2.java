package com.ledt.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ledt.Bean.ChildAccountBean;
import com.ledt.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13126 on 2017/8/25.
 */

public class ListActivity2 extends Activity {
    private Spinner sp1;
    private Spinner spinner;
    private Spinner spinner1;
    private List<String> data_list;
    private ProgressDialog progressDialog;
    private ArrayAdapter<String> arr_adapter;
    SharedPreferences sp;
    private ListView changge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        changge= (ListView) findViewById(R.id.change);
        sp=getSharedPreferences("Userinfo", MODE_PRIVATE);
        String pai=sp.getString("pai","111");
        if(pai=="111"){
            Toast.makeText(this,"请到list1里面添加品牌",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,pai,Toast.LENGTH_LONG).show();
        }
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //数据
        data_list = new ArrayList<String>();
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner1.setAdapter(arr_adapter);
        initDatas2();


    }
    private void initDatas2(){
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("UserID",sp.getString("parentId",""));
        params.put("TokenString",sp.getString("token",""));
        params.setContentEncoding("UTF-8");
        client.post("http://m1api.chetxt.com:8083/Customer.asmx/GetRecursiveUserByUserID", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "UTF-8").toString().trim();
                    JSONTokener jt = new JSONTokener(json);
                    JSONObject object1 = (JSONObject) jt.nextValue();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
        //  地址  参数  回调函数
//        HttpRequest.post(Api.GET_RECURSIVE, params, new JsonHttpRequestCallback() {
//            @Override
//            protected void onSuccess(Headers headers, JSONObject jsonObject) {
//                super.onSuccess(headers, jsonObject);
//                Log.e("-1222-------->", jsonObject.toString());
//                ChildAccountBean childAccountBean = JSONObject.parseObject(jsonObject.toString(), ChildAccountBean.class);
//
//
//                m=0;
//
//                serach.getText().toString().length();
//                Log.e("leng", serach.getText().toString().length() + "" + childAccountBean.getCustomerName() + "" + childAccountBean.getCustomerName().length());
//
//                for(int o=0;o<childAccountBean.getCustomerName().length()-serach.getText().toString().length()+1;o++)
//                {
//                    Log.e("OKM",childAccountBean.getCustomerName().substring(o,o+serach.getText().toString().length()));
//
//                    if(childAccountBean.getCustomerName().substring(o,o+serach.getText().toString().length()).equals(serach.getText().toString()))
//                    {
//                        userList.add(m, new UserchengeBean.messageBean());
//                        userList.get(m).setAleaQty(childAccountBean.getAleaQty());
//                        userList.get(m).setTempQty(childAccountBean.getTempQty());
//                        userList.get(m).setCustomerName(childAccountBean.getCustomerName());
//                        userList.get(m).setUserID(childAccountBean.getUserID());
//                    }
//                }
//
//                List<ChildAccountBean> childs1= childAccountBean.getChildren();
//                if(childs1!=null){
//                    for(ChildAccountBean child : childs1){
//                        child1(child);
//                    }
//                }
////                while (childAccountBean.getChildren()!=null)
////                {
////
////
////
////                    userList.add(m, new UserchengeBean.messageBean());
////                    userList.get(m).setAleaQty(childAccountBean.getAleaQty());
////                    userList.get(m).setTempQty(childAccountBean.getTempQty());
////                    userList.get(m).setCustomerName(childAccountBean.getCustomerName());
////                    userList.get(m).setUserID(childAccountBean.getUserID());
////                }
//
//
//                adapter1 = new AtychildAdapter(Atychildaccount.this, userList);
//                adapter1.notifyDataSetChanged();
//                mTree.setAdapter(adapter1);
//
//            }
//
//
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//                Log.e("alertMsg failure", errorCode + msg);
//
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                //show  progressdialog
//                progressDialog.setMessage("正在获取信息");
//                progressDialog.show();
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                if(progressDialog.isShowing()&&progressDialog!=null){
//                    progressDialog.dismiss();
//                }
//                //hide progressdialog
//            }
//        });
//        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {//+ item点击事件监听器
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//
//                System.out.println("position" + position);
//
//                // TODAuto-generated method stub
//                showDialog("是否切换账户"+userList.get(position).getCustomerName(),userList.get(position).getUserID(),
//                        userList.get(position).getCustomerName(), 0);
//            }
//        });

    }
}
