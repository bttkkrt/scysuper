package com.jshx.zlqxzgzls.entity;

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
@Table(name="ORDER_DEADLINE_BOOK")
public class OrderDeadlineBook extends BaseModel
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
	 * 修改项
	 */
	private String changeItem;

	/**
	 * 整改时间
	 */
	private Date startTime;

	/**
	 * 执法人员
	 */
	private String lawOfficer;
	
	/**
	 * 执法人员
	 */
	private String lawOfficerName;
	
	private String lawOfficerName1;
	
	@Column(name="LAW_OFFICER_NAME")
	public String getLawOfficerName()
	{
		return this.lawOfficerName;
	}

	public void setLawOfficerName(String lawOfficerName)
	{
		this.lawOfficerName = lawOfficerName;
	}
	

	public OrderDeadlineBook(){
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

	@Column(name="CHANGE_ITEM")
	public String getChangeItem()
	{
		return this.changeItem;
	}

	public void setChangeItem(String changeItem)
	{
		this.changeItem = changeItem;
	}

	@Column(name="START_TIME")
	public Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	@Column(name="LAW_OFFICER")
	public String getLawOfficer()
	{
		return this.lawOfficer;
	}

	public void setLawOfficer(String lawOfficer)
	{
		this.lawOfficer = lawOfficer;
	}
	@Column
	public String getLawOfficerName1() {
		return lawOfficerName1;
	}

	public void setLawOfficerName1(String lawOfficerName1) {
		this.lawOfficerName1 = lawOfficerName1;
	}

}
