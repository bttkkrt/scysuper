package com.jshx.jdjcjh.entity;

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
@Table(name="SUP_PLA")
public class SupPla extends BaseModel
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
	 * 检查对象名称
	 */
	private String checkCompanyName;

	/**
	 * 计划状态
	 */
	private String planState;
	/**
	 * 计划ID
	 */
	private String planId;

	/**
	 * 计划名称
	 */
	private String planName;

	/**
	 * 计划类型
	 */
	private String planType;

	/**
	 * 计划开始时间
	 */
	private Date planStartTime;

	/**
	 * 计划结束时间
	 */
	private Date planEndTime;

	/**
	 * 检查部门ID
	 */
	private String checkDeptId;

	/**
	 * 检查部门名称
	 */
	private String checkDeptName;

	/**
	 * 检查人员ID
	 */
	private String checkUserId;

	/**
	 * 检查人员姓名
	 */
	private String checkUserName;
	
	/**
	 * 协办检查人员ID
	 */
	private String xbUserId;

	/**
	 * 协办检查人员姓名
	 */
	private String xbUserName;


	/**
	 * 检查项类型
	 */
	private String checkItemType;

	/**
	 * 检查对象ID
	 */
	private String checkCompanyId;
	/**
	 * 是否有已完成
	 */
	private String ifywc;//1表示有0表示没有

	private String yearPlanName;//周计划对应的年计划名称
	
	private String addType;//添加方式 0：添加按钮；1：excel导入
	
	private String finishState;//计划完成度
	public SupPla(){
	}
	
	public SupPla(String id, String checkCompanyName, String planName, String planType, String checkItemType,String createUserID,String ifywc){
this.id = id;

this.checkCompanyName = checkCompanyName;

this.planName = planName;

this.planType = planType;

this.checkItemType = checkItemType;

this.createUserID = createUserID;

this.ifywc = ifywc;
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

	
	@Column(name="CHECK_COMPANY_NAME")
	public String getCheckCompanyName()
	{
		return this.checkCompanyName;
	}

	public void setCheckCompanyName(String checkCompanyName)
	{
		this.checkCompanyName = checkCompanyName;
	}

	@Column(name="PLAN_STATE")
	public String getPlanState()
	{
		return this.planState;
	}

	public void setPlanState(String planState)
	{
		this.planState = planState;
	}

	@Column(name="PLAN_NAME")
	public String getPlanName()
	{
		return this.planName;
	}

	public void setPlanName(String planName)
	{
		this.planName = planName;
	}

	@Column(name="PLAN_TYPE")
	public String getPlanType()
	{
		return this.planType;
	}

	public void setPlanType(String planType)
	{
		this.planType = planType;
	}

	@Column(name="PLAN_START_TIME")
	public Date getPlanStartTime()
	{
		return this.planStartTime;
	}

	public void setPlanStartTime(Date planStartTime)
	{
		this.planStartTime = planStartTime;
	}

	@Column(name="PLAN_END_TIME")
	public Date getPlanEndTime()
	{
		return this.planEndTime;
	}

	public void setPlanEndTime(Date planEndTime)
	{
		this.planEndTime = planEndTime;
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

	@Column(name="CHECK_DEPT_NAME")
	public String getCheckDeptName()
	{
		return this.checkDeptName;
	}

	public void setCheckDeptName(String checkDeptName)
	{
		this.checkDeptName = checkDeptName;
	}

	@Column(name="CHECK_USER_ID")
	public String getCheckUserId()
	{
		return this.checkUserId;
	}

	public void setCheckUserId(String checkUserId)
	{
		this.checkUserId = checkUserId;
	}

	@Column(name="CHECK_USER_NAME")
	public String getCheckUserName()
	{
		return this.checkUserName;
	}

	public void setCheckUserName(String checkUserName)
	{
		this.checkUserName = checkUserName;
	}

	@Column(name="CHECK_ITEM_TYPE")
	public String getCheckItemType()
	{
		return this.checkItemType;
	}

	public void setCheckItemType(String checkItemType)
	{
		this.checkItemType = checkItemType;
	}

	@Column(name="CHECK_COMPANY_ID")
	public String getCheckCompanyId()
	{
		return this.checkCompanyId;
	}

	public void setCheckCompanyId(String checkCompanyId)
	{
		this.checkCompanyId = checkCompanyId;
	}
	@Column(name="PLAN_ID")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	@Column(name="IFYWC")
	public String getIfywc() {
		return ifywc;
	}

	public void setIfywc(String ifywc) {
		this.ifywc = ifywc;
	}
	@Column(name="YEAR_PLAN_NAME")
	public String getYearPlanName() {
		return yearPlanName;
	}
	
	public void setYearPlanName(String yearPlanName) {
		this.yearPlanName = yearPlanName;
	}
	@Column(name="ADD_TYPE")
	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getFinishState() {
		return finishState;
	}

	public void setFinishState(String finishState) {
		this.finishState = finishState;
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
