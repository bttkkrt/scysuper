package com.jshx.dfzw.entity;

import java.util.Date;

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
@Table(name="DFZW")
public class Dfzw extends BaseModel
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
	 * 填报单位id
	 */
	private String szzid;

	/**
	 * 填报单位名称
	 */
	private String szzname;
	
	private String tbr;
	
	private Date tbsj;

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

	
	@Column(name="SZZID")
	public String getSzzid()
	{
		return this.szzid;
	}

	public void setSzzid(String szzid)
	{
		this.szzid = szzid;
	}

	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
	}
	@Column(name="TBR")
	public String getTbr() {
		return tbr;
	}

	public void setTbr(String tbr) {
		this.tbr = tbr;
	}
	@Column(name="TBSJ")
	public Date getTbsj() {
		return tbsj;
	}

	public void setTbsj(Date tbsj) {
		this.tbsj = tbsj;
	}


}
