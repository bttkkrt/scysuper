package com.jshx.gwhy.entity;

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
@Table(name="GWHY")
public class Gwhy extends BaseModel
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
	 * 与共保体专门协商
	 */
	private String ygbtzmxs;

	/**
	 * 专题动员会
	 */
	private String ztdyh;

	/**
	 * 已报试点企业
	 */
	private String ybsdqy;

	/**
	 * 试点企业安全现状评估数
	 */
	private String sdqyaqpgs;

	/**
	 * 试点企业已投保
	 */
	private String sdqyytb;

	/**
	 * 所在区域
	 */
	private String areaName;

	private String areaId;
	
	public Gwhy(){
	}
	
	public Gwhy(String id, Date monthTime, String areaName){
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

	@Column(name="YGBTZMXS")
	public String getYgbtzmxs()
	{
		return this.ygbtzmxs;
	}

	public void setYgbtzmxs(String ygbtzmxs)
	{
		this.ygbtzmxs = ygbtzmxs;
	}

	@Column(name="ZTDYH")
	public String getZtdyh()
	{
		return this.ztdyh;
	}

	public void setZtdyh(String ztdyh)
	{
		this.ztdyh = ztdyh;
	}

	@Column(name="YBSDQY")
	public String getYbsdqy()
	{
		return this.ybsdqy;
	}

	public void setYbsdqy(String ybsdqy)
	{
		this.ybsdqy = ybsdqy;
	}

	@Column(name="SDQYAQPGS")
	public String getSdqyaqpgs()
	{
		return this.sdqyaqpgs;
	}

	public void setSdqyaqpgs(String sdqyaqpgs)
	{
		this.sdqyaqpgs = sdqyaqpgs;
	}

	@Column(name="SDQYYTB")
	public String getSdqyytb()
	{
		return this.sdqyytb;
	}

	public void setSdqyytb(String sdqyytb)
	{
		this.sdqyytb = sdqyytb;
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
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
