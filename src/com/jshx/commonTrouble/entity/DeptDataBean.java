package com.jshx.commonTrouble.entity;

import java.text.DecimalFormat;

public class DeptDataBean {
	private String szz;//所在镇
	private String qylx;//企业类型
	private int qyTotal;//企业总人数
	private int cdcs;//出动次数
	private int jcqys;//检查企业数
	private int ccyhs;//查出隐患数
	private int zdyhs;//重大隐患数
	private int wcyhs;//完成隐患数
	private int wczdyhs;//完成重大隐患数
	private String yhzgl;//隐患整改率
	private String zdyhzgl;//重大隐患整改率
	private String zgzj;//整改资金
	private String xcjcjl;//现场检查记录 A001
	private String zgzls;//责令限期整改指令书 A002
	private String qzcs;//强制措施决定书 A003
	private String fcyjs;//整改复查意见书 A004
	
	public String getZgzj() {
		return zgzj;
	}
	public void setZgzj(String zgzj) {
		this.zgzj = zgzj;
	}
	public String getSzz() {
		if(szz!=null){
			return szz.replace("抚顺市", "");
		}
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
	public int getCdcs() {
		return cdcs;
	}
	public void setCdcs(int cdcs) {
		this.cdcs = cdcs;
	}
	public int getJcqys() {
		return jcqys;
	}
	public void setJcqys(int jcqys) {
		this.jcqys = jcqys;
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
	public int getQyTotal() {
		return qyTotal;
	}
	public void setQyTotal(int qyTotal) {
		this.qyTotal = qyTotal;
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
	public String getQzcs() {
		return qzcs;
	}
	public void setQzcs(String qzcs) {
		this.qzcs = qzcs;
	}
	public String getFcyjs() {
		return fcyjs;
	}
	public void setFcyjs(String fcyjs) {
		this.fcyjs = fcyjs;
	}
	
}
