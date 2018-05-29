package com.ledt.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.ledt.R;

import java.util.List;

/**
 * Created by Lenovo on 2018/5/29.
 */

public class ViewPagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private List<Fragment>fragments;
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
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
