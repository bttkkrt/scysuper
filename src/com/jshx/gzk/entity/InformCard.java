package com.jshx.gzk.entity;

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
@Table(name="INFORM_CARD")
public class InformCard extends BaseModel
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
	 * 告知卡编号
	 */
	private String informNo;

	/**
	 * 告知卡名称
	 */
	private String informName;

	/**
	 * 告知卡类别
	 */
	private String informType;

	/**
	 * 告知卡内容
	 */
	private String informContent;

	/**
	 * 告知卡地址
	 */
	private String informAddress;

	/**
	 * 经度
	 */
	private String informLongitude;

	/**
	 * 纬度
	 */
	private String informLatitude;
	
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
	
	
	public InformCard(){
	}
	
	public InformCard(String id, String areaName, String companyName, String informNo, String informName, String informType,String informAddress){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.informNo = informNo;

this.informName = informName;

this.informType = informType;

this.informAddress = informAddress;
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

	@Column(name="INFORM_NO")
	public String getInformNo()
	{
		return this.informNo;
	}

	public void setInformNo(String informNo)
	{
		this.informNo = informNo;
	}

	@Column(name="INFORM_NAME")
	public String getInformName()
	{
		return this.informName;
	}

	public void setInformName(String informName)
	{
		this.informName = informName;
	}

	@Column(name="INFORM_TYPE")
	public String getInformType()
	{
		return this.informType;
	}

	public void setInformType(String informType)
	{
		this.informType = informType;
	}

	@Column(name="INFORM_CONTENT")
	public String getInformContent()
	{
		return this.informContent;
	}

	public void setInformContent(String informContent)
	{
		this.informContent = informContent;
	}

	@Column(name="INFORM_ADDRESS")
	public String getInformAddress()
	{
		return this.informAddress;
	}

	public void setInformAddress(String informAddress)
	{
		this.informAddress = informAddress;
	}

	@Column(name="INFORM_LONGITUDE")
	public String getInformLongitude()
	{
		return this.informLongitude;
	}

	public void setInformLongitude(String informLongitude)
	{
		this.informLongitude = informLongitude;
	}

	@Column(name="INFORM_LATITUDE")
	public String getInformLatitude()
	{
		return this.informLatitude;
	}

	public void setInformLatitude(String informLatitude)
	{
		this.informLatitude = informLatitude;
	}

}
