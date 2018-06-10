/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

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
 * @version 创建时间：2011-2-18 上午10:13:15  
 * Portal与角色的多对多映射
 */
@Table(name="PORTAL_RIGHT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PortalRight extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private UserRole role;
	
	private Portal portal;

	/**
	 * @return the role
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=UserRole.class)
	@JoinColumn(name = "ROLE_ID")
	public UserRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * @return the portal
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Portal.class)
	@JoinColumn(name = "PORTAL_ID")
	public Portal getPortal() {
		return portal;
	}

	/**
	 * @param portal the portal to set
	 */
	public void setPortal(Portal portal) {
		this.portal = portal;
	}
}
