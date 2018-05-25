package com.ledt.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ledt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13126 on 2017/9/28.
 */

public class ExpandableListView1 extends Activity {
    ExpandableListView sp_date_list = null;     //列表
    MyExpandAdapter adapter = null;             //数据适配器

    List<String> group_head;                    //组列表项，每个组都有一个子List
    List<List<String>> child;                   //子列表项
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_manager);
        sp_date_list = (ExpandableListView) findViewById(R.id.expandableListView_plan_manager);
        //初始化组、子列表项
        group_head = new ArrayList<String>();
        child = new ArrayList<List<String>>();

        //设置适配器
        sp_date_list.setAdapter(new MyExpandAdapter(this,group_head,child)); //设置数据适配器
        sp_date_list.setCacheColorHint(0);              //拖动列表的时候不出现黑色背景

        addGroup("静夜思");
        addGroup("春晓");
        addChild(0,"床前明月光");
        addChild(0,"疑是地上霜");
        addChild(1,"春眠不觉晓");
        addChild(1,"处处闻啼鸟");
    }

    //添加组列表项
    public void addGroup(String group){
        group_head.add(group);
        child.add(new ArrayList<String>()); //child中添加新数组
    }

    //添加对应组的自列表项
    public void addChild(int position,String child){
        List<String> it = this.child.get(position);
        if(it != null){
            it.add(child);
        }else{
            it = new ArrayList<String>();
            it.add(child);
        }
    }

    //子列表项被选中的响应方法
    public void childSelect(int groupPosition,int childPosition){
        Toast.makeText(getBaseContext(), Integer.toString(groupPosition)+":" +
                Integer.toString(childPosition), Toast.LENGTH_LONG).show();
    }

}
