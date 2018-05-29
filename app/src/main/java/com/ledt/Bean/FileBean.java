package com.ledt.Bean;


import com.ledt.tree.TreeNodeId;
import com.ledt.tree.TreeNodeLabel;
import com.ledt.tree.TreeNodePid;
import com.ledt.tree.TreeNodeUseid;

public class FileBean
{
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int parentId;
	@TreeNodeLabel
	private String name;
	@TreeNodeUseid
	private String status;
	private long length;
	private String desc;
//这边其实可以加个是否展开
	public FileBean(int _id, int parentId, String name,String status)
	{
		super();
		this._id = _id;
		this.parentId = parentId;
		this.name = name;
		this.status=status;
	}

}
