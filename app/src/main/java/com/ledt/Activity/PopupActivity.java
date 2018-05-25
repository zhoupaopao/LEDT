package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.ledt.R;

/**
 * Created by 13126 on 2017/9/28.
 */

/**
 * 用于记录在页面上面的弹出框显示，例子为listview上显示弹出
 */
public class PopupActivity extends Activity {
    private ListView list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        list= (ListView) findViewById(R.id.listv);
        information();

    }

    private void information() {

    }
}
