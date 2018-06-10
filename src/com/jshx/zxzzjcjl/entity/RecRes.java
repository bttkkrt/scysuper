package com.jshx.zxzzjcjl.entity;

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
@Table(name="REC_RES")
public class RecRes extends BaseModel
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
	 * 计划编号
	 */
	private String planId;

	/**
	 * 检查时间
	 */
	private Date checkTime;

	/**
	 * 检查部门
	 */
	private String checkDept;

	/**
	 * 检查内容
	 */
	private String checkContent;

	/**
	 * 检查人员名单
	 */
	private String checkUser;

	/**
	 * 整改期限
	 */
	private Date rectificationDate;
	/**
	 * 整改措施
	 */
	private String rectificationMeasure;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 计划名称
	 */
	private String planName;
	public RecRes(){
	}
	
	public RecRes(String id, Date checkTime, String checkDept, String rectificationMeasure,Date rectificationDate,String planId,String planName,String createUserID){
this.id = id;

this.checkTime = checkTime;

this.checkDept = checkDept;

this.rectificationMeasure = rectificationMeasure;
this.rectificationDate=rectificationDate;
this.planId=planId;
this.planName=planName;
this.createUserID=createUserID;
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

	
	@Column(name="PLAN_ID")
	public String getPlanId()
	{
		return this.planId;
	}

	public void setPlanId(String planId)
	{
		this.planId = planId;
	}

	@Column(name="CHECK_TIME")
	public Date getCheckTime()
	{
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}

	@Column(name="CHECK_DEPT")
	public String getCheckDept()
	{
		return this.checkDept;
	}

	public void setCheckDept(String checkDept)
	{
		this.checkDept = checkDept;
	}

	@Column(name="CHECK_CONTENT")
	public String getCheckContent()
	{
		return this.checkContent;
	}

	public void setCheckContent(String checkContent)
	{
		this.checkContent = checkContent;
	}

	@Column(name="CHECK_USER")
	public String getCheckUser()
	{
		return this.checkUser;
	}

	public void setCheckUser(String checkUser)
	{
		this.checkUser = checkUser;
	}


	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}
	@Column(name="RECTIFICATION_DATE")
	public Date getRectificationDate() {
		return rectificationDate;
	}

	public void setRectificationDate(Date rectificationDate) {
		this.rectificationDate = rectificationDate;
	}
	@Column(name="RECTIFICATION_MEASURE")
	public String getRectificationMeasure() {
		return rectificationMeasure;
	}

	public void setRectificationMeasure(String rectificationMeasure) {
		this.rectificationMeasure = rectificationMeasure;
	}
	@Column(name="PLAN_NAME")
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	
}
