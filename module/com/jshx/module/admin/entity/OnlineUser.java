/**
 * 
 */
package com.jshx.module.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

/**
 * 在线用户实体类
 * @author f_cheng
 *
 */
@Entity
@Table(name = "Online_User")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OnlineUser extends BaseModel {
	private User user;
	private String sessionId;
	private Date loginTime;
	private String loginType;
	/**
	 * 获取用户对象
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}
	/**
	 * 设置用户对象
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 获取SessionId
	 */
	@Column(name = "Session_Id")
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * 设置SessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * 获取登录时间
	 */
	@Column(name = "Login_Time")
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取登录类型
	 */
	@Column(name = "Login_Type", length = 20)
	public String getLoginType() {
		return loginType;
	}
	/**
	 * 设置登录类型
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
