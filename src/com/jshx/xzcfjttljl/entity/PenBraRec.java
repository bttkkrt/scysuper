package com.jshx.xzcfjttljl.entity;

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
@Table(name="PEN_BRA_REC")
public class PenBraRec extends BaseModel
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
	 * 讨论开始时间
	 */
	private Date startTime;

	/**
	 * 讨论结束时间
	 */
	private Date endTime;

	/**
	 * 讨论地点
	 */
	private String discussionAddress;

	/**
	 * 主持人
	 */
	private String chairperson;

	/**
	 * 汇报人
	 */
	private String reportPerson;

	/**
	 * 记录人
	 */
	private String recordPerson;

	/**
	 * 出席人员姓名及职务
	 */
	private String attendName;
	
	private String attendId;

	/**
	 * 讨论内容
	 */
	private String discussionContent;

	/**
	 * 讨论记录
	 */
	private String discussionRecord;

	/**
	 * 结论性意见
	 */
	private String conclusionComment;
	
	/**
	 * 主持人
	 */
	private String chairpersonName;

	/**
	 * 汇报人
	 */
	private String reportPersonName;

	/**
	 * 记录人
	 */
	private String recordPersonName;


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

	@Column(name="DISCUSSION_ADDRESS")
	public String getDiscussionAddress()
	{
		return this.discussionAddress;
	}

	public void setDiscussionAddress(String discussionAddress)
	{
		this.discussionAddress = discussionAddress;
	}

	@Column(name="CHAIRPERSON")
	public String getChairperson()
	{
		return this.chairperson;
	}

	public void setChairperson(String chairperson)
	{
		this.chairperson = chairperson;
	}

	@Column(name="REPORT_PERSON")
	public String getReportPerson()
	{
		return this.reportPerson;
	}

	public void setReportPerson(String reportPerson)
	{
		this.reportPerson = reportPerson;
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

	@Column(name="ATTEND_NAME")
	public String getAttendName()
	{
		return this.attendName;
	}

	public void setAttendName(String attendName)
	{
		this.attendName = attendName;
	}
	
	@Column(name="ATTEND_ID")
	public String getAttendId()
	{
		return this.attendId;
	}

	public void setAttendId(String attendId)
	{
		this.attendId = attendId;
	}

	@Column(name="DISCUSSION_CONTENT")
	public String getDiscussionContent()
	{
		return this.discussionContent;
	}

	public void setDiscussionContent(String discussionContent)
	{
		this.discussionContent = discussionContent;
	}

	@Column(name="DISCUSSION_RECORD")
	public String getDiscussionRecord()
	{
		return this.discussionRecord;
	}

	public void setDiscussionRecord(String discussionRecord)
	{
		this.discussionRecord = discussionRecord;
	}

	@Column(name="CONCLUSION_COMMENT")
	public String getConclusionComment()
	{
		return this.conclusionComment;
	}

	public void setConclusionComment(String conclusionComment)
	{
		this.conclusionComment = conclusionComment;
	}
	@Column
	public String getChairpersonName() {
		return chairpersonName;
	}

	public void setChairpersonName(String chairpersonName) {
		this.chairpersonName = chairpersonName;
	}
	@Column
	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}
	@Column
	public String getRecordPersonName() {
		return recordPersonName;
	}

	public void setRecordPersonName(String recordPersonName) {
		this.recordPersonName = recordPersonName;
	}

}
