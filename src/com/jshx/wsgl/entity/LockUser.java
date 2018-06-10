package com.jshx.wsgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="LOCK_USER")
public class LockUser extends BaseModel
{ 
	private String userId;
	private String lockTime;
	private String caseId;
	private String wstype;
	private String doUserId;
	
	@Column
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column
	public String getLockTime() {
		return lockTime;
	}
	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}
	@Column
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column
	public String getWstype() {
		return wstype;
	}
	public void setWstype(String wstype) {
		this.wstype = wstype;
	}
	@Column
	public String getDoUserId() {
		return doUserId;
	}
	public void setDoUserId(String doUserId) {
		this.doUserId = doUserId;
	}
	
}
