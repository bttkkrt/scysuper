package com.jshx.aqssdjtz.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SAF_LED")
public class SafLed extends BaseModel
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
	 * 所在区域id
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 台账类型
	 */
	private String accountType;

	/**
	 * 类别
	 */
	private String category;
	
	/**
	 * 类别1
	 */
	private String category1;
	/**
	 * 类别2
	 */
	private String category2;
	/**
	 * 类别3
	 */
	private String category3;

	/**
	 * 关联id
	 */
	private String linkId;

	
	public SafLed(){
	}
	
	public SafLed(String id,String areaName, String companyName, String accountType,String category){
this.id = id;

this.areaName = areaName;
this.companyName = companyName;

this.accountType = accountType;

this.category = category;
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

	@Column(name="ACCOUNT_TYPE")
	public String getAccountType()
	{
		return this.accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	@Column(name="CATEGORY")
	public String getCategory()
	{
		return this.category;
	}

	public void setCategory(String category)
	{
		this.category = category;
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
	@Transient
	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	@Transient
	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	@Transient
	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

}
