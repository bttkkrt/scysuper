package com.jshx.wxyxcrwgl.entity;

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
@Table(name="DAN_TAS_MAN")
public class DanTasMan extends BaseModel
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
	 * 危险源名称
	 */
	private String dangerName;

	/**
	 * 巡查开始时间
	 */
	private Date checkTime;

	/**
	 * 巡查人员ID
	 */
	private String checkPeopleId;

	/**
	 * 巡查人员姓名
	 */
	private String checkPeopleName;

	/**
	 * 所在区域id
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 关联计划编号
	 */
	private String assPlanNo;

	/**
	 * 巡查单号
	 */
	private String chenkNo;

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 巡查项类型
	 */
	private String checkType;

	/**
	 * 附件关联id
	 */
	private String linkId;
	/**
	 * 新增巡查结束时间
	 * lj
	 * 2015-10-14
	 */
	private Date checkTimeEnd;
	/**
	 * 新增危险源id
	 * lj
	 * 2015-10-14
	 */
	private  String dangerId;
	
	/**
	 * 新增实际巡查项
	 * lj 
	 * 2015-10-16
	 */
	private  String realCheckTerm;
	/**
	 * 新增巡查反馈备注
	 * lj 
	 * 2015-10-16
	 */
	private String remark;
	/**
	 * 新增巡查结果
	 * lj 
	 * 2015-10-16
	 */
	private String result="待巡查";
	/**
	 * 新增巡查上报时间
	 */
	private String reportTime;
	
	
	/**
	 * 巡查类型
	 */
	private String checkKind;
	
	/**
	 * 巡查路线Id
	 */
	private String checkRodeId;
	
	/**
	 * 巡查路线名称
	 */
	private String checkRodeName;
	
	/**
	 * 巡查项目id
	 */
	private  String checkId;
	
	/**
	 * 巡查项目名称
	 */
	private String checkName;
	
	@Column
	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public DanTasMan(){
	}
	
	public DanTasMan(String assPlanNo,String id, String dangerName, Date checkTime, String checkPeopleName, String areaName, String companyName, String taskName, String checkType, Date checkTimeEnd,String result){

		this.assPlanNo = assPlanNo;
		
		this.id = id;

this.dangerName = dangerName;

this.checkTime = checkTime;

this.checkPeopleName = checkPeopleName;

this.areaName = areaName;

this.companyName = companyName;

this.taskName = taskName;

this.checkType = checkType;

this.checkTimeEnd = checkTimeEnd;

this.result = result;
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

	
	@Column(name="DANGER_NAME")
	public String getDangerName()
	{
		return this.dangerName;
	}

	public void setDangerName(String dangerName)
	{
		this.dangerName = dangerName;
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

	@Column(name="CHECK_PEOPLE_ID")
	public String getCheckPeopleId()
	{
		return this.checkPeopleId;
	}

	public void setCheckPeopleId(String checkPeopleId)
	{
		this.checkPeopleId = checkPeopleId;
	}

	@Column(name="CHECK_PEOPLE_NAME")
	public String getCheckPeopleName()
	{
		return this.checkPeopleName;
	}

	public void setCheckPeopleName(String checkPeopleName)
	{
		this.checkPeopleName = checkPeopleName;
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

	@Column(name="ASS_PLAN_NO")
	public String getAssPlanNo()
	{
		return this.assPlanNo;
	}

	public void setAssPlanNo(String assPlanNo)
	{
		this.assPlanNo = assPlanNo;
	}

	@Column(name="CHENK_NO")
	public String getChenkNo()
	{
		return this.chenkNo;
	}

	public void setChenkNo(String chenkNo)
	{
		this.chenkNo = chenkNo;
	}

	@Column(name="TASK_NAME")
	public String getTaskName()
	{
		return this.taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	@Column(name="CHECK_TYPE")
	public String getCheckType()
	{
		return this.checkType;
	}

	public void setCheckType(String checkType)
	{
		this.checkType = checkType;
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
	@Column
	public Date getCheckTimeEnd() {
		return checkTimeEnd;
	}

	public void setCheckTimeEnd(Date checkTimeEnd) {
		this.checkTimeEnd = checkTimeEnd;
	}
	@Column
	public String getDangerId() {
		return dangerId;
	}

	public void setDangerId(String dangerId) {
		this.dangerId = dangerId;
	}
	@Column
	public String getRealCheckTerm() {
		return realCheckTerm;
	}

	public void setRealCheckTerm(String realCheckTerm) {
		this.realCheckTerm = realCheckTerm;
	}
	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="CHECK_KIND")
	

	public String getCheckKind() {
		return checkKind;
	}
	public void setCheckKind(String checkKind) {
		this.checkKind = checkKind;
	}
	@Column(name="CHECK_RODE_ID")
	

	public String getCheckRodeId() {
		return checkRodeId;
	}

	public void setCheckRodeId(String checkRodeId) {
		this.checkRodeId = checkRodeId;
	}
	@Column(name="CHECK_RODE_NAME")
	public String getCheckRodeName() {
		return checkRodeName;
	}

	public void setCheckRodeName(String checkRodeName) {
		this.checkRodeName = checkRodeName;
	}
	@Column(name="CHECK_ID")
	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	@Column(name="CHECK_NAME")
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	

}
