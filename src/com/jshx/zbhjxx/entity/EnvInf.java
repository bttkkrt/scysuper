package com.jshx.zbhjxx.entity;

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
@Table(name="ENV_INF")
public class EnvInf extends BaseModel
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
	 * 周边环境类型
	 */
	private String surroundingEnvironmentType;

	/**
	 * 周边环境名称
	 */
	private String surroundingEnvironmentName;

	/**
	 * 周边环境方位
	 */
	private String surroundingEnvironment;

	/**
	 * 与危险源最小距离
	 */
	private String minimumDistance;

	/**
	 * 建筑结构
	 */
	private String buildingStructure;

	/**
	 * 建筑高度
	 */
	private String buildingHeight;

	/**
	 * 人员类型
	 */
	private String dangerousChemicalName;

	/**
	 * 人员数量
	 */
	private String personnelType;

	/**
	 * 联系人
	 */
	private String contactPerson;

	/**
	 * 联系人固定电话
	 */
	private String telephone;

	/**
	 * 联系人移动电话
	 */
	private String mobile;

	/**
	 * 联系人电子邮箱
	 */
	private String email;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;
	
	private String remark;
	
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
	
	public EnvInf(){
	}
	
	public EnvInf(String id, String areaId, String companyName, String surroundingEnvironmentName, String contactPerson,String surroundingEnvironmentType,String personnelType){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.surroundingEnvironmentName = surroundingEnvironmentName;

this.contactPerson = contactPerson;
this.surroundingEnvironmentType=surroundingEnvironmentType;

this.personnelType=personnelType;
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

	@Column(name="SURROUNDING_ENVIRONMENT_TYPE")
	public String getSurroundingEnvironmentType()
	{
		return this.surroundingEnvironmentType;
	}

	public void setSurroundingEnvironmentType(String surroundingEnvironmentType)
	{
		this.surroundingEnvironmentType = surroundingEnvironmentType;
	}

	@Column(name="SURROUNDING_ENVIRONMENT_NAME")
	public String getSurroundingEnvironmentName()
	{
		return this.surroundingEnvironmentName;
	}

	public void setSurroundingEnvironmentName(String surroundingEnvironmentName)
	{
		this.surroundingEnvironmentName = surroundingEnvironmentName;
	}

	@Column(name="SURROUNDING_ENVIRONMENT")
	public String getSurroundingEnvironment()
	{
		return this.surroundingEnvironment;
	}

	public void setSurroundingEnvironment(String surroundingEnvironment)
	{
		this.surroundingEnvironment = surroundingEnvironment;
	}

	@Column(name="MINIMUM_DISTANCE")
	public String getMinimumDistance()
	{
		return this.minimumDistance;
	}

	public void setMinimumDistance(String minimumDistance)
	{
		this.minimumDistance = minimumDistance;
	}

	@Column(name="BUILDING_STRUCTURE")
	public String getBuildingStructure()
	{
		return this.buildingStructure;
	}

	public void setBuildingStructure(String buildingStructure)
	{
		this.buildingStructure = buildingStructure;
	}

	@Column(name="BUILDING_HEIGHT")
	public String getBuildingHeight()
	{
		return this.buildingHeight;
	}

	public void setBuildingHeight(String buildingHeight)
	{
		this.buildingHeight = buildingHeight;
	}

	@Column(name="DANGEROUS_CHEMICAL_NAME")
	public String getDangerousChemicalName()
	{
		return this.dangerousChemicalName;
	}

	public void setDangerousChemicalName(String dangerousChemicalName)
	{
		this.dangerousChemicalName = dangerousChemicalName;
	}

	@Column(name="PERSONNEL_TYPE")
	public String getPersonnelType()
	{
		return this.personnelType;
	}

	public void setPersonnelType(String personnelType)
	{
		this.personnelType = personnelType;
	}

	@Column(name="CONTACT_PERSON")
	public String getContactPerson()
	{
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson)
	{
		this.contactPerson = contactPerson;
	}

	@Column(name="TELEPHONE")
	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	@Column(name="MOBILE")
	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
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

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

	@Column(name="LONGITUDE")
	public String getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	@Column(name="LATITUDE")
	public String getLatitude()
	{
		return this.latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
