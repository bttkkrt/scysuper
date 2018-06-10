package com.jshx.swgl.entity;

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
@Table(name="RECEIVE_INFORMATION")
public class ReceiveInformation extends BaseModel
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
	 * 来文标题
	 */
	private String recinfoTitle;

	/**
	 * 来文编号
	 */
	private String recinfoNum;

	/**
	 * 来文类型
	 */
	private String recinfoType;
	
	/**
	 * 信息内容
	 */
	private String infoContent;

	/**
	 * 来文单位
	 */
	private String recinfoDept;

	/**
	 * 收文时间
	 */
	private Date recinfoTime;

	/**
	 * 阅读人员ID
	 */
	private String recinfoUserids;

	/**
	 * 阅读人员名称
	 */
	private String recinfoUsernames;

	/**
	 * 审批人id
	 */
	private String recinfoCheckUsrid;

	/**
	 * 审批人姓名
	 */
	private String recinfoCheckUsrname;

	/**
	 * 审批结果
	 */
	private String recinfoCheckResult;

	/**
	 * 审批备注
	 */
	private String recinfoCheckRemark;

	/**
	 * 领导批示
	 */
	private String recinfoInstruction;

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

	
	public ReceiveInformation(){
	}
	
	public ReceiveInformation(String id, String recinfoTitle, String recinfoNum, String recinfoDept,String auditState, String createUserID){
this.id = id;

this.recinfoTitle = recinfoTitle;

this.recinfoNum = recinfoNum;

this.recinfoDept = recinfoDept;

this.auditState = auditState;

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

	
	@Column(name="RECINFO_TITLE")
	public String getRecinfoTitle()
	{
		return this.recinfoTitle;
	}

	public void setRecinfoTitle(String recinfoTitle)
	{
		this.recinfoTitle = recinfoTitle;
	}

	@Column(name="RECINFO_NUM")
	public String getRecinfoNum()
	{
		return this.recinfoNum;
	}

	public void setRecinfoNum(String recinfoNum)
	{
		this.recinfoNum = recinfoNum;
	}

	@Column(name="RECINFO_TYPE")
	public String getRecinfoType()
	{
		return this.recinfoType;
	}

	public void setRecinfoType(String recinfoType)
	{
		this.recinfoType = recinfoType;
	}

	@Column(name="RECINFO_DEPT")
	public String getRecinfoDept()
	{
		return this.recinfoDept;
	}

	public void setRecinfoDept(String recinfoDept)
	{
		this.recinfoDept = recinfoDept;
	}

	@Column(name="RECINFO_TIME")
	public Date getRecinfoTime()
	{
		return this.recinfoTime;
	}

	public void setRecinfoTime(Date recinfoTime)
	{
		this.recinfoTime = recinfoTime;
	}

	@Column(name="RECINFO_USERIDS")
	public String getRecinfoUserids()
	{
		return this.recinfoUserids;
	}

	public void setRecinfoUserids(String recinfoUserids)
	{
		this.recinfoUserids = recinfoUserids;
	}

	@Column(name="RECINFO_USERNAMES")
	public String getRecinfoUsernames()
	{
		return this.recinfoUsernames;
	}

	public void setRecinfoUsernames(String recinfoUsernames)
	{
		this.recinfoUsernames = recinfoUsernames;
	}

	@Column(name="RECINFO_CHECK_USRID")
	public String getRecinfoCheckUsrid()
	{
		return this.recinfoCheckUsrid;
	}

	public void setRecinfoCheckUsrid(String recinfoCheckUsrid)
	{
		this.recinfoCheckUsrid = recinfoCheckUsrid;
	}

	@Column(name="RECINFO_CHECK_USRNAME")
	public String getRecinfoCheckUsrname()
	{
		return this.recinfoCheckUsrname;
	}

	public void setRecinfoCheckUsrname(String recinfoCheckUsrname)
	{
		this.recinfoCheckUsrname = recinfoCheckUsrname;
	}

	@Column(name="RECINFO_CHECK_RESULT")
	public String getRecinfoCheckResult()
	{
		return this.recinfoCheckResult;
	}

	public void setRecinfoCheckResult(String recinfoCheckResult)
	{
		this.recinfoCheckResult = recinfoCheckResult;
	}

	@Column(name="RECINFO_CHECK_REMARK")
	public String getRecinfoCheckRemark()
	{
		return this.recinfoCheckRemark;
	}

	public void setRecinfoCheckRemark(String recinfoCheckRemark)
	{
		this.recinfoCheckRemark = recinfoCheckRemark;
	}

	@Column(name="RECINFO_INSTRUCTION")
	public String getRecinfoInstruction()
	{
		return this.recinfoInstruction;
	}

	public void setRecinfoInstruction(String recinfoInstruction)
	{
		this.recinfoInstruction = recinfoInstruction;
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
