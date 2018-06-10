package com.jshx.sjzfgpdb.entity;

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
@Table(name="SJZFGPDB")
public class Sjzfgpdb extends BaseModel
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
	 * 月份
	 */
	private Date monthTime;

	/**
	 * 总体挂牌数
	 */
	private String ztgps;

	/**
	 * 总体已整改数
	 */
	private String ztyzgs;
	
	/**
	 * 总体投入整改资金
	 */
	private String zttrzgzj;

	/**
	 * 总体已完成百分率
	 */
	private String ztywcbfl;

	/**
	 * 市级挂牌数
	 */
	private String sjgps;

	/**
	 * 市级已整改数
	 */
	private String sjyzgs;
	
	/**
	 * 市级投入经费
	 */
	private String sjtrjf;

	/**
	 * 县级挂牌数
	 */
	private String xjgps;

	/**
	 * 县级已整改数
	 */
	private String xjyzgs;
	/**
	 * 县级投入经费
	 */
	private String xjtrjf;

	/**
	 * 镇级挂牌数
	 */
	private String zjgps;

	/**
	 * 镇级已整改数
	 */
	private String zjyzgs;
	
	/**
	 * 镇级投入经费
	 */
	private String zjtrjf;
	
	
	private String areaId;

	
	public Sjzfgpdb(){
	}
	
	public Sjzfgpdb(String id, String areaName, Date monthTime){
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

	@Column(name="MONTH_TIME")
	public Date getMonthTime()
	{
		return this.monthTime;
	}

	public void setMonthTime(Date monthTime)
	{
		this.monthTime = monthTime;
	}

	@Column(name="ZTGPS")
	public String getZtgps()
	{
		return this.ztgps;
	}

	public void setZtgps(String ztgps)
	{
		this.ztgps = ztgps;
	}

	@Column(name="ZTYZGS")
	public String getZtyzgs()
	{
		return this.ztyzgs;
	}

	public void setZtyzgs(String ztyzgs)
	{
		this.ztyzgs = ztyzgs;
	}

	@Column(name="ZTYWCBFL")
	public String getZtywcbfl()
	{
		return this.ztywcbfl;
	}

	public void setZtywcbfl(String ztywcbfl)
	{
		this.ztywcbfl = ztywcbfl;
	}

	@Column(name="SJGPS")
	public String getSjgps()
	{
		return this.sjgps;
	}

	public void setSjgps(String sjgps)
	{
		this.sjgps = sjgps;
	}

	@Column(name="SJYZGS")
	public String getSjyzgs()
	{
		return this.sjyzgs;
	}

	public void setSjyzgs(String sjyzgs)
	{
		this.sjyzgs = sjyzgs;
	}

	@Column(name="XJGPS")
	public String getXjgps()
	{
		return this.xjgps;
	}

	public void setXjgps(String xjgps)
	{
		this.xjgps = xjgps;
	}

	@Column(name="XJYZGS")
	public String getXjyzgs()
	{
		return this.xjyzgs;
	}

	public void setXjyzgs(String xjyzgs)
	{
		this.xjyzgs = xjyzgs;
	}

	@Column(name="ZJGPS")
	public String getZjgps()
	{
		return this.zjgps;
	}

	public void setZjgps(String zjgps)
	{
		this.zjgps = zjgps;
	}

	@Column(name="ZJYZGS")
	public String getZjyzgs()
	{
		return this.zjyzgs;
	}

	public void setZjyzgs(String zjyzgs)
	{
		this.zjyzgs = zjyzgs;
	}
	@Column(name="ZTTRZGZJ")
	public String getZttrzgzj() {
		return zttrzgzj;
	}

	public void setZttrzgzj(String zttrzgzj) {
		this.zttrzgzj = zttrzgzj;
	}
	@Column(name="SJTRJF")
	public String getSjtrjf() {
		return sjtrjf;
	}

	public void setSjtrjf(String sjtrjf) {
		this.sjtrjf = sjtrjf;
	}
	@Column(name="XJTRJF")
	public String getXjtrjf() {
		return xjtrjf;
	}

	public void setXjtrjf(String xjtrjf) {
		this.xjtrjf = xjtrjf;
	}
	@Column(name="ZJTRJF")
	public String getZjtrjf() {
		return zjtrjf;
	}

	public void setZjtrjf(String zjtrjf) {
		this.zjtrjf = zjtrjf;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
