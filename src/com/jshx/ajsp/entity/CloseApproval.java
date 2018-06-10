package com.jshx.ajsp.entity;

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
@Table(name="CLOSE_APPROVAL")
public class CloseApproval extends BaseModel
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
	 * 处理结果
	 */
	private String approvalResult;

	/**
	 * 执法情况
	 */
	private String executeCondition;

	/**
	 * 承办人
	 */
	private String undertaker;
	
	private Date underTime;

	/**
	 * 审核人
	 */
	private String checkPerson;

	/**
	 * 审核意见
	 */
	private String checkComment;
	
	private Date checkTime;

	/**
	 * 审批人
	 */
	private String approver;

	/**
	 * 审批意见
	 */
	private String approverComment;
	
	private Date approverTime;
	
	private String undertakerName;
	
	private String undertakerName1;
	
	public CloseApproval(){
	}
	
	public CloseApproval(String id){
this.id = id;
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

	@Column(name="APPROVAL_RESULT")
	public String getApprovalResult()
	{
		return this.approvalResult;
	}

	public void setApprovalResult(String approvalResult)
	{
		this.approvalResult = approvalResult;
	}

	@Column(name="EXECUTE_CONDITION")
	public String getExecuteCondition()
	{
		return this.executeCondition;
	}

	public void setExecuteCondition(String executeCondition)
	{
		this.executeCondition = executeCondition;
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

	@Column(name="CHECK_PERSON")
	public String getCheckPerson()
	{
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson)
	{
		this.checkPerson = checkPerson;
	}

	@Column(name="CHECK_COMMENT")
	public String getCheckComment()
	{
		return this.checkComment;
	}

	public void setCheckComment(String checkComment)
	{
		this.checkComment = checkComment;
	}

	@Column(name="APPROVER")
	public String getApprover()
	{
		return this.approver;
	}

	public void setApprover(String approver)
	{
		this.approver = approver;
	}

	@Column(name="APPROVER_COMMENT")
	public String getApproverComment()
	{
		return this.approverComment;
	}

	public void setApproverComment(String approverComment)
	{
		this.approverComment = approverComment;
	}
	@Column(name="UNDER_TIME")
	public Date getUnderTime() {
		return underTime;
	}

	public void setUnderTime(Date underTime) {
		this.underTime = underTime;
	}
	@Column(name="CHECK_TIME")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	@Column(name="APPROVER_TIME")
	public Date getApproverTime() {
		return approverTime;
	}

	public void setApproverTime(Date approverTime) {
		this.approverTime = approverTime;
	}
	@Column
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
