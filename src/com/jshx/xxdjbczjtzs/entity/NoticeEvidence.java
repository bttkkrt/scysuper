package com.jshx.xxdjbczjtzs.entity;

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
@Table(name="NOTICE_EVIDENCE")
public class NoticeEvidence extends BaseModel
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
	 * 违法行为
	 */
	private String violation;

	/**
	 * 处理时间
	 */
	private Date dealTime;

	/**
	 * 处理地点
	 */
	private String dealAddress;
	
	/**
	 * 承办人
	 */
	private String undertaker;
	
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

	@Column(name="VIOLATION")
	public String getViolation()
	{
		return this.violation;
	}

	public void setViolation(String violation)
	{
		this.violation = violation;
	}

	@Column(name="DEAL_TIME")
	public Date getDealTime()
	{
		return this.dealTime;
	}

	public void setDealTime(Date dealTime)
	{
		this.dealTime = dealTime;
	}

	@Column(name="DEAL_ADDRESS")
	public String getDealAddress()
	{
		return this.dealAddress;
	}

	public void setDealAddress(String dealAddress)
	{
		this.dealAddress = dealAddress;
	}
	@Column(name="UNDER_TAKER")
	public String getUndertaker() {
		return undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}
	
	@Column(name="UNDER_TAKER_NAME")
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
