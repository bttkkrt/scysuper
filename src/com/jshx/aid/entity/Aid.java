package com.jshx.aid.entity;

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
@Table(name="AID")
public class Aid extends BaseModel
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
	 * 物资名称
	 */
	private String suppliedName;

	/**
	 * 物资级别
	 */
	private String suppliedLevel;

	/**
	 * 物资数量
	 */
	private String suppliedCount;

	/**
	 * 物资型号
	 */
	private String suppliedModel;

	/**
	 * 物资规格
	 */
	private String suppliedSpecificate;

	/**
	 * 购入日期
	 */
	private Date purchaseDate;

	/**
	 * 生产厂家
	 */
	private String manufacture;

	/**
	 * 出厂日期
	 */
	private Date manufactureDate;

	/**
	 * 有效期至
	 */
	private Date validity;

	/**
	 * 用途说明
	 */
	private String application;

	/**
	 * 性能说明
	 */
	private String performance;

	/**
	 * 存放地点
	 */
	private String storageLocation;

	/**
	 * 负责保管人
	 */
	private String custodian;

	/**
	 * 保管人联系方式
	 */
	private String custodianMoblie;

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
	 * 关联附件id
	 */
	private String linkId;

	
	public Aid(){
	}
	
	public Aid(String id, String suppliedName, String suppliedLevel, String suppliedSpecificate){
this.id = id;

this.suppliedName = suppliedName;

this.suppliedLevel = suppliedLevel;

this.suppliedSpecificate = suppliedSpecificate;
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

	
	@Column(name="SUPPLIED_NAME")
	public String getSuppliedName()
	{
		return this.suppliedName;
	}

	public void setSuppliedName(String suppliedName)
	{
		this.suppliedName = suppliedName;
	}

	@Column(name="SUPPLIED_LEVEL")
	public String getSuppliedLevel()
	{
		return this.suppliedLevel;
	}

	public void setSuppliedLevel(String suppliedLevel)
	{
		this.suppliedLevel = suppliedLevel;
	}

	@Column(name="SUPPLIED_COUNT")
	public String getSuppliedCount()
	{
		return this.suppliedCount;
	}

	public void setSuppliedCount(String suppliedCount)
	{
		this.suppliedCount = suppliedCount;
	}

	@Column(name="SUPPLIED_MODEL")
	public String getSuppliedModel()
	{
		return this.suppliedModel;
	}

	public void setSuppliedModel(String suppliedModel)
	{
		this.suppliedModel = suppliedModel;
	}

	@Column(name="SUPPLIED_SPECIFICATE")
	public String getSuppliedSpecificate()
	{
		return this.suppliedSpecificate;
	}

	public void setSuppliedSpecificate(String suppliedSpecificate)
	{
		this.suppliedSpecificate = suppliedSpecificate;
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

	@Column(name="MANUFACTURE")
	public String getManufacture()
	{
		return this.manufacture;
	}

	public void setManufacture(String manufacture)
	{
		this.manufacture = manufacture;
	}

	@Column(name="MANUFACTURE_DATE")
	public Date getManufactureDate()
	{
		return this.manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate)
	{
		this.manufactureDate = manufactureDate;
	}

	@Column(name="VALIDITY")
	public Date getValidity()
	{
		return this.validity;
	}

	public void setValidity(Date validity)
	{
		this.validity = validity;
	}

	@Column(name="APPLICATION")
	public String getApplication()
	{
		return this.application;
	}

	public void setApplication(String application)
	{
		this.application = application;
	}

	@Column(name="PERFORMANCE")
	public String getPerformance()
	{
		return this.performance;
	}

	public void setPerformance(String performance)
	{
		this.performance = performance;
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

	@Column(name="CUSTODIAN")
	public String getCustodian()
	{
		return this.custodian;
	}

	public void setCustodian(String custodian)
	{
		this.custodian = custodian;
	}

	@Column(name="CUSTODIAN_MOBLIE")
	public String getCustodianMoblie()
	{
		return this.custodianMoblie;
	}

	public void setCustodianMoblie(String custodianMoblie)
	{
		this.custodianMoblie = custodianMoblie;
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

}
