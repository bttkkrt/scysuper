package com.jshx.yhb.entity;

import java.text.DecimalFormat;

public class TjYhBean {
	private int qynum;
	private int qyTotal;//符合条件企业总数
	private int sbqy;//上报企业
	private int wsbqy;//未上报企业
	private int yhTotal;//隐患总数
	private int zgwc;//整改完成数
	private int zgwwc;//整改未完成数
	private int zeroCount;//零隐患企业
	private int noZeroCount;//非零隐患企业
	private String zgl;//整改率
	private String dwdz;//所在镇
	private String szzid;//所在镇id
	private int sbcs;//上报次数
	public String getDwdz() {
		return dwdz;
	}
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	public String getZgl() {
		if(sbcs!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)zgwc*100/sbcs);
			return strFloat+"%";
		}else{
			return "-";
		}
	}
	public void setZgl(String zgl) {
		this.zgl = zgl;
	}
	public int getQyTotal() {
		return qyTotal;
	}
	public void setQyTotal(int qyTotal) {
		this.qyTotal = qyTotal;
	}
	public int getSbqy() {
		return sbqy;
	}
	public void setSbqy(int sbqy) {
		this.sbqy = sbqy;
	}
	public int getWsbqy() {
		return qyTotal-sbqy;
	}
	public void setWsbqy(int wsbqy) {
		this.wsbqy = wsbqy;
	}
	public int getYhTotal() {
		return yhTotal;
	}
	public void setYhTotal(int yhTotal) {
		this.yhTotal = yhTotal;
	}
	public int getZgwc() {
		return zgwc;
	}
	public void setZgwc(int zgwc) {
		this.zgwc = zgwc;
	}
	public int getZgwwc() {
		return zgwwc;
	}
	public void setZgwwc(int zgwwc) {
		this.zgwwc = zgwwc;
	}
	public int getZeroCount() {
		return sbqy-noZeroCount;
	}
	public void setZeroCount(int zeroCount) {
		this.zeroCount = zeroCount;
	}
	public int getNoZeroCount() {
		return noZeroCount;
	}
	public void setNoZeroCount(int noZeroCount) {
		this.noZeroCount = noZeroCount;
	}
	public String getSzzid() {
		return szzid;
	}
	public void setSzzid(String szzid) {
		this.szzid = szzid;
	}
	public int getQynum() {
		return qynum;
	}
	public void setQynum(int qynum) {
		this.qynum = qynum;
	}
	public int getSbcs() {
		return sbcs;
	}
	public void setSbcs(int sbcs) {
		this.sbcs = sbcs;
	}
	
	
}
