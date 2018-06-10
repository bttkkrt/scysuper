package com.jshx.aqscfysytz.entity;

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
@Table(name="SEC_PRO_FEE_ACC")
public class SecProFeeAcc extends BaseModel
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
	 * 项目
	 */
	private String feeAccountProject;

	/**
	 * 支出金额
	 */
	private String feeAccountAmount;

	/**
	 * 使用月份
	 */
	private String feeAccountMonth;

	/**
	 * 备注
	 */
	private String feeAccountRemark;

	
	public SecProFeeAcc(){
	}
	
	public SecProFeeAcc(String id, String areaName, String companyName, String feeAccountAmount, String feeAccountMonth,String companyId,String feeAccountRemark){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.feeAccountAmount = feeAccountAmount;

this.feeAccountMonth = feeAccountMonth;

this.companyId = companyId;
this.feeAccountRemark=feeAccountRemark;
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

	@Column(name="FEE_ACCOUNT_PROJECT")
	public String getFeeAccountProject()
	{
		return this.feeAccountProject;
	}

	public void setFeeAccountProject(String feeAccountProject)
	{
		this.feeAccountProject = feeAccountProject;
	}

	@Column(name="FEE_ACCOUNT_AMOUNT")
	public String getFeeAccountAmount()
	{
		return this.feeAccountAmount;
	}

	public void setFeeAccountAmount(String feeAccountAmount)
	{
		this.feeAccountAmount = feeAccountAmount;
	}

	@Column(name="FEE_ACCOUNT_MONTH")
	public String getFeeAccountMonth()
	{
		return this.feeAccountMonth;
	}

	public void setFeeAccountMonth(String feeAccountMonth)
	{
		this.feeAccountMonth = feeAccountMonth;
	}

	@Column(name="FEE_ACCOUNT_REMARK")
	public String getFeeAccountRemark()
	{
		return this.feeAccountRemark;
	}

	public void setFeeAccountRemark(String feeAccountRemark)
	{
		this.feeAccountRemark = feeAccountRemark;
	}

}
