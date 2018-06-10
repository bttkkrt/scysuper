package com.jshx.kcbl.entity;

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
@Table(name="INQUEST_RECORD")
public class InquestRecord extends BaseModel
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
	 * 勘验开始时间
	 */
	private Date startTime;

	/**
	 * 勘验结束时间
	 */
	private Date endTime;

	/**
	 * 勘验场所
	 */
	private String inquestAddress;

	/**
	 * 天气情况
	 */
	private String weatherCondition;

	/**
	 * 当事人1
	 */
	private String party1;

	/**
	 * 当事人1单位及职务
	 */
	private String party1Company;
	
	/**
	 * 当事人1联系方式
	 */
	private String party1Tel;

	/**
	 * 当事人2
	 */
	private String party2;

	/**
	 * 当事人2单位及职务
	 */
	private String party2Company;
	
	/**
	 * 当事人2联系方式
	 */
	private String party2Tel;

	/**
	 * 被邀请人
	 */
	private String invitee;

	/**
	 * 被邀请人单位及职务
	 */
	private String inviteeCompany;
	
	/**
	 * 记录人联系方式
	 */
	private String inviteeTel;

	/**
	 * 记录人
	 */
	private String recordPerson;

	/**
	 * 记录人单位及职务
	 */
	private String recordCompany;
	

	/**
	 * 勘验情况
	 */
	private String inquestCondition;

	/**
	 * 勘验人
	 */
	private String inquestPerson;
	
	/**
	 * 记录人
	 */
	private String recordPersonName;
	
	
	/**
	 * 勘验人
	 */
	private String inquestPersonName;
	
	
	
	

	public InquestRecord(){
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

	@Column(name="START_TIME")
	public Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
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

	@Column(name="INQUEST_ADDRESS")
	public String getInquestAddress()
	{
		return this.inquestAddress;
	}

	public void setInquestAddress(String inquestAddress)
	{
		this.inquestAddress = inquestAddress;
	}

	@Column(name="WEATHER_CONDITION")
	public String getWeatherCondition()
	{
		return this.weatherCondition;
	}

	public void setWeatherCondition(String weatherCondition)
	{
		this.weatherCondition = weatherCondition;
	}

	@Column(name="PARTY1")
	public String getParty1()
	{
		return this.party1;
	}

	public void setParty1(String party1)
	{
		this.party1 = party1;
	}

	@Column(name="PARTY1_COMPANY")
	public String getParty1Company()
	{
		return this.party1Company;
	}

	public void setParty1Company(String party1Company)
	{
		this.party1Company = party1Company;
	}

	@Column(name="PARTY2")
	public String getParty2()
	{
		return this.party2;
	}

	public void setParty2(String party2)
	{
		this.party2 = party2;
	}

	@Column(name="PARTY2_COMPANY")
	public String getParty2Company()
	{
		return this.party2Company;
	}

	public void setParty2Company(String party2Company)
	{
		this.party2Company = party2Company;
	}

	@Column(name="INVITEE")
	public String getInvitee()
	{
		return this.invitee;
	}

	public void setInvitee(String invitee)
	{
		this.invitee = invitee;
	}

	@Column(name="INVITEE_COMPANY")
	public String getInviteeCompany()
	{
		return this.inviteeCompany;
	}

	public void setInviteeCompany(String inviteeCompany)
	{
		this.inviteeCompany = inviteeCompany;
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
	
	@Column(name="RECORD_PERSON_NAME")
	public String getRecordPersonName()
	{
		return this.recordPersonName;
	}

	public void setRecordPersonName(String recordPersonName)
	{
		this.recordPersonName = recordPersonName;
	}

	@Column(name="RECORD_COMPANY")
	public String getRecordCompany()
	{
		return this.recordCompany;
	}

	public void setRecordCompany(String recordCompany)
	{
		this.recordCompany = recordCompany;
	}

	@Column(name="INQUEST_CONDITION")
	public String getInquestCondition()
	{
		return this.inquestCondition;
	}

	public void setInquestCondition(String inquestCondition)
	{
		this.inquestCondition = inquestCondition;
	}

	@Column(name="INQUEST_PERSON")
	public String getInquestPerson()
	{
		return this.inquestPerson;
	}

	public void setInquestPerson(String inquestPerson)
	{
		this.inquestPerson = inquestPerson;
	}
	
	@Column(name="INQUEST_PERSON_NAME")
	public String getInquestPersonName()
	{
		return this.inquestPersonName;
	}

	public void setInquestPersonName(String inquestPersonName)
	{
		this.inquestPersonName = inquestPersonName;
	}
	
	@Column(name="PARTY1_TEL")
	public String getParty1Tel() {
		return party1Tel;
	}

	public void setParty1Tel(String party1Tel) {
		this.party1Tel = party1Tel;
	}
	@Column(name="PARTY2_TEL")
	public String getParty2Tel() {
		return party2Tel;
	}

	public void setParty2Tel(String party2Tel) {
		this.party2Tel = party2Tel;
	}
	@Column(name="INVITEE_TEL")
	public String getInviteeTel() {
		return inviteeTel;
	}

	public void setInviteeTel(String inviteeTel) {
		this.inviteeTel = inviteeTel;
	}

}
