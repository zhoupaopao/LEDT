package com.ledt.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.ledt.R;
import com.ledt.fragment.FifthFragment;
import com.ledt.fragment.FirstFragment;
import com.ledt.fragment.ForthFragment;
import com.ledt.fragment.SecondFragment;
import com.ledt.fragment.SixthFragment;
import com.ledt.fragment.ThirdFragment;

import java.util.ArrayList;

/**
 * Created by 13126 on 2017/8/16.
 */

public class VPager extends FragmentActivity{
 private ViewPager vp;
    private ArrayList<Fragment> fs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vpager);
        initView();
        setOnListener();
        Handle();
        //这个方法需要在fragmentactivity中使用
//        FragmentManager fm=getSupportFragmentManager();
    }

    private void setOnListener() {
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        Toast.makeText(VPager.this,"1",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(VPager.this,"2",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(VPager.this,"3",Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void Handle() {
        FragmentManager fm=getSupportFragmentManager();
        MypageAdapter myadapter=new MypageAdapter(fm);
        vp.setAdapter(myadapter);

    }

    private void initView() {
        vp= (ViewPager) findViewById(R.id.vp);
        fs=new ArrayList<Fragment>();
        fs.add(new ForthFragment("1"));
        fs.add(new FifthFragment("2"));
        fs.add(new SixthFragment("3"));

    }
    class MypageAdapter extends FragmentPagerAdapter{

        public MypageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fs.get(position);
        }

        @Override
        public int getCount() {
            return fs.size();
        }
    }
}
