package com.ledt.Activity;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.TableLayout;

import com.dou361.dialogui.DialogUIUtils;
import com.ledt.R;
import com.ledt.adapter.MyPagerAdapter;
import com.ledt.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2018/5/29.
 */

public class ViewPagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment>fragments;
    private TabLayout tableLayout;
    MyPagerAdapter adapter;
    private ArrayList<String>titlelist=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
        initListener();
    }

    private void initView() {
        viewPager=findViewById(R.id.viewpager);
        radioGroup=findViewById(R.id.radio_group);
        tableLayout=findViewById(R.id.tab_layout);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        tableLayout.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.white));
        tableLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
        ViewCompat.setElevation(tableLayout, 10);
        tableLayout.setupWithViewPager(viewPager);
        fragments= new ArrayList<Fragment>();
        fragments.add(new TestFragment("123"));
        fragments.add(new TestFragment("1234"));
        fragments.add(new TestFragment("12345"));
        titlelist.add("第一页");
        titlelist.add("第二页");
        titlelist.add("第三页");
        adapter=new MyPagerAdapter(getSupportFragmentManager(),fragments,titlelist);
        viewPager.setAdapter(adapter);
//        MyPagerAdapter
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        viewPager.setCurrentItem(0,true);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1,true);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2,true);
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        DialogUIUtils.showToast("0");
                        radioGroup.check(R.id.rb1);
                        break;
                    case 1:
                        DialogUIUtils.showToast("1");
                        radioGroup.check(R.id.rb2);
                        break;
                    case 2:
                        DialogUIUtils.showToast("2");
                        radioGroup.check(R.id.rb3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case 0:
                        DialogUIUtils.showToast("00");
                        break;
                    case 1:
                        DialogUIUtils.showToast("11");
                        break;
                    case 2:
                        DialogUIUtils.showToast("22");
                        break;
                }
            }
        });

    }
    public void addpage(){
        titlelist.add("新的页面");
        fragments.add(new TestFragment("新的"));
        adapter.notifyDataSetChanged();
    }
}
