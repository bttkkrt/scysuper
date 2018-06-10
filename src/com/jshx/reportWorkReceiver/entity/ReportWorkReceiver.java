package com.jshx.reportWorkReceiver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;
import com.jshx.reportWork.entity.ReportWork;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="REPORT_WORK_RECEIVER")
public class ReportWorkReceiver extends BaseModel
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
	 * 工单ID
	 */
	private ReportWork reportWork;

	/**
	 * 接收人
	 */
	private User receiveUser;

	/**
	 * 接受时间
	 */
	private Date receiveTime;

	/**
	 * 接受标记
	 */
	private String receiveFlag;


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


	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="RECEIVE_USER")
	public User getReceiveUser()
	{
		return this.receiveUser;
	}

	public void setReceiveUser(User receiveUser)
	{
		this.receiveUser = receiveUser;
	}

	@Column(name="RECEIVE_TIME")
	public Date getReceiveTime()
	{
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime)
	{
		this.receiveTime = receiveTime;
	}

	@Column(name="RECEIVE_FLAG")
	public String getReceiveFlag()
	{
		return this.receiveFlag;
	}

	public void setReceiveFlag(String receiveFlag)
	{
		this.receiveFlag = receiveFlag;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=ReportWork.class)
    @JoinColumn(name="WORK_ID")
	public ReportWork getReportWork()
	{
		return reportWork;
	}

	public void setReportWork(ReportWork reportWork)
	{
		this.reportWork = reportWork;
	}

}
