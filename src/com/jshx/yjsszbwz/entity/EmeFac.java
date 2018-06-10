package com.jshx.yjsszbwz.entity;

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
@Table(name="EME_FAC")
public class EmeFac extends BaseModel
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
	 * 物资级别
	 */
	private String facilityLevel;

	/**
	 * 物资数量
	 */
	private String facilityNumber;

	/**
	 * 物资型号
	 */
	private String facilityModel;

	/**
	 * 物资规格
	 */
	private String facilitySpecific;

	/**
	 * 购入日期
	 */
	private Date purchaseDate;

	/**
	 * 生产厂家
	 */
	private String vender;

	/**
	 * 出厂日期
	 */
	private Date produceTime;

	/**
	 * 有效期至
	 */
	private Date expiryDate;

	/**
	 * 用途说明
	 */
	private String purposeDescrip;

	/**
	 * 性能说明
	 */
	private String performanceDescrip;

	/**
	 * 存放地点
	 */
	private String storageLocation;

	/**
	 * 负责保管人
	 */
	private String keeper;

	/**
	 * 保管人联系方式
	 */
	private String keeperPhone;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 附件关联id
	 */
	private String linkId;

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
	 * 物资名称
	 */
	private String facilityName;

	/**
	 * type=0表示企业
	 */
	private String type;
	
	/**
	 * 新增地图key  用于关联地图空间表数据
	 * lj
	 * 2015-11-17
	 */
	public String mapkey;
	
	public EmeFac(){
	}
	
	public EmeFac(String id, String facilityLevel, String areaName, String companyName, String facilityName,String createUserID){
this.id = id;

this.facilityLevel = facilityLevel;

this.areaName = areaName;

this.companyName = companyName;

this.facilityName = facilityName;

this.createUserID = createUserID;
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

	
	@Column(name="FACILITY_LEVEL")
	public String getFacilityLevel()
	{
		return this.facilityLevel;
	}

	public void setFacilityLevel(String facilityLevel)
	{
		this.facilityLevel = facilityLevel;
	}

	@Column(name="FACILITY_NUMBER")
	public String getFacilityNumber()
	{
		return this.facilityNumber;
	}

	public void setFacilityNumber(String facilityNumber)
	{
		this.facilityNumber = facilityNumber;
	}

	@Column(name="FACILITY_MODEL")
	public String getFacilityModel()
	{
		return this.facilityModel;
	}

	public void setFacilityModel(String facilityModel)
	{
		this.facilityModel = facilityModel;
	}

	@Column(name="FACILITY_SPECIFIC")
	public String getFacilitySpecific()
	{
		return this.facilitySpecific;
	}

	public void setFacilitySpecific(String facilitySpecific)
	{
		this.facilitySpecific = facilitySpecific;
	}

	@Column(name="PURCHASE_DATE")
	public Date getPurchaseDate()
	{
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	@Column(name="VENDER")
	public String getVender()
	{
		return this.vender;
	}

	public void setVender(String vender)
	{
		this.vender = vender;
	}

	@Column(name="PRODUCE_TIME")
	public Date getProduceTime()
	{
		return this.produceTime;
	}

	public void setProduceTime(Date produceTime)
	{
		this.produceTime = produceTime;
	}

	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate()
	{
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	@Column(name="PURPOSE_DESCRIP")
	public String getPurposeDescrip()
	{
		return this.purposeDescrip;
	}

	public void setPurposeDescrip(String purposeDescrip)
	{
		this.purposeDescrip = purposeDescrip;
	}

	@Column(name="PERFORMANCE_DESCRIP")
	public String getPerformanceDescrip()
	{
		return this.performanceDescrip;
	}

	public void setPerformanceDescrip(String performanceDescrip)
	{
		this.performanceDescrip = performanceDescrip;
	}

	@Column(name="STORAGE_LOCATION")
	public String getStorageLocation()
	{
		return this.storageLocation;
	}

	public void setStorageLocation(String storageLocation)
	{
		this.storageLocation = storageLocation;
	}

	@Column(name="KEEPER")
	public String getKeeper()
	{
		return this.keeper;
	}

	public void setKeeper(String keeper)
	{
		this.keeper = keeper;
	}

	@Column(name="KEEPER_PHONE")
	public String getKeeperPhone()
	{
		return this.keeperPhone;
	}

	public void setKeeperPhone(String keeperPhone)
	{
		this.keeperPhone = keeperPhone;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column
	public String getMapkey() {
		return mapkey;
	}

	public void setMapkey(String mapkey) {
		this.mapkey = mapkey;
	}

}
