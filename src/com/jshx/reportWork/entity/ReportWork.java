package com.jshx.reportWork.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.reportWorkReceiver.entity.ReportWorkReceiver;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="REPORT_WORK")
public class ReportWork extends BaseModel
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
	 * 工作标题
	 */
	private String workTitle;

	/**
	 * 工作类型
	 */
	private String workType;

	/**
	 * 部门编码
	 */
	private String deptCode;

	/**
	 * 工作内容
	 */
	private String workContent;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 关联附件id
	 */
	private String proId;
	
	/**
	 * 查看记录集合
	 */
	private List<ReportWorkReceiver> receiverList;

	private String userIds;
	private String userNames;

	  private String time;
	@Column(name="PROID")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
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

	
	@Column(name="WORK_TITLE")
	public String getWorkTitle()
	{
		return this.workTitle;
	}

	public void setWorkTitle(String workTitle)
	{
		this.workTitle = workTitle;
	}

	@Column(name="WORK_TYPE")
	public String getWorkType()
	{
		return this.workType;
	}

	public void setWorkType(String workType)
	{
		this.workType = workType;
	}

	@Column(name="DEPT_CODE")
	public String getDeptCode()
	{
		return this.deptCode;
	}

	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
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

	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@OneToMany(mappedBy="reportWork", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	@Where(clause="delFlag=0")
	public List<ReportWorkReceiver> getReceiverList()
	{
		return receiverList;
	}

	public void setReceiverList(List<ReportWorkReceiver> receiverList)
	{
		this.receiverList = receiverList;
	}
	 @Column(name="USERIDS")
	  public String getUserIds() {
		  return userIds;
	  }

	  public void setUserIds(String userIds) {
		  this.userIds = userIds;
	  }
	  @Column(name="USERNAMES")
	  public String getUserNames() {
		  return userNames;
	  }

	  public void setUserNames(String userNames) {
		  this.userNames = userNames;
	  }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	  
}
