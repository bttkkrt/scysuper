package com.jshx.xzcfgzs.entity;

//import java.beans.Transient;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PENALTY_NOTICE")
public class PenaltyNotice extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 规定
	 */
	private String provision;
	
	private String proName;

	/**
	 * 执法依据
	 */
	private String lawBasic;
	
	private String lawName;

	/**
	 * 行政处罚
	 */
	private String adminPenality;
	
	/**
	 * 违法行为
	 */
	private String wfxw;

	
	private String contact;
	
	private String tele;
	
	/**
	 * 承办人意见
	 */
	private String undertakerComment;

	/**
	 * 承办人
	 */
	private String undertaker;
	
	/**
	 * 承办时间
	 */
	private Date underTime;

	/**
	 * 部门负责人
	 */
	private String departPerson;

	/**
	 * 部门负责人意见
	 */
	private String departComment;
	
	/**
	 * 部门负责人处理时间
	 */
	private Date departTime;

	/**
	 * 机关负责人
	 */
	private String officePerson;

	/**
	 * 机关负责人意见
	 */
	private String officeComment;
	
	/**
	 * 机关负责人处理时间
	 */
	private Date officeTime;
	
	/**
	 * 案件基本情况
	 * @return
	 */
	private String caseCondition;
	
	private String iftzgz;


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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="PROVISION")
	public String getProvision()
	{
		return this.provision;
	}

	public void setProvision(String provision)
	{
		this.provision = provision;
	}

	@Column(name="LAW_BASIC")
	public String getLawBasic()
	{
		return this.lawBasic;
	}

	public void setLawBasic(String lawBasic)
	{
		this.lawBasic = lawBasic;
	}

	@Column(name="ADMIN_PENALITY")
	public String getAdminPenality()
	{
		return this.adminPenality;
	}

	public void setAdminPenality(String adminPenality)
	{
		this.adminPenality = adminPenality;
	}
	@Column(name="WFXW")
	public String getWfxw() {
		return wfxw;
	}

	public void setWfxw(String wfxw) {
		this.wfxw = wfxw;
	}
	@Column(name="CONTACT")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name="TELE")
	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
	@Column(name="PRO_NAME")
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	@Column(name="LAW_NAME")
	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
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

	@Column(name="UNDERTAKER")
	public String getUndertaker()
	{
		return this.undertaker;
	}

	public void setUndertaker(String undertaker)
	{
		this.undertaker = undertaker;
	}

	@Column(name="DEPART_PERSON")
	public String getDepartPerson()
	{
		return this.departPerson;
	}

	public void setDepartPerson(String departPerson)
	{
		this.departPerson = departPerson;
	}

	@Column(name="DEPART_COMMENT")
	public String getDepartComment()
	{
		return this.departComment;
	}

	public void setDepartComment(String departComment)
	{
		this.departComment = departComment;
	}

	@Column(name="OFFICE_PERSON")
	public String getOfficePerson()
	{
		return this.officePerson;
	}

	public void setOfficePerson(String officePerson)
	{
		this.officePerson = officePerson;
	}

	@Column(name="OFFICE_COMMENT")
	public String getOfficeComment()
	{
		return this.officeComment;
	}

	public void setOfficeComment(String officeComment)
	{
		this.officeComment = officeComment;
	}
	@Column(name="UNDER_TIME")
	public Date getUnderTime() {
		return underTime;
	}

	public void setUnderTime(Date underTime) {
		this.underTime = underTime;
	}
	@Column(name="DEPART_TIME")
	public Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}
	@Column(name="OFFICE_TIME")
	public Date getOfficeTime() {
		return officeTime;
	}

	public void setOfficeTime(Date officeTime) {
		this.officeTime = officeTime;
	}
	@Column
	public String getCaseCondition() {
		return caseCondition;
	}

	public void setCaseCondition(String caseCondition) {
		this.caseCondition = caseCondition;
	}
	@Transient
	public String getIftzgz() {
		return iftzgz;
	}

	public void setIftzgz(String iftzgz) {
		this.iftzgz = iftzgz;
	}

}
