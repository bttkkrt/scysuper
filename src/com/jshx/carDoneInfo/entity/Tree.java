package com.jshx.carDoneInfo.entity;

import java.util.List;

public class Tree {
	
	//	节点id
	private String id;
	
	//	节点名称
	private String text;
	
	//	节点状态(open 打开 closed 关闭)
	private String state;
	
	//	是否选中节点(ture 选中 false没选中)
	private boolean checked;
	
	//	节点其他属性
	private String attributes;
	
	//	子节点
	private List<Tree> children;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

}
