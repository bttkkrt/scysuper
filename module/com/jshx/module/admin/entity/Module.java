/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 17, 2011        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.base.entity.IModule;
import com.jshx.module.admin.extend.IModuleExtendInfo;

/**
 * 模块实体类
 * 
 * @author   Chenjian
 * @version 创建时间：2011-1-17 下午11:22:05  
 * 
 */
@Table(name="MODULE")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Module extends BaseModel implements IModule{

	private static final long serialVersionUID = 1338706570732611607L;
	
	/** 模块编码 */
	private String moduleCode;
	
	/** 模块名称 */
	private String moduleName;
	
	/** 模块链接地址 */
	private String moduleAddr;
	
	/** 模块图标（用于树形菜单显示） */
	private String smallIconAddr;
	
	/** 是否可见 */
	private Integer isVisible;
	
	/** 模块大图标（用于导航） */
	private String bigIconAddr;
	
	/** 模块链接打开位置 */
	private String target;
	
	/** 模块排序 */
	private Integer sortSq;
	
	/** 其他属性，如邮件文件夹、信息目录等 */
	private String otherProperty;
	
	/** 上层模块 */
	private Module parentModule;
	
	/** 模块全称 */
	private String moduleFullName;
	
	private Integer hasChild;
	
	/** 是否完全公开 */
	private Integer isPublic;
	
	/** 模块树中是否自动展开此节点 */
	private Integer isAutoExpand; //2013.3.6 李淮如 添加
	
	private List<ModuleRight> rightList;
	
	/** 模块扩展信息  */
	private IModuleExtendInfo moduleExtendInfo;	

	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(String id, String moduleName) {
		setId(id);
		this.moduleName = moduleName;
	}

	/** 获取模块编码 */
	@Column(name="MODULE_CODE", length=100, nullable=false)
	public String getModuleCode() {
		return moduleCode;
	}

	/** 设置模块编码 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/** 获取模块名称 */
	@Column(name="MODULE_NAME", length=30, nullable=false)
	public String getModuleName() {
		return moduleName;
	}

	/** 设置模块名称 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/** 获取模块链接地址 */
	@Column(name="MODULE_ADDR", length=500, nullable=true)
	public String getModuleAddr() {
		return moduleAddr;
	}

	/** 设置模块链接地址 */
	public void setModuleAddr(String moduleAddr) {
		this.moduleAddr = moduleAddr;
	}

	/** 获取模块图标（用于树形菜单显示） */
	@Column(name="SMALL_ICON_ADDR", length=100, nullable=true)
	public String getSmallIconAddr() {
		return smallIconAddr;
	}

	/** 设置模块图标（用于树形菜单显示） */
	public void setSmallIconAddr(String smallIconAddr) {
		this.smallIconAddr = smallIconAddr;
	}

	/** 获取是否可见标志位*/
	@Column(name="IS_VISIBLE", length=1, nullable=false)
	public Integer getIsVisible() {
		return isVisible;
	}

	/** 设置是否可见标志位*/
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	/** 获取模块大图标（用于导航） */
	@Column(name="BIG_ICON_ADDR", length=100, nullable=true)
	public String getBigIconAddr() {
		return bigIconAddr;
	}

	/** 设置模块大图标（用于导航） */
	public void setBigIconAddr(String bigIconAddr) {
		this.bigIconAddr = bigIconAddr;
	}

	/** 获取模块链接打开位置 */
	@Column(name="TARGET", length=30, nullable=true)
	public String getTarget() {
		return target;
	}

	/** 设置模块链接打开位置 */
	public void setTarget(String target) {
		this.target = target;
	}

	/** 获取模块排序 */
	@Column(name="SORT_SQ", length=3, nullable=true)
	public Integer getSortSq() {
		return sortSq;
	}

	/** 设置模块排序 */
	public void setSortSq(Integer sortSq) {
		this.sortSq = sortSq;
	}

	/** 获取其他属性，如邮件文件夹、信息目录等 */
	@Column(name="OTHER_PROPERTY", length=100, nullable=true)
	public String getOtherProperty() {
		return otherProperty;
	}

	/**设置其他属性，如邮件文件夹、信息目录等 */
	public void setOtherProperty(String otherProperty) {
		this.otherProperty = otherProperty;
	}

	/** 获取上层模块 */
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, targetEntity=Module.class)
	@JoinColumn(name = "PARENT_MODULE_ID")
	public Module getParentModule() {
		return parentModule;
	}

	/** 设置上层模块 */
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}

	/**
	 * 获取是否含子模块的标志位
	 */
	@Column(name="HAS_CHILD", length=3, nullable=true)
	public Integer getHasChild() {
		return hasChild;
	}

	/**
	 * 设置是否含子模块的标志位
	 */
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	/** 获取模块全称 */
	@Column(name="MODULE_FULL_NAME", length=100, nullable=true)
	public String getModuleFullName() {
		return moduleFullName;
	}

	/** 设置模块全称 */
	public void setModuleFullName(String moduleFullName) {
		this.moduleFullName = moduleFullName;
	}


	/**
	 * 获取权限列表
	 */
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="module", targetEntity=ModuleRight.class)
	public List<ModuleRight> getRightList() {
		return rightList;
	}

	/**
	 * 设置权限列表
	 */
	public void setRightList(List<ModuleRight> rightList) {
		this.rightList = rightList;
	}

	/** 获取是否完全公开标志位 */
	@Column(name = "IS_PUBLIC", length = 1)
	public Integer getIsPublic() {
		return isPublic;
	}

	/** 设置是否完全公开标志位 */
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	/** 获取模块扩展信息 */
	@Transient
	public IModuleExtendInfo getModuleExtendInfo() {
		return moduleExtendInfo;
	}
	/** 设置模块扩展信息 */
	public void setModuleExtendInfo(IModuleExtendInfo moduleExtendInfo) {
		this.moduleExtendInfo = moduleExtendInfo;
	}
	/** 获取模块树中是否自动展开此节点的标志位 */
	@Column(name = "IS_AUTO_EXPAND", length = 1)
	public Integer getIsAutoExpand() {
		return isAutoExpand;
	}
	/** 设置模块树中是否自动展开此节点的标志位 */
	public void setIsAutoExpand(Integer isAutoExpand) {
		this.isAutoExpand = isAutoExpand;
	}
}
