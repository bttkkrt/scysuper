package com.jshx.gmys.entity;

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
@Table(name="GMYS")
public class Gmys extends BaseModel
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
	 * 达标数量合计
	 */
	private String dbslhj;

	/**
	 * 一级计划达标数
	 */
	private String yjjhdbs;

	/**
	 * 一级申报企业数
	 */
	private String yjsbqys;

	/**
	 * 一级达标企业数
	 */
	private String yjdbqys;

	/**
	 * 一级累计达标数
	 */
	private String yjljdbs;

	/**
	 * 二级计划达标数
	 */
	private String ejjhdbs;

	/**
	 * 二级申报企业数
	 */
	private String ejsbqys;

	/**
	 * 二级达标企业数
	 */
	private String ejdbqys;

	/**
	 * 二级累计达标数
	 */
	private String ejljdbs;

	/**
	 * 三级计划复审数
	 */
	private String sjjhfss;

	/**
	 * 三级复审达标数
	 */
	private String sjfsdbs;

	/**
	 * 三级累计达标数
	 */
	private String sjljdbs;
	
	private String  areaId;
	
	/**
	 * 三级摘牌企业数
	 */
	private String sjzpqys;

	
	public Gmys(){
	}
	
	public Gmys(String id, Date monthTime, String areaName){
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

	@Column(name="DBSLHJ")
	public String getDbslhj()
	{
		return this.dbslhj;
	}

	public void setDbslhj(String dbslhj)
	{
		this.dbslhj = dbslhj;
	}

	@Column(name="YJJHDBS")
	public String getYjjhdbs()
	{
		return this.yjjhdbs;
	}

	public void setYjjhdbs(String yjjhdbs)
	{
		this.yjjhdbs = yjjhdbs;
	}

	@Column(name="YJSBQYS")
	public String getYjsbqys()
	{
		return this.yjsbqys;
	}

	public void setYjsbqys(String yjsbqys)
	{
		this.yjsbqys = yjsbqys;
	}

	@Column(name="YJDBQYS")
	public String getYjdbqys()
	{
		return this.yjdbqys;
	}

	public void setYjdbqys(String yjdbqys)
	{
		this.yjdbqys = yjdbqys;
	}

	@Column(name="YJLJDBS")
	public String getYjljdbs()
	{
		return this.yjljdbs;
	}

	public void setYjljdbs(String yjljdbs)
	{
		this.yjljdbs = yjljdbs;
	}

	@Column(name="EJJHDBS")
	public String getEjjhdbs()
	{
		return this.ejjhdbs;
	}

	public void setEjjhdbs(String ejjhdbs)
	{
		this.ejjhdbs = ejjhdbs;
	}

	@Column(name="EJSBQYS")
	public String getEjsbqys()
	{
		return this.ejsbqys;
	}

	public void setEjsbqys(String ejsbqys)
	{
		this.ejsbqys = ejsbqys;
	}

	@Column(name="EJDBQYS")
	public String getEjdbqys()
	{
		return this.ejdbqys;
	}

	public void setEjdbqys(String ejdbqys)
	{
		this.ejdbqys = ejdbqys;
	}

	@Column(name="EJLJDBS")
	public String getEjljdbs()
	{
		return this.ejljdbs;
	}

	public void setEjljdbs(String ejljdbs)
	{
		this.ejljdbs = ejljdbs;
	}

	@Column(name="SJJHFSS")
	public String getSjjhfss()
	{
		return this.sjjhfss;
	}

	public void setSjjhfss(String sjjhfss)
	{
		this.sjjhfss = sjjhfss;
	}

	@Column(name="SJFSDBS")
	public String getSjfsdbs()
	{
		return this.sjfsdbs;
	}

	public void setSjfsdbs(String sjfsdbs)
	{
		this.sjfsdbs = sjfsdbs;
	}

	@Column(name="SJLJDBS")
	public String getSjljdbs()
	{
		return this.sjljdbs;
	}

	public void setSjljdbs(String sjljdbs)
	{
		this.sjljdbs = sjljdbs;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name="SJZPQYS")
	public String getSjzpqys() {
		return sjzpqys;
	}

	public void setSjzpqys(String sjzpqys) {
		this.sjzpqys = sjzpqys;
	}

}
