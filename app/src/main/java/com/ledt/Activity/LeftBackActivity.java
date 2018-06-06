package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.ledt.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Lenovo on 2018/6/5.
 */

public class LeftBackActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个只是对标题进行修改的
setContentView(R.layout.activity_left_back);
        ImmersionBar.with(this)
                .titleBar(R.id.toolbar)
                .navigationBarColor(R.color.colorPrimary)
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
