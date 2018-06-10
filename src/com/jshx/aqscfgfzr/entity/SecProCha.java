package com.jshx.aqscfgfzr.entity;

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
@Table(name="SEC_PRO_CHA")
public class SecProCha extends BaseModel
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
	 * 姓名
	 */
	private String chargerName;

	/**
	 * 性别
	 */
	private String chargerSex;

	/**
	 * 国籍
	 */
	private String chargerNationnality;

	/**
	 * 身份证号码
	 */
	private String chargerCardNum;

	/**
	 * 最高学历
	 */
	private String chargerHighestSchool;

	/**
	 * 最高学位
	 */
	private String chargerHighestDegree;

	/**
	 * 毕业院校
	 */
	private String chargerSchool;

	/**
	 * 专业
	 */
	private String chargerSpecialized;

	/**
	 * 职称
	 */
	private String chargerTitle;

	/**
	 * 联系电话1
	 */
	private String chargerPhone;
	/**
	 * 联系电话2
	 */
	private String chargerPhone2;

	/**
	 * 电子邮箱
	 */
	private String chargerEmail;

	/**
	 * 住址
	 */
	private String chargerAddress;

	/**
	 * 进入本单位日期
	 */
	private Date chargerDutyDate;

	/**
	 * 从事本岗位时间
	 */
	private Date chargerPostDate;

	/**
	 * 专业资格证号
	 */
	private String chargerSpecializedNum;

	/**
	 * 备注
	 */
	private String chargerRemark;
	

	
	public SecProCha(){
	}
	
	public SecProCha(String id, String areaId, String companyName, String chargerName, String chargerCardNum, String chargerSpecializedNum,String chargerPhone){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.chargerName = chargerName;

this.chargerCardNum = chargerCardNum;

this.chargerSpecializedNum = chargerSpecializedNum;
this.chargerPhone=chargerPhone;
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

	@Column(name="CHARGER_NAME")
	public String getChargerName()
	{
		return this.chargerName;
	}

	public void setChargerName(String chargerName)
	{
		this.chargerName = chargerName;
	}

	@Column(name="CHARGER_SEX")
	public String getChargerSex()
	{
		return this.chargerSex;
	}

	public void setChargerSex(String chargerSex)
	{
		this.chargerSex = chargerSex;
	}

	@Column(name="CHARGER_NATIONNALITY")
	public String getChargerNationnality()
	{
		return this.chargerNationnality;
	}

	public void setChargerNationnality(String chargerNationnality)
	{
		this.chargerNationnality = chargerNationnality;
	}

	@Column(name="CHARGER_CARD_NUM")
	public String getChargerCardNum()
	{
		return this.chargerCardNum;
	}

	public void setChargerCardNum(String chargerCardNum)
	{
		this.chargerCardNum = chargerCardNum;
	}

	@Column(name="CHARGER_HIGHEST_SCHOOL")
	public String getChargerHighestSchool()
	{
		return this.chargerHighestSchool;
	}

	public void setChargerHighestSchool(String chargerHighestSchool)
	{
		this.chargerHighestSchool = chargerHighestSchool;
	}

	@Column(name="CHARGER_HIGHEST_DEGREE")
	public String getChargerHighestDegree()
	{
		return this.chargerHighestDegree;
	}

	public void setChargerHighestDegree(String chargerHighestDegree)
	{
		this.chargerHighestDegree = chargerHighestDegree;
	}

	@Column(name="CHARGER_SCHOOL")
	public String getChargerSchool()
	{
		return this.chargerSchool;
	}

	public void setChargerSchool(String chargerSchool)
	{
		this.chargerSchool = chargerSchool;
	}

	@Column(name="CHARGER_SPECIALIZED")
	public String getChargerSpecialized()
	{
		return this.chargerSpecialized;
	}

	public void setChargerSpecialized(String chargerSpecialized)
	{
		this.chargerSpecialized = chargerSpecialized;
	}

	@Column(name="CHARGER_TITLE")
	public String getChargerTitle()
	{
		return this.chargerTitle;
	}

	public void setChargerTitle(String chargerTitle)
	{
		this.chargerTitle = chargerTitle;
	}

	@Column(name="CHARGER_PHONE")
	public String getChargerPhone()
	{
		return this.chargerPhone;
	}

	public void setChargerPhone(String chargerPhone)
	{
		this.chargerPhone = chargerPhone;
	}

	@Column(name="CHARGER_EMAIL")
	public String getChargerEmail()
	{
		return this.chargerEmail;
	}

	public void setChargerEmail(String chargerEmail)
	{
		this.chargerEmail = chargerEmail;
	}

	@Column(name="CHARGER_ADDRESS")
	public String getChargerAddress()
	{
		return this.chargerAddress;
	}

	public void setChargerAddress(String chargerAddress)
	{
		this.chargerAddress = chargerAddress;
	}

	@Column(name="CHARGER_DUTY_DATE")
	public Date getChargerDutyDate()
	{
		return this.chargerDutyDate;
	}

	public void setChargerDutyDate(Date chargerDutyDate)
	{
		this.chargerDutyDate = chargerDutyDate;
	}

	@Column(name="CHARGER_POST_DATE")
	public Date getChargerPostDate()
	{
		return this.chargerPostDate;
	}

	public void setChargerPostDate(Date chargerPostDate)
	{
		this.chargerPostDate = chargerPostDate;
	}

	@Column(name="CHARGER_SPECIALIZED_NUM")
	public String getChargerSpecializedNum()
	{
		return this.chargerSpecializedNum;
	}

	public void setChargerSpecializedNum(String chargerSpecializedNum)
	{
		this.chargerSpecializedNum = chargerSpecializedNum;
	}

	@Column(name="CHARGER_REMARK")
	public String getChargerRemark()
	{
		return this.chargerRemark;
	}

	public void setChargerRemark(String chargerRemark)
	{
		this.chargerRemark = chargerRemark;
	}
	@Column(name="CHARGER_PHONE2")
	public String getChargerPhone2() {
		return chargerPhone2;
	}

	public void setChargerPhone2(String chargerPhone2) {
		this.chargerPhone2 = chargerPhone2;
	}

}
