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
@Table(name="DATA_LOG")
public class DataLog extends BaseModel
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
	 * 数据对象名称
	 */
	private String entityName;

	/**
	 * 记录数
	 */
	private Long recordNum;

	/**
	 * 操作时长(ms)
	 */
	private Long opDuration;

	/**
	 * 操作日志
	 */
	private String opLog;

	/**
	 * 操作类型
	 */
	private String opType;

	/**
	 * 操作唯一值
	 */
	private String opId;
	
	private User oprator;

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

	
	@Column(name="ENTITY_NAME")
	public String getEntityName()
	{
		return this.entityName;
	}

	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	@Column(name="RECORD_NUM")
	public Long getRecordNum()
	{
		return this.recordNum;
	}

	public void setRecordNum(Long recordNum)
	{
		this.recordNum = recordNum;
	}

	@Column(name="OP_DURATION")
	public Long getOpDuration()
	{
		return this.opDuration;
	}

	public void setOpDuration(Long opDuration)
	{
		this.opDuration = opDuration;
	}

	@Column(name="OP_LOG")
	public String getOpLog()
	{
		return this.opLog;
	}

	public void setOpLog(String opLog)
	{
		this.opLog = opLog;
	}

	@Column(name="OP_TYPE")
	public String getOpType()
	{
		return this.opType;
	}

	public void setOpType(String opType)
	{
		this.opType = opType;
	}

	@Column(name="OP_ID")
	public String getOpId()
	{
		return this.opId;
	}

	public void setOpId(String opId)
	{
		this.opId = opId;
	}
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "createuserid", insertable = false, updatable = false)
	public User getOprator() {
		return oprator;
	}

	public void setOprator(User oprator) {
		this.oprator = oprator;
	}

}
