package com.jshx.cyqzpz.entity;

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
@Table(name="SAMPLING_EVIDENCE")
public class SamplingEvidence extends BaseModel
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
	 * 现场负责人
	 */
	private String chargePerson;

	/**
	 * 抽样取证开始时间
	 */
	private Date startTime;

	/**
	 * 抽样取证结束时间
	 */
	private Date endTime;

	/**
	 * 抽样地点
	 */
	private String forensicAddress;

	/**
	 * 执法人员
	 */
	private String lawOfficer;
	
	/**
	 * 执法人员
	 */
	private String lawOfficerName;
	
	private String lawOfficerName1;
	
	
	public SamplingEvidence(){
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

	@Column(name="CHARGE_PERSON")
	public String getChargePerson()
	{
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson)
	{
		this.chargePerson = chargePerson;
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

	@Column(name="FORENSIC_ADDRESS")
	public String getForensicAddress()
	{
		return this.forensicAddress;
	}

	public void setForensicAddress(String forensicAddress)
	{
		this.forensicAddress = forensicAddress;
	}

	@Column(name="LAW_OFFICER")
	public String getLawOfficer()
	{
		return this.lawOfficer;
	}

	public void setLawOfficer(String lawOfficer)
	{
		this.lawOfficer = lawOfficer;
	}
	
	@Column(name="LAW_OFFICER_NAME")
	public String getLawOfficerName()
	{
		return this.lawOfficerName;
	}

	public void setLawOfficerName(String lawOfficerName)
	{
		this.lawOfficerName = lawOfficerName;
	}

	@Column(name="LAW_OFFICER_NAME1")
	public String getLawOfficerName1() {
		return lawOfficerName1;
	}

	public void setLawOfficerName1(String lawOfficerName1) {
		this.lawOfficerName1 = lawOfficerName1;
	}

}
