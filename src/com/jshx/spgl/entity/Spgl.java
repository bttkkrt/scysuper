package com.jshx.spgl.entity;

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
@Table(name="SPGL")
public class Spgl extends BaseModel
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
	 * 登录人名称
	 */
	private String loginName;

	/**
	 * 登录时间
	 */
	private Date loginTime;
	
	/**
	 * 通道编号
	 */
	private String guid;
	
	/**
	 * 通道名称
	 */
	private String videoName;

	
	public Spgl(){
	}
	
	public Spgl(String id, String loginName, Date loginTime,String videoName){
this.id = id;

this.loginName = loginName;

this.loginTime = loginTime;

this.videoName = videoName;
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

	
	@Column(name="LOGIN_NAME")
	public String getLoginName()
	{
		return this.loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	@Column(name="LOGIN_TIME")
	public Date getLoginTime()
	{
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		this.loginTime = loginTime;
	}
	@Column(name="GUID")
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	@Column(name="VIDEO_NAME")
	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

}
