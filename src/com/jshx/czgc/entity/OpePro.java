package com.jshx.czgc.entity;

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
@Table(name="OPE_PRO")
public class OpePro extends BaseModel
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
	 * 车间ID
	 */
	private String operationWorkshopId;

	/**
	 * 车间名称
	 */
	private String operationWorkshopName;

	/**
	 * 岗位名称
	 */
	private String operationPostname;

	/**
	 * 最大工作时间
	 */
	private String operationMostWorktime;

	/**
	 * 岗位员工数
	 */
	private String operationPostCount;

	/**
	 * 是否倒班
	 */
	private String operationShiftsOrnot;

	/**
	 * 倒班总人数
	 */
	private String operationShiftsPersons;

	/**
	 * 起草人
	 */
	private String operationDraftPerson;

	/**
	 * 批准人
	 */
	private String operationAuthorization;

	/**
	 * 操作规程编号
	 */
	private String operationCode;

	/**
	 * 关联附件id
	 */
	private String linkId;
	
	/**
	 * 有效时间
	 */
    private Date effectiveDate;
	
	public OpePro(){
	}
	
	public OpePro(String id, String areaName, String companyName, String operationWorkshopName,String operationPostname,String operationCode){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.operationWorkshopName = operationWorkshopName;

this.operationPostname = operationPostname;

this.operationCode = operationCode;
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

	@Column(name="OPERATION_WORKSHOP_ID")
	public String getOperationWorkshopId()
	{
		return this.operationWorkshopId;
	}

	public void setOperationWorkshopId(String operationWorkshopId)
	{
		this.operationWorkshopId = operationWorkshopId;
	}

	@Column(name="OPERATION_WORKSHOP_NAME")
	public String getOperationWorkshopName()
	{
		return this.operationWorkshopName;
	}

	public void setOperationWorkshopName(String operationWorkshopName)
	{
		this.operationWorkshopName = operationWorkshopName;
	}

	@Column(name="OPERATION_POSTNAME")
	public String getOperationPostname()
	{
		return this.operationPostname;
	}

	public void setOperationPostname(String operationPostname)
	{
		this.operationPostname = operationPostname;
	}

	@Column(name="OPERATION_MOST_WORKTIME")
	public String getOperationMostWorktime()
	{
		return this.operationMostWorktime;
	}

	public void setOperationMostWorktime(String operationMostWorktime)
	{
		this.operationMostWorktime = operationMostWorktime;
	}

	@Column(name="OPERATION_POST_COUNT")
	public String getOperationPostCount()
	{
		return this.operationPostCount;
	}

	public void setOperationPostCount(String operationPostCount)
	{
		this.operationPostCount = operationPostCount;
	}

	@Column(name="OPERATION_SHIFTS_ORNOT")
	public String getOperationShiftsOrnot()
	{
		return this.operationShiftsOrnot;
	}

	public void setOperationShiftsOrnot(String operationShiftsOrnot)
	{
		this.operationShiftsOrnot = operationShiftsOrnot;
	}

	@Column(name="OPERATION_SHIFTS_PERSONS")
	public String getOperationShiftsPersons()
	{
		return this.operationShiftsPersons;
	}

	public void setOperationShiftsPersons(String operationShiftsPersons)
	{
		this.operationShiftsPersons = operationShiftsPersons;
	}

	@Column(name="OPERATION_DRAFT_PERSON")
	public String getOperationDraftPerson()
	{
		return this.operationDraftPerson;
	}

	public void setOperationDraftPerson(String operationDraftPerson)
	{
		this.operationDraftPerson = operationDraftPerson;
	}

	@Column(name="OPERATION_AUTHORIZATION")
	public String getOperationAuthorization()
	{
		return this.operationAuthorization;
	}

	public void setOperationAuthorization(String operationAuthorization)
	{
		this.operationAuthorization = operationAuthorization;
	}

	@Column(name="OPERATION_CODE")
	public String getOperationCode()
	{
		return this.operationCode;
	}

	public void setOperationCode(String operationCode)
	{
		this.operationCode = operationCode;
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
	@Column(name="EFFECTIVE_DATE")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}
