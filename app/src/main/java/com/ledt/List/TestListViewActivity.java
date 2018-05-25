package com.ledt.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.ledt.R;
import com.ledt.adapter.LvAdapter;

import java.util.ArrayList;
import java.util.List;

import library.ISlide;
import library.OnClickSlideItemListener;

/**
 * Created by lingyiyong on 2017/8/21.
 */

public class TestListViewActivity extends AppCompatActivity {
    //左滑删除不同list
    private ListView mListView;
    LvAdapter adapter;
    List<Integer> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }


        mListView = (ListView) findViewById(R.id.mListView);
        adapter = new LvAdapter(list);
        mListView.setAdapter(adapter);
        adapter.setupListView(mListView);

        adapter.setOnClickSlideItemListener(new OnClickSlideItemListener() {
            @Override
            public void onItemClick(ISlide iSlideView, View v, int position) {
                Toast.makeText(v.getContext(), "click item position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(ISlide iSlideView, View v, int position) {
                if (v.getId() == R.id.btn_del) {
                    iSlideView.close();
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
