package com.jshx.http.bean;

import java.util.Date;

public class SignBean {
	private String id;
	private String taskId;//任务id
	private String longitude;//经度
	private String recitifylong;//纠偏经度
	private String latitude;//维度
	private String recitifylat;//纠偏纬度
	private String type;//类型 签到 签退
	private String taskType;//任务类型 1 机房 2 光交 3 生产区
	private String isPass;//是否合格
	
	private String mark;//描述
	
	private String time;//考勤时间
	private String place;//地址
	private String tasktime;//巡检时间
	
	private String username;
	private String deptname;
	

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getRecitifylong() {
		return recitifylong;
	}

	public void setRecitifylong(String recitifylong) {
		this.recitifylong = recitifylong;
	}

	public String getRecitifylat() {
		return recitifylat;
	}

	public void setRecitifylat(String recitifylat) {
		this.recitifylat = recitifylat;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTasktime() {
		return tasktime;
	}

	public void setTasktime(String tasktime) {
		this.tasktime = tasktime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
}
