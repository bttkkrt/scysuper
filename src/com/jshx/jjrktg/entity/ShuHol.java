package com.jshx.jjrktg.entity;

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
@Table(name="SHU_HOL")
public class ShuHol extends BaseModel
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
	 * 放假时间(开始)
	 */
	private Date holidayTimeStart;
	/**
	 * 放假时间(结束)
	 */
	private Date holidayTimeEnd;

	/**
	 * 是否开工
	 */
	private String ifStart;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 关联id
	 */
	private String linkId;

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
	private String auditResults;

	/**
	 * 审核状态
	 */
	private String auditState;
	
	public ShuHol(){
	}
	
	public ShuHol(String id, String areaId, String companyName, Date holidayTimeStart, Date holidayTimeEnd, String ifStart,String auditState){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.holidayTimeStart = holidayTimeStart;
this.holidayTimeEnd=holidayTimeEnd;

this.ifStart = ifStart;
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

	@Column(name="HOLIDAY_TIME_START")
	public Date getHolidayTimeStart()
	{
		return this.holidayTimeStart;
	}

	public void setHolidayTimeStart(Date holidayTimeStart)
	{
		this.holidayTimeStart = holidayTimeStart;
	}
	
	@Column(name="HOLIDAY_TIME_END")
	public Date getHolidayTimeEnd()
	{
		return this.holidayTimeEnd;
	}

	public void setHolidayTimeEnd(Date holidayTimeEnd)
	{
		this.holidayTimeEnd = holidayTimeEnd;
	}

	@Column(name="IF_START")
	public String getIfStart()
	{
		return this.ifStart;
	}

	public void setIfStart(String ifStart)
	{
		this.ifStart = ifStart;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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

	@Column(name="AUDIT_RESULTS")
	public String getAuditResults()
	{
		return this.auditResults;
	}

	public void setAuditResults(String auditResults)
	{
		this.auditResults = auditResults;
	}
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
}
