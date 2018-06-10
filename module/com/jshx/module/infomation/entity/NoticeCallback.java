package com.jshx.module.infomation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;

@SuppressWarnings("serial")
@Table(name="NOTICECALLBACK")
@Entity
public class NoticeCallback extends BaseModel{
	private String noticeId;
	private String userID;
	private String userName;
	private String isRead;
	@Column
	public String getLastReadTime() {
		return lastReadTime;
	}
	public void setLastReadTime(String lastReadTime) {
		this.lastReadTime = lastReadTime;
	}
	private String lastReadTime;
	@Column
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	@Column
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	@Column
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	}
