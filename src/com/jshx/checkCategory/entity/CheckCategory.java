package com.jshx.checkCategory.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.jshx.checkContent.entity.CheckContent;
import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CHECK_CATEGORY")
public class CheckCategory extends BaseModel
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
	 * 栏目内容
	 */
	private String content;

	/**
	 * 栏目排序号
	 */
	private Long indexNum;

	/**
	 * 是否启用 0 启用 1 作废
	 */
	private String isusing;
	
	/**
	 * 检查内容
	 */
	private List<CheckContent> contents;


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

	@OneToMany(mappedBy="category", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	@OrderBy(value = "indexNum")
	@Where(clause="delFlag=0")
	public List<CheckContent> getContents()
	{
		return contents;
	}

	public void setContents(List<CheckContent> contents)
	{
		this.contents = contents;
	}

}
