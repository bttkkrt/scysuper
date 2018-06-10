package com.jshx.docReview.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.docReviewReceiver.entity.DocReviewReceiver;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="DOC_REVIEW")
public class DocReview extends BaseModel
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
	 * 公文标题
	 */
	private String docTitle;

	/**
	 * 公文类型
	 */
	private String docType;

	/**
	 * 部门编码
	 */
	private String deptCode;

	/**
	 * 公文内容
	 */
	private String docContent;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 关联附件id
	 */
	private String proId;
	
	/**
	 * 查看记录集合
	 */
	private List<DocReviewReceiver> receiverList;

	private String userIds;
	private String userNames;

	  private String time;
	  
	  
	  private String defId; 
	  //   通过1 ，不通过 -1，默认0
	  private Integer auditStatus;
	  
	  private String backReason;
	//   结束1 ，，默认0，取消-1
	  private Integer end;
	
	 
	  @Column(name="AUDITSTATUS")
	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name="END")
	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	@Column(name="BACKREASON")
	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	@Column(name="DEFID")  
	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	@Column(name="PROID")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@OneToMany(mappedBy="docReview", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	@Where(clause="delFlag=0")
	public List<DocReviewReceiver> getReceiverList()
	{
		return receiverList;
	}

	public void setReceiverList(List<DocReviewReceiver> receiverList)
	{
		this.receiverList = receiverList;
	}
	 @Column(name="USERIDS")
	  public String getUserIds() {
		  return userIds;
	  }

	  public void setUserIds(String userIds) {
		  this.userIds = userIds;
	  }
	  @Column(name="USERNAMES")
	  public String getUserNames() {
		  return userNames;
	  }

	  public void setUserNames(String userNames) {
		  this.userNames = userNames;
	  }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	
	@Column(name="DOC_TITLE")
	public String getDocTitle()
	{
		return this.docTitle;
	}

	public void setDocTitle(String docTitle)
	{
		this.docTitle = docTitle;
	}

	@Column(name="DOC_TYPE")
	public String getDocType()
	{
		return this.docType;
	}

	public void setDocType(String docType)
	{
		this.docType = docType;
	}

	@Column(name="DEPT_CODE")
	public String getDeptCode()
	{
		return this.deptCode;
	}

	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}

	@Column(name="DOC_CONTENT")
	public String getDocContent()
	{
		return this.docContent;
	}

	public void setDocContent(String docContent)
	{
		this.docContent = docContent;
	}

	@Column(name="USER_NAME")
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
