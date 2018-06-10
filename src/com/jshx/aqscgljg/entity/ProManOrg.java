package com.jshx.aqscgljg.entity;

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
@Table(name="PRO_MAN_ORG")
public class ProManOrg extends BaseModel
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
	 * 所在区域
	 */
	private String areaId;

	/**
	 * 所在区域
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
	private String orgenizationName;

	/**
	 * 机构职责
	 */
	private String orgenizationResponsibility;

	/**
	 * 成员数量
	 */
	private String orgenizationMenberCount;

	/**
	 * 负责人
	 */
	private String orgenizationCharge;

	/**
	 * 负责人联系方式1
	 */
	private String orgenizationChargePhone;
	
	/**
	 * 负责人联系方式2
	 */
	private String orgenizationChargePhone2;

	/**
	 * 负责人邮箱
	 */
	private String orgenizationChargeEmail;
	

	/**
	 * 备注
	 */
	private String orgenizationRemark;
	

	
	public ProManOrg(){
	}
	
	public ProManOrg(String id, String areaId, String companyName, String orgenizationName, String orgenizationResponsibility, String orgenizationMenberCount, String orgenizationCharge){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.orgenizationName = orgenizationName;

this.orgenizationResponsibility = orgenizationResponsibility;

this.orgenizationMenberCount = orgenizationMenberCount;

this.orgenizationCharge = orgenizationCharge;
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

	@Column(name="ORGENIZATION_NAME")
	public String getOrgenizationName()
	{
		return this.orgenizationName;
	}

	public void setOrgenizationName(String orgenizationName)
	{
		this.orgenizationName = orgenizationName;
	}

	@Column(name="ORGENIZATION_RESPONSIBILITY")
	public String getOrgenizationResponsibility()
	{
		return this.orgenizationResponsibility;
	}

	public void setOrgenizationResponsibility(String orgenizationResponsibility)
	{
		this.orgenizationResponsibility = orgenizationResponsibility;
	}

	@Column(name="ORGENIZATION_MENBER_COUNT")
	public String getOrgenizationMenberCount()
	{
		return this.orgenizationMenberCount;
	}

	public void setOrgenizationMenberCount(String orgenizationMenberCount)
	{
		this.orgenizationMenberCount = orgenizationMenberCount;
	}

	@Column(name="ORGENIZATION_CHARGE")
	public String getOrgenizationCharge()
	{
		return this.orgenizationCharge;
	}

	public void setOrgenizationCharge(String orgenizationCharge)
	{
		this.orgenizationCharge = orgenizationCharge;
	}

	@Column(name="ORGENIZATION_CHARGE_PHONE")
	public String getOrgenizationChargePhone()
	{
		return this.orgenizationChargePhone;
	}

	public void setOrgenizationChargePhone(String orgenizationChargePhone)
	{
		this.orgenizationChargePhone = orgenizationChargePhone;
	}
	
	@Column(name="ORGENIZATION_CHARGE_PHONE2")
	public String getOrgenizationChargePhone2()
	{
		return this.orgenizationChargePhone2;
	}

	public void setOrgenizationChargePhone2(String orgenizationChargePhone2)
	{
		this.orgenizationChargePhone2 = orgenizationChargePhone2;
	}
	
	@Column(name="ORGENIZATION_CHARGE_EMAIL")
	public String getOrgenizationChargeEmail()
	{
		return this.orgenizationChargeEmail;
	}

	public void setOrgenizationChargeEmail(String orgenizationChargeEmail)
	{
		this.orgenizationChargeEmail = orgenizationChargeEmail;
	}

	@Column(name="ORGENIZATION_REMARK")
	public String getOrgenizationRemark()
	{
		return this.orgenizationRemark;
	}

	public void setOrgenizationRemark(String orgenizationRemark)
	{
		this.orgenizationRemark = orgenizationRemark;
	}

}
