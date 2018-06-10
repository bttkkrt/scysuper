package com.jshx.checkContent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.checkCategory.entity.CheckCategory;
import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CHECK_CONTENT")
public class CheckContent extends BaseModel
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
	 * 类别ID
	 */
	private CheckCategory category;

	/**
	 * 检查内容
	 */
	private String content;

	/**
	 * 排序序号
	 */
	private Long indexNum;

	/**
	 * 是否启用 0 启用 1 作废
	 */
	private String isusing;


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


	@Column(name="CONTENT")
	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Column(name="INDEX_NUM")
	public Long getIndexNum()
	{
		return this.indexNum;
	}

	public void setIndexNum(Long indexNum)
	{
		this.indexNum = indexNum;
	}

	@Column(name="ISUSING")
	public String getIsusing()
	{
		return this.isusing;
	}

	public void setIsusing(String isusing)
	{
		this.isusing = isusing;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CheckCategory.class)
    @JoinColumn(name="CATEGORY_ID")
	public CheckCategory getCategory()
	{
		return category;
	}

	public void setCategory(CheckCategory category)
	{
		this.category = category;
	}

}
