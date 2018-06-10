package com.jshx.tztzs.entity;

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
@Table(name="HEARING_NOTICE")
public class HearingNotice extends BaseModel
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
	 * 听证会时间
	 */
	private Date hearingTime;

	/**
	 * 听证地点
	 */
	private String hearingAddress;

	/**
	 * 是否公开
	 */
	private String publicCondition;

	/**
	 * 听证主持人
	 */
	private String hearingChairperson;

	/**
	 * 听证员
	 */
	private String hearingOfficer;

	/**
	 * 书记员
	 */
	private String clerk;
	
	
	private String contact;
	
	private String tele;
	
	private String hearingChairpersonName;

	/**
	 * 听证员
	 */
	private String hearingOfficerName;

	/**
	 * 书记员
	 */
	private String clerkName;
	
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

	@Column(name="HEARING_TIME")
	public Date getHearingTime()
	{
		return this.hearingTime;
	}

	public void setHearingTime(Date hearingTime)
	{
		this.hearingTime = hearingTime;
	}

	@Column(name="HEARING_ADDRESS")
	public String getHearingAddress()
	{
		return this.hearingAddress;
	}

	public void setHearingAddress(String hearingAddress)
	{
		this.hearingAddress = hearingAddress;
	}

	@Column(name="PUBLIC_CONDITION")
	public String getPublicCondition()
	{
		return this.publicCondition;
	}

	public void setPublicCondition(String publicCondition)
	{
		this.publicCondition = publicCondition;
	}

	@Column(name="HEARING_CHAIRPERSON")
	public String getHearingChairperson()
	{
		return this.hearingChairperson;
	}

	public void setHearingChairperson(String hearingChairperson)
	{
		this.hearingChairperson = hearingChairperson;
	}

	@Column(name="HEARING_OFFICER")
	public String getHearingOfficer()
	{
		return this.hearingOfficer;
	}

	public void setHearingOfficer(String hearingOfficer)
	{
		this.hearingOfficer = hearingOfficer;
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
	@Column(name="CLERK")
	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}
	@Column
	public String getHearingChairpersonName() {
		return hearingChairpersonName;
	}

	public void setHearingChairpersonName(String hearingChairpersonName) {
		this.hearingChairpersonName = hearingChairpersonName;
	}
	@Column
	public String getHearingOfficerName() {
		return hearingOfficerName;
	}

	public void setHearingOfficerName(String hearingOfficerName) {
		this.hearingOfficerName = hearingOfficerName;
	}
	@Column
	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

}
