package com.jshx.sccjgl.entity;

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
@Table(name="WORKSHOP")
public class Workshop extends BaseModel
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
	 * 车间名称
	 */
	private String workshopName;

	/**
	 * 车间编号
	 */
	private String workshopCode;

	/**
	 * 员工数
	 */
	private String workshopWorkers;

	/**
	 * 车间负责人
	 */
	private String workshopCharge;

	/**
	 * 操作方式
	 */
	private String workshopOperation;

	
	public Workshop(){
	}
	
	public Workshop(String id, String areaId, String companyName, String workshopName, String workshopCode, String workshopWorkers, String workshopCharge, String workshopOperation){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.workshopName = workshopName;

this.workshopCode = workshopCode;

this.workshopWorkers = workshopWorkers;

this.workshopCharge = workshopCharge;

this.workshopOperation = workshopOperation;
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

	@Column(name="WORKSHOP_NAME")
	public String getWorkshopName()
	{
		return this.workshopName;
	}

	public void setWorkshopName(String workshopName)
	{
		this.workshopName = workshopName;
	}

	@Column(name="WORKSHOP_CODE")
	public String getWorkshopCode()
	{
		return this.workshopCode;
	}

	public void setWorkshopCode(String workshopCode)
	{
		this.workshopCode = workshopCode;
	}

	@Column(name="WORKSHOP_WORKERS")
	public String getWorkshopWorkers()
	{
		return this.workshopWorkers;
	}

	public void setWorkshopWorkers(String workshopWorkers)
	{
		this.workshopWorkers = workshopWorkers;
	}

	@Column(name="WORKSHOP_CHARGE")
	public String getWorkshopCharge()
	{
		return this.workshopCharge;
	}

	public void setWorkshopCharge(String workshopCharge)
	{
		this.workshopCharge = workshopCharge;
	}

	@Column(name="WORKSHOP_OPERATION")
	public String getWorkshopOperation()
	{
		return this.workshopOperation;
	}

	public void setWorkshopOperation(String workshopOperation)
	{
		this.workshopOperation = workshopOperation;
	}

}
