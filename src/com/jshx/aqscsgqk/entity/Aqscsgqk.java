package com.jshx.aqscsgqk.entity;

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
@Table(name="AQSCSGQK")
public class Aqscsgqk extends BaseModel
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
	 * 当月事故起数
	 */
	private String dysgqs;

	/**
	 * 当月事故生产性
	 */
	private String dysgscx;
	
	/**
	 * 当月死亡事故起数
	 */
	private String dyswsgqs;

	/**
	 * 当月死亡事故生产性
	 */
	private String dyswsgscx;
	
	
	/**
	 * 当月死亡事故人数
	 */
	private String dyswsgrs;

	/**
	 * 当月死亡事故生产性2
	 */
	private String dyswsgscxs;
	

	/**
	 * 当月重伤事故起数
	 */
	private String dyzssgqs;

	/**
	 * 当月重伤事故生产性
	 */
	private String dyzssgscx;
	
	
	/**
	 * 当月重伤事故人数
	 */
	private String dyzssgrs;

	/**
	 * 当月重伤事故生产性2
	 */
	private String dyzssgscxs;
	
	
	/**
	 * 累计事故情况起数
	 */
	private String ljsgqkqs;

	/**
	 * 累计事故情况生产性
	 */
	private String ljsgqkscx;
	
	/**
	 * 累计亡人事故起数
	 */
	private String ljwrsgqs;

	/**
	 * 累计亡人事故生产性
	 */
	private String ljwrsgscx;
	
	
	/**
	 * 累计重伤事故起数
	 */
	private String ljzssgqs;

	/**
	 * 累计重伤事故生产性
	 */
	private String ljzssgscx;

	/**
	 * 月份
	 */
	private Date monthTime;

	/**
	 * 区域
	 */
	private String areaName;
	
	
	private String areaId;

	
	public Aqscsgqk(){
	}
	
	public Aqscsgqk(String id, Date monthTime, String areaName){
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

	
	@Column(name="DYSWSGQS")
	public String getDyswsgqs()
	{
		return this.dyswsgqs;
	}

	public void setDyswsgqs(String dyswsgqs)
	{
		this.dyswsgqs = dyswsgqs;
	}

	@Column(name="DYSGQS")
	public String getDysgqs()
	{
		return this.dysgqs;
	}

	public void setDysgqs(String dysgqs)
	{
		this.dysgqs = dysgqs;
	}

	@Column(name="DYSGSCX")
	public String getDysgscx()
	{
		return this.dysgscx;
	}

	public void setDysgscx(String dysgscx)
	{
		this.dysgscx = dysgscx;
	}

	@Column(name="DYSWSGSCX")
	public String getDyswsgscx()
	{
		return this.dyswsgscx;
	}

	public void setDyswsgscx(String dyswsgscx)
	{
		this.dyswsgscx = dyswsgscx;
	}

	@Column(name="DYZSSGQS")
	public String getDyzssgqs()
	{
		return this.dyzssgqs;
	}

	public void setDyzssgqs(String dyzssgqs)
	{
		this.dyzssgqs = dyzssgqs;
	}

	@Column(name="DYZSSGSCX")
	public String getDyzssgscx()
	{
		return this.dyzssgscx;
	}

	public void setDyzssgscx(String dyzssgscx)
	{
		this.dyzssgscx = dyzssgscx;
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
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name="DYSWSGRS")
	public String getDyswsgrs() {
		return dyswsgrs;
	}

	public void setDyswsgrs(String dyswsgrs) {
		this.dyswsgrs = dyswsgrs;
	}
	@Column(name="DYSWSGSCXS")
	public String getDyswsgscxs() {
		return dyswsgscxs;
	}

	public void setDyswsgscxs(String dyswsgscxs) {
		this.dyswsgscxs = dyswsgscxs;
	}
	@Column(name="DYZSSGRS")
	public String getDyzssgrs() {
		return dyzssgrs;
	}

	public void setDyzssgrs(String dyzssgrs) {
		this.dyzssgrs = dyzssgrs;
	}
	@Column(name="DYZSSGSCXS")
	public String getDyzssgscxs() {
		return dyzssgscxs;
	}

	public void setDyzssgscxs(String dyzssgscxs) {
		this.dyzssgscxs = dyzssgscxs;
	}
	@Column(name="LJSGQKQS")
	public String getLjsgqkqs() {
		return ljsgqkqs;
	}

	public void setLjsgqkqs(String ljsgqkqs) {
		this.ljsgqkqs = ljsgqkqs;
	}
	@Column(name="LJSGQKSCX")
	public String getLjsgqkscx() {
		return ljsgqkscx;
	}

	public void setLjsgqkscx(String ljsgqkscx) {
		this.ljsgqkscx = ljsgqkscx;
	}
	@Column(name="LJWRSGQS")
	public String getLjwrsgqs() {
		return ljwrsgqs;
	}

	public void setLjwrsgqs(String ljwrsgqs) {
		this.ljwrsgqs = ljwrsgqs;
	}
	@Column(name="LJWRSGSCX")
	public String getLjwrsgscx() {
		return ljwrsgscx;
	}

	public void setLjwrsgscx(String ljwrsgscx) {
		this.ljwrsgscx = ljwrsgscx;
	}
	@Column(name="LJZSSGQS")
	public String getLjzssgqs() {
		return ljzssgqs;
	}

	public void setLjzssgqs(String ljzssgqs) {
		this.ljzssgqs = ljzssgqs;
	}
	@Column(name="LJZSSGSCX")
	public String getLjzssgscx() {
		return ljzssgscx;
	}

	public void setLjzssgscx(String ljzssgscx) {
		this.ljzssgscx = ljzssgscx;
	}

}
