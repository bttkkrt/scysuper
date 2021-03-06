package com.jshx.jsxmzzybwhyp.entity;

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
@Table(name="PRO_EVA_NOT")
public class ProEvaNot extends BaseModel
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
	 * 编号
	 */
	private String proNo;

	/**
	 * 预备字
	 */
	private String preparatoryWord;

	/**
	 * 排号
	 */
	private String arranging;

	/**
	 * 日期
	 */
	private String proDate;

	/**
	 * 建设项目名称
	 */
	private String projectName;

	/**
	 * 附件关联id
	 */
	private String linkId;

	private String conPeople;
	private String conPhone;
	public ProEvaNot(){
	}
	
	public ProEvaNot(String id, String areaName, String companyName,String proDate,String createUserID){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;
this.proDate = proDate;
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

	@Column(name="PRO_NO")
	public String getProNo()
	{
		return this.proNo;
	}

	public void setProNo(String proNo)
	{
		this.proNo = proNo;
	}

	@Column(name="PREPARATORY_WORD")
	public String getPreparatoryWord()
	{
		return this.preparatoryWord;
	}

	public void setPreparatoryWord(String preparatoryWord)
	{
		this.preparatoryWord = preparatoryWord;
	}

	@Column(name="ARRANGING")
	public String getArranging()
	{
		return this.arranging;
	}

	public void setArranging(String arranging)
	{
		this.arranging = arranging;
	}

	@Column(name="PRO_DATE")
	public String getProDate()
	{
		return this.proDate;
	}

	public void setProDate(String proDate)
	{
		this.proDate = proDate;
	}

	@Column(name="PROJECT_NAME")
	public String getProjectName()
	{
		return this.projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

	public void setConPeople(String conPeople) {
		this.conPeople = conPeople;
	}
	@Column(name="CON_PEOPLE")
	public String getConPeople() {
		return conPeople;
	}

	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}
	@Column(name="CON_PHONE")
	public String getConPhone() {
		return conPhone;
	}

}
