package com.jshx.yjjgydw.entity;

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
@Table(name="EME_AGE")
public class EmeAge extends BaseModel
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
	 * 机构名称
	 */
	private String agencyName;

	/**
	 * 机构职责
	 */
	private String agencyResponsible;

	/**
	 * 成员数量
	 */
	private String memberNumber;

	/**
	 * 负责人
	 */
	private String personInCharge;

	/**
	 * 负责人联系方式
	 */
	private String inChargePhone;

	/**
	 * 负责人邮箱
	 */
	private String inChargeEmail;

	/**
	 * 备注
	 */
	private String remark;

	
	public EmeAge(){
	}
	
	public EmeAge(String id, String areaName, String companyName, String agencyName, String memberNumber, String personInCharge){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.agencyName = agencyName;

this.memberNumber = memberNumber;

this.personInCharge = personInCharge;
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

	@Column(name="AGENCY_NAME")
	public String getAgencyName()
	{
		return this.agencyName;
	}

	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	@Column(name="AGENCY_RESPONSIBLE")
	public String getAgencyResponsible()
	{
		return this.agencyResponsible;
	}

	public void setAgencyResponsible(String agencyResponsible)
	{
		this.agencyResponsible = agencyResponsible;
	}

	@Column(name="MEMBER_NUMBER")
	public String getMemberNumber()
	{
		return this.memberNumber;
	}

	public void setMemberNumber(String memberNumber)
	{
		this.memberNumber = memberNumber;
	}

	@Column(name="PERSON_IN_CHARGE")
	public String getPersonInCharge()
	{
		return this.personInCharge;
	}

	public void setPersonInCharge(String personInCharge)
	{
		this.personInCharge = personInCharge;
	}

	@Column(name="IN_CHARGE_PHONE")
	public String getInChargePhone()
	{
		return this.inChargePhone;
	}

	public void setInChargePhone(String inChargePhone)
	{
		this.inChargePhone = inChargePhone;
	}

	@Column(name="IN_CHARGE_EMAIL")
	public String getInChargeEmail()
	{
		return this.inChargeEmail;
	}

	public void setInChargeEmail(String inChargeEmail)
	{
		this.inChargeEmail = inChargeEmail;
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

}
