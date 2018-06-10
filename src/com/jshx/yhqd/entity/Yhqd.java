package com.jshx.yhqd.entity;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.core.base.entity.BaseModel;
import com.jshx.photo.entity.PhotoPic;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="YHQD")
public class Yhqd extends BaseModel
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
	 * 检查时间
	 */
	private Date checktime;

	/**
	 * 隐患内容
	 */
	private String yhContent;

	/**
	 * 监管部门跟踪责任人
	 */
	private String jgzrrNames;
	/**
	 * 监管部门跟踪责任人
	 */
	private String jgzrrIds;

	/**
	 * 企业责任人
	 */
	private String qyzrr;

	/**
	 * 整改期限
	 */
	private Date zgqx;

	/**
	 * 所需资金
	 */
	private String sxzj;

	/**
	 * 整改方案
	 */
	private String zgfa;

	/**
	 * 防范措施
	 */
	private String ffcs;

	/**
	 * 基本表ID
	 */
	private CheckBasic basic;

	/**
	 * 企业责任人联系电话
	 */
	private String qyzrrlxdh;

	/**
	 * 整改验收人
	 */
	private String zgysr;

	/**
	 * 验收时间
	 */
	private String yssj;

	/**
	 * 结论
	 */
	private String resultContent;

	/**
	 * 是否结束流程
	 * 开启为0，结束为1，无隐患默认结束
	 */ 
	private Integer ended;
	/**
	 * 隐患是否处理了
	 * 0未处理，1已处理待验收 
	 */ 
	private Integer dealFlag;

	/**
	 * 是否通过整改
	 * 0，不通过；1通过；空未处理
	 */
	private Integer passFlag;
	
	//隐患附件
	private List<PhotoPic> picList;
	//整改附件
	private List<PhotoPic> zgpicList;
	
	/**
	 * 整改说明
	 */
	private String zgInfo;
	/**
	 *  流程id
	 */
	private String defId; 
	@Column(name="DEFID")  
	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
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

	@Column(name="CHECKTIME")
	public Date getChecktime()
	{
		return this.checktime;
	}

	public void setChecktime(Date checktime)
	{
		this.checktime = checktime;
	}

	@Column(name="YH_CONTENT")
	public String getYhContent()
	{
		return this.yhContent;
	}

	public void setYhContent(String yhContent)
	{
		this.yhContent = yhContent;
	}
 

	/**  
	 * 获取jgzrrNames  
	 * @return jgzrrNames    
	 */
	@Column(name="JGZRRNAMES")
	public String getJgzrrNames() {
		return jgzrrNames;
	}

	/**  
	 * 设置jgzrrNames  
	 * @param jgzrrNames jgzrrNames  
	 */
	public void setJgzrrNames(String jgzrrNames) {
		this.jgzrrNames = jgzrrNames;
	}

	/**  
	 * 获取jgzrrIds  
	 * @return jgzrrIds    
	 */
	@Column(name="JGZRRIDS")
	public String getJgzrrIds() {
		return jgzrrIds;
	}

	/**  
	 * 设置jgzrrIds  
	 * @param jgzrrIds jgzrrIds  
	 */
	public void setJgzrrIds(String jgzrrIds) {
		this.jgzrrIds = jgzrrIds;
	}

	@Column(name="QYZRR")
	public String getQyzrr()
	{
		return this.qyzrr;
	}

	public void setQyzrr(String qyzrr)
	{
		this.qyzrr = qyzrr;
	}

	@Column(name="ZGQX")
	public Date getZgqx()
	{
		return this.zgqx;
	}

	public void setZgqx(Date zgqx)
	{
		this.zgqx = zgqx;
	}

	@Column(name="SXZJ")
	public String getSxzj()
	{
		return this.sxzj;
	}

	public void setSxzj(String sxzj)
	{
		this.sxzj = sxzj;
	}

	@Column(name="ZGFA")
	public String getZgfa()
	{
		return this.zgfa;
	}

	public void setZgfa(String zgfa)
	{
		this.zgfa = zgfa;
	}

	@Column(name="FFCS")
	public String getFfcs()
	{
		return this.ffcs;
	}

	public void setFfcs(String ffcs)
	{
		this.ffcs = ffcs;
	}

	@ManyToOne(fetch=FetchType.EAGER, targetEntity=CheckBasic.class)
    @JoinColumn(name="BASIC_ID")
	public CheckBasic getBasic()
	{
		return basic;
	}

	public void setBasic(CheckBasic basic)
	{
		this.basic = basic;
	}

	@Column(name="QYZRRLXDH")
	public String getQyzrrlxdh()
	{
		return this.qyzrrlxdh;
	}

	public void setQyzrrlxdh(String qyzrrlxdh)
	{
		this.qyzrrlxdh = qyzrrlxdh;
	}

	@Column(name="ZGYSR")
	public String getZgysr()
	{
		return this.zgysr;
	}

	public void setZgysr(String zgysr)
	{
		this.zgysr = zgysr;
	}

	@Column(name="YSSJ")
	public String getYssj()
	{
		return this.yssj;
	}

	public void setYssj(String yssj)
	{
		this.yssj = yssj;
	}

	@Column(name="RESULT_CONTENT")
	public String getResultContent()
	{
		return this.resultContent;
	}

	public void setResultContent(String resultContent)
	{
		this.resultContent = resultContent;
	}

	@Column(name="ENDED")
	public Integer getEnded()
	{
		return this.ended;
	}

	public void setEnded(Integer ended)
	{
		this.ended = ended;
	}

	@Column(name="PASSFLAG")
	public Integer getPassFlag()
	{
		return this.passFlag;
	}

	public void setPassFlag(Integer passFlag)
	{
		this.passFlag = passFlag;
	}

	/**  
	 * 获取dealFlag  
	 * @return dealFlag    
	 */
	@Column(name="DEALFLAG")
	public Integer getDealFlag() {
		return dealFlag;
	}

	/**  
	 * 设置dealFlag  
	 * @param dealFlag    
	 */
	public void setDealFlag(Integer dealFlag) {
		this.dealFlag = dealFlag;
	}
	@Transient
	public List<PhotoPic> getPicList()
	{
		return picList;
	}

	/**  
	 * 设置picList  
	 * @param picList    
	 */
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}

	/**  
	 * 获取zgpicList  
	 * @return zgpicList    
	 */
	@Transient
	public List<PhotoPic> getZgpicList() {
		return zgpicList;
	}

	/**  
	 * 设置zgpicList  
	 * @param zgpicList    
	 */
	public void setZgpicList(List<PhotoPic> zgpicList) {
		this.zgpicList = zgpicList;
	}

	/**  
	 * 获取zgInfo  
	 * @return zgInfo    
	 */
	public String getZgInfo() {
		return zgInfo;
	}

	/**  
	 * 设置zgInfo  
	 * @param zgInfo    
	 */
	public void setZgInfo(String zgInfo) {
		this.zgInfo = zgInfo;
	}
	
	

}
