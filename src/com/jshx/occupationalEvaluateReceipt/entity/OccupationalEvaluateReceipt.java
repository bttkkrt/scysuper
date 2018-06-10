/*
*作者：李海棠
*/
package com.jshx.occupationalEvaluateReceipt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="OCCUPATIONAL_EVALUATE_RECEIPT")
public class OccupationalEvaluateReceipt extends BaseModel
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
	 * 企业编号
	 */
	private String qyid;

	/**
	 * 必填项一
	 */
	private String requiredOne;

	/**
	 * 必填项二
	 */
	private String requiredTwo;

	/**
	 * 必填项三
	 */
	private String requiredThree;

	/**
	 * 单位名称
	 */
	private String companyName;

	/**
	 * 上报时间
	 */
	private String reprotTime;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 发送时间
	 */
	private String sendTime;

	private String szzid;
	
	private String szzname;
	
	private String linkId;
	
	private String userName;
	/**
	 * 新增字段  职业病危害分类 lj 2014-06-27
	 * @return
	 */
	private String whfl;
	/**
	 * 新增字段 评价机构 lj 2014-06-27
	 * @return
	 */
	private String pjjg;
	
	private String dabh;
	
	@Column
	public String getDabh() {
		return dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}
	@Column
	public String getWhfl() {
		return whfl;
	}

	public void setWhfl(String whfl) {
		this.whfl = whfl;
	}
	@Column
	public String getPjjg() {
		return pjjg;
	}

	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}

	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@Column
	public String getSzzid() {
		return szzid;
	}

	public void setSzzid(String szzid) {
		this.szzid = szzid;
	}

	@Column
	public String getSzzname() {
		return szzname;
	}

	public void setSzzname(String szzname) {
		this.szzname = szzname;
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

	
	@Column(name="QYID")
	public String getQyid()
	{
		return this.qyid;
	}

	public void setQyid(String qyid)
	{
		this.qyid = qyid;
	}

	@Column(name="REQUIRED_ONE")
	public String getRequiredOne()
	{
		return this.requiredOne;
	}

	public void setRequiredOne(String requiredOne)
	{
		this.requiredOne = requiredOne;
	}

	@Column(name="REQUIRED_TWO")
	public String getRequiredTwo()
	{
		return this.requiredTwo;
	}

	public void setRequiredTwo(String requiredTwo)
	{
		this.requiredTwo = requiredTwo;
	}

	@Column(name="REQUIRED_THREE")
	public String getRequiredThree()
	{
		return this.requiredThree;
	}

	public void setRequiredThree(String requiredThree)
	{
		this.requiredThree = requiredThree;
	}

	@Column(name="COMPANY_NAME")
	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	@Column(name="REPROT_TIME")
	public String getReprotTime()
	{
		return this.reprotTime;
	}

	public void setReprotTime(String reprotTime)
	{
		this.reprotTime = reprotTime;
	}

	@Column(name="PROJECT_NAME")
	public String getProjectName()
	{
		return this.projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	@Column(name="SEND_TIME")
	public String getSendTime()
	{
		return this.sendTime;
	}

	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
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
