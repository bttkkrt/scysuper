package com.jshx.cyzjglb.entity;

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
@Table(name="SAMPLING_ASSOCIATE")
public class SamplingAssociate extends BaseModel
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
	 * 关联取证编号
	 */
	private String forensicId;

	/**
	 * 证据物品名称
	 */
	private String evidenceName;

	/**
	 * 规格及批号
	 */
	private String specificationLot;

	/**
	 * 数量
	 */
	private String samplingNum;

	
	public SamplingAssociate(){
	}
	
	public SamplingAssociate(String id, String forensicId, String evidenceName, String samplingNum){
this.id = id;

this.forensicId = forensicId;

this.evidenceName = evidenceName;

this.samplingNum = samplingNum;
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

	
	@Column(name="FORENSIC_ID")
	public String getForensicId()
	{
		return this.forensicId;
	}

	public void setForensicId(String forensicId)
	{
		this.forensicId = forensicId;
	}

	@Column(name="EVIDENCE_NAME")
	public String getEvidenceName()
	{
		return this.evidenceName;
	}

	public void setEvidenceName(String evidenceName)
	{
		this.evidenceName = evidenceName;
	}

	@Column(name="SPECIFICATION_LOT")
	public String getSpecificationLot()
	{
		return this.specificationLot;
	}

	public void setSpecificationLot(String specificationLot)
	{
		this.specificationLot = specificationLot;
	}

	@Column(name="SAMPLING_NUM")
	public String getSamplingNum()
	{
		return this.samplingNum;
	}

	public void setSamplingNum(String samplingNum)
	{
		this.samplingNum = samplingNum;
	}

}
