package com.jshx.qygjzzhzdbw.entity;

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
@Table(name="KEY_PAR")
public class KeyPar extends BaseModel
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
	 * 主要危险因素
	 */
	private String majorRiskFactors;

	/**
	 * 责任人
	 */
	private String responsiblePerson;

	/**
	 * 岗位员工数量
	 */
	private String postEmployeeNumber;

	/**
	 * 安全操作规程
	 */
	private String safetyOperationRegulations;

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
	 * 关键装置重点部位名称
	 */
	private String keyPartName;
	
	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public KeyPar(){
	}
	
	public KeyPar(String id, String responsiblePerson, String areaName, String companyName, String keyPartName){
this.id = id;

this.responsiblePerson = responsiblePerson;

this.areaName = areaName;

this.companyName = companyName;

this.keyPartName = keyPartName;
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

	
	@Column(name="MAJOR_RISK_FACTORS")
	public String getMajorRiskFactors()
	{
		return this.majorRiskFactors;
	}

	public void setMajorRiskFactors(String majorRiskFactors)
	{
		this.majorRiskFactors = majorRiskFactors;
	}

	@Column(name="RESPONSIBLE_PERSON")
	public String getResponsiblePerson()
	{
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson)
	{
		this.responsiblePerson = responsiblePerson;
	}

	@Column(name="POST_EMPLOYEE_NUMBER")
	public String getPostEmployeeNumber()
	{
		return this.postEmployeeNumber;
	}

	public void setPostEmployeeNumber(String postEmployeeNumber)
	{
		this.postEmployeeNumber = postEmployeeNumber;
	}

	@Column(name="SAFETY_OPERATION_REGULATIONS")
	public String getSafetyOperationRegulations()
	{
		return this.safetyOperationRegulations;
	}

	public void setSafetyOperationRegulations(String safetyOperationRegulations)
	{
		this.safetyOperationRegulations = safetyOperationRegulations;
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

	@Column(name="KEY_PART_NAME")
	public String getKeyPartName()
	{
		return this.keyPartName;
	}

	public void setKeyPartName(String keyPartName)
	{
		this.keyPartName = keyPartName;
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
