package com.jshx.zdwxysbpgfj.entity;

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
@Table(name="COM_DAN_IDE")
public class ComDanIde extends BaseModel
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
	 * 重点危险源名称
	 */
	private String dangerName;

	/**
	 * 重点危险源类别
	 */
	private String dangerType;

	/**
	 * 重点危险源级别
	 */
	private String dangerLevel;

	/**
	 * 重点危险源地址
	 */
	private String dangerAddress;

	/**
	 * 安全负责人
	 */
	private String safePerson;

	/**
	 * 联系方式
	 */
	private String tele;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 审核人
	 */
	private String auditPerson;

	/**
	 * 审核结果
	 */
	private String auditResult;
	
	/**
	 * 审核状态
	 */
	private String auditState;
	
	/**
	 * 新增地图key  用于关联地图空间表数据
	 * lj
	 * 2015-11-17
	 */
	public String mapkey;
	
	/**
	 * 巡查频率
	 */
	public String checkFrequency;
	
	/**
	 * 巡查人员ID
	 */
	private String checkPeopleId;

	/**
	 * 巡查人员姓名
	 */
	private String checkPeopleName;
	@Column
	public String getMapkey() {
		return mapkey;
	}

	public void setMapkey(String mapkey) {
		this.mapkey = mapkey;
	}
	
	public ComDanIde(){
	}
	
	public ComDanIde(String id, String areaName, String companyName, String dangerName, String dangerType, String dangerLevel, String safePerson, String auditResult,String auditState){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.dangerName = dangerName;

this.dangerType = dangerType;

this.dangerLevel = dangerLevel;

this.safePerson = safePerson;

this.auditResult = auditResult;

this.auditState=auditState;
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

	@Column(name="DANGER_NAME")
	public String getDangerName()
	{
		return this.dangerName;
	}

	public void setDangerName(String dangerName)
	{
		this.dangerName = dangerName;
	}

	@Column(name="DANGER_TYPE")
	public String getDangerType()
	{
		return this.dangerType;
	}

	public void setDangerType(String dangerType)
	{
		this.dangerType = dangerType;
	}

	@Column(name="DANGER_LEVEL")
	public String getDangerLevel()
	{
		return this.dangerLevel;
	}

	public void setDangerLevel(String dangerLevel)
	{
		this.dangerLevel = dangerLevel;
	}

	@Column(name="DANGER_ADDRESS")
	public String getDangerAddress()
	{
		return this.dangerAddress;
	}

	public void setDangerAddress(String dangerAddress)
	{
		this.dangerAddress = dangerAddress;
	}

	@Column(name="SAFE_PERSON")
	public String getSafePerson()
	{
		return this.safePerson;
	}

	public void setSafePerson(String safePerson)
	{
		this.safePerson = safePerson;
	}

	@Column(name="TELE")
	public String getTele()
	{
		return this.tele;
	}

	public void setTele(String tele)
	{
		this.tele = tele;
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
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name="AUDIT_PERSON")
	public String getAuditPerson()
	{
		return this.auditPerson;
	}

	public void setAuditPerson(String auditPerson)
	{
		this.auditPerson = auditPerson;
	}

	@Column(name="AUDIT_RESULT")
	public String getAuditResult()
	{
		return this.auditResult;
	}

	public void setAuditResult(String auditResult)
	{
		this.auditResult = auditResult;
	}
	@Column(name="AUDIT_STATE")
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}
	@Column(name="CHECK_FREQUENCY")
	public String getCheckFrequency()
	{
		return this.checkFrequency;
	}

	public void setCheckFrequency(String checkFrequency)
	{
		this.checkFrequency = checkFrequency;
	}
	
	@Column(name="CHECK_PEOPLE_ID")
	public String getCheckPeopleId()
	{
		return this.checkPeopleId;
	}

	public void setCheckPeopleId(String checkPeopleId)
	{
		this.checkPeopleId = checkPeopleId;
	}

	@Column(name="CHECK_PEOPLE_NAME")
	public String getCheckPeopleName()
	{
		return this.checkPeopleName;
	}

	public void setCheckPeopleName(String checkPeopleName)
	{
		this.checkPeopleName = checkPeopleName;
	}
    
}
