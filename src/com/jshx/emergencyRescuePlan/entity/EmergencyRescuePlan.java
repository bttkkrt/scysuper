/*
*作者：李海棠
*/
package com.jshx.emergencyRescuePlan.entity;

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
@Table(name="EMERGENCY_RESCUE_PLAN")
public class EmergencyRescuePlan extends BaseModel
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
	 * 备案编号
	 */
	private String planId;

	/**
	 * 备案告知书发送时间
	 */
	private String sendTime;
	
	private String userName;
	
	private String qyid;

	private String szzid;
	
	private String szzname;

	private String linkId;
	
	/**
	 * 备案日期
	 */
	private String planDate;
	
	/**
	 * 备案机关
	 */
	private String planJg;
	
	@Column
	public String getPlanJg() {
		return planJg;
	}

	public void setPlanJg(String planJg) {
		this.planJg = planJg;
	}

	@Column
	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	@Column(name="PLAN_ID")
	public String getPlanId()
	{
		return this.planId;
	}

	public void setPlanId(String planId)
	{
		this.planId = planId;
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
	public String getQyid() {
		return qyid;
	}

	public void setQyid(String qyid) {
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
