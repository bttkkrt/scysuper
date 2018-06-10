package com.jshx.zywsaqglzd.entity;

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
@Table(name="OCC_HEA_MAN")
public class OccHeaMan extends BaseModel
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
	 * 关联id
	 */
	private String linkid;

	/**
	 * 制度名称
	 */
	private String systemName;

	/**
	 * 制度类型
	 */
	private String systemType;

	/**
	 * 上传时间
	 */
	private Date uploadTime;

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

	
	public OccHeaMan(){
	}
	
	public OccHeaMan(String id, String systemName, String systemType, String areaName, String companyName){
this.id = id;

this.systemName = systemName;

this.systemType = systemType;

this.areaName = areaName;

this.companyName = companyName;
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

	
	@Column(name="LINKID")
	public String getLinkid()
	{
		return this.linkid;
	}

	public void setLinkid(String linkid)
	{
		this.linkid = linkid;
	}

	@Column(name="SYSTEM_NAME")
	public String getSystemName()
	{
		return this.systemName;
	}

	public void setSystemName(String systemName)
	{
		this.systemName = systemName;
	}

	@Column(name="SYSTEM_TYPE")
	public String getSystemType()
	{
		return this.systemType;
	}

	public void setSystemType(String systemType)
	{
		this.systemType = systemType;
	}

	@Column(name="UPLOAD_TIME")
	public Date getUploadTime()
	{
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime)
	{
		this.uploadTime = uploadTime;
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

}
