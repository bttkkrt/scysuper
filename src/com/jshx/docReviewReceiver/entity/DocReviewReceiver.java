package com.jshx.docReviewReceiver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.docReview.entity.DocReview;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="DOC_REVIEW_RECEIVER")
public class DocReviewReceiver extends BaseModel
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
	 * 公文ID
	 */
	private DocReview docReview;

	/**
	 * 接收人
	 */
	private User receiveUser;

	/**
	 * 接受时间
	 */
	private Date receiveTime;

	/**
	 * 接受标记
	 */
	private String receiveFlag;


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
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=DocReview.class)
	@JoinColumn(name="DOC_ID")
	public DocReview getDocReview() {
		return docReview;
	}

	public void setDocReview(DocReview docReview) {
		this.docReview = docReview;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
    @JoinColumn(name="RECEIVE_USER")
	public User getReceiveUser()
	{
		return this.receiveUser;
	}

	public void setReceiveUser(User receiveUser)
	{
		this.receiveUser = receiveUser;
	}

	@Column(name="RECEIVE_TIME")
	public Date getReceiveTime()
	{
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime)
	{
		this.receiveTime = receiveTime;
	}

	@Column(name="RECEIVE_FLAG")
	public String getReceiveFlag()
	{
		return this.receiveFlag;
	}

	public void setReceiveFlag(String receiveFlag)
	{
		this.receiveFlag = receiveFlag;
	}

}
