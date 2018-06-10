package com.jshx.ajyssp.entity;

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
@Table(name="CASE_REFER")
public class CaseRefer extends BaseModel
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
	 * 受移送机关
	 */
	private String transferAuthority;

	/**
	 * 移送理由
	 */
	private String feedingGrounds;
	
	private String undertaker;

	/**
	 * 承办人意见
	 */
	private String undertakerComment;

	
	private Date underTime;

	/**
	 * 部门负责人
	 */
	private String departPerson;

	/**
	 * 部门负责人审核意见
	 */
	private String departComment;
	
	private Date departTime;

	/**
	 * 机关负责人
	 */
	private String officePerson;

	/**
	 * 机关负责人审核意见
	 */
	private String officeComment;
	
	private Date officeTime;
	
	private String undertakerName;
	
	private String undertakerName1;


	
	public CaseRefer(){
	}
	
	public CaseRefer(String id){
this.id = id;
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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="TRANSFER_AUTHORITY")
	public String getTransferAuthority()
	{
		return this.transferAuthority;
	}

	public void setTransferAuthority(String transferAuthority)
	{
		this.transferAuthority = transferAuthority;
	}

	@Column(name="FEEDING_GROUNDS")
	public String getFeedingGrounds()
	{
		return this.feedingGrounds;
	}

	public void setFeedingGrounds(String feedingGrounds)
	{
		this.feedingGrounds = feedingGrounds;
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
	@Column(name="UNDERTAKER")
	public String getUndertaker() {
		return undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}
	@Column
	public String getUndertakerName() {
		return undertakerName;
	}

	public void setUndertakerName(String undertakerName) {
		this.undertakerName = undertakerName;
	}
	@Column
	public String getUndertakerName1() {
		return undertakerName1;
	}

	public void setUndertakerName1(String undertakerName1) {
		this.undertakerName1 = undertakerName1;
	}

}
