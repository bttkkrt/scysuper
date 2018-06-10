package com.jshx.ndzybfzjf.entity;

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
@Table(name="OCC_DIS_PRE")
public class OccDisPre extends BaseModel
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
	 * 年度
	 */
	private String jshxYear;

	/**
	 * 用途
	 */
	private String jshxUse;

	/**
	 * 工作内容
	 */
	private String workContent;

	/**
	 * 经费
	 */
	private String attachment;

	/**
	 * 备注
	 */
	private String remark;

	
	public OccDisPre(){
	}
	
	public OccDisPre(String id, String areaName, String companyName, String jshxYear, String jshxUse, String attachment){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.jshxYear = jshxYear;

this.jshxUse = jshxUse;

this.attachment = attachment;
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

	@Column(name="JSHX_YEAR")
	public String getJshxYear()
	{
		return this.jshxYear;
	}

	public void setJshxYear(String jshxYear)
	{
		this.jshxYear = jshxYear;
	}

	@Column(name="JSHX_USE")
	public String getJshxUse()
	{
		return this.jshxUse;
	}

	public void setJshxUse(String jshxUse)
	{
		this.jshxUse = jshxUse;
	}

	@Column(name="WORK_CONTENT")
	public String getWorkContent()
	{
		return this.workContent;
	}

	public void setWorkContent(String workContent)
	{
		this.workContent = workContent;
	}

	@Column(name="ATTACHMENT")
	public String getAttachment()
	{
		return this.attachment;
	}

	public void setAttachment(String attachment)
	{
		this.attachment = attachment;
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

}
