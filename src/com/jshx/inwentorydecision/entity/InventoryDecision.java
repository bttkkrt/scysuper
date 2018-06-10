package com.jshx.inwentorydecision.entity;

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
@Table(name="INVENTORY_DECISION")
public class InventoryDecision extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 处理
	 */
	private String deal;

	/**
	 * 机关负责人意见
	 */
	private String officeComment;
	
	/**
	 * 是否处罚
	 */
	private String ifcf;
	
	/**
	 * 是否移交
	 */
	private String ifyj;
	
	@Column(name="IFCF")
	public String getIfcf() {
		return ifcf;
	}

	public void setIfcf(String ifcf) {
		this.ifcf = ifcf;
	}
	@Column(name="IFYJ")
	public String getIfyj() {
		return ifyj;
	}

	public void setIfyj(String ifyj) {
		this.ifyj = ifyj;
	}

	
	public InventoryDecision(){
	}
	
	public InventoryDecision(String id, String relatedId){
this.id = id;

this.relatedId = relatedId;
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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="DEAL")
	public String getDeal()
	{
		return this.deal;
	}

	public void setDeal(String deal)
	{
		this.deal = deal;
	}

	@Column(name="OFFICE_COMMENT")
	public String getOfficeComment()
	{
		return this.officeComment;
	}

	public void setOfficeComment(String officeComment)
	{
		this.officeComment = officeComment;
	}

}
