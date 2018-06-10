package com.jshx.sgbgdchcl.entity;

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
@Table(name="ACC_REP_INV_HAN")
public class AccRepInvHan extends BaseModel
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
	 * 事故编号
	 */
	private String accidentId;

	/**
	 * 事故名称
	 */
	private String accidentName;

	/**
	 * 事故发生时间
	 */
	private Date accidentTime;

	/**
	 * 事故经过
	 */
	private String accidentDescrip;

	/**
	 * 事故原因
	 */
	private String accidentReason;

	/**
	 * 轻伤人数
	 */
	private String concussionsNum;

	/**
	 * 重伤人数
	 */
	private String woundedNum;

	/**
	 * 死亡人数
	 */
	private String deathNum;

	/**
	 * 经济损失
	 */
	private String economicLoss;

	/**
	 * 事故级别
	 */
	private String accidentLevel;

	/**
	 * 事故类别
	 */
	private String accidentType;

	/**
	 * 调查组成员
	 */
	private String inverstTeamNumber;

	/**
	 * 事故责任
	 */
	private String accidentResponsible;

	/**
	 * 处理建议
	 */
	private String handleSuggest;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;
	/**
	 *整改措施
	 */
	private String method;

	
	public AccRepInvHan(){
	}
	
	public AccRepInvHan(String id, String areaName, String companyName, String accidentId, String accidentName, Date accidentTime, String accidentLevel, String accidentType){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.accidentId = accidentId;

this.accidentName = accidentName;

this.accidentTime = accidentTime;

this.accidentLevel = accidentLevel;

this.accidentType = accidentType;
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

	@Column(name="ACCIDENT_ID")
	public String getAccidentId()
	{
		return this.accidentId;
	}

	public void setAccidentId(String accidentId)
	{
		this.accidentId = accidentId;
	}

	@Column(name="ACCIDENT_NAME")
	public String getAccidentName()
	{
		return this.accidentName;
	}

	public void setAccidentName(String accidentName)
	{
		this.accidentName = accidentName;
	}

	@Column(name="ACCIDENT_TIME")
	public Date getAccidentTime()
	{
		return this.accidentTime;
	}

	public void setAccidentTime(Date accidentTime)
	{
		this.accidentTime = accidentTime;
	}

	@Column(name="ACCIDENT_DESCRIP")
	public String getAccidentDescrip()
	{
		return this.accidentDescrip;
	}

	public void setAccidentDescrip(String accidentDescrip)
	{
		this.accidentDescrip = accidentDescrip;
	}

	@Column(name="ACCIDENT_REASON")
	public String getAccidentReason()
	{
		return this.accidentReason;
	}

	public void setAccidentReason(String accidentReason)
	{
		this.accidentReason = accidentReason;
	}

	@Column(name="CONCUSSIONS_NUM")
	public String getConcussionsNum()
	{
		return this.concussionsNum;
	}

	public void setConcussionsNum(String concussionsNum)
	{
		this.concussionsNum = concussionsNum;
	}

	@Column(name="WOUNDED_NUM")
	public String getWoundedNum()
	{
		return this.woundedNum;
	}

	public void setWoundedNum(String woundedNum)
	{
		this.woundedNum = woundedNum;
	}

	@Column(name="DEATH_NUM")
	public String getDeathNum()
	{
		return this.deathNum;
	}

	public void setDeathNum(String deathNum)
	{
		this.deathNum = deathNum;
	}

	@Column(name="ECONOMIC_LOSS")
	public String getEconomicLoss()
	{
		return this.economicLoss;
	}

	public void setEconomicLoss(String economicLoss)
	{
		this.economicLoss = economicLoss;
	}

	@Column(name="ACCIDENT_LEVEL")
	public String getAccidentLevel()
	{
		return this.accidentLevel;
	}

	public void setAccidentLevel(String accidentLevel)
	{
		this.accidentLevel = accidentLevel;
	}

	@Column(name="ACCIDENT_TYPE")
	public String getAccidentType()
	{
		return this.accidentType;
	}

	public void setAccidentType(String accidentType)
	{
		this.accidentType = accidentType;
	}

	@Column(name="INVERST_TEAM_NUMBER")
	public String getInverstTeamNumber()
	{
		return this.inverstTeamNumber;
	}

	public void setInverstTeamNumber(String inverstTeamNumber)
	{
		this.inverstTeamNumber = inverstTeamNumber;
	}

	@Column(name="ACCIDENT_RESPONSIBLE")
	public String getAccidentResponsible()
	{
		return this.accidentResponsible;
	}

	public void setAccidentResponsible(String accidentResponsible)
	{
		this.accidentResponsible = accidentResponsible;
	}

	@Column(name="HANDLE_SUGGEST")
	public String getHandleSuggest()
	{
		return this.handleSuggest;
	}

	public void setHandleSuggest(String handleSuggest)
	{
		this.handleSuggest = handleSuggest;
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
	@Column(name="METHOD")
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
