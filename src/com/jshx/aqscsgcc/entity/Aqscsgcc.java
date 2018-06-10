package com.jshx.aqscsgcc.entity;

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
@Table(name="AQSCSGCC")
public class Aqscsgcc extends BaseModel
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
	 * 填报部门
	 */
	private String deptname;

	/**
	 * 月份
	 */
	private String yf;

	/**
	 * 负责人
	 */
	private String fzr;

	/**
	 * 填表人
	 */
	private String tbr;


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

	@Column(name="DEPTNAME")
	public String getDeptname()
	{
		return this.deptname;
	}

	public void setDeptname(String deptname)
	{
		this.deptname = deptname;
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

	@Column(name="FZR")
	public String getFzr()
	{
		return this.fzr;
	}

	public void setFzr(String fzr)
	{
		this.fzr = fzr;
	}

	@Column(name="TBR")
	public String getTbr()
	{
		return this.tbr;
	}

	public void setTbr(String tbr)
	{
		this.tbr = tbr;
	}

}
