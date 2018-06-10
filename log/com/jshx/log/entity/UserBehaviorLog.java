package com.jshx.log.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="USER_BEHAVIOR_LOG")
public class UserBehaviorLog extends BaseModel
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
	 * 用户行为
	 */
	private UserBehavior behavior;

	/**
	 * 记录时间
	 */
	private Date loggedDate;
	
	private User user;

	/**
	 * 日志内容
	 */
	private String logContent;
	
	private String remoteIp;
	
	private List<BehaviorLogParam> paramList;


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

	@Column(name="LOGGED_DATE")
	public Date getLoggedDate()
	{
		return this.loggedDate;
	}

	public void setLoggedDate(Date loggedDate)
	{
		this.loggedDate = loggedDate;
	}

	@Column(name="LOG_CONTENT")
	public String getLogContent()
	{
		return this.logContent;
	}

	public void setLogContent(String logContent)
	{
		this.logContent = logContent;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity=UserBehavior.class)
	@JoinColumn(name = "BEHAVIOR_ID")
	public UserBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(UserBehavior behavior) {
		this.behavior = behavior;
	}

	@OneToMany(mappedBy="log", targetEntity = BehaviorLogParam.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("createTime desc")
	public List<BehaviorLogParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<BehaviorLogParam> paramList) {
		this.paramList = paramList;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity=User.class)
	@JoinColumn(name = "CREATEUSERID", insertable = false, updatable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "REMOTE_IP", length = 50)
	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

}
