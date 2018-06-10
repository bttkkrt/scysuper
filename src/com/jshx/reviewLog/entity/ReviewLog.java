package com.jshx.reviewLog.entity;

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
@Table(name="REVIEW_LOG")
public class ReviewLog extends BaseModel
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
	 * 审核项目id
	 */
	private String itemId;

	/**
	 * 审核项目类型
	 */
	private String itemType;

	/**
	 * 审核状态
	 */
	private String state;

	/**
	 * 审核岗位标记
	 */
	private String dutyFlag;

	/**
	 * 审核人Id
	 */
	private String userId;

	/**
	 * 审核人名称
	 */
	private String userName;

	/**
	 * 审核人部门编码
	 */
	private String userDeptCode;

	/**
	 * 审核开始时间
	 */
	private Date startTime;

	/**
	 * 审核结束时间
	 */
	private Date endTime;

	/**
	 * 审核记录
	 */
	private String record;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 完结审核任务层级
	 */
	private String finishDutyFlag;


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

	
	@Column(name="ITEM_ID")
	public String getItemId()
	{
		return this.itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	@Column(name="ITEM_TYPE")
	public String getItemType()
	{
		return this.itemType;
	}

	public void setItemType(String itemType)
	{
		this.itemType = itemType;
	}

	@Column(name="STATE")
	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Column(name="DUTY_FLAG")
	public String getDutyFlag()
	{
		return this.dutyFlag;
	}

	public void setDutyFlag(String dutyFlag)
	{
		this.dutyFlag = dutyFlag;
	}

	@Column(name="USER_ID")
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name="USER_DEPT_CODE")
	public String getUserDeptCode()
	{
		return this.userDeptCode;
	}

	public void setUserDeptCode(String userDeptCode)
	{
		this.userDeptCode = userDeptCode;
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

	@Column(name="END_TIME")
	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	@Column(name="RECORD")
	public String getRecord()
	{
		return this.record;
	}

	public void setRecord(String record)
	{
		this.record = record;
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
	@Column(name="FINISHDUTYFLAG")
	public String getFinishDutyFlag() {
		return finishDutyFlag;
	}

	public void setFinishDutyFlag(String finishDutyFlag) {
		this.finishDutyFlag = finishDutyFlag;
	}

}
