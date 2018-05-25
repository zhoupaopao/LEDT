package com.ledt.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.ledt.Bean.DeviceBean;
import com.ledt.Bean.Group;
import com.ledt.Login.LoginBean;
import com.ledt.R;
import com.ledt.adapter.CarListAdapter;
import com.ledt.widget.pulltorefresh.PullToRefreshLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.finalteam.okhttpfinal.HttpCycleContext;


/**
 * Created by Lenovo on 2017/11/17.
 */

public class OkHttpActivity extends Activity {
    private EditText name;
    private EditText pwd;
    private Button submit;
    private Button chakan;
    private LoginBean userBean;
    private String result="";
    private RelativeLayout back;// 返回
    private TextView title;// 标题
    private TextView all_list;// 全部
    private TextView on_list;// 在线
    private TextView off_list;// 离线
    private int flag=0;//标识当前选中的是 全部0  在线1 离线2
    private TextView select;//筛选
    private EditText car_search;
    private LinearLayout requestFocuslinear;//抢焦点用
    private PullToRefreshLayout pullToRefreshLayout;
    private int nowpage=0;
    private PopupWindow mPopupWindowDialog;// popupwindow
    private SharedPreferences sp = null; // 存放用户信息
    private ProgressDialog progressDialog;
    private DeviceBean deviceBean;

    private ExpandableListView carListView;
    private CarListAdapter carListAdapter;
    Map<String, ArrayList<DeviceBean.CarListBean>> map = null;
    private ArrayList<Group> group = new ArrayList<Group>();
    private ArrayList<DeviceBean.CarListBean> carList;
//    private String url="http://m1api.chetxt.com:8083/Customer.asmx/Jsonp_GetLogin";
    private String url="http://m1api.chetxt.com:8083/customer.asmx/Jsonp_GetDevicePositionByUserID";
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
        donetwork();

//        initPupwindow();
        initListener();



    }

    private void donetwork() {
//        String name1=name.getText().toString().trim();
//        String pwd1=pwd.getText().toString().trim();
//        Log.i("name+pwd",name1+pwd1);
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
        params.put("tokenString","JdefLeelKBb3tmhj+Thwc8UDX9OiXS8LL/6J9OKHhA6PLmh2IetJlKcUMahLaUbp/wueOVl9KgDzKgRKT6ZDZQXsu5fV2tuymVhkCHgzN4E/A3gXLFuILeYlbK0KJPju8CssGwH6Leu03aumIXHfoat5Bm8US21ePuKg+vUUcEU=");
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
                    deviceBean=JSONObject.parseObject(json,DeviceBean.class);
                    Log.i("JSONObjectok",deviceBean.getDataList().toString() );
                    initCarList();
                    initSearchList(flag);
                    notifyAdapterDataChange();//数据有变化 刷新适配器
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
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    private void initListener() {
        all_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag = 0;
                all_list.setTextColor(Color.BLACK);
                all_list.setBackgroundColor(Color.parseColor("#cccccc"));
                on_list.setTextColor(Color.WHITE);
                on_list.setBackgroundColor(getResources().getColor(R.color.title));
                off_list.setTextColor(Color.WHITE);
                off_list.setBackgroundColor(getResources().getColor(R.color.title));
                initSearchList(flag);
                notifyAdapterDataChange();//数据有变化 刷新适配器

            }
        });

        on_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag = 1;
                all_list.setTextColor(Color.WHITE);
                all_list.setBackgroundColor(getResources().getColor(R.color.title));
                on_list.setTextColor(Color.BLACK);
                on_list.setBackgroundColor(Color.parseColor("#cccccc"));
                off_list.setTextColor(Color.WHITE);
                off_list.setBackgroundColor(getResources().getColor(R.color.title));
                initSearchList(flag);
                notifyAdapterDataChange();//数据有变化 刷新适配器
            }
        });

        off_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                flag = 2;
                all_list.setTextColor(Color.WHITE);
                all_list.setBackgroundColor(getResources().getColor(R.color.title));
                on_list.setTextColor(Color.WHITE);
                on_list.setBackgroundColor(getResources().getColor(R.color.title));
                off_list.setTextColor(Color.BLACK);
                off_list.setBackgroundColor(Color.parseColor("#cccccc"));
                initSearchList(flag);
                notifyAdapterDataChange();//数据有变化 刷新适配器
            }
        });
        pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                // 刷新
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                // 下拉加载
                System.out.println("开始下拉加载");
                //从网络获取数据
                AsyncHttpClient client=new AsyncHttpClient();
                RequestParams params=new RequestParams();
                //?queryString=&userID=3ab403f00d324cb9807dfbb7d8bd63ee&onLineType=0&isBindCar=0&page=1&pageSize=50&sortName=&asc=&showChild=true&tokenString=SNuQ+1fM8gr8tL90bcVJHg2sC32Le14dC3fatrhriNiae5/SHtGJFYN2WRhKFEI7eus1HsNKEwVTrK84pGOa2LYtS7pIPQNsx7GiwRXvju6guhpbBeDmjjT6asKS3WgMaFYUBOu5kGn8BtriknpV0vd91bHlcQzXnAZdxi5fmQY=
                params.put("queryString","");
                params.put("userID","3ab403f00d324cb9807dfbb7d8bd63ee");
                params.put("onLineType",0);
                params.put("isBindCar",0);
                params.put("page",1);
                params.put("pageSize",5*nowpage);
                params.put("sortName","");
                params.put("asc","");
                params.put("showChild",true);
                params.put("tokenString","JdefLeelKBb3tmhj+Thwc8UDX9OiXS8LL/6J9OKHhA6PLmh2IetJlKcUMahLaUbp/wueOVl9KgDzKgRKT6ZDZQXsu5fV2tuymVhkCHgzN4E/A3gXLFuILeYlbK0KJPju8CssGwH6Leu03aumIXHfoat5Bm8US21ePuKg+vUUcEU=");
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
                            deviceBean=JSONObject.parseObject(json,DeviceBean.class);
                            initCarList();
                            initSearchList(flag);
                            notifyAdapterDataChange();//数据有变化 刷新适配器
                            nowpage=nowpage+1;
                            // 下拉加载完毕 加载成功
                            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });
//                HttpRequest.post(url, getRequestParams( nowpage+ 1),
//                        new JsonHttpRequestCallback() {
//                            @Override
//                            protected void onSuccess(Headers headers, JSONObject jsonObject) {
//                                super.onSuccess(headers, jsonObject);
//                                System.out.println(jsonObject.toString());
//                                Log.e("789456123----->", jsonObject.toString());
//                                deviceBean = JSONObject.parseObject(jsonObject.toString(), DeviceBean.class);
//                                Log.e("CESHI",deviceBean.getDataList().size()+"");
//                                for(int u=0;u<deviceBean.getDataList().size();u++)
//                                    Log.e("7894561234----->", deviceBean.getDataList().get(u).getBS());
//                                initCarList();
//                                initSearchList(flag);
//                                notifyAdapterDataChange();//数据有变化 刷新适配器
//
//                                nowpage=nowpage+1;
//
//                                // 下拉加载完毕 加载成功
//                                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//                            }
//
//                            @Override
//                            public void onFailure(int errorCode, String msg) {
//                                super.onFailure(errorCode, msg);
//                                Log.e("Carlist", errorCode + msg);
//                                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
//                            }
//
//                        });


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString().trim();
                String pwd1=pwd.getText().toString().trim();
                Log.i("name+pwd",name1+pwd1);
                AsyncHttpClient client=new AsyncHttpClient();
                RequestParams params=new RequestParams();
                //?queryString=&userID=3ab403f00d324cb9807dfbb7d8bd63ee&onLineType=0&isBindCar=0&page=1&pageSize=50&sortName=&asc=&showChild=true&tokenString=SNuQ+1fM8gr8tL90bcVJHg2sC32Le14dC3fatrhriNiae5/SHtGJFYN2WRhKFEI7eus1HsNKEwVTrK84pGOa2LYtS7pIPQNsx7GiwRXvju6guhpbBeDmjjT6asKS3WgMaFYUBOu5kGn8BtriknpV0vd91bHlcQzXnAZdxi5fmQY=
                params.put("queryString","");
                params.put("userID","3ab403f00d324cb9807dfbb7d8bd63ee");
                params.put("onLineType",0);
                params.put("isBindCar",0);
                params.put("page",1);
                params.put("pageSize",50);
                params.put("sortName","");
                params.put("asc","");
                params.put("showChild",true);
                params.put("tokenString","JdefLeelKBb3tmhj+Thwc8UDX9OiXS8LL/6J9OKHhA6PLmh2IetJlKcUMahLaUbp/wueOVl9KgDzKgRKT6ZDZQXsu5fV2tuymVhkCHgzN4E/A3gXLFuILeYlbK0KJPju8CssGwH6Leu03aumIXHfoat5Bm8US21ePuKg+vUUcEU=");
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
                            deviceBean=JSONObject.parseObject(json,DeviceBean.class);
                            Log.i("JSONObjectok",deviceBean.getDataList().toString() );
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
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
//                RequestParams params = new RequestParams(OkHttpActivity.this);
//                params.addFormDataPart("userName",name1);
//                params.addFormDataPart("password", pwd1);
//                HttpRequest.post(url,params,new JsonHttpRequestCallback(){
//                    @Override
//                    protected void onSuccess(JSONObject jsonObject) {
//                        super.onSuccess(jsonObject);
//                        userBean=JSONObject.parseObject(jsonObject.toString(),LoginBean.class);
//                        if(userBean.getResult()!=0){//登录失败
////                            mView.showToast(String.valueOf(userBean.getErrorMsg()));
//                            Toast.makeText(OkHttpActivity.this,String.valueOf(userBean.getErrorMsg()),Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(OkHttpActivity.this,"成功",Toast.LENGTH_SHORT).show();
//
//
//                        }
//                    }
//                });
            }
        });
        chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OkHttpActivity.this,String.valueOf(deviceBean.getDataList()),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        name=findViewById(R.id.name);
        pwd=findViewById(R.id.pwd);
        submit=findViewById(R.id.submit);
        chakan=findViewById(R.id.chakan );
        all_list = (TextView) findViewById(R.id.all_list);
        on_list = (TextView) findViewById(R.id.on_list);
        off_list = (TextView) findViewById(R.id.off_list);
        flag = 0;
        all_list.setTextColor(Color.BLACK);
        all_list.setBackgroundColor(Color.parseColor("#cccccc"));
        on_list.setTextColor(Color.WHITE);
        on_list.setBackgroundColor(getResources().getColor(R.color.title));
        off_list.setTextColor(Color.WHITE);
        off_list.setBackgroundColor(getResources().getColor(R.color.title));

        carListView = (ExpandableListView) this
                .findViewById(R.id.expandableListView1);
        carListAdapter=new CarListAdapter(this,group,map);
        carListView.setAdapter(carListAdapter);
        carListView.expandGroup(0);

        progressDialog=new ProgressDialog(this);
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        requestFocuslinear = (LinearLayout) findViewById(R.id.request_focus_linear);
        car_search = (EditText) findViewById(R.id.car_search);
        requestFocuslinear.requestFocus();
    }
//    void initPupwindow(){
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.pupwindow_select, null);
//        RadioGroup radioGroup = (RadioGroup) view
//                .findViewById(R.id.carlist_select_radiogroup);
//        RadioButton radioAll = (RadioButton) view.findViewById(R.id.carlist_select_all);
//        RadioButton radioWired = (RadioButton) view.findViewById(R.id.carlist_select_wired);
//        RadioButton radioWireless = (RadioButton) view.findViewById(R.id.carlist_select_wireless);
//        RadioButton radioObd = (RadioButton) view.findViewById(R.id.carlist_select_obd);
//        radioAll.setChecked(true);
//
//
//        radioAll.setOnClickListener(new View.OnClickListener() {// 所有
//
//            @Override
//            public void onClick(View v) {
//            }
//        });
//        radioWired.setOnClickListener(new View.OnClickListener() {// 有线
//
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
//        radioWireless.setOnClickListener(new View.OnClickListener() {//无线
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        radioObd.setOnClickListener(new View.OnClickListener() {//obd
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        mPopupWindowDialog = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        mPopupWindowDialog.setFocusable(true);
//        mPopupWindowDialog.update();
//        mPopupWindowDialog.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindowDialog.setOutsideTouchable(true);
//    }
    //遍历一次找到总共有多少分组
    void initCarList(){

        all_list.setText("全部("+deviceBean.getAllCount()+")");
        on_list.setText("在线("+deviceBean.getOnLineCount()+")");
        off_list.setText("离线("+deviceBean.getOffLineCount()+")");
        group = new ArrayList<Group>();
        //遍历一次找到总共有多少分组
        for (DeviceBean.CarListBean carListBean : deviceBean.getDataList()) {
            boolean isnewGroup = true;
            for (Group groupStr : group) {
                if (carListBean.getGroupName().equals(groupStr.getGroupName())) {
                    isnewGroup = false;
                }
            }
            if (isnewGroup) {
                Group g = new Group();
                g.setGroupName(carListBean.getGroupName());
                group.add(g);
            }
        }
        Log.e("groupNub","----->"+group.size());
    }

    void notifyAdapterDataChange(){
        Log.e("0987890",group.size()+"");
        carListAdapter=new CarListAdapter(OkHttpActivity.this,group,map);
        carListView.setAdapter(carListAdapter);
        carListView.expandGroup(0);
    }

    //往各分组中存入数据
    void initSearchList(int i) {
        switch (i) {
            case 0:
                map = new HashMap<String, ArrayList<DeviceBean.CarListBean>>();
                for (Group groupStr : group) {
                    carList = new ArrayList<DeviceBean.CarListBean>();
                    for(int k=0;k<deviceBean.getDataList().size();k++){
                        DeviceBean.CarListBean carListBean=deviceBean.getDataList().get(k);
                        if (carListBean.getGroupName().equals(groupStr.getGroupName())) {
                            carListBean.setPosition(k);//分组后顺序打乱 记录下分组前的位置
                            carList.add(carListBean);
                        }
                    }
                    map.put(groupStr.getGroupName(), carList);
                }
                break;
            case 1://在线
                map = new HashMap<String, ArrayList<DeviceBean.CarListBean>>();
                for (Group groupStr : group) {
                    carList = new ArrayList<DeviceBean.CarListBean>();
                    for(int k=0;k<deviceBean.getDataList().size();k++){
                        DeviceBean.CarListBean carListBean=deviceBean.getDataList().get(k);
                        if (carListBean.getGroupName().equals(groupStr.getGroupName())
                                &&carListBean.isIsOnline() == true) {
                            carListBean.setPosition(k);//分组后顺序打乱 记录下分组前的位置
                            carList.add(carListBean);
                        }
                    }
                    map.put(groupStr.getGroupName(), carList);
                }
                break;
            case 2://离线
                map = new HashMap<String, ArrayList<DeviceBean.CarListBean>>();
                for (Group groupStr : group) {
                    carList = new ArrayList<DeviceBean.CarListBean>();
                    for(int k=0;k<deviceBean.getDataList().size();k++){
                        DeviceBean.CarListBean carListBean=deviceBean.getDataList().get(k);
                        if (carListBean.getGroupName().equals(groupStr.getGroupName())
                                &&carListBean.isIsOnline() == false) {
                            carListBean.setPosition(k);//分组后顺序打乱 记录下分组前的位置
                            carList.add(carListBean);
                        }
                    }
                    map.put(groupStr.getGroupName(), carList);
                }
                break;
            default:
                break;
        }

    }
    //传搜索框中的字符串 以及页数要加几  加载更多的时候就要传1 即页数要加1
    cn.finalteam.okhttpfinal.RequestParams getRequestParams( int page){
//		if(queryString.equals("")){
//			queryString=sp.getString(Constant.sp_queryString,"");
//		}
        cn.finalteam.okhttpfinal.RequestParams params = new cn.finalteam.okhttpfinal.RequestParams((HttpCycleContext) OkHttpActivity.this);
        params.addFormDataPart("userID", "3ab403f00d324cb9807dfbb7d8bd63ee");
        params.addFormDataPart("onLineType", 0);
        params.addFormDataPart("isBindCar", 0);
        params.addFormDataPart("queryString", "");
        params.addFormDataPart("page", 1);
        params.addFormDataPart("pageSize", 5 * page);
        params.addFormDataPart("sortName", "");
        params.addFormDataPart("asc", "");
        params.addFormDataPart("showChild", true);
        params.addFormDataPart("tokenString", "JdefLeelKBb3tmhj+Thwc8UDX9OiXS8LL/6J9OKHhA6PLmh2IetJlKcUMahLaUbp/wueOVl9KgDzKgRKT6ZDZQXsu5fV2tuymVhkCHgzN4E/A3gXLFuILeYlbK0KJPju8CssGwH6Leu03aumIXHfoat5Bm8US21ePuKg+vUUcEU=");
        return params;
    }



}
