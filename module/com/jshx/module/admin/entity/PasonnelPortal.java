package com.jshx.module.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

@Entity
@Table(name = "PORTAL_PERSONAL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PasonnelPortal extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	
	private String userCode;
	
	private String left;
	
	private String right;

	@Column(name = "USERCODE",length=50)
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "LEFT_PORTAL",length=500)
	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	@Column(name = "RIGHT_PORTAL",length=50)
	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

}
