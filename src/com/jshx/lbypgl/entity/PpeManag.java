package com.jshx.lbypgl.entity;

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
@Table(name="PPE_MANAG")
public class PpeManag extends BaseModel
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
	 * 用品编号
	 */
	private String ppeNo;

	/**
	 * 用品名称
	 */
	private String ppeName;

	/**
	 * 用品分类
	 */
	private String ppeType;

	/**
	 * 用品单位
	 */
	private String ppeCompany;

	/**
	 * 用品数量
	 */
	private String ppeNum;

	/**
	 * 备注
	 */
	private String ppeRemark;

	
	public PpeManag(){
	}
	
	public PpeManag(String id, String areaId, String companyName, String ppeNo, String ppeName, String ppeType, String ppeNum){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.ppeNo = ppeNo;

this.ppeName = ppeName;

this.ppeType = ppeType;

this.ppeNum = ppeNum;
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

	@Column(name="PPE_NO")
	public String getPpeNo()
	{
		return this.ppeNo;
	}

	public void setPpeNo(String ppeNo)
	{
		this.ppeNo = ppeNo;
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

	@Column(name="PPE_TYPE")
	public String getPpeType()
	{
		return this.ppeType;
	}

	public void setPpeType(String ppeType)
	{
		this.ppeType = ppeType;
	}

	@Column(name="PPE_COMPANY")
	public String getPpeCompany()
	{
		return this.ppeCompany;
	}

	public void setPpeCompany(String ppeCompany)
	{
		this.ppeCompany = ppeCompany;
	}

	@Column(name="PPE_NUM")
	public String getPpeNum()
	{
		return this.ppeNum;
	}

	public void setPpeNum(String ppeNum)
	{
		this.ppeNum = ppeNum;
	}

	@Column(name="PPE_REMARK")
	public String getPpeRemark()
	{
		return this.ppeRemark;
	}

	public void setPpeRemark(String ppeRemark)
	{
		this.ppeRemark = ppeRemark;
	}

}
