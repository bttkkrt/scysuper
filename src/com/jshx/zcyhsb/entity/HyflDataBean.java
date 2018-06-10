package com.jshx.zcyhsb.entity;

import java.text.DecimalFormat;

public class HyflDataBean {
	private int qyTotal;//企业总数
	private int sbqy;//上报企业
	private String fgl;//覆盖率
	private int jb01;//一般级别总数
	private int jb0101;//一般级别已整改总数
	private String zgl01;//整改率
	private int jb02;//一级隐患总数
	private int jb0202;//一级隐患已整改总数
	private String zgl02;//整改率
	private int jb03;//二级隐患总数
	private int jb0303;//二级隐患已整改总数
	private String zgl03;//整改率
	private int jb04;//三级隐患总数
	private int jb0404;//三级隐患已整改总数
	private String zgl04;//整改率
	private String hyfl;//行业分类
	private String zgzj;//整改资金
	
	private int yhtotal;//隐患总数
	
	private int yzgTotal;//已整改
	
	private  String totalZgl;//整改率
	
	
	public int getYhtotal() {
		return jb01+jb02+jb03+jb04;
	}
	public void setYhtotal(int yhtotal) {
		this.yhtotal = yhtotal;
	}
	public int getYzgTotal() {
		return jb0101+jb0202+jb0303+jb0404;
	}
	public void setYzgTotal(int yzgTotal) {
		this.yzgTotal = yzgTotal;
	}
	public String getTotalZgl() {
		if((jb01+jb02+jb03+jb04)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(jb0101+jb0202+jb0303+jb0404)*100/(jb01+jb02+jb03+jb04));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setTotalZgl(String totalZgl) {
		this.totalZgl = totalZgl;
	}
	public String getZgzj() {
		return zgzj;
	}
	public void setZgzj(String zgzj) {
		this.zgzj = zgzj;
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
	public String getFgl() {
		if(qyTotal!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)sbqy*100/qyTotal);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setFgl(String fgl) {
		this.fgl = fgl;
	}
	public int getJb01() {
		return jb01;
	}
	public void setJb01(int jb01) {
		this.jb01 = jb01;
	}
	public int getJb0101() {
		return jb0101;
	}
	public void setJb0101(int jb0101) {
		this.jb0101 = jb0101;
	}
	public String getZgl01() {
		if(jb01!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)jb0101*100/jb01);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZgl01(String zgl01) {
		this.zgl01 = zgl01;
	}
	public int getJb02() {
		return jb02;
	}
	public void setJb02(int jb02) {
		this.jb02 = jb02;
	}
	public int getJb0202() {
		return jb0202;
	}
	public void setJb0202(int jb0202) {
		this.jb0202 = jb0202;
	}
	public String getZgl02() {
		if(jb02!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)jb0202*100/jb02);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZgl02(String zgl02) {
		this.zgl02 = zgl02;
	}
	public int getJb03() {
		return jb03;
	}
	public void setJb03(int jb03) {
		this.jb03 = jb03;
	}
	public int getJb0303() {
		return jb0303;
	}
	public void setJb0303(int jb0303) {
		this.jb0303 = jb0303;
	}
	public String getZgl03() {
		if(jb03!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)jb0303*100/jb03);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZgl03(String zgl03) {
		this.zgl03 = zgl03;
	}
	public int getJb04() {
		return jb04;
	}
	public void setJb04(int jb04) {
		this.jb04 = jb04;
	}
	public int getJb0404() {
		return jb0404;
	}
	public void setJb0404(int jb0404) {
		this.jb0404 = jb0404;
	}
	public String getZgl04() {
		if(jb04!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)jb0404*100/jb04);
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setZgl04(String zgl04) {
		this.zgl04 = zgl04;
	}
	public String getHyfl() {
		return hyfl;
	}
	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	
}
