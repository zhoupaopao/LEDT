package com.ledt.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.ledt.R;
import com.ledt.adapter.RcyAdapter;

import java.util.ArrayList;
import java.util.List;

import library.ISlide;
import library.OnClickSlideItemListener;

public class TestRcyActivity extends AppCompatActivity {
    //左滑删除不同list
    private RecyclerView mRecyclerView;
    RcyAdapter adapter;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycle_view);

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        adapter = new RcyAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.setupRecyclerView(mRecyclerView);

        adapter.setOnClickSlideItemListener(new OnClickSlideItemListener() {
            @Override
            public void onItemClick(ISlide iSlideView, View v, int position) {
                Toast.makeText(v.getContext(), "click item position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(ISlide iSlideView, View v, int position) {
                if (v.getId() == R.id.btn_del) {
                    //在这儿执行删除操作
                    iSlideView.close();
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}
