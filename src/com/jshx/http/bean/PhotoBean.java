package com.jshx.http.bean;

public class PhotoBean {
	private String id;
	private String  picName;//图片相对路径
	private String  type;//类型 签到：0 签退：1 机房巡检：2 光交巡检：3 生产区巡检：4 装移机：5
	private String remark;///备注
	private String taskId;//关联id（签到信息）
	private String keyId;//关键点id
	private String fileName;//真实文件名 lj 2013-07-27
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
