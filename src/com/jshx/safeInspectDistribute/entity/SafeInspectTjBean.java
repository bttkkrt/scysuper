package com.jshx.safeInspectDistribute.entity;

import java.text.DecimalFormat;

public class SafeInspectTjBean {
	private String title;//
	private String rootid;//
	private String total;//
	private String donenum;//
	private String notnum;//
	private String rightnum;//
	private String wrongnum;//
	private String goodrate;//
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRootid() {
		return rootid;
	}
	public void setRootid(String rootid) {
		this.rootid = rootid;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDonenum() {
		return donenum;
	}
	public void setDonenum(String donenum) {
		this.donenum = donenum;
	}
	public String getNotnum() {
		return notnum;
	}
	public void setNotnum(String notnum) {
		this.notnum = notnum;
	}
	public String getRightnum() {
		return rightnum;
	}
	public void setRightnum(String rightnum) {
		this.rightnum = rightnum;
	}
	public String getWrongnum() {
		return wrongnum;
	}
	public void setWrongnum(String wrongnum) {
		this.wrongnum = wrongnum;
	}
	public String getGoodrate() {
		return this.formatRate(goodrate);
	}
	public void setGoodrate(String goodrate) {
		this.goodrate = goodrate;
	}
	private String formatRate(String goodrate) {
		int index = goodrate.indexOf(".");
		String b = goodrate.substring(index+1, goodrate.length());
		if(-1 != index && b.length()>2){
			DecimalFormat df = new DecimalFormat("#0.0000"); 
			Double temp = Double.parseDouble(goodrate);
			String a = df.format(temp);
			temp = Double.parseDouble(a);
			temp = temp*100;
			goodrate = temp.toString() + "%";
		}else{
			Double temp = Double.parseDouble(goodrate);
			temp = temp*100;
			index = temp.toString().indexOf(".");

			goodrate = temp.toString().substring(0, index) + "%";
		}
		return goodrate;
	}
	
}
