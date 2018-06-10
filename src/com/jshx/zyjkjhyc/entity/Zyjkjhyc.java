package com.jshx.zyjkjhyc.entity;

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
@Table(name="ZYJKJHYC")
public class Zyjkjhyc extends BaseModel
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
	 * 身份证
	 */
	private String sfz;

	/**
	 * 姓名
	 */
	private String xm;

	/**
	 * 体检机构
	 */
	private String tjjg;

	/**
	 * 体检日期
	 */
	private String tjrq;

	/**
	 * 复查情况
	 */
	private String fcqk;

	/**
	 * 处理情况
	 */
	private String clqk;

	/**
	 * 作业场所接触人员关联id
	 */
	private String proid;


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

	
	@Column(name="SFZ")
	public String getSfz()
	{
		return this.sfz;
	}

	public void setSfz(String sfz)
	{
		this.sfz = sfz;
	}

	@Column(name="XM")
	public String getXm()
	{
		return this.xm;
	}

	public void setXm(String xm)
	{
		this.xm = xm;
	}

	@Column(name="TJJG")
	public String getTjjg()
	{
		return this.tjjg;
	}

	public void setTjjg(String tjjg)
	{
		this.tjjg = tjjg;
	}

	@Column(name="TJRQ")
	public String getTjrq()
	{
		return this.tjrq;
	}

	public void setTjrq(String tjrq)
	{
		this.tjrq = tjrq;
	}

	@Column(name="FCQK")
	public String getFcqk()
	{
		return this.fcqk;
	}

	public void setFcqk(String fcqk)
	{
		this.fcqk = fcqk;
	}

	@Column(name="CLQK")
	public String getClqk()
	{
		return this.clqk;
	}

	public void setClqk(String clqk)
	{
		this.clqk = clqk;
	}

	@Column(name="PROID")
	public String getProid()
	{
		return this.proid;
	}

	public void setProid(String proid)
	{
		this.proid = proid;
	}

}
