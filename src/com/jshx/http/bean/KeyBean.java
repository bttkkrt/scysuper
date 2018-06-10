package com.jshx.http.bean;

public class KeyBean {
	private String type;//类型
	private String cycle;//周期
	private String content;//关键点内容
	private String versionNumber;//版本号
	private String versionDownload;//版本下载地址
	
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	public String getVersionDownload() {
		return versionDownload;
	}
	public void setVersionDownload(String versionDownload) {
		this.versionDownload = versionDownload;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
