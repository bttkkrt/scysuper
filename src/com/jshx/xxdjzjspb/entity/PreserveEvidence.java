package com.jshx.xxdjzjspb.entity;

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
@Table(name="PRESERVE_EVIDENCE")
public class PreserveEvidence extends BaseModel
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
	 * 提请理由及依据
	 */
	private String reasonBasic;

	/**
	 * 保存方式
	 */
	private String preserveMethod;

	/**
	 * 承办人
	 */
	private String undertaker;

	/**
	 * 承办人意见
	 */
	private String undertakerComment;
	
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
	 * 承办人
	 */
	private String undertakerName;
	
	/**
	 * 承办人
	 */
	private String undertakerName1;
	

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

	@Column(name="REASON_BASIC")
	public String getReasonBasic()
	{
		return this.reasonBasic;
	}

	public void setReasonBasic(String reasonBasic)
	{
		this.reasonBasic = reasonBasic;
	}

	@Column(name="PRESERVE_METHOD")
	public String getPreserveMethod()
	{
		return this.preserveMethod;
	}

	public void setPreserveMethod(String preserveMethod)
	{
		this.preserveMethod = preserveMethod;
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
	
	@Column(name="UNDERTAKER_NAME")
	public String getUndertakerName()
	{
		return this.undertakerName;
	}

	public void setUndertakerName(String undertakerName)
	{
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
