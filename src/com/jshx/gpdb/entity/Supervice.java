package com.jshx.gpdb.entity;

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
@Table(name="SUPERVICE")
public class Supervice extends BaseModel
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
	 * 所在区域编号
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业编号
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 隐患名称
	 */
	private String dangerName;

	/**
	 * 挂牌时间
	 */
	private Date listingTime;

	/**
	 * 隐患类别
	 */
	private String dangerSort;

	/**
	 * 隐患级别
	 */
	private String dangerLevel;

	/**
	 * 隐患内容
	 */
	private String dangerContent;

	/**
	 * 责任人
	 */
	private String responsible;

	/**
	 * 责任人联系电话
	 */
	private String responsibleMobile;

	/**
	 * 责任单位
	 */
	private String responsibleUnit;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 整改期限
	 */
	private Date rectificationTerm;

	/**
	 * 整改状态
	 */
	private String rectificationState;

	/**
	 * 整改资金
	 */
	private String rectificationMoney;

	/**
	 * 整改完成时间
	 */
	private Date recfinishTime;

	/**
	 * 验收时间
	 */
	private Date acceptTime;

	/**
	 * 隐患整改数
	 */
	private String danrecNum;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 挂牌督办整改级别
	 */
	private String rectificationLevel;
	
	/**
	 * 下一节点执行人员角色
	 * 科员 A04:修改基本信息；A04:修改整改信息；
	 * 处长 A03：审核基本信息；A03：审核整改信息；
	 * 局长 A02：审批基本信息；A02：审批整改信息；
	 * 完成： finish finish
	 */
	private String nextRoleCode;
	
	/**
	 * 下一节点执行名称
	 * 科员 A04:修改基本信息 update1；A04:修改(上报)整改信息 update2；
	 * 处长 A03：审核基本信息 check1；A03：审核整改信息 check3；
	 * 局长 A02：审批基本信息 check2；A02：审批整改信息 check4； 
	 * 完成： finish finish
	 */
	private String nextOperation;
	
	/**
	 * 整改人员id
	 */
	private String rectUserId;
	
	
	public Supervice(){
	}
	
	public Supervice(String id, String areaId, String companyName, String dangerName, String dangerSort, String dangerLevel, String rectificationState,String rectificationLevel,String createUserID,String nextRoleCode,String nextOperation){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.dangerName = dangerName;

this.dangerSort = dangerSort;

this.dangerLevel = dangerLevel;

this.rectificationState = rectificationState;
this.rectificationLevel=rectificationLevel;
this.createUserID=createUserID;
this.nextRoleCode=nextRoleCode;
this.nextOperation=nextOperation;
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

	
	@Column(name="AREA_ID")
	public String getAreaId()
	{
		return this.areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="COMPANY_ID")
	public String getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
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

	@Column(name="DANGER_NAME")
	public String getDangerName()
	{
		return this.dangerName;
	}

	public void setDangerName(String dangerName)
	{
		this.dangerName = dangerName;
	}

	@Column(name="LISTING_TIME")
	public Date getListingTime()
	{
		return this.listingTime;
	}

	public void setListingTime(Date listingTime)
	{
		this.listingTime = listingTime;
	}

	@Column(name="DANGER_SORT")
	public String getDangerSort()
	{
		return this.dangerSort;
	}

	public void setDangerSort(String dangerSort)
	{
		this.dangerSort = dangerSort;
	}

	@Column(name="DANGER_LEVEL")
	public String getDangerLevel()
	{
		return this.dangerLevel;
	}

	public void setDangerLevel(String dangerLevel)
	{
		this.dangerLevel = dangerLevel;
	}

	@Column(name="DANGER_CONTENT")
	public String getDangerContent()
	{
		return this.dangerContent;
	}

	public void setDangerContent(String dangerContent)
	{
		this.dangerContent = dangerContent;
	}

	@Column(name="RESPONSIBLE")
	public String getResponsible()
	{
		return this.responsible;
	}

	public void setResponsible(String responsible)
	{
		this.responsible = responsible;
	}

	@Column(name="RESPONSIBLE_MOBILE")
	public String getResponsibleMobile()
	{
		return this.responsibleMobile;
	}

	public void setResponsibleMobile(String responsibleMobile)
	{
		this.responsibleMobile = responsibleMobile;
	}

	@Column(name="RESPONSIBLE_UNIT")
	public String getResponsibleUnit()
	{
		return this.responsibleUnit;
	}

	public void setResponsibleUnit(String responsibleUnit)
	{
		this.responsibleUnit = responsibleUnit;
	}

	@Column(name="ADDRESS")
	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name="RECTIFICATION_TERM")
	public Date getRectificationTerm()
	{
		return this.rectificationTerm;
	}

	public void setRectificationTerm(Date rectificationTerm)
	{
		this.rectificationTerm = rectificationTerm;
	}

	@Column(name="RECTIFICATION_STATE")
	public String getRectificationState()
	{
		return this.rectificationState;
	}

	public void setRectificationState(String rectificationState)
	{
		this.rectificationState = rectificationState;
	}

	@Column(name="RECTIFICATION_MONEY")
	public String getRectificationMoney()
	{
		return this.rectificationMoney;
	}

	public void setRectificationMoney(String rectificationMoney)
	{
		this.rectificationMoney = rectificationMoney;
	}

	@Column(name="RECFINISH_TIME")
	public Date getRecfinishTime()
	{
		return this.recfinishTime;
	}

	public void setRecfinishTime(Date recfinishTime)
	{
		this.recfinishTime = recfinishTime;
	}

	@Column(name="ACCEPT_TIME")
	public Date getAcceptTime()
	{
		return this.acceptTime;
	}

	public void setAcceptTime(Date acceptTime)
	{
		this.acceptTime = acceptTime;
	}

	@Column(name="DANREC_NUM")
	public String getDanrecNum()
	{
		return this.danrecNum;
	}

	public void setDanrecNum(String danrecNum)
	{
		this.danrecNum = danrecNum;
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
	@Column(name="RECTIFICATION_LEVEL")
	public String getRectificationLevel() {
		return rectificationLevel;
	}

	public void setRectificationLevel(String rectificationLevel) {
		this.rectificationLevel = rectificationLevel;
	}
	@Column(name="NEXT_ROLE_CODE")
	public String getNextRoleCode() {
		return nextRoleCode;
	}

	public void setNextRoleCode(String nextRoleCode) {
		this.nextRoleCode = nextRoleCode;
	}
	@Column(name="NEXT_OPERATION")
	public String getNextOperation() {
		return nextOperation;
	}

	public void setNextOperation(String nextOperation) {
		this.nextOperation = nextOperation;
	}
	@Column(name="RECT_USER_ID")
	public String getRectUserId() {
		return rectUserId;
	}

	public void setRectUserId(String rectUserId) {
		this.rectUserId = rectUserId;
	}
	
	

}
