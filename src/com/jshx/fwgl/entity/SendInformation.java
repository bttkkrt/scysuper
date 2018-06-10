package com.jshx.fwgl.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SEND_INFORMATION")
public class SendInformation extends BaseModel
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
	 * 发文标题
	 */
	private String sendinfoTitle;


	/**
	 * 发文类型
	 */
	private String sendinfoType;
	
	/**
	 * 信息内容
	 */
	private String infoContent;

	/**
	 * 阅读人员id
	 */
	private String sendinfoUserids;

	/**
	 * 阅读人员姓名
	 */
	private String sendinfoUsernames;

	/**
	 * 审批人id
	 */
	private String sendinfoCheckUserid;

	/**
	 * 审批人姓名
	 */
	private String sendinfCheckUsrname;

	/**
	 * 审批结果
	 */
	private String sendinfoCheckResult;

	/**
	 * 审批备注
	 */
	private String sendinfoCheckRemark;

	/**
	 * 发文字号
	 */
	private String sendinfoNum;

	/**
	 * 签发人id
	 */
	private String sendinfoSignerId;

	/**
	 * 签发人姓名
	 */
	private String sendinfoSignerName;

	/**
	 * 签发时间
	 */
	private Date sendinfoSignTime;

	/**
	 * 拟稿人id
	 */
	private String sendinfoDraftUserid;

	/**
	 * 拟稿人姓名
	 */
	private String sendinfoDraftUsername;

	/**
	 * 拟稿时间
	 */
	private Date sendinfoDraftTime;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 审核状态
	 */
	private String auditState;
	
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
	private String userName;
	private String userId;
	private User user;
	
	private String time;
	 private String userIds;
	 private String userNames;
	  private String readed;
	  private int viewnum;
	  private int realnum;

	
	public SendInformation(){
	}
	
	public SendInformation(String id, String sendinfoTitle, String sendinfoUsernames,String auditState,String sendinfoNum,String sendinfoDraftUsername,String createUserID){
this.id = id;

this.sendinfoTitle = sendinfoTitle;

this.sendinfoUsernames = sendinfoUsernames;

this.auditState = auditState;

this.sendinfoNum = sendinfoNum;

this.sendinfoDraftUsername = sendinfoDraftUsername;
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

	
	@Column(name="SENDINFO_TITLE")
	public String getSendinfoTitle()
	{
		return this.sendinfoTitle;
	}

	public void setSendinfoTitle(String sendinfoTitle)
	{
		this.sendinfoTitle = sendinfoTitle;
	}


	@Column(name="SENDINFO_TYPE")
	public String getSendinfoType()
	{
		return this.sendinfoType;
	}

	public void setSendinfoType(String sendinfoType)
	{
		this.sendinfoType = sendinfoType;
	}

	@Column(name="SENDINFO_USERIDS")
	public String getSendinfoUserids()
	{
		return this.sendinfoUserids;
	}

	public void setSendinfoUserids(String sendinfoUserids)
	{
		this.sendinfoUserids = sendinfoUserids;
	}

	@Column(name="SENDINFO_USERNAMES")
	public String getSendinfoUsernames()
	{
		return this.sendinfoUsernames;
	}

	public void setSendinfoUsernames(String sendinfoUsernames)
	{
		this.sendinfoUsernames = sendinfoUsernames;
	}

	@Column(name="SENDINFO_CHECK_USERID")
	public String getSendinfoCheckUserid()
	{
		return this.sendinfoCheckUserid;
	}

	public void setSendinfoCheckUserid(String sendinfoCheckUserid)
	{
		this.sendinfoCheckUserid = sendinfoCheckUserid;
	}

	@Column(name="SENDINF_CHECK_USRNAME")
	public String getSendinfCheckUsrname()
	{
		return this.sendinfCheckUsrname;
	}

	public void setSendinfCheckUsrname(String sendinfCheckUsrname)
	{
		this.sendinfCheckUsrname = sendinfCheckUsrname;
	}

	@Column(name="SENDINFO_CHECK_RESULT")
	public String getSendinfoCheckResult()
	{
		return this.sendinfoCheckResult;
	}

	public void setSendinfoCheckResult(String sendinfoCheckResult)
	{
		this.sendinfoCheckResult = sendinfoCheckResult;
	}

	@Column(name="SENDINFO_CHECK_REMARK")
	public String getSendinfoCheckRemark()
	{
		return this.sendinfoCheckRemark;
	}

	public void setSendinfoCheckRemark(String sendinfoCheckRemark)
	{
		this.sendinfoCheckRemark = sendinfoCheckRemark;
	}

	@Column(name="SENDINFO_NUM")
	public String getSendinfoNum()
	{
		return this.sendinfoNum;
	}

	public void setSendinfoNum(String sendinfoNum)
	{
		this.sendinfoNum = sendinfoNum;
	}

	@Column(name="SENDINFO_SIGNER_ID")
	public String getSendinfoSignerId()
	{
		return this.sendinfoSignerId;
	}

	public void setSendinfoSignerId(String sendinfoSignerId)
	{
		this.sendinfoSignerId = sendinfoSignerId;
	}

	@Column(name="SENDINFO_SIGNER_NAME")
	public String getSendinfoSignerName()
	{
		return this.sendinfoSignerName;
	}

	public void setSendinfoSignerName(String sendinfoSignerName)
	{
		this.sendinfoSignerName = sendinfoSignerName;
	}

	@Column(name="SENDINFO_SIGN_TIME")
	public Date getSendinfoSignTime()
	{
		return this.sendinfoSignTime;
	}

	public void setSendinfoSignTime(Date sendinfoSignTime)
	{
		this.sendinfoSignTime = sendinfoSignTime;
	}

	@Column(name="SENDINFO_DRAFT_USERID")
	public String getSendinfoDraftUserid()
	{
		return this.sendinfoDraftUserid;
	}

	public void setSendinfoDraftUserid(String sendinfoDraftUserid)
	{
		this.sendinfoDraftUserid = sendinfoDraftUserid;
	}

	@Column(name="SENDINFO_DRAFT_USERNAME")
	public String getSendinfoDraftUsername()
	{
		return this.sendinfoDraftUsername;
	}

	public void setSendinfoDraftUsername(String sendinfoDraftUsername)
	{
		this.sendinfoDraftUsername = sendinfoDraftUsername;
	}

	@Column(name="SENDINFO_DRAFT_TIME")
	public Date getSendinfoDraftTime()
	{
		return this.sendinfoDraftTime;
	}

	public void setSendinfoDraftTime(Date sendinfoDraftTime)
	{
		this.sendinfoDraftTime = sendinfoDraftTime;
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
	
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	@Column(name="TOP_FLAG")
	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}
	@Column(name="EXPIRE_FLAG")
	public String getExpireFlag() {
		return expireFlag;
	}

	public void setExpireFlag(String expireFlag) {
		this.expireFlag = expireFlag;
	}
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "USER_ID",updatable=false,insertable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="TIME")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@Column(name="USER_IDS")
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	@Column(name="USER_NAMES")
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
	@Column(name="INFO_CONTENT")
	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
