package com.jshx.aqscfk.entity;

import java.text.DecimalFormat;



public class Aqscxzcfglb
{
	private String data1;
	private String data2;
	private String data3;
	private String data4;
	private String data5;
	private String data6;
	private String data7;
	private String data8;
	private String data9;
	private String data10;
	private String data11;
	private String data12;
	private String data13;
	private String data14;
	private String data15;
	private String data16;
	private String data17;
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
		return data5;
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	public String getData6() {
		return data6;
	}
	public void setData6(String data6) {
		this.data6 = data6;
	}
	public String getData7() {
		return data7;
	}
	public void setData7(String data7) {
		this.data7 = data7;
	}
	public String getData8() {
		return data8;
	}
	public void setData8(String data8) {
		this.data8 = data8;
	}
	public String getData9() {
		return data9;
	}
	public void setData9(String data9) {
		this.data9 = data9;
	}
	public String getData10() {
		return data10;
	}
	public void setData10(String data10) {
		this.data10 = data10;
	}
	public String getData11() {
		return data11;
	}
	public void setData11(String data11) {
		this.data11 = data11;
	}
	public String getData12() {
		return data12;
	}
	public void setData12(String data12) {
		this.data12 = data12;
	}
	public String getData13() {
		return data13;
	}
	public void setData13(String data13) {
		this.data13 = data13;
	}
	public String getData14() {
		return data14;
	}
	public void setData14(String data14) {
		this.data14 = data14;
	}
	public String getData15() {
		if(data9 != null && !"".equals(data9) && Double.valueOf(data9) >= 0.00000000000000001 && data12 != null && !"".equals(data12)){
			DecimalFormat myFormat = new DecimalFormat( "0.00 "); 
			String strFloat = myFormat.format(Double.valueOf(data12)*100/Double.valueOf(data9));
			return strFloat;
		}else{
			return "0.00";
		}
	}
	public void setData15(String data15) {
		this.data15 = data15;
	}
	public String getData16() {
		if(data10 != null && !"".equals(data10) && Double.valueOf(data10) >= 0.00000000000000001  && data13 != null && !"".equals(data13)){
			DecimalFormat myFormat = new DecimalFormat( "0.00 "); 
			String strFloat = myFormat.format(Double.valueOf(data13)*100/Double.valueOf(data10));
			return strFloat;
		}else{
			return "0.00";
		}
	}
	public void setData16(String data16) {
		this.data16 = data16;
	}
	public String getData17() {
		if(data11 != null && !"".equals(data11) && Double.valueOf(data11) >= 0.00000000000000001  && data14 != null && !"".equals(data14)){
			DecimalFormat myFormat = new DecimalFormat( "0.00 "); 
			String strFloat = myFormat.format(Double.valueOf(data14)*100/Double.valueOf(data11));
			return strFloat;
		}else{
			return "0.00";
		}
	}
	public void setData17(String data17) {
		this.data17 = data17;
	}
	
}
