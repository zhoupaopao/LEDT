package com.ledt.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.ledt.Bean.FileBean;
import com.ledt.R;
import com.ledt.adapter.SimpleTreeAdapter;
import com.ledt.tree.TreeListViewAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhy.tree.bean.Node;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13126 on 2017/9/8.
 */

public class ListActivity3 extends Activity {
    private ListView list;
    private SharedPreferences sp;
    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ListView mTree;
    private TreeListViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_list);
//        R.drawable.tree_ec=0x7f020001;
//        R.drawable.tree_ex=0x7f020002;
//        R.drawable.ab_share123=0x7f020073;
        mTree= (ListView) findViewById(R.id.id_tree);

        initDatas();
        setView();
        initDatas2();
        chall();
    }



    private void chall() {
        Toast.makeText(getApplicationContext(), "111",
                Toast.LENGTH_SHORT).show();
        try
        {
            mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);

            mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int i) {
//                    if(node.isLeaf()){
                        Toast.makeText(getApplicationContext(), node.getName(),
                                Toast.LENGTH_SHORT).show();
//                    }
                }
            });
            mAdapter.notifyDataSetChanged();
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    private void setView() {

        sp=getSharedPreferences("Userinfo", MODE_PRIVATE);

    }
    private void initDatas2() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("UserID", sp.getString("parentId", ""));
        params.put("TokenString", sp.getString("token", ""));
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
    }
    private void initDatas()

    {
        Toast.makeText(getApplicationContext(), "000",
                Toast.LENGTH_SHORT).show();

        // id , pid , label , 其他属性
        mDatas.add(new FileBean(1, 0, "文件管理系统"));
        mDatas.add(new FileBean(2, 1, "游戏"));
        mDatas.add(new FileBean(3, 1, "文档"));
        mDatas.add(new FileBean(4, 1, "程序"));
        mDatas.add(new FileBean(5, 2, "war3"));
        mDatas.add(new FileBean(6, 2, "刀塔传奇"));
        mDatas.add(new FileBean(13, 6, "刀塔传奇1"));
        mDatas.add(new FileBean(14, 6, "刀塔传奇2"));
        mDatas.add(new FileBean(7, 4, "面向对象"));
        mDatas.add(new FileBean(8, 4, "非面向对象"));

        mDatas.add(new FileBean(9, 7, "C++"));
        mDatas.add(new FileBean(10, 7, "JAVA"));
        mDatas.add(new FileBean(11, 7, "Javascript"));
        mDatas.add(new FileBean(12, 8, "C语言"));
        mDatas.add(new FileBean(15, 12, "C语言1"));
        mDatas.add(new FileBean(16, 12, "C语言2"));
        mDatas.add(new FileBean(17, 12, "C语言3"));
        mDatas.add(new FileBean(18, 12, "C语言4"));

        mDatas.add(new FileBean(19, 1, "新的"));
        mDatas.add(new FileBean(20, 19, "新的"));
        mDatas.add(new FileBean(21, 19, "新的"));
    }
}
