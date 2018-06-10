/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-10        Chenjian          create
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

/** 
 * 登陆日志实体类
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午10:00:26  
 */
@Entity
@Table(name = "LOGON_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogonLog extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	/** 访问者 */
	private User visitor;
	
	/** 访问日期 */
	private Date visitedDate;

	/** 访问者的IP */
	private String fromIp;
	
	/** 访问方式：移动终端或PC */
	private String loginType;
	
	/**浏览器类型*/
	private String browser;

	/** 操作系统*/
	private String os;
	
	 /** 用户使用的浏览器和操作系统详情*/
    private String userAgent;

    /** 获取访问者 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name = "USER_ID")
	public User getVisitor() {
		return visitor;
	}

	 /** 设置访问者 */
	public void setVisitor(User visitor) {
		this.visitor = visitor;
	}

	/** 获取访问日期 */
	@Column(name = "VISITED_DATE")
	public Date getVisitedDate() {
		return visitedDate;
	}

	/** 设置访问日期 */
	public void setVisitedDate(Date visitedDate) {
		this.visitedDate = visitedDate;
	}

	/** 获取访问者的IP */
	@Column(name = "FROM_IP", length = 30)
	public String getFromIp() {
		return fromIp;
	}

	/** 设置访问者的IP */
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}

	/** 获取访问方式：移动终端或PC */
	@Column(name = "LOGIN_TYPE", length = 20)
	public String getLoginType() {
		return loginType;
	}

	/** 设置访问方式：移动终端或PC */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	/**
	 * 获取浏览器信息
	 */
	@Column(name="BROWSER")
	public String getBrowser() {
		return browser;
	}
	/**
	 * 设置浏览器信息
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	/**
	 * 获取操作系统
	 */
	@Column(name="OS")
	public String getOs() {
		return os;
	}
	/**
	 * 设置操作系统
	 */
	public void setOs(String os) {
		this.os = os;
	}
	/**
	 * 获取浏览器的userAgent
	 */
	@Column(name="USERAGENT")
	public String getUserAgent() {
		return userAgent;
	}
	/**
	 * 设置浏览器的userAgent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
