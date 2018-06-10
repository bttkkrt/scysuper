package com.jshx.majorTrouble.entity;

import java.text.DecimalFormat;

public class QyData {
	private String qymc;//企业名称
	private int yhs;//隐患数
	private int zgs;//整改数
	private String zgl;//整改率
	private String zgzj;//整改资金
	
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
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
}
