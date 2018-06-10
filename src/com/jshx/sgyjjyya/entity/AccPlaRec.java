package com.jshx.sgyjjyya.entity;

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
@Table(name="ACC_PLA_REC")
public class AccPlaRec extends BaseModel
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
	 * 备案编号
	 */
	private String recordNum;

	/**
	 * 备案机构
	 */
	private String recordAgency;

	/**
	 * 备案日期
	 */
	private Date recordDate;

	/**
	 * 备案内容
	 */
	private String recordContent;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public AccPlaRec(){
	}
	
	public AccPlaRec(String id, String areaId, String companyName, String recordNum, String recordAgency, Date recordDate,String createUserID){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.recordNum = recordNum;

this.recordAgency = recordAgency;

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

	@Column(name="RECORD_AGENCY")
	public String getRecordAgency()
	{
		return this.recordAgency;
	}

	public void setRecordAgency(String recordAgency)
	{
		this.recordAgency = recordAgency;
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

	@Column(name="RECORD_CONTENT")
	public String getRecordContent()
	{
		return this.recordContent;
	}

	public void setRecordContent(String recordContent)
	{
		this.recordContent = recordContent;
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
