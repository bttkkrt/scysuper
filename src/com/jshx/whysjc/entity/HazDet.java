package com.jshx.whysjc.entity;

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
@Table(name="HAZ_DET")
public class HazDet extends BaseModel
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
	 * 检测危害因素
	 */
	private String detectionRiskFactors;

	/**
	 * 监测点数
	 */
	private String monitoringPoints;

	/**
	 * 检测日期
	 */
	private Date testDate;

	/**
	 * 不合格点的危害因素名称
	 */
	private String hazardFactorName;

	/**
	 * 检测机构
	 */
	private String detectionMechanism;

	/**
	 * 关联id
	 */
	private String linkId;
	
	/**
	 * 不合格点数
	 */
	private String unqualifiedPoints;


	
	public HazDet(){
	}
	
	public HazDet(String id,String areaId, String companyName, String detectionRiskFactors, String monitoringPoints, Date testDate,String createUserID){
this.id = id;
this.areaId=areaId;

this.companyName = companyName;

this.detectionRiskFactors = detectionRiskFactors;

this.monitoringPoints = monitoringPoints;

this.testDate = testDate;
this.createUserID=createUserID;
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

	@Column(name="DETECTION_RISK_FACTORS")
	public String getDetectionRiskFactors()
	{
		return this.detectionRiskFactors;
	}

	public void setDetectionRiskFactors(String detectionRiskFactors)
	{
		this.detectionRiskFactors = detectionRiskFactors;
	}

	@Column(name="MONITORING_POINTS")
	public String getMonitoringPoints()
	{
		return this.monitoringPoints;
	}

	public void setMonitoringPoints(String monitoringPoints)
	{
		this.monitoringPoints = monitoringPoints;
	}

	@Column(name="TEST_DATE")
	public Date getTestDate()
	{
		return this.testDate;
	}

	public void setTestDate(Date testDate)
	{
		this.testDate = testDate;
	}

	@Column(name="HAZARD_FACTOR_NAME")
	public String getHazardFactorName()
	{
		return this.hazardFactorName;
	}

	public void setHazardFactorName(String hazardFactorName)
	{
		this.hazardFactorName = hazardFactorName;
	}

	@Column(name="DETECTION_MECHANISM")
	public String getDetectionMechanism()
	{
		return this.detectionMechanism;
	}

	public void setDetectionMechanism(String detectionMechanism)
	{
		this.detectionMechanism = detectionMechanism;
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
	@Column(name="UNQUALIFIED_POINTS")
	public String getUnqualifiedPoints() {
		return unqualifiedPoints;
	}

	public void setUnqualifiedPoints(String unqualifiedPoints) {
		this.unqualifiedPoints = unqualifiedPoints;
	}

}
