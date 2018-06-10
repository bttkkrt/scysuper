package com.jshx.whpxzxk.entity;

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
@Table(name="CHE_MAN_LIC")
public class CheManLic extends BaseModel
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
	 * 评价机构
	 */
	private String ratingAgencies;

	/**
	 * 经营方式
	 */
	private String operateWay;

	/**
	 * 经营类型
	 */
	private String operateType;

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
	 * 许可证有效截至日期
	 */
	private Date licenseValidEnd;

	/**
	 * 发证单位
	 */
	private String lssuingUnit;

	/**
	 * 材料接收人员
	 */
	private String receivePerson;

	/**
	 * 材料审查人员
	 */
	private String checkPerson;

	/**
	 * 档案编号
	 */
	private String archivesNo;

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
	 * 街道预审意见
	 */
	private String streetComment;

	/**
	 * 现场核查部门
	 */
	private String checkDepart;

	/**
	 * 核查结论
	 */
	private String checkConclusion;

	/**
	 * 是否储存涉及
	 */
	private String storageCondition;

	/**
	 * 材料审查情况
	 */
	private String materialCondition;

	/**
	 * 行政许可建议
	 */
	private String adminSuggest;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 审核状态
	 */
	private String auditState;
	
	/**
	 * 许可证编号
	 */
	private String xkzbh;
	/**
	 * 发证日期
	 */
	private Date fzrq;

	/**
	 * 经营范围
	 */
	private String jyfw;
	
	public CheManLic(){
	}
	
	public CheManLic(String id, String areaName, String companyName, String ratingAgencies, Date receiveDate, Date dealDate, Date licenseValid, String receivePerson, String checkPerson,String createUserID,String auditState){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.ratingAgencies = ratingAgencies;

this.receiveDate = receiveDate;

this.dealDate = dealDate;

this.licenseValid = licenseValid;

this.receivePerson = receivePerson;

this.checkPerson = checkPerson;
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

	@Column(name="RATING_AGENCIES")
	public String getRatingAgencies()
	{
		return this.ratingAgencies;
	}

	public void setRatingAgencies(String ratingAgencies)
	{
		this.ratingAgencies = ratingAgencies;
	}

	@Column(name="OPERATE_WAY")
	public String getOperateWay()
	{
		return this.operateWay;
	}

	public void setOperateWay(String operateWay)
	{
		this.operateWay = operateWay;
	}

	@Column(name="OPERATE_TYPE")
	public String getOperateType()
	{
		return this.operateType;
	}

	public void setOperateType(String operateType)
	{
		this.operateType = operateType;
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

	@Column(name="RECEIVE_PERSON")
	public String getReceivePerson()
	{
		return this.receivePerson;
	}

	public void setReceivePerson(String receivePerson)
	{
		this.receivePerson = receivePerson;
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

	@Column(name="ARCHIVES_NO")
	public String getArchivesNo()
	{
		return this.archivesNo;
	}

	public void setArchivesNo(String archivesNo)
	{
		this.archivesNo = archivesNo;
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

	@Column(name="STREET_COMMENT")
	public String getStreetComment()
	{
		return this.streetComment;
	}

	public void setStreetComment(String streetComment)
	{
		this.streetComment = streetComment;
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

	@Column(name="STORAGE_CONDITION")
	public String getStorageCondition()
	{
		return this.storageCondition;
	}

	public void setStorageCondition(String storageCondition)
	{
		this.storageCondition = storageCondition;
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
	@Column(name="XKZBH")
	public String getXkzbh() {
		return xkzbh;
	}

	public void setXkzbh(String xkzbh) {
		this.xkzbh = xkzbh;
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
