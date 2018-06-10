package com.jshx.cheprolicense.entity;

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
@Table(name="CHE_PRO_LIC")
public class CheProLic extends BaseModel
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
	 * 评价机构id
	 */
	private String ratingAgenciesId;

	/**
	 * 评价机构名称
	 */
	private String ratingAgenciesName;

	/**
	 * 生产证或使用证
	 */
	private String productionPermit;

	/**
	 * 许可证编号
	 */
	private String licenseNumber;

	/**
	 * 许可证有效期起始时间
	 */
	private Date licenseValid;
	
	/**
	 * 许可证有效期截至时间
	 */
	private Date licenseValidEnd;

	/**
	 * 发证机关
	 */
	private String issuingAuthority;

	/**
	 * 发证日期
	 */
	private Date issuingDate;

	/**
	 * 材料上报市局日期
	 */
	private Date submitDate;

	/**
	 * 材料接收日期
	 */
	private Date receptDate;

	/**
	 * 材料接收人员
	 */
	private String receptName;

	/**
	 * 材料审查人员
	 */
	private String reviewName;

	/**
	 * 档案编号
	 */
	private String fileNo;

	/**
	 * 申请材料是否齐全
	 */
	private String isComplete;

	/**
	 * 现场核查部门
	 */
	private String checkDept;

	/**
	 * 材料审查情况
	 */
	private String checkResult;

	/**
	 * 本次领证情况
	 */
	private String licensingCondition;

	/**
	 * 行政许可建议
	 */
	private String proposal;

	/**
	 * 签字领导
	 */
	private String signLeader;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 审核状态
	 */
	private String auditState;

	/**
	 * 经营范围
	 */
	private String jyfw;
	
	public CheProLic(){
	}
	
	public CheProLic(String id, String areaName, String companyName, String ratingAgenciesName, String licenseNumber, String issuingAuthority, Date issuingDate, Date receptDate, String receptName, String reviewName, String fileNo, String isComplete,String createUserID,String auditState){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.ratingAgenciesName = ratingAgenciesName;

this.licenseNumber = licenseNumber;

this.issuingAuthority = issuingAuthority;

this.issuingDate = issuingDate;

this.receptDate = receptDate;

this.receptName = receptName;

this.reviewName = reviewName;

this.fileNo = fileNo;

this.isComplete = isComplete;
this.createUserID=createUserID;
this.auditState=auditState;
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

	@Column(name="RATING_AGENCIES_ID")
	public String getRatingAgenciesId()
	{
		return this.ratingAgenciesId;
	}

	public void setRatingAgenciesId(String ratingAgenciesId)
	{
		this.ratingAgenciesId = ratingAgenciesId;
	}

	@Column(name="RATING_AGENCIES_NAME")
	public String getRatingAgenciesName()
	{
		return this.ratingAgenciesName;
	}

	public void setRatingAgenciesName(String ratingAgenciesName)
	{
		this.ratingAgenciesName = ratingAgenciesName;
	}

	@Column(name="PRODUCTION_PERMIT")
	public String getProductionPermit()
	{
		return this.productionPermit;
	}

	public void setProductionPermit(String productionPermit)
	{
		this.productionPermit = productionPermit;
	}

	@Column(name="LICENSE_NUMBER")
	public String getLicenseNumber()
	{
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber)
	{
		this.licenseNumber = licenseNumber;
	}

	@Column(name="LICENSE_VALID")
	public Date getLicenseValid()
	{
		return this.licenseValid;
	}

	public void setLicenseValid(Date licenseValid)
	{
		this.licenseValid = licenseValid;
	}

	@Column(name="ISSUING_AUTHORITY")
	public String getIssuingAuthority()
	{
		return this.issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority)
	{
		this.issuingAuthority = issuingAuthority;
	}

	@Column(name="ISSUING_DATE")
	public Date getIssuingDate()
	{
		return this.issuingDate;
	}

	public void setIssuingDate(Date issuingDate)
	{
		this.issuingDate = issuingDate;
	}

	@Column(name="SUBMIT_DATE")
	public Date getSubmitDate()
	{
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate)
	{
		this.submitDate = submitDate;
	}

	@Column(name="RECEPT_DATE")
	public Date getReceptDate()
	{
		return this.receptDate;
	}

	public void setReceptDate(Date receptDate)
	{
		this.receptDate = receptDate;
	}

	@Column(name="RECEPT_NAME")
	public String getReceptName()
	{
		return this.receptName;
	}

	public void setReceptName(String receptName)
	{
		this.receptName = receptName;
	}

	@Column(name="REVIEW_NAME")
	public String getReviewName()
	{
		return this.reviewName;
	}

	public void setReviewName(String reviewName)
	{
		this.reviewName = reviewName;
	}

	@Column(name="FILE_NO")
	public String getFileNo()
	{
		return this.fileNo;
	}

	public void setFileNo(String fileNo)
	{
		this.fileNo = fileNo;
	}

	@Column(name="IS_COMPLETE")
	public String getIsComplete()
	{
		return this.isComplete;
	}

	public void setIsComplete(String isComplete)
	{
		this.isComplete = isComplete;
	}

	@Column(name="CHECK_DEPT")
	public String getCheckDept()
	{
		return this.checkDept;
	}

	public void setCheckDept(String checkDept)
	{
		this.checkDept = checkDept;
	}

	@Column(name="CHECK_RESULT")
	public String getCheckResult()
	{
		return this.checkResult;
	}

	public void setCheckResult(String checkResult)
	{
		this.checkResult = checkResult;
	}

	@Column(name="LICENSING_CONDITION")
	public String getLicensingCondition()
	{
		return this.licensingCondition;
	}

	public void setLicensingCondition(String licensingCondition)
	{
		this.licensingCondition = licensingCondition;
	}

	@Column(name="PROPOSAL")
	public String getProposal()
	{
		return this.proposal;
	}

	public void setProposal(String proposal)
	{
		this.proposal = proposal;
	}

	@Column(name="SIGN_LEADER")
	public String getSignLeader()
	{
		return this.signLeader;
	}

	public void setSignLeader(String signLeader)
	{
		this.signLeader = signLeader;
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
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	@Column(name="LICENSE_VALID_End")
	public Date getLicenseValidEnd() {
		return licenseValidEnd;
	}

	public void setLicenseValidEnd(Date licenseValidEnd) {
		this.licenseValidEnd = licenseValidEnd;
	}
	@Column(name="JYFW")
	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

}
