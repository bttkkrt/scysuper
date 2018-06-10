package com.jshx.dsrcssbbl.entity;

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
@Table(name="PARTY_STATE_NOTE")
public class PartyStateNote extends BaseModel
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
	 * 陈述开始时间
	 */
	private Date startTime;

	/**
	 * 陈述结束时间
	 */
	private Date endTime;

	/**
	 * 陈述地点
	 */
	private String stateAddress;

	/**
	 * 陈述申辩人
	 */
	private String stateDefense;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 职务
	 */
	private String position;

	/**
	 * 电话
	 */
	private String tele;

	/**
	 * 联系地址
	 */
	private String address;

	/**
	 * 邮编
	 */
	private String zipCode;

	/**
	 * 承办人
	 */
	private String undertaker;

	/**
	 * 记录人
	 */
	private String recorder;

	/**
	 * 陈述申辩记录
	 */
	private String stateRecord;
	
	/**
	 * 承办人
	 */
	private String undertakerName;

	/**
	 * 记录人
	 */
	private String recorderName;
	

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

	@Column(name="STATE_ADDRESS")
	public String getStateAddress()
	{
		return this.stateAddress;
	}

	public void setStateAddress(String stateAddress)
	{
		this.stateAddress = stateAddress;
	}

	@Column(name="STATE_DEFENSE")
	public String getStateDefense()
	{
		return this.stateDefense;
	}

	public void setStateDefense(String stateDefense)
	{
		this.stateDefense = stateDefense;
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

	@Column(name="POSITION")
	public String getPosition()
	{
		return this.position;
	}

	public void setPosition(String position)
	{
		this.position = position;
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

	@Column(name="ADDRESS")
	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name="ZIP_CODE")
	public String getZipCode()
	{
		return this.zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
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

	@Column(name="RECORDER")
	public String getRecorder()
	{
		return this.recorder;
	}

	public void setRecorder(String recorder)
	{
		this.recorder = recorder;
	}

	@Column(name="STATE_RECORD")
	public String getStateRecord()
	{
		return this.stateRecord;
	}

	public void setStateRecord(String stateRecord)
	{
		this.stateRecord = stateRecord;
	}
	@Column(name="UNDERTAKER_NAME")
	public String getUndertakerName() {
		return undertakerName;
	}

	public void setUndertakerName(String undertakerName) {
		this.undertakerName = undertakerName;
	}
	@Column(name="RECORDER_NAME")
	public String getRecorderName() {
		return recorderName;
	}

	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}
}
