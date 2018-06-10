package com.jshx.aqscxzcf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="AQSCXZCF")
public class Aqscxzcf extends BaseModel
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
	 * 填报部门名称
	 */
	private String deptname;

	/**
	 * 统计月份
	 */
	private String yf;

	
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

	@Column(name="YF")
	public String getYf()
	{
		return this.yf;
	}

	public void setYf(String yf)
	{
		this.yf = yf;
	}
	
	@Column(name="DEPTNAME")
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

}
