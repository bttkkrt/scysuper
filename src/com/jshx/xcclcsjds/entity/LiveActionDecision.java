package com.jshx.xcclcsjds.entity;

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
@Table(name="LIVE_ACTION_DECISION")
public class LiveActionDecision extends BaseModel
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
	 * 违法违规行为和事故隐患
	 */
	private String illegalAccident;

	/**
	 * 执法依据
	 */
	private String lawBasic;
	
	private String lawName;
	
	/**
	 * 处理决定
	 */
	private String dealDecision;

	/**
	 * 执法人员
	 */
	private String lawOfficer;
	
	/**
	 * 执法人员
	 */
	private String lawOfficerName;
	
	private String lawOfficerName1;
	

	public LiveActionDecision(){
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

	@Column(name="ILLEGAL_ACCIDENT")
	public String getIllegalAccident()
	{
		return this.illegalAccident;
	}

	public void setIllegalAccident(String illegalAccident)
	{
		this.illegalAccident = illegalAccident;
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

	@Column(name="DEAL_DECISION")
	public String getDealDecision()
	{
		return this.dealDecision;
	}

	public void setDealDecision(String dealDecision)
	{
		this.dealDecision = dealDecision;
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
	@Column(name="LAW_NAME")
	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	@Column(name="LAW_OFFICER_NAME")
	public String getLawOfficerName()
	{
		return this.lawOfficerName;
	}

	public void setLawOfficerName(String lawOfficerName)
	{
		this.lawOfficerName = lawOfficerName;
	}


	@Column
	public String getLawOfficerName1() {
		return lawOfficerName1;
	}



	public void setLawOfficerName1(String lawOfficerName1) {
		this.lawOfficerName1 = lawOfficerName1;
	}
}
