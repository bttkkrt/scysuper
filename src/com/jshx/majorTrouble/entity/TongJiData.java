package com.jshx.majorTrouble.entity;

import java.text.DecimalFormat;

public class TongJiData {
	private String szz;//乡镇
	private String qyTotal;//企业总数
	private String jcqys;//检查企业数
	private String qylx;//企业类型
	private int yhs;//隐患数
	private int zgs;//整改数
	private String zgl;//整改率
	private String zgzj;//整改资金
	public String getSzz() {
		return szz;
	}
	public void setSzz(String szz) {
		this.szz = szz;
	}
	public String getQylx() {
		return qylx;
	}
	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	
	public String getZgzj() {
		return zgzj;
	}
	public void setZgzj(String zgzj) {
		this.zgzj = zgzj;
	}
	public int getYhs() {
		return yhs;
	}
	public void setYhs(int yhs) {
		this.yhs = yhs;
	}
	public int getZgs() {
		return zgs;
	}
	public void setZgs(int zgs) {
		this.zgs = zgs;
	}
	public String getZgl() {
		if((yhs)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(zgs)*100/(yhs));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZgl(String zgl) {
		this.zgl = zgl;
	}
	public String getQyTotal() {
		return qyTotal;
	}
	public void setQyTotal(String qyTotal) {
		this.qyTotal = qyTotal;
	}
	public String getJcqys() {
		return jcqys;
	}
	public void setJcqys(String jcqys) {
		this.jcqys = jcqys;
	}
	
}
