package com.jshx.xcjcjl.entity;

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
@Table(name="SITE_CHECK_RECORD")
public class SiteCheckRecord extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 现场负责人
	 */
	private String chargePerson;
	
	/**
	 * 职务
	 */
	private String chargePersonZw;
	
	/**
	 * 现场负责人联系电话
	 */
	private String chargePersonTel;

	/**
	 * 检查场所
	 */
	private String checkAddress;

	/**
	 * 检查开始时间
	 */
	private Date startTime;

	/**
	 * 检查结束时间
	 */
	private Date endTime;

	/**
	 * 检查情况
	 */
	private String checkCondition;

	/**
	 * 检查人员
	 */
	private String checkPerson;
	
	/**
	 * 检查人员
	 */
	private String checkPersonName;
	
	private String checkPersonName1;
	
	private String companyAddress;
	
	
	public SiteCheckRecord(){
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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="CHARGE_PERSON")
	public String getChargePerson()
	{
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson)
	{
		this.chargePerson = chargePerson;
	}

	@Column(name="CHECK_ADDRESS")
	public String getCheckAddress()
	{
		return this.checkAddress;
	}

	public void setCheckAddress(String checkAddress)
	{
		this.checkAddress = checkAddress;
	}

	@Column(name="START_TIME")
	public Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	@Column(name="END_TIME")
	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	@Column(name="CHECK_CONDITION")
	public String getCheckCondition()
	{
		return this.checkCondition;
	}

	public void setCheckCondition(String checkCondition)
	{
		this.checkCondition = checkCondition;
	}

	@Column(name="CHECK_PERSON")
	public String getCheckPerson()
	{
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson)
	{
		this.checkPerson = checkPerson;
	}
	
	@Column(name="CHECK_PERSON_NAME")
	public String getCheckPersonName()
	{
		return this.checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName)
	{
		this.checkPersonName = checkPersonName;
	}
	@Column(name="CHARGE_PERSON_ZW")
	public String getChargePersonZw() {
		return chargePersonZw;
	}

	public void setChargePersonZw(String chargePersonZw) {
		this.chargePersonZw = chargePersonZw;
	}
	@Column(name="CHARGE_PERSON_TEL")
	public String getChargePersonTel() {
		return chargePersonTel;
	}

	public void setChargePersonTel(String chargePersonTel) {
		this.chargePersonTel = chargePersonTel;
	}


	@Column
	public String getCheckPersonName1() {
		return checkPersonName1;
	}



	public void setCheckPersonName1(String checkPersonName1) {
		this.checkPersonName1 = checkPersonName1;
	}


	@Column
	public String getCompanyAddress() {
		return companyAddress;
	}



	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}
