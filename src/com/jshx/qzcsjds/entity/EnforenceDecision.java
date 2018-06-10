package com.jshx.qzcsjds.entity;

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
@Table(name="ENFORENCE_DECISION")
public class EnforenceDecision extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 问题
	 */
	private String problem;

	/**
	 * 执法依据
	 */
	private String lawBasic;
	
	private String lawName;

	/**
	 * 措施
	 */
	private String method;

	
	public EnforenceDecision(){
	}
	
	public EnforenceDecision(String id, String relatedId){
this.id = id;

this.relatedId = relatedId;
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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="PROBLEM")
	public String getProblem()
	{
		return this.problem;
	}

	public void setProblem(String problem)
	{
		this.problem = problem;
	}

	@Column(name="LAW_BASIC")
	public String getLawBasic()
	{
		return this.lawBasic;
	}

	public void setLawBasic(String lawBasic)
	{
		this.lawBasic = lawBasic;
	}

	@Column(name="METHOD")
	public String getMethod()
	{
		return this.method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	@Column(name="LAW_NAME")
	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

}
