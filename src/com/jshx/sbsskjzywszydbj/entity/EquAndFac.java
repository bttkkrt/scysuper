package com.jshx.sbsskjzywszydbj.entity;

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
@Table(name="EQU_AND_FAC")
public class EquAndFac extends BaseModel
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
	 * 设备名称
	 */
	private String equipmentName;

	/**
	 * 设备地点
	 */
	private String equipmentPlace;

	/**
	 * 设备经度
	 */
	private String equipmentLongitude;

	/**
	 * 设备纬度
	 */
	private String equipmentLatitude;

	/**
	 * 设备编号
	 */
	private String equipmentNumber;

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
	public EquAndFac(){
	}
	
	public EquAndFac(String id, String areaId, String companyName, String equipmentName, String equipmentNumber){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.equipmentName = equipmentName;

this.equipmentNumber = equipmentNumber;
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

	@Column(name="EQUIPMENT_NAME")
	public String getEquipmentName()
	{
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName)
	{
		this.equipmentName = equipmentName;
	}

	@Column(name="EQUIPMENT_PLACE")
	public String getEquipmentPlace()
	{
		return this.equipmentPlace;
	}

	public void setEquipmentPlace(String equipmentPlace)
	{
		this.equipmentPlace = equipmentPlace;
	}

	@Column(name="EQUIPMENT_LONGITUDE")
	public String getEquipmentLongitude()
	{
		return this.equipmentLongitude;
	}

	public void setEquipmentLongitude(String equipmentLongitude)
	{
		this.equipmentLongitude = equipmentLongitude;
	}

	@Column(name="EQUIPMENT_LATITUDE")
	public String getEquipmentLatitude()
	{
		return this.equipmentLatitude;
	}

	public void setEquipmentLatitude(String equipmentLatitude)
	{
		this.equipmentLatitude = equipmentLatitude;
	}

	@Column(name="EQUIPMENT_NUMBER")
	public String getEquipmentNumber()
	{
		return this.equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber)
	{
		this.equipmentNumber = equipmentNumber;
	}

}
