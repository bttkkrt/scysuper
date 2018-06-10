package com.jshx.regulationsLevel.entity;

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
@Table(name="REGULATIONS_LEVEL")
public class RegulationsLevel extends BaseModel
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
	 * 级别编码
	 */
	private String levelCode;

	/**
	 * 级别名称
	 */
	private String levelName;

	/**
	 * 上级级别Id
	 */
	private String uplevelId;

	/**
	 * 全路径
	 */
	private String fullpath;

	/**
	 * 序号
	 */
	private String numbercard;


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

	
	@Column(name="LEVEL_CODE")
	public String getLevelCode()
	{
		return this.levelCode;
	}

	public void setLevelCode(String levelCode)
	{
		this.levelCode = levelCode;
	}

	@Column(name="LEVEL_NAME")
	public String getLevelName()
	{
		return this.levelName;
	}

	public void setLevelName(String levelName)
	{
		this.levelName = levelName;
	}

	@Column(name="UPLEVEL_ID")
	public String getUplevelId()
	{
		return this.uplevelId;
	}

	public void setUplevelId(String uplevelId)
	{
		this.uplevelId = uplevelId;
	}

	@Column(name="FULLPATH")
	public String getFullpath()
	{
		return this.fullpath;
	}

	public void setFullpath(String fullpath)
	{
		this.fullpath = fullpath;
	}

	@Column(name="NUMBERCARD")
	public String getNumbercard()
	{
		return this.numbercard;
	}

	public void setNumbercard(String numbercard)
	{
		this.numbercard = numbercard;
	}

}
