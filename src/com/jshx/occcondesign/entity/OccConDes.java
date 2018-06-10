package com.jshx.occcondesign.entity;

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
@Table(name="OCC_CON_DES")
public class OccConDes extends BaseModel
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
	 * 行业类别
	 */
	private String industryCategory;

	/**
	 * 职业病危害风险分类
	 */
	private String occupationalClassification;

	/**
	 * 设计单位
	 */
	private String designUnit;

	/**
	 * 项目内容
	 */
	private String projectContent;

	/**
	 * 项目性质
	 */
	private String projectNature;

	/**
	 * 材料接收人员
	 */
	private String receptName;

	/**
	 * 材料审查人员
	 */
	private String reviewName;

	/**
	 * 审查专家
	 */
	private String expertReview;

	/**
	 * 审查结果
	 */
	private String review;

	/**
	 * 审查日期
	 */
	private Date reviewDate;

	/**
	 * 审批日期
	 */
	private Date approvalDate;

	/**
	 * 审批编号
	 */
	private String approvalNum;

	/**
	 * 档案编号
	 */
	private String fileNo;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public OccConDes(){
	}
	
	public OccConDes(String id, String areaName, String companyName, String industryCategory, String designUnit, String receptName, String reviewName, String expertReview, String review, Date reviewDate,String createUserID){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.industryCategory = industryCategory;

this.designUnit = designUnit;

this.receptName = receptName;

this.reviewName = reviewName;

this.expertReview = expertReview;

this.review = review;

this.reviewDate = reviewDate;
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

	@Column(name="INDUSTRY_CATEGORY")
	public String getIndustryCategory()
	{
		return this.industryCategory;
	}

	public void setIndustryCategory(String industryCategory)
	{
		this.industryCategory = industryCategory;
	}

	@Column(name="OCCUPATIONAL_CLASSIFICATION")
	public String getOccupationalClassification()
	{
		return this.occupationalClassification;
	}

	public void setOccupationalClassification(String occupationalClassification)
	{
		this.occupationalClassification = occupationalClassification;
	}

	@Column(name="DESIGN_UNIT")
	public String getDesignUnit()
	{
		return this.designUnit;
	}

	public void setDesignUnit(String designUnit)
	{
		this.designUnit = designUnit;
	}

	@Column(name="PROJECT_CONTENT")
	public String getProjectContent()
	{
		return this.projectContent;
	}

	public void setProjectContent(String projectContent)
	{
		this.projectContent = projectContent;
	}

	@Column(name="PROJECT_NATURE")
	public String getProjectNature()
	{
		return this.projectNature;
	}

	public void setProjectNature(String projectNature)
	{
		this.projectNature = projectNature;
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

	@Column(name="EXPERT_REVIEW")
	public String getExpertReview()
	{
		return this.expertReview;
	}

	public void setExpertReview(String expertReview)
	{
		this.expertReview = expertReview;
	}

	@Column(name="REVIEW")
	public String getReview()
	{
		return this.review;
	}

	public void setReview(String review)
	{
		this.review = review;
	}

	@Column(name="REVIEW_DATE")
	public Date getReviewDate()
	{
		return this.reviewDate;
	}

	public void setReviewDate(Date reviewDate)
	{
		this.reviewDate = reviewDate;
	}

	@Column(name="APPROVAL_DATE")
	public Date getApprovalDate()
	{
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate)
	{
		this.approvalDate = approvalDate;
	}

	@Column(name="APPROVAL_NUM")
	public String getApprovalNum()
	{
		return this.approvalNum;
	}

	public void setApprovalNum(String approvalNum)
	{
		this.approvalNum = approvalNum;
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
