package com.jshx.tzhbgs.entity;

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
@Table(name="HEARING_REPORT")
public class HearingReport extends BaseModel
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
	 * 听证会基本情况摘要
	 */
	private String hearingSummary;

	/**
	 * 听证主持人意见
	 */
	private String hearChairComment;
	
	private Date hearChairTime;

	/**
	 * 负责人审核意见
	 */
	private String checkComment;
	
	private String checkPerson;
	
	private Date checkTime;
	
	
	public HearingReport(){
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

	@Column(name="HEARING_SUMMARY")
	public String getHearingSummary()
	{
		return this.hearingSummary;
	}

	public void setHearingSummary(String hearingSummary)
	{
		this.hearingSummary = hearingSummary;
	}

	@Column(name="HEAR_CHAIR_COMMENT")
	public String getHearChairComment()
	{
		return this.hearChairComment;
	}

	public void setHearChairComment(String hearChairComment)
	{
		this.hearChairComment = hearChairComment;
	}
	@Column(name="HEAR_CHAIR_TIME")

	public Date getHearChairTime() {
		return hearChairTime;
	}


	public void setHearChairTime(Date hearChairTime) {
		this.hearChairTime = hearChairTime;
	}

	@Column(name="CHECK_COMMENT")
	public String getCheckComment() {
		return checkComment;
	}


	public void setCheckComment(String checkComment) {
		this.checkComment = checkComment;
	}

	@Column(name="CHECK_TIME")
	public Date getCheckTime() {
		return checkTime;
	}


	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}


	@Column(name="CHECK_PERSON")
	public String getCheckPerson() {
		return checkPerson;
	}


	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}


}
