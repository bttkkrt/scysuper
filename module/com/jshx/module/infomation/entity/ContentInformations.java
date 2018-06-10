package com.jshx.module.infomation.entity;

import java.sql.Clob;
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
@Table(name="CONTENT_INFORMATIONS")
public class ContentInformations extends BaseModel
{
	/**
	 * 标题
	 */
	private String infoTitle;

	/**
	 * 信息内容
	 */
	private String infoContent;

	/**
	 * 信息类型
	 */
	private String infoType;

	/**
	 * 所属部门
	 */
	private String deptId;
	private Department dept;

	/**
	 * 删除标志
	 */
	private String delFlag;

	/**
	 * 置顶标志
	 */
	private String topFlag;

	/**
	 * 过期标志
	 */
	private String expireFlag;

	/**
	 * 发布人
	 */
	private String userId;
	private User user;

	/**
	 * 发布日期
	 */
	private Date publicDate;
	private String time;
	private String linkId;
	 private String userIds;
	 private String userNames;
	  private String readed;
	  private int viewnum;
	  private int realnum;
	  
	  
	  private Date readPeriod;
	  
	  private String proid;

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
	 * 获取信息内容
	 */
	@Column(name="INFO_CONTENT")
	public String getInfoContent()
	{
		return this.infoContent;
	}
	/**
	 * 设置获取信息内容
	 */
	public void setInfoContent(String infoContent)
	{
		this.infoContent = infoContent;
	}
	/**
	 * 获取信息类型
	 */
	@Column(name="INFO_TYPE")
	public String getInfoType()
	{
		return this.infoType;
	}
	/**
	 * 设置获取信息类型
	 */
	public void setInfoType(String infoType)
	{
		this.infoType = infoType;
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
	 * 获取置顶标志
	 */
	@Column(name="TOP_FLAG")
	public String getTopFlag()
	{
		return this.topFlag;
	}
	/**
	 * 设置置顶标志
	 */
	public void setTopFlag(String topFlag)
	{
		this.topFlag = topFlag;
	}
	/**
	 * 获取过期标志
	 */
	@Column(name="EXPIRE_FLAG")
	public String getExpireFlag()
	{
		return this.expireFlag;
	}
	/**
	 * 设置过期标志
	 */
	public void setExpireFlag(String expireFlag)
	{
		this.expireFlag = expireFlag;
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
	@Column(name="usernames")
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	@Column(name="READED")
	public String getReaded() {
		return readed;
	}
	public void setReaded(String readed) {
		this.readed = readed;
	}
	@Column(name="VIEWNUM")
	public int getViewnum() {
		return viewnum;
	}
	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	@Column(name="REALNUM")
	public int getRealnum() {
		return realnum;
	}
	public void setRealnum(int realnum) {
		this.realnum = realnum;
	}
	@Column(name="userids")
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	@Column(name="TIME")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Column(name="READ_PERIOD")
	public Date getReadPeriod() {
		return readPeriod;
	}
	public void setReadPeriod(Date readPeriod) {
		this.readPeriod = readPeriod;
	}
	
	@Column
	  public String getProid() {
		  return proid;
	  }

	  public void setProid(String proid) {
		  this.proid = proid;
	  }

}
