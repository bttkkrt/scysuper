package com.jshx.log.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="BEHAVIOR_LOG_PARAM")
public class BehaviorLogParam extends BaseModel
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
	 * 参数名称
	 */
	private String paramName;

	/**
	 * 参数值
	 */
	private String paramValue;

	/**
	 * 日志
	 */
	private UserBehaviorLog log;


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

	
	@Column(name="PARAM_NAME")
	public String getParamName()
	{
		return this.paramName;
	}

	public void setParamName(String paramName)
	{
		this.paramName = paramName;
	}

	@Column(name="PARAM_VALUE")
	public String getParamValue()
	{
		return this.paramValue;
	}

	public void setParamValue(String paramValue)
	{
		this.paramValue = paramValue;
	}

	@ManyToOne(fetch = FetchType.EAGER, targetEntity=UserBehaviorLog.class)
	@JoinColumn(name = "LOG_ID")
	public UserBehaviorLog getLog() {
		return log;
	}

	public void setLog(UserBehaviorLog log) {
		this.log = log;
	}

}
