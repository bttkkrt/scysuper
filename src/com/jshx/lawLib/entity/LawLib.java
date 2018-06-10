package com.jshx.lawLib.entity;

//import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="LAW_LIB")
public class LawLib extends BaseModel
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
	 * 法律法规名称
	 */
	private String lawName;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 状态
	 * 企业添加：0，安监添加：1
	 */
	private String state;

	/**
	 * 企业id
	 */
	private String companyId;
	
	/**
	 * 类型  1：国家法律:2：部门规章:3：地方规章 4：其他
	 */
	private String type;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 索引号
	 */
	private String syh;
	
	/**
	 * 发布机构
	 */
	private String fbjg;
	
	/**
	 * 生成日期
	 */
	private Date createDay;
	
	/**
	 * 发布日期
	 */
	private Date pubDay;
	
	/**
	 * 内容概述
	 */
	private String gs;
	
	
	private String url;
	
	public LawLib(){
	}
	
	public LawLib(String id, String lawName, String state,String companyId,String type){
this.id = id;

this.lawName = lawName;

this.state = state;
this.companyId=companyId;
this.type = type;
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

	
	@Column(name="LAW_NAME")
	public String getLawName()
	{
		return this.lawName;
	}

	public void setLawName(String lawName)
	{
		this.lawName = lawName;
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

	@Column(name="STATE")
	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Column(name="COMPANY_ID")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column
	public String getSyh() {
		return syh;
	}

	public void setSyh(String syh) {
		this.syh = syh;
	}
	@Column
	public String getFbjg() {
		return fbjg;
	}

	public void setFbjg(String fbjg) {
		this.fbjg = fbjg;
	}
	@Column
	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}
	@Column
	public Date getPubDay() {
		return pubDay;
	}

	public void setPubDay(Date pubDay) {
		this.pubDay = pubDay;
	}
	@Column
	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}
	@Transient
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
