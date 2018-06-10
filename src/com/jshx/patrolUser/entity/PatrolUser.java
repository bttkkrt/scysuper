package com.jshx.patrolUser.entity;

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
@Table(name="PATROL_USER")
public class PatrolUser extends BaseModel
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
	 * 姓名
	 */
	private String userName;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 用户名
	 */
	private String loginId;

	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * 职务
	 */
	private String job;

	/**
	 * 企业id
	 */
	private String companyId;

	
	public PatrolUser(){
	}
	
	public PatrolUser(String id, String userName, String loginId, String job,String mobile){
this.id = id;

this.userName = userName;

this.loginId = loginId;

this.job = job;
this.mobile=mobile;
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

	
	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name="MOBILE")
	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	@Column(name="LOGIN_ID")
	public String getLoginId()
	{
		return this.loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	@Column(name="PASS_WORD")
	public String getPassWord()
	{
		return this.passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	@Column(name="JOB")
	public String getJob()
	{
		return this.job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	@Column(name="COMPANY_ID")
	public String getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}

}
