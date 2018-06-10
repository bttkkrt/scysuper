package com.jshx.mainPersonReceipt.entity;

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
@Table(name="MAIN_PERSON_RECEIPT")
public class MainPersonReceipt extends BaseModel
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
	 * 单位名称
	 */
	private String companyName;

	/**
	 * 负责人姓名
	 */
	private String userName;

	/**
	 * 起始时间
	 */
	private String timeStart;

	/**
	 * 结束时间
	 */
	private String timeEnd;

	/**
	 * 发送时间
	 */
	private String sendTime;

	/**
	 * 企业编号
	 */
	private String qyid;
	
	private String szzid;
	
	private String szzname;
	
	private String linkId;
	
	/**
	 * 企业分色分类
	 */
	private String qyfsfl;
	
	/**
	 * 两重点一重大
	 */
	private String lzdyzd;
	
	@Column
	public String getQyfsfl() {
		return qyfsfl;
	}

	public void setQyfsfl(String qyfsfl) {
		this.qyfsfl = qyfsfl;
	}

	@Column
	public String getLzdyzd() {
		return lzdyzd;
	}

	public void setLzdyzd(String lzdyzd) {
		this.lzdyzd = lzdyzd;
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

	
	@Column(name="COMPANY_NAME")
	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
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

	@Column(name="TIME_START")
	public String getTimeStart()
	{
		return this.timeStart;
	}

	public void setTimeStart(String timeStart)
	{
		this.timeStart = timeStart;
	}

	@Column(name="TIME_END")
	public String getTimeEnd()
	{
		return this.timeEnd;
	}

	public void setTimeEnd(String timeEnd)
	{
		this.timeEnd = timeEnd;
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

	@Column(name="QYID")
	public String getQyid()
	{
		return this.qyid;
	}

	public void setQyid(String qyid)
	{
		this.qyid = qyid;
	}

}
