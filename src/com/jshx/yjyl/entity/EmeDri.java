package com.jshx.yjyl.entity;

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
@Table(name="EME_DRI")
public class EmeDri extends BaseModel
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
	 * 演练名称
	 */
	private String drillName;

	/**
	 * 演练类型
	 */
	private String drillType;

	/**
	 * 演练地点
	 */
	private String drillAddress;

	/**
	 * 演练目的
	 */
	private String drillPurpose;

	/**
	 * 演练形式
	 */
	private String drillForm;

	/**
	 * 演练内容
	 */
	private String drillContent;

	/**
	 * 演练开始时间
	 */
	private Date drillStartTime;

	/**
	 * 演练结束时间
	 */
	private Date drillStopTime;

	/**
	 * 主办单位
	 */
	private String organizer;

	/**
	 * 演练单位
	 */
	private String drillCompany;

	/**
	 * 参演人数
	 */
	private String drillPersonNum;

	/**
	 * 评估总结
	 */
	private String evaluateSummary;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public EmeDri(){
	}
	
	public EmeDri(String id, String areaName, String companyName, String drillName, String drillType){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.drillName = drillName;

this.drillType = drillType;
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

	@Column(name="DRILL_NAME")
	public String getDrillName()
	{
		return this.drillName;
	}

	public void setDrillName(String drillName)
	{
		this.drillName = drillName;
	}

	@Column(name="DRILL_TYPE")
	public String getDrillType()
	{
		return this.drillType;
	}

	public void setDrillType(String drillType)
	{
		this.drillType = drillType;
	}

	@Column(name="DRILL_ADDRESS")
	public String getDrillAddress()
	{
		return this.drillAddress;
	}

	public void setDrillAddress(String drillAddress)
	{
		this.drillAddress = drillAddress;
	}

	@Column(name="DRILL_PURPOSE")
	public String getDrillPurpose()
	{
		return this.drillPurpose;
	}

	public void setDrillPurpose(String drillPurpose)
	{
		this.drillPurpose = drillPurpose;
	}

	@Column(name="DRILL_FORM")
	public String getDrillForm()
	{
		return this.drillForm;
	}

	public void setDrillForm(String drillForm)
	{
		this.drillForm = drillForm;
	}

	@Column(name="DRILL_CONTENT")
	public String getDrillContent()
	{
		return this.drillContent;
	}

	public void setDrillContent(String drillContent)
	{
		this.drillContent = drillContent;
	}

	@Column(name="DRILL_START_TIME")
	public Date getDrillStartTime()
	{
		return this.drillStartTime;
	}

	public void setDrillStartTime(Date drillStartTime)
	{
		this.drillStartTime = drillStartTime;
	}

	@Column(name="DRILL_STOP_TIME")
	public Date getDrillStopTime()
	{
		return this.drillStopTime;
	}

	public void setDrillStopTime(Date drillStopTime)
	{
		this.drillStopTime = drillStopTime;
	}

	@Column(name="ORGANIZER")
	public String getOrganizer()
	{
		return this.organizer;
	}

	public void setOrganizer(String organizer)
	{
		this.organizer = organizer;
	}

	@Column(name="DRILL_COMPANY")
	public String getDrillCompany()
	{
		return this.drillCompany;
	}

	public void setDrillCompany(String drillCompany)
	{
		this.drillCompany = drillCompany;
	}

	@Column(name="DRILL_PERSON_NUM")
	public String getDrillPersonNum()
	{
		return this.drillPersonNum;
	}

	public void setDrillPersonNum(String drillPersonNum)
	{
		this.drillPersonNum = drillPersonNum;
	}

	@Column(name="EVALUATE_SUMMARY")
	public String getEvaluateSummary()
	{
		return this.evaluateSummary;
	}

	public void setEvaluateSummary(String evaluateSummary)
	{
		this.evaluateSummary = evaluateSummary;
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
