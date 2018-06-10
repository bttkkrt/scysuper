package com.jshx.yjya.entity;

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
@Table(name="EME_PLA")
public class EmePla extends BaseModel
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
	 * 预案类别
	 */
	private String planType;

	/**
	 * 预案级别
	 */
	private String planLevel;

	/**
	 * 预案摘要
	 */
	private String planSummary;

	/**
	 * 编制单位
	 */
	private String drawUpInstitution;

	/**
	 * 编制人
	 */
	private String drawUpPerson;

	/**
	 * 发布日期
	 */
	private Date publishDate;

	/**
	 * 发布文号
	 */
	private String publishNumber;

	/**
	 * 发布单位
	 */
	private String publishInstitution;

	/**
	 * 签发人
	 */
	private String publisher;

	/**
	 * 预案备案时间
	 */
	private Date planFilingTime;

	/**
	 * 预案备案部门
	 */
	private String planFilingDepart;

	/**
	 * 预案备案编号
	 */
	private String planFilingNumber;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;

	/**
	 * 企业或者政府
	 */
	private String kind;

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
	 * 预案名称
	 */
	private String planName;
	
	/**
	 * 试用领域
	 */
	private String filed;

	
	/**
	 * type=0表示企业
	 */
	private String type;
	
	public EmePla(){
	}
	
	public EmePla(String id, String planType, String planLevel, String planFilingNumber, String areaName, String companyName, String planName,String createUserID){
this.id = id;

this.planType = planType;

this.planLevel = planLevel;

this.planFilingNumber = planFilingNumber;

this.areaName = areaName;

this.companyName = companyName;

this.planName = planName;
this.createUserID = createUserID;
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

	
	@Column(name="PLAN_TYPE")
	public String getPlanType()
	{
		return this.planType;
	}

	public void setPlanType(String planType)
	{
		this.planType = planType;
	}

	@Column(name="PLAN_LEVEL")
	public String getPlanLevel()
	{
		return this.planLevel;
	}

	public void setPlanLevel(String planLevel)
	{
		this.planLevel = planLevel;
	}

	@Column(name="PLAN_SUMMARY")
	public String getPlanSummary()
	{
		return this.planSummary;
	}

	public void setPlanSummary(String planSummary)
	{
		this.planSummary = planSummary;
	}

	@Column(name="DRAW_UP_INSTITUTION")
	public String getDrawUpInstitution()
	{
		return this.drawUpInstitution;
	}

	public void setDrawUpInstitution(String drawUpInstitution)
	{
		this.drawUpInstitution = drawUpInstitution;
	}

	@Column(name="DRAW_UP_PERSON")
	public String getDrawUpPerson()
	{
		return this.drawUpPerson;
	}

	public void setDrawUpPerson(String drawUpPerson)
	{
		this.drawUpPerson = drawUpPerson;
	}

	@Column(name="PUBLISH_DATE")
	public Date getPublishDate()
	{
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}

	@Column(name="PUBLISH_NUMBER")
	public String getPublishNumber()
	{
		return this.publishNumber;
	}

	public void setPublishNumber(String publishNumber)
	{
		this.publishNumber = publishNumber;
	}

	@Column(name="PUBLISH_INSTITUTION")
	public String getPublishInstitution()
	{
		return this.publishInstitution;
	}

	public void setPublishInstitution(String publishInstitution)
	{
		this.publishInstitution = publishInstitution;
	}

	@Column(name="PUBLISHER")
	public String getPublisher()
	{
		return this.publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	@Column(name="PLAN_FILING_TIME")
	public Date getPlanFilingTime()
	{
		return this.planFilingTime;
	}

	public void setPlanFilingTime(Date planFilingTime)
	{
		this.planFilingTime = planFilingTime;
	}

	@Column(name="PLAN_FILING_DEPART")
	public String getPlanFilingDepart()
	{
		return this.planFilingDepart;
	}

	public void setPlanFilingDepart(String planFilingDepart)
	{
		this.planFilingDepart = planFilingDepart;
	}

	@Column(name="PLAN_FILING_NUMBER")
	public String getPlanFilingNumber()
	{
		return this.planFilingNumber;
	}

	public void setPlanFilingNumber(String planFilingNumber)
	{
		this.planFilingNumber = planFilingNumber;
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

	@Column(name="KIND")
	public String getKind()
	{
		return this.kind;
	}

	public void setKind(String kind)
	{
		this.kind = kind;
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

	@Column(name="PLAN_NAME")
	public String getPlanName()
	{
		return this.planName;
	}

	public void setPlanName(String planName)
	{
		this.planName = planName;
	}
	@Column(name="FIELD")
	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
