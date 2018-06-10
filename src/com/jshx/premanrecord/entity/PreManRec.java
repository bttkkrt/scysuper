package com.jshx.premanrecord.entity;

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
@Table(name="PRE_MAN_REC")
public class PreManRec extends BaseModel
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
	 * 材料接收人员
	 */
	private String receptName;

	/**
	 * 材料审查人员
	 */
	private String reviewName;

	/**
	 * 档案编号
	 */
	private String fileNo;

	/**
	 * 申请材料是否齐全
	 */
	private String isComplete;

	/**
	 * 签字领导
	 */
	private String signLeader;

	/**
	 * 材料审查情况
	 */
	private String materialReview;

	/**
	 * 本次备案情况
	 */
	private String filingCase;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public PreManRec(){
	}
	
	public PreManRec(String id, String areaId, String companyName, String receptName, String reviewName, String fileNo, String isComplete,String createUserID){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.receptName = receptName;

this.reviewName = reviewName;

this.fileNo = fileNo;

this.isComplete = isComplete;
this.createUserID=createUserID;
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

	@Column(name="RECEPT_NAME")
	public String getReceptName()
	{
		return this.receptName;
	}

	public void setReceptName(String receptName)
	{
		this.receptName = receptName;
	}

	@Column(name="REVIEW_NAME")
	public String getReviewName()
	{
		return this.reviewName;
	}

	public void setReviewName(String reviewName)
	{
		this.reviewName = reviewName;
	}

	@Column(name="FILE_NO")
	public String getFileNo()
	{
		return this.fileNo;
	}

	public void setFileNo(String fileNo)
	{
		this.fileNo = fileNo;
	}

	@Column(name="IS_COMPLETE")
	public String getIsComplete()
	{
		return this.isComplete;
	}

	public void setIsComplete(String isComplete)
	{
		this.isComplete = isComplete;
	}

	@Column(name="SIGN_LEADER")
	public String getSignLeader()
	{
		return this.signLeader;
	}

	public void setSignLeader(String signLeader)
	{
		this.signLeader = signLeader;
	}

	@Column(name="MATERIAL_REVIEW")
	public String getMaterialReview()
	{
		return this.materialReview;
	}

	public void setMaterialReview(String materialReview)
	{
		this.materialReview = materialReview;
	}

	@Column(name="FILING_CASE")
	public String getFilingCase()
	{
		return this.filingCase;
	}

	public void setFilingCase(String filingCase)
	{
		this.filingCase = filingCase;
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

}
