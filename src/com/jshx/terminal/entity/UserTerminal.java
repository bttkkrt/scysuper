package com.jshx.terminal.entity;

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
@Table(name="USER_TERMINAL")
public class UserTerminal extends BaseModel
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
	 * 用户编号
	 */
	private String userNum;

	/**
	 * IMSI
	 */
	private String imsi;

	/**
	 * IMEI
	 */
	private String imei;

	/**
	 * 终端号码
	 */
	private String telephone;

	/**
	 * 终端名称
	 */
	private String terminalName;


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

	
	@Column(name="USER_NUM")
	public String getUserNum()
	{
		return this.userNum;
	}

	public void setUserNum(String userNum)
	{
		this.userNum = userNum;
	}

	@Column(name="IMSI")
	public String getImsi()
	{
		return this.imsi;
	}

	public void setImsi(String imsi)
	{
		this.imsi = imsi;
	}

	@Column(name="IMEI")
	public String getImei()
	{
		return this.imei;
	}

	public void setImei(String imei)
	{
		this.imei = imei;
	}

	@Column(name="TELEPHONE")
	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	@Column(name="TERMINAL_NAME")
	public String getTerminalName()
	{
		return this.terminalName;
	}

	public void setTerminalName(String terminalName)
	{
		this.terminalName = terminalName;
	}

}
