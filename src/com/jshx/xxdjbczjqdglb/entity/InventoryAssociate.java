package com.jshx.xxdjbczjqdglb.entity;

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
@Table(name="INVENTORY_ASSOCIATE")
public class InventoryAssociate extends BaseModel
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
	 * 关联id
	 */
	private String relatedId;

	
	/**
	 * 证据名称
	 */
	private String evidenceName;

	/**
	 * 规格型号
	 */
	private String specificationModel;

	/**
	 * 产地
	 */
	private String originPlace;

	/**
	 * 成色
	 */
	private String condition;

	/**
	 * 单位
	 */
	private String company;

	/**
	 * 价格
	 */
	private String price;

	/**
	 * 数量
	 */
	private String inventoryNum;

	/**
	 * 备注
	 */
	private String remark;

	
	public InventoryAssociate(){
	}
	
	public InventoryAssociate(String id, String evidenceName, String specificationModel, String originPlace, String condition, String price, String inventoryNum){
this.id = id;

this.evidenceName = evidenceName;

this.specificationModel = specificationModel;

this.originPlace = originPlace;

this.condition = condition;

this.price = price;

this.inventoryNum = inventoryNum;
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

	
	@Column(name="EVIDENCE_NAME")
	public String getEvidenceName()
	{
		return this.evidenceName;
	}

	public void setEvidenceName(String evidenceName)
	{
		this.evidenceName = evidenceName;
	}

	@Column(name="SPECIFICATION_MODEL")
	public String getSpecificationModel()
	{
		return this.specificationModel;
	}

	public void setSpecificationModel(String specificationModel)
	{
		this.specificationModel = specificationModel;
	}

	@Column(name="ORIGIN_PLACE")
	public String getOriginPlace()
	{
		return this.originPlace;
	}

	public void setOriginPlace(String originPlace)
	{
		this.originPlace = originPlace;
	}

	@Column(name="CONDITION")
	public String getCondition()
	{
		return this.condition;
	}

	public void setCondition(String condition)
	{
		this.condition = condition;
	}

	@Column(name="COMPANY")
	public String getCompany()
	{
		return this.company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	@Column(name="PRICE")
	public String getPrice()
	{
		return this.price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	@Column(name="INVENTORY_NUM")
	public String getInventoryNum()
	{
		return this.inventoryNum;
	}

	public void setInventoryNum(String inventoryNum)
	{
		this.inventoryNum = inventoryNum;
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
	@Column(name="RELATED_ID")
	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

}
