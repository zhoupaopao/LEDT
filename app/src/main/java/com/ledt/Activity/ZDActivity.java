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
import com.ledt.Bean.FileBean;
import com.ledt.R;
import com.ledt.adapter.NewSimpleTreeAdapter;
import com.ledt.tree.TreeListViewAdapter;
import com.zhy.tree.bean.Node;

import java.util.ArrayList;
import java.util.List;

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
    private NewSimpleTreeAdapter adapter;
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    ArrayList<String>idlist=new ArrayList<>();
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
        Log.i("initData: ", Api.GET_CHILD_TREE+"?UserID="+userid+"&TokenString="+token);
        HttpRequest.post(Api.GET_CHILD_TREE,params,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                DialogUIUtils.showToast("请求成功");
                Log.i("onSuccess: ", jsonObject.toString());
                ChooseAccountBean chooseAccountBean=JSONObject.parseObject(jsonObject.toString(),ChooseAccountBean.class);
                //请求到数据后，先把第一次的数据拿出来
                //做个假的数据，由于数据都是closed，我设置一个opened让他打开第一个列表
                chooseAccountBean.setState("opened");
                child(0,chooseAccountBean);
                if(chooseAccountBean.getChildren()==null){
                    DialogUIUtils.showToast("该账户下没有子账户");
                }
                try {
//                    mDatas.add
                    adapter=new NewSimpleTreeAdapter(zd_list,ZDActivity.this,mDatas,5);
                    adapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                        @Override
                        public void onClick(com.ledt.tree.Node node, int position) {
                            Log.e("---------->852",""+node.getName()+node.getId());
                            Log.e("---------->852",""+node.getName()+idlist.get(node.getId()-1));

                        }
                    });
                    zd_list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
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
        //写入一个值，代表着
        //因为返回的第一个数据总会有一个最早的，即第一级只有一个账号，这里可以不用for循环
        mDatas.add(new FileBean(mDatas.size()+1,parentid,chooseAccountBean.getText().toString(),chooseAccountBean.getState()));
        idlist.add(chooseAccountBean.getId());
        parentid=mDatas.size();
        List<ChooseAccountBean>childs=chooseAccountBean.getChildren();
        if(childs!=null){
            //有下级数据的
            for(int i=0;i<childs.size();i++){
                child(parentid,childs.get(i));
            }
        }
    }

    private void initListener() {

    }
}
