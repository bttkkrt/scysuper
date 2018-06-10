package com.jshx.zcyhsb.entity;

import java.text.DecimalFormat;

public class TypeBean {
	private String jclb;//检查类别
	private String jclbName;//检查类别名称
	private int yhlbTotal;//隐患类别总数量
	private int lbCount;//隐患总数量
	private int wzgCount;//未整改数量
	private int yzgCount;//已整改数量
	private String zgl;//整改率
	
	private String zsyyh;//占所有隐患比率
	
	private String yhzl;//隐患中类
	
	public String getYhzl() {
		return yhzl;
	}
	public void setYhzl(String yhzl) {
		this.yhzl = yhzl;
	}
	
	public String getZgl() {
		if((wzgCount+yzgCount)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)yzgCount*100/(wzgCount+yzgCount));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZgl(String zgl) {
		this.zgl = zgl;
	}
	public String getJclb() {
		return jclb;
	}
	public void setJclb(String jclb) {
		this.jclb = jclb;
	}
	
	public String getJclbName() {
		if(jclb!=null){
			if("0".equals(jclb)){
				jclbName="企业自查";
			}else if("1".equals(jclb)){
				jclbName="专家检查";
			}else if("2".equals(jclb)){
				jclbName="企业互评互查";
			}
		}
		return jclbName;
	}
	public void setJclbName(String jclbName) {
		this.jclbName = jclbName;
	}
	public int getLbCount() {
		return wzgCount+yzgCount;
	}
	public void setLbCount(int lbCount) {
		this.lbCount = lbCount;
	}
	public int getWzgCount() {
		return wzgCount;
	}
	public void setWzgCount(int wzgCount) {
		this.wzgCount = wzgCount;
	}
	public int getYzgCount() {
		return yzgCount;
	}
	public void setYzgCount(int yzgCount) {
		this.yzgCount = yzgCount;
	}
	public int getYhlbTotal() {
		return yhlbTotal;
	}
	public void setYhlbTotal(int yhlbTotal) {
		this.yhlbTotal = yhlbTotal;
	}
	
	
}
