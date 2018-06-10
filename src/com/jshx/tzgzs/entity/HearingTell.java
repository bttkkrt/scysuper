package com.jshx.tzgzs.entity;

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
@Table(name="HEARING_TELL")
public class HearingTell extends BaseModel
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


	private String contact;
	
	private String tele;
	
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
	@Column(name="UNDER_TIME")
	public Date getUnderTime() {
		return underTime;
	}

	public void setUnderTime(Date underTime) {
		this.underTime = underTime;
	}
}
