package com.jshx.qyfjfl.entity;

import javax.persistence.Transient;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.entity.EntBaseInfo;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ENTERPRISE_GRADE")
public class EnterpriseGrade extends BaseModel
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
	 * 企业id
	 */
	private String companyId;

	/**
	 * 系统评分
	 */
	private String systemScore;

	/**
	 * 企业评分
	 */
	private String checkScore;
	
	/**
	 * 见证评分
	 */
	private String ajScore;
	
	private String qyremark;

	/**
	 * 备注
	 */
	private String remarks;
	
	private String areaId;
	
	private String areaName;
	
	private String state;
	
	private String companyName;
	
	private String zpzf;
	
	private String ajzf;
	
	private Date startTime;
	
	private Date endTime;
	
	
	public EnterpriseGrade(){
	}
	
	public EnterpriseGrade(String id, String areaName,Date startTime,Date endTime,String companyName,String state,String zpzf,String ajzf){
this.id = id;
this.areaName = areaName;
this.startTime = startTime;
this.endTime = endTime;
this.companyName=companyName;
this.state=state;
this.zpzf = zpzf;
this.ajzf = ajzf;
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

	
	@Column(name="COMPANY_ID")
	public String getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}

	@Column(name="SYSTEM_SCORE")
	public String getSystemScore()
	{
		return this.systemScore;
	}

	public void setSystemScore(String systemScore)
	{
		this.systemScore = systemScore;
	}

	@Column(name="CHECK_SCORE")
	public String getCheckScore()
	{
		return this.checkScore;
	}

	public void setCheckScore(String checkScore)
	{
		this.checkScore = checkScore;
	}

	@Column(name="REMARKS")
	public String getRemarks()
	{
		return this.remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	@Column
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name="AJ_SCORE")
	public String getAjScore() {
		return ajScore;
	}

	public void setAjScore(String ajScore) {
		this.ajScore = ajScore;
	}

	@Column(name="STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name="ZPZF")
	public String getZpzf() {
		return zpzf;
	}

	public void setZpzf(String zpzf) {
		this.zpzf = zpzf;
	}
	@Column(name="AJZF")
	public String getAjzf() {
		return ajzf;
	}

	public void setAjzf(String ajzf) {
		this.ajzf = ajzf;
	}
	@Column(name="QY_REMARK")
	public String getQyremark() {
		return qyremark;
	}

	public void setQyremark(String qyremark) {
		this.qyremark = qyremark;
	}
	@Column(name="AREA_NAME")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Column(name="START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name="END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
