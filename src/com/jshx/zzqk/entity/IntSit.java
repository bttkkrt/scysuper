package com.jshx.zzqk.entity;

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
@Table(name="INT_SIT")
public class IntSit extends BaseModel
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
	 * 所在区域
	 */
	private String areaId;

	/**
	 * 所在区域
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
	 * 证书编号
	 */
	private String intelligenceCardnum;

	/**
	 * 证书名称
	 */
	private String intelligenceCardname;

	/**
	 * 资质类型
	 */
	private String intelligenceType;

	/**
	 * 资质内容
	 */
	private String intelligenceContent;

	/**
	 * 发证机关
	 */
	private String intelligenceInstitution;

	/**
	 * 发证日期
	 */
	private Date intelligenceCardDate;

	/**
	 * 有效期起始日期
	 */
	private Date intelligenceValidityStart;

	/**
	 * 备注
	 */
	private String intelligenceRemark;

	/**
	 * 附件关联id
	 */
	private String linkId;

	/**
	 * 有效期截止日期
	 */
	private Date intelligenceValidityEnd;
	/**
	 *资质级别 
	 **/
	private String zzjb;

	/**
	 *变更日期
	 **/
	private Date changeDate;
	public IntSit(){
	}
	/**
	 * 审核人
	 */
	private String auditPerson;

	/**
	 * 审核结果
	 */
	private String auditResult;
	
	/**
	 * 审核状态
	 */
	private String auditState;
	
	/**
	 * 审核备注
	 */
	private String checkRemark;
	
	/**
	 * 业务范围
	 */
	private String bussinessScope;
	
	
	
	public IntSit(String id, String areaName, String companyName, String intelligenceCardnum, String intelligenceCardname, String intelligenceType,String auditResult,String auditState,String createUserID){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.intelligenceCardnum = intelligenceCardnum;

this.intelligenceCardname = intelligenceCardname;

this.intelligenceType = intelligenceType;
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

	@Column(name="INTELLIGENCE_CARDNUM")
	public String getIntelligenceCardnum()
	{
		return this.intelligenceCardnum;
	}

	public void setIntelligenceCardnum(String intelligenceCardnum)
	{
		this.intelligenceCardnum = intelligenceCardnum;
	}

	@Column(name="INTELLIGENCE_CARDNAME")
	public String getIntelligenceCardname()
	{
		return this.intelligenceCardname;
	}

	public void setIntelligenceCardname(String intelligenceCardname)
	{
		this.intelligenceCardname = intelligenceCardname;
	}

	@Column(name="INTELLIGENCE_TYPE")
	public String getIntelligenceType()
	{
		return this.intelligenceType;
	}

	public void setIntelligenceType(String intelligenceType)
	{
		this.intelligenceType = intelligenceType;
	}

	@Column(name="INTELLIGENCE_CONTENT")
	public String getIntelligenceContent()
	{
		return this.intelligenceContent;
	}

	public void setIntelligenceContent(String intelligenceContent)
	{
		this.intelligenceContent = intelligenceContent;
	}

	@Column(name="INTELLIGENCE_INSTITUTION")
	public String getIntelligenceInstitution()
	{
		return this.intelligenceInstitution;
	}

	public void setIntelligenceInstitution(String intelligenceInstitution)
	{
		this.intelligenceInstitution = intelligenceInstitution;
	}

	@Column(name="INTELLIGENCE_CARD_DATE")
	public Date getIntelligenceCardDate()
	{
		return this.intelligenceCardDate;
	}

	public void setIntelligenceCardDate(Date intelligenceCardDate)
	{
		this.intelligenceCardDate = intelligenceCardDate;
	}

	@Column(name="INTELLIGENCE_VALIDITY_START")
	public Date getIntelligenceValidityStart()
	{
		return this.intelligenceValidityStart;
	}

	public void setIntelligenceValidityStart(Date intelligenceValidityStart)
	{
		this.intelligenceValidityStart = intelligenceValidityStart;
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

	@Column(name="INTELLIGENCE_VALIDITY_END")
	public Date getIntelligenceValidityEnd()
	{
		return this.intelligenceValidityEnd;
	}

	public void setIntelligenceValidityEnd(Date intelligenceValidityEnd)
	{
		this.intelligenceValidityEnd = intelligenceValidityEnd;
	}

	public void setZzjb(String zzjb) {
		this.zzjb = zzjb;
	}
	@Column(name="ZZJB")
	public String getZzjb() {
		return zzjb;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	@Column(name="CHANGE_DATE")
	public Date getChangeDate() {
		return changeDate;
	}
	@Column(name="AUDIT_PERSON")
	public String getAuditPerson()
	{
		return this.auditPerson;
	}

	public void setAuditPerson(String auditPerson)
	{
		this.auditPerson = auditPerson;
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

	@Column(name="BUSSINESS_SCOPE")
	public String getBussinessScope() {
		return bussinessScope;
	}


	public void setBussinessScope(String bussinessScope) {
		this.bussinessScope = bussinessScope;
	}

}
