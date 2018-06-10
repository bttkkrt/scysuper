package com.jshx.yhbzlsd.entity;

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
@Table(name="YHBZLSD")
public class Yhbzlsd extends BaseModel
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
	 * 持证上岗人数
	 */
	private String czsgrs;

	/**
	 * 当年累计督察
	 */
	private String dnljdc;

	/**
	 * 区域
	 */
	private String areaName;

	/**
	 * 现有零售点
	 */
	private String xylsd;

	/**
	 * 当年换证零售点计划数
	 */
	private String dnhzlsdjhs;

	/**
	 * 当年换证零售点完成数
	 */
	private String dnhzlsdwcs;

	/**
	 * 当年参加培训零售点计划数
	 */
	private String dncjpxjhs;

	/**
	 * 当年参加培训零售点完成数
	 */
	private String dncjpxwcs;

	/**
	 * 年度
	 */
	private Date yearTime;
	
	private String  areaId;

	
	public Yhbzlsd(){
	}
	
	public Yhbzlsd(String id, String areaName, Date yearTime){
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

	
	@Column(name="CZSGRS")
	public String getCzsgrs()
	{
		return this.czsgrs;
	}

	public void setCzsgrs(String czsgrs)
	{
		this.czsgrs = czsgrs;
	}

	@Column(name="DNLJDC")
	public String getDnljdc()
	{
		return this.dnljdc;
	}

	public void setDnljdc(String dnljdc)
	{
		this.dnljdc = dnljdc;
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

	@Column(name="XYLSD")
	public String getXylsd()
	{
		return this.xylsd;
	}

	public void setXylsd(String xylsd)
	{
		this.xylsd = xylsd;
	}

	@Column(name="DNHZLSDJHS")
	public String getDnhzlsdjhs()
	{
		return this.dnhzlsdjhs;
	}

	public void setDnhzlsdjhs(String dnhzlsdjhs)
	{
		this.dnhzlsdjhs = dnhzlsdjhs;
	}

	@Column(name="DNHZLSDWCS")
	public String getDnhzlsdwcs()
	{
		return this.dnhzlsdwcs;
	}

	public void setDnhzlsdwcs(String dnhzlsdwcs)
	{
		this.dnhzlsdwcs = dnhzlsdwcs;
	}

	@Column(name="DNCJPXJHS")
	public String getDncjpxjhs()
	{
		return this.dncjpxjhs;
	}

	public void setDncjpxjhs(String dncjpxjhs)
	{
		this.dncjpxjhs = dncjpxjhs;
	}

	@Column(name="DNCJPXWCS")
	public String getDncjpxwcs()
	{
		return this.dncjpxwcs;
	}

	public void setDncjpxwcs(String dncjpxwcs)
	{
		this.dncjpxwcs = dncjpxwcs;
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
