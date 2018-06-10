package com.jshx.zfyj.entity;

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
@Table(name="LAW_BASIS")
public class LawBasis extends BaseModel
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
	 * 法律法规id
	 */
	private String lawId;
	
	/**
	 * 法律法规名称
	 */
	private String lawName;

	/**
	 * 法律法规条款项
	 */
	private String lawProvision;

	/**
	 * 法律法规内容
	 */
	private String lawContent;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 序号
	 */
	private Integer sort;

	
	public LawBasis(){
	}
	
	public LawBasis(String id, String lawName, String lawProvision,String lawContent){
this.id = id;

this.lawName = lawName;

this.lawProvision = lawProvision;

this.lawContent = lawContent;

}


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

	
	@Column(name="LAW_NAME")
	public String getLawName()
	{
		return this.lawName;
	}

	public void setLawName(String lawName)
	{
		this.lawName = lawName;
	}

	@Column(name="LAW_PROVISION")
	public String getLawProvision()
	{
		return this.lawProvision;
	}

	public void setLawProvision(String lawProvision)
	{
		this.lawProvision = lawProvision;
	}

	@Column(name="LAW_CONTENT")
	public String getLawContent()
	{
		return this.lawContent;
	}

	public void setLawContent(String lawContent)
	{
		this.lawContent = lawContent;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name="LAW_ID")
	public String getLawId() {
		return lawId;
	}

	public void setLawId(String lawId) {
		this.lawId = lawId;
	}
	@Column
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
