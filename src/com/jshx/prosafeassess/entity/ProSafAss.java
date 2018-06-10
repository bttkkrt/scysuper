package com.jshx.prosafeassess.entity;

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
@Table(name="PRO_SAF_ASS")
public class ProSafAss extends BaseModel
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
	 * 评价机构id
	 */
	private String ratingAgenciesId;

	/**
	 * 评价机构名称
	 */
	private String ratingAgenciesName;

	/**
	 * 项目内容
	 */
	private String projectContent;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public ProSafAss(){
	}
	
	public ProSafAss(String id, String areaName, String companyName, String ratingAgenciesName,String createUserID){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.ratingAgenciesName = ratingAgenciesName;
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

	@Column(name="PROJECT_CONTENT")
	public String getProjectContent()
	{
		return this.projectContent;
	}

	public void setProjectContent(String projectContent)
	{
		this.projectContent = projectContent;
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

}
