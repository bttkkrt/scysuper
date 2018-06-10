package com.jshx.zywsgljbxx.entity;

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
@Table(name="OCC_HEA_INFO")
public class OccHeaInfo extends BaseModel
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
	 * 女职工人数
	 */
	private String femaleWorkersNumber;

	/**
	 * 接触职业病危害因素人数
	 */
	private String occupationalDiseasersNumber;

	/**
	 * 职业病危害行业类别
	 */
	private String hazardIndustryCategory;

	/**
	 * 企业职业病危害类别
	 */
	private String companyHazardCategory;

	/**
	 * 接触职业病危害因素女工人数
	 */
	private String femaleWorkersDiseasesNumber;

	/**
	 * 职业病危害岗位数
	 */
	private String occupationDiseasePosts;
	
	/**
	 * 职业卫生分管负责人 姓名
	 */
	private String zywsfgfzrName;
	/**
	 * 职业卫生分管负责人 职务
	 */
	private String zywsfgfzrDuty;
	/**
	 * 职业卫生分管负责人 办公电话
	 */
	private String zywsfgfzrTelephone;
	/**
	 * 职业卫生分管负责人 手机
	 */
	private String zywsfgfzrMobile;
	/**
	 * 职业卫生分管负责人 文化程度
	 */
	private String zywsfgfzrEducation;
	/**
	 * 职业卫生分管负责人 专业
	 */
	private String zywsfgfzrProfession;
	
	/**
	 * 职业卫生分管负责人 培训日期(开始)
	 */
	private Date zywsfgfzrTrainingDateStart;
	/**
	 * 职业卫生分管负责人 培训日期(结束)
	 */
	private Date zywsfgfzrTrainingDateEnd;
	/**
	 * 职业卫生分管负责人 培训合格证号
	 */
	private String zywsfgfzrTrainingNo;
	
	/**
	 * 职业卫生管理机构负责人 姓名
	 */
	private String zywsgljgfzrName;
	/**
	 * 职业卫生管理机构负责人 职务
	 */
	private String zywsgljgfzrDuty;
	/**
	 * 职业卫生管理机构负责人 办公电话
	 */
	private String zywsgljgfzrTelephone;
	/**
	 * 职业卫生管理机构负责人 手机
	 */
	private String zywsgljgfzrMobile;
	/**
	 * 职业卫生管理机构负责人 文化程度
	 */
	private String zywsgljgfzrEducation;
	/**
	 * 职业卫生管理机构负责人 专业
	 */
	private String zywsgljgfzrProfession;
	
	/**
	 * 职业卫生管理机构负责人 培训日期(开始)
	 */
	private Date zywsgljgfzrTrainingDateStart;
	/**
	 * 职业卫生管理机构负责人 培训日期(结束)
	 */
	private Date zywsgljgfzrTrainingDateEnd;
	/**
	 * 职业卫生管理机构负责人 培训合格证号
	 */
	private String zywsgljgfzrTrainingNo;
	
	
	public OccHeaInfo(){
	}
	
	public OccHeaInfo(String id, String areaId, String companyName, String hazardIndustryCategory, String femaleWorkersDiseasesNumber){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.hazardIndustryCategory = hazardIndustryCategory;

this.femaleWorkersDiseasesNumber = femaleWorkersDiseasesNumber;
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

	@Column(name="FEMALE_WORKERS_NUMBER")
	public String getFemaleWorkersNumber()
	{
		return this.femaleWorkersNumber;
	}

	public void setFemaleWorkersNumber(String femaleWorkersNumber)
	{
		this.femaleWorkersNumber = femaleWorkersNumber;
	}

	@Column(name="OCCUPATIONAL_DISEASERS_NUMBER")
	public String getOccupationalDiseasersNumber()
	{
		return this.occupationalDiseasersNumber;
	}

	public void setOccupationalDiseasersNumber(String occupationalDiseasersNumber)
	{
		this.occupationalDiseasersNumber = occupationalDiseasersNumber;
	}

	@Column(name="HAZARD_INDUSTRY_CATEGORY")
	public String getHazardIndustryCategory()
	{
		return this.hazardIndustryCategory;
	}

	public void setHazardIndustryCategory(String hazardIndustryCategory)
	{
		this.hazardIndustryCategory = hazardIndustryCategory;
	}

	@Column(name="COMPANY_HAZARD_CATEGORY")
	public String getCompanyHazardCategory()
	{
		return this.companyHazardCategory;
	}

	public void setCompanyHazardCategory(String companyHazardCategory)
	{
		this.companyHazardCategory = companyHazardCategory;
	}

	@Column(name="FEMALE_WORKERS_DISEASES_NUMBER")
	public String getFemaleWorkersDiseasesNumber()
	{
		return this.femaleWorkersDiseasesNumber;
	}

	public void setFemaleWorkersDiseasesNumber(String femaleWorkersDiseasesNumber)
	{
		this.femaleWorkersDiseasesNumber = femaleWorkersDiseasesNumber;
	}

	@Column(name="OCCUPATION_DISEASE_POSTS")
	public String getOccupationDiseasePosts()
	{
		return this.occupationDiseasePosts;
	}

	public void setOccupationDiseasePosts(String occupationDiseasePosts)
	{
		this.occupationDiseasePosts = occupationDiseasePosts;
	}

	@Column(name="ZYWSFGFZR_NAME")
	public String getZywsfgfzrName() {
		return zywsfgfzrName;
	}

	public void setZywsfgfzrName(String zywsfgfzrName) {
		this.zywsfgfzrName = zywsfgfzrName;
	}
	@Column(name="ZYWSFGFZR_DUTY")
	public String getZywsfgfzrDuty() {
		return zywsfgfzrDuty;
	}

	public void setZywsfgfzrDuty(String zywsfgfzrDuty) {
		this.zywsfgfzrDuty = zywsfgfzrDuty;
	}
	@Column(name="ZYWSFGFZR_TELEPHONE")
	public String getZywsfgfzrTelephone() {
		return zywsfgfzrTelephone;
	}

	public void setZywsfgfzrTelephone(String zywsfgfzrTelephone) {
		this.zywsfgfzrTelephone = zywsfgfzrTelephone;
	}
	@Column(name="ZYWSFGFZR_MOBILE")
	public String getZywsfgfzrMobile() {
		return zywsfgfzrMobile;
	}

	public void setZywsfgfzrMobile(String zywsfgfzrMobile) {
		this.zywsfgfzrMobile = zywsfgfzrMobile;
	}
	@Column(name="ZYWSFGFZR_EDUCATION")
	public String getZywsfgfzrEducation() {
		return zywsfgfzrEducation;
	}

	public void setZywsfgfzrEducation(String zywsfgfzrEducation) {
		this.zywsfgfzrEducation = zywsfgfzrEducation;
	}
	@Column(name="ZYWSFGFZR_PROFESSION")
	public String getZywsfgfzrProfession() {
		return zywsfgfzrProfession;
	}

	public void setZywsfgfzrProfession(String zywsfgfzrProfession) {
		this.zywsfgfzrProfession = zywsfgfzrProfession;
	}
	@Column(name="ZYWSFGFZR_TRAININGNO")
	public String getZywsfgfzrTrainingNo() {
		return zywsfgfzrTrainingNo;
	}

	public void setZywsfgfzrTrainingNo(String zywsfgfzrTrainingNo) {
		this.zywsfgfzrTrainingNo = zywsfgfzrTrainingNo;
	}
	@Column(name="ZYWSGLJGFZR_NAME")
	public String getZywsgljgfzrName() {
		return zywsgljgfzrName;
	}

	public void setZywsgljgfzrName(String zywsgljgfzrName) {
		this.zywsgljgfzrName = zywsgljgfzrName;
	}
	@Column(name="ZYWSGLJGFZR_DUTY")
	public String getZywsgljgfzrDuty() {
		return zywsgljgfzrDuty;
	}

	public void setZywsgljgfzrDuty(String zywsgljgfzrDuty) {
		this.zywsgljgfzrDuty = zywsgljgfzrDuty;
	}
	@Column(name="ZYWSGLJGFZR_TELEPHONE")
	public String getZywsgljgfzrTelephone() {
		return zywsgljgfzrTelephone;
	}

	public void setZywsgljgfzrTelephone(String zywsgljgfzrTelephone) {
		this.zywsgljgfzrTelephone = zywsgljgfzrTelephone;
	}
	@Column(name="ZYWSGLJGFZR_MOBILE")
	public String getZywsgljgfzrMobile() {
		return zywsgljgfzrMobile;
	}

	public void setZywsgljgfzrMobile(String zywsgljgfzrMobile) {
		this.zywsgljgfzrMobile = zywsgljgfzrMobile;
	}
	@Column(name="ZYWSGLJGFZR_EDUCATION")
	public String getZywsgljgfzrEducation() {
		return zywsgljgfzrEducation;
	}

	public void setZywsgljgfzrEducation(String zywsgljgfzrEducation) {
		this.zywsgljgfzrEducation = zywsgljgfzrEducation;
	}
	@Column(name="ZYWSGLJGFZR_PROFESSION")
	public String getZywsgljgfzrProfession() {
		return zywsgljgfzrProfession;
	}

	public void setZywsgljgfzrProfession(String zywsgljgfzrProfession) {
		this.zywsgljgfzrProfession = zywsgljgfzrProfession;
	}
	@Column(name="ZYWSGLJGFZR_TRAININGNO")
	public String getZywsgljgfzrTrainingNo() {
		return zywsgljgfzrTrainingNo;
	}

	public void setZywsgljgfzrTrainingNo(String zywsgljgfzrTrainingNo) {
		this.zywsgljgfzrTrainingNo = zywsgljgfzrTrainingNo;
	}
	@Column(name="ZYWSFGFZR_TRAININGDATE_START")
	public Date getZywsfgfzrTrainingDateStart() {
		return zywsfgfzrTrainingDateStart;
	}

	public void setZywsfgfzrTrainingDateStart(Date zywsfgfzrTrainingDateStart) {
		this.zywsfgfzrTrainingDateStart = zywsfgfzrTrainingDateStart;
	}
	@Column(name="ZYWSFGFZR_TRAININGDATE_END")
	public Date getZywsfgfzrTrainingDateEnd() {
		return zywsfgfzrTrainingDateEnd;
	}

	public void setZywsfgfzrTrainingDateEnd(Date zywsfgfzrTrainingDateEnd) {
		this.zywsfgfzrTrainingDateEnd = zywsfgfzrTrainingDateEnd;
	}
	@Column(name="ZYWSGLJGFZR_TRAININGDATE_START")
	public Date getZywsgljgfzrTrainingDateStart() {
		return zywsgljgfzrTrainingDateStart;
	}

	public void setZywsgljgfzrTrainingDateStart(Date zywsgljgfzrTrainingDateStart) {
		this.zywsgljgfzrTrainingDateStart = zywsgljgfzrTrainingDateStart;
	}
	@Column(name="ZYWSGLJGFZR_TRAININGDATE_END")
	public Date getZywsgljgfzrTrainingDateEnd() {
		return zywsgljgfzrTrainingDateEnd;
	}

	public void setZywsgljgfzrTrainingDateEnd(Date zywsgljgfzrTrainingDateEnd) {
		this.zywsgljgfzrTrainingDateEnd = zywsgljgfzrTrainingDateEnd;
	}

	
}
