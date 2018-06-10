package com.wzxx.xyjc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="XYJC")
public class Xyjc extends BaseModel
{
	private String name;
	
	private String sex;
	
	private String address;
	
	private String mobile;
	
	private String mail;
	
	/**
	 * 标题
	 */
	private String infoTitle;

	/**
	 * 信息内容
	 */
	private String infoContent;


	/**
	 * 删除标志
	 */
	private String delFlag;

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
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
