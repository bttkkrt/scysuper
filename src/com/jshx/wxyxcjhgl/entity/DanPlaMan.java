package com.jshx.wxyxcjhgl.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="DAN_PLA_MAN")
public class DanPlaMan extends BaseModel
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
	 * 所在区域id
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 计划名称
	 */
	private String planName;

	/**
	 * 危险源ID
	 */
	private String dangerId;

	/**
	 * 危险源名称
	 */
	private String dangerName;

	/**
	 * 计划开始时间
	 */
	private Date planStartTime;

	/**
	 * 计划结束时间
	 */
	private Date planEndTime;

	/**
	 * 巡查频率
	 */
	private String checkFrequency;

	/**
	 * 巡查人员ID
	 */
	private String checkPeopleId;

	/**
	 * 巡查人员姓名
	 */
	private String checkPeopleName;

	/**
	 * 巡查项类型ID
	 */
	private String checkTypeId;

	/**
	 * 审核人ID
	 */
	private String auditPersonId;

	/**
	 * 审核人姓名
	 */
	private String auditPersonName;

	/**
	 * 审核结果
	 */
	private String auditResult;
	/**
	 * 审核备注
	 */
	private String remark;
	
	/**
	 * 巡查类型
	 */
	private String checkKind;
	
	/**
	 * 巡查路线Id
	 */
	private String checkRodeId;
	
	/**
	 * 巡查路线名称
	 */
	private String checkRodeName;
	
	/**
	 * 新增是否审核
	 * 巡查计划非必须审核的 0:是 1：否 
	 * lj
	 * 2015-12-22
	 */
	private String isAudit;
	
	/**
	 * 巡查项目id
	 */
	private  String checkId;
	
	/**
	 * 巡查项目名称
	 */
	private String checkName;
	
	@Column
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	public DanPlaMan(){
	}
	
	public DanPlaMan(String id, String companyName,String planName, String dangerName, String checkFrequency, String checkPeopleName, String checkTypeId, String auditResult,String isAudit){
this.id = id;

this.companyName = companyName;

this.planName = planName;

this.dangerName = dangerName;

this.checkFrequency = checkFrequency;

this.checkPeopleName = checkPeopleName;

this.checkTypeId = checkTypeId;

this.auditResult = auditResult;
this.isAudit=isAudit;

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

	
	@Column(name="AREA_ID")
	public String getAreaId()
	{
		return this.areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="COMPANY_ID")
	public String getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
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

	@Column(name="PLAN_NAME")
	public String getPlanName()
	{
		return this.planName;
	}

	public void setPlanName(String planName)
	{
		this.planName = planName;
	}

	@Column(name="DANGER_ID")
	public String getDangerId()
	{
		return this.dangerId;
	}

	public void setDangerId(String dangerId)
	{
		this.dangerId = dangerId;
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

	@Column(name="PLAN_START_TIME")
	public Date getPlanStartTime()
	{
		return this.planStartTime;
	}

	public void setPlanStartTime(Date planStartTime)
	{
		this.planStartTime = planStartTime;
	}

	@Column(name="PLAN_END_TIME")
	public Date getPlanEndTime()
	{
		return this.planEndTime;
	}

	public void setPlanEndTime(Date planEndTime)
	{
		this.planEndTime = planEndTime;
	}

	@Column(name="CHECK_FREQUENCY")
	public String getCheckFrequency()
	{
		return this.checkFrequency;
	}

	public void setCheckFrequency(String checkFrequency)
	{
		this.checkFrequency = checkFrequency;
	}

	@Column(name="CHECK_PEOPLE_ID")
	public String getCheckPeopleId()
	{
		return this.checkPeopleId;
	}

	public void setCheckPeopleId(String checkPeopleId)
	{
		this.checkPeopleId = checkPeopleId;
	}

	@Column(name="CHECK_PEOPLE_NAME")
	public String getCheckPeopleName()
	{
		return this.checkPeopleName;
	}

	public void setCheckPeopleName(String checkPeopleName)
	{
		this.checkPeopleName = checkPeopleName;
	}

	@Column(name="CHECK_TYPE_ID")
	public String getCheckTypeId()
	{
		return this.checkTypeId;
	}

	public void setCheckTypeId(String checkTypeId)
	{
		this.checkTypeId = checkTypeId;
	}

	@Column(name="AUDIT_PERSON_ID")
	public String getAuditPersonId()
	{
		return this.auditPersonId;
	}

	public void setAuditPersonId(String auditPersonId)
	{
		this.auditPersonId = auditPersonId;
	}

	@Column(name="AUDIT_PERSON_NAME")
	public String getAuditPersonName()
	{
		return this.auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName)
	{
		this.auditPersonName = auditPersonName;
	}

	@Column(name="AUDIT_RESULT")
	public String getAuditResult()
	{
		return this.auditResult;
	}

	public void setAuditResult(String auditResult)
	{
		this.auditResult = auditResult;
	}
	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="CHECK_KIND")
	

	public String getCheckKind() {
		return checkKind;
	}
	public void setCheckKind(String checkKind) {
		this.checkKind = checkKind;
	}
	@Column(name="CHECK_RODE_ID")
	

	public String getCheckRodeId() {
		return checkRodeId;
	}

	public void setCheckRodeId(String checkRodeId) {
		this.checkRodeId = checkRodeId;
	}
	@Column(name="CHECK_RODE_NAME")
	public String getCheckRodeName() {
		return checkRodeName;
	}

	public void setCheckRodeName(String checkRodeName) {
		this.checkRodeName = checkRodeName;
	}
	@Column(name="CHECK_ID")
	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	@Column(name="CHECK_NAME")
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

}
