package com.jshx.terminlLoginInfo.entity;

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
@Table(name="TERMINAL_LOGIN_INFO")
public class TerminalLoginInfo extends BaseModel
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
	private String versionNum;

	/**
	 * 型号
	 */
	private String modal;

	/**
	 * 用户名
	 */
	private String userName;

   /**
    * 添加登录时间 2011-10-18
    * @return
    */
    private String loginTime;
    
    private String imsi;
    
    @Column(name="login_time")
	public String getLoginTime() {
	return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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

	
	@Column(name="VERSION_NUM")
	public String getVersionNum()
	{
		return this.versionNum;
	}

	public void setVersionNum(String versionNum)
	{
		this.versionNum = versionNum;
	}

	@Column(name="MODAL")
	public String getModal()
	{
		return this.modal;
	}

	public void setModal(String modal)
	{
		this.modal = modal;
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
	@Column(name="IMSI")
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

}
