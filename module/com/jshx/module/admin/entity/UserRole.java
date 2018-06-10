/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-15        Chenjian          添加角色编码，修改角色，使其可以分级
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
 * @author f_cheng
 *
 */
@Table(name="USERROLE")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole extends BaseModel {
	
	private static final long serialVersionUID = 8530925498978243339L;

	/** 角色名 */
	private String roleName;
	
	/** */
	private String caption;
	
	/** 删除标记 */
	private Integer delFlag;
	
	/** 排序 */
	private Integer sortSq;
	
	private Integer isSupAdmin;
	
	private String parentRoleId;
	
	private UserRole parentRole;
	
	/** 角色编号 */
	private String roleCode;
	
	/** 角色类型 */
	private String roleType;
	
	private Integer isDeptAdmin;
	
	/**
	 * Default Constructor
	 */
	public UserRole(){}
	/** 获取角色名称 */
	@Column(name = "RoleName", length = 100, unique = true)
	public String getRoleName() {
		return roleName;
	}
	/** 设置角色名称 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "caption", length=100)
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "DelFlag")
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "SortSQ")
	public Integer getSortSq() {
		return sortSq;
	}

	public void setSortSq(Integer sortSq) {
		this.sortSq = sortSq;
	}

	@Column(name = "IS_SUP_ADMIN", length = 1, nullable = true)
	public Integer getIsSupAdmin() {
		return isSupAdmin;
	}

	public void setIsSupAdmin(Integer isSupAdmin) {
		this.isSupAdmin = isSupAdmin;
	}

	/**
	 * @return the roleCode
	 */
	@Column(name = "ROLE_CODE", length = 200, nullable = true)
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "PARENT_ROLE_ID", length = 40, nullable = true)
	public String getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(String parentRoleId) {
		this.parentRoleId = parentRoleId;
	}

	@ManyToOne(targetEntity = UserRole.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ROLE_ID", insertable = false, updatable = false)
	public UserRole getParentRole() {
		return parentRole;
	}

	public void setParentRole(UserRole parentRole) {
		this.parentRole = parentRole;
	}

	@Column(name = "ROLE_TYPE", length = 50)
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	@Column(name="IS_DEPT_ADMIN", length=1, nullable=true)
	  public Integer getIsDeptAdmin() {
	    return this.isDeptAdmin;
	  }

	  public void setIsDeptAdmin(Integer isDeptAdmin) {
	    this.isDeptAdmin = isDeptAdmin;
	  }
}
