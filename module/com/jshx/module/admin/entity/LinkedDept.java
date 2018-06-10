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
 * 关联部门实体类
 */
@Table(name = "LINKED_DEPARTMENT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LinkedDept extends BaseModel {

	private static final long serialVersionUID = -6155863958794764199L;

	/** 主部门 */
	private Department mainDept;

	/** 副部门 */
	private Department subDept;
	/** 获取主部门 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
	@JoinColumn(name = "MAIN_DEPT_ID")
	public Department getMainDept() {
		return mainDept;
	}
	/** 设置主部门 */
	public void setMainDept(Department mainDept) {
		this.mainDept = mainDept;
	}

	/** 获取副部门 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
	@JoinColumn(name = "SUB_DEPT_ID")
	public Department getSubDept() {
		return subDept;
	}
	/** 设置副部门 */
	public void setSubDept(Department subDept) {
		this.subDept = subDept;
	}

}
