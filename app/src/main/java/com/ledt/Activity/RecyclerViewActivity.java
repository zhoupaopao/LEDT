package com.ledt.Activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.ledt.Listener.RecyclerItemClickListener;
import com.ledt.R;
import com.ledt.adapter.MyRecycleAdpter;
import com.ledt.utils.DividerGridItemDecoration;

import java.util.ArrayList;

import library.ISlide;
import library.OnClickSlideItemListener;

/**
 * Created by Lenovo on 2018/5/16.
 */

public class RecyclerViewActivity extends Activity {
    RecyclerView recyclerView;
    MyRecycleAdpter adapter;
    ArrayList<String> names;
    XRefreshView xRefreshView;
    int mLoadCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        xRefreshView = findViewById(R.id.xrefreshview);
        names = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            names.add("name" + i);
        }
        recyclerView = findViewById(R.id.recycleview);
        adapter = new MyRecycleAdpter(RecyclerViewActivity.this, names);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
//        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        names.set(2, "sadasd");
                        names.remove(5);
//                        adapter.notifyItemRangeChanged(0,5) ;//列表从positionStart位置到itemCount数量的列表项进行数据刷新
                        adapter.notifyDataSetChanged();//整个数据刷新
                        xRefreshView.stopRefresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        for (int i = 0; i < 6; i++) {
//                            recyclerviewAdapter.insert(new Person("More ", mLoadCount + "21"),
//                                    recyclerviewAdapter.getAdapterItemCount());
//                        }
                        mLoadCount++;
                        if (mLoadCount >= 3) {//模拟没有更多数据的情况
                            xRefreshView.setLoadComplete(true);
                        } else {
                            names.add("12312");
                            adapter.notifyItemRangeInserted(names.size() - 1, names.size());//批量插入。从起始位置到结束位置
                            // 刷新完成必须调用此方法停止加载
                            xRefreshView.stopLoadMore(true);//true代表加载成功，false代表失败
                            //当数据加载失败 不需要隐藏footerview时，可以调用以下方法，传入false，不传默认为true
                            // 同时在Footerview的onStateFinish(boolean hideFooter)，可以在hideFooter为false时，显示数据加载失败的ui
//                            xRefreshView1.stopLoadMore(false);
                        }
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
    }

    private void initListener() {
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,new RecyclerItemClickListener.OnItemClickListener() {
//            public void onItemClick(View view, int position) {
//                //在此处做点击之后的逻辑处理
//                Toast.makeText(RecyclerViewActivity.this, "短按", Toast.LENGTH_SHORT).show();
//            };
//                @Override
//                public void onLongClick (View view,int position){
//                    //在此处做长按之后的逻辑处理
//                        Toast.makeText(RecyclerViewActivity.this, "长按", Toast.LENGTH_SHORT).show();
//                }
//            }));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setupRecyclerView(recyclerView);
//        adapter.setOnClickSlideItemListener(new OnClickSlideItemListener() {
//            @Override
//            public void onItemClick(ISlide iSlideView, View v, int position) {
//                Toast.makeText(v.getContext(), "click item position:" + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onClick(ISlide iSlideView, View v, int position) {
//                if (v.getId() == R.id.btn_del) {
//                    //在这儿执行删除操作
//                    iSlideView.close();
//                    names.remove(position);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        divider.setDrawable(getDrawable(R.drawable.custom_divider));
//        recyclerView.addItemDecoration(divider);
//
/// //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void addlistw(String sss) {

    }

}
