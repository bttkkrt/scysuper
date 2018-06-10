package com.jshx.lddbqk.entity;

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
@Table(name="LEA_CLA")
public class LeaCla extends BaseModel
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
	 * 计划月份
	 */
	private Date plannedMonth;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 审核人
	 */
	private String auditPerson;

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 审核结果
	 */
	private String auditResults;

	/**
	 * 审核记录
	 */
	private String auditRecord;
	
	/**
	 * 审核状态
	 */
	private String auditState;

	
	public LeaCla(){
	}
	
	public LeaCla(String id, String areaName, Date plannedMonth, String companyName,String auditResults,String auditState){
this.id = id;

this.areaName = areaName;

this.plannedMonth = plannedMonth;

this.companyName = companyName;
this.auditResults=auditResults;
this.auditState=auditState;
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

	@Column(name="PLANNED_MONTH")
	public Date getPlannedMonth()
	{
		return this.plannedMonth;
	}

	public void setPlannedMonth(Date plannedMonth)
	{
		this.plannedMonth = plannedMonth;
	}

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

	@Column(name="AUDIT_PERSON")
	public String getAuditPerson()
	{
		return this.auditPerson;
	}

	public void setAuditPerson(String auditPerson)
	{
		this.auditPerson = auditPerson;
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

	@Column(name="AUDIT_RESULTS")
	public String getAuditResults()
	{
		return this.auditResults;
	}

	public void setAuditResults(String auditResults)
	{
		this.auditResults = auditResults;
	}

	@Column(name="AUDIT_RECORD")
	public String getAuditRecord()
	{
		return this.auditRecord;
	}

	public void setAuditRecord(String auditRecord)
	{
		this.auditRecord = auditRecord;
	}

	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

}
