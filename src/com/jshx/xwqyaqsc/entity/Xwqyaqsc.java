package com.jshx.xwqyaqsc.entity;

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
@Table(name="XWQYAQSC")
public class Xwqyaqsc extends BaseModel
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
	 * 培训企业数
	 */
	private String pxqys;

	/**
	 * 明年已达标企业数
	 */
	private String mnydbqys;

	/**
	 * 小微企业累计达标数
	 */
	private String xwqyljdbs;

	/**
	 * 年度
	 */
	private Date yearTime;

	/**
	 * 区域
	 */
	private String areaName;

	/**
	 * 今年累计达标数
	 */
	private String jnljdbs;

	/**
	 * 明年创建目标数
	 */
	private String mncjmbs;

	/**
	 * 明天已申请企业数
	 */
	private String mnysqqys;
	
	private String areaId;

	
	public Xwqyaqsc(){
	}
	
	public Xwqyaqsc(String id, Date yearTime, String areaName){
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

	
	@Column(name="PXQYS")
	public String getPxqys()
	{
		return this.pxqys;
	}

	public void setPxqys(String pxqys)
	{
		this.pxqys = pxqys;
	}

	@Column(name="MNYDBQYS")
	public String getMnydbqys()
	{
		return this.mnydbqys;
	}

	public void setMnydbqys(String mnydbqys)
	{
		this.mnydbqys = mnydbqys;
	}

	@Column(name="XWQYLJDBS")
	public String getXwqyljdbs()
	{
		return this.xwqyljdbs;
	}

	public void setXwqyljdbs(String xwqyljdbs)
	{
		this.xwqyljdbs = xwqyljdbs;
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

	@Column(name="JNLJDBS")
	public String getJnljdbs()
	{
		return this.jnljdbs;
	}

	public void setJnljdbs(String jnljdbs)
	{
		this.jnljdbs = jnljdbs;
	}

	@Column(name="MNCJMBS")
	public String getMncjmbs()
	{
		return this.mncjmbs;
	}

	public void setMncjmbs(String mncjmbs)
	{
		this.mncjmbs = mncjmbs;
	}

	@Column(name="MNYSQQYS")
	public String getMnysqqys()
	{
		return this.mnysqqys;
	}

	public void setMnysqqys(String mnysqqys)
	{
		this.mnysqqys = mnysqqys;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
