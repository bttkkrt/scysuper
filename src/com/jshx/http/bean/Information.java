package com.jshx.http.bean;

import java.sql.Clob;
import java.util.Date;

public class Information {
	private String id;
	private String infoTitle;
	private Clob infoContent;
	private String userName;
	private String deptName;
	private Date time;
	private String readed;
	private String fileName;
	private String path;
	private String infoType;
	private String createUserId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public Clob getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(Clob infoContent) {
		this.infoContent = infoContent;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getReaded() {
		return readed;
	}
	public void setReaded(String readed) {
		this.readed = readed;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
}
