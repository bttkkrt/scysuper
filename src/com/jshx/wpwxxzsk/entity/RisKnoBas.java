package com.jshx.wpwxxzsk.entity;

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
@Table(name="RIS_KNO_BAS")
public class RisKnoBas extends BaseModel
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
	 * 物品名称
	 */
	private String itemName;

	/**
	 * 危险性内容
	 */
	private String dangerousContent;

	/**
	 * 应对措施
	 */
	private String responseMeasures;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 关联id
	 */
	private String linkId;
	
	private String deptType;

	
	public RisKnoBas(){
	}
	public RisKnoBas(String id, String areaId, String companyName, String itemName,String dangerousContent,String responseMeasures,String deptType){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.itemName = itemName;
this.dangerousContent=dangerousContent;
this.responseMeasures=responseMeasures;
this.deptType=deptType;
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

	@Column(name="ITEM_NAME")
	public String getItemName()
	{
		return this.itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	@Column(name="DANGEROUS_CONTENT")
	public String getDangerousContent()
	{
		return this.dangerousContent;
	}

	public void setDangerousContent(String dangerousContent)
	{
		this.dangerousContent = dangerousContent;
	}

	@Column(name="RESPONSE_MEASURES")
	public String getResponseMeasures()
	{
		return this.responseMeasures;
	}

	public void setResponseMeasures(String responseMeasures)
	{
		this.responseMeasures = responseMeasures;
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

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}
	@Column(name="DEPT_TYPE")
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

}
