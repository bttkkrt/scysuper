package com.jshx.mxbz.entity;

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
@Table(name="SIGNS")
public class Signs extends BaseModel
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
	 * 标志类别
	 */
	private String signsType;

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
	 * 标志编号
	 */
	private String signsNo;

	/**
	 * 标志名称
	 */
	private String signsName;

	/**
	 * 标志内容
	 */
	private String signsContent;

	/**
	 * 标志地址
	 */
	private String signsAddress;

	/**
	 * 新增地图key  用于关联地图空间表数据
	 * lj
	 * 2015-11-17
	 */
	public String mapkey;
	
	@Column
	public String getMapkey() {
		return mapkey;
	}

	public void setMapkey(String mapkey) {
		this.mapkey = mapkey;
	}
	
	public Signs(){
	}
	
	public Signs(String id, String signsType, String areaName, String companyName, String signsNo, String signsName){
this.id = id;

this.signsType = signsType;

this.areaName = areaName;

this.companyName = companyName;

this.signsNo = signsNo;

this.signsName = signsName;
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

	
	@Column(name="SIGNS_TYPE")
	public String getSignsType()
	{
		return this.signsType;
	}

	public void setSignsType(String signsType)
	{
		this.signsType = signsType;
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

	@Column(name="SIGNS_NO")
	public String getSignsNo()
	{
		return this.signsNo;
	}

	public void setSignsNo(String signsNo)
	{
		this.signsNo = signsNo;
	}

	@Column(name="SIGNS_NAME")
	public String getSignsName()
	{
		return this.signsName;
	}

	public void setSignsName(String signsName)
	{
		this.signsName = signsName;
	}

	@Column(name="SIGNS_CONTENT")
	public String getSignsContent()
	{
		return this.signsContent;
	}

	public void setSignsContent(String signsContent)
	{
		this.signsContent = signsContent;
	}

	@Column(name="SIGNS_ADDRESS")
	public String getSignsAddress()
	{
		return this.signsAddress;
	}

	public void setSignsAddress(String signsAddress)
	{
		this.signsAddress = signsAddress;
	}
}
