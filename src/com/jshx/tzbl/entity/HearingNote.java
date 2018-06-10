package com.jshx.tzbl.entity;

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
@Table(name="HEARING_NOTE")
public class HearingNote extends BaseModel
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
	 * 听证开始时间
	 */
	private Date startTime;

	/**
	 * 听证结束时间
	 */
	private Date endTime;

	/**
	 * 调查人员
	 */
	private String investigator;

	/**
	 * 委托代理人1
	 */
	private String attorney1;

	/**
	 * 委托代理人1性别
	 */
	private String attorney1Sex;

	/**
	 * 委托代理人1年龄
	 */
	private String attorney1Age;

	/**
	 * 委托代理人1工作单位
	 */
	private String attorney1Company;

	/**
	 * 委托代理人2
	 */
	private String attorney2;

	/**
	 * 委托代理人2性别
	 */
	private String attorney2Sex;

	/**
	 * 委托代理人2年龄
	 */
	private String attorney2Age;

	/**
	 * 委托代理人2工作单位
	 */
	private String attorney2Company;

	/**
	 * 第三人
	 */
	private String thirdPerson;

	/**
	 * 其他参与人员
	 */
	private String otherParticipants;

	/**
	 * 听证记录
	 */
	private String hearingRecord;
	
	private String undertaker;
	
	private String undertakerName;

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

	@Column(name="INVESTIGATOR")
	public String getInvestigator()
	{
		return this.investigator;
	}

	public void setInvestigator(String investigator)
	{
		this.investigator = investigator;
	}

	@Column(name="ATTORNEY1")
	public String getAttorney1()
	{
		return this.attorney1;
	}

	public void setAttorney1(String attorney1)
	{
		this.attorney1 = attorney1;
	}

	@Column(name="ATTORNEY1_SEX")
	public String getAttorney1Sex()
	{
		return this.attorney1Sex;
	}

	public void setAttorney1Sex(String attorney1Sex)
	{
		this.attorney1Sex = attorney1Sex;
	}

	@Column(name="ATTORNEY1_AGE")
	public String getAttorney1Age()
	{
		return this.attorney1Age;
	}

	public void setAttorney1Age(String attorney1Age)
	{
		this.attorney1Age = attorney1Age;
	}

	@Column(name="ATTORNEY1_COMPANY")
	public String getAttorney1Company()
	{
		return this.attorney1Company;
	}

	public void setAttorney1Company(String attorney1Company)
	{
		this.attorney1Company = attorney1Company;
	}

	@Column(name="ATTORNEY2")
	public String getAttorney2()
	{
		return this.attorney2;
	}

	public void setAttorney2(String attorney2)
	{
		this.attorney2 = attorney2;
	}

	@Column(name="ATTORNEY2_SEX")
	public String getAttorney2Sex()
	{
		return this.attorney2Sex;
	}

	public void setAttorney2Sex(String attorney2Sex)
	{
		this.attorney2Sex = attorney2Sex;
	}

	@Column(name="ATTORNEY2_AGE")
	public String getAttorney2Age()
	{
		return this.attorney2Age;
	}

	public void setAttorney2Age(String attorney2Age)
	{
		this.attorney2Age = attorney2Age;
	}

	@Column(name="ATTORNEY2_COMPANY")
	public String getAttorney2Company()
	{
		return this.attorney2Company;
	}

	public void setAttorney2Company(String attorney2Company)
	{
		this.attorney2Company = attorney2Company;
	}

	@Column(name="THIRD_PERSON")
	public String getThirdPerson()
	{
		return this.thirdPerson;
	}

	public void setThirdPerson(String thirdPerson)
	{
		this.thirdPerson = thirdPerson;
	}

	@Column(name="OTHER_PARTICIPANTS")
	public String getOtherParticipants()
	{
		return this.otherParticipants;
	}

	public void setOtherParticipants(String otherParticipants)
	{
		this.otherParticipants = otherParticipants;
	}

	@Column(name="HEARING_RECORD")
	public String getHearingRecord()
	{
		return this.hearingRecord;
	}

	public void setHearingRecord(String hearingRecord)
	{
		this.hearingRecord = hearingRecord;
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

}
