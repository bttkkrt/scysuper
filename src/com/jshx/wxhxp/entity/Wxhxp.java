package com.jshx.wxhxp.entity;

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
@Table(name="WXHXP")
public class Wxhxp extends BaseModel
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
	 * 名称
	 */
	private String whpname;

	/**
	 * 别名
	 */
	private String bname;

	/**
	 * UN号
	 */
	private String unnum;

	/**
	 * 危险货物编号
	 */
	private String whpnum;


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

	
	@Column(name="WHPNAME")
	public String getWhpname()
	{
		return this.whpname;
	}

	public void setWhpname(String whpname)
	{
		this.whpname = whpname;
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

	@Column(name="UNNUM")
	public String getUnnum()
	{
		return this.unnum;
	}

	public void setUnnum(String unnum)
	{
		this.unnum = unnum;
	}

	@Column(name="WHPNUM")
	public String getWhpnum()
	{
		return this.whpnum;
	}

	public void setWhpnum(String whpnum)
	{
		this.whpnum = whpnum;
	}

}
