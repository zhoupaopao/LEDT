package com.ledt.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ledt.R;
import com.ledt.tree.Node;
import com.ledt.tree.TreeListViewAdapter;



public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T>
{

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas,
			int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException
	{
		super(mTree, context, datas, defaultExpandLevel);
	}

//	@Override
//	public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
//		return null;
//	}

	@Override
	public View getConvertView(Node node , final int position, View convertView, ViewGroup parent)
	{
		
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.id_treenode_icon);
			viewHolder.icon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.i("expandOrCollapse", position+"");
				}
			});
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.id_treenode_label);
			convertView.setTag(viewHolder);

		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (node.getIcon() == -1)
		{
			viewHolder.icon.setVisibility(View.INVISIBLE);
			Log.i("cansee", "不可见");
		} else
		{
			Log.i("cansee", "可见");
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(node.getIcon());
		}
		viewHolder.label.setText(node.getName());
		
		return convertView;
	}

	private final class ViewHolder
	{
		ImageView icon;
		TextView label;
	}

}
