package com.jshx.tzzyry.entity;

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
@Table(name="SPE_JOB_PER")
public class SpeJobPer extends BaseModel
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
	 * 专业
	 */
	private String specialSpecialized;

	/**
	 * 职称
	 */
	private String specialTitle;

	/**
	 * 联系电话1
	 */
	private String specialPhone;
	/**
	 * 联系电话2
	 */
	private String specialPhone2;

	/**
	 * 电子邮箱
	 */
	private String specialEmail;

	/**
	 * 住址
	 */
	private String specialAddress;

	/**
	 * 进入本单位日期
	 */
	private Date specialDutyDate;

	/**
	 * 从事本岗位时间
	 */
	private Date specialPostDate;

	/**
	 * 专业资格证号
	 */
	private String specialSpecializedNum;

	/**
	 * 备注
	 */
	private String specialRemark;

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
	 * 姓名
	 */
	private String specialName;

	/**
	 * 性别
	 */
	private String specialSex;

	/**
	 * 国籍
	 */
	private String specialNationnality;

	/**
	 * 身份证号码
	 */
	private String specialCardNum;

	/**
	 * 最高学历
	 */
	private String specialHighestSchool;

	/**
	 * 最高学位
	 */
	private String specialHighestDegree;

	/**
	 * 毕业院校
	 */
	private String specialSchool;
	
	/**
	 * 特种作业类型
	 */
	private String specialJobType;
	
	/**
	 * 特种作业证号
	 */
	private String specialJobCradnum;
	
	/**
	 * 培训单位
	 */
	private String specialTrainingUnit;
	
	/**
	 * 发证机关
	 */
	private String specialCardInstitution;
	
	/**
	 * 初领日期
	 */
	private Date specialFirstPickDate;
	
	/**
	 * 复审日期
	 */
	private Date specialVerificationDate;
	
	
	public SpeJobPer(){
	}
	
	public SpeJobPer(String id, String areaId, String companyName, String specialName, String specialJobCradnum,String specialJobType,Date specialVerificationDate){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.specialName = specialName;

this.specialJobCradnum = specialJobCradnum;
this.specialJobType=specialJobType;
this.specialVerificationDate = specialVerificationDate;
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

	
	@Column(name="SPECIAL_SPECIALIZED")
	public String getSpecialSpecialized()
	{
		return this.specialSpecialized;
	}

	public void setSpecialSpecialized(String specialSpecialized)
	{
		this.specialSpecialized = specialSpecialized;
	}

	@Column(name="SPECIAL_TITLE")
	public String getSpecialTitle()
	{
		return this.specialTitle;
	}

	public void setSpecialTitle(String specialTitle)
	{
		this.specialTitle = specialTitle;
	}

	@Column(name="SPECIAL_PHONE")
	public String getSpecialPhone()
	{
		return this.specialPhone;
	}

	public void setSpecialPhone(String specialPhone)
	{
		this.specialPhone = specialPhone;
	}
	
	@Column(name="SPECIAL_PHONE2")
	public String getSpecialPhone2()
	{
		return this.specialPhone2;
	}

	public void setSpecialPhone2(String specialPhone2)
	{
		this.specialPhone2 = specialPhone2;
	}

	@Column(name="SPECIAL_EMAIL")
	public String getSpecialEmail()
	{
		return this.specialEmail;
	}

	public void setSpecialEmail(String specialEmail)
	{
		this.specialEmail = specialEmail;
	}

	@Column(name="SPECIAL_ADDRESS")
	public String getSpecialAddress()
	{
		return this.specialAddress;
	}

	public void setSpecialAddress(String specialAddress)
	{
		this.specialAddress = specialAddress;
	}

	@Column(name="SPECIAL_DUTY_DATE")
	public Date getSpecialDutyDate()
	{
		return this.specialDutyDate;
	}

	public void setSpecialDutyDate(Date specialDutyDate)
	{
		this.specialDutyDate = specialDutyDate;
	}

	@Column(name="SPECIAL_POST_DATE")
	public Date getSpecialPostDate()
	{
		return this.specialPostDate;
	}

	public void setSpecialPostDate(Date specialPostDate)
	{
		this.specialPostDate = specialPostDate;
	}

	@Column(name="SPECIAL_SPECIALIZED_NUM")
	public String getSpecialSpecializedNum()
	{
		return this.specialSpecializedNum;
	}

	public void setSpecialSpecializedNum(String specialSpecializedNum)
	{
		this.specialSpecializedNum = specialSpecializedNum;
	}

	@Column(name="SPECIAL_REMARK")
	public String getSpecialRemark()
	{
		return this.specialRemark;
	}

	public void setSpecialRemark(String specialRemark)
	{
		this.specialRemark = specialRemark;
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

	@Column(name="SPECIAL_NAME")
	public String getSpecialName()
	{
		return this.specialName;
	}

	public void setSpecialName(String specialName)
	{
		this.specialName = specialName;
	}

	@Column(name="SPECIAL_SEX")
	public String getSpecialSex()
	{
		return this.specialSex;
	}

	public void setSpecialSex(String specialSex)
	{
		this.specialSex = specialSex;
	}

	@Column(name="SPECIAL_NATIONNALITY")
	public String getSpecialNationnality()
	{
		return this.specialNationnality;
	}

	public void setSpecialNationnality(String specialNationnality)
	{
		this.specialNationnality = specialNationnality;
	}

	@Column(name="SPECIAL_CARD_NUM")
	public String getSpecialCardNum()
	{
		return this.specialCardNum;
	}

	public void setSpecialCardNum(String specialCardNum)
	{
		this.specialCardNum = specialCardNum;
	}

	@Column(name="SPECIAL_HIGHEST_SCHOOL")
	public String getSpecialHighestSchool()
	{
		return this.specialHighestSchool;
	}

	public void setSpecialHighestSchool(String specialHighestSchool)
	{
		this.specialHighestSchool = specialHighestSchool;
	}

	@Column(name="SPECIAL_HIGHEST_DEGREE")
	public String getSpecialHighestDegree()
	{
		return this.specialHighestDegree;
	}

	public void setSpecialHighestDegree(String specialHighestDegree)
	{
		this.specialHighestDegree = specialHighestDegree;
	}

	@Column(name="SPECIAL_SCHOOL")
	public String getSpecialSchool()
	{
		return this.specialSchool;
	}

	public void setSpecialSchool(String specialSchool)
	{
		this.specialSchool = specialSchool;
	}

	@Column(name="SPECIAL_JOB_TYPE")
	public String getSpecialJobType() {
		return specialJobType;
	}

	public void setSpecialJobType(String specialJobType) {
		this.specialJobType = specialJobType;
	}

	@Column(name="SPECIAL_JOB_CRADNUM")
	public String getSpecialJobCradnum() {
		return specialJobCradnum;
	}

	public void setSpecialJobCradnum(String specialJobCradnum) {
		this.specialJobCradnum = specialJobCradnum;
	}

	@Column(name="SPECIAL_TRAINING_UNIT")
	public String getSpecialTrainingUnit() {
		return specialTrainingUnit;
	}

	public void setSpecialTrainingUnit(String specialTrainingUnit) {
		this.specialTrainingUnit = specialTrainingUnit;
	}

	@Column(name="SPECIAL_CARD_INSTITUTION")
	public String getSpecialCardInstitution() {
		return specialCardInstitution;
	}

	public void setSpecialCardInstitution(String specialCardInstitution) {
		this.specialCardInstitution = specialCardInstitution;
	}

	@Column(name="SPECIAL_FIRST_PICK_DATE")
	public Date getSpecialFirstPickDate() {
		return specialFirstPickDate;
	}

	public void setSpecialFirstPickDate(Date specialFirstPickDate) {
		this.specialFirstPickDate = specialFirstPickDate;
	}

	@Column(name="SPECIAL_VERIFICATION_DATE")
	public Date getSpecialVerificationDate() {
		return specialVerificationDate;
	}

	public void setSpecialVerificationDate(Date specialVerificationDate) {
		this.specialVerificationDate = specialVerificationDate;
	}
	
	

}
