package com.jshx.module.mobile.version.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;

@SuppressWarnings("serial")
@Entity
@Table(name="VERSION")
public class Version extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	
	/**
	 * 版本号
	 */
	private String versionNumber;

	/**
	 * 版本下载
	 */
	private String versionDownload;

	/**
	 * 版本平台
	 */
	private String versionPlatform;
	
	private String content;


	@Column
	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	@Column
	public Integer getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	
	@Column(name="VERSION_NUMBER")
	public String getVersionNumber()
	{
		return this.versionNumber;
	}

	public void setVersionNumber(String versionNumber)
	{
		this.versionNumber = versionNumber;
	}

	@Column(name="VERSION_DOWNLOAD")
	public String getVersionDownload()
	{
		return this.versionDownload;
	}

	public void setVersionDownload(String versionDownload)
	{
		this.versionDownload = versionDownload;
	}

	@Column(name="VERSION_PLATFORM")
	public String getVersionPlatform()
	{
		return this.versionPlatform;
	}

	public void setVersionPlatform(String versionPlatform)
	{
		this.versionPlatform = versionPlatform;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
