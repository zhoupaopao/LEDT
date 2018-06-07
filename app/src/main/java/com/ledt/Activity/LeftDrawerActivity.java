package com.ledt.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.ledt.R;
/**
 * Created by Lenovo on 2018/6/7.
 */

public class LeftDrawerActivity extends Activity {
//    @BindView(R.id.drawer)
    DrawerLayout drawer;
    Button show_left;
    Toolbar toolbar;
    ImageView xuanzhuan;
    protected ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_drawer);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        initView();
    }

    private void initView() {
        drawer=findViewById(R.id.drawer);
        show_left=findViewById(R.id.showleft);
        xuanzhuan=findViewById(R.id.xuanzhuan);
        xuanzhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotateAnimation  = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setDuration(50);
                rotateAnimation.setRepeatCount(0);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                xuanzhuan.startAnimation(rotateAnimation);
            }
        });

        toolbar=findViewById(R.id.toolbar);
        show_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        //给Toolbar设置左上角的图标和监听事件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationIcon(R.drawable.ab_edit);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //如果抽屉关闭就打开，如果抽屉打开就关闭
                    if(drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.closeDrawer(Gravity.LEFT);
                    }else {//如果已经是关闭状态
                        drawer.openDrawer(Gravity.LEFT);
                    }
                }
            });
        }else{
            Log.i("initView: ", "initView: ");
        }

    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar.titleBar(R.id.toolbar).init();
    }

}
