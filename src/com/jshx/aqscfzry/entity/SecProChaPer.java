package com.jshx.aqscfzry.entity;

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
@Table(name="SEC_PRO_CHA_PER")
public class SecProChaPer extends BaseModel
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
	 * 姓名
	 */
	private String chargeName;

	/**
	 * 性别
	 */
	private String chargeSex;

	/**
	 * 国籍
	 */
	private String chargeNationnality;

	/**
	 * 身份证号码
	 */
	private String chargeCardNum;

	/**
	 * 最高学历
	 */
	private String chargeHighestSchool;

	/**
	 * 最高学位
	 */
	private String chargeHighestDegree;

	/**
	 * 毕业院校
	 */
	private String chargeSchool;

	/**
	 * 专业
	 */
	private String chargeSpecialized;

	/**
	 * 职称
	 */
	private String chargeTitle;

	/**
	 * 联系电话1
	 */
	private String chargePhone;
	/**
	 * 联系电话2
	 */
	private String chargePhone2;

	/**
	 * 电子邮箱
	 */
	private String chargeEmail;

	/**
	 * 住址
	 */
	private String chargeAddress;

	/**
	 * 进入本单位日期
	 */
	private Date chargeDutyDate;

	/**
	 * 从事本岗位时间
	 */
	private Date chargePostDate;

	/**
	 * 专业资格证号
	 */
	private String chargeSpecializedNum;

	/**
	 * 备注
	 */
	private String chargeRemark;
	

	
	public SecProChaPer(){
	}
	
	public SecProChaPer(String id, String areaId, String companyName, String chargeName, String chargeSpecializedNum,String chargePhone){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.chargeName = chargeName;

this.chargeSpecializedNum = chargeSpecializedNum;
this.chargePhone=chargePhone;
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

	@Column(name="CHARGE_NAME")
	public String getChargeName()
	{
		return this.chargeName;
	}

	public void setChargeName(String chargeName)
	{
		this.chargeName = chargeName;
	}

	@Column(name="CHARGE_SEX")
	public String getChargeSex()
	{
		return this.chargeSex;
	}

	public void setChargeSex(String chargeSex)
	{
		this.chargeSex = chargeSex;
	}

	@Column(name="CHARGE_NATIONNALITY")
	public String getChargeNationnality()
	{
		return this.chargeNationnality;
	}

	public void setChargeNationnality(String chargeNationnality)
	{
		this.chargeNationnality = chargeNationnality;
	}

	@Column(name="CHARGE_CARD_NUM")
	public String getChargeCardNum()
	{
		return this.chargeCardNum;
	}

	public void setChargeCardNum(String chargeCardNum)
	{
		this.chargeCardNum = chargeCardNum;
	}

	@Column(name="CHARGE_HIGHEST_SCHOOL")
	public String getChargeHighestSchool()
	{
		return this.chargeHighestSchool;
	}

	public void setChargeHighestSchool(String chargeHighestSchool)
	{
		this.chargeHighestSchool = chargeHighestSchool;
	}

	@Column(name="CHARGE_HIGHEST_DEGREE")
	public String getChargeHighestDegree()
	{
		return this.chargeHighestDegree;
	}

	public void setChargeHighestDegree(String chargeHighestDegree)
	{
		this.chargeHighestDegree = chargeHighestDegree;
	}

	@Column(name="CHARGE_SCHOOL")
	public String getChargeSchool()
	{
		return this.chargeSchool;
	}

	public void setChargeSchool(String chargeSchool)
	{
		this.chargeSchool = chargeSchool;
	}

	@Column(name="CHARGE_SPECIALIZED")
	public String getChargeSpecialized()
	{
		return this.chargeSpecialized;
	}

	public void setChargeSpecialized(String chargeSpecialized)
	{
		this.chargeSpecialized = chargeSpecialized;
	}

	@Column(name="CHARGE_TITLE")
	public String getChargeTitle()
	{
		return this.chargeTitle;
	}

	public void setChargeTitle(String chargeTitle)
	{
		this.chargeTitle = chargeTitle;
	}

	@Column(name="CHARGE_PHONE")
	public String getChargePhone()
	{
		return this.chargePhone;
	}

	public void setChargePhone(String chargePhone)
	{
		this.chargePhone = chargePhone;
	}
	
	@Column(name="CHARGE_PHONE2")
	public String getChargePhone2()
	{
		return this.chargePhone2;
	}

	public void setChargePhone2(String chargePhone2)
	{
		this.chargePhone2 = chargePhone2;
	}

	@Column(name="CHARGE_EMAIL")
	public String getChargeEmail()
	{
		return this.chargeEmail;
	}

	public void setChargeEmail(String chargeEmail)
	{
		this.chargeEmail = chargeEmail;
	}

	@Column(name="CHARGE_ADDRESS")
	public String getChargeAddress()
	{
		return this.chargeAddress;
	}

	public void setChargeAddress(String chargeAddress)
	{
		this.chargeAddress = chargeAddress;
	}

	@Column(name="CHARGE_DUTY_DATE")
	public Date getChargeDutyDate()
	{
		return this.chargeDutyDate;
	}

	public void setChargeDutyDate(Date chargeDutyDate)
	{
		this.chargeDutyDate = chargeDutyDate;
	}

	@Column(name="CHARGE_POST_DATE")
	public Date getChargePostDate()
	{
		return this.chargePostDate;
	}

	public void setChargePostDate(Date chargePostDate)
	{
		this.chargePostDate = chargePostDate;
	}

	@Column(name="CHARGE_SPECIALIZED_NUM")
	public String getChargeSpecializedNum()
	{
		return this.chargeSpecializedNum;
	}

	public void setChargeSpecializedNum(String chargeSpecializedNum)
	{
		this.chargeSpecializedNum = chargeSpecializedNum;
	}

	@Column(name="CHARGE_REMARK")
	public String getChargeRemark()
	{
		return this.chargeRemark;
	}

	public void setChargeRemark(String chargeRemark)
	{
		this.chargeRemark = chargeRemark;
	}

}
