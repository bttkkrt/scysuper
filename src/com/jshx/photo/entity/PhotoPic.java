package com.jshx.photo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PHOTO_PIC")
public class PhotoPic extends BaseModel
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
	 * 关联编号
	 */
	private String taskProId;

	/**
	 * 图片名称
	 */
	private String picName;

	/**
	 * 图片类型
	 */
	private String picType;
	/**
	 * 模块类型
	 */
	private String type;
	/**
	 * 图片备注
	 */
	private String taskRemark;
	/**
	 * 存储的原文件名 用于页面展示 
	 * 李军 2013-07-18
	 * @return
	 */
	private String fileName;
	
	
	@Column
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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

	
	@Column(name="TASK_PRO_ID")
	public String getTaskProId()
	{
		return this.taskProId;
	}

	public void setTaskProId(String taskProId)
	{
		this.taskProId = taskProId;
	}

	@Column(name="PIC_NAME")
	public String getPicName()
	{
		return this.picName;
	}

	public void setPicName(String picName)
	{
		this.picName = picName;
	}

	@Column(name="PIC_TYPE")
	public String getPicType()
	{
		return this.picType;
	}

	public void setPicType(String picType)
	{
		this.picType = picType;
	}

	@Column(name="TASK_REMARK")
	public String getTaskRemark()
	{
		return this.taskRemark;
	}

	public void setTaskRemark(String taskRemark)
	{
		this.taskRemark = taskRemark;
	}
	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
