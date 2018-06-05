package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ledt.Bean.CityBean;
import com.ledt.R;
import com.ledt.adapter.CityAdapter;
import com.ledt.stickyListHeaders.StickyListHeadersListView;
import com.ledt.utils.DividerItemDecoration;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018/6/5.
 */

public class SYActivity extends Activity {
    //索引列表，右边的东西被我屏蔽了，有需要可以开启
    private RecyclerView mRv;
    private LinearLayoutManager mManager;
    private CityAdapter mAdapter;
    private List<CityBean> mDatas = new ArrayList<>();
    private SuspensionDecoration mDecoration;
    private static final String INDEX_STRING_TOP = "↑";
    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        mAdapter = new CityAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(SYActivity.this, DividerItemDecoration.VERTICAL_LIST));
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        initDatas(getResources().getStringArray(R.array.provinces));
    }
    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                //微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((CityBean) new CityBean("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("群聊").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("标签").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mAdapter.setDatas(mDatas);
                mAdapter.notifyDataSetChanged();
                mIndexBar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 500);
    }

    private void initData() {

    }

    private void initListener() {

    }
    /**
     * 更新数据源
     *
     * @param view
     */
    public void updateDatas(View view) {
        for (int i = 0; i < 5; i++) {
            mDatas.add(new CityBean("东京"));
            mDatas.add(new CityBean("大阪"));
        }

        mIndexBar.setmSourceDatas(mDatas)
                .invalidate();
        mAdapter.notifyDataSetChanged();
    }
}
