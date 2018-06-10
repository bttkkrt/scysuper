package com.jshx.module.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
/**
 * 用户的关联部门，用以实现部门与用户之间的多对多关联
 * 
 * @author Chenjian
 *
 */
@Entity
@Table(name = "USER_LINKED_DEPT")
public class UserLinkedDept extends BaseModel {

	private static final long serialVersionUID = -4637096760512689603L;
	
	private String userId;
	
	private String linkedDeptId;
	
	private Department linkedDept;
	/**
	 * 获取用户ID
	 */
	@Column(name = "USER_ID", length = 40, nullable = false)
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取用户的关联部门ID
	 */
	@Column(name = "LINKED_DEP_ID", length = 40, nullable = false)
	public String getLinkedDeptId() {
		return linkedDeptId;
	}
	/**
	 * 设置用户的关联部门ID
	 */
	public void setLinkedDeptId(String linkedDeptId) {
		this.linkedDeptId = linkedDeptId;
	}
	/**
	 * 获取用户的关联部门
	 */
	@ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "LINKED_DEP_ID", insertable = false, updatable = false)
	public Department getLinkedDept() {
		return linkedDept;
	}
	/**
	 * 设置用户的关联部门
	 */
	public void setLinkedDept(Department linkedDept) {
		this.linkedDept = linkedDept;
	}

}
