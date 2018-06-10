package com.jshx.company.entity;

import java.util.Date;

import com.jshx.core.base.entity.BaseModel;

public class XfjCompany extends BaseModel
{
	/**
	 * 注册用户名
	 */
	private String loginname;

	/**
	 * 注册密码
	 */
	private String loginword;
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;


	/**
	 * 单位编码
	 */
	private String gszch;
	
	
	/**
	 * 组织机构代码
	 */
	private String zzjgdm;
	
	/**
	 * 单位名称
	 */
	private String companyname;
	
	/**
	 * 单位地址
	 */
	private String dwdz;
	/**
	 * 镇，开发区等
	 */
	private String dwdz1;
	
	/**
	 * 村
	 */
	private String dwdz2;
	
	/**
	 * 火灾危险性
	 */
	private String hzwxx;
	
	/**
	 * 建筑管理单位
	 */
	private String jzgldw;
	
	/**
	 * 邮政编码
	 */
	private String yzbm;
	
	/**
	 * 企业邮箱
	 */
	private String qyyx;
	
	/**
	 * 固定电话
	 */
	private String gddh;
	
	/**
	 * 传       真
	 */
	private String cz;
	
	/**
	 * 经济所有制
	 */
	private String jjsyz;
	
	/**
	 * 企业成立时间
	 */
	private Date qyclsj;
	
	/**
	 * 单位类型
	 */
	private String dwlx;
	
	/**
	 * 上级单位
	 */
	private String sjdw;
	
	/**
	 * 法定代表人
	 */
	private String fddbr;
	
	/**
	 * 法定代表人联系方式
	 */
	private String fddbrlxhm;
	
	/**
	 * 消防安全管理人
	 */
	private String xfaqglr;
	
	/**
	 * 消防安全管理人电话
	 */
	private String xfaqglrlxhm;
	
	/**
	 * 消防安全责任人
	 */
	private String xfaqzrr;
	
	/**
	 * 消防安全责任人联系方式
	 */
	private String xfaqzrrlxhm;
	
	/**
	 * 消防管辖
	 */
	private String xfgx;
	
	/**
	 * 分管级别
	 */
	private String fgjb;

	/**
	 * 单位其他情况
	 */
	private String dwqtqk;
	
	/**
	 * 单位属性主类
	 */
	private String dwsxzl;
	
	/**
	 * 单位属性子类
	 */
	private String dwsxzil;
	
	/**
	 * 固定资产
	 */
	private String gdzc;
	
	/**
	 * 职工人数
	 */
	private Long cyry;
	
	/**
	 * 占地面积（m2）
	 */
	private Long zdmj;

	/**
	 * 建筑面积（m2）
	 */
	private Long jzmj;
	
	/**
	 * 地理情况
	 */
	private String dlqk;
	
	/**
	 * 自动消防设施
	 */
	private String zdxfss;

	private String longitude;
	
	private String latitude;
	
	/**
	 * 备注
	 */
	private String remark;
	
	private Date zddwrdsj;
	
	/**
	 * 燃气类型
	 */
	private String rqlx;
	
	/**
	 * 义务消防员
	 */
	private String ywxfy;
	
	/**
	 * 安全出口数
	 */
	private String aqcks;
	
	/**
	 * 疏散楼梯数
	 */
	private String sslts;

	/**
	 * 消防车道数
	 */
	private String xfcds;
	
	/**
	 * 消防电梯数
	 */
	private String xfdts;
	
	/**
	 * 消防车道位置
	 */
	private String xfcdwz;
	
	/**
	 * 避难层数
	 */
	private String bncs;
	
	/**
	 * 避难层总面积
	 */
	private String bnczmj;
	
	/**
	 * 避难层位置
	 */
	private String bncwz;
	
	/**
	 * 毗邻单位东
	 */
	private String pldwd;
	
	/**
	 * 毗邻单位南
	 */
	private String pldwn;
	
	/**
	 * 毗邻单位西
	 */
	private String pldwx;
	
	/**
	 * 毗邻单位北
	 */
	private String pldwb;
	
	/**
	 * 外围消防设施及位置
	 */
	private String wwxfcs;
	
	/**
	 * 消防自救措施
	 */
	private String xfzjcs;

	 /**
     * 基本信息是否审核通过  0:待审核  1:通过 2:未通过
     * @return
     */
	private int basePass=0;
	/**
	 * 基本信息的未通过备注
	 */
	private String baseRemark;
	
	private String companyId;
	
	
	public String getBaseRemark() {
		return baseRemark;
	}

	public void setBaseRemark(String baseRemark) {
		this.baseRemark = baseRemark;
	}

	public int getBasePass() {
		return basePass;
	}

	public void setBasePass(int basePass) {
		this.basePass = basePass;
	}

	public String getDeptId()
	{
		return deptId;
	}
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	public String getDwdz1() {
		return dwdz1;
	}

	public void setDwdz1(String dwdz1) {
		this.dwdz1 = dwdz1;
	}
	public String getDwdz2() {
		return dwdz2;
	}

	public void setDwdz2(String dwdz2) {
		this.dwdz2 = dwdz2;
	}

	public Integer getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	public String getQyyx()
	{
		return this.qyyx;
	}

	public void setQyyx(String qyyx)
	{
		this.qyyx = qyyx;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLoginword() {
		return loginword;
	}

	public void setLoginword(String loginword) {
		this.loginword = loginword;
	}
	public String getGszch() {
		return gszch;
	}

	public void setGszch(String gszch) {
		this.gszch = gszch;
	}
	public String getZzjgdm() {
		return zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	public String getHzwxx() {
		return hzwxx;
	}

	public void setHzwxx(String hzwxx) {
		this.hzwxx = hzwxx;
	}
	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getGddh() {
		return gddh;
	}

	public void setGddh(String gddh) {
		this.gddh = gddh;
	}
	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}
	public String getJjsyz() {
		return jjsyz;
	}

	public void setJjsyz(String jjsyz) {
		this.jjsyz = jjsyz;
	}
	public Date getQyclsj() {
		return qyclsj;
	}

	public void setQyclsj(Date qyclsj) {
		this.qyclsj = qyclsj;
	}
	public String getDwlx() {
		return dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}
	public String getSjdw() {
		return sjdw;
	}

	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	public String getFddbr() {
		return fddbr;
	}

	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getFddbrlxhm() {
		return fddbrlxhm;
	}

	public void setFddbrlxhm(String fddbrlxhm) {
		this.fddbrlxhm = fddbrlxhm;
	}
	public String getXfaqglr() {
		return xfaqglr;
	}

	public void setXfaqglr(String xfaqglr) {
		this.xfaqglr = xfaqglr;
	}
	public String getXfaqglrlxhm() {
		return xfaqglrlxhm;
	}

	public void setXfaqglrlxhm(String xfaqglrlxhm) {
		this.xfaqglrlxhm = xfaqglrlxhm;
	}
	public String getXfaqzrr() {
		return xfaqzrr;
	}

	public void setXfaqzrr(String xfaqzrr) {
		this.xfaqzrr = xfaqzrr;
	}
	public String getXfaqzrrlxhm() {
		return xfaqzrrlxhm;
	}

	public void setXfaqzrrlxhm(String xfaqzrrlxhm) {
		this.xfaqzrrlxhm = xfaqzrrlxhm;
	}
	public String getFgjb() {
		return fgjb;
	}

	public void setFgjb(String fgjb) {
		this.fgjb = fgjb;
	}
	public String getDwqtqk() {
		return dwqtqk;
	}

	public void setDwqtqk(String dwqtqk) {
		this.dwqtqk = dwqtqk;
	}
	public String getDwsxzl() {
		return dwsxzl;
	}

	public void setDwsxzl(String dwsxzl) {
		this.dwsxzl = dwsxzl;
	}
	public String getDwsxzil() {
		return dwsxzil;
	}

	public void setDwsxzil(String dwsxzil) {
		this.dwsxzil = dwsxzil;
	}
	public String getGdzc() {
		return gdzc;
	}

	public void setGdzc(String gdzc) {
		this.gdzc = gdzc;
	}
	public Long getCyry() {
		return cyry;
	}

	public void setCyry(Long cyry) {
		this.cyry = cyry;
	}
	public Long getZdmj() {
		return zdmj;
	}

	public void setZdmj(Long zdmj) {
		this.zdmj = zdmj;
	}
	public Long getJzmj() {
		return jzmj;
	}

	public void setJzmj(Long jzmj) {
		this.jzmj = jzmj;
	}
	public String getDlqk() {
		return dlqk;
	}

	public void setDlqk(String dlqk) {
		this.dlqk = dlqk;
	}
	public String getZdxfss() {
		return zdxfss;
	}

	public void setZdxfss(String zdxfss) {
		this.zdxfss = zdxfss;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRqlx() {
		return rqlx;
	}

	public void setRqlx(String rqlx) {
		this.rqlx = rqlx;
	}
	public String getYwxfy() {
		return ywxfy;
	}

	public void setYwxfy(String ywxfy) {
		this.ywxfy = ywxfy;
	}
	public String getAqcks() {
		return aqcks;
	}

	public void setAqcks(String aqcks) {
		this.aqcks = aqcks;
	}
	public String getSslts() {
		return sslts;
	}

	public void setSslts(String sslts) {
		this.sslts = sslts;
	}
	public String getXfcds() {
		return xfcds;
	}

	public void setXfcds(String xfcds) {
		this.xfcds = xfcds;
	}
	public String getXfdts() {
		return xfdts;
	}

	public void setXfdts(String xfdts) {
		this.xfdts = xfdts;
	}
	public String getXfcdwz() {
		return xfcdwz;
	}

	public void setXfcdwz(String xfcdwz) {
		this.xfcdwz = xfcdwz;
	}
	public String getBncs() {
		return bncs;
	}

	public void setBncs(String bncs) {
		this.bncs = bncs;
	}
	public String getBnczmj() {
		return bnczmj;
	}

	public void setBnczmj(String bnczmj) {
		this.bnczmj = bnczmj;
	}
	public String getBncwz() {
		return bncwz;
	}

	public void setBncwz(String bncwz) {
		this.bncwz = bncwz;
	}
	public String getPldwd() {
		return pldwd;
	}

	public void setPldwd(String pldwd) {
		this.pldwd = pldwd;
	}
	public String getPldwn() {
		return pldwn;
	}

	public void setPldwn(String pldwn) {
		this.pldwn = pldwn;
	}
	public String getPldwx() {
		return pldwx;
	}

	public void setPldwx(String pldwx) {
		this.pldwx = pldwx;
	}
	public String getPldwb() {
		return pldwb;
	}

	public void setPldwb(String pldwb) {
		this.pldwb = pldwb;
	}
	public String getWwxfcs() {
		return wwxfcs;
	}

	public void setWwxfcs(String wwxfcs) {
		this.wwxfcs = wwxfcs;
	}
	public String getXfzjcs() {
		return xfzjcs;
	}

	public void setXfzjcs(String xfzjcs) {
		this.xfzjcs = xfzjcs;
	}
	public String getXfgx() {
		return xfgx;
	}

	public void setXfgx(String xfgx) {
		this.xfgx = xfgx;
	}
	public String getJzgldw() {
		return jzgldw;
	}

	public void setJzgldw(String jzgldw) {
		this.jzgldw = jzgldw;
	}
	public Date getZddwrdsj() {
		return zddwrdsj;
	}

	public void setZddwrdsj(Date zddwrdsj) {
		this.zddwrdsj = zddwrdsj;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
