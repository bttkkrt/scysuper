package com.jshx.xcxlxgl.entity;

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
@Table(name="PAT_TYP_MAN")
public class PatTypMan extends BaseModel
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
	 * 巡查项类型名称
	 */
	private String patrolTypeName;

	/**
	 * 备注
	 */
	private String remark;
	
	public PatTypMan(){
	}
	
	public PatTypMan(String id, String patrolTypeName){
this.id = id;

this.patrolTypeName = patrolTypeName;
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

	
	@Column(name="PATROL_TYPE_NAME")
	public String getPatrolTypeName()
	{
		return this.patrolTypeName;
	}

	public void setPatrolTypeName(String patrolTypeName)
	{
		this.patrolTypeName = patrolTypeName;
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
