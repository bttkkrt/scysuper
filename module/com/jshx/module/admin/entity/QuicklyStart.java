/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-11        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

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
 * @author   Chenjian
 * @version 创建时间：2011-3-11 下午03:08:53  
 * 快捷菜单  
 */
@Table(name="QUICKLY_START")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuicklyStart extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	/** 所属用户 */
	private User user;
	
	/** 模块 */
	private Module module;
	
	/** 排序 */
	private Integer sortSq;

	/**
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the module
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=Module.class)
	@JoinColumn(name = "MODULE_ID")
	public Module getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * @return the sortSq
	 */
	@Column(name = "SORT_SQ", length = 3)
	public Integer getSortSq() {
		return sortSq;
	}

	/**
	 * @param sortSq the sortSq to set
	 */
	public void setSortSq(Integer sortSq) {
		this.sortSq = sortSq;
	}

}
