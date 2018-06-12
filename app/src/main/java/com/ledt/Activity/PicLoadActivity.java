package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.ledt.R;
import com.ledt.adapter.PicLoadAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by Lenovo on 2018/6/8.
 */

public class PicLoadActivity extends Activity {
    ListView listView;
    private ArrayList<String> arrayList=new ArrayList<>();
    PicLoadAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_load);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        listView=findViewById(R.id.piclist);
        adapter=new PicLoadAdapter(this,arrayList);
        listView.setAdapter(adapter);
        readmsg();
    }

    private void initData() {

    }
    private void readmsg() {
//        String mmm=readFileData("message1.text");
        String path="/sdcard/YWMessage/message.text";
        String res= loadFromSDFile(path);
        Log.i("readmsg: ", res);
        String[] meslist=res.split(";");
        arrayList.clear();
        for(int i=0;i<meslist.length-1;i++){
            String[]mds=meslist[i].split(",");

            arrayList.add(mds[2]);
            Log.i("readmsg: ", meslist[i]);
        }
        adapter.addList(arrayList);
        adapter.notifyDataSetChanged();
    }
    private String loadFromSDFile(String path) {
        String result="";

        try {
            File file=new File(path);
            int length= (int) file.length();
            byte[]buff=new byte[length];
            FileInputStream fileInputStream=new FileInputStream(file);
            fileInputStream.read(buff);
            fileInputStream.close();
            result=new String(buff,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(PicLoadActivity.this,"找不到指定文件",Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    private void initListener() {

    }
}
