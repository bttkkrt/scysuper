package com.jshx.aqsczjzd.entity;

import java.text.DecimalFormat;

public class AqsczjzdBean {
	  private String data1;
	  private String data2;
	  private String data3;
	  private String data4;
	  private String data5;
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData4() {
		return data4;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public String getData5() {
		if(!data2.equals("0")){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format(Integer.parseInt(data4)*100/Integer.parseInt(data2));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	

}
