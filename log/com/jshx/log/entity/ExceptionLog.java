package com.jshx.log.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="EXCEPTION_LOG")
public class ExceptionLog extends BaseModel
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
	 * 类名
	 */
	private String className;

	/**
	 * 方法名
	 */
	private String mothodName;

	/**
	 * 异常详情
	 */
	private String msg;
	
	private User oprator;

	private String logLevel;

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

	
	@Column(name="CLASS_NAME")
	public String getClassName()
	{
		return this.className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	@Column(name="MOTHOD_NAME")
	public String getMothodName()
	{
		return this.mothodName;
	}

	public void setMothodName(String mothodName)
	{
		this.mothodName = mothodName;
	}

	@Column(name="MSG", length = 2000)
	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "createuserid", insertable = false, updatable = false)
	public User getOprator() {
		return oprator;
	}

	public void setOprator(User oprator) {
		this.oprator = oprator;
	}

	@Column(name = "log_level", length = 50)
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

}
