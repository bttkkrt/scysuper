package com.jshx.zywsjbxx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author 高强
 *  createtime 13年11月20
 *  desc 职业卫生管理人员
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ZYWSGLRY")
public class Zywsglry extends BaseModel
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
	 * 职业卫生管理人员姓名
	 */
	private String glrxm;

	/**
	 * 职业卫生管理人员职务
	 */
	private String glrzw;

	/**
	 * 职业卫生管理人员办公电话
	 */
	private String glrdh;

	/**
	 * 职业卫生管理人员手机
	 */
	private String glrsj;

	/**
	 * 职业卫生管理人员文化程度
	 */
	private String slrwhcd;

	/**
	 * 职业卫生管理人员专业
	 */
	private String slrzy;

	/**
	 * 职业卫生关联id
	 */
	private String zywsjbxxid;


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

	
	@Column(name="GLRXM")
	public String getGlrxm()
	{
		return this.glrxm;
	}

	public void setGlrxm(String glrxm)
	{
		this.glrxm = glrxm;
	}

	@Column(name="GLRZW")
	public String getGlrzw()
	{
		return this.glrzw;
	}

	public void setGlrzw(String glrzw)
	{
		this.glrzw = glrzw;
	}

	@Column(name="GLRDH")
	public String getGlrdh()
	{
		return this.glrdh;
	}

	public void setGlrdh(String glrdh)
	{
		this.glrdh = glrdh;
	}

	@Column(name="GLRSJ")
	public String getGlrsj()
	{
		return this.glrsj;
	}

	public void setGlrsj(String glrsj)
	{
		this.glrsj = glrsj;
	}

	@Column(name="SLRWHCD")
	public String getSlrwhcd()
	{
		return this.slrwhcd;
	}

	public void setSlrwhcd(String slrwhcd)
	{
		this.slrwhcd = slrwhcd;
	}

	@Column(name="SLRZY")
	public String getSlrzy()
	{
		return this.slrzy;
	}

	public void setSlrzy(String slrzy)
	{
		this.slrzy = slrzy;
	}

	@Column(name="ZYWSJBXXID")
	public String getZywsjbxxid()
	{
		return this.zywsjbxxid;
	}

	public void setZywsjbxxid(String zywsjbxxid)
	{
		this.zywsjbxxid = zywsjbxxid;
	}

}
