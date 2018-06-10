package com.jshx.jcx.entity;

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
@Table(name="CHE_ITE")
public class CheIte extends BaseModel
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
	 * 检查项名称
	 */
	private String checkName;

	/**
	 * 检查项类型
	 */
	private String checkType;

	/**
	 * 备注
	 */
	private String remark;

	
	public CheIte(){
	}
	
	public CheIte(String id, String checkName, String checkType){
this.id = id;

this.checkName = checkName;

this.checkType = checkType;
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

	
	@Column(name="CHECK_NAME")
	public String getCheckName()
	{
		return this.checkName;
	}

	public void setCheckName(String checkName)
	{
		this.checkName = checkName;
	}

	@Column(name="CHECK_TYPE")
	public String getCheckType()
	{
		return this.checkType;
	}

	public void setCheckType(String checkType)
	{
		this.checkType = checkType;
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
