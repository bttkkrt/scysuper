package com.jshx.ggl.entity;

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
@Table(name="PUBLIC_BOARD")
public class PublicBoard extends BaseModel
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
	 * 公告栏类别
	 */
	private String publicType;

	/**
	 * 公告栏内容
	 */
	private String publicContent;

	/**
	 * 公告栏地址
	 */
	private String publicAddress;

	/**
	 * 经度
	 */
	private String publicLongitude;

	/**
	 * 纬度
	 */
	private String publicLatitude;

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
	 * 公告栏编号
	 */
	private String publicNo;

	/**
	 * 公告栏名称
	 */
	private String publicName;
	/**
	 * 新增地图key  用于关联地图空间表数据
	 * lj
	 * 2015-11-17
	 */
	public String mapkey;
	
	public PublicBoard(){
	}
	
	public PublicBoard(String id, String publicType, String areaName, String companyName, String publicNo, String publicName,String publicAddress){
this.id = id;

this.publicType = publicType;

this.areaName = areaName;

this.companyName = companyName;

this.publicNo = publicNo;

this.publicName = publicName;

this.publicAddress = publicAddress;
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

	
	@Column(name="PUBLIC_TYPE")
	public String getPublicType()
	{
		return this.publicType;
	}

	public void setPublicType(String publicType)
	{
		this.publicType = publicType;
	}

	@Column(name="PUBLIC_CONTENT")
	public String getPublicContent()
	{
		return this.publicContent;
	}

	public void setPublicContent(String publicContent)
	{
		this.publicContent = publicContent;
	}

	@Column(name="PUBLIC_ADDRESS")
	public String getPublicAddress()
	{
		return this.publicAddress;
	}

	public void setPublicAddress(String publicAddress)
	{
		this.publicAddress = publicAddress;
	}

	@Column(name="PUBLIC_LONGITUDE")
	public String getPublicLongitude()
	{
		return this.publicLongitude;
	}

	public void setPublicLongitude(String publicLongitude)
	{
		this.publicLongitude = publicLongitude;
	}

	@Column(name="PUBLIC_LATITUDE")
	public String getPublicLatitude()
	{
		return this.publicLatitude;
	}

	public void setPublicLatitude(String publicLatitude)
	{
		this.publicLatitude = publicLatitude;
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

	@Column(name="PUBLIC_NO")
	public String getPublicNo()
	{
		return this.publicNo;
	}

	public void setPublicNo(String publicNo)
	{
		this.publicNo = publicNo;
	}

	@Column(name="PUBLIC_NAME")
	public String getPublicName()
	{
		return this.publicName;
	}

	public void setPublicName(String publicName)
	{
		this.publicName = publicName;
	}
	@Column
	public String getMapkey() {
		return mapkey;
	}

	public void setMapkey(String mapkey) {
		this.mapkey = mapkey;
	}
	

}
