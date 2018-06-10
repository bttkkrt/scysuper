package com.jshx.gridManager.entity;

import java.util.List;

public class GridmanagerBean {
	private String id;
	private String name;
	private String szc;
	private List<GridmanagerBean> list ;
	private String yznum;
	private String wznum;
	private String gxfw;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GridmanagerBean> getList() {
		return list;
	}
	public void setList(List<GridmanagerBean> list) {
		this.list = list;
	}
	public String getYznum() {
		return yznum;
	}
	public void setYznum(String yznum) {
		this.yznum = yznum;
	}
	public String getWznum() {
		return wznum;
	}
	public void setWznum(String wznum) {
		this.wznum = wznum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSzc() {
		return szc;
	}
	public void setSzc(String szc) {
		this.szc = szc;
	}
	public String getGxfw() {
		return gxfw;
	}
	public void setGxfw(String gxfw) {
		this.gxfw = gxfw;
	}
}
