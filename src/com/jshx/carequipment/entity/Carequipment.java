package com.jshx.carequipment.entity;

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
@Table(name="CAREQUIPMENT")
public class Carequipment extends BaseModel
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
	 * COMPANYNAME
	 */
	private String companyname;

	/**
	 * DETAILNAME
	 */
	private String detailname;

	/**
	 * GUID
	 */
	private String guid;

	/**
	 * PUID
	 */
	private String puid;

	/**
	 * STREAMID
	 */
	private String streamid;


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

	
	@Column(name="COMPANYNAME")
	public String getCompanyname()
	{
		return this.companyname;
	}

	public void setCompanyname(String companyname)
	{
		this.companyname = companyname;
	}

	@Column(name="DETAILNAME")
	public String getDetailname()
	{
		return this.detailname;
	}

	public void setDetailname(String detailname)
	{
		this.detailname = detailname;
	}

	@Column(name="GUID")
	public String getGuid()
	{
		return this.guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}

	@Column(name="PUID")
	public String getPuid()
	{
		return this.puid;
	}

	public void setPuid(String puid)
	{
		this.puid = puid;
	}

	@Column(name="STREAMID")
	public String getStreamid()
	{
		return this.streamid;
	}

	public void setStreamid(String streamid)
	{
		this.streamid = streamid;
	}

}
