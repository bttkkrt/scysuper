package com.jshx.gridManager.entity;

public class BaseBean {
	private String id;
	private String code;//对应code值
	private String name;//名称
	private String count;//企业数
	private String wzCount;//无证照企业数
	private String checked;//0 否 1 是 判断是否已选中
	private String  mark;//0 未绑定 1 已绑定
	private String info;//绑定信息
	private String szc;//所在村
	private String gxfw;
	
	
	public String getWzCount() {
		return wzCount;
	}
	public void setWzCount(String wzCount) {
		this.wzCount = wzCount;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
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
