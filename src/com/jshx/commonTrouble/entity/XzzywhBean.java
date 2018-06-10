package com.jshx.commonTrouble.entity;

import java.text.DecimalFormat;

public class XzzywhBean {
	private String szz;//所在镇
	private int qyTotal;//职业危害企业数
	private int zjwhs;//镇级危害数
	private int cjwhs;//村级危害数
	private String xcjcjl;//现场检查记录 A001
	private String zgzls;//责令限期整改指令书 A002
	private String qzcs;//强制措施决定书 A003
	private String fcyjs;//整改复查意见书 A004
	private int yhTotal;//检查发现隐患数
	private int zgyhs;//整改隐患数
	private int wzgyhs;//未整改隐患数
	private int zgl;//整改率
	
	public String getZgl() {
		if(yhTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)zgyhs*100/yhTotal);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	
	public String getSzz() {
		return szz;
	}
	public void setSzz(String szz) {
		this.szz = szz;
	}
	public int getZjwhs() {
		return zjwhs;
	}
	public void setZjwhs(int zjwhs) {
		this.zjwhs = zjwhs;
	}
	public int getCjwhs() {
		return cjwhs;
	}
	public void setCjwhs(int cjwhs) {
		this.cjwhs = cjwhs;
	}
	public String getXcjcjl() {
		return xcjcjl;
	}
	public void setXcjcjl(String xcjcjl) {
		this.xcjcjl = xcjcjl;
	}
	public String getZgzls() {
		return zgzls;
	}
	public void setZgzls(String zgzls) {
		this.zgzls = zgzls;
	}
	public String getFcyjs() {
		return fcyjs;
	}
	public void setFcyjs(String fcyjs) {
		this.fcyjs = fcyjs;
	}
	public String getQzcs() {
		return qzcs;
	}
	public void setQzcs(String qzcs) {
		this.qzcs = qzcs;
	}
	
	public int getYhTotal() {
		return yhTotal;
	}

	public void setYhTotal(int yhTotal) {
		this.yhTotal = yhTotal;
	}

	public int getZgyhs() {
		return zgyhs;
	}
	public void setZgyhs(int zgyhs) {
		this.zgyhs = zgyhs;
	}
	public int getWzgyhs() {
		return yhTotal-zgyhs;
	}
	public void setWzgyhs(int wzgyhs) {
		this.wzgyhs = wzgyhs;
	}
	
	public void setZgl(int zgl) {
		this.zgl = zgl;
	}

	public int getQyTotal() {
		return qyTotal;
	}

	public void setQyTotal(int qyTotal) {
		this.qyTotal = qyTotal;
	}
	
	
}