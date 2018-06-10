package com.jshx.yhbzxzxk.entity;

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
@Table(name="FIR_LIC")
public class FirLic extends BaseModel
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
	 * 档案号
	 */
	private String itemNo;

	/**
	 * 材料审查人员
	 */
	private String checkPerson;

	/**
	 * 材料接收人员
	 */
	private String receivePerson;

	/**
	 * 材料接收日期
	 */
	private Date receiveDate;

	/**
	 * 受理材料日期
	 */
	private Date dealDate;

	/**
	 * 许可证有效起始日期
	 */
	private Date licenseValid;
	
	/**
	 * 许可证有效截止日期
	 */
	private Date licenseValidEnd;
	
	/**
	 * 变更日期
	 */
	private Date bgrq;
	/**
	 * 仓库设施地址
	 */
	private String ckssdz;
	/**
	 * 发证单位
	 */
	private String lssuingUnit;

	/**
	 * 本次领证情况
	 */
	private String licenseCondition;

	/**
	 * 申请材料是否齐全
	 */
	private String applyCondition;

	/**
	 * 签字领导
	 */
	private String signLeader;

	/**
	 * 预审意见
	 */
	private String preComment;

	/**
	 * 现场检查部门
	 */
	private String checkDepart;

	/**
	 * 核查结论
	 */
	private String checkConclusion;

	/**
	 * 材料审查情况
	 */
	private String materialCondition;

	/**
	 * 行政许可建议
	 */
	private String adminSuggest;

	/**
	 * 局审会意见
	 */
	private String agencyComment;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 审核状态
	 */
	private String auditState;
	
	/**
	 * 证书编号
	 */
	private String zsbh;
	/**
	 * 发证日期
	 */
	private Date fzrq;
	
	/**
	 * 经营范围
	 */
	private String jyfw;
	
	public FirLic(){
	}
	
	public FirLic(String id, String areaName, String companyName, String itemNo, String checkPerson, String receivePerson, Date receiveDate, Date dealDate, Date licenseValid,String createUserID,String auditState){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.itemNo = itemNo;

this.checkPerson = checkPerson;

this.receivePerson = receivePerson;

this.receiveDate = receiveDate;

this.dealDate = dealDate;

this.licenseValid = licenseValid;
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

	@Column(name="ITEM_NO")
	public String getItemNo()
	{
		return this.itemNo;
	}

	public void setItemNo(String itemNo)
	{
		this.itemNo = itemNo;
	}

	@Column(name="CHECK_PERSON")
	public String getCheckPerson()
	{
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson)
	{
		this.checkPerson = checkPerson;
	}

	@Column(name="RECEIVE_PERSON")
	public String getReceivePerson()
	{
		return this.receivePerson;
	}

	public void setReceivePerson(String receivePerson)
	{
		this.receivePerson = receivePerson;
	}

	@Column(name="RECEIVE_DATE")
	public Date getReceiveDate()
	{
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate)
	{
		this.receiveDate = receiveDate;
	}

	@Column(name="DEAL_DATE")
	public Date getDealDate()
	{
		return this.dealDate;
	}

	public void setDealDate(Date dealDate)
	{
		this.dealDate = dealDate;
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

	@Column(name="LSSUING_UNIT")
	public String getLssuingUnit()
	{
		return this.lssuingUnit;
	}

	public void setLssuingUnit(String lssuingUnit)
	{
		this.lssuingUnit = lssuingUnit;
	}

	@Column(name="LICENSE_CONDITION")
	public String getLicenseCondition()
	{
		return this.licenseCondition;
	}

	public void setLicenseCondition(String licenseCondition)
	{
		this.licenseCondition = licenseCondition;
	}

	@Column(name="APPLY_CONDITION")
	public String getApplyCondition()
	{
		return this.applyCondition;
	}

	public void setApplyCondition(String applyCondition)
	{
		this.applyCondition = applyCondition;
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

	@Column(name="PRE_COMMENT")
	public String getPreComment()
	{
		return this.preComment;
	}

	public void setPreComment(String preComment)
	{
		this.preComment = preComment;
	}

	@Column(name="CHECK_DEPART")
	public String getCheckDepart()
	{
		return this.checkDepart;
	}

	public void setCheckDepart(String checkDepart)
	{
		this.checkDepart = checkDepart;
	}

	@Column(name="CHECK_CONCLUSION")
	public String getCheckConclusion()
	{
		return this.checkConclusion;
	}

	public void setCheckConclusion(String checkConclusion)
	{
		this.checkConclusion = checkConclusion;
	}

	@Column(name="MATERIAL_CONDITION")
	public String getMaterialCondition()
	{
		return this.materialCondition;
	}

	public void setMaterialCondition(String materialCondition)
	{
		this.materialCondition = materialCondition;
	}

	@Column(name="ADMIN_SUGGEST")
	public String getAdminSuggest()
	{
		return this.adminSuggest;
	}

	public void setAdminSuggest(String adminSuggest)
	{
		this.adminSuggest = adminSuggest;
	}

	@Column(name="AGENCY_COMMENT")
	public String getAgencyComment()
	{
		return this.agencyComment;
	}

	public void setAgencyComment(String agencyComment)
	{
		this.agencyComment = agencyComment;
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
	@Column(name="ZSBH")
	public String getZsbh() {
		return zsbh;
	}

	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	@Column(name="LICENSE_VALID_END")
	public Date getLicenseValidEnd() {
		return licenseValidEnd;
	}

	public void setLicenseValidEnd(Date licenseValidEnd) {
		this.licenseValidEnd = licenseValidEnd;
	}
	@Column(name="BGRQ")
	public Date getBgrq() {
		return bgrq;
	}

	public void setBgrq(Date bgrq) {
		this.bgrq = bgrq;
	}
	@Column(name="CKSSDZ")
	public String getCkssdz() {
		return ckssdz;
	}

	public void setCkssdz(String ckssdz) {
		this.ckssdz = ckssdz;
	}
	@Column(name="FZRQ")
	public Date getFzrq() {
		return fzrq;
	}

	public void setFzrq(Date fzrq) {
		this.fzrq = fzrq;
	}
	@Column(name="JYFW")
	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
}
