package com.jshx.regionCode.entity;

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
@Table(name="REGION_CODE")
public class RegionCode extends BaseModel
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
	 * 单位名称
	 */
	private String regionName;

	/**
	 * 代码
	 */
	private String regionCode;

	/**
	 * 部门code
	 */
	private String deptCode;


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

	
	@Column(name="REGION_NAME")
	public String getRegionName()
	{
		return this.regionName;
	}

	public void setRegionName(String regionName)
	{
		this.regionName = regionName;
	}

	@Column(name="REGION_CODE")
	public String getRegionCode()
	{
		return this.regionCode;
	}

	public void setRegionCode(String regionCode)
	{
		this.regionCode = regionCode;
	}

	@Column(name="DEPT_CODE")
	public String getDeptCode()
	{
		return this.deptCode;
	}

	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}

}
