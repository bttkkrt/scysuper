package com.jshx.tjbhgjl.entity;

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
@Table(name="PHY_UNQ_REC")
public class PhyUnqRec extends BaseModel
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
	 * 身份证
	 */
	private String identification;

	/**
	 * 姓名
	 */
	private String jshxName;

	/**
	 * 体检类型
	 */
	private String physicalExaminatioType;

	/**
	 * 体检日期
	 */
	private Date medicalExaminationDate;

	/**
	 * 体检机构
	 */
	private String medicalInstitution;

	/**
	 * 体检结果
	 */
	private String physicalExaminatioResults;

	/**
	 * 职业禁忌岗位
	 */
	private String occupationalTabooPost;

	
	public PhyUnqRec(){
	}
	
	public PhyUnqRec(String id,String areaName, String companyName, String identification, String jshxName, Date medicalExaminationDate, String physicalExaminatioResults){
this.id = id;

this.companyName = companyName;

this.areaName = areaName;

this.identification = identification;

this.jshxName = jshxName;

this.medicalExaminationDate = medicalExaminationDate;

this.physicalExaminatioResults = physicalExaminatioResults;
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

	@Column(name="IDENTIFICATION")
	public String getIdentification()
	{
		return this.identification;
	}

	public void setIdentification(String identification)
	{
		this.identification = identification;
	}

	@Column(name="JSHX_NAME")
	public String getJshxName()
	{
		return this.jshxName;
	}

	public void setJshxName(String jshxName)
	{
		this.jshxName = jshxName;
	}

	@Column(name="PHYSICAL_EXAMINATIO_TYPE")
	public String getPhysicalExaminatioType()
	{
		return this.physicalExaminatioType;
	}

	public void setPhysicalExaminatioType(String physicalExaminatioType)
	{
		this.physicalExaminatioType = physicalExaminatioType;
	}

	@Column(name="MEDICAL_EXAMINATION_DATE")
	public Date getMedicalExaminationDate()
	{
		return this.medicalExaminationDate;
	}

	public void setMedicalExaminationDate(Date medicalExaminationDate)
	{
		this.medicalExaminationDate = medicalExaminationDate;
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

	@Column(name="PHYSICAL_EXAMINATIO_RESULTS")
	public String getPhysicalExaminatioResults()
	{
		return this.physicalExaminatioResults;
	}

	public void setPhysicalExaminatioResults(String physicalExaminatioResults)
	{
		this.physicalExaminatioResults = physicalExaminatioResults;
	}

	@Column(name="OCCUPATIONAL_TABOO_POST")
	public String getOccupationalTabooPost()
	{
		return this.occupationalTabooPost;
	}

	public void setOccupationalTabooPost(String occupationalTabooPost)
	{
		this.occupationalTabooPost = occupationalTabooPost;
	}

}
