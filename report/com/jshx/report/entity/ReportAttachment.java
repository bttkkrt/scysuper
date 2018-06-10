/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-4-20          YuWeitao          create
 * ---------------------------------------------------------------
 */

package com.jshx.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

/**  
 * @author   YuWeitao
 * @version  创建时间：2011-4-20 下午17:12:13  
 * 报表.rptdesign文件附件实体类
 */

@Table(name="REPORT_ATTACHMENT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportAttachment extends BaseModel {

	private static final long serialVersionUID = -8029752118969486581L;
	
	/**
	 * 报表名
	 */
	private String reportName;
	
	/**
	 * 报表文件名
	 */
	private String fileName;
	
	/**
	 * 上传路径名
	 */
	private String uploadFilePath;
	
	/**
	 * 报表ID
	 */
	private String reportId;
	
	/**
	 * 版本号
	 */
	private Integer version;

	@Column(name = "FILENAME", length = 32, nullable = false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "UPLOADFILEPATH", length = 128, nullable = false)
	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	
	@Column(name = "REPORTID", length = 32, nullable = false)
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "VERSION", length = 32, nullable = true)
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "REPORTNAME", length = 32, nullable = false)
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}


