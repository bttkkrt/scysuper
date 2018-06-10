package com.jshx.aqscbxtbqk.entity;

import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SEC_PRO_INS")
public class SecProIns extends BaseModel
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
	 * 团体人身意外伤害险总保费
	 */
	private String insuranceTeamFee;

	/**
	 * 团体人身意外伤害险总保额
	 */
	private String insuranceTeamInsured;

	/**
	 * 承保保险公司
	 */
	private String insuranceCompnay;

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;
	
	/**
	 * 所在区域
	 */
	private String areaId;

	/**
	 * 所在区域
	 */
	private String areaName;

	/**
	 * 企业总人数
	 */
	private String insuranceEnterprisePersons;

	/**
	 * 一线员工数
	 */
	private String insuranceWorkerCount;

	/**
	 * 安全生产责任险参保人数
	 */
	private String insurancePersonCount;

	/**
	 * 安全生产责任险总保费
	 */
	private String insuranceTotalFee;

	/**
	 * 安全生产责任险总保额
	 */
	private String insuranceTotalInsured;

	/**
	 * 雇主责任险参保人数
	 */
	private String insuranceEmployerCount;

	/**
	 * 雇主责任险总保费
	 */
	private String insuranceEmployerFee;

	/**
	 * 雇主责任险总保额
	 */
	private String insuranceEmployerInsured;

	/**
	 * 公众责任险总保费
	 */
	private String insurancePublicFee;

	/**
	 * 公众责任险总保额
	 */
	private String insurancePublicInsured;
	
	/**
	 * 投保时间
	 */
	private Date insuranceTime;
	
	/**
	 * 其他参保人数
	 */
	private String otherEmployerCount;

	/**
	 * 其他总保费
	 */
	private String otherEmployerFee;

	/**
	 * 其他总保额
	 */
	private String otherEmployerInsured;
	
	

	
	public SecProIns(){
	}
	
	public SecProIns(String id,String areaName, String companyName, String insuranceWorkerCount,Date insuranceTime,String insuranceCompnay){
this.id = id;
this.areaName = areaName;
this.companyName = companyName;

this.insuranceWorkerCount = insuranceWorkerCount;
this.insuranceTime=insuranceTime;

this.insuranceCompnay=insuranceCompnay;
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

	
	@Column(name="INSURANCE_TEAM_FEE")
	public String getInsuranceTeamFee()
	{
		return this.insuranceTeamFee;
	}

	public void setInsuranceTeamFee(String insuranceTeamFee)
	{
		this.insuranceTeamFee = insuranceTeamFee;
	}

	@Column(name="INSURANCE_TEAM_INSURED")
	public String getInsuranceTeamInsured()
	{
		return this.insuranceTeamInsured;
	}

	public void setInsuranceTeamInsured(String insuranceTeamInsured)
	{
		this.insuranceTeamInsured = insuranceTeamInsured;
	}

	@Column(name="INSURANCE_COMPNAY")
	public String getInsuranceCompnay()
	{
		return this.insuranceCompnay;
	}

	public void setInsuranceCompnay(String insuranceCompnay)
	{
		this.insuranceCompnay = insuranceCompnay;
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

	@Column(name="INSURANCE_ENTERPRISE_PERSONS")
	public String getInsuranceEnterprisePersons()
	{
		if(this.insuranceEnterprisePersons != null)
		{
			if(this.insuranceEnterprisePersons.indexOf(".") != -1)
			{
				return this.insuranceEnterprisePersons.substring(0,this.insuranceEnterprisePersons.indexOf("."));
			}
			else
			{
				return this.insuranceEnterprisePersons;
			}
		}
		else
		{
			return "";
		}
	}

	public void setInsuranceEnterprisePersons(String insuranceEnterprisePersons)
	{
		this.insuranceEnterprisePersons = insuranceEnterprisePersons;
	}

	@Column(name="INSURANCE_WORKER_COUNT")
	public String getInsuranceWorkerCount()
	{
		return this.insuranceWorkerCount;
	}

	public void setInsuranceWorkerCount(String insuranceWorkerCount)
	{
		this.insuranceWorkerCount = insuranceWorkerCount;
	}

	@Column(name="INSURANCE_PERSON_COUNT")
	public String getInsurancePersonCount()
	{
		return this.insurancePersonCount;
	}

	public void setInsurancePersonCount(String insurancePersonCount)
	{
		this.insurancePersonCount = insurancePersonCount;
	}

	@Column(name="INSURANCE_TOTAL_FEE")
	public String getInsuranceTotalFee()
	{
		return this.insuranceTotalFee;
	}

	public void setInsuranceTotalFee(String insuranceTotalFee)
	{
		this.insuranceTotalFee = insuranceTotalFee;
	}

	@Column(name="INSURANCE_TOTAL_INSURED")
	public String getInsuranceTotalInsured()
	{
		return this.insuranceTotalInsured;
	}

	public void setInsuranceTotalInsured(String insuranceTotalInsured)
	{
		this.insuranceTotalInsured = insuranceTotalInsured;
	}

	@Column(name="INSURANCE_EMPLOYER_COUNT")
	public String getInsuranceEmployerCount()
	{
		return this.insuranceEmployerCount;
	}

	public void setInsuranceEmployerCount(String insuranceEmployerCount)
	{
		this.insuranceEmployerCount = insuranceEmployerCount;
	}

	@Column(name="INSURANCE_EMPLOYER_FEE")
	public String getInsuranceEmployerFee()
	{
		return this.insuranceEmployerFee;
	}

	public void setInsuranceEmployerFee(String insuranceEmployerFee)
	{
		this.insuranceEmployerFee = insuranceEmployerFee;
	}

	@Column(name="INSURANCE_EMPLOYER_INSURED")
	public String getInsuranceEmployerInsured()
	{
		return this.insuranceEmployerInsured;
	}

	public void setInsuranceEmployerInsured(String insuranceEmployerInsured)
	{
		this.insuranceEmployerInsured = insuranceEmployerInsured;
	}

	@Column(name="INSURANCE_PUBLIC_FEE")
	public String getInsurancePublicFee()
	{
		return this.insurancePublicFee;
	}

	public void setInsurancePublicFee(String insurancePublicFee)
	{
		this.insurancePublicFee = insurancePublicFee;
	}

	@Column(name="INSURANCE_PUBLIC_INSURED")
	public String getInsurancePublicInsured()
	{
		return this.insurancePublicInsured;
	}

	public void setInsurancePublicInsured(String insurancePublicInsured)
	{
		this.insurancePublicInsured = insurancePublicInsured;
	}
	@Column(name="INSURANCE_TIME")
	public Date getInsuranceTime() {
		return insuranceTime;
	}

	public void setInsuranceTime(Date insuranceTime) {
		this.insuranceTime = insuranceTime;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name="AREA_NAME")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@Column
	public String getOtherEmployerCount() {
		return otherEmployerCount;
	}

	public void setOtherEmployerCount(String otherEmployerCount) {
		this.otherEmployerCount = otherEmployerCount;
	}
	@Column
	public String getOtherEmployerFee() {
		return otherEmployerFee;
	}

	public void setOtherEmployerFee(String otherEmployerFee) {
		this.otherEmployerFee = otherEmployerFee;
	}
	@Column
	public String getOtherEmployerInsured() {
		return otherEmployerInsured;
	}

	public void setOtherEmployerInsured(String otherEmployerInsured) {
		this.otherEmployerInsured = otherEmployerInsured;
	}

}
