package com.jshx.zybwhys.entity;

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
@Table(name="OCC_DIS_IND")
public class OccDisInd extends BaseModel
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
	 * 关联基本因素分布id
	 */
	private String proId;

	/**
	 * 职业病危害因素名称
	 */
	private String occupationalDiseaseName;

	/**
	 * 现场浓度
	 */
	private String fieldConcentration;

	/**
	 * 接触人数
	 */
	private String contactNumber;

	
	public OccDisInd(){
	}
	
	public OccDisInd(String id, String occupationalDiseaseName, String fieldConcentration, String contactNumber){
this.id = id;

this.occupationalDiseaseName = occupationalDiseaseName;

this.fieldConcentration = fieldConcentration;

this.contactNumber = contactNumber;
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

	
	@Column(name="PRO_ID")
	public String getProId()
	{
		return this.proId;
	}

	public void setProId(String proId)
	{
		this.proId = proId;
	}

	@Column(name="OCCUPATIONAL_DISEASE_NAME")
	public String getOccupationalDiseaseName()
	{
		return this.occupationalDiseaseName;
	}

	public void setOccupationalDiseaseName(String occupationalDiseaseName)
	{
		this.occupationalDiseaseName = occupationalDiseaseName;
	}

	@Column(name="FIELD_CONCENTRATION")
	public String getFieldConcentration()
	{
		return this.fieldConcentration;
	}

	public void setFieldConcentration(String fieldConcentration)
	{
		this.fieldConcentration = fieldConcentration;
	}

	@Column(name="CONTACT_NUMBER")
	public String getContactNumber()
	{
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

}
