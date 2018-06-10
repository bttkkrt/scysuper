/*
*作者：李海棠
*/
package com.jshx.safetyEvaluationReport.entity;

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
@Table(name="SAFETY_EVALUATION_REPORT")
public class SafetyEvaluationReport extends BaseModel
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
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 备案编号
	 */
	private String safetyPlanId;

	/**
	 * 限制时间
	 */
	private String planTime;

	/**
	 * 发送时间
	 */
	private String sendTime;

	/**
	 * 企业编号
	 */
	private String qyid;
	private String szzid;
	
	private String szzname;
	
	private String linkId;
	
	private String userName;
	

	/**
	 * 报告完成日期
	 */
    private String bgwcDate;
	/**
	 * 报告上报日期
	 */
    private String bgsbDate;
    /**
	 * 评价机构
	 */
    private String pjjg;
    
    private String jdhxpname;
    
    private String nsyl;
    
    @Column
	public String getBgwcDate() {
		return bgwcDate;
	}

	public void setBgwcDate(String bgwcDate) {
		this.bgwcDate = bgwcDate;
	}
	@Column
	public String getBgsbDate() {
		return bgsbDate;
	}

	public void setBgsbDate(String bgsbDate) {
		this.bgsbDate = bgsbDate;
	}
	@Column
	public String getPjjg() {
		return pjjg;
	}

	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
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

	@Column(name="SAFETY_PLAN_ID")
	public String getSafetyPlanId()
	{
		return this.safetyPlanId;
	}

	public void setSafetyPlanId(String safetyPlanId)
	{
		this.safetyPlanId = safetyPlanId;
	}

	@Column(name="PLAN_TIME")
	public String getPlanTime()
	{
		return this.planTime;
	}

	public void setPlanTime(String planTime)
	{
		this.planTime = planTime;
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
	@Column
	public String getJdhxpname() {
		return jdhxpname;
	}

	public void setJdhxpname(String jdhxpname) {
		this.jdhxpname = jdhxpname;
	}
	@Column
	public String getNsyl() {
		return nsyl;
	}

	public void setNsyl(String nsyl) {
		this.nsyl = nsyl;
	}
}
