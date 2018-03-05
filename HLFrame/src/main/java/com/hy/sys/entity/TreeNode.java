package com.hy.sys.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class TreeNode implements Serializable {
	private String id;
	private String text;
	private String iconCls;
	private String state = "open";// open
	private List<TreeNode> children;

	public TreeNode() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}
