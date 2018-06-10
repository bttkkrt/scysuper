package com.jshx.scxcgl.entity;

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
@Table(name="PRODUCTION_MANAGE")
public class ProductionManage extends BaseModel
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
	 * 作业类型
	 */
	private String jobType;
	
	/**
	 * 作业日期
	 */
	private Date jobTime;

	/**
	 * 作业内容
	 */
	private String jobContent;

	/**
	 * 危害因素分析
	 */
	private String hazardAnalysiss;

	/**
	 * 项目负责人
	 */
	private String personInCharge;

	/**
	 * 附件关联id
	 */
	private String linkId;

	/**
	 * 安全措施
	 */
	private String safeMeasures;
	
	/**
	 * 应急措施措施
	 */
	private String emerMeasure;
	
	/**
	 * 施工负责人
	 */
	private String personName;
	
	public ProductionManage(){
	}
	
	public ProductionManage(String id, String areaName, String companyName, Date jobTime, String personInCharge){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.jobTime = jobTime;

this.personInCharge = personInCharge;
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

	@Column(name="JOB_TYPE")
	public String getJobType()
	{
		return this.jobType;
	}

	public void setJobType(String jobType)
	{
		this.jobType = jobType;
	}

	@Column(name="JOB_CONTENT")
	public String getJobContent()
	{
		return this.jobContent;
	}

	public void setJobContent(String jobContent)
	{
		this.jobContent = jobContent;
	}

	@Column(name="HAZARD_ANALYSISS")
	public String getHazardAnalysiss()
	{
		return this.hazardAnalysiss;
	}

	public void setHazardAnalysiss(String hazardAnalysiss)
	{
		this.hazardAnalysiss = hazardAnalysiss;
	}

	@Column(name="PERSON_IN_CHARGE")
	public String getPersonInCharge()
	{
		return this.personInCharge;
	}

	public void setPersonInCharge(String personInCharge)
	{
		this.personInCharge = personInCharge;
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
	@Column(name="SAFETY_MEASURES")
	public String getSafeMeasures() {
		return safeMeasures;
	}

	public void setSafeMeasures(String safeMeasures) {
		this.safeMeasures = safeMeasures;
	}
	@Column(name="EMERGENCY_MEASURES")
	public String getEmerMeasure() {
		return emerMeasure;
	}

	public void setEmerMeasure(String emerMeasure) {
		this.emerMeasure = emerMeasure;
	}
	@Column(name="PERSON_NAME")
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@Column(name="JOB_TIME")
	public Date getJobTime() {
		return jobTime;
	}

	public void setJobTime(Date jobTime) {
		this.jobTime = jobTime;
	}

}