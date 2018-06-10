package com.jshx.safeInspectDistribute.entity;

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
@Table(name="SAFE_INSPECT_DISTRIBUTE")
public class SafeInspectDistribute extends BaseModel
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
	 * 检查类型
	 */
	private String inspectType;

	/**
	 * 巡检标题
	 */
	private String title;

	/**
	 * 检测岗位
	 */
	private String quarters;

	/**
	 * 检测周期标识
	 */
	private String cycleFlag;

	/**
	 * 检测周期值
	 */
	private String cycleValue;

	/**
	 * 巡检人员
	 */
	private String personnel;

	/**
	 * 企业标识
	 */
	private String companyFlag;
	
	/**
	 * 任务状态
	 */
	private String taskStatus;

	/**
	 * 源任务ID
	 */
	private String rootId;

	/**
	 * 任务时间
	 */
	private String taskTime;
	/**
	 * 上报时间
	 */
	private String reportTime;
	
	/**
	 * 不合格数量
	 */
	private String count;
	
	/**
	 * 每天安全检查次数
	 */
	private String inspectNum;
	/**
	 * 创建者部门编码
	 */
	private String personnelDeptCode;

	/**
	 * 创建者部门编码
	 */
	private String serialNum;

	/**
	 * 经度
	 */
	private String longitude;
	
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 位置
	 */
	private String local;
	
	/**
	 * 计划完成时间
	 */
	private Date startDate;
	/**
	 * 计划完成时间
	 */
	private Date endDate;
	

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

	
	@Column(name="INSPECT_TYPE")
	public String getInspectType()
	{
		return this.inspectType;
	}

	public void setInspectType(String inspectType)
	{
		this.inspectType = inspectType;
	}

	@Column(name="TITLE")
	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Column(name="QUARTERS")
	public String getQuarters()
	{
		return this.quarters;
	}

	public void setQuarters(String quarters)
	{
		this.quarters = quarters;
	}

	@Column(name="CYCLE_FLAG")
	public String getCycleFlag()
	{
		return this.cycleFlag;
	}

	public void setCycleFlag(String cycleFlag)
	{
		this.cycleFlag = cycleFlag;
	}

	@Column(name="CYCLE_VALUE")
	public String getCycleValue()
	{
		return this.cycleValue;
	}

	public void setCycleValue(String cycleValue)
	{
		this.cycleValue = cycleValue;
	}

	@Column(name="PERSONNEL")
	public String getPersonnel()
	{
		return this.personnel;
	}

	public void setPersonnel(String personnel)
	{
		this.personnel = personnel;
	}

	@Column(name="COMPANY_FLAG")
	public String getCompanyFlag()
	{
		return this.companyFlag;
	}

	public void setCompanyFlag(String companyFlag)
	{
		this.companyFlag = companyFlag;
	}

	@Column(name="task_status")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name="root_id")
	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	@Column(name="task_time")
	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	@Column(name="report_time")
	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	@Column(name="COUNT")
	public String getCount()
	{
		return this.count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

	@Column(name="INSPECT_NUM")
	public String getInspectNum() {
		return inspectNum;
	}

	public void setInspectNum(String inspectNum) {
		this.inspectNum = inspectNum;
	}

	@Column(name="PERSONNEL_DEPT_CODE")
	public String getPersonnelDeptCode() {
		return personnelDeptCode;
	}

	public void setPersonnelDeptCode(String personnelDeptCode) {
		this.personnelDeptCode = personnelDeptCode;
	}

	@Column(name="SERIAL_NUM")
	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="local")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Column(name="start_Date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name="end_Date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
