package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.ledt.Bean.Person;
import com.ledt.R;
import com.ledt.adapter.SimpleAdapter;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/1.
 */

public class RefreshSJBBActivity extends Activity {
    XRefreshView xRefreshView;
    RecyclerView recyclerView;
    SimpleAdapter simpleAdapter;
    ArrayList<Person>personList=new ArrayList<>();
    private LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_sjbb);
        initView();
        initData();
        initListener();
        doRefresh();
        //添加数据
        requestRecyclerViewData();
    }

    private void doRefresh() {
        configXRfreshView(xRefreshView, new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRefreshView.stopRefresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            simpleAdapter.insert(new Person("More ", "21" + simpleAdapter.getAdapterItemCount()),
                                    simpleAdapter.getAdapterItemCount());
                        }
                        // 刷新完成必须调用此方法停止加载
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });
    }

    private void initView() {
        xRefreshView=findViewById(R.id.xrefreshview);
        recyclerView=findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);
        simpleAdapter=new SimpleAdapter(personList,this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(simpleAdapter);
        simpleAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
    }

    private void initData() {

    }

    private void initListener() {

    }
    private void configXRfreshView(XRefreshView xRefreshView, XRefreshView.SimpleXRefreshListener listener) {
        xRefreshView.setPullLoadEnable(true);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setAutoLoadMore(true);
        //两种方式设置空布局，传入空布局的view或者传入布局id都可以
//        TextView textView = new TextView(this);
//        textView.setText("没有数据，点击刷新");
//        textView.setGravity(Gravity.CENTER);
//        xRefreshView.setEmptyView(textView);
        xRefreshView.setEmptyView(R.layout.layout_emptyview);
        xRefreshView.setXRefreshViewListener(listener);
    }
    private void requestRecyclerViewData() {
        personList.clear();
        for (int i = 0; i < 6; i++) {
            Person person = new Person("name" + i, "" + i);
            personList.add(person);
        }
        simpleAdapter.setData(personList);
    }
}
