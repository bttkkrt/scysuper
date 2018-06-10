/*
*作者：李海棠
*/
package com.jshx.dangerousChemicalsPlan.entity;

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
@Table(name="DANGEROUS_CHEMICALS_PLAN")
public class DangerousChemicalsPlan extends BaseModel
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
	 * 单位名称
	 */
	private String companyName;

	/**
	 * 上报时间
	 */
	private String reportTime;

	/**
	 * 危险源名称
	 */
	private String dangerName;

	/**
	 * 备案编号
	 */
	private String planId;

	/**
	 * 备案有效期
	 */
	private String planValid;

	/**
	 * 联系人
	 */
	private String contact;

	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 报告发送时间
	 */
	private String sendTime;

	/**
	 * 企业编号
	 */
	private String qyid;

private String szzid;
	
	private String szzname;

private String szc;
	
	private String szcname;
	
	private String linkId;
	
	private String userName;
	
	/**
	 * 危险源级别
	 */
	private String dangerLevel;
	/**
	 * 危险化学品单位类型
	 */
	private String dangerType;
	
	/**
	 * 备案日期
	 */
	private String planDate;
	
	@Column
	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	@Column
	public String getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(String dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	@Column
	public String getDangerType() {
		return dangerType;
	}

	public void setDangerType(String dangerType) {
		this.dangerType = dangerType;
	}

	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	
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
	public String getSzzid() {
		return szzid;
	}

	public void setSzzid(String szzid) {
		this.szzid = szzid;
	}

	@Column
	public String getSzzname() {
		return szzname;
	}

	public void setSzzname(String szzname) {
		this.szzname = szzname;
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

	
	@Column(name="COMPANY_NAME")
	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	@Column(name="REPORT_TIME")
	public String getReportTime()
	{
		return this.reportTime;
	}

	public void setReportTime(String reportTime)
	{
		this.reportTime = reportTime;
	}

	@Column(name="DANGER_NAME")
	public String getDangerName()
	{
		return this.dangerName;
	}

	public void setDangerName(String dangerName)
	{
		this.dangerName = dangerName;
	}

	@Column(name="PLAN_ID")
	public String getPlanId()
	{
		return this.planId;
	}

	public void setPlanId(String planId)
	{
		this.planId = planId;
	}

	@Column(name="PLAN_VALID")
	public String getPlanValid()
	{
		return this.planValid;
	}

	public void setPlanValid(String planValid)
	{
		this.planValid = planValid;
	}

	@Column(name="CONTACT")
	public String getContact()
	{
		return this.contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	@Column(name="TEL")
	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	@Column(name="SEND_TIME")
	public String getSendTime()
	{
		return this.sendTime;
	}

	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
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
	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
