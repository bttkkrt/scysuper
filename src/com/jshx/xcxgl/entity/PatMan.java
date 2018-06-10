package com.jshx.xcxgl.entity;

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
@Table(name="PAT_MAN")
public class PatMan extends BaseModel
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
	 * 巡查项名称
	 */
	private String patrolName;

	/**
	 * 巡查项类型
	 */
	private String patrolType;

	/**
	 * 备注
	 */
	private String remark;
	

	public PatMan(){
	}
	
	public PatMan(String id, String patrolName, String patrolType){
this.id = id;

this.patrolName = patrolName;

this.patrolType = patrolType;
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

	
	@Column(name="PATROL_NAME")
	public String getPatrolName()
	{
		return this.patrolName;
	}

	public void setPatrolName(String patrolName)
	{
		this.patrolName = patrolName;
	}

	@Column(name="PATROL_TYPE")
	public String getPatrolType()
	{
		return this.patrolType;
	}

	public void setPatrolType(String patrolType)
	{
		this.patrolType = patrolType;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

}
