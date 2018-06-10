package com.jshx.aqsczj.entity;

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
@Table(name="SEC_PRO_MAJ")
public class SecProMaj extends BaseModel
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
	private String majordomoName;

	/**
	 * 性别
	 */
	private String majordomoSex;

	/**
	 * 国籍
	 */
	private String majordomoNationnality;

	/**
	 * 身份证号码
	 */
	private String majordomoCardNum;

	/**
	 * 最高学历
	 */
	private String majordomoHighestSchool;

	/**
	 * 最高学位
	 */
	private String majordomoHighestDegree;

	/**
	 * 毕业院校
	 */
	private String majordomoSchool;

	/**
	 * 专业
	 */
	private String majordomoSpecialized;

	/**
	 * 职称
	 */
	private String majordomoTitle;

	/**
	 * 联系电话1
	 */
	private String majordomoPhone;
	/**
	 * 联系电话2
	 */
	private String majordomoPhone2;

	/**
	 * 电子邮箱
	 */
	private String majordomoEmail;

	/**
	 * 住址
	 */
	private String majordomoAddress;

	/**
	 * 进入本单位日期
	 */
	private Date majordomoDutyDate;

	/**
	 * 从事本岗位时间
	 */
	private Date majordomoPostDate;

	/**
	 * 专业资格证号
	 */
	private String majordomoSpecializedNum;

	/**
	 * 备注
	 */
	private String majordomoRemark;
	

	
	public SecProMaj(){
	}
	
	public SecProMaj(String id, String areaId, String companyName, String majordomoName, String majordomoPhone, String majordomoSpecializedNum){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.majordomoName = majordomoName;

this.majordomoPhone = majordomoPhone;

this.majordomoSpecializedNum = majordomoSpecializedNum;
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

	@Column(name="MAJORDOMO_NAME")
	public String getMajordomoName()
	{
		return this.majordomoName;
	}

	public void setMajordomoName(String majordomoName)
	{
		this.majordomoName = majordomoName;
	}

	@Column(name="MAJORDOMO_SEX")
	public String getMajordomoSex()
	{
		return this.majordomoSex;
	}

	public void setMajordomoSex(String majordomoSex)
	{
		this.majordomoSex = majordomoSex;
	}

	@Column(name="MAJORDOMO_NATIONNALITY")
	public String getMajordomoNationnality()
	{
		return this.majordomoNationnality;
	}

	public void setMajordomoNationnality(String majordomoNationnality)
	{
		this.majordomoNationnality = majordomoNationnality;
	}

	@Column(name="MAJORDOMO_CARD_NUM")
	public String getMajordomoCardNum()
	{
		return this.majordomoCardNum;
	}

	public void setMajordomoCardNum(String majordomoCardNum)
	{
		this.majordomoCardNum = majordomoCardNum;
	}

	@Column(name="MAJORDOMO_HIGHEST_SCHOOL")
	public String getMajordomoHighestSchool()
	{
		return this.majordomoHighestSchool;
	}

	public void setMajordomoHighestSchool(String majordomoHighestSchool)
	{
		this.majordomoHighestSchool = majordomoHighestSchool;
	}

	@Column(name="MAJORDOMO_HIGHEST_DEGREE")
	public String getMajordomoHighestDegree()
	{
		return this.majordomoHighestDegree;
	}

	public void setMajordomoHighestDegree(String majordomoHighestDegree)
	{
		this.majordomoHighestDegree = majordomoHighestDegree;
	}

	@Column(name="MAJORDOMO_SCHOOL")
	public String getMajordomoSchool()
	{
		return this.majordomoSchool;
	}

	public void setMajordomoSchool(String majordomoSchool)
	{
		this.majordomoSchool = majordomoSchool;
	}

	@Column(name="MAJORDOMO_SPECIALIZED")
	public String getMajordomoSpecialized()
	{
		return this.majordomoSpecialized;
	}

	public void setMajordomoSpecialized(String majordomoSpecialized)
	{
		this.majordomoSpecialized = majordomoSpecialized;
	}

	@Column(name="MAJORDOMO_TITLE")
	public String getMajordomoTitle()
	{
		return this.majordomoTitle;
	}

	public void setMajordomoTitle(String majordomoTitle)
	{
		this.majordomoTitle = majordomoTitle;
	}

	@Column(name="MAJORDOMO_PHONE")
	public String getMajordomoPhone()
	{
		return this.majordomoPhone;
	}

	public void setMajordomoPhone(String majordomoPhone)
	{
		this.majordomoPhone = majordomoPhone;
	}

	@Column(name="MAJORDOMO_PHONE2")
	public String getMajordomoPhone2()
	{
		return this.majordomoPhone2;
	}

	public void setMajordomoPhone2(String majordomoPhone2)
	{
		this.majordomoPhone2 = majordomoPhone2;
	}

	@Column(name="MAJORDOMO_EMAIL")
	public String getMajordomoEmail()
	{
		return this.majordomoEmail;
	}

	public void setMajordomoEmail(String majordomoEmail)
	{
		this.majordomoEmail = majordomoEmail;
	}

	@Column(name="MAJORDOMO_ADDRESS")
	public String getMajordomoAddress()
	{
		return this.majordomoAddress;
	}

	public void setMajordomoAddress(String majordomoAddress)
	{
		this.majordomoAddress = majordomoAddress;
	}

	@Column(name="MAJORDOMO_DUTY_DATE")
	public Date getMajordomoDutyDate()
	{
		return this.majordomoDutyDate;
	}

	public void setMajordomoDutyDate(Date majordomoDutyDate)
	{
		this.majordomoDutyDate = majordomoDutyDate;
	}

	@Column(name="MAJORDOMO_POST_DATE")
	public Date getMajordomoPostDate()
	{
		return this.majordomoPostDate;
	}

	public void setMajordomoPostDate(Date majordomoPostDate)
	{
		this.majordomoPostDate = majordomoPostDate;
	}

	@Column(name="MAJORDOMO_SPECIALIZED_NUM")
	public String getMajordomoSpecializedNum()
	{
		return this.majordomoSpecializedNum;
	}

	public void setMajordomoSpecializedNum(String majordomoSpecializedNum)
	{
		this.majordomoSpecializedNum = majordomoSpecializedNum;
	}

	@Column(name="MAJORDOMO_REMARK")
	public String getMajordomoRemark()
	{
		return this.majordomoRemark;
	}

	public void setMajordomoRemark(String majordomoRemark)
	{
		this.majordomoRemark = majordomoRemark;
	}

}
