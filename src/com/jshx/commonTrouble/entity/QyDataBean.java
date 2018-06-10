package com.jshx.commonTrouble.entity;

import java.text.DecimalFormat;

public class QyDataBean {
	private String qymc;//企业名称
	private int cdcs;//出动次数
	private int ccyhs;//查出隐患数
	private int zdyhs;//重大隐患数
	private int wcyhs;//完成隐患数
	private int wczdyhs;//完成重大隐患数
	private String yhzgl;//隐患整改率
	private String zdyhzgl;//重大隐患整改率
	private String zgzj;//整改资金
	
	public String getZgzj() {
		return zgzj;
	}
	public void setZgzj(String zgzj) {
		this.zgzj = zgzj;
	}
	public int getCdcs() {
		return cdcs;
	}
	public void setCdcs(int cdcs) {
		this.cdcs = cdcs;
	}
	public int getCcyhs() {
		return ccyhs;
	}
	public void setCcyhs(int ccyhs) {
		this.ccyhs = ccyhs;
	}
	public int getZdyhs() {
		return zdyhs;
	}
	public void setZdyhs(int zdyhs) {
		this.zdyhs = zdyhs;
	}
	public int getWcyhs() {
		return wcyhs;
	}
	public void setWcyhs(int wcyhs) {
		this.wcyhs = wcyhs;
	}
	public int getWczdyhs() {
		return wczdyhs;
	}
	public void setWczdyhs(int wczdyhs) {
		this.wczdyhs = wczdyhs;
	}
	public String getYhzgl() {
		if((ccyhs)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(wcyhs)*100/(ccyhs));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setYhzgl(String yhzgl) {
		this.yhzgl = yhzgl;
	}
	public String getZdyhzgl() {
		if((zdyhs)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(wczdyhs)*100/(zdyhs));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZdyhzgl(String zdyhzgl) {
		this.zdyhzgl = zdyhzgl;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	
}
