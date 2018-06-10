package com.jshx.zdwxyczgc.entity;

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
@Table(name="COM_OPE_RUL")
public class ComOpeRul extends BaseModel
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
	 * 重点危险源级别
	 */
	private String dangerLevel;

	/**
	 * 重点危险源类别
	 */
	private String dangerType;

	/**
	 * 操作规程名称
	 */
	private String ruleName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 重点危险源名称
	 */
	private String dangerName;

	
	public ComOpeRul(){
	}
	
	public ComOpeRul(String id, String areaName, String companyName, String dangerName, String ruleName){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.dangerName = dangerName;

this.ruleName = ruleName;
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

	@Column(name="DANGER_LEVEL")
	public String getDangerLevel()
	{
		return this.dangerLevel;
	}

	public void setDangerLevel(String dangerLevel)
	{
		this.dangerLevel = dangerLevel;
	}

	@Column(name="DANGER_TYPE")
	public String getDangerType()
	{
		return this.dangerType;
	}

	public void setDangerType(String dangerType)
	{
		this.dangerType = dangerType;
	}

	@Column(name="RULE_NAME")
	public String getRuleName()
	{
		return this.ruleName;
	}

	public void setRuleName(String ruleName)
	{
		this.ruleName = ruleName;
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
	
	@Column(name="DANGER_NAME")
	public String getDangerName()
	{
		return this.dangerName;
	}

	public void setDangerName(String dangerName)
	{
		this.dangerName = dangerName;
	}


}
