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
 * 功能点实体类
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCTION_POINT")
public class FunctionPoint extends BaseModel {
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 功能点名称
	 */
	private String funcName;

	/**
	 * 权限表达式
	 */
	private String funcPermission;

	/**
	 * 是否绑定模块
	 */
	private String isBandingModule;

	/**
	 * 绑定模块
	 */
	private String moduleId;
	private Module module;
	private String moduleName;

	/**
	 * 功能点实体类缺省构造函数
	 */
	public FunctionPoint() {
	}
	/**
	 * 功能点实体类构造函数
	 */
	public FunctionPoint(String id, String funcName, String funcPermission,
			String isBandingModule, String moduleId) {
		this.id = id;
		this.funcName = funcName;
		this.funcPermission = funcPermission;
		this.isBandingModule = isBandingModule;
		this.moduleId = moduleId;
	}
	/**
	 * 获取部门id
	 */
	@Column
	public String getDeptId() {
		return deptId;
	}

	/**
	 * 设置部门id
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * 获取删除标志位
	 */
	@Column
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 *设置删除标志位
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 获取功能点名称
	 */
	@Column(name = "FUNC_NAME")
	public String getFuncName() {
		return this.funcName;
	}
	/**
	 * 设置功能点名称
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	/**
	 * 获取权限表达式
	 */
	@Column(name = "FUNC_PERMISSION")
	public String getFuncPermission() {
		return this.funcPermission;
	}
	/**
	 * 设置权限表达式
	 */
	public void setFuncPermission(String funcPermission) {
		this.funcPermission = funcPermission;
	}

	/**
	 * 获取是否绑定模块字段
	 */
	@Column(name = "IS_BANDING_MODULE")
	public String getIsBandingModule() {
		return this.isBandingModule;
	}

	/**
	 * 设置是否绑定模块字段
	 */
	public void setIsBandingModule(String isBandingModule) {
		this.isBandingModule = isBandingModule;
	}

	/**
	 * 获取模块ID
	 */
	@Column(name = "MODULE_ID")
	public String getModuleId() {
		return this.moduleId;
	}

	/**
	 * 设置模块ID
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	

	/**
	 * 获取模块
	 */
	@ManyToOne(targetEntity = Module.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "MODULE_ID", insertable = false, updatable = false)
	public Module getModule() {
		return module;
	}

	/**
	 * 设置模块
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * 获取模块名称
	 */
	@Transient
	public String getModuleName() {
		try {
			moduleName = module.getModuleName();
		} catch (Exception e) {

		}
		return moduleName;
	}

	/**
	 * 设置模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
