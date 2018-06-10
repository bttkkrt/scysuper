package com.jshx.rybzxx.entity;

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
@Table(name="HON_REC")
public class HonRec extends BaseModel
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
	 * 批准文号
	 */
	private String approvalNumber;

	/**
	 * 表彰内容
	 */
	private String recognitionContent;

	/**
	 * 荣誉称号
	 */
	private String honor;

	/**
	 * 地区
	 */
	private String area;

	/**
	 * 年度
	 */
	private Date bzyear;

	/**
	 * 表彰日期
	 */
	private String recognitionDate;

	/**
	 * 表彰部门
	 */
	private String recognitionDept;

	/**
	 * 审核人id
	 */
	private String checkUserId;

	/**
	 * 审核人姓名
	 */
	private String checkUserName;

	/**
	 * 审核结果
	 */
	private String auditResult;
	/**
	 * 审核状态 待审核 审核通过  审核未通过 待提交
	 */
	private String auditState;
	/**
	 * 审核备注
	 */
	private String checkRemark;

	/**
	 * 备注
	 */
	private String intelligenceRemark;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public HonRec(){
	}
	
	public HonRec(String id, String areaName, String companyName, String honor, String recognitionDept, Date bzyear,String auditResult,String auditState,String createUserID){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.honor = honor;

this.recognitionDept = recognitionDept;

this.bzyear = bzyear;
this.auditResult = auditResult;

this.auditState=auditState;

this.createUserID = createUserID;
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

	@Column(name="APPROVAL_NUMBER")
	public String getApprovalNumber()
	{
		return this.approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber)
	{
		this.approvalNumber = approvalNumber;
	}

	@Column(name="RECOGNITION_CONTENT")
	public String getRecognitionContent()
	{
		return this.recognitionContent;
	}

	public void setRecognitionContent(String recognitionContent)
	{
		this.recognitionContent = recognitionContent;
	}

	@Column(name="HONOR")
	public String getHonor()
	{
		return this.honor;
	}

	public void setHonor(String honor)
	{
		this.honor = honor;
	}

	@Column(name="AREA")
	public String getArea()
	{
		return this.area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	@Column(name="BZYEAR")
	public Date getBzyear()
	{
		return this.bzyear;
	}

	public void setBzyear(Date bzyear)
	{
		this.bzyear = bzyear;
	}

	@Column(name="RECOGNITION_DATE")
	public String getRecognitionDate()
	{
		return this.recognitionDate;
	}

	public void setRecognitionDate(String recognitionDate)
	{
		this.recognitionDate = recognitionDate;
	}

	@Column(name="RECOGNITION_DEPT")
	public String getRecognitionDept()
	{
		return this.recognitionDept;
	}

	public void setRecognitionDept(String recognitionDept)
	{
		this.recognitionDept = recognitionDept;
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



	@Column(name="INTELLIGENCE_REMARK")
	public String getIntelligenceRemark()
	{
		return this.intelligenceRemark;
	}

	public void setIntelligenceRemark(String intelligenceRemark)
	{
		this.intelligenceRemark = intelligenceRemark;
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

	@Column(name="AUDIT_RESULT")
	public String getAuditResult()
	{
		return this.auditResult;
	}

	public void setAuditResult(String auditResult)
	{
		this.auditResult = auditResult;
	}
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
	@Column(name="CHECK_REMARK")
	public String getCheckRemark() {
		return checkRemark;
	}

}
