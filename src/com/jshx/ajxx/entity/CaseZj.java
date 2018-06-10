package com.jshx.ajxx.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CASE_ZJ")
public class CaseZj extends BaseModel
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
	 * 案件id
	 */
	private String caseId;

	/**
	 * 证据内容
	 */
	private String zjContent;

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

	@Column(name="CASE_ID")
	public String getCaseId()
	{
		return this.caseId;
	}

	public void setCaseId(String caseId)
	{
		this.caseId = caseId;
	}

	@Column(name="ZJ_CONTENT")
	public String getZjContent() {
		return zjContent;
	}

	public void setZjContent(String zjContent) {
		this.zjContent = zjContent;
	}
}
