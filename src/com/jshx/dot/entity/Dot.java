package com.jshx.dot.entity;

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
@Table(name="DOT")
public class Dot extends BaseModel
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
	 * 名称
	 */
	private String dotName;

	/**
	 * 位置
	 */
	private String position;

	
	public Dot(){
	}
	
	public Dot(String id, String dotName, String position){
this.id = id;

this.dotName = dotName;

this.position = position;
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

	
	@Column(name="DOT_NAME")
	public String getDotName()
	{
		return this.dotName;
	}

	public void setDotName(String dotName)
	{
		this.dotName = dotName;
	}

	@Column(name="POSITION")
	public String getPosition()
	{
		return this.position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

}
