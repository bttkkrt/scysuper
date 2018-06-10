package com.jshx.aqscgljl.entity;

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
@Table(name="SEC_PRO_MAN")
public class SecProMan extends BaseModel
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
	private String managerName;

	/**
	 * 性别
	 */
	private String managerSex;

	/**
	 * 国籍
	 */
	private String managerNationnality;

	/**
	 * 身份证号码
	 */
	private String managerCardNum;

	/**
	 * 最高学历
	 */
	private String managerHighestSchool;

	/**
	 * 最高学位
	 */
	private String managerHighestDegree;

	/**
	 * 毕业院校
	 */
	private String managerSchool;

	/**
	 * 专业
	 */
	private String managerSpecialized;

	/**
	 * 职称
	 */
	private String managerTitle;

	/**
	 * 联系电话1
	 */
	private String managerPhone;
	
	/**
	 * 联系电话2
	 */
	private String managerPhone2;

	/**
	 * 电子邮箱
	 */
	private String managerEmail;

	/**
	 * 住址
	 */
	private String managerAddress;

	/**
	 * 进入本单位日期
	 */
	private Date managerDutyDate;

	/**
	 * 从事本岗位时间
	 */
	private Date managerPostDate;

	/**
	 * 专业资格证号
	 */
	private String managerSpecializedNum;

	/**
	 * 备注
	 */
	private String managerRemark;

	
	public SecProMan(){
	}
	
	public SecProMan(String id, String areaId, String companyName, String managerName, String managerSpecializedNum,String managerPhone){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.managerName = managerName;

this.managerSpecializedNum = managerSpecializedNum;
this.managerPhone=managerPhone;
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

	@Column(name="MANAGER_NAME")
	public String getManagerName()
	{
		return this.managerName;
	}

	public void setManagerName(String managerName)
	{
		this.managerName = managerName;
	}

	@Column(name="MANAGER_SEX")
	public String getManagerSex()
	{
		return this.managerSex;
	}

	public void setManagerSex(String managerSex)
	{
		this.managerSex = managerSex;
	}

	@Column(name="MANAGER_NATIONNALITY")
	public String getManagerNationnality()
	{
		return this.managerNationnality;
	}

	public void setManagerNationnality(String managerNationnality)
	{
		this.managerNationnality = managerNationnality;
	}

	@Column(name="MANAGER_CARD_NUM")
	public String getManagerCardNum()
	{
		return this.managerCardNum;
	}

	public void setManagerCardNum(String managerCardNum)
	{
		this.managerCardNum = managerCardNum;
	}

	@Column(name="MANAGER_HIGHEST_SCHOOL")
	public String getManagerHighestSchool()
	{
		return this.managerHighestSchool;
	}

	public void setManagerHighestSchool(String managerHighestSchool)
	{
		this.managerHighestSchool = managerHighestSchool;
	}

	@Column(name="MANAGER_HIGHEST_DEGREE")
	public String getManagerHighestDegree()
	{
		return this.managerHighestDegree;
	}

	public void setManagerHighestDegree(String managerHighestDegree)
	{
		this.managerHighestDegree = managerHighestDegree;
	}

	@Column(name="MANAGER_SCHOOL")
	public String getManagerSchool()
	{
		return this.managerSchool;
	}

	public void setManagerSchool(String managerSchool)
	{
		this.managerSchool = managerSchool;
	}

	@Column(name="MANAGER_SPECIALIZED")
	public String getManagerSpecialized()
	{
		return this.managerSpecialized;
	}

	public void setManagerSpecialized(String managerSpecialized)
	{
		this.managerSpecialized = managerSpecialized;
	}

	@Column(name="MANAGER_TITLE")
	public String getManagerTitle()
	{
		return this.managerTitle;
	}

	public void setManagerTitle(String managerTitle)
	{
		this.managerTitle = managerTitle;
	}

	@Column(name="MANAGER_PHONE")
	public String getManagerPhone()
	{
		return this.managerPhone;
	}

	public void setManagerPhone(String managerPhone)
	{
		this.managerPhone = managerPhone;
	}
	
	@Column(name="MANAGER_PHONE2")
	public String getManagerPhone2()
	{
		return this.managerPhone2;
	}

	public void setManagerPhone2(String managerPhone2)
	{
		this.managerPhone2 = managerPhone2;
	}

	@Column(name="MANAGER_EMAIL")
	public String getManagerEmail()
	{
		return this.managerEmail;
	}

	public void setManagerEmail(String managerEmail)
	{
		this.managerEmail = managerEmail;
	}

	@Column(name="MANAGER_ADDRESS")
	public String getManagerAddress()
	{
		return this.managerAddress;
	}

	public void setManagerAddress(String managerAddress)
	{
		this.managerAddress = managerAddress;
	}

	@Column(name="MANAGER_DUTY_DATE")
	public Date getManagerDutyDate()
	{
		return this.managerDutyDate;
	}

	public void setManagerDutyDate(Date managerDutyDate)
	{
		this.managerDutyDate = managerDutyDate;
	}

	@Column(name="MANAGER_POST_DATE")
	public Date getManagerPostDate()
	{
		return this.managerPostDate;
	}

	public void setManagerPostDate(Date managerPostDate)
	{
		this.managerPostDate = managerPostDate;
	}

	@Column(name="MANAGER_SPECIALIZED_NUM")
	public String getManagerSpecializedNum()
	{
		return this.managerSpecializedNum;
	}

	public void setManagerSpecializedNum(String managerSpecializedNum)
	{
		this.managerSpecializedNum = managerSpecializedNum;
	}

	@Column(name="MANAGER_REMARK")
	public String getManagerRemark()
	{
		return this.managerRemark;
	}

	public void setManagerRemark(String managerRemark)
	{
		this.managerRemark = managerRemark;
	}

}
