/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-22        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

/**  
 * 模块权限实体类
 * @author   Chenjian
 * @version 创建时间：2011-1-22 下午06:42:58  
 */
@Deprecated
@Table(name="MODULE_RIGHT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModuleRight extends BaseModel {

	private static final long serialVersionUID = -8328957048060834629L;
	
	/** 所属模块 */
	private Module module;
	
	/** 所属角色 */
	private UserRole role;

	/**
	 * 获取模块对象
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE, targetEntity=Module.class)
	@JoinColumn(name = "MODULE_ID")
	public Module getModule() {
		return module;
	}

	/**
	 * 设置模块对象
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * 获取角色对象
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE, targetEntity=UserRole.class)
	@JoinColumn(name = "ROLE_ID")
	public UserRole getRole() {
		return role;
	}

	/**
	 * 设置角色对象
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
}
