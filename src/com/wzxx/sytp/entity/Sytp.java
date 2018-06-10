package com.wzxx.sytp.entity;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SYTP")
public class Sytp extends BaseModel
{
	/**
	 * 标题
	 */
	private String infoTitle;

	/**
	 * 所属部门
	 */
	private String deptId;
	
	/**
	 * 删除标志
	 */
	private String delFlag;
	
	private String filePath;
	
	/**
	 * 第一标题
	 */
	private String firTitle;

	@Column
	public String getFirTitle() {
		return firTitle;
	}

	public void setFirTitle(String firTitle) {
		this.firTitle = firTitle;
	}
	/**
	 * 获取信息标题
	 */
	@Column(name="INFO_TITLE")
	public String getInfoTitle()
	{
		return this.infoTitle;
	}
	/**
	 * 设置信息标题
	 */
	public void setInfoTitle(String infoTitle)
	{
		this.infoTitle = infoTitle;
	}
	
	/**
	 * 获取所属部门ID
	 */
	@Column(name="DEPT_ID")
	public String getDeptId()
	{
		return this.deptId;
	}
	/**
	 * 设置所属部门ID
	 */
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	/**
	 * 获取删除标志
	 */
	@Column(name="DEL_FLAG")
	public String getDelFlag()
	{
		return this.delFlag;
	}
	/**
	 * 设置删除标志
	 */
	public void setDelFlag(String delFlag)
	{
		this.delFlag = delFlag;
	}
	@Column(name="FILE_PATH")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
