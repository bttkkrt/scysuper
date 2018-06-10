package com.jshx.lbypkfgl.entity;

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
@Table(name="PPE_WARE_MANAG")
public class PpeWareManag extends BaseModel
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
	 * 用品id
	 */
	private String ppeId;

	/**
	 * 用品名称
	 */
	private String ppeName;

	/**
	 * 类型
	 */
	private String ppeWareType;

	/**
	 * 数量
	 */
	private String ppeWareNum;

	/**
	 * 盘点人
	 */
	private String ppeWarePeople;

	/**
	 * 盘点时间
	 */
	private Date ppeWareTime;

	/**
	 * 备注
	 */
	private String ppeWareRemark;

	
	public PpeWareManag(){
	}
	
	public PpeWareManag(String id, String areaId, String companyName, String ppeName, String ppeWareType, String ppeWareNum, String ppeWarePeople){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.ppeName = ppeName;

this.ppeWareType = ppeWareType;

this.ppeWareNum = ppeWareNum;

this.ppeWarePeople = ppeWarePeople;
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

	@Column(name="PPE_ID")
	public String getPpeId()
	{
		return this.ppeId;
	}

	public void setPpeId(String ppeId)
	{
		this.ppeId = ppeId;
	}

	@Column(name="PPE_NAME")
	public String getPpeName()
	{
		return this.ppeName;
	}

	public void setPpeName(String ppeName)
	{
		this.ppeName = ppeName;
	}

	@Column(name="PPE_WARE_TYPE")
	public String getPpeWareType()
	{
		return this.ppeWareType;
	}

	public void setPpeWareType(String ppeWareType)
	{
		this.ppeWareType = ppeWareType;
	}

	@Column(name="PPE_WARE_NUM")
	public String getPpeWareNum()
	{
		return this.ppeWareNum;
	}

	public void setPpeWareNum(String ppeWareNum)
	{
		this.ppeWareNum = ppeWareNum;
	}

	@Column(name="PPE_WARE_PEOPLE")
	public String getPpeWarePeople()
	{
		return this.ppeWarePeople;
	}

	public void setPpeWarePeople(String ppeWarePeople)
	{
		this.ppeWarePeople = ppeWarePeople;
	}

	@Column(name="PPE_WARE_TIME")
	public Date getPpeWareTime()
	{
		return this.ppeWareTime;
	}

	public void setPpeWareTime(Date ppeWareTime)
	{
		this.ppeWareTime = ppeWareTime;
	}

	@Column(name="PPE_WARE_REMARK")
	public String getPpeWareRemark()
	{
		return this.ppeWareRemark;
	}

	public void setPpeWareRemark(String ppeWareRemark)
	{
		this.ppeWareRemark = ppeWareRemark;
	}

}
