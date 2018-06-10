package com.jshx.zcyhsb.entity;

import java.text.DecimalFormat;

public class BzhBean {
	private String dwdz;//所在镇
	private int qyTotal;//安全生产标准化达标企业总数
	private int sbqy;//上报企业
	private int wsbqy;//未上报企业
	private int yhTotal;//隐患总数
	private int zgwc;//整改隐患数
	private int zgwwc;//未整改隐患数
	private String zgl;//整改率
	
	public String getZgl() {
		if(yhTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)zgwc*100/yhTotal);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	
	public String getDwdz() {
		return dwdz;
	}
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
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

	
	public void setZgl(String zgl) {
		this.zgl = zgl;
	}
	
	
}