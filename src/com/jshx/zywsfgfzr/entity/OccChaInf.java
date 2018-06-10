package com.jshx.zywsfgfzr.entity;

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
@Table(name="OCC_CHA_INF")
public class OccChaInf extends BaseModel
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
	 * 姓名
	 */
	private String jshxName;

	/**
	 * 培训日期(开始)
	 */
	private Date trainingDateStart;
	
	/**
	 * 培训日期(结束)
	 */
	private Date trainingDateEnd;

	/**
	 * 培训合格证号
	 */
	private String trainingCertificatNumber;

	/**
	 * 职务
	 */
	private String duty;

	/**
	 * 办公电话
	 */
	private String telephone;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 文化程度
	 */
	private String degreeEducation;

	/**
	 * 专业
	 */
	private String professional;
	
	/**
	 * 职业卫生管理基本信息id
	 */
	private String occHeaInfoId;

	
	public OccChaInf(){
	}
	public OccChaInf(String id, String jshxName, Date trainingDateStart,Date trainingDateEnd, String trainingCertificatNumber, String duty, String telephone, String mobile, String degreeEducation, String professional){
this.id = id;

this.jshxName = jshxName;

this.trainingDateStart = trainingDateStart;
this.trainingDateEnd = trainingDateEnd;

this.trainingCertificatNumber = trainingCertificatNumber;

this.duty = duty;

this.telephone = telephone;

this.mobile = mobile;

this.degreeEducation = degreeEducation;

this.professional = professional;
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

	
	@Column(name="JSHX_NAME")
	public String getJshxName()
	{
		return this.jshxName;
	}

	public void setJshxName(String jshxName)
	{
		this.jshxName = jshxName;
	}

	@Column(name="TRAINING_DATE_START")
	public Date getTrainingDateStart() {
		return trainingDateStart;
	}
	public void setTrainingDateStart(Date trainingDateStart) {
		this.trainingDateStart = trainingDateStart;
	}
	@Column(name="TRAINING_DATE_END")
	public Date getTrainingDateEnd() {
		return trainingDateEnd;
	}
	public void setTrainingDateEnd(Date trainingDateEnd) {
		this.trainingDateEnd = trainingDateEnd;
	}
	@Column(name="TRAINING_CERTIFICAT_NUMBER")
	public String getTrainingCertificatNumber()
	{
		return this.trainingCertificatNumber;
	}

	public void setTrainingCertificatNumber(String trainingCertificatNumber)
	{
		this.trainingCertificatNumber = trainingCertificatNumber;
	}

	@Column(name="DUTY")
	public String getDuty()
	{
		return this.duty;
	}

	public void setDuty(String duty)
	{
		this.duty = duty;
	}

	@Column(name="TELEPHONE")
	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	@Column(name="MOBILE")
	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	@Column(name="DEGREE_EDUCATION")
	public String getDegreeEducation()
	{
		return this.degreeEducation;
	}

	public void setDegreeEducation(String degreeEducation)
	{
		this.degreeEducation = degreeEducation;
	}

	@Column(name="PROFESSIONAL")
	public String getProfessional()
	{
		return this.professional;
	}

	public void setProfessional(String professional)
	{
		this.professional = professional;
	}
	@Column(name="OCCHEAINFO_ID")
	public String getOccHeaInfoId() {
		return occHeaInfoId;
	}

	public void setOccHeaInfoId(String occHeaInfoId) {
		this.occHeaInfoId = occHeaInfoId;
	}

	
}
