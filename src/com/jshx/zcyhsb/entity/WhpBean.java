package com.jshx.zcyhsb.entity;

import java.text.DecimalFormat;

public class WhpBean {
	private String dwdz;//所在镇
	private int qyTotal;//危险化学品企业总数
	private int sbqy;//上报企业数
	private int yhTotal;//隐患排查数
	private int zgwc;//隐患整改数
	private String zgl;//整改率
	private int hgscqyTotal;//化工生产企业数
	private int hgscsbqy;//化工生产上报企业数
	private int hgscyhTotal;//化工生产隐患排查数
	private int hgsczgwc;//化工生产隐患整改数
	private int hgsczgwwc;//化工生产未整改隐患数
	private String hgsczgl;//化工生产隐患整改率
	private int whpjyqyTotal;//危险化学品经营企业数
	private int whpjysbqy;//危险化学品经营上报企业数
	private int whpjyyhTotal;//危险化学品经营隐患排查数
	private int whpjyzgwc;//危险化学品经营隐患整改数
	private int whpjyzgwwc;//危险化学品经营未整改隐患数
	private String whpjyzgl;//危险化学品经营隐患整改率
	private int whpsyqyTotal;//危险化学品使用企业数
	private int whpsysbqy;//危险化学品使用上报企业数
	private int whpsyyhTotal;//危险化学品使用隐患排查数
	private int whpsyzgwc;//危险化学品使用隐患整改数
	private int whpsyzgwwc;//危险化学品使用未整改隐患数
	private String whpsyzgl;//危险化学品使用隐患整改率
	
	public String getZgl() {
		if(yhTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)zgwc*100/yhTotal);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	
	public String getHgsczgl() {
		if(hgscyhTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)hgsczgwc*100/hgscyhTotal);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	
	public String getWhpjyzgl() {
		if(whpjyyhTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)whpjyzgwc*100/whpjyyhTotal);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	
	public String getWhpsyzgl() {
		if(whpsyyhTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)whpsyzgwc*100/whpsyyhTotal);
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
	
	public void setZgl(String zgl) {
		this.zgl = zgl;
	}
	public int getHgscqyTotal() {
		return hgscqyTotal;
	}
	public void setHgscqyTotal(int hgscqyTotal) {
		this.hgscqyTotal = hgscqyTotal;
	}
	public int getHgscsbqy() {
		return hgscsbqy;
	}
	public void setHgscsbqy(int hgscsbqy) {
		this.hgscsbqy = hgscsbqy;
	}
	public int getHgscyhTotal() {
		return hgscyhTotal;
	}
	public void setHgscyhTotal(int hgscyhTotal) {
		this.hgscyhTotal = hgscyhTotal;
	}
	public int getHgsczgwc() {
		return hgsczgwc;
	}
	public void setHgsczgwc(int hgsczgwc) {
		this.hgsczgwc = hgsczgwc;
	}
	public int getHgsczgwwc() {
		return hgsczgwwc;
	}
	public void setHgsczgwwc(int hgsczgwwc) {
		this.hgsczgwwc = hgsczgwwc;
	}
	
	public void setHgsczgl(String hgsczgl) {
		this.hgsczgl = hgsczgl;
	}
	public int getWhpjyqyTotal() {
		return whpjyqyTotal;
	}
	public void setWhpjyqyTotal(int whpjyqyTotal) {
		this.whpjyqyTotal = whpjyqyTotal;
	}
	public int getWhpjysbqy() {
		return whpjysbqy;
	}
	public void setWhpjysbqy(int whpjysbqy) {
		this.whpjysbqy = whpjysbqy;
	}
	public int getWhpjyyhTotal() {
		return whpjyyhTotal;
	}
	public void setWhpjyyhTotal(int whpjyyhTotal) {
		this.whpjyyhTotal = whpjyyhTotal;
	}
	public int getWhpjyzgwc() {
		return whpjyzgwc;
	}
	public void setWhpjyzgwc(int whpjyzgwc) {
		this.whpjyzgwc = whpjyzgwc;
	}
	public int getWhpjyzgwwc() {
		return whpjyzgwwc;
	}
	public void setWhpjyzgwwc(int whpjyzgwwc) {
		this.whpjyzgwwc = whpjyzgwwc;
	}
	
	public void setWhpjyzgl(String whpjyzgl) {
		this.whpjyzgl = whpjyzgl;
	}
	public int getWhpsyqyTotal() {
		return whpsyqyTotal;
	}
	public void setWhpsyqyTotal(int whpsyqyTotal) {
		this.whpsyqyTotal = whpsyqyTotal;
	}
	public int getWhpsysbqy() {
		return whpsysbqy;
	}
	public void setWhpsysbqy(int whpsysbqy) {
		this.whpsysbqy = whpsysbqy;
	}
	public int getWhpsyyhTotal() {
		return whpsyyhTotal;
	}
	public void setWhpsyyhTotal(int whpsyyhTotal) {
		this.whpsyyhTotal = whpsyyhTotal;
	}
	public int getWhpsyzgwc() {
		return whpsyzgwc;
	}
	public void setWhpsyzgwc(int whpsyzgwc) {
		this.whpsyzgwc = whpsyzgwc;
	}
	public int getWhpsyzgwwc() {
		return whpsyzgwwc;
	}
	public void setWhpsyzgwwc(int whpsyzgwwc) {
		this.whpsyzgwwc = whpsyzgwwc;
	}
	
	public void setWhpsyzgl(String whpsyzgl) {
		this.whpsyzgl = whpsyzgl;
	}
	
	
	
	
	
	
}
