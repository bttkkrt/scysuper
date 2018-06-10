package com.jshx.zyjktj.entity;

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
@Table(name="OCC_HEA_EXA")
public class OccHeaExa extends BaseModel
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
	 * 体检机构
	 */
	private String medicalInstitution;
	
	/**
	 * 体检机构名称
	 */
	private String medicalInstitutionName;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 职业病危害因素名称
	 */
	private String occupationalDiseasName;

	/**
	 * 岗前职业健康体检人数
	 */
	private String preOccupationHealthNumber;

	/**
	 * 岗中职业健康体检人数
	 */
	private String postOccupationalHealth;

	/**
	 * 离岗职业健康体检人数
	 */
	private String postOccupationHealthNumber;

	/**
	 * 体检发现应调岗离岗人数
	 */
	private String foundPostsNumber;

	/**
	 * 实际调离岗位人数
	 */
	private String actualPositionNumber;

	
	public OccHeaExa(){
	}
	
	public OccHeaExa(String id,String areaId, String companyName, String medicalInstitution,String medicalInstitutionName,Date createTime){
this.id = id;
this.areaId=areaId;
this.companyName = companyName;

this.medicalInstitution = medicalInstitution;
this.medicalInstitutionName=medicalInstitutionName;
this.createTime=createTime;
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

	@Column(name="MEDICAL_INSTITUTION")
	public String getMedicalInstitution()
	{
		return this.medicalInstitution;
	}

	public void setMedicalInstitution(String medicalInstitution)
	{
		this.medicalInstitution = medicalInstitution;
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

	@Column(name="OCCUPATIONAL_DISEAS_NAME")
	public String getOccupationalDiseasName()
	{
		return this.occupationalDiseasName;
	}

	public void setOccupationalDiseasName(String occupationalDiseasName)
	{
		this.occupationalDiseasName = occupationalDiseasName;
	}

	@Column(name="PRE_OCCUPATION_HEALTH_NUMBER")
	public String getPreOccupationHealthNumber()
	{
		return this.preOccupationHealthNumber;
	}

	public void setPreOccupationHealthNumber(String preOccupationHealthNumber)
	{
		this.preOccupationHealthNumber = preOccupationHealthNumber;
	}

	@Column(name="POST_OCCUPATIONAL_HEALTH")
	public String getPostOccupationalHealth()
	{
		return this.postOccupationalHealth;
	}

	public void setPostOccupationalHealth(String postOccupationalHealth)
	{
		this.postOccupationalHealth = postOccupationalHealth;
	}

	@Column(name="POST_OCCUPATION_HEALTH_NUMBER")
	public String getPostOccupationHealthNumber()
	{
		return this.postOccupationHealthNumber;
	}

	public void setPostOccupationHealthNumber(String postOccupationHealthNumber)
	{
		this.postOccupationHealthNumber = postOccupationHealthNumber;
	}

	@Column(name="FOUND_POSTS_NUMBER")
	public String getFoundPostsNumber()
	{
		return this.foundPostsNumber;
	}

	public void setFoundPostsNumber(String foundPostsNumber)
	{
		this.foundPostsNumber = foundPostsNumber;
	}

	@Column(name="ACTUAL_POSITION_NUMBER")
	public String getActualPositionNumber()
	{
		return this.actualPositionNumber;
	}

	public void setActualPositionNumber(String actualPositionNumber)
	{
		this.actualPositionNumber = actualPositionNumber;
	}
	@Column(name="MEDICAL_INSTITUTION_NAME")
	public String getMedicalInstitutionName() {
		return medicalInstitutionName;
	}

	public void setMedicalInstitutionName(String medicalInstitutionName) {
		this.medicalInstitutionName = medicalInstitutionName;
	}

}
