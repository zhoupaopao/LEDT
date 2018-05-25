package com.ledt.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.andview.refreshview.XRefreshView;
import com.ledt.Bean.DeviceBean;
import com.ledt.Bean.MylistBean;
import com.ledt.R;
import com.ledt.UI.CustomerFooter;
import com.ledt.widget.pulltorefresh.PullToRefreshLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lenovo on 2017/11/22.
 */

public class MyListview extends Activity {
    private ListView mylist;
    ProgressDialog dialog;
    private XRefreshView refreshView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String>alllist=new ArrayList<String>();
    private MylistBean mylistBean;
    private int beishu=1;
    private SharedPreferences sp=null;
    private String token;
    private String url="http://m1api.chetxt.com:8083/customer.asmx/Jsonp_GetDevicePositionByUserID";
    private List<MylistBean.carbean> carlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        initview();
        setOnClick();
    }

    private void setOnClick() {

    }
    private void donetwork() {


    }

    private void initview() {
        sp=getSharedPreferences("Userinfo",MODE_PRIVATE);
        token=sp.getString("token","");
        dialog=new ProgressDialog(this);
        mylist=findViewById(R.id.mylistview);
        //直接请求接口获取数据
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        //?queryString=&userID=3ab403f00d324cb9807dfbb7d8bd63ee&onLineType=0&isBindCar=0&page=1&pageSize=50&sortName=&asc=&showChild=true&tokenString=SNuQ+1fM8gr8tL90bcVJHg2sC32Le14dC3fatrhriNiae5/SHtGJFYN2WRhKFEI7eus1HsNKEwVTrK84pGOa2LYtS7pIPQNsx7GiwRXvju6guhpbBeDmjjT6asKS3WgMaFYUBOu5kGn8BtriknpV0vd91bHlcQzXnAZdxi5fmQY=
        params.put("queryString","");
        params.put("userID","3ab403f00d324cb9807dfbb7d8bd63ee");
        params.put("onLineType",0);
        params.put("isBindCar",0);
        params.put("page",1);
        params.put("pageSize",5);
        params.put("sortName","");
        params.put("asc","");
        params.put("showChild",true);
        params.put("tokenString",token);
        params.setContentEncoding("UTF-8");
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String json= null;
                try {
                    json = new String(bytes,"UTF-8").trim();
                    JSONTokener jsonparser=new JSONTokener(json);
                    org.json.JSONObject ob= (org.json.JSONObject) jsonparser.nextValue();
                    Log.i("JSONObjectok", ob.toString());
                    mylistBean= JSONObject.parseObject(json,MylistBean.class);
                    carlist=mylistBean.getDataList();
                    for(int j=0;j<carlist.size();j++){
                        alllist.add(carlist.get(j).getAddress());
                    }
                    adapter=new ArrayAdapter<String>(MyListview.this,android.R.layout.simple_list_item_1,alllist);
                    mylist.setAdapter(adapter);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("JSONObjectok","错误" );
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });


        refreshView=findViewById(R.id.custom_view1);

        refreshView.setPullLoadEnable(true);
        //设置在上拉加载被禁用的情况下，是否允许界面被上拉
//		refreshView.setMoveFootWhenDisablePullLoadMore(false);
        refreshView.setPinnedTime(1000);
        refreshView.setAutoLoadMore(false);
//		refreshView.setCustomHeaderView(new CustomHeader(this));
//		refreshView.setCustomHeaderView(new XRefreshViewHeader(this));
        refreshView.setMoveForHorizontal(true);
        refreshView.setCustomFooterView(new CustomerFooter(this));
//		refreshView.setPinnedContent(true);
        //设置当非RecyclerView上拉加载完成以后的回弹时间
        refreshView.setScrollBackDuration(300);
        refreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                alllist.clear();

                //下拉刷新
                AsyncHttpClient client=new AsyncHttpClient();
                RequestParams params=new RequestParams();
                //?queryString=&userID=3ab403f00d324cb9807dfbb7d8bd63ee&onLineType=0&isBindCar=0&page=1&pageSize=50&sortName=&asc=&showChild=true&tokenString=SNuQ+1fM8gr8tL90bcVJHg2sC32Le14dC3fatrhriNiae5/SHtGJFYN2WRhKFEI7eus1HsNKEwVTrK84pGOa2LYtS7pIPQNsx7GiwRXvju6guhpbBeDmjjT6asKS3WgMaFYUBOu5kGn8BtriknpV0vd91bHlcQzXnAZdxi5fmQY=
                params.put("queryString","");
                params.put("userID","3ab403f00d324cb9807dfbb7d8bd63ee");
                params.put("onLineType",0);
                params.put("isBindCar",0);
                params.put("page",1);
                params.put("pageSize",5);
                params.put("sortName","");
                params.put("asc","");
                params.put("showChild",true);
                params.put("tokenString",token);
                params.setContentEncoding("UTF-8");
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        String json= null;
                        try {
                            json = new String(bytes,"UTF-8").trim();
                            JSONTokener jsonparser=new JSONTokener(json);
                            org.json.JSONObject ob= (org.json.JSONObject) jsonparser.nextValue();
                            Log.i("JSONObjectok", ob.toString());
                            mylistBean= JSONObject.parseObject(json,MylistBean.class);
                            carlist=mylistBean.getDataList();
                            for(int j=0;j<carlist.size();j++){
                                alllist.add(carlist.get(j).getAddress());
                            }
                            adapter=new ArrayAdapter<String>(MyListview.this,android.R.layout.simple_list_item_1,alllist);
                            mylist.setAdapter(adapter);
                            //刷新成功
                            refreshView.stopRefresh();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Log.i("JSONObjectok","错误" );
                        //刷新失败
                        refreshView.stopRefresh(false);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
//                        dialog.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
//                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                final ArrayList<String>alllist1=new ArrayList<String>();
                 beishu=beishu+1;
                AsyncHttpClient client=new AsyncHttpClient();
                RequestParams params=new RequestParams();
                //?queryString=&userID=3ab403f00d324cb9807dfbb7d8bd63ee&onLineType=0&isBindCar=0&page=1&pageSize=50&sortName=&asc=&showChild=true&tokenString=SNuQ+1fM8gr8tL90bcVJHg2sC32Le14dC3fatrhriNiae5/SHtGJFYN2WRhKFEI7eus1HsNKEwVTrK84pGOa2LYtS7pIPQNsx7GiwRXvju6guhpbBeDmjjT6asKS3WgMaFYUBOu5kGn8BtriknpV0vd91bHlcQzXnAZdxi5fmQY=
                params.put("queryString","");
                params.put("userID","3ab403f00d324cb9807dfbb7d8bd63ee");
                params.put("onLineType",0);
                params.put("isBindCar",0);
                params.put("page",beishu);
                params.put("pageSize",5);
                params.put("sortName","");
                params.put("asc","");
                params.put("showChild",true);
                params.put("tokenString",token);
                params.setContentEncoding("UTF-8");
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        String json= null;
                        try {
                            json = new String(bytes,"UTF-8").trim();
                            JSONTokener jsonparser=new JSONTokener(json);
                            org.json.JSONObject ob= (org.json.JSONObject) jsonparser.nextValue();
                            Log.i("JSONObjectok", ob.toString());
                            mylistBean= JSONObject.parseObject(json,MylistBean.class);
                            carlist=mylistBean.getDataList();
                            if(carlist.size()==0){
                                //没有更多数据
                                refreshView.setLoadComplete(true);
                            }else{
                                for(int j=0;j<carlist.size();j++){
                                    alllist1.add(carlist.get(j).getAddress());
                                }
                                adapter.addAll(alllist1);
                                refreshView.stopLoadMore();
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Log.i("JSONObjectok","错误" );
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
//                        dialog.show();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

//                        dialog.dismiss();
                    }
                });

//                for (int i = 0; i < 10; i++) {
//                    Log.i("alllist", ""+alllist.size());
//                    Log.i("alllist", "我是" + (i + alllist.size()));
//                    alllist1.add("我是" + (i+(alllist.size())));
//                }
//
//                new Handler().postDelayed(new Runnable() {
//
//                    @SuppressLint("NewApi")
//                    @Override
//                    public void run() {
//                        if (alllist.size() <= 40) {
//                            if (Build.VERSION.SDK_INT >= 11) {
//                                Log.i("alllist", "进来过");
////                                adapter.clear();
//                                //其实这边应该是把数据加在了最初的list里面
//                                adapter.addAll(alllist1);
////                                adapter.notifyDataSetChanged();
//                            }
//                            refreshView.stopLoadMore();
//                        } else {
//                            refreshView.setLoadComplete(true);
//                        }
//                    }
//                }, 2000);
            }
        });

    }
}
