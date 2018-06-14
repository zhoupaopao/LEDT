package com.ledt.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    Button open_sw;
    Button sql_update;


    MyDBOpenHelper myDBOpenHelper;
    SQLiteDatabase db;
    private StringBuilder sb;
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
        open_sw=findViewById(R.id.open_sw);
        sql_update=findViewById(R.id.sql_update);
        sql_search.setOnClickListener(this);
        sql_start.setOnClickListener(this);
        sql_insert.setOnClickListener(this);
        sql_delete.setOnClickListener(this);
        sql_change.setOnClickListener(this);
        open_sw.setOnClickListener(this);
        sql_update.setOnClickListener(this);
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

                break;
            case R.id.sql_insert:
                ContentValues contentValues=new ContentValues();
                contentValues.put("name",i);
                i++;
                //参数依次是：表名，强行插入null值得数据列的列名，一行记录的数据
                db.insert("person", null, contentValues);
                Toast.makeText(this, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sql_delete:
                //参数依次是表名，以及where条件与约束
                //用处是删除personid=3的那条数据
                db.delete("person","personid=?",new String[]{"3"});
                break;
            case R.id.sql_change:
                ContentValues contentValues1=new ContentValues();
                contentValues1.put("name","yayay");
                db.update("person",contentValues1,"name=?",new String[]{"haha1"});
                break;
            case R.id.sql_search:
                sb=new StringBuilder();
                //参数依次是:表名，列名，where约束条件，where中占位符提供具体的值，指定group by的列，进一步约束
                //指定查询结果的排序方式
                Cursor cursor=db.query("person",null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    do {
                        int pid=cursor.getInt(cursor.getColumnIndex("personid"));
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        sb.append("id:"+pid+":"+name+"\n");
                    }while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.sql_update:
                myDBOpenHelper.onUpgrade(db,1,2);
                break;
            case R.id.open_sw:
                //开启事物
                //列子，用户1给用户2转10块钱
                db.beginTransaction();
                try{
                    db.execSQL("update person set name=name-1 where personid=13");
                    db.execSQL("update person set name=name+1 where personid=12");
                    db.setTransactionSuccessful();//设置事物标志为true，表示提交事物
                    Log.i("onClick: ", "setTransactionSuccessful: ");
                }finally {
                    db.endTransaction();
                    Log.i("onClick: ", "endTransaction: ");
                }
                break;

        }
    }
}
