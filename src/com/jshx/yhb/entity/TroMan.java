package com.jshx.yhb.entity;

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
@Table(name="TRO_MAN")
public class TroMan extends BaseModel
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
	 * 隐患名称
	 */
	private String troubleName;

	/**
	 * 隐患来源
	 */
	private String troubleSource;

	/**
	 * 所在区域ID
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业编号
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 上报人员编号
	 */
	private String userId;

	/**
	 * 上报人员名称
	 */
	private String userName;

	/**
	 * 上报时间
	 */
	private Date reportTime;

	/**
	 * 隐患地点
	 */
	private String troubleAdd;

	/**
	 * 处理职能部门id
	 */
	private String dealDeptId;

	/**
	 * 处理职能部门名称
	 */
	private String dealDeptName;

	/**
	 * 隐患级别
	 */
	private String troubleLevel;

	/**
	 * 隐患类别
	 */
	private String troubleSort;

	/**
	 * 检查项
	 */
	private String checkItem;

	/**
	 * 整改期限
	 */
	private Date rectificationTerm;

	/**
	 * 整改完成时间
	 */
	private Date recfinishTime;

	/**
	 * 整改资金
	 */
	private String rectificationMoney;

	/**
	 * 整改状态
	 */
	private String rectificationState;
	/**
	 * 处理状态（处理完成，处理未完成）
	 */
	private String dealState;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 审核结果
	 * lj 2015-10-20
	 */
	private String auditResult;
	/**
	 * 审核备注
	 * lj 2015-10-20
	 */
	private String remark;
	/**
	 * 是否上报安委会
	 * 费谦 2015-10-30
	 * 1:是，0：否
	 */
	private String ifReportAwh;
	/**
	 * 是否已整改
	 * 费谦 2015-10-30
	 * 0:否，1：是
	 */
	private String ifCorrected;
	/**
	 * 是否需要安监处理
	 * 费谦 2015-10-30
	 * 1:是，0：否
	 */
	private String ifNeedAj;

	/**
	 * 新增关联任务的id
	 */
	private String taskId;
	
	/**
	 * 新增整改备注
	 */
	private String rectRemark;
	
	/**
	 * 新增字段 隐患详情
	 * lj 2015-12-22
	 * @return
	 */
	private String introduce;
	/**
	 * 新增字段 整改责任部门
	 * lj 2015-12-22
	 * @return
	 */
	private String rectDept;
	/**
	 * 新增字段 整改责任人
	 * lj 2015-12-22
	 * @return
	 */
	private String rectPerson;
	
	/**整改责任人联系方式
	 * 
	 */
	private String rectTel;

	
	/**
	 * 是否需要回复
	 * 费谦 2016-1-21
	 * 1:是，0：否
	 */
	private String ifReply;
	
	
	private String fileName;
	
	private String httpurl;
	
	private String nwurl;
	
	/**
	 * 是否立案 0没有 1有
	 */
	private String ifla;
	

	@Column
	public String getIfReply() {
		return ifReply;
	}

	public void setIfReply(String ifReply) {
		this.ifReply = ifReply;
	}

	@Column
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	@Column
	public String getRectDept() {
		return rectDept;
	}

	public void setRectDept(String rectDept) {
		this.rectDept = rectDept;
	}
	@Column
	public String getRectPerson() {
		return rectPerson;
	}

	public void setRectPerson(String rectPerson) {
		this.rectPerson = rectPerson;
	}

	@Column
	public String getRectRemark() {
		return rectRemark;
	}

	public void setRectRemark(String rectRemark) {
		this.rectRemark = rectRemark;
	}

	@Column
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public TroMan(){
	}
	
	public TroMan(String id, String troubleName, String troubleSource, String areaId, String companyName, String userName, String troubleLevel, String troubleSort, String rectificationState,String createUserID,String auditResult,String ifCorrected,String userId,String ifReportAwh,String dealState,String dealDeptId,String ifReply,String areaName,String ifla){
this.id = id;

this.troubleName = troubleName;

this.troubleSource = troubleSource;

this.areaId = areaId;

this.companyName = companyName;

this.userName = userName;

this.troubleLevel = troubleLevel;

this.troubleSort = troubleSort;

this.rectificationState = rectificationState;
this.createUserID=createUserID;
this.auditResult=auditResult;
this.ifCorrected=ifCorrected;
this.userId=userId;
this.ifReportAwh = ifReportAwh;
this.dealState=dealState;
this.dealDeptId=dealDeptId;
this.ifReply=ifReply;
this.areaName = areaName;
this.ifla = ifla;
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

	
	@Column(name="TROUBLE_NAME")
	public String getTroubleName()
	{
		return this.troubleName;
	}

	public void setTroubleName(String troubleName)
	{
		this.troubleName = troubleName;
	}

	@Column(name="TROUBLE_SOURCE")
	public String getTroubleSource()
	{
		return this.troubleSource;
	}

	public void setTroubleSource(String troubleSource)
	{
		this.troubleSource = troubleSource;
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

	@Column(name="USER_ID")
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name="REPORT_TIME")
	public Date getReportTime()
	{
		return this.reportTime;
	}

	public void setReportTime(Date reportTime)
	{
		this.reportTime = reportTime;
	}

	@Column(name="TROUBLE_ADD")
	public String getTroubleAdd()
	{
		return this.troubleAdd;
	}

	public void setTroubleAdd(String troubleAdd)
	{
		this.troubleAdd = troubleAdd;
	}

	@Column(name="DEAL_DEPT_ID")
	public String getDealDeptId()
	{
		return this.dealDeptId;
	}

	public void setDealDeptId(String dealDeptId)
	{
		this.dealDeptId = dealDeptId;
	}

	@Column(name="DEAL_DEPT_NAME")
	public String getDealDeptName()
	{
		return this.dealDeptName;
	}

	public void setDealDeptName(String dealDeptName)
	{
		this.dealDeptName = dealDeptName;
	}

	@Column(name="TROUBLE_LEVEL")
	public String getTroubleLevel()
	{
		return this.troubleLevel;
	}

	public void setTroubleLevel(String troubleLevel)
	{
		this.troubleLevel = troubleLevel;
	}

	@Column(name="TROUBLE_SORT")
	public String getTroubleSort()
	{
		return this.troubleSort;
	}

	public void setTroubleSort(String troubleSort)
	{
		this.troubleSort = troubleSort;
	}

	@Column(name="CHECK_ITEM")
	public String getCheckItem()
	{
		return this.checkItem;
	}

	public void setCheckItem(String checkItem)
	{
		this.checkItem = checkItem;
	}

	@Column(name="RECTIFICATION_TERM")
	public Date getRectificationTerm()
	{
		return this.rectificationTerm;
	}

	public void setRectificationTerm(Date rectificationTerm)
	{
		this.rectificationTerm = rectificationTerm;
	}

	@Column(name="RECFINISH_TIME")
	public Date getRecfinishTime()
	{
		return this.recfinishTime;
	}

	public void setRecfinishTime(Date recfinishTime)
	{
		this.recfinishTime = recfinishTime;
	}

	@Column(name="RECTIFICATION_MONEY")
	public String getRectificationMoney()
	{
		return this.rectificationMoney;
	}

	public void setRectificationMoney(String rectificationMoney)
	{
		this.rectificationMoney = rectificationMoney;
	}

	@Column(name="RECTIFICATION_STATE")
	public String getRectificationState()
	{
		return this.rectificationState;
	}

	public void setRectificationState(String rectificationState)
	{
		this.rectificationState = rectificationState;
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
	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="IF_REPORT_AWH")
	public String getIfReportAwh() {
		return ifReportAwh;
	}

	public void setIfReportAwh(String ifReportAwh) {
		this.ifReportAwh = ifReportAwh;
	}
	@Column(name="IF_CORRECTED")
	public String getIfCorrected() {
		return ifCorrected;
	}

	public void setIfCorrected(String ifCorrected) {
		this.ifCorrected = ifCorrected;
	}
	@Column(name="IF_NEEDAJ")
	public String getIfNeedAj() {
		return ifNeedAj;
	}

	public void setIfNeedAj(String ifNeedAj) {
		this.ifNeedAj = ifNeedAj;
	}
	@Column(name="DEAL_STATE")
	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
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
	@Column(name="RECT_TEL")
	public String getRectTel() {
		return rectTel;
	}

	public void setRectTel(String rectTel) {
		this.rectTel = rectTel;
	}
	@Column
	public String getIfla() {
		return ifla;
	}

	public void setIfla(String ifla) {
		this.ifla = ifla;
	}
}
