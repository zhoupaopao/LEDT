package com.ledt.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ledt.R;

/**
 * Created by Lenovo on 2017/11/15.
 */

public class ListActivity4 extends Activity {
    //用来属性如何给listview加左化删除的
    private Button btnTestInListView, btnTestInRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list4);
        btnTestInListView = (Button) findViewById(R.id.btnTestInListView);
        btnTestInRecycleView = (Button) findViewById(R.id.btnTestInRecycleView);

        btnTestInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity4.this, TestListViewActivity.class));
            }
        });

        btnTestInRecycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity4.this, TestRcyActivity.class));
            }
        });
    }
}
