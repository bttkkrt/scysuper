package com.jshx.log.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="USER_BEHAVIOR")
public class UserBehavior extends BaseModel
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
	 * 是否到指定的URL
	 */
	private String isContinue;

	/**
	 * 类别
	 */
	private String behaviorType;

	/**
	 * 用户行为名称
	 */
	private String behaviorName;

	/**
	 * 用户行为地址
	 */
	private String behaviorUrl;

	/**
	 * 用户行为处理服务
	 */
	private String behaviorService;

	/**
	 * 用户行为默认日志
	 */
	private String defaultLog;

	private List<UserBehaviorLog> logList;

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

	
	@Column(name="IS_CONTINUE")
	public String getIsContinue()
	{
		return this.isContinue;
	}

	public void setIsContinue(String isContinue)
	{
		this.isContinue = isContinue;
	}

	@Column(name="BEHAVIOR_TYPE")
	public String getBehaviorType()
	{
		return this.behaviorType;
	}

	public void setBehaviorType(String behaviorType)
	{
		this.behaviorType = behaviorType;
	}

	@Column(name="BEHAVIOR_NAME")
	public String getBehaviorName()
	{
		return this.behaviorName;
	}

	public void setBehaviorName(String behaviorName)
	{
		this.behaviorName = behaviorName;
	}

	@Column(name="BEHAVIOR_URL")
	public String getBehaviorUrl()
	{
		return this.behaviorUrl;
	}

	public void setBehaviorUrl(String behaviorUrl)
	{
		this.behaviorUrl = behaviorUrl;
	}

	@Column(name="BEHAVIOR_SERVICE")
	public String getBehaviorService()
	{
		return this.behaviorService;
	}

	public void setBehaviorService(String behaviorService)
	{
		this.behaviorService = behaviorService;
	}

	@Column(name="DEFAULT_LOG")
	public String getDefaultLog()
	{
		return this.defaultLog;
	}

	public void setDefaultLog(String defaultLog)
	{
		this.defaultLog = defaultLog;
	}

	@OneToMany(mappedBy="behavior", targetEntity = UserBehaviorLog.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("createTime desc")
	public List<UserBehaviorLog> getLogList() {
		return logList;
	}

	public void setLogList(List<UserBehaviorLog> logList) {
		this.logList = logList;
	}

}
