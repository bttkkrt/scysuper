package com.jshx.xfzzjggl.entity;

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
@Table(name="FIR_INS")
public class FirIns extends BaseModel
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
	 * 机构编号
	 */
	private String agencyNum;

	/**
	 * 机构名称
	 */
	private String agencyName;

	/**
	 * 机构地址
	 */
	private String agencyAddress;

	/**
	 * 联系方式
	 */
	private String mobile;

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
	 * 2015-12-03
	 */
	public String mapkey;
	
	@Column
	public String getMapkey() {
		return mapkey;
	}

	public void setMapkey(String mapkey) {
		this.mapkey = mapkey;
	}
	
	public FirIns(){
	}
	
	public FirIns(String id, String agencyNum, String agencyName, String mobile,String createUserID){
this.id = id;

this.agencyNum = agencyNum;

this.agencyName = agencyName;

this.mobile = mobile;
this.createUserID=createUserID;
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

	
	@Column(name="AGENCY_NUM")
	public String getAgencyNum()
	{
		return this.agencyNum;
	}

	public void setAgencyNum(String agencyNum)
	{
		this.agencyNum = agencyNum;
	}

	@Column(name="AGENCY_NAME")
	public String getAgencyName()
	{
		return this.agencyName;
	}

	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	@Column(name="AGENCY_ADDRESS")
	public String getAgencyAddress()
	{
		return this.agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress)
	{
		this.agencyAddress = agencyAddress;
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
