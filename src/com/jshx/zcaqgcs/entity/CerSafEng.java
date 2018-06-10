package com.jshx.zcaqgcs.entity;

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
@Table(name="CER_SAF_ENG")
public class CerSafEng extends BaseModel
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
	private String saftyName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 职位
	 */
	private String position;

	/**
	 * 文化程度
	 */
	private String education;

	/**
	 * 电话
	 */
	private String tele;

	/**
	 * 身份证
	 */
	private String idCard;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 职称
	 */
	private String jobTitle;

	/**
	 * 首次领证时间
	 */
	private Date firstLicense;

	/**
	 * 合格证号
	 */
	private String certificateNo;

	/**
	 * 证书有效期
	 */
	private Date certificaateValid;

	
	public CerSafEng(){
	}
	
	public CerSafEng(String id, String areaName,String companyName, String saftyName, String idCard, String certificateNo,String createUserID){
this.id = id;
this.areaName=areaName;
this.companyName = companyName;

this.saftyName = saftyName;

this.idCard = idCard;

this.certificateNo = certificateNo;
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

	@Column(name="SAFTY_NAME")
	public String getSaftyName()
	{
		return this.saftyName;
	}

	public void setSaftyName(String saftyName)
	{
		this.saftyName = saftyName;
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

	@Column(name="POSITION")
	public String getPosition()
	{
		return this.position;
	}

	public void setPosition(String position)
	{
		this.position = position;
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

	@Column(name="TELE")
	public String getTele()
	{
		return this.tele;
	}

	public void setTele(String tele)
	{
		this.tele = tele;
	}

	@Column(name="ID_CARD")
	public String getIdCard()
	{
		return this.idCard;
	}

	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
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

	@Column(name="JOB_TITLE")
	public String getJobTitle()
	{
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	@Column(name="FIRST_LICENSE")
	public Date getFirstLicense()
	{
		return this.firstLicense;
	}

	public void setFirstLicense(Date firstLicense)
	{
		this.firstLicense = firstLicense;
	}

	@Column(name="CERTIFICATE_NO")
	public String getCertificateNo()
	{
		return this.certificateNo;
	}

	public void setCertificateNo(String certificateNo)
	{
		this.certificateNo = certificateNo;
	}

	@Column(name="CERTIFICAATE_VALID")
	public Date getCertificaateValid()
	{
		return this.certificaateValid;
	}

	public void setCertificaateValid(Date certificaateValid)
	{
		this.certificaateValid = certificaateValid;
	}

}
