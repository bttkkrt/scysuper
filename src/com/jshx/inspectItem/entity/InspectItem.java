package com.jshx.inspectItem.entity;

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
@Table(name="INSPECT_ITEM")
public class InspectItem extends BaseModel
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
	 * 企业标识
	 */
	private String companyFlag;

	/**
	 * 检测类型标识
	 */
	private String inspectType;

	/**
	 * 巡检项
	 */
	private String item;

	/**
	 * 巡检要求
	 */
	private String requirement;


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

	
	@Column(name="COMPANY_FLAG")
	public String getCompanyFlag()
	{
		return this.companyFlag;
	}

	public void setCompanyFlag(String companyFlag)
	{
		this.companyFlag = companyFlag;
	}

	@Column(name="INSPECT_TYPE")
	public String getInspectType()
	{
		return this.inspectType;
	}

	public void setInspectType(String inspectType)
	{
		this.inspectType = inspectType;
	}

	@Column(name="ITEM")
	public String getItem()
	{
		return this.item;
	}

	public void setItem(String item)
	{
		this.item = item;
	}

	@Column(name="REQUIREMENT")
	public String getRequirement()
	{
		return this.requirement;
	}

	public void setRequirement(String requirement)
	{
		this.requirement = requirement;
	}

}
