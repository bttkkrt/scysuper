package com.jshx.ajxx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CASE_INFO")
public class CaseInfo extends BaseModel
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
	 * 企业地址
	 */
	private String companyAddress;

	/**
	 * 立案号
	 */
	private String caseId;

	/**
	 * 案由
	 */
	private String caseCause;

	/**
	 * 案件来源
	 */
	private String caseSource;

	/**
	 * 案件时间
	 */
	private Date caseTime;

	/**
	 * 案件名称
	 */
	private String caseName;

	/**
	 * 当事人类别
	 */
	private String personType;

	/**
	 * 当事人
	 */
	private String person;

	/**
	 * 联系电话
	 */
	private String tele;
	
	/**
	 * 家庭住址
	 */
	private String address;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 性别
	 */
	private String sex;
	
	
	/**
	 * 身份证号
	 */
	private String sfzh;
	
	/**
	 * 职务
	 */
	private String zw;
	
	
	/**
	 * 邮编
	 */
	private String zipCode;
	
	/**
	 * 当事人基本情况
	 */
	private String personCondition;

	/**
	 * 案件基本情况
	 */
	private String caseCondition;

	/**
	 * 承办人意见
	 */
	private String undertakerComment;

	/**
	 * 承办人ID
	 */
	private String undertakerId;
	
	/**
	 * 承办日期
	 */
	private Date underTime;
	
	/**
	 * 承办人
	 */
	private String undertakerName;
	
	private String undertakerName1;


	/**
	 * 审核意见
	 */
	private String checkComment;

	/**
	 * 审核人ID
	 */
	private String checkPersonId;
	
	/**
	 * 审核人
	 */
	private String checkPersonName;
	
	/**
	 * 审核日期
	 */
	private Date checkTime;

	/**
	 * 审批意见
	 */
	private String approvalComment;

	/**
	 * 审批人ID
	 */
	private String approvalId;
	
	/**
	 * 审批人
	 */
	private String approvalName;
	
	/**
	 * 审批日期
	 */
	private Date approvalTime;

	/**
	 * 案件状态     7承办人审核  0表示待队长审核，1表示待领导审批，2表示执行中，3表示已结案,4表示已归档，5表示审核不通过  8:法务审核
	 */
	private String caseStatus;

	/**
	 * 轻伤人数
	 */
	private String miborNum;

	/**
	 * 重伤人数
	 */
	private String injuriesNum;

	/**
	 * 死亡人数
	 */
	private String dieNum;

	/**
	 * 事故级别
	 */
	private String accidentLevel;

	/**
	 * 事故类别
	 */
	private String accidentCategory;
	
	/**
	 * 审核结果
	 */
	private String result;
	
	/**
	 * 归档标志
	 */
	private String gajbz;
	
	/**
	 * 立案年
	 */
	private String glh;
	
	/**
	 * 立案号
	 */
	private String glhNum;
	
	/**
	 * 结案日期
	 */
	private Date jaTime;
	/**
	 * 立案日期
	 */
	private Date laTime;
	/**
	 * 归档日期
	 */
	private Date gdTime;
	/**
	 * 结案号
	 */
	private String gdNum;
	/**
	 * 保存期限
	 */
	private String  bcqx;
	/**
	 * 处理结果
	 */
	private String approvalResult;
	
	/**
	 * 归档地址
	 */
	private String gdhttpurl;
	
	/**
	 * 归档地址
	 */
	private String gdnwurl;
	
	/**
	 * 首页大小
	 */
	private String sySize;
	
	/**
	 * 卷内目录大小
	 */
	private String jnmuSize;
	
	/**
	 * 处罚类型
	 */
	private String fineType;
	
	/**
	 * 法定代表人
	 */
	private String fddbr;
	
	/**
	 * 统一社会信用代码
	 */
	private String xyhm;
	
	private String fwcheck;
	
	private String dzqmcheck;
	
	private String dzcheck;
	
	private String jzcheck;
	
	public CaseInfo(){
	}
	
	public CaseInfo(String id, String areaName, String companyName,Date caseTime, String caseName, String caseSource,String caseStatus,String createUserID,String undertakerId,String undertakerName){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.caseTime = caseTime;

this.caseName = caseName;

this.caseSource = caseSource;

this.caseStatus = caseStatus;

this.createUserID = createUserID;

this.undertakerId = undertakerId;

this.undertakerName = undertakerName;

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

	@Column(name="CASE_ID")
	public String getCaseId()
	{
		return this.caseId;
	}

	public void setCaseId(String caseId)
	{
		this.caseId = caseId;
	}

	@Column(name="CASE_CAUSE")
	public String getCaseCause()
	{
		return this.caseCause;
	}

	public void setCaseCause(String caseCause)
	{
		this.caseCause = caseCause;
	}

	@Column(name="CASE_SOURCE")
	public String getCaseSource()
	{
		return this.caseSource;
	}

	public void setCaseSource(String caseSource)
	{
		this.caseSource = caseSource;
	}

	@Column(name="CASE_TIME")
	public Date getCaseTime()
	{
		return this.caseTime;
	}

	public void setCaseTime(Date caseTime)
	{
		this.caseTime = caseTime;
	}

	@Column(name="CASE_NAME")
	public String getCaseName()
	{
		return this.caseName;
	}

	public void setCaseName(String caseName)
	{
		this.caseName = caseName;
	}

	@Column(name="PERSON_TYPE")
	public String getPersonType()
	{
		return this.personType;
	}

	public void setPersonType(String personType)
	{
		this.personType = personType;
	}

	@Column(name="PERSON")
	public String getPerson()
	{
		return this.person;
	}

	public void setPerson(String person)
	{
		this.person = person;
	}

	@Column(name="TELE")
	public String getTele()
	{
		return this.tele;
	}

	public void setTele(String tele)
	{
		this.tele = tele;
	}

	@Column(name="PERSON_CONDITION")
	public String getPersonCondition()
	{
		return this.personCondition;
	}

	public void setPersonCondition(String personCondition)
	{
		this.personCondition = personCondition;
	}

	@Column(name="CASE_CONDITION")
	public String getCaseCondition()
	{
		return this.caseCondition;
	}

	public void setCaseCondition(String caseCondition)
	{
		this.caseCondition = caseCondition;
	}

	@Column(name="UNDERTAKER_COMMENT")
	public String getUndertakerComment()
	{
		return this.undertakerComment;
	}

	public void setUndertakerComment(String undertakerComment)
	{
		this.undertakerComment = undertakerComment;
	}

	@Column(name="UNDERTAKER_ID")
	public String getUndertakerId()
	{
		return this.undertakerId;
	}

	public void setUndertakerId(String undertakerId)
	{
		this.undertakerId = undertakerId;
	}


	@Column(name="CHECK_COMMENT")
	public String getCheckComment()
	{
		return this.checkComment;
	}

	public void setCheckComment(String checkComment)
	{
		this.checkComment = checkComment;
	}

	@Column(name="CHECK_PERSON_ID")
	public String getCheckPersonId()
	{
		return this.checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId)
	{
		this.checkPersonId = checkPersonId;
	}


	@Column(name="APPROVAL_COMMENT")
	public String getApprovalComment()
	{
		return this.approvalComment;
	}

	public void setApprovalComment(String approvalComment)
	{
		this.approvalComment = approvalComment;
	}

	@Column(name="APPROVAL_ID")
	public String getApprovalId()
	{
		return this.approvalId;
	}

	public void setApprovalId(String approvalId)
	{
		this.approvalId = approvalId;
	}


	@Column(name="CASE_STATUS")
	public String getCaseStatus()
	{
		return this.caseStatus;
	}

	public void setCaseStatus(String caseStatus)
	{
		this.caseStatus = caseStatus;
	}

	@Column(name="MIBOR_NUM")
	public String getMiborNum()
	{
		return this.miborNum;
	}

	public void setMiborNum(String miborNum)
	{
		this.miborNum = miborNum;
	}

	@Column(name="INJURIES_NUM")
	public String getInjuriesNum()
	{
		return this.injuriesNum;
	}

	public void setInjuriesNum(String injuriesNum)
	{
		this.injuriesNum = injuriesNum;
	}

	@Column(name="DIE_NUM")
	public String getDieNum()
	{
		return this.dieNum;
	}

	public void setDieNum(String dieNum)
	{
		this.dieNum = dieNum;
	}

	@Column(name="ACCIDENT_LEVEL")
	public String getAccidentLevel()
	{
		return this.accidentLevel;
	}

	public void setAccidentLevel(String accidentLevel)
	{
		this.accidentLevel = accidentLevel;
	}

	@Column(name="ACCIDENT_CATEGORY")
	public String getAccidentCategory()
	{
		return this.accidentCategory;
	}

	public void setAccidentCategory(String accidentCategory)
	{
		this.accidentCategory = accidentCategory;
	}
	@Column(name="CHECK_PERSON_NAME")
	public String getCheckPersonName() {
		return checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}
	@Column(name="APPROVAL_NAME")
	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}
	@Column(name="UNDERTAKER_NAME")
	public String getUndertakerName() {
		return undertakerName;
	}

	public void setUndertakerName(String undertakerName) {
		this.undertakerName = undertakerName;
	}
	@Column(name="RESULT")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="UNDER_TIME")
	public Date getUnderTime() {
		return underTime;
	}

	public void setUnderTime(Date underTime) {
		this.underTime = underTime;
	}
	@Column(name="CHECK_TIME")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	@Column(name="APPROVAL_TIME")
	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="ZIP_CODE")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Column(name="AGE")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	@Column(name="SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="SFZH")
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	@Column(name="ZW")
	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}
	@Column(name="GAJBZ")
	public String getGajbz() {
		return gajbz;
	}

	public void setGajbz(String gajbz) {
		this.gajbz = gajbz;
	}
	@Column(name="JA_TIME")
	public Date getJaTime() {
		return jaTime;
	}

	public void setJaTime(Date jaTime) {
		this.jaTime = jaTime;
	}
	@Column(name="LA_TIME")
	public Date getLaTime() {
		return laTime;
	}

	public void setLaTime(Date laTime) {
		this.laTime = laTime;
	}
	@Column(name="GD_TIME")
	public Date getGdTime() {
		return gdTime;
	}

	public void setGdTime(Date gdTime) {
		this.gdTime = gdTime;
	}
	@Column(name="GD_NUM")
	public String getGdNum() {
		return gdNum;
	}

	public void setGdNum(String gdNum) {
		this.gdNum = gdNum;
	}
	@Column(name="BCQX")
	public String getBcqx() {
		return bcqx;
	}

	public void setBcqx(String bcqx) {
		this.bcqx = bcqx;
	}
	@Column(name="APPROVAL_RESULT")
	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}
	@Column(name="GD_HTTP_URL")
	public String getGdhttpurl() {
		return gdhttpurl;
	}

	public void setGdhttpurl(String gdhttpurl) {
		this.gdhttpurl = gdhttpurl;
	}
	@Column(name="GD_NW_URL")
	public String getGdnwurl() {
		return gdnwurl;
	}

	public void setGdnwurl(String gdnwurl) {
		this.gdnwurl = gdnwurl;
	}
	@Column(name="SY_SIZE")
	public String getSySize() {
		return sySize;
	}

	public void setSySize(String sySize) {
		this.sySize = sySize;
	}
	@Column(name="JNMU_SIZE")
	public String getJnmuSize() {
		return jnmuSize;
	}

	public void setJnmuSize(String jnmuSize) {
		this.jnmuSize = jnmuSize;
	}
	@Column(name="COMPANY_ADDRESS")
	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	@Column(name="FINE_TYPE")
	public String getFineType() {
		return fineType;
	}

	public void setFineType(String fineType) {
		this.fineType = fineType;
	}
	@Column(name="GLH")
	public String getGlh() {
		return glh;
	}

	public void setGlh(String glh) {
		this.glh = glh;
	}
	@Column(name="GLH_NUM")
	public String getGlhNum() {
		return glhNum;
	}

	public void setGlhNum(String glhNum) {
		this.glhNum = glhNum;
	}
	@Column
	public String getFddbr() {
		return fddbr;
	}

	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	@Column
	public String getXyhm() {
		return xyhm;
	}

	public void setXyhm(String xyhm) {
		this.xyhm = xyhm;
	}
	@Column
	public String getFwcheck() {
		return fwcheck;
	}

	public void setFwcheck(String fwcheck) {
		this.fwcheck = fwcheck;
	}
	@Column
	public String getDzqmcheck() {
		return dzqmcheck;
	}

	public void setDzqmcheck(String dzqmcheck) {
		this.dzqmcheck = dzqmcheck;
	}
	@Column
	public String getDzcheck() {
		return dzcheck;
	}

	public void setDzcheck(String dzcheck) {
		this.dzcheck = dzcheck;
	}
	@Column
	public String getJzcheck() {
		return jzcheck;
	}

	public void setJzcheck(String jzcheck) {
		this.jzcheck = jzcheck;
	}
	@Column
	public String getUndertakerName1() {
		return undertakerName1;
	}

	public void setUndertakerName1(String undertakerName1) {
		this.undertakerName1 = undertakerName1;
	}
}
