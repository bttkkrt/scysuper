/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-12        Chenjian          create
 * 2011-1-20        Chenjian          添加部门属性、开通时间、有效期、是否完全开放等字段
 * 2011-1-20        Chenjian          修改采用Hibernate的annotation方式
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
import com.jshx.core.base.entity.IDepartment;
import com.jshx.module.admin.extend.IDeptExtendInfo;

/** 
 * 部门实体类 
 * @author   Chenjian
 * @version 创建时间：2011-1-12 下午04:13:57  
 */
@Table(name="DEPARTMENT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends BaseModel implements IDepartment {
	
	private static final long serialVersionUID = -5627610283337003294L;

	/** 部门名称 */
	private String deptName;
	
	/** 部门代码 */
	private String deptCode;
	
	/** 部门排序索引 */
	private Integer sortSQ;
	
	/** 逻辑删除标识 */
	private Integer delFlag;
	
	/** 是否有下层部门 */
	private Integer hasChild;
	
	/** 父部门id */
	private String parentDeptCode;
	
	/** 上层部门 */
	private Department parentDept;
		
	/** 部门类型编码 */
	private String deptTypeCode;
		
	/** 下层部门ID的列表 */
	private List<String> childDeptIds;
		
	/** 关联部门的类型 */
	private String linkedDeptTypeCode;
	
	/*
	 * 子部门
	 */
	private List<Department> childDepts;
	/*
	 * 下属员工
	 */
	private List<User> users;
	
	/** 关联部门 */
	private List<LinkedDept> subLinkedDepts;
	private List<LinkedDept> mainLinkedDepts;
	
	/** 组织机构扩展信息 */
	private IDeptExtendInfo deptExtendInfo;
	
	 private String needSubFlow;

	/** 
	 * 获取部门代码 
	 */
	@Column(name = "DEPT_CODE", length =100, nullable = false)
	public String getDeptCode() {
		return this.deptCode;
	}
	
	/** 
	 * 设置部门代码 
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/** 
	 * 获取部门名称
	 */
	@Column(name = "DEPT_NAME", length =100, nullable = false)
	public String getDeptName() {
		return deptName;
	}
	/** 
	 * 设置部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/** 
	 * 获取排序字段
	 */
	@Column(name = "SORT_SQ", length =3, nullable = false)
	public Integer getSortSQ() {
		return sortSQ;
	}
	/** 
	 * 设置排序字段
	 */
	public void setSortSQ(Integer sortSQ) {
		this.sortSQ = sortSQ;
	}

	/**  
	 * 获取删除标志位
	 */
	@Column(name = "DEL_FLAG", length =1, nullable = false)
	public Integer getDelFlag() {
		return delFlag;
	}

	/**  
	 * 设置删除标志位
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**  
	 * 获取是否有下层部门标志位
	 */
	@Column(name = "HAS_CHILD", length =3, nullable = false)
	public Integer getHasChild() {
		return hasChild;
	}
	/**  
	 * 设置是否有下层部门标志位
	 */
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	
	/**
	 * 获取父部门id
	 * @return
	 */
	@Column(name = "PARENT_DEPT_ID")
	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}

	/** 获取上层部门 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Department.class)
	@JoinColumn(name = "PARENT_DEPT_ID", insertable = false, updatable = false)
	public Department getParentDept() {
		return parentDept;
	}
	/**设置上层部门 */
	public void setParentDept(Department parentDept) {
		this.parentDept = parentDept;
	}

	

	/** 获取部门类型编码 */
	@Column(name = "DEPT_TYPE_CODE", length =10, nullable = true)
	public String getDeptTypeCode() {
		return deptTypeCode;
	}

	/** 设置部门类型编码 */
	public void setDeptTypeCode(String deptTypeCode) {
		this.deptTypeCode = deptTypeCode;
	}

	/** 获取下层部门ID的列表 */
	@Transient
	public List<String> getChildDeptIds() {
		return childDeptIds;
	}

	/**
	 * 设置下层部门ID的列表
	 */
	public void setChildDeptIds(List<String> childDeptIds) {
		this.childDeptIds = childDeptIds;
	}

	/** 获取关联部门的类型 */
	@Column(name = "LINKED_DEPT_TYPE_CODE" , length = 30)
	public String getLinkedDeptTypeCode() {
		return linkedDeptTypeCode;
	}

	/** 设置关联部门的类型 */
	public void setLinkedDeptTypeCode(String linkedDeptTypeCode) {
		this.linkedDeptTypeCode = linkedDeptTypeCode;
	}

	/**
	 * 获取关联子部门
	 */
	@OneToMany(mappedBy = "parentDept", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	//@Cascade(value = {org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
    //  org.hibernate.annotations.CascadeType.ALL})
	public List<Department> getChildDepts() {
		return childDepts;
	}

	/**
	 * 设置关联子部门
	 */
	public void setChildDepts(List<Department> childDepts) {
		this.childDepts = childDepts;
	}

	/**
	 * 获取关联用户
	 */
	@OneToMany(mappedBy = "dept", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<User> getUsers() {
		return users;
	}

	/**
	 * 设置关联用户
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/** 获取关联主部门 */
	@OneToMany(mappedBy = "mainDept", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<LinkedDept> getMainLinkedDepts() {
		return mainLinkedDepts;
	}
	/** 设置关联主部门 */
	public void setMainLinkedDepts(List<LinkedDept> mainLinkedDepts) {
		this.mainLinkedDepts = mainLinkedDepts;
	}

	/** 获取关联副部门 */
	@OneToMany(mappedBy = "subDept", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<LinkedDept> getSubLinkedDepts() {
		return subLinkedDepts;
	}
	/** 设置关联副部门 */
	public void setSubLinkedDepts(List<LinkedDept> subLinkedDepts) {
		this.subLinkedDepts = subLinkedDepts;
	}

	/** 获取部门扩展信息 */
	@Transient
	public IDeptExtendInfo getDeptExtendInfo() {
		return deptExtendInfo;
	}
	/** 设置部门扩展信息 */
	public void setDeptExtendInfo(IDeptExtendInfo deptExtendInfo) {
		this.deptExtendInfo = deptExtendInfo;
	}
	
	@Column(name="NEED_SUB_FLOW", length=10)
	  public String getNeedSubFlow() {
	    return this.needSubFlow;
	  }

	  public void setNeedSubFlow(String needSubFlow) {
	    this.needSubFlow = needSubFlow;
	  }
	
}
