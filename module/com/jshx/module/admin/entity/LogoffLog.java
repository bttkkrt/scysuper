/**
 * Copyright 2014 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2014-3-18          YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.jshx.core.base.entity.BaseModel;


/**
 * 登出日志实体类
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="LOGOFF_LOG")
public class LogoffLog extends BaseModel
{
	/**
	 * 删除标记
	 */
	private Integer delFlag;
	
	/**
	 * 登出用户ID
	 */	
	private User user;

	/**
	 * 登出IP
	 */
	private String fromIp;

	/**
	 * 登出类型
	 */
	private String logoffType;

	/**
	 * 登出时间
	 */
	private Date logoffDate;

	/**
	 * 浏览器的userAgent
	 */
	private String userAgent;

	/**
	 * 浏览器信息
	 */
	private String browser;

	/**
	 * 操作系统
	 */
	private String operationsystem;

	/**
	 * 获取删除标志位
	 */
	@Column
	public Integer getDelFlag()
	{
		return delFlag;
	}

	/**
	 * 设置删除标志位
	 */
	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取登出用户
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	/**
	 * 设置登出用户
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取登出IP
	 */
	@Column(name="FROM_IP")
	public String getFromIp()
	{
		return this.fromIp;
	}

	/**
	 * 设置登出IP
	 */
	public void setFromIp(String fromIp)
	{
		this.fromIp = fromIp;
	}

	/**
	 * 获取登出类型
	 */
	@Column(name="LOGOFF_TYPE")
	public String getLogoffType()
	{
		return this.logoffType;
	}

	/**
	 * 设置登出类型
	 */
	public void setLogoffType(String logoffType)
	{
		this.logoffType = logoffType;
	}

	/**
	 * 获取登出时间
	 */
	@Column(name="LOGOFF_DATE")
	public Date getLogoffDate()
	{
		return this.logoffDate;
	}

	/**
	 * 设置登出时间
	 */
	public void setLogoffDate(Date logoffDate)
	{
		this.logoffDate = logoffDate;
	}

	/**
	 * 获取浏览器的userAgent
	 */
	@Column(name="USER_AGENT",length=256)
	public String getUserAgent()
	{
		return this.userAgent;
	}

	/**
	 * 设置浏览器的userAgent
	 */
	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	/**
	 * 获取浏览器信息
	 */
	@Column(name="BROWSER")
	public String getBrowser()
	{
		return this.browser;
	}

	/**
	 * 设置浏览器信息
	 */
	public void setBrowser(String browser)
	{
		this.browser = browser;
	}

	/**
	 * 获取操作系统
	 */
	@Column(name="OPERATIONSYSTEM")
	public String getOperationsystem()
	{
		return this.operationsystem;
	}

	/**
	 * 设置操作系统
	 */
	public void setOperationsystem(String operationsystem)
	{
		this.operationsystem = operationsystem;
	}

}
