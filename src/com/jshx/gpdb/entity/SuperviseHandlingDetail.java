package com.jshx.gpdb.entity;

public class SuperviseHandlingDetail {
	private String id;//编号
	private String szzname;//	所在镇 szzname
	private String qyid;//企业id
	private String qymc;//	企业名称 qymc
	private String yhmc;//	隐患名称 yhmc
	private String gpsj;//	挂牌时间 gpsj
	private String yhlb;//	隐患类别 yhlb
	private String yhs;//	隐患数 yhs
	private String zrr;//	责任人 zrr
	private String zgzj;	//	整改资金（万元） zgzj
	private String zgwcsj;	//	整改完成时间 zgwcsj
	private String yssj;	//	验收时间 yssj
	private String yhzgs;	//	隐患整改数 yhzgs
	private String state;//	状态 state
	private String remark;	//	备注 remark	
	//整改前图片
	private String zgqtp;
	//检查文书
	private String jcws;
	//复查文书
	private String fcws;
	//整改方案
	private String zgfa;
	//监测措施
	private String jkcs;
	//整改后图片
	private String zghtp;
	
	public void setSzzname(String szzname) {
		this.szzname = szzname;
	}
	public String getSzzname() {
		return szzname;
	}
	public void setQymc(String szzname) {
		this.qymc = szzname;
	}
	public String getQymc() {
		return qymc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	public String getYhmc() {
		return yhmc;
	}
	public void setGpsj(String gpsj) {
		this.gpsj = gpsj;
	}
	public String getGpsj() {
		return gpsj;
	}
	public void setYhlb(String yhlb) {
		this.yhlb = yhlb;
	}
	public String getYhlb() {
		return yhlb;
	}
	public void setYhs(String yhs) {
		this.yhs = yhs;
	}
	public String getYhs() {
		return yhs;
	}
	public void setZrr(String zrr) {
		this.zrr = zrr;
	}
	public String getZrr() {
		return zrr;
	}
	public void setZgzj(String zgzj) {
		this.zgzj = zgzj;
	}
	public String getZgzj() {
		return zgzj;
	}
	public void setZgwcsj(String zgwcsj) {
		this.zgwcsj = zgwcsj;
	}
	public String getZgwcsj() {
		return zgwcsj;
	}
	public void setYssj(String zgwcsj) {
		this.yssj = zgwcsj;
	}
	public String getYssj() {
		return yssj;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	
	public void setYhzgs(String yhzgs) {
		this.yhzgs = yhzgs;
	}
	public String getYhzgs() {
		return yhzgs;
	}
	public void setZgqtp(String zgqtp) {
		this.zgqtp = zgqtp;
	}
	public String getZgqtp() {
		return zgqtp;
	}
	public void setJcws(String jcws) {
		this.jcws = jcws;
	}
	public String getJcws() {
		return jcws;
	}
	public void setFcws(String fcws) {
		this.fcws = fcws;
	}
	public String getFcws() {
		return fcws;
	}
	public void setZgfa(String zgfa) {
		this.zgfa = zgfa;
	}
	public String getZgfa() {
		return zgfa;
	}
	public void setJkcs(String jkcs) {
		this.jkcs = jkcs;
	}
	public String getJkcs() {
		return jkcs;
	}
	public void setZghtp(String zghtp) {
		this.zghtp = zghtp;
	}
	public String getZghtp() {
		return zghtp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQyid() {
		return qyid;
	}
	public void setQyid(String qyid) {
		this.qyid = qyid;
	}
	
}