/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-20        Chenjian          create
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
import com.jshx.module.admin.entity.UserRole;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-20 上午11:31:12  
 * 用户权限实体类
 */
@Table(name="USER_RIGHT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRight extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private UserRole role;

	/**
	 * 获取用户信息
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE, targetEntity=User.class)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	/**
	 * 设置用户信息
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取角色信息
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE, targetEntity=UserRole.class)
	@JoinColumn(name = "ROLE_ID")
	public UserRole getRole() {
		return role;
	}

	/**
	 * 设置角色信息
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

}
