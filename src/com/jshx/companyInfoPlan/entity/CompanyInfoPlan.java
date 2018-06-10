package com.jshx.companyInfoPlan.entity;

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
@Table(name="COMPANY_INFO_PLAN")
public class CompanyInfoPlan extends BaseModel
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
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 所属区域
	 */
	private String area;
	
	private String areaId;
	
	/**
	 * 地址
	 */
	private String address;

	/**
	 * 主要负责人
	 */
	private String charger;

	/**
	 * 联系电话
	 */
	private String chargerPhone;

	/**
	 * 联系人
	 */
	private String contact;

	/**
	 * 联系电话
	 */
	private String contactPhone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 类型
	 */
	private String typeDetail;

	/**
	 * 监督检查计划id
	 */
	private String planbId;
	
	
	/**
	 * 是否有未完成任务（0：没有；1：有）
	 */
	private String ifFinish;
	
	/**
	 * 备注
	 */
	private String remark;
	
	public CompanyInfoPlan(){
	}
	
	public CompanyInfoPlan(String id){
this.id = id;
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

	
	@Column(name="COMPANY_NAME")
	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	@Column(name="AREA")
	public String getArea()
	{
		return this.area;
	}

	public void setArea(String area)
	{
		this.area = area;
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

	@Column(name="CHARGER")
	public String getCharger()
	{
		return this.charger;
	}

	public void setCharger(String charger)
	{
		this.charger = charger;
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

	@Column(name="CONTACT")
	public String getContact()
	{
		return this.contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	@Column(name="CONTACT_PHONE")
	public String getContactPhone()
	{
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}

	@Column(name="EMAIL")
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name="TYPE_DETAIL")
	public String getTypeDetail()
	{
		return this.typeDetail;
	}

	public void setTypeDetail(String typeDetail)
	{
		this.typeDetail = typeDetail;
	}
	@Column(name="PLAN_ID")
	public String getPlanbId() {
		return planbId;
	}

	public void setPlanbId(String planbId) {
		this.planbId = planbId;
	}

	public String getIfFinish() {
		return ifFinish;
	}

	public void setIfFinish(String ifFinish) {
		this.ifFinish = ifFinish;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	

}
