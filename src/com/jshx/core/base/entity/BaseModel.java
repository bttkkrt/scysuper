/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 11, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 所有实体类的基类
 * 
 * @author john.zhang
 * @version 创建时间：Jan 11, 2011 8:52:42 AM 
 *  
 */
@MappedSuperclass
public class  BaseModel implements Serializable {

	private static final long serialVersionUID = -6263479185850015392L;

	/** 主键 */
	protected String id;

	/** 创建时间 */
	protected Date createTime;

	/** 最近修改时间 */
	protected Date updateTime;

	/** 创建用户ID */
	protected String createUserID;

	/** 最近修改用户的ID */
	protected String updateUserID;

	@Id
	@Column(name="ROW_ID", length = 32, nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CREATETIME", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATETIME", nullable = true)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "CREATEUSERID", length=32, nullable = true)
	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	@Column(name = "UPDATEUSERID", length=32, nullable = true)
	public String getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}
}
