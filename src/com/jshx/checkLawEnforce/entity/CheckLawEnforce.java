package com.jshx.checkLawEnforce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CHECK_LAW_ENFORCE")
public class CheckLawEnforce extends BaseModel
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
	 * 安全生产执法检查基本信息表ID
	 */
	private CheckBasic basic;

	/**
	 * 执法人员ID
	 */
	private User checkUser;


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

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CheckBasic.class)
    @JoinColumn(name="BASIC_ID")
	public CheckBasic getBasic()
	{
		return basic;
	}

	public void setBasic(CheckBasic basic)
	{
		this.basic = basic;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name="USER_ID")
	public User getCheckUser()
	{
		return checkUser;
	}

	public void setCheckUser(User checkUser)
	{
		this.checkUser = checkUser;
	}

}
