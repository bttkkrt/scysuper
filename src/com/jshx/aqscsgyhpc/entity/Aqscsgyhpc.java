package com.jshx.aqscsgyhpc.entity;

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
@Table(name="AQSCSGYHPC")
public class Aqscsgyhpc extends BaseModel
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
	 * 年度
	 */
	private Date yearTime;

	/**
	 * 区域
	 */
	private String areaName;

	/**
	 * 已覆盖企业数量
	 */
	private String yfgqysl;

	/**
	 * 已覆盖规模以上企业数量
	 */
	private String yfggmysqysl;

	/**
	 * 年度累计排查隐患数量
	 */
	private String ndljpcthsl;

	/**
	 * 年度累计整改隐患数量
	 */
	private String ndljzgyhsl;

	/**
	 * 年度隐患整改率
	 */
	private String ndyhzgl;

	/**
	 * 备注
	 */
	private String remark;
	
	
	private String areaId;
	
	public Aqscsgyhpc(){
	}
	
	public Aqscsgyhpc(String id, Date yearTime, String areaName){
this.id = id;

this.yearTime = yearTime;

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

	
	@Column(name="YEAR_TIME")
	public Date getYearTime()
	{
		return this.yearTime;
	}

	public void setYearTime(Date yearTime)
	{
		this.yearTime = yearTime;
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

	@Column(name="YFGQYSL")
	public String getYfgqysl()
	{
		return this.yfgqysl;
	}

	public void setYfgqysl(String yfgqysl)
	{
		this.yfgqysl = yfgqysl;
	}

	@Column(name="YFGGMYSQYSL")
	public String getYfggmysqysl()
	{
		return this.yfggmysqysl;
	}

	public void setYfggmysqysl(String yfggmysqysl)
	{
		this.yfggmysqysl = yfggmysqysl;
	}

	@Column(name="NDLJPCTHSL")
	public String getNdljpcthsl()
	{
		return this.ndljpcthsl;
	}

	public void setNdljpcthsl(String ndljpcthsl)
	{
		this.ndljpcthsl = ndljpcthsl;
	}

	@Column(name="NDLJZGYHSL")
	public String getNdljzgyhsl()
	{
		return this.ndljzgyhsl;
	}

	public void setNdljzgyhsl(String ndljzgyhsl)
	{
		this.ndljzgyhsl = ndljzgyhsl;
	}

	@Column(name="NDYHZGL")
	public String getNdyhzgl()
	{
		return this.ndyhzgl;
	}

	public void setNdyhzgl(String ndyhzgl)
	{
		this.ndyhzgl = ndyhzgl;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
