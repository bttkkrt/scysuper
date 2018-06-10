package com.jshx.aqsczjk.entity;

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
@Table(name="SAFETY_EXPERTS")
public class SafetyExperts extends BaseModel
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
	private String safetyName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 出生年月
	 */
	private Date birth;

	/**
	 * 所学专业
	 */
	private String professional;

	/**
	 * 学历
	 */
	private String education;

	/**
	 * 毕业时间
	 */
	private Date graduationTime;

	/**
	 * 职称
	 */
	private String jobTitle;

	/**
	 * 工作单位
	 */
	private String employer;

	/**
	 * 专长
	 */
	private String specialty;

	/**
	 * 工作记录
	 */
	private String workRecord;

	/**
	 * 教育情况
	 */
	private String educationSec;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 联系电话
	 */
	private String mobile;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	
	public SafetyExperts(){
	}
	
	public SafetyExperts(String id, String safetyName, String education, String jobTitle, String mobile,String createUserID){
this.id = id;

this.safetyName = safetyName;

this.education = education;

this.jobTitle = jobTitle;

this.mobile = mobile;
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

	
	@Column(name="SAFETY_NAME")
	public String getSafetyName()
	{
		return this.safetyName;
	}

	public void setSafetyName(String safetyName)
	{
		this.safetyName = safetyName;
	}

	@Column(name="SEX")
	public String getSex()
	{
		return this.sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	@Column(name="BIRTH")
	public Date getBirth()
	{
		return this.birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
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

	@Column(name="EDUCATION")
	public String getEducation()
	{
		return this.education;
	}

	public void setEducation(String education)
	{
		this.education = education;
	}

	@Column(name="GRADUATION_TIME")
	public Date getGraduationTime()
	{
		return this.graduationTime;
	}

	public void setGraduationTime(Date graduationTime)
	{
		this.graduationTime = graduationTime;
	}

	@Column(name="JOB_TITLE")
	public String getJobTitle()
	{
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	@Column(name="EMPLOYER")
	public String getEmployer()
	{
		return this.employer;
	}

	public void setEmployer(String employer)
	{
		this.employer = employer;
	}

	@Column(name="SPECIALTY")
	public String getSpecialty()
	{
		return this.specialty;
	}

	public void setSpecialty(String specialty)
	{
		this.specialty = specialty;
	}

	@Column(name="WORK_RECORD")
	public String getWorkRecord()
	{
		return this.workRecord;
	}

	public void setWorkRecord(String workRecord)
	{
		this.workRecord = workRecord;
	}

	@Column(name="EDUCATION_SEC")
	public String getEducationSec()
	{
		return this.educationSec;
	}

	public void setEducationSec(String educationSec)
	{
		this.educationSec = educationSec;
	}

	@Column(name="ADDRESS")
	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
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

	@Column(name="EMAIL")
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name="LONGITUDE")
	public String getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	@Column(name="LATITUDE")
	public String getLatitude()
	{
		return this.latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

}
