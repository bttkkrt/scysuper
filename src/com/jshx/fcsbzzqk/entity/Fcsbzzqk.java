package com.jshx.fcsbzzqk.entity;

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
@Table(name="FCSBZZQK")
public class Fcsbzzqk extends BaseModel
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
	 * 原有企业
	 */
	private String yyqy;

	/**
	 * 现有企业
	 */
	private String xyqy;

	/**
	 * 当年新增
	 */
	private String dnxz;

	/**
	 * 当年关闭
	 */
	private String dngb;

	/**
	 * 当年取缔
	 */
	private String dnqd;

	/**
	 * 省级检查
	 */
	private String sjjc;

	/**
	 * 市级检查
	 */
	private String shjjc;

	/**
	 * 区级检查
	 */
	private String qjjc;

	/**
	 * 已安装
	 */
	private String yaz;

	/**
	 * 当年计划安装数
	 */
	private String dnjhazs;

	/**
	 * 当年安装数 
	 */
	private String dnazs;

	/**
	 * 完成进度
	 */
	private String wcjd;
	
	
	private String areaId;
	/**
	 * 正在施工数
	 */
	private String zzsgs;
	
	public Fcsbzzqk(){
	}
	
	public Fcsbzzqk(String id, Date yearTime, String areaName){
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

	@Column(name="YYQY")
	public String getYyqy()
	{
		return this.yyqy;
	}

	public void setYyqy(String yyqy)
	{
		this.yyqy = yyqy;
	}

	@Column(name="XYQY")
	public String getXyqy()
	{
		return this.xyqy;
	}

	public void setXyqy(String xyqy)
	{
		this.xyqy = xyqy;
	}

	@Column(name="DNXZ")
	public String getDnxz()
	{
		return this.dnxz;
	}

	public void setDnxz(String dnxz)
	{
		this.dnxz = dnxz;
	}

	@Column(name="DNGB")
	public String getDngb()
	{
		return this.dngb;
	}

	public void setDngb(String dngb)
	{
		this.dngb = dngb;
	}

	@Column(name="DNQD")
	public String getDnqd()
	{
		return this.dnqd;
	}

	public void setDnqd(String dnqd)
	{
		this.dnqd = dnqd;
	}

	@Column(name="SJJC")
	public String getSjjc()
	{
		return this.sjjc;
	}

	public void setSjjc(String sjjc)
	{
		this.sjjc = sjjc;
	}

	@Column(name="SHJJC")
	public String getShjjc()
	{
		return this.shjjc;
	}

	public void setShjjc(String shjjc)
	{
		this.shjjc = shjjc;
	}

	@Column(name="QJJC")
	public String getQjjc()
	{
		return this.qjjc;
	}

	public void setQjjc(String qjjc)
	{
		this.qjjc = qjjc;
	}

	@Column(name="YAZ")
	public String getYaz()
	{
		return this.yaz;
	}

	public void setYaz(String yaz)
	{
		this.yaz = yaz;
	}

	@Column(name="DNJHAZS")
	public String getDnjhazs()
	{
		return this.dnjhazs;
	}

	public void setDnjhazs(String dnjhazs)
	{
		this.dnjhazs = dnjhazs;
	}

	@Column(name="DNAZS")
	public String getDnazs()
	{
		return this.dnazs;
	}

	public void setDnazs(String dnazs)
	{
		this.dnazs = dnazs;
	}

	@Column(name="WCJD")
	public String getWcjd()
	{
		return this.wcjd;
	}

	public void setWcjd(String wcjd)
	{
		this.wcjd = wcjd;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name="ZZSGS")
	public String getZzsgs() {
		return zzsgs;
	}

	public void setZzsgs(String zzsgs) {
		this.zzsgs = zzsgs;
	}

}
