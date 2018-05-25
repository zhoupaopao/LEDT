package com.ledt.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ledt.Activity.Activity1;
import com.ledt.Activity.GridViewActivity;
import com.ledt.Activity.LinearRecyclerViewActivity;
import com.ledt.Api;
import com.ledt.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 测试activity的生命周期
 */

/**
 * Created by 13126 on 2017/8/22.
 */

public class ListActivity1 extends Activity{
    private ListView list1;
    private String url="http://118.178.141.251:8082/RiskService.asmx/Jsonp_GetCarBrandList";
    HashMap<String,Object>map;
    ArrayList<HashMap<String,Object>> alist1=new ArrayList<HashMap<String,Object>>();
    private  ProgressDialog progressDialog;
    ArrayList<String>al1=new ArrayList<String>();
    private List<String> allType=new ArrayList<String>();
    private Spinner sp1;
    ArrayAdapter model2Adapter;
    SharedPreferences sp;
    private Button bt1;
    private  Button bt2;
    private  Button bt3;
    private String TAG=Api.act;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list1);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_list1);
        progressDialog=new ProgressDialog(this);
        sp=getSharedPreferences("Userinfo",MODE_PRIVATE);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        bt3= (Button) findViewById(R.id.bt3);
        list1= (ListView) findViewById(R.id.list1);
        doNetWork();
        sp1= (Spinner) findViewById(R.id.spinner1);
        allType = new ArrayList<String>();
        allType.add("北京");
        allType.add("上海");
        allType.add("广州");
        allType.add("深圳");
//        model2Adapter=new ArrayAdapter<String>(ListActivity1.this,android.R.layout.simple_spinner_item,allType);
//        model2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp1.setAdapter(model2Adapter);


//        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String pp= (String) sp1.getAdapter().getItem((int)id);
//                Toast.makeText(ListActivity1.this,pp+"||"+al1.get(position),Toast.LENGTH_LONG).show();
//                Log.i("abc", "onItemSelected: ");
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(ListActivity1.this,"2312",Toast.LENGTH_LONG).show();
//                Log.i("abc", "onNothingSelected: ");
//            }
//        });
        inintview();
        setOnClick();


    }

    private void setOnClick() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ListActivity1.this, Activity1.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ListActivity1.this, LinearRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ListActivity1.this, GridViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inintview() {
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                String aaabbb  = (String)sp1.getAdapter().getItem((int) id);
                SharedPreferences.Editor editor=  sp.edit();
                editor.putString("pai",aaabbb);
                editor.commit();
                int series_id=position;
                sp1.setSelection(position);
                Log.e("OOO", aaabbb+position);
                setTitle(aaabbb + series_id);

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    private void doNetWork() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("token","AMzDkosl/a2XdVa7vkmCDCH/9UjYlArO3ANRdQaMzPr+1QL9z5gPyKPy6Q31AKUPWiVPGpa//nGH+qlKXFL1cSZLCSD69rgqpDEk3AYLvaPCEJ5PoZeNlcgilPfObCBLH7T0PnHtSTj6sP8OwFsCR3XHgTkST363unDjL90vZ4w=");
        params.setContentEncoding("UTF-8");
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json=new String(bytes,"UTF-8").toString().trim();
                    JSONTokener jsot=new JSONTokener(json);
                    JSONObject jo= (JSONObject) jsot.nextValue();
                    JSONArray ja=jo.getJSONArray("brand_list");
                    //获取整个list的数据
                    //遍历数据，进行赋值
                    for(int a=0;a<ja.length();a++){
                        JSONObject message=ja.getJSONObject(a);

                        map= new HashMap<String, Object>();
                        map.put("brand_id",message.getString("brand_id"));
                        map.put("brand_name",message.getString("brand_name"));
                        alist1.add(map);
                        al1.add(message.getString("brand_name"));
                    }
                    model2Adapter=new ArrayAdapter<String>(ListActivity1.this,android.R.layout.simple_spinner_item,al1);
                    model2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp1.setAdapter(model2Adapter);
                    MyAdapter adapter=new MyAdapter(ListActivity1.this);
                    list1.setAdapter(adapter);
                    list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HashMap<String,Object> hm1=alist1.get(position);
//                            HashMap<String,Object> hm= (HashMap<String, Object>) list1.getItemAtPosition(position);

                            Toast.makeText(ListActivity1.this,  hm1.get("brand_name")+"||"+hm1.get("brand_id"),Toast.LENGTH_LONG).show();
                        }
                    });
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
                progressDialog.setMessage("正在获取信息……");
                progressDialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if(progressDialog.isShowing()&&progressDialog!=null){
                    progressDialog.dismiss();
                }
            }
        });
    }
    public class MyAdapter extends BaseAdapter{

        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return alist1.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=mInflater.inflate(R.layout.item_list_text,null);
                holder.name= (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.name.setText((String) alist1.get(position).get("brand_name"));
            return convertView;
        }
    }
    public final  class ViewHolder{
        public TextView name;

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }
}
