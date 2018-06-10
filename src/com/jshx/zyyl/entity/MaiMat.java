package com.jshx.zyyl.entity;

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
@Table(name="MAI_MAT")
public class MaiMat extends BaseModel
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
	 * 物料
	 */
	private String material;

	/**
	 * 危险性分析
	 */
	private String riskAnalysis;

	/**
	 * 存放方式
	 */
	private String storageMode;

	
	public MaiMat(){
	}
	
	public MaiMat(String id, String areaName, String companyName, String material){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.material = material;
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

	@Column(name="MATERIAL")
	public String getMaterial()
	{
		return this.material;
	}

	public void setMaterial(String material)
	{
		this.material = material;
	}

	@Column(name="RISK_ANALYSIS")
	public String getRiskAnalysis()
	{
		return this.riskAnalysis;
	}

	public void setRiskAnalysis(String riskAnalysis)
	{
		this.riskAnalysis = riskAnalysis;
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

}
