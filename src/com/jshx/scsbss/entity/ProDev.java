package com.jshx.scsbss.entity;

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
@Table(name="PRO_DEV")
public class ProDev extends BaseModel
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
	 * 车间ID
	 */
	private String deviceWorkshopId;

	/**
	 * 车间名称
	 */
	private String deviceWorkshopName;

	/**
	 * 设备名称
	 */
	private String deviceName;

	/**
	 * 设备用途
	 */
	private String deviceUse;

	/**
	 * 设备编号
	 */
	private String deviceCode;

	/**
	 * 设备型号
	 */
	private String deviceType;

	/**
	 * 设备出厂日期
	 */
	private Date deviceManufactureDate;

	/**
	 * 设备生产商
	 */
	private String deviceProductCommpnay;

	/**
	 * 备注
	 */
	private String deviceRemark;

	/**
	 * 关联附件id
	 */
	private String linkId;

	
	public ProDev(){
	}
	
	public ProDev(String id, String areaId, String companyName, String deviceWorkshopName, String deviceName, String deviceCode){
this.id = id;

this.areaId = areaId;

this.companyName = companyName;

this.deviceWorkshopName = deviceWorkshopName;

this.deviceName = deviceName;

this.deviceCode = deviceCode;
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

	@Column(name="DEVICE_WORKSHOP_ID")
	public String getDeviceWorkshopId()
	{
		return this.deviceWorkshopId;
	}

	public void setDeviceWorkshopId(String deviceWorkshopId)
	{
		this.deviceWorkshopId = deviceWorkshopId;
	}

	@Column(name="DEVICE_WORKSHOP_NAME")
	public String getDeviceWorkshopName()
	{
		return this.deviceWorkshopName;
	}

	public void setDeviceWorkshopName(String deviceWorkshopName)
	{
		this.deviceWorkshopName = deviceWorkshopName;
	}

	@Column(name="DEVICE_NAME")
	public String getDeviceName()
	{
		return this.deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}

	@Column(name="DEVICE_USE")
	public String getDeviceUse()
	{
		return this.deviceUse;
	}

	public void setDeviceUse(String deviceUse)
	{
		this.deviceUse = deviceUse;
	}

	@Column(name="DEVICE_CODE")
	public String getDeviceCode()
	{
		return this.deviceCode;
	}

	public void setDeviceCode(String deviceCode)
	{
		this.deviceCode = deviceCode;
	}

	@Column(name="DEVICE_TYPE")
	public String getDeviceType()
	{
		return this.deviceType;
	}

	public void setDeviceType(String deviceType)
	{
		this.deviceType = deviceType;
	}

	@Column(name="DEVICE_MANUFACTURE_DATE")
	public Date getDeviceManufactureDate()
	{
		return this.deviceManufactureDate;
	}

	public void setDeviceManufactureDate(Date deviceManufactureDate)
	{
		this.deviceManufactureDate = deviceManufactureDate;
	}

	@Column(name="DEVICE_PRODUCT_COMMPNAY")
	public String getDeviceProductCommpnay()
	{
		return this.deviceProductCommpnay;
	}

	public void setDeviceProductCommpnay(String deviceProductCommpnay)
	{
		this.deviceProductCommpnay = deviceProductCommpnay;
	}

	@Column(name="DEVICE_REMARK")
	public String getDeviceRemark()
	{
		return this.deviceRemark;
	}

	public void setDeviceRemark(String deviceRemark)
	{
		this.deviceRemark = deviceRemark;
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

}
