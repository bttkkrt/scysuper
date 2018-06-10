package com.jshx.gridManager.entity;

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
@Table(name="GRID_MANAGER")
public class GridManager extends BaseModel
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
	 * 上级责任人名称
	 */
	private String upUsername;

	/**
	 * 上级责任人id
	 */
	private String upUserid;

	/**
	 * 下级责任人名称
	 */
	private String downUsername;

	/**
	 * 下级责任人id
	 */
	private String downUserid;

	/**
	 * 类型
	 */
	private String gridType;


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

	
	@Column(name="UP_USERNAME")
	public String getUpUsername()
	{
		return this.upUsername;
	}

	public void setUpUsername(String upUsername)
	{
		this.upUsername = upUsername;
	}

	@Column(name="UP_USERID")
	public String getUpUserid()
	{
		return this.upUserid;
	}

	public void setUpUserid(String upUserid)
	{
		this.upUserid = upUserid;
	}

	@Column(name="DOWN_USERNAME")
	public String getDownUsername()
	{
		return this.downUsername;
	}

	public void setDownUsername(String downUsername)
	{
		this.downUsername = downUsername;
	}

	@Column(name="DOWN_USERID")
	public String getDownUserid()
	{
		return this.downUserid;
	}

	public void setDownUserid(String downUserid)
	{
		this.downUserid = downUserid;
	}

	@Column(name="GRID_TYPE")
	public String getGridType()
	{
		return this.gridType;
	}

	public void setGridType(String gridType)
	{
		this.gridType = gridType;
	}

}
