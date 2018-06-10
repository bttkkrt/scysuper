package com.jshx.qyaqscmb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SEC_PRO_GOA")
public class SecProGoa extends BaseModel
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
	 * 总体生产目标
	 */
	private String productGoalTotalGoal;

	/**
	 * 年度
	 */
	private Date productGoalYear;

	/**
	 * 年度安全目标
	 */
	private String productGoalYearGoal;

	/**
	 * 备注
	 */
	private String productGoalRemak;

	/**
	 * 附件关联id
	 */
	private String linkId;

	/**
	 * 所在区域
	 */
	private String areaId;

	
	public SecProGoa(){
	}
	
	public SecProGoa(String id, String areaName, String companyName, Date productGoalYear,String productGoalYearGoal){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.productGoalYear = productGoalYear;

this.productGoalYearGoal = productGoalYearGoal;
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

	@Column(name="PRODUCT_GOAL_TOTAL_GOAL")
	public String getProductGoalTotalGoal()
	{
		return this.productGoalTotalGoal;
	}

	public void setProductGoalTotalGoal(String productGoalTotalGoal)
	{
		this.productGoalTotalGoal = productGoalTotalGoal;
	}

	@Column(name="PRODUCT_GOAL_YEAR")
	public Date getProductGoalYear()
	{
		return this.productGoalYear;
	}

	public void setProductGoalYear(Date productGoalYear)
	{
		this.productGoalYear = productGoalYear;
	}

	@Column(name="PRODUCT_GOAL_YEAR_GOAL")
	public String getProductGoalYearGoal()
	{
		return this.productGoalYearGoal;
	}

	public void setProductGoalYearGoal(String productGoalYearGoal)
	{
		this.productGoalYearGoal = productGoalYearGoal;
	}

	@Column(name="PRODUCT_GOAL_REMAK")
	public String getProductGoalRemak()
	{
		return this.productGoalRemak;
	}

	public void setProductGoalRemak(String productGoalRemak)
	{
		this.productGoalRemak = productGoalRemak;
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

	@Column(name="AREA_ID")
	public String getAreaId()
	{
		return this.areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

}
