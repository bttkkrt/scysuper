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
 * @version  创建时间：2011-4-20 下午15:56:45  
 * 报表配置信息实体类
 */

@Table(name="REPORT")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Report extends BaseModel {

	private static final long serialVersionUID = 6719681976238913172L;
	
	/**
	 * 报表名
	 */
	private String reportName;
	
	/**
	 * 报表发布的文件名，.rptdesign文件
	 */
	private String deployFileName;
	
	/**
	 * 报表访问路径
	 */
	private String url;
	
	/**
	 * SQL脚本
	 */
	private String sqlScript;
	
	/**
	 * 最新版本号
	 */
	private Integer latestVersion;

	/**
	 * 删除标记
	 */
	private Integer delFlag;
	
	/**
	 * 发布者
	 */
	private String deployer;
	
	/**
	 * 部门编号
	 */
	private String deptId;
	

	@Column(name = "REPORTNAME", length = 32, nullable = false)
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Column(name = "DEPLOYFILENAME", length = 32, nullable = false)
	public String getDeployFileName() {
		return deployFileName;
	}

	public void setDeployFileName(String deployFileName) {
		this.deployFileName = deployFileName;
	}

	@Column(name = "URL", length = 128, nullable = false)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "SQLSCRIPT", length = 1024, nullable = true)
	public String getSqlScript() {
		return sqlScript;
	}

	public void setSqlScript(String sqlScript) {
		this.sqlScript = sqlScript;
	}
	
	@Column(name = "LATESTVERSION", length = 32, nullable = true)
	public Integer getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(Integer latestVersion) {
		this.latestVersion = latestVersion;
	}
	
	@Column(name = "DELFLAG", length = 1, nullable = false)
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "DEPLOYER", length = 32, nullable = true)
	public String getDeployer() {
		return deployer;
	}

	public void setDeployer(String deployer) {
		this.deployer = deployer;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
