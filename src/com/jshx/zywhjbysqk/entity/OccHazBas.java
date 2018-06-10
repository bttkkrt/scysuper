package com.jshx.zywhjbysqk.entity;

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
@Table(name="OCC_HAZ_BAS")
public class OccHazBas extends BaseModel
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
	 * 接触职业病危害总人数
	 */
	private String totalNumber;

	/**
	 * 合计
	 */
	private String total;

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
	 * 粉尘类
	 */
	private String ifDust;

	/**
	 * 粉尘类接触人数
	 */
	private String dustContactNumber;

	/**
	 * 化学物质类
	 */
	private String ifChemical;

	/**
	 * 化学物质类接触人数
	 */
	private String chemicalContactNumber;

	/**
	 * 物理因素类
	 */
	private String ifPhysical;

	/**
	 * 物理因素类接触人数
	 */
	private String physicalContactNumber;

	/**
	 * 放射性物质类
	 */
	private String ifRadioactivity;

	/**
	 * 放射性物质类接触人数
	 */
	private String radioactivityContactNumber;

	/**
	 * 其他
	 */
	private String ifOther;

	/**
	 * 其他接触人数
	 */
	private String otherContactNumber;

	
	public OccHazBas(){
	}
	
	public OccHazBas(String id, String total, String areaId, String companyName,String ifDust,String ifChemical,String ifPhysical,String ifRadioactivity,String ifOther){
this.id = id;

this.total = total;

this.areaId = areaId;

this.companyName = companyName;

this.ifDust=ifDust;
this.ifChemical=ifChemical;
this.ifPhysical=ifPhysical;
this.ifRadioactivity=ifRadioactivity;
this.ifOther=ifOther;
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

	
	@Column(name="TOTAL_NUMBER")
	public String getTotalNumber()
	{
		return this.totalNumber;
	}

	public void setTotalNumber(String totalNumber)
	{
		this.totalNumber = totalNumber;
	}

	@Column(name="TOTAL")
	public String getTotal()
	{
		return this.total;
	}

	public void setTotal(String total)
	{
		this.total = total;
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

	@Column(name="IF_DUST")
	public String getIfDust()
	{
		return this.ifDust;
	}

	public void setIfDust(String ifDust)
	{
		this.ifDust = ifDust;
	}

	@Column(name="DUST_CONTACT_NUMBER")
	public String getDustContactNumber()
	{
		return this.dustContactNumber;
	}

	public void setDustContactNumber(String dustContactNumber)
	{
		this.dustContactNumber = dustContactNumber;
	}

	@Column(name="IF_CHEMICAL")
	public String getIfChemical()
	{
		return this.ifChemical;
	}

	public void setIfChemical(String ifChemical)
	{
		this.ifChemical = ifChemical;
	}

	@Column(name="CHEMICAL_CONTACT_NUMBER")
	public String getChemicalContactNumber()
	{
		return this.chemicalContactNumber;
	}

	public void setChemicalContactNumber(String chemicalContactNumber)
	{
		this.chemicalContactNumber = chemicalContactNumber;
	}

	@Column(name="IF_PHYSICAL")
	public String getIfPhysical()
	{
		return this.ifPhysical;
	}

	public void setIfPhysical(String ifPhysical)
	{
		this.ifPhysical = ifPhysical;
	}

	@Column(name="PHYSICAL_CONTACT_NUMBER")
	public String getPhysicalContactNumber()
	{
		return this.physicalContactNumber;
	}

	public void setPhysicalContactNumber(String physicalContactNumber)
	{
		this.physicalContactNumber = physicalContactNumber;
	}

	@Column(name="IF_RADIOACTIVITY")
	public String getIfRadioactivity()
	{
		return this.ifRadioactivity;
	}

	public void setIfRadioactivity(String ifRadioactivity)
	{
		this.ifRadioactivity = ifRadioactivity;
	}

	@Column(name="RADIOACTIVITY_CONTACT_NUMBER")
	public String getRadioactivityContactNumber()
	{
		return this.radioactivityContactNumber;
	}

	public void setRadioactivityContactNumber(String radioactivityContactNumber)
	{
		this.radioactivityContactNumber = radioactivityContactNumber;
	}

	@Column(name="IF_OTHER")
	public String getIfOther()
	{
		return this.ifOther;
	}

	public void setIfOther(String ifOther)
	{
		this.ifOther = ifOther;
	}

	@Column(name="OTHER_CONTACT_NUMBER")
	public String getOtherContactNumber()
	{
		return this.otherContactNumber;
	}

	public void setOtherContactNumber(String otherContactNumber)
	{
		this.otherContactNumber = otherContactNumber;
	}

}
