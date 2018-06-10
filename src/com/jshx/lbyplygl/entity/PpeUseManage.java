package com.jshx.lbyplygl.entity;

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
@Table(name="PPE_USE_MANAGE")
public class PpeUseManage extends BaseModel
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
	 * 领用数量
	 */
	private String ppeUseNum;

	/**
	 * 领用人
	 */
	private String ppeUsePeople;

	/**
	 * 领用时间
	 */
	private Date ppeUseTime;

	/**
	 * 备注
	 */
	private String ppeUseRemark;

	
	public PpeUseManage(){
	}
	
	public PpeUseManage(String id, String areaId, String companyName, String ppeName, String ppeUseNum, String ppeUsePeople){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.ppeName = ppeName;

this.ppeUseNum = ppeUseNum;

this.ppeUsePeople = ppeUsePeople;
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

	@Column(name="PPE_USE_NUM")
	public String getPpeUseNum()
	{
		return this.ppeUseNum;
	}

	public void setPpeUseNum(String ppeUseNum)
	{
		this.ppeUseNum = ppeUseNum;
	}

	@Column(name="PPE_USE_PEOPLE")
	public String getPpeUsePeople()
	{
		return this.ppeUsePeople;
	}

	public void setPpeUsePeople(String ppeUsePeople)
	{
		this.ppeUsePeople = ppeUsePeople;
	}

	@Column(name="PPE_USE_TIME")
	public Date getPpeUseTime()
	{
		return this.ppeUseTime;
	}

	public void setPpeUseTime(Date ppeUseTime)
	{
		this.ppeUseTime = ppeUseTime;
	}

	@Column(name="PPE_USE_REMARK")
	public String getPpeUseRemark()
	{
		return this.ppeUseRemark;
	}

	public void setPpeUseRemark(String ppeUseRemark)
	{
		this.ppeUseRemark = ppeUseRemark;
	}

}
