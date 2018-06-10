package com.jshx.jdjcrw.entity;

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
@Table(name="SUP_TAS")
public class SupTas extends BaseModel
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
	 * 所在区域编号
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 关联计划id
	 */
	private String planId;

	/**
	 * 任务编号
	 */
	private String taskNum;
	
	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 检查开始时间
	 */
	private Date checkTime;
	/**
	 * 检查结束时间
	 */
	private Date checkTimeEnd;

	/**
	 * 检查人员ID
	 */
	private String checkUserid;

	/**
	 * 检查人员名称
	 */
	private String checkUsername;
	
	/**
	 * 协办检查人员ID
	 */
	private String xbUserId;

	/**
	 * 协办检查人员姓名
	 */
	private String xbUserName;

	/**
	 * 检查项类型id
	 */
	private String checkItemId;
	
	/**
	 * 检查项类型名称
	 */
	private String checkItemName;

	/**
	 * 任务状态
	 */
	private String taskState;

	/**
	 * 任务类型
	 */
	private String taskType;
	
	/**
	 * 任务开始时间
	 */
	private Date stime;
	/**
	 * 任务结束时间
	 */
	private Date ftime;
	
	
	/**
	 * 任务备注
	 */
	private String remark;
	
	/**
	 * 实际巡查项id
	 */
    private String actualCheckId;
    
    /**
	 * 实际巡查项名称
	 */
   private String actualCheckName;

   /**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 检查文书号
	 */
	private String checkInstrumentNum;
	 /**
	 * 计划巡查项名称
	 */
	private String planCheckName;
	
	/**
	 * 检查部门ID
	 */
	private String checkDeptId;
	
	/**
	 * 年计划ID
	 */
	private String yearPlanId;
	
	/**
	 * 检查记录
	 */
	private String checkRecord;
	
	private String fileName;
	
	private String httpurl;
	
	private String nwurl;
	
	public SupTas(){
	}
	
	public SupTas(String id, String areaName, String companyName, String taskNum, Date checkTime, String checkUsername, String taskState, String taskType,Date stime,Date ftime,String createUserID,String checkUserid, Date checkTimeEnd,String xbUserId,String xbUserName){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.taskNum = taskNum;

this.checkTime = checkTime;

this.checkUsername = checkUsername;

this.taskState = taskState;

this.taskType = taskType;
this.stime = stime;
this.ftime = ftime;
this.createUserID = createUserID;
this.checkUserid = checkUserid;
this.checkTimeEnd=checkTimeEnd;
this.xbUserId = xbUserId;
this.xbUserName = xbUserName;
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

	
	@Column(name="AREA_ID")
	public String getAreaId()
	{
		return this.areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
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

	@Column(name="COMPANY_ID")
	public String getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}

	@Column(name="COMPANY_NAME")
	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	@Column(name="PLAN_ID")
	public String getPlanId()
	{
		return this.planId;
	}

	public void setPlanId(String planId)
	{
		this.planId = planId;
	}

	@Column(name="TASK_NUM")
	public String getTaskNum()
	{
		return this.taskNum;
	}

	public void setTaskNum(String taskNum)
	{
		this.taskNum = taskNum;
	}

	@Column(name="CHECK_TIME")
	public Date getCheckTime()
	{
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}

	@Column(name="CHECK_USERID")
	public String getCheckUserid()
	{
		return this.checkUserid;
	}

	public void setCheckUserid(String checkUserid)
	{
		this.checkUserid = checkUserid;
	}

	@Column(name="CHECK_USERNAME")
	public String getCheckUsername()
	{
		return this.checkUsername;
	}

	public void setCheckUsername(String checkUsername)
	{
		this.checkUsername = checkUsername;
	}

	@Column(name="CHECK_ITEM_ID")
	public String getCheckItemId()
	{
		return this.checkItemId;
	}

	public void setCheckItemId(String checkItemId)
	{
		this.checkItemId = checkItemId;
	}

	@Column(name="TASK_STATE")
	public String getTaskState()
	{
		return this.taskState;
	}

	public void setTaskState(String taskState)
	{
		this.taskState = taskState;
	}

	@Column(name="TASK_TYPE")
	public String getTaskType()
	{
		return this.taskType;
	}

	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}
	@Column(name="STIME")
	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}
	@Column(name="FTIME")
	public Date getFtime() {
		return ftime;
	}

	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}
	@Column(name="TASK_NAME")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="CHECK_ITEM_NAME")
	public String getCheckItemName() {
		return checkItemName;
	}

	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}
	@Column(name="ACTUAL_CHECK_ID")
	public String getActualCheckId() {
		return actualCheckId;
	}

	public void setActualCheckId(String actualCheckId) {
		this.actualCheckId = actualCheckId;
	}
	@Column(name="ACTUAL_CHECK_NAME")
	public String getActualCheckName() {
		return actualCheckName;
	}

	public void setActualCheckName(String actualCheckName) {
		this.actualCheckName = actualCheckName;
	}
	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}
	@Column(name="CHECK_INSTRUMENT_NUM")
	public String getCheckInstrumentNum()
	{
		return this.checkInstrumentNum;
	}

	public void setCheckInstrumentNum(String checkInstrumentNum)
	{
		this.checkInstrumentNum = checkInstrumentNum;
	}
	@Column(name="PLAN_CHECK_NAME")
	public String getPlanCheckName() {
		return planCheckName;
	}

	public void setPlanCheckName(String planCheckName) {
		this.planCheckName = planCheckName;
	}
	@Column(name="CHECK_DEPT_ID")
	public String getCheckDeptId()
	{
		return this.checkDeptId;
	}

	public void setCheckDeptId(String checkDeptId)
	{
		this.checkDeptId = checkDeptId;
	}
	@Column(name="YEAR_PLAN_ID")
	public String getYearPlanId() {
		return yearPlanId;
	}

	public void setYearPlanId(String yearPlanId) {
		this.yearPlanId = yearPlanId;
	}

	@Column(name="CHECK_TIME_END")
	public Date getCheckTimeEnd() {
		return checkTimeEnd;
	}

	public void setCheckTimeEnd(Date checkTimeEnd) {
		this.checkTimeEnd = checkTimeEnd;
	}
	
	@Column(name="CHECK_RECORD")
	public String getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(String checkRecord) {
		this.checkRecord = checkRecord;
	}
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="HTTP_URL")
	public String getHttpurl() {
		return httpurl;
	}

	public void setHttpurl(String httpurl) {
		this.httpurl = httpurl;
	}
	@Column(name="NW_URL")
	public String getNwurl() {
		return nwurl;
	}

	public void setNwurl(String nwurl) {
		this.nwurl = nwurl;
	}
	@Column
	public String getXbUserId() {
		return xbUserId;
	}

	public void setXbUserId(String xbUserId) {
		this.xbUserId = xbUserId;
	}
	@Column
	public String getXbUserName() {
		return xbUserName;
	}

	public void setXbUserName(String xbUserName) {
		this.xbUserName = xbUserName;
	}
	
	
}
