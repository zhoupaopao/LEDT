package com.ledt.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ledt.R;
import com.ledt.helper.MyDBOpenHelper;

/**
 * Created by Lenovo on 2018/6/11.
 */
//初次使用数据库

public class SqlActivity extends Activity implements View.OnClickListener{
    Button sql_start;
    Button sql_insert;
    Button sql_delete;
    Button sql_change;
    Button sql_search;
    MyDBOpenHelper myDBOpenHelper;
    SQLiteDatabase db;
    private int i = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        sql_start=findViewById(R.id.sql_start);
        sql_insert=findViewById(R.id.sql_insert);
        sql_delete=findViewById(R.id.sql_delete);
        sql_change=findViewById(R.id.sql_change);
        sql_search=findViewById(R.id.sql_search);
        sql_search.setOnClickListener(this);
        sql_start.setOnClickListener(this);
        sql_insert.setOnClickListener(this);
        sql_delete.setOnClickListener(this);
        sql_change.setOnClickListener(this);
        myDBOpenHelper=new MyDBOpenHelper(this,"my.db",null,1);


    }

    private void initData() {

    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        db=myDBOpenHelper.getWritableDatabase();
        switch (v.getId()){
            case R.id.sql_start:
                ContentValues contentValues=new ContentValues();
                contentValues.put("name","haha"+i);
                i++;

                break;
            case R.id.sql_insert:
                break;
            case R.id.sql_delete:
                break;
            case R.id.sql_change:
                break;
            case R.id.sql_search:
                break;
        }
    }
}
