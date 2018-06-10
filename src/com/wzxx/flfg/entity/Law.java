package com.wzxx.flfg.entity;

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
@Table(name="LAW")
public class Law extends BaseModel
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
	 * 信息名称
	 */
	private String infoTitle;

	/**
	 * 发布机构
	 */
	private String publicAgency;

	/**
	 * 索引号
	 */
	private String numbers;

	/**
	 * 生成日期
	 */
	private Date generateDate;

	/**
	 * 发布日期
	 */
	private Date publicDate;

	/**
	 * 发布时间
	 */
	private Date publicTime;

	/**
	 * 内容概述
	 */
	private String remark;

	/**
	 * 信息内容
	 */
	private String infoContent;
	
	private String linkId;
	
	/**
	 * 信息内容
	 */
   private String type;
   
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
	
	public Law(){
	}
	
	public Law(String id, String infoTitle, String publicAgency, String numbers, Date publicTime){
this.id = id;

this.infoTitle = infoTitle;

this.publicAgency = publicAgency;

this.numbers = numbers;

this.publicTime = publicTime;
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

	
	@Column(name="INFO_TITLE")
	public String getInfoTitle()
	{
		return this.infoTitle;
	}

	public void setInfoTitle(String infoTitle)
	{
		this.infoTitle = infoTitle;
	}

	@Column(name="PUBLIC_AGENCY")
	public String getPublicAgency()
	{
		return this.publicAgency;
	}

	public void setPublicAgency(String publicAgency)
	{
		this.publicAgency = publicAgency;
	}

	@Column(name="NUMBERS")
	public String getNumbers()
	{
		return this.numbers;
	}

	public void setNumbers(String numbers)
	{
		this.numbers = numbers;
	}

	@Column(name="GENERATE_DATE")
	public Date getGenerateDate()
	{
		return this.generateDate;
	}

	public void setGenerateDate(Date generateDate)
	{
		this.generateDate = generateDate;
	}

	@Column(name="PUBLIC_DATE")
	public Date getPublicDate()
	{
		return this.publicDate;
	}

	public void setPublicDate(Date publicDate)
	{
		this.publicDate = publicDate;
	}

	@Column(name="PUBLIC_TIME")
	public Date getPublicTime()
	{
		return this.publicTime;
	}

	public void setPublicTime(Date publicTime)
	{
		this.publicTime = publicTime;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name="INFO_CONTENT")
	public String getInfoContent()
	{
		return this.infoContent;
	}

	public void setInfoContent(String infoContent)
	{
		this.infoContent = infoContent;
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
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
