package com.ledt.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ledt.R;
import com.ledt.tree.TreeListViewAdapter;
import com.zhy.tree.bean.Node;

import java.util.List;

/**
 * Created by Lenovo on 2018/5/29.
 */

public class NewSimpleTreeAdapter extends TreeListViewAdapter{
    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public NewSimpleTreeAdapter(ListView mTree, Context context, List datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(com.ledt.tree.Node node, final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.icon=convertView.findViewById(R.id.id_treenode_icon);
            viewHolder.label=convertView.findViewById(R.id.id_treenode_label);
            viewHolder.qty=convertView.findViewById(R.id.textView47);
            if(node.isExpand()){

            }
            if(node.isExpand()){
                //如果这个node是展开的
                viewHolder.icon.setImageResource(R.mipmap.tree_ex);
            }else{
                viewHolder.icon.setImageResource(R.mipmap.tree_ec);
            }
            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击加减号需要展开收缩下级列表
                    expandOrCollapse(position);
                }
            });
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
            if(node.isExpand()){
                viewHolder.icon.setImageResource(R.mipmap.tree_ex);
            }else{
                viewHolder.icon.setImageResource(R.mipmap.tree_ec);
            }
        }
//        if(node.isExpand()){
//            //展开
//
//        }
        if (node.getIcon() == -1)
        {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else
        {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        viewHolder.label.setText(node.getName());
        viewHolder.qty.setVisibility(View.GONE);
        return convertView;
    }
    private class ViewHolder{
        ImageView icon;
        TextView label;
        TextView qty;
    }
}
