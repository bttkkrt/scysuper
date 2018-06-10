package com.jshx.splx.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SPLX")
public class Splx extends BaseModel
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
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 开始时间
	 */
	private Date beginTime;

	/**
	 * GUID
	 */
	private String guid;
	
	private String taskId;

	
	public Splx(){
	}
	
	public Splx(String id,String fileName,Date endTime,Date beginTime,String guid,String taskId){
this.id = id;

this.fileName = fileName;

this.endTime = endTime;

this.beginTime = beginTime;

this.guid=guid;
this.taskId=taskId;
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

	
	@Column(name="FILE_NAME")
	public String getFileName()
	{
		return this.fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	@Column(name="END_TIME")
	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	@Column(name="FILE_TYPE")
	public String getFileType()
	{
		return this.fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	@Column(name="BEGIN_TIME")
	public Date getBeginTime()
	{
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}

	@Column(name="GUID")
	public String getGuid()
	{
		return this.guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	@Column(name="TASK_ID")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
