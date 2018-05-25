package com.ledt.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.ledt.Api;
import com.ledt.Bean.CarBrandBean;
import com.ledt.R;
import com.ledt.UI.SideBar;
import com.ledt.adapter.SortAdapter;

import java.util.ArrayList;

import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

/**
 * Created by Lenovo on 2018/5/23.
 */

public class NavigationListActivity extends Activity {
    private SideBar sideBar;
    private TextView dialog;
    private ListView sortListView;
    private SharedPreferences sp;
    private String token;
    private BuildBean buildBean;
    private String TAG="NavigationListActivity";
    private ArrayList<CarBrandBean.BrandListBean>brandListBeans=new ArrayList<>();
    ArrayList<String> indexString = new ArrayList<>();
    SortAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationlist);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sp=getSharedPreferences("Userinfo",MODE_PRIVATE);
        token=sp.getString("token","");
    }

    private void initData() {
        //将屏幕中间的提示文字放入右侧导航栏中
        sideBar.setTextView(dialog);
        //请求列表数据
        RequestParams params=new RequestParams();
        params.addFormDataPart("token",token);
        HttpRequest.post( Api.GET_CAR_BRANDLIST,params,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.i(TAG, jsonObject.toString());
                CarBrandBean carBrandBean=JSONObject.parseObject(jsonObject.toString(),CarBrandBean.class);
                brandListBeans= (ArrayList<CarBrandBean.BrandListBean>) carBrandBean.getBrand_list();
                for(int i=0;i<brandListBeans.size();i++){
                    //获取到单个信息
                    CarBrandBean.BrandListBean brandListBean=brandListBeans.get(i);
                    //获取这个信息的首字母
                    String showzm=brandListBean.getInitial();
                    //将首字母大写后，和A-Z进行匹配
                    if(showzm.toUpperCase().matches("[A-Z]")){
                        //匹配到之后，判断首字母集合中是否已经有这个首字母了，没有添加
                        if(!indexString.contains(showzm)){
                            //没有，对数组进行添加
                            indexString.add(showzm);
                        }
                    }
                }
                sideBar.setIndexText(indexString);
                adapter=new SortAdapter(NavigationListActivity.this,brandListBeans);
                sortListView.setAdapter(adapter);


            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
            }

            @Override
            public void onStart() {
                super.onStart();
                buildBean=DialogUIUtils.showLoading(NavigationListActivity.this,"加载中...",false,false,true,true);
                buildBean.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                buildBean.dialog.dismiss();
            }
        });
    }

    private void initListener() {
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position!=-1){
                    sortListView.setSelection(position);
                }
            }
        });
    }
}
