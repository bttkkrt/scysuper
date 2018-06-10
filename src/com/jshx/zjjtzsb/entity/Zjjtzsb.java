package com.jshx.zjjtzsb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ZJJTZSB")
public class Zjjtzsb extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	
	/**
	 * 单位地址
	 */
	private String dwdz;

	/**
	 * 所属乡镇id
	 */
	private String szzid;

	/**
	 * 所属乡镇名称
	 */
	private String szzname;

	/**
	 * 设备类别
	 */
	private String sblb;

	/**
	 * 设备档案号
	 */
	private String sbdah;

	/**
	 * 注册代码
	 */
	private String zcdm;

	/**
	 * 检验日期
	 */
	private String jyrq;

	/**
	 * 检验结论
	 */
	private String jyjl;

	/**
	 * 检查日期
	 */
	private String jcrq;

	/**
	 * 检查人员
	 */
	private String jcry;

	/**
	 * 主要问题
	 */
	private String zywt;

	/**
	 * 单位联系人
	 */
	private String dwlxr;

	/**
	 * 电话
	 */
	private String dh;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 单位id
	 */
	private String qyid;

	/**
	 * 单位名称
	 */
	private String qymc;
	
	/**
	 * 审核状态 0表示待整改，1表示待核实，2表示待审核，3表示已办结,4表示整改不合格
	 */
	private String state;
	
	/*
	 * 审核备注
	 */
	private String remark;
	
	/*
	 * 是否可修改
	 */
	private String isFirst;
	
	/*
	 * 审核备注
	 */
	private String shbs;
	
	/**
	 * 整改情况
	 */
	private String zgqk;
	
	/**
	 * 整改完成时间
	 */
	private String zgwcsj;
	
	private String linkId;
	
	private String szc;
	
	private String szcname;
	
	
	@Column
	public String getSzc() {
		return szc;
	}

	public void setSzc(String szc) {
		this.szc = szc;
	}
	@Column
	public String getSzcname() {
		return szcname;
	}

	public void setSzcname(String szcname) {
		this.szcname = szcname;
	}

	@Column
	public String getShbs() {
		return shbs;
	}

	public void setShbs(String shbs) {
		this.shbs = shbs;
	}

	@Column
	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	@Column
	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	@Column
	public Integer getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	
	@Column(name="DWDZ")
	public String getDwdz()
	{
		return this.dwdz;
	}

	public void setDwdz(String dwdz)
	{
		this.dwdz = dwdz;
	}

	@Column(name="SZZID")
	public String getSzzid()
	{
		return this.szzid;
	}

	public void setSzzid(String szzid)
	{
		this.szzid = szzid;
	}

	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
	}

	@Column(name="SBLB")
	public String getSblb()
	{
		return this.sblb;
	}

	public void setSblb(String sblb)
	{
		this.sblb = sblb;
	}

	@Column(name="SBDAH")
	public String getSbdah()
	{
		return this.sbdah;
	}

	public void setSbdah(String sbdah)
	{
		this.sbdah = sbdah;
	}

	@Column(name="ZCDM")
	public String getZcdm()
	{
		return this.zcdm;
	}

	public void setZcdm(String zcdm)
	{
		this.zcdm = zcdm;
	}

	@Column(name="JYRQ")
	public String getJyrq()
	{
		return this.jyrq;
	}

	public void setJyrq(String jyrq)
	{
		this.jyrq = jyrq;
	}

	@Column(name="JYJL")
	public String getJyjl()
	{
		return this.jyjl;
	}

	public void setJyjl(String jyjl)
	{
		this.jyjl = jyjl;
	}

	@Column(name="JCRQ")
	public String getJcrq()
	{
		return this.jcrq;
	}

	public void setJcrq(String jcrq)
	{
		this.jcrq = jcrq;
	}

	@Column(name="JCRY")
	public String getJcry()
	{
		return this.jcry;
	}

	public void setJcry(String jcry)
	{
		this.jcry = jcry;
	}

	@Column(name="ZYWT")
	public String getZywt()
	{
		return this.zywt;
	}

	public void setZywt(String zywt)
	{
		this.zywt = zywt;
	}

	@Column(name="DWLXR")
	public String getDwlxr()
	{
		return this.dwlxr;
	}

	public void setDwlxr(String dwlxr)
	{
		this.dwlxr = dwlxr;
	}

	@Column(name="DH")
	public String getDh()
	{
		return this.dh;
	}

	public void setDh(String dh)
	{
		this.dh = dh;
	}

	@Column(name="BZ")
	public String getBz()
	{
		return this.bz;
	}

	public void setBz(String bz)
	{
		this.bz = bz;
	}

	@Column(name="QYID")
	public String getQyid()
	{
		return this.qyid;
	}

	public void setQyid(String qyid)
	{
		this.qyid = qyid;
	}

	@Column(name="QYMC")
	public String getQymc()
	{
		return this.qymc;
	}

	public void setQymc(String qymc)
	{
		this.qymc = qymc;
	}
	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column
	public String getZgqk() {
		return zgqk;
	}

	public void setZgqk(String zgqk) {
		this.zgqk = zgqk;
	}
	@Column
	public String getZgwcsj() {
		return zgwcsj;
	}

	public void setZgwcsj(String zgwcsj) {
		this.zgwcsj = zgwcsj;
	}
	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
}
