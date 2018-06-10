package com.jshx.feedback.entity;

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
@Table(name="FEEDBACK")
public class Feedback extends BaseModel
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
	 * 意见反馈
	 */
	private String content;

	/**
	 * 用户姓名
	 */
	private String userName;

	
	public Feedback(){
	}
	
	public Feedback(String id, String content, String userName){
this.id = id;

this.content = content;

this.userName = userName;
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

	
	@Column(name="CONTENT")
	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
