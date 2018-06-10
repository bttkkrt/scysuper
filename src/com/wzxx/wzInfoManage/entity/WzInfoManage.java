package com.wzxx.wzInfoManage.entity;

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
@Table(name="WZ_INFO_MANAGE")
public class WzInfoManage extends BaseModel
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
	 * 标题
	 */
	private String infotitle;

	/**
	 * 信息内容
	 */
	private String infoContent;

	/**
	 * 信息类型
	 * 1:安委会工作,2:政务互动,3:专题报道,4:媒体关注,5:职业卫生,6:组织机构,7:信息公开,8:行证许可公示,9:办事流程
	 */
	private String infoType;

	/**
	 * 点击量
	 */
	private int  clickTime;

	/**
	 * 关联id
	 */
	private String linkId;
	
	private User user;
	
	private String category;//行政许可类别
	
	private String companyName;//行政许可申报企业

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
	
	public WzInfoManage(){
	}
	
	public WzInfoManage(String id, String infotitle, String infoType, int clickTime,User user,Date createTime,Date updateTime,String category,String companyName){
this.id = id;

this.infotitle = infotitle;

this.infoType = infoType;

this.clickTime = clickTime;
this.user=user;
this.createTime=createTime;
this.updateTime=updateTime;
this.category=category;
this.companyName=companyName;
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

	
	@Column(name="INFOTITLE")
	public String getInfotitle()
	{
		return this.infotitle;
	}

	public void setInfotitle(String infotitle)
	{
		this.infotitle = infotitle;
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

	@Column(name="INFO_TYPE")
	public String getInfoType()
	{
		return this.infoType;
	}

	public void setInfoType(String infoType)
	{
		this.infoType = infoType;
	}

	@Column(name="CLICK_TIME")
	public int getClickTime()
	{
		return this.clickTime;
	}

	public void setClickTime(int clickTime)
	{
		this.clickTime = clickTime;
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
	
	/**
	 * 获取发布人
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=User.class)
	@JoinColumn(name = "CREATEUSERID",updatable=false,insertable=false)
	public User getUser() {
		return user;
	}
	/**
	 * 设置发布人
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="CATEGORY")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name="COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
