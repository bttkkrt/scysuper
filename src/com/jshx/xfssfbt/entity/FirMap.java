package com.jshx.xfssfbt.entity;

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
@Table(name="FIR_MAP")
public class FirMap extends BaseModel
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
	 * 设施名称
	 */
	private String facilityName;

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
	
	public FirMap(){
	}
	
	public FirMap(String id, String areaName, String companyName, String facilityName){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.facilityName = facilityName;
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

	@Column(name="FACILITY_NAME")
	public String getFacilityName()
	{
		return this.facilityName;
	}

	public void setFacilityName(String facilityName)
	{
		this.facilityName = facilityName;
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

}
