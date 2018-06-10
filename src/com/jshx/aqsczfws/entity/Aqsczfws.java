package com.jshx.aqsczfws.entity;

import java.util.Date;

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
@Table(name="AQSCZFWS")
public class Aqsczfws extends BaseModel
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
	 * 所在镇
	 */
	private String deptname;

	/**
	 * 填表人
	 */
	private String tbr;

	private Date tbrq;

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

	@Column(name="TBR")
	public String getTbr()
	{
		return this.tbr;
	}

	public void setTbr(String tbr)
	{
		this.tbr = tbr;
	}
	@Column(name="TBRQ")
	public Date getTbrq() {
		return tbrq;
	}

	public void setTbrq(Date tbrq) {
		this.tbrq = tbrq;
	}

}
