package com.jshx.xgfgl.entity;

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
@Table(name="PARTY_MANAGE")
public class PartyManage extends BaseModel
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
	 * 服务类型
	 */
	private String serviceType;

	/**
	 * 行为风险
	 */
	private String behaveRisk;

	/**
	 * 控制措施
	 */
	private String controlMeasures;

	/**
	 * 安全生产责任
	 */
	private String safetyProDuty;

	/**
	 * 义务
	 */
	private String obligation;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;

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
	 * 相关方名称
	 */
	private String partyName;

	/**
	 * 车间ID
	 */
	private String wokrshopId;

	/**
	 * 车间名称
	 */
	private String wokrshopName;

	
	public PartyManage(){
	}
	
	public PartyManage(String id, String areaId, String companyName, String partyName, String wokrshopName){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.partyName = partyName;

this.wokrshopName = wokrshopName;
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

	
	@Column(name="SERVICE_TYPE")
	public String getServiceType()
	{
		return this.serviceType;
	}

	public void setServiceType(String serviceType)
	{
		this.serviceType = serviceType;
	}

	@Column(name="BEHAVE_RISK")
	public String getBehaveRisk()
	{
		return this.behaveRisk;
	}

	public void setBehaveRisk(String behaveRisk)
	{
		this.behaveRisk = behaveRisk;
	}

	@Column(name="CONTROL_MEASURES")
	public String getControlMeasures()
	{
		return this.controlMeasures;
	}

	public void setControlMeasures(String controlMeasures)
	{
		this.controlMeasures = controlMeasures;
	}

	@Column(name="SAFETY_PRO_DUTY")
	public String getSafetyProDuty()
	{
		return this.safetyProDuty;
	}

	public void setSafetyProDuty(String safetyProDuty)
	{
		this.safetyProDuty = safetyProDuty;
	}

	@Column(name="OBLIGATION")
	public String getObligation()
	{
		return this.obligation;
	}

	public void setObligation(String obligation)
	{
		this.obligation = obligation;
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

	@Column(name="PARTY_NAME")
	public String getPartyName()
	{
		return this.partyName;
	}

	public void setPartyName(String partyName)
	{
		this.partyName = partyName;
	}

	@Column(name="WOKRSHOP_ID")
	public String getWokrshopId()
	{
		return this.wokrshopId;
	}

	public void setWokrshopId(String wokrshopId)
	{
		this.wokrshopId = wokrshopId;
	}

	@Column(name="WOKRSHOP_NAME")
	public String getWokrshopName()
	{
		return this.wokrshopName;
	}

	public void setWokrshopName(String wokrshopName)
	{
		this.wokrshopName = wokrshopName;
	}

}
