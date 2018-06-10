package com.jshx.jdwpglb.entity;

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
@Table(name="IDENTIFY_ITEM_ASSOCIATE")
public class IdentifyItemAssociate extends BaseModel
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
	 * 关联委托书编号
	 */
	private String attorenyId;

	/**
	 * 物品名称
	 */
	private String itemName;

	/**
	 * 规格型号
	 */
	private String specificationModel;

	/**
	 * 数量
	 */
	private String identifyNum;

	/**
	 * 备注
	 */
	private String remark;

	
	public IdentifyItemAssociate(){
	}
	
	public IdentifyItemAssociate(String id, String itemName, String specificationModel, String identifyNum){
this.id = id;

this.itemName = itemName;

this.specificationModel = specificationModel;

this.identifyNum = identifyNum;
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

	
	@Column(name="ATTORENY_ID")
	public String getAttorenyId()
	{
		return this.attorenyId;
	}

	public void setAttorenyId(String attorenyId)
	{
		this.attorenyId = attorenyId;
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

	@Column(name="SPECIFICATION_MODEL")
	public String getSpecificationModel()
	{
		return this.specificationModel;
	}

	public void setSpecificationModel(String specificationModel)
	{
		this.specificationModel = specificationModel;
	}

	@Column(name="IDENTIFY_NUM")
	public String getIdentifyNum()
	{
		return this.identifyNum;
	}

	public void setIdentifyNum(String identifyNum)
	{
		this.identifyNum = identifyNum;
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

}
