package com.jshx.zgfcyjs.entity;

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
@Table(name="REVIEW_SUBMISSION")
public class ReviewSubmission extends BaseModel
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
	 * 意见
	 */
	private String reviewComment;

	/**
	 * 执法人员
	 */
	private String lawOfficer;
	
	/**
	 * 执法人员
	 */
	private String lawOfficerName;
	
	private Date xqzgTime;
	
	private String jdajbz;
	
	private String zfh;
	
	private String zfhNum;
	
	private String zgjd;
	private String lawOfficerName1;
	
	@Column(name="LAW_OFFICER_NAME")
	public String getLawOfficerName()
	{
		return this.lawOfficerName;
	}

	public void setLawOfficerName(String lawOfficerName)
	{
		this.lawOfficerName = lawOfficerName;
	}
	
	
	public ReviewSubmission(){
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

	@Column(name="REVIEW_COMMENT")
	public String getReviewComment()
	{
		return this.reviewComment;
	}

	public void setReviewComment(String reviewComment)
	{
		this.reviewComment = reviewComment;
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
	@Column
	public String getLawOfficerName1() {
		return lawOfficerName1;
	}

	public void setLawOfficerName1(String lawOfficerName1) {
		this.lawOfficerName1 = lawOfficerName1;
	}
	@Column
	public Date getXqzgTime() {
		return xqzgTime;
	}

	public void setXqzgTime(Date xqzgTime) {
		this.xqzgTime = xqzgTime;
	}
	@Column
	public String getJdajbz() {
		return jdajbz;
	}

	public void setJdajbz(String jdajbz) {
		this.jdajbz = jdajbz;
	}
	@Column
	public String getZfh() {
		return zfh;
	}

	public void setZfh(String zfh) {
		this.zfh = zfh;
	}
	@Column
	public String getZfhNum() {
		return zfhNum;
	}

	public void setZfhNum(String zfhNum) {
		this.zfhNum = zfhNum;
	}
	@Column
	public String getZgjd() {
		return zgjd;
	}

	public void setZgjd(String zgjd) {
		this.zgjd = zgjd;
	}

}
