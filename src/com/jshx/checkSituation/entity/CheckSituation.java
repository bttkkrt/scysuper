package com.jshx.checkSituation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CHECK_SITUATION")
public class CheckSituation extends BaseModel
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
	 * 图片关联ID
	 */
	private String linkid;

	/**
	 * 描述
	 */
	private String discreption;

	/**
	 * 基本表ID
	 */
	private CheckBasic basic;

	/**
	 * 图片附件路径
	 * 用于封装传值
	 */
	private List<String> pathList;
	
	/**
	 * 检查标记
	 * 0 只用作现场检查记录表中检查情况
	 * 1 在责令限期整改指令书中做整改问题展示
	 */
	private Integer checkFlag;

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

	
	@Column(name="LINKID")
	public String getLinkid()
	{
		return this.linkid;
	}

	public void setLinkid(String linkid)
	{
		this.linkid = linkid;
	}

	@Column(name="DISCREPTION")
	public String getDiscreption()
	{
		return this.discreption;
	}

	public void setDiscreption(String discreption)
	{
		this.discreption = discreption;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CheckBasic.class)
    @JoinColumn(name="BASIC_ID")
	public CheckBasic getBasic()
	{
		return basic;
	}

	public void setBasic(CheckBasic basic)
	{
		this.basic = basic;
	}

	@Transient
	public List<String> getPathList()
	{
		return pathList;
	}

	public void setPathList(List<String> pathList)
	{
		this.pathList = pathList;
	}

	@Column
	public Integer getCheckFlag()
	{
		return checkFlag;
	}

	public void setCheckFlag(Integer checkFlag)
	{
		this.checkFlag = checkFlag;
	}
	
}
