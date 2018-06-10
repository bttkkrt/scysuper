package com.jshx.xwbl.entity;

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
@Table(name="INQUIRY_RECORD")
public class InquiryRecord extends BaseModel
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
	 * 询问开始时间
	 */
	private Date inquiryPeriod;

	/**
	 * 询问结束时间
	 */
	private Date endTime;
	
	/**
	 * 询问地点
	 */
	private String inquiryAddress;

	/**
	 * 被询问人姓名
	 */
	private String askedPerson;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 年龄
	 */
	private String peopleAge;

	/**
	 * 身份证号
	 */
	private String cardId;

	/**
	 * 职位
	 */
	private String position;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 电话
	 */
	private String tele;

	/**
	 * 在场人
	 */
	private String presentPeople;

	/**
	 * 询问人
	 */
	private String inquiryPerson;

	/**
	 * 记录人
	 */
	private String recordPerson;
	
	/**
	 * 询问人
	 */
	private String inquiryPersonName;

	/**
	 * 记录人
	 */
	private String recordPersonName;
	
	/**
	 * 询问人证号
	 */
	private String inquiryPersonZh;
	
	/**
	 * 记录人证号
	 */
	private String recordPersonZh;
	
	/**
	 * 询问次数
	 */
	private String xwcs;
	
	/**
	 * 工作单位
	 */
	private String companyName;
	
	
	public InquiryRecord(){
	}
	
	public InquiryRecord(String id, String relatedId, Date inquiryPeriod, Date endTime, String sex, String peopleAge){
this.id = id;

this.relatedId = relatedId;

this.inquiryPeriod = inquiryPeriod;

this.endTime = endTime;

this.sex = sex;

this.peopleAge = peopleAge;
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

	@Column(name="INQUIRY_PERIOD")
	public Date getInquiryPeriod()
	{
		return this.inquiryPeriod;
	}

	public void setInquiryPeriod(Date inquiryPeriod)
	{
		this.inquiryPeriod = inquiryPeriod;
	}

	@Column(name="END_TIME")
	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	@Column(name="ASKED_PERSON")
	public String getAskedPerson()
	{
		return this.askedPerson;
	}

	public void setAskedPerson(String askedPerson)
	{
		this.askedPerson = askedPerson;
	}

	@Column(name="SEX")
	public String getSex()
	{
		return this.sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	@Column(name="PEOPLE_AGE")
	public String getPeopleAge()
	{
		return this.peopleAge;
	}

	public void setPeopleAge(String peopleAge)
	{
		this.peopleAge = peopleAge;
	}

	@Column(name="CARD_ID")
	public String getCardId()
	{
		return this.cardId;
	}

	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}

	@Column(name="POSITION")
	public String getPosition()
	{
		return this.position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	@Column(name="ADDRESS")
	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name="TELE")
	public String getTele()
	{
		return this.tele;
	}

	public void setTele(String tele)
	{
		this.tele = tele;
	}

	@Column(name="PRESENT_PEOPLE")
	public String getPresentPeople()
	{
		return this.presentPeople;
	}

	public void setPresentPeople(String presentPeople)
	{
		this.presentPeople = presentPeople;
	}


	@Column(name="INQUIRY_PERSON")
	public String getInquiryPerson()
	{
		return this.inquiryPerson;
	}

	public void setInquiryPerson(String inquiryPerson)
	{
		this.inquiryPerson = inquiryPerson;
	}

	@Column(name="RECORD_PERSON")
	public String getRecordPerson()
	{
		return this.recordPerson;
	}

	public void setRecordPerson(String recordPerson)
	{
		this.recordPerson = recordPerson;
	}
	@Column(name="XWCS")
	public String getXwcs() {
		return xwcs;
	}

	public void setXwcs(String xwcs) {
		this.xwcs = xwcs;
	}
	@Column(name="INQUIRY_ADDRESS")
	public String getInquiryAddress() {
		return inquiryAddress;
	}

	public void setInquiryAddress(String inquiryAddress) {
		this.inquiryAddress = inquiryAddress;
	}
	@Column(name="COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name="INQUIRY_PERSON_NAME")
	public String getInquiryPersonName()
	{
		return this.inquiryPersonName;
	}

	public void setInquiryPersonName(String inquiryPersonName)
	{
		this.inquiryPersonName = inquiryPersonName;
	}

	@Column(name="RECORD_PERSON_NAME")
	public String getRecordPersonName()
	{
		return this.recordPersonName;
	}

	public void setRecordPersonName(String recordPersonName)
	{
		this.recordPersonName = recordPersonName;
	}
	@Column(name="INQUIRY_PERSON_ZH")
	public String getInquiryPersonZh() {
		return inquiryPersonZh;
	}

	public void setInquiryPersonZh(String inquiryPersonZh) {
		this.inquiryPersonZh = inquiryPersonZh;
	}
	@Column(name="RECORD_PERSON_ZH")
	public String getRecordPersonZh() {
		return recordPersonZh;
	}

	public void setRecordPersonZh(String recordPersonZh) {
		this.recordPersonZh = recordPersonZh;
	}
	
}
