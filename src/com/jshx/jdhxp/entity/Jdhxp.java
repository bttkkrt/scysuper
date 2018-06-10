package com.jshx.jdhxp.entity;

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
@Table(name="JDHXP")
public class Jdhxp extends BaseModel
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
	 * 化学名
	 */
	private String hxname;

	/**
	 * 别名
	 */
	private String bname;

	/**
	 * CAS号
	 */
	private String casname;

	/**
	 * UN号
	 */
	private String unname;


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

	
	@Column(name="HXNAME")
	public String getHxname()
	{
		return this.hxname;
	}

	public void setHxname(String hxname)
	{
		this.hxname = hxname;
	}

	@Column(name="BNAME")
	public String getBname()
	{
		return this.bname;
	}

	public void setBname(String bname)
	{
		this.bname = bname;
	}

	@Column(name="CASNAME")
	public String getCasname()
	{
		return this.casname;
	}

	public void setCasname(String casname)
	{
		this.casname = casname;
	}

	@Column(name="UNNAME")
	public String getUnname()
	{
		return this.unname;
	}

	public void setUnname(String unname)
	{
		this.unname = unname;
	}

}
