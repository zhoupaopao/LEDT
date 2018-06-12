package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ledt.R;
import com.ledt.adapter.EasyRecycleAdapter;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/7.
 */

public class UpTouchActivity extends Activity {
    RecyclerView recyclerView;
    ArrayList<String>arrayList;
    EasyRecycleAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uptouch);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        recyclerView=findViewById(R.id.recyclerView);
        arrayList=new ArrayList<>();
        for(int i=0;i<20;i++){
            arrayList.add("aaa"+i);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new EasyRecycleAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        adapter.Additem(arrayList);
//        adapter.notifyDataSetChanged();
    }

    private void initListener() {

    }
}
