package com.jshx.aqscpxqk.entity;

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
@Table(name="AQSCPXQK")
public class Aqscpxqk extends BaseModel
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
	 * 月份
	 */
	private Date monthTime;

	/**
	 * 区域
	 */
	private String areaName;

	/**
	 * 主要负责人
	 */
	private String zyfzr;

	/**
	 * 安全管理人员
	 */
	private String aqglry;

	/**
	 * 职业卫生
	 */
	private String zyws;

	/**
	 * 班组长
	 */
	private String bzz;

	/**
	 * 特种作业人员
	 */
	private String tzzyry;
	
	private String areaId;

	
	public Aqscpxqk(){
	}
	
	public Aqscpxqk(String id, Date monthTime, String areaName){
this.id = id;

this.monthTime = monthTime;

this.areaName = areaName;
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

	
	@Column(name="MONTH_TIME")
	public Date getMonthTime()
	{
		return this.monthTime;
	}

	public void setMonthTime(Date monthTime)
	{
		this.monthTime = monthTime;
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

	@Column(name="ZYFZR")
	public String getZyfzr()
	{
		return this.zyfzr;
	}

	public void setZyfzr(String zyfzr)
	{
		this.zyfzr = zyfzr;
	}

	@Column(name="AQGLRY")
	public String getAqglry()
	{
		return this.aqglry;
	}

	public void setAqglry(String aqglry)
	{
		this.aqglry = aqglry;
	}

	@Column(name="ZYWS")
	public String getZyws()
	{
		return this.zyws;
	}

	public void setZyws(String zyws)
	{
		this.zyws = zyws;
	}

	@Column(name="BZZ")
	public String getBzz()
	{
		return this.bzz;
	}

	public void setBzz(String bzz)
	{
		this.bzz = bzz;
	}

	@Column(name="TZZYRY")
	public String getTzzyry()
	{
		return this.tzzyry;
	}

	public void setTzzyry(String tzzyry)
	{
		this.tzzyry = tzzyry;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
