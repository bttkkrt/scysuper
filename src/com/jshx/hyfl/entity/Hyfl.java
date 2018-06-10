package com.jshx.hyfl.entity;

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
@Table(name="HYFL")
public class Hyfl extends BaseModel
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
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String hyname;

	/**
	 * 描述
	 */
	private String hytext;


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

	
	@Column(name="CODE")
	public String getCode()
	{
		return this.code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	@Column(name="HYNAME")
	public String getHyname()
	{
		return this.hyname;
	}

	public void setHyname(String hyname)
	{
		this.hyname = hyname;
	}

	@Column(name="HYTEXT")
	public String getHytext()
	{
		return this.hytext;
	}

	public void setHytext(String hytext)
	{
		this.hytext = hytext;
	}

}
