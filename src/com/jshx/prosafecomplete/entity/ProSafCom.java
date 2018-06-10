package com.jshx.prosafecomplete.entity;

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
@Table(name="PRO_SAF_COM")
public class ProSafCom extends BaseModel
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
	 * 评价单位
	 */
	private String evaluationUnit;

	/**
	 * 项目内容
	 */
	private String projectContent;

	/**
	 * 项目性质
	 */
	private String projectNature;

	/**
	 * 项目类型
	 */
	private String projectType;

	/**
	 * 验收专家
	 */
	private String acceptanceExpert;

	/**
	 * 验收日期
	 */
	private Date acceptanceDate;

	/**
	 * 审批编号
	 */
	private String approvalNum;

	/**
	 * 审批日期
	 */
	private Date approvalDate;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;

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

	
	public ProSafCom(){
	}
	
	public ProSafCom(String id, String evaluationUnit, String projectNature, String projectType, String acceptanceExpert, Date acceptanceDate, String approvalNum, Date approvalDate, String areaName, String companyName,String createUserID){
this.id = id;

this.evaluationUnit = evaluationUnit;

this.projectNature = projectNature;

this.projectType = projectType;

this.acceptanceExpert = acceptanceExpert;

this.acceptanceDate = acceptanceDate;

this.approvalNum = approvalNum;

this.approvalDate = approvalDate;

this.areaName = areaName;

this.companyName = companyName;

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

	
	@Column(name="EVALUATION_UNIT")
	public String getEvaluationUnit()
	{
		return this.evaluationUnit;
	}

	public void setEvaluationUnit(String evaluationUnit)
	{
		this.evaluationUnit = evaluationUnit;
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

	@Column(name="PROJECT_TYPE")
	public String getProjectType()
	{
		return this.projectType;
	}

	public void setProjectType(String projectType)
	{
		this.projectType = projectType;
	}

	@Column(name="ACCEPTANCE_EXPERT")
	public String getAcceptanceExpert()
	{
		return this.acceptanceExpert;
	}

	public void setAcceptanceExpert(String acceptanceExpert)
	{
		this.acceptanceExpert = acceptanceExpert;
	}

	@Column(name="ACCEPTANCE_DATE")
	public Date getAcceptanceDate()
	{
		return this.acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate)
	{
		this.acceptanceDate = acceptanceDate;
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

	@Column(name="APPROVAL_DATE")
	public Date getApprovalDate()
	{
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate)
	{
		this.approvalDate = approvalDate;
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

}
