package com.jshx.module.admin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.core.base.entity.BaseModel;
/**
 * 权限，包括用户、角色对模块、权限点的操作权限
 * 
 * @author Chenjian
 *
 */
@Table(name = "PERMISSION")
@Entity
public class Permission extends BaseModel {
	
	private static final long serialVersionUID = -3825231112968080186L;
	
	/** 权限关联的用户ID，在设置用户权限时需要配置 */
	private String userId;
	private User user;
	
	/** 权限关联的角色，在设置角色权限时需要配置 */
	private String roleId;
	private UserRole role;
	
	/** 权限表达式 */
	private String permissionExpression;
	
	/** 权限资源类型 
	 *  0：模块权限；
	 *  1：权限点权限；
	 *  2：数据权限
	 * 
	 * */
	private Integer permissionType;
	
	private Module module;
	
	/** 获取权限关联的用户ID */
	@Column(name = "USER_ID", length = 40, nullable = true)
	public String getUserId() {
		return userId;
	}
	/** 设置权限关联的用户ID */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/** 获取权限关联的用户 */
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	/** 设置权限关联的用户 */
	public void setUser(User user) {
		this.user = user;
	}
	/** 获取权限关联的角色ID*/
	@Column(name = "ROLE_ID", length = 40, nullable = true)
	public String getRoleId() {
		return roleId;
	}
	/** 设置权限关联的角色ID*/
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/** 获取权限关联的角色*/
	@ManyToOne(targetEntity = UserRole.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	public UserRole getRole() {
		return role;
	}
	/** 设置权限关联的角色*/
	public void setRole(UserRole role) {
		this.role = role;
	}
	/** 获取权限表达式 */
	@Column(name="PERMISSION_EXPRESSION", length = 200, nullable = false)
	public String getPermissionExpression() {
		return permissionExpression;
	}
	/** 设置权限表达式 */
	public void setPermissionExpression(String permissionExpression) {
		this.permissionExpression = permissionExpression;
	}
	/** 获取权限资源类型 */
	@Column(name = "PERMISSION_TYPE", length = 2, nullable = false)
	public Integer getPermissionType() {
		return permissionType;
	}
	/** 设置权限资源类型 */
	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}
	/** 获取权限关联模块*/
	@Transient
	public Module getModule() {
		return module;
	}
	/** 设置权限关联模块*/
	public void setModule(Module module) {
		this.module = module;
	}
}
