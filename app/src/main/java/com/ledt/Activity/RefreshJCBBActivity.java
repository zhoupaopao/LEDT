package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.ledt.R;
import com.ledt.adapter.SimpAdapter;
import com.ledt.widget.MyListener;
import com.ledt.widget.PullToRefreshLayout;
import com.ledt.widget.pullableview.PullableListView;


import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/1.
 */
//采用PullableListView方案来进行下拉刷新
public class RefreshJCBBActivity extends Activity {
    PullableListView pull_refresh;
    ArrayList<String>arrayList;
    SimpAdapter adapter;
    PullToRefreshLayout refresh_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_jcbb);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        adapter=new SimpAdapter(this,arrayList);
        refresh_view.setOnRefreshListener(new MyListener(){


            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
//                super.onLoadMore(pullToRefreshLayout);
                for(int i=0;i<10;i++){
                    arrayList.add(i+"");
                }
                adapter.notifyDataSetChanged();
                //上拉加载
                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        // 千万别忘了告诉控件刷新完毕了哦！
                        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

                    }
                }.sendEmptyMessageDelayed(0, 3000);

//                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
//                super.onRefresh(pullToRefreshLayout);
                new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        // 千万别忘了告诉控件刷新完毕了哦！
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

                    }
                }.sendEmptyMessageDelayed(0, 2000);
            }
        });
        pull_refresh.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0;i<20;i++){
            arrayList.add(i+"");
        }
    }

    private void initView() {
        pull_refresh=findViewById(R.id.pull_refresh);
        refresh_view=findViewById(R.id.refresh_view);
        arrayList=new ArrayList<>();
    }
}
