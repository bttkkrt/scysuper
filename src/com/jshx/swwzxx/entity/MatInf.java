package com.jshx.swwzxx.entity;

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
@Table(name="MAT_INF")
public class MatInf extends BaseModel
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
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 危险源单元名称
	 */
	private String dangerouSourceName;

	/**
	 * 是否剧毒化学品
	 */
	private String ifToxicChemicals;

	/**
	 * 所处装置
	 */
	private String device;

	/**
	 * 临界值
	 */
	private String criticalValue;

	/**
	 * 涉及危险工艺
	 */
	private String hazardousProcess;

	/**
	 * 运输注意事项
	 */
	private String transportationNote;

	/**
	 * 危险化学品名称
	 */
	private String dangerousChemicalName;

	/**
	 * 危规号
	 */
	private String riskGauge;

	/**
	 * 存在量
	 */
	private String existenceQuantity;

	/**
	 * 校正系数
	 */
	private String correctionFactor;

	/**
	 * 操作人员情况
	 */
	private String operatingPersonnel;

	/**
	 * 备注
	 */
	private String remark;

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

	
	public MatInf(){
	}
	
	public MatInf(String id, String companyName, String dangerousChemicalName, String riskGauge, String areaId,String dangerouSourceName,String device,String existenceQuantity){
this.id = id;

this.companyName = companyName;

this.dangerousChemicalName = dangerousChemicalName;

this.riskGauge = riskGauge;

this.areaId = areaId;
this.dangerouSourceName=dangerouSourceName;
this.device=device;
this.existenceQuantity=existenceQuantity;
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

	@Column(name="DANGEROU_SOURCE_NAME")
	public String getDangerouSourceName()
	{
		return this.dangerouSourceName;
	}

	public void setDangerouSourceName(String dangerouSourceName)
	{
		this.dangerouSourceName = dangerouSourceName;
	}

	@Column(name="IF_TOXIC_CHEMICALS")
	public String getIfToxicChemicals()
	{
		return this.ifToxicChemicals;
	}

	public void setIfToxicChemicals(String ifToxicChemicals)
	{
		this.ifToxicChemicals = ifToxicChemicals;
	}

	@Column(name="DEVICE")
	public String getDevice()
	{
		return this.device;
	}

	public void setDevice(String device)
	{
		this.device = device;
	}

	@Column(name="CRITICAL_VALUE")
	public String getCriticalValue()
	{
		return this.criticalValue;
	}

	public void setCriticalValue(String criticalValue)
	{
		this.criticalValue = criticalValue;
	}

	@Column(name="HAZARDOUS_PROCESS")
	public String getHazardousProcess()
	{
		return this.hazardousProcess;
	}

	public void setHazardousProcess(String hazardousProcess)
	{
		this.hazardousProcess = hazardousProcess;
	}

	@Column(name="TRANSPORTATION_NOTE")
	public String getTransportationNote()
	{
		return this.transportationNote;
	}

	public void setTransportationNote(String transportationNote)
	{
		this.transportationNote = transportationNote;
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

	@Column(name="EXISTENCE_QUANTITY")
	public String getExistenceQuantity()
	{
		return this.existenceQuantity;
	}

	public void setExistenceQuantity(String existenceQuantity)
	{
		this.existenceQuantity = existenceQuantity;
	}

	@Column(name="CORRECTION_FACTOR")
	public String getCorrectionFactor()
	{
		return this.correctionFactor;
	}

	public void setCorrectionFactor(String correctionFactor)
	{
		this.correctionFactor = correctionFactor;
	}

	@Column(name="OPERATING_PERSONNEL")
	public String getOperatingPersonnel()
	{
		return this.operatingPersonnel;
	}

	public void setOperatingPersonnel(String operatingPersonnel)
	{
		this.operatingPersonnel = operatingPersonnel;
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

}
