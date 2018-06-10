package com.jshx.zfyjlb.entity;

import java.util.Date;

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
@Table(name="LAW_BASE")
public class LawBase extends BaseModel
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
	 * 法律法规名称
	 */
	private String lawName;

	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 索引号
	 */
	private String syh;
	
	/**
	 * 发布机构
	 */
	private String fbjg;
	
	/**
	 * 生成日期
	 */
	private Date createDay;
	
	/**
	 * 发布日期
	 */
	private Date pubDay;
	
	/**
	 * 内容概述
	 */
	private String gs;
	
	public LawBase(){
	}
	
	public LawBase(String id, String lawName){
this.id = id;

this.lawName = lawName;

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

	@Column
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column
	public String getSyh() {
		return syh;
	}

	public void setSyh(String syh) {
		this.syh = syh;
	}
	@Column
	public String getFbjg() {
		return fbjg;
	}

	public void setFbjg(String fbjg) {
		this.fbjg = fbjg;
	}
	@Column
	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}
	@Column
	public Date getPubDay() {
		return pubDay;
	}

	public void setPubDay(Date pubDay) {
		this.pubDay = pubDay;
	}
	@Column
	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}

}
