package com.wzxx.jgdl.entity;

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
@Table(name="JGDL")
public class Jgdl extends BaseModel
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
	 * 发布时间
	 */
	private Date publicDate;

	/**
	 * 标题
	 */
	private String infoTitle;

	/**
	 * 信息内容
	 */
	private String infoContent;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 点击量
	 */
	private int clickTime;
	
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
	public Jgdl(){
	}
	
	public Jgdl(String id, Date publicDate, String infoTitle,int clickTime){
this.id = id;

this.publicDate = publicDate;

this.infoTitle = infoTitle;
this.clickTime = clickTime;
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

	
	@Column(name="PUBLIC_DATE")
	public Date getPublicDate()
	{
		return this.publicDate;
	}

	public void setPublicDate(Date publicDate)
	{
		this.publicDate = publicDate;
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
	@Column(name="CLICK_TIME")
	public int getClickTime() {
		return clickTime;
	}

	public void setClickTime(int clickTime) {
		this.clickTime = clickTime;
	}

}
