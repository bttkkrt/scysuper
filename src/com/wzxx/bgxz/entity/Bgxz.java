package com.wzxx.bgxz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="BGXZ")
public class Bgxz extends BaseModel
{
	/**
	 * 标题
	 */
	private String infoTitle;

	private String deptId;
	private Department dept;

	/**
	 * 删除标志
	 */
	private String delFlag;

	/**
	 * 发布人
	 */
	private String userId;
	private User user;

	/**
	 * 发布日期
	 */
	private Date publicDate;
	private String linkId;
	private String bgzl;
	
	private String nrgs;
	
	/**
	 * 第一标题
	 */
	private String firTitle;

	@Column
	public String getFirTitle() {
		return firTitle;
	}

	public void setFirTitle(String firTitle) {
		this.firTitle = firTitle;
	}
	
	/**
	 * 获取信息标题
	 */
	@Column(name="INFO_TITLE")
	public String getInfoTitle()
	{
		return this.infoTitle;
	}
	/**
	 * 设置信息标题
	 */
	public void setInfoTitle(String infoTitle)
	{
		this.infoTitle = infoTitle;
	}
		/**
	 * 获取所属部门ID
	 */
	@Column(name="DEPT_ID")
	public String getDeptId()
	{
		return this.deptId;
	}
	/**
	 * 设置所属部门ID
	 */
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	/**
	 * 获取删除标志
	 */
	@Column(name="DEL_FLAG")
	public String getDelFlag()
	{
		return this.delFlag;
	}
	/**
	 * 设置删除标志
	 */
	public void setDelFlag(String delFlag)
	{
		this.delFlag = delFlag;
	}
	/**
	 * 获取信息发布人ID
	 */
	@Column(name="USER_ID")
	public String getUserId()
	{
		return this.userId;
	}
	/**
	 * 设置信息发布人ID
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	/**
	 * 获取发布日期
	 */
	@Column(name="PUBLIC_DATE")
	public Date getPublicDate()
	{
		return this.publicDate;
	}
	/**
	 * 设置发布日期
	 */
	public void setPublicDate(Date publicDate)
	{
		this.publicDate = publicDate;
	}
	/**
	 * 获取所属部门
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=Department.class)
	@JoinColumn(name = "DEPT_ID",updatable=false,insertable=false)
	public Department getDept() {
		return dept;
	}
	/**
	 * 设置所属部门
	 */
	public void setDept(Department dept) {
		this.dept = dept;
	}
	/**
	 * 获取发布人
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "USER_ID",updatable=false,insertable=false)
	public User getUser() {
		return user;
	}
	/**
	 * 设置发布人
	 */
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="LINK_ID")
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	@Column(name="BGZL")
	public String getBgzl() {
		return bgzl;
	}
	public void setBgzl(String bgzl) {
		this.bgzl = bgzl;
	}
	@Column(name="NRGS")
	public String getNrgs() {
		return nrgs;
	}
	public void setNrgs(String nrgs) {
		this.nrgs = nrgs;
	}

}
