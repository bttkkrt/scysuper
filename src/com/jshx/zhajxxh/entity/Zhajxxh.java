package com.jshx.zhajxxh.entity;

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
@Table(name="ZHAJXXH")
public class Zhajxxh extends BaseModel
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
	 * 监管企业数
	 */
	private String jgqys;

	/**
	 * 推广使用企业目标数
	 */
	private String tgmbs;

	/**
	 * 企业注册数
	 */
	private String qyzcs;

	/**
	 * 完成基础信息填报企业数
	 */
	private String wctbqys;

	/**
	 * 完成填报比例
	 */
	private String wctbbl;

	/**
	 * 正常运行隐患排查企业数
	 */
	private String yhpcqys;

	/**
	 * 隐患排查比例
	 */
	private String yhpcbl;

	/**
	 * 月份
	 */
	private Date monthTime;

	
	public Zhajxxh(){
	}
	
	public Zhajxxh(String id, String areaName, Date monthTime){
this.id = id;

this.areaName = areaName;

this.monthTime = monthTime;
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

	@Column(name="JGQYS")
	public String getJgqys()
	{
		return this.jgqys;
	}

	public void setJgqys(String jgqys)
	{
		this.jgqys = jgqys;
	}

	@Column(name="TGMBS")
	public String getTgmbs()
	{
		return this.tgmbs;
	}

	public void setTgmbs(String tgmbs)
	{
		this.tgmbs = tgmbs;
	}

	@Column(name="QYZCS")
	public String getQyzcs()
	{
		return this.qyzcs;
	}

	public void setQyzcs(String qyzcs)
	{
		this.qyzcs = qyzcs;
	}

	@Column(name="WCTBQYS")
	public String getWctbqys()
	{
		return this.wctbqys;
	}

	public void setWctbqys(String wctbqys)
	{
		this.wctbqys = wctbqys;
	}

	@Column(name="WCTBBL")
	public String getWctbbl()
	{
		return this.wctbbl;
	}

	public void setWctbbl(String wctbbl)
	{
		this.wctbbl = wctbbl;
	}

	@Column(name="YHPCQYS")
	public String getYhpcqys()
	{
		return this.yhpcqys;
	}

	public void setYhpcqys(String yhpcqys)
	{
		this.yhpcqys = yhpcqys;
	}

	@Column(name="YHPCBL")
	public String getYhpcbl()
	{
		return this.yhpcbl;
	}

	public void setYhpcbl(String yhpcbl)
	{
		this.yhpcbl = yhpcbl;
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

}
