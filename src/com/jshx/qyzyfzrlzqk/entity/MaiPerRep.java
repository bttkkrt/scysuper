package com.jshx.qyzyfzrlzqk.entity;

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
@Table(name="MAI_PER_REP")
public class MaiPerRep extends BaseModel
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
	 * 主要负责人
	 */
	private String principalResponsiblePerson;

	/**
	 * 履职报告
	 */
	private String performanceReport;

	/**
	 * 是否危险工艺
	 */
	private String ifHazardousProcess;

	/**
	 * 是否构成重大危险源
	 */
	private String ifHazardSources;

	/**
	 * 关联id
	 */
	private String linkId;

	
	public MaiPerRep(){
	}
	
	public MaiPerRep(String id, String areaName, String companyName,String principalResponsiblePerson){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;
this.principalResponsiblePerson = principalResponsiblePerson;
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

	@Column(name="PRINCIPAL_RESPONSIBLE_PERSON")
	public String getPrincipalResponsiblePerson()
	{
		return this.principalResponsiblePerson;
	}

	public void setPrincipalResponsiblePerson(String principalResponsiblePerson)
	{
		this.principalResponsiblePerson = principalResponsiblePerson;
	}

	@Column(name="PERFORMANCE_REPORT")
	public String getPerformanceReport()
	{
		return this.performanceReport;
	}

	public void setPerformanceReport(String performanceReport)
	{
		this.performanceReport = performanceReport;
	}

	@Column(name="IF_HAZARDOUS_PROCESS")
	public String getIfHazardousProcess()
	{
		return this.ifHazardousProcess;
	}

	public void setIfHazardousProcess(String ifHazardousProcess)
	{
		this.ifHazardousProcess = ifHazardousProcess;
	}

	@Column(name="IF_HAZARD_SOURCES")
	public String getIfHazardSources()
	{
		return this.ifHazardSources;
	}

	public void setIfHazardSources(String ifHazardSources)
	{
		this.ifHazardSources = ifHazardSources;
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

}
