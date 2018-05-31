package com.ledt.Activity;

/**
 * Created by Lenovo on 2018/5/31.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.widget.FrameLayout;

import com.ledt.R;
import com.ledt.fragment.SixthFragment;
import com.ledt.fragment.TestFragment;

public class FragmentPageActivity extends FragmentActivity {
    private FrameLayout framelayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentpage);
        initView();
        initListener();
    }

    private void initView() {
        framelayout=findViewById(R.id.framelayout);
        //必需继承FragmentActivity,嵌套fragment只需要这行代码
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new SixthFragment("内嵌的fragment")).commitAllowingStateLoss();
    }

    private void initListener() {

    }
}
