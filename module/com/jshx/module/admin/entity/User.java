/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-12        Chenjian          create
 * 2011-1-20        Chenjian          修改采用Hibernate的annotation方式
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

import java.util.Date;
import java.util.List; 
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.shiro.authz.AuthorizationInfo;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.base.entity.IDepartment;
import com.jshx.core.base.entity.IUser;
import com.jshx.module.admin.extend.IUserExtendInfo;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-12 下午04:13:32  
 * 用户实例
 */
@Table(name="USERS")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseModel implements IUser {
	
	private static final long serialVersionUID = 8568426839204401086L;

	private Department dept;
	
	private String password;

	private String displayName;

	private String duty;

	private Integer displayNum;
	
	private String cssId;
	
	private String tel;
	
	private String mobile;
	
	private String email;
	
	private Integer sortSq;
	
	private Date logTime;
	
	private String loginId;	
	
	private Integer delFlag;
	
	private Boolean isSuperAdmin = false;
	
	private Boolean isDeptAdmin;
	
	private List userRoles;
	
	private String deptCode;
		
	/** 用户所有角色的ID */
	private String[] roleIds;
	
	/** 用户所能访问的部门ID数组 */
	private List<String> deptIds;
	
	private List<OnlineUser> onlineUser;
	private List<LogonLog> logonLogs;
	private List<OperationLog> operationLog;
	
	/** 用户扩展信息 */
	private IUserExtendInfo userExtendInfo;
	
	/** 用户的关联部门 */
	private List<UserLinkedDept> linkedDepts;
	
	private Map<String, List<String>> permissions;
	
	private AuthorizationInfo authorizationInfo;
	
	/** 执法证号 */
	private String zfzh;
	
	private String filePath;
	
	private String deptRole;
	
	private Department indepenceDept;
	
	/**
	   * 新增字段
	   * 证件号码
	   */
	  private String idNum;
	  private String gxfw;
	
	
	/**
	 * 获得用户所在部门
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Department.class)
	@JoinColumn(name = "DEPT_ID")
	public IDepartment getDept() {
		return this.dept;
	}

	/**
	 * 获得用户密码
	 */
	@Column(name="password", length=50, nullable=false)
	public String getPassword() {
		return this.password;
	}

	/**
	 * 获得用户权限列表
	 */
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user", targetEntity=UserRight.class)
	public List getUserRoles() {
		return this.userRoles;
	}

	/**
	 * 获得用户名
	 */
	@Column(name="LOGIN_ID", length=20, nullable=false)
	public String getLoginId() {
		return this.loginId;
	}
	/**
	 * 获取用户的姓名
	 */
	@Column(name="DISPLAY_NAME", length=100, nullable=false)
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * 设置用户的姓名
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * 获取用户职务
	 */
	@Column(name="DUTY", length=100, nullable=true)
	public String getDuty() {
		return duty;
	}
	/**
	 * 设置用户职务
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 *获取列表页面每页显示数
	 */
	@Column(name="DISPLAY_NUM", length=3, nullable=true)
	public Integer getDisplayNum() {
		return displayNum;
	}
	/**
	 *设置列表页面每页显示数
	 */
	public void setDisplayNum(Integer displayNum) {
		this.displayNum = displayNum;
	}
	/**
	 *获取页面CSS代码
	 */
	@Column(name="CSS_ID", length=50, nullable=true)
	public String getCssId() {
		return cssId;
	}
	/**
	 *设置页面CSS代码
	 */
	public void setCssId(String cssId) {
		this.cssId = cssId;
	}
	/**
	 *获取用户电话
	 */
	@Column(name="TEL", length=50, nullable=true)
	public String getTel() {
		return tel;
	}
	/**
	 *设置用户电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 *获取用户手机号
	 */
	@Column(name="MOBILE", length=11, nullable=true)
	public String getMobile() {
		return mobile;
	}
	/**
	 *设置用户手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 *获取电子邮箱
	 */
	@Column(name="EMAIL", length=100, nullable=true)
	public String getEmail() {
		return email;
	}
	/**
	 *设置电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 *获取同级排序
	 */
	@Column(name="SORT_SQ", length=10, nullable=true)
	public Integer getSortSq() {
		return sortSq;
	}
	/**
	 *设置同级排序
	 */
	public void setSortSq(Integer sortSq) {
		this.sortSq = sortSq;
	}
	/**
	 *获取登录时间
	 */
	@Transient
	public Date getLogTime() {
		return logTime;
	}
	/**
	 *设置登录时间
	 */
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	/**
	 *获取删除状态
	 */
	@Column(name="DEL_FLAG", length=1, nullable=false)
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 *设置删除状态
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 *设置用户部门
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}
	/**
	 *设置用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 *设置用户名
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 *设置用户权限列表
	 */
	public void setUserRoles(List<?> userRoles) {
		this.userRoles = userRoles;
	}
	
	/** 获取用户所有角色的ID */
	@Transient
	public String[] getRoleIds() {
		return roleIds;
	}
	
	/** 设置用户所有角色的ID */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	/** 获取用户部门代码 */
	@Column(name="DEPT_CODE", length=50, nullable=false)
	public String getDeptCode() {
		return deptCode;
	}
	/** 设置用户部门代码 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/** 获取是否是超级用户字段 */
	@Transient
	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}

	/** 设置是否是超级用户字段 */
	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	/** 获取用户所能访问的部门ID数组 */
	@Transient
	public List<String> getDeptIds() {
		return deptIds;
	}
	/** 设置用户所能访问的部门ID数组 */
	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	/**
	 * 获取多对一关联在线用户记录
	 */
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<OnlineUser> getOnlineUser() {
		return onlineUser;
	}
	/**
	 * 设置多对一关联在线用户记录
	 */
	public void setOnlineUser(List<OnlineUser> onlineUser) {
		this.onlineUser = onlineUser;
	}

	/**
	 * 获取多对一关联用户登陆记录
	 */
	@OneToMany(mappedBy = "visitor", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<LogonLog> getLogonLogs() {
		return logonLogs;
	}
	/**
	 *设置多对一关联用户登陆记录
	 */
	public void setLogonLogs(List<LogonLog> logonLogs) {
		this.logonLogs = logonLogs;
	}

	/**
	 * 获取多对一关联用户操作记录
	 */
	@OneToMany(mappedBy = "visitor", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<OperationLog> getOperationLog() {
		return operationLog;
	}
	/**
	 * 设置多对一关联用户操作记录
	 */
	public void setOperationLog(List<OperationLog> operationLog) {
		this.operationLog = operationLog;
	}
	/** 获取用户扩展信息 */
	@Transient
	public IUserExtendInfo getUserExtendInfo() {
		return userExtendInfo;
	}
	/** 设置用户扩展信息 */
	public void setUserExtendInfo(IUserExtendInfo userExtendInfo) {
		this.userExtendInfo = userExtendInfo;
	}
	/** 获取用户的关联部门 */
	@Transient
	public List<UserLinkedDept> getLinkedDepts() {
		return linkedDepts;
	}
	/**设置用户的关联部门 */
	public void setLinkedDepts(List<UserLinkedDept> linkedDepts) {
		this.linkedDepts = linkedDepts;
	}
	/**获取用户许可、权限 */
	@Transient
	public Map<String, List<String>> getPermissions() {
		return permissions;
	}
	/**设置用户许可、权限 */
	public void setPermissions(Map<String, List<String>> permissions) {
		this.permissions = permissions;
	}
	/**获取用户授权 */
	@Transient
	public AuthorizationInfo getAuthorizationInfo() {
		return authorizationInfo;
	}
	/**设置用户授权 */
	public void setAuthorizationInfo(AuthorizationInfo authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}
	@Column(name="ZFZH")
	public String getZfzh() {
		return zfzh;
	}

	public void setZfzh(String zfzh) {
		this.zfzh = zfzh;
	}
	@Column(name="FILE_PATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name="DEPT_ROLE", length=100, nullable=true)
	public String getDeptRole() {
		return deptRole;
	}

	public void setDeptRole(String deptRole) {
		this.deptRole = deptRole;
	}
	
	@Column(name="ID_NUM", length=10)
	public String getIdNum()
	{
		return idNum;
	}

	public void setIdNum(String idNum)
	{
		this.idNum = idNum;
	}
	
	@Transient
	  public Boolean getIsDeptAdmin()
	  {
	    return this.isDeptAdmin;
	  }

	  public void setIsDeptAdmin(Boolean isDeptAdmin)
	  {
	    this.isDeptAdmin = isDeptAdmin;
	  }
	  
	  @Transient
	  public Department getIndepenceDept()
	  {
	    return this.indepenceDept;
	  }

	  public void setIndepenceDept(Department indepenceDept)
	  {
	    this.indepenceDept = indepenceDept;
	  }
	  @Column
		public String getGxfw() {
			return gxfw;
		}
		
		public void setGxfw(String gxfw) {
			this.gxfw = gxfw;
		}
}
