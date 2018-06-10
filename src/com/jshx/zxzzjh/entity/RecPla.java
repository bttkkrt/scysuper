package com.jshx.zxzzjh.entity;

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
@Table(name="REC_PLA")
public class RecPla extends BaseModel
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
	 * 工作目标
	 */
	private String workGoal;

	/**
	 * 工作内容
	 */
	private String workContent;

	/**
	 * 具体措施
	 */
	private String workMeasure;

	/**
	 * 实施步骤
	 */
	private String workStep;

	/**
	 * 工作要求
	 */
	private String workClaim;

	/**
	 * 整治时间(开始)
	 */
	private Date workTimeStart;
	
	/**
	 * 整治时间(结束)
	 */
	private Date workTimeEnd;
	
	/**
	 * 计划名称
	 */
	private String planName;

	/**
	 * 计划状态 0：未完成，1：已完成
	 */
	private String status;
	public RecPla(){
	}
	
	public RecPla(String id, String workGoal, Date workTimeStart, Date workTimeEnd,String planName,String status,String createUserID){
this.id = id;

this.workGoal = workGoal;

this.workTimeStart = workTimeStart;
this.workTimeEnd=workTimeEnd;
this.planName=planName;
this.status=status;
this.createUserID=createUserID;
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

	
	@Column(name="WORK_GOAL")
	public String getWorkGoal()
	{
		return this.workGoal;
	}

	public void setWorkGoal(String workGoal)
	{
		this.workGoal = workGoal;
	}

	@Column(name="WORK_CONTENT")
	public String getWorkContent()
	{
		return this.workContent;
	}

	public void setWorkContent(String workContent)
	{
		this.workContent = workContent;
	}

	@Column(name="WORK_MEASURE")
	public String getWorkMeasure()
	{
		return this.workMeasure;
	}

	public void setWorkMeasure(String workMeasure)
	{
		this.workMeasure = workMeasure;
	}

	@Column(name="WORK_STEP")
	public String getWorkStep()
	{
		return this.workStep;
	}

	public void setWorkStep(String workStep)
	{
		this.workStep = workStep;
	}

	@Column(name="WORK_CLAIM")
	public String getWorkClaim()
	{
		return this.workClaim;
	}

	public void setWorkClaim(String workClaim)
	{
		this.workClaim = workClaim;
	}

	@Column(name="WORK_TIME_START")
	public Date getWorkTimeStart()
	{
		return this.workTimeStart;
	}

	public void setWorkTimeStart(Date workTimeStart)
	{
		this.workTimeStart = workTimeStart;
	}
	
	@Column(name="WORK_TIME_END")
	public Date getWorkTimeEnd()
	{
		return this.workTimeEnd;
	}

	public void setWorkTimeEnd(Date workTimeEnd)
	{
		this.workTimeEnd = workTimeEnd;
	}
	@Column(name="PLAN_NAME")
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
