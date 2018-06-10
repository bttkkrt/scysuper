package com.jshx.sxxw.entity;

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
@Table(name="DISHONESTY")
public class Dishonesty extends BaseModel
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
	 * 审核人员姓名
	 */
	private String checkPersonName;

	/**
	 * 审核结果
	 */
	private String checkComment;

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
	 * 组织机构代码
	 */
	private String organizationCode;

	/**
	 * 工商注册号
	 */
	private String businessRegistration;

	/**
	 * 处罚名称
	 */
	private String punishName;

	/**
	 * 处罚决定书文号
	 */
	private String symbolDecision;

	/**
	 * 被处罚当事人
	 */
	private String punishedParty;

	/**
	 * 被处罚当事人证件号码
	 */
	private String punishedNumber;

	/**
	 * 处罚事由
	 */
	private String punishedSubject;

	/**
	 * 处罚种类
	 */
	private String punishedSpecies;

	/**
	 * 行政处罚依据
	 */
	private String punishedBasis;

	/**
	 * 行政处罚结论
	 */
	private String punishedConclusion;

	/**
	 * 没收违法所得
	 */
	private String illegalIncome;

	/**
	 * 罚款金额
	 */
	private String fines;

	/**
	 * 失信等级
	 */
	private String creditRating;

	/**
	 * 处罚机关全称
	 */
	private String penalizingOrgan;

	/**
	 * 行政处罚日期
	 */
	private Date penalizingDate;

	/**
	 * 是否公示
	 */
	private String isPublic;

	/**
	 * 公示起日期
	 */
	private Date publicStartDate;

	/**
	 * 公示止日期
	 */
	private Date publicEndDate;

	/**
	 * 执行完成日期
	 */
	private Date finishTime;

	/**
	 * 执行情况
	 */
	private String implementation;

	/**
	 * 审核人员ID
	 */
	private String checkPersonId;
	
	/**
	 * 审核状态
	 */
	private String checkStatus;

	
	public Dishonesty(){
	}
	
	public Dishonesty(String id, String checkComment, String areaId, String companyName, String punishName, String symbolDecision, String punishedSpecies, String creditRating,String checkStatus,String createUserID){
this.id = id;

this.checkComment = checkComment;

this.areaId = areaId;

this.companyName = companyName;

this.punishName = punishName;

this.symbolDecision = symbolDecision;

this.punishedSpecies = punishedSpecies;

this.creditRating = creditRating;
this.checkStatus=checkStatus;
this.createUserID=createUserID;
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

	
	@Column(name="CHECK_PERSON_NAME")
	public String getCheckPersonName()
	{
		return this.checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName)
	{
		this.checkPersonName = checkPersonName;
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

	@Column(name="ORGANIZATION_CODE")
	public String getOrganizationCode()
	{
		return this.organizationCode;
	}

	public void setOrganizationCode(String organizationCode)
	{
		this.organizationCode = organizationCode;
	}

	@Column(name="BUSINESS_REGISTRATION")
	public String getBusinessRegistration()
	{
		return this.businessRegistration;
	}

	public void setBusinessRegistration(String businessRegistration)
	{
		this.businessRegistration = businessRegistration;
	}

	@Column(name="PUNISH_NAME")
	public String getPunishName()
	{
		return this.punishName;
	}

	public void setPunishName(String punishName)
	{
		this.punishName = punishName;
	}

	@Column(name="SYMBOL_DECISION")
	public String getSymbolDecision()
	{
		return this.symbolDecision;
	}

	public void setSymbolDecision(String symbolDecision)
	{
		this.symbolDecision = symbolDecision;
	}

	@Column(name="PUNISHED_PARTY")
	public String getPunishedParty()
	{
		return this.punishedParty;
	}

	public void setPunishedParty(String punishedParty)
	{
		this.punishedParty = punishedParty;
	}

	@Column(name="PUNISHED_NUMBER")
	public String getPunishedNumber()
	{
		return this.punishedNumber;
	}

	public void setPunishedNumber(String punishedNumber)
	{
		this.punishedNumber = punishedNumber;
	}

	@Column(name="PUNISHED_SUBJECT")
	public String getPunishedSubject()
	{
		return this.punishedSubject;
	}

	public void setPunishedSubject(String punishedSubject)
	{
		this.punishedSubject = punishedSubject;
	}

	@Column(name="PUNISHED_SPECIES")
	public String getPunishedSpecies()
	{
		return this.punishedSpecies;
	}

	public void setPunishedSpecies(String punishedSpecies)
	{
		this.punishedSpecies = punishedSpecies;
	}

	@Column(name="PUNISHED_BASIS")
	public String getPunishedBasis()
	{
		return this.punishedBasis;
	}

	public void setPunishedBasis(String punishedBasis)
	{
		this.punishedBasis = punishedBasis;
	}

	@Column(name="PUNISHED_CONCLUSION")
	public String getPunishedConclusion()
	{
		return this.punishedConclusion;
	}

	public void setPunishedConclusion(String punishedConclusion)
	{
		this.punishedConclusion = punishedConclusion;
	}

	@Column(name="ILLEGAL_INCOME")
	public String getIllegalIncome()
	{
		return this.illegalIncome;
	}

	public void setIllegalIncome(String illegalIncome)
	{
		this.illegalIncome = illegalIncome;
	}

	@Column(name="FINES")
	public String getFines()
	{
		return this.fines;
	}

	public void setFines(String fines)
	{
		this.fines = fines;
	}

	@Column(name="CREDIT_RATING")
	public String getCreditRating()
	{
		return this.creditRating;
	}

	public void setCreditRating(String creditRating)
	{
		this.creditRating = creditRating;
	}

	@Column(name="PENALIZING_ORGAN")
	public String getPenalizingOrgan()
	{
		return this.penalizingOrgan;
	}

	public void setPenalizingOrgan(String penalizingOrgan)
	{
		this.penalizingOrgan = penalizingOrgan;
	}

	@Column(name="PENALIZING_DATE")
	public Date getPenalizingDate()
	{
		return this.penalizingDate;
	}

	public void setPenalizingDate(Date penalizingDate)
	{
		this.penalizingDate = penalizingDate;
	}

	@Column(name="IS_PUBLIC")
	public String getIsPublic()
	{
		return this.isPublic;
	}

	public void setIsPublic(String isPublic)
	{
		this.isPublic = isPublic;
	}

	@Column(name="PUBLIC_START_DATE")
	public Date getPublicStartDate()
	{
		return this.publicStartDate;
	}

	public void setPublicStartDate(Date publicStartDate)
	{
		this.publicStartDate = publicStartDate;
	}

	@Column(name="PUBLIC_END_DATE")
	public Date getPublicEndDate()
	{
		return this.publicEndDate;
	}

	public void setPublicEndDate(Date publicEndDate)
	{
		this.publicEndDate = publicEndDate;
	}

	@Column(name="FINISH_TIME")
	public Date getFinishTime()
	{
		return this.finishTime;
	}

	public void setFinishTime(Date finishTime)
	{
		this.finishTime = finishTime;
	}

	@Column(name="IMPLEMENTATION")
	public String getImplementation()
	{
		return this.implementation;
	}

	public void setImplementation(String implementation)
	{
		this.implementation = implementation;
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
	@Column(name="CHECK_STATUS")
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

}
