package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.ledt.R;
import com.ledt.adapter.ScrollAdapter;
import com.ledt.utils.DensityUtil;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/1.
 */

public class RefreshZDYBBActivity extends Activity {
    private XRefreshView xrefreshview;
    private LinearLayout linearLayout;
    Button changestyle;
    RecyclerView recycler_view_test_rv;
    XRefreshView xscreshview;
    ScrollAdapter adapter;
    private LinearLayoutManager layoutManager;

    private ArrayList<String>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_zdybb);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        xrefreshview=findViewById(R.id.xrefreshview);
        linearLayout=findViewById(R.id.linearlayout);
        changestyle=findViewById(R.id.changestyle);
        recycler_view_test_rv=findViewById(R.id.recycler_view_test_rv);
        recycler_view_test_rv.setHasFixedSize(true);
        xscreshview=findViewById(R.id.xscreshview);
        for(int i=0;i<5;i++){
            arrayList.add(i+"123");
        }

        adapter=new ScrollAdapter(arrayList,this);
    }

    private void initData() {
        for (int i=0;i<20;i++){
            TextView tv=new TextView(this);
            tv.setTextSize(16);
            int padding= DensityUtil.dip2px(this,20);
            tv.setPadding(padding,padding,0,0);
            tv.setTextIsSelectable(true);
            tv.setText("数据"+i);
            linearLayout.addView(tv);
        }
    }
    public void configXRfreshView(XRefreshView xRefreshView, XRefreshView.SimpleXRefreshListener listener){
        xRefreshView.setPullLoadEnable(true);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        //是否自动加载更新
        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setXRefreshViewListener(listener);
//        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
//        xRefreshView.enableReleaseToLoadMore(true);
//        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
    }
    private void initListener() {
        changestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xrefreshview.setVisibility(View.GONE);
                xscreshview.setVisibility(View.VISIBLE);

            }
        });
        layoutManager = new LinearLayoutManager(this);
        recycler_view_test_rv.setLayoutManager(layoutManager);
        recycler_view_test_rv.setAdapter(adapter);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        configXRfreshView(xscreshview, new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xscreshview.stopLoadMore();
                    }
                },1000);
//                xscreshview.stopLoadMore();
            }

            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                xscreshview.stopRefresh();
            }
        });
        configXRfreshView(xrefreshview,new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onLoadMore(boolean isSilence) {
                initData();
                xrefreshview.stopLoadMore();
                //创建子线程在这里不适用，是因为在显示加载完成等等东西的时候需要控制界面的，子线程不能对主线程布局进行控制
//                MyThread1 myThread1=new MyThread1();
//                myThread1.start();

            }

            @Override
            public void onRefresh(boolean isPullDown) {
//                MyThread myThread=new MyThread();
//                myThread.start();
                linearLayout.removeAllViews();
                initData();
                xrefreshview.stopRefresh();

            }
        });
    }
    class MyThread extends Thread{
        @Override
        public void run() {
//            xrefreshview.stopRefresh();
        }
    }
    class MyThread1 extends Thread{
        @Override
        public void run() {


        }
    }
}
