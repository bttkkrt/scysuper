package com.jshx.hmd.entity;

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
@Table(name="HMD")
public class Hmd extends BaseModel
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
	 * 违法违规行为
	 */
	private String illegalActivity;

	/**
	 * 管理期限
	 */
	private String manageTerm;

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
	 * 发生时间
	 */
	private String startTime;

	
	public Hmd(){
	}
	
	public Hmd(String id, String areaId, String companyName, String startTime,String illegalActivity){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.startTime = startTime;

this.illegalActivity= illegalActivity;
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

	
	@Column(name="ILLEGAL_ACTIVITY")
	public String getIllegalActivity()
	{
		return this.illegalActivity;
	}

	public void setIllegalActivity(String illegalActivity)
	{
		this.illegalActivity = illegalActivity;
	}

	@Column(name="MANAGE_TERM")
	public String getManageTerm()
	{
		return this.manageTerm;
	}

	public void setManageTerm(String manageTerm)
	{
		this.manageTerm = manageTerm;
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

	@Column(name="START_TIME")
	public String getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

}
