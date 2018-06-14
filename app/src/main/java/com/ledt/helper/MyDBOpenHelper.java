package com.ledt.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lenovo on 2018/6/12.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"my.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //AUTOINCREMENT主键
        db.execSQL("CREATE TABLE person(personid INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20))");
        Log.i("onCreate: ", "创建数据库 ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //软件版本号发生改变时调用
        //向已有的表中添加列
        Log.i("onUpgrade: ", "版本升级 ");
        switch (newVersion){
            case 2:
                //添加列
                db.execSQL("ALTER TABLE person ADD phonee VARCHAR(12) NULL");
                break;

        }

    }
}
