package com.jshx.zdwhpclqkb.entity;

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
@Table(name="CHE_RES")
public class CheRes extends BaseModel
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
	 * 危险化学品名称
	 */
	private String dangerousChemicalName;

	/**
	 * 危规号
	 */
	private String riskGauge;

	/**
	 * UN号
	 */
	private String unNumber;

	/**
	 * 年使用量
	 */
	private String annualUsage;

	/**
	 * 最大贮存量
	 */
	private String maximumStorageCapacity;

	/**
	 * 贮存方式
	 */
	private String storageMode;

	/**
	 * 贮存地点
	 */
	private String storageLocation;

	/**
	 * 包装方式
	 */
	private String packing;

	/**
	 * 是否易制毒化学品
	 */
	private String isChemical;

	/**
	 * 是否重点监管化学品
	 */
	private String isRegulatorChemical;

	/**
	 * 是否易制爆化学品
	 */
	private String isExplosiveChemical;

	/**
	 * 是否剧毒学品
	 */
	private String isToxicChemical;

	/**
	 * 现有储存量
	 */
	private String storage;

	
	public CheRes(){
	}
	
	public CheRes(String id, String areaId, String companyName, String dangerousChemicalName, String riskGauge, String unNumber, String storage,String annualUsage){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.dangerousChemicalName = dangerousChemicalName;

this.riskGauge = riskGauge;

this.unNumber = unNumber;

this.storage = storage;
this.annualUsage=annualUsage;
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

	@Column(name="DANGEROUS_CHEMICAL_NAME")
	public String getDangerousChemicalName()
	{
		return this.dangerousChemicalName;
	}

	public void setDangerousChemicalName(String dangerousChemicalName)
	{
		this.dangerousChemicalName = dangerousChemicalName;
	}

	@Column(name="RISK_GAUGE")
	public String getRiskGauge()
	{
		return this.riskGauge;
	}

	public void setRiskGauge(String riskGauge)
	{
		this.riskGauge = riskGauge;
	}

	@Column(name="UN_NUMBER")
	public String getUnNumber()
	{
		return this.unNumber;
	}

	public void setUnNumber(String unNumber)
	{
		this.unNumber = unNumber;
	}

	@Column(name="ANNUAL_USAGE")
	public String getAnnualUsage()
	{
		return this.annualUsage;
	}

	public void setAnnualUsage(String annualUsage)
	{
		this.annualUsage = annualUsage;
	}

	@Column(name="MAXIMUM_STORAGE_CAPACITY")
	public String getMaximumStorageCapacity()
	{
		return this.maximumStorageCapacity;
	}

	public void setMaximumStorageCapacity(String maximumStorageCapacity)
	{
		this.maximumStorageCapacity = maximumStorageCapacity;
	}

	@Column(name="STORAGE_MODE")
	public String getStorageMode()
	{
		return this.storageMode;
	}

	public void setStorageMode(String storageMode)
	{
		this.storageMode = storageMode;
	}

	@Column(name="STORAGE_LOCATION")
	public String getStorageLocation()
	{
		return this.storageLocation;
	}

	public void setStorageLocation(String storageLocation)
	{
		this.storageLocation = storageLocation;
	}

	@Column(name="PACKING")
	public String getPacking()
	{
		return this.packing;
	}

	public void setPacking(String packing)
	{
		this.packing = packing;
	}

	@Column(name="IS_CHEMICAL")
	public String getIsChemical()
	{
		return this.isChemical;
	}

	public void setIsChemical(String isChemical)
	{
		this.isChemical = isChemical;
	}

	@Column(name="IS_REGULATOR_CHEMICAL")
	public String getIsRegulatorChemical()
	{
		return this.isRegulatorChemical;
	}

	public void setIsRegulatorChemical(String isRegulatorChemical)
	{
		this.isRegulatorChemical = isRegulatorChemical;
	}

	@Column(name="IS_EXPLOSIVE_CHEMICAL")
	public String getIsExplosiveChemical()
	{
		return this.isExplosiveChemical;
	}

	public void setIsExplosiveChemical(String isExplosiveChemical)
	{
		this.isExplosiveChemical = isExplosiveChemical;
	}

	@Column(name="IS_TOXIC_CHEMICAL")
	public String getIsToxicChemical()
	{
		return this.isToxicChemical;
	}

	public void setIsToxicChemical(String isToxicChemical)
	{
		this.isToxicChemical = isToxicChemical;
	}

	@Column(name="STORAGE")
	public String getStorage()
	{
		return this.storage;
	}

	public void setStorage(String storage)
	{
		this.storage = storage;
	}

}
