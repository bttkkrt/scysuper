package com.jshx.rectifyOpinion.entity;

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
@Table(name="RECTIFY_OPINION")
public class RectifyOpinion extends BaseModel
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
	 * 意见
	 */
	private String opinion;

	/**
	 * 基本表ID
	 */
	private CheckBasic basic;
	
	/**
	 * 图片附件路径
	 * 用于封装传值
	 */
	private List<String> pathList;


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

	@Column(name="OPINION")
	public String getOpinion()
	{
		return this.opinion;
	}

	public void setOpinion(String opinion)
	{
		this.opinion = opinion;
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
}
