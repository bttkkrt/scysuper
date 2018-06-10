package com.jshx.ybqyapbgba.entity;

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
@Table(name="ASS_REP_FIL")
public class AssRepFil extends BaseModel
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
	 * 评价机构id
	 */
	private String ratingAgenciesId;

	/**
	 * 评价机构名称
	 */
	private String ratingAgenciesName;

	/**
	 * 报告上传日期
	 */
	private Date submitDate;

	/**
	 * 报告完成日期
	 */
	private Date completeDate;

	/**
	 * 下次备案日期
	 */
	private Date nextRecordDate;

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
	 * 备案编号
	 */
	private String recordNum;

	/**
	 * 备案内容
	 */
	private String recordContent;

	/**
	 * 备案日期
	 */
	private Date recordDate;

	
	public AssRepFil(){
	}
	
	public AssRepFil(String id, String ratingAgenciesName, Date submitDate, Date completeDate, Date nextRecordDate, String areaId, String companyName, String recordNum, Date recordDate,String createUserID){
this.id = id;

this.ratingAgenciesName = ratingAgenciesName;

this.submitDate = submitDate;

this.completeDate = completeDate;

this.nextRecordDate = nextRecordDate;

this.areaId = areaId;

this.companyName = companyName;

this.recordNum = recordNum;

this.recordDate = recordDate;
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

	
	@Column(name="RATING_AGENCIES_ID")
	public String getRatingAgenciesId()
	{
		return this.ratingAgenciesId;
	}

	public void setRatingAgenciesId(String ratingAgenciesId)
	{
		this.ratingAgenciesId = ratingAgenciesId;
	}

	@Column(name="RATING_AGENCIES_NAME")
	public String getRatingAgenciesName()
	{
		return this.ratingAgenciesName;
	}

	public void setRatingAgenciesName(String ratingAgenciesName)
	{
		this.ratingAgenciesName = ratingAgenciesName;
	}

	@Column(name="SUBMIT_DATE")
	public Date getSubmitDate()
	{
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate)
	{
		this.submitDate = submitDate;
	}

	@Column(name="COMPLETE_DATE")
	public Date getCompleteDate()
	{
		return this.completeDate;
	}

	public void setCompleteDate(Date completeDate)
	{
		this.completeDate = completeDate;
	}

	@Column(name="NEXT_RECORD_DATE")
	public Date getNextRecordDate()
	{
		return this.nextRecordDate;
	}

	public void setNextRecordDate(Date nextRecordDate)
	{
		this.nextRecordDate = nextRecordDate;
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

	@Column(name="RECORD_NUM")
	public String getRecordNum()
	{
		return this.recordNum;
	}

	public void setRecordNum(String recordNum)
	{
		this.recordNum = recordNum;
	}

	@Column(name="RECORD_CONTENT")
	public String getRecordContent()
	{
		return this.recordContent;
	}

	public void setRecordContent(String recordContent)
	{
		this.recordContent = recordContent;
	}

	@Column(name="RECORD_DATE")
	public Date getRecordDate()
	{
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate)
	{
		this.recordDate = recordDate;
	}

}
