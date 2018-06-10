package com.jshx.heflRelation.entity;

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
@Table(name="HYFL_RELATION")
public class HyflRelation extends BaseModel
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
	 * 原行业分类编码
	 */
	private String oldCode;

	/**
	 * 新行业分类编码
	 */
	private String newCode;

	/**
	 * 新行业分类名称
	 */
	private String newCodename;


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

	
	@Column(name="OLD_CODE")
	public String getOldCode()
	{
		return this.oldCode;
	}

	public void setOldCode(String oldCode)
	{
		this.oldCode = oldCode;
	}

	@Column(name="NEW_CODE")
	public String getNewCode()
	{
		return this.newCode;
	}

	public void setNewCode(String newCode)
	{
		this.newCode = newCode;
	}

	@Column(name="NEW_CODENAME")
	public String getNewCodename()
	{
		return this.newCodename;
	}

	public void setNewCodename(String newCodename)
	{
		this.newCodename = newCodename;
	}

}
