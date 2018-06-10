package com.jshx.qyaqscyt.entity;

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
@Table(name="QYAQSCYT")
public class Qyaqscyt extends BaseModel
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
	 * 本月约谈企业数
	 */
	private String byytqys;

	/**
	 * 本年度累计约谈企业数
	 */
	private String bnyljytqys;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 约谈分类
	 */
	private String areaName;

	/**
	 * 本年度应约谈数
	 */
	private String bndyyt;

	
	private String areaId;
	
	public Qyaqscyt(){
	}
	
	public Qyaqscyt(String id, Date monthTime, String areaName){
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

	@Column(name="BYYTQYS")
	public String getByytqys()
	{
		return this.byytqys;
	}

	public void setByytqys(String byytqys)
	{
		this.byytqys = byytqys;
	}

	@Column(name="BNYLJYTQYS")
	public String getBnyljytqys()
	{
		return this.bnyljytqys;
	}

	public void setBnyljytqys(String bnyljytqys)
	{
		this.bnyljytqys = bnyljytqys;
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

	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="BNDYYT")
	public String getBndyyt()
	{
		return this.bndyyt;
	}

	public void setBndyyt(String bndyyt)
	{
		this.bndyyt = bndyyt;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
}
