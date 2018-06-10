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

import javax.persistence.CascadeType;
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
 * 用户操作日志实体类
 * @author   Chenjian
 * @version 创建时间：2011-2-10 上午09:03:05  
 * 记录用户的访问路径
 */
@Entity
@Table(name = "OPERATION_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperationLog extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 访问者 */
	private User visitor;
	
	/** 访问日期 */
	private Date visitedDate;
	
	/** 访问地址 */
	private String url;
	
	/** 访问的模块 */
	private Module module;

	/** 访问者的IP */
	private String fromIp;
	
	/**
	 * 获取访问者信息
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name = "USER_ID")
	public User getVisitor() {
		return visitor;
	}

	/**
	 * 设置访问者信息
	 */
	public void setVisitor(User visitor) {
		this.visitor = visitor;
	}

	/**
	 * 获取访问日期
	 */
	@Column(name = "VISITED_DATE")
	public Date getVisitedDate() {
		return visitedDate;
	}

	/**
	 * 设置访问日期
	 */
	public void setVisitedDate(Date visitedDate) {
		this.visitedDate = visitedDate;
	}

	/**
	 * 获取访问URL
	 */
	@Column(name = "URL", length = 200)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置访问URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** 获取访问的模块 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Module.class)
	@JoinColumn(name = "MODULE_ID")
	public Module getModule() {
		return module;
	}

	/** 设置访问的模块 */
	public void setModule(Module module) {
		this.module = module;
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
}
