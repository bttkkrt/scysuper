package com.jshx.aqsczjzd.entity;

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
@Table(name="AQSCZJZD")
public class Aqsczjzd extends BaseModel
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
	 * 区域
	 */
	private String areaName;

	/**
	 * 今年已完成数
	 */
	private String jnywcs;

	/**
	 * 目标数
	 */
	private String mbs;

	/**
	 * 上报数
	 */
	private String sbs;

	/**
	 * 验收数
	 */
	private String yss;

	/**
	 * 完成比例
	 */
	private String wcbl;

	/**
	 * 年度
	 */
	private Date yearTime;
	
	private String  areaId;

	
	public Aqsczjzd(){
	}
	
	public Aqsczjzd(String id, String areaName, Date yearTime){
this.id = id;

this.areaName = areaName;

this.yearTime = yearTime;
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

	
	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="JNYWCS")
	public String getJnywcs()
	{
		return this.jnywcs;
	}

	public void setJnywcs(String jnywcs)
	{
		this.jnywcs = jnywcs;
	}

	@Column(name="MBS")
	public String getMbs()
	{
		return this.mbs;
	}

	public void setMbs(String mbs)
	{
		this.mbs = mbs;
	}

	@Column(name="SBS")
	public String getSbs()
	{
		return this.sbs;
	}

	public void setSbs(String sbs)
	{
		this.sbs = sbs;
	}

	@Column(name="YSS")
	public String getYss()
	{
		return this.yss;
	}

	public void setYss(String yss)
	{
		this.yss = yss;
	}

	@Column(name="WCBL")
	public String getWcbl()
	{
		return this.wcbl;
	}

	public void setWcbl(String wcbl)
	{
		this.wcbl = wcbl;
	}

	@Column(name="YEAR_TIME")
	public Date getYearTime()
	{
		return this.yearTime;
	}

	public void setYearTime(Date yearTime)
	{
		this.yearTime = yearTime;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
