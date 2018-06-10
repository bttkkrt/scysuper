package com.jshx.jccbgl.entity;

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
@Table(name="DET_OVER_STA_MAN")
public class DetOverStaMan extends BaseModel
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
	 * 检测类别
	 */
	private String detectionCategory;

	/**
	 * 检测岗位
	 */
	private String testPosition;

	/**
	 * 检测项目
	 */
	private String testItems;

	/**
	 * 检测结果
	 */
	private String testResult;

	/**
	 * 标准值
	 */
	private String standardValue;

	/**
	 * 检测机构
	 */
	private String detectionMechanism;

	/**
	 * 备注
	 */
	private String remark;

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
	 * 样品编码
	 */
	private String sampleEncoding;

	
	public DetOverStaMan(){
	}
	
	public DetOverStaMan(String id, String areaName, String companyName,String detectionCategory,String detectionMechanism){
this.id = id;

this.detectionCategory = detectionCategory;

this.areaName = areaName;
this.companyName = companyName;

this.detectionMechanism = detectionMechanism;


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

	
	@Column(name="DETECTION_CATEGORY")
	public String getDetectionCategory()
	{
		return this.detectionCategory;
	}

	public void setDetectionCategory(String detectionCategory)
	{
		this.detectionCategory = detectionCategory;
	}

	@Column(name="TEST_POSITION")
	public String getTestPosition()
	{
		return this.testPosition;
	}

	public void setTestPosition(String testPosition)
	{
		this.testPosition = testPosition;
	}

	@Column(name="TEST_ITEMS")
	public String getTestItems()
	{
		return this.testItems;
	}

	public void setTestItems(String testItems)
	{
		this.testItems = testItems;
	}

	@Column(name="TEST_RESULT")
	public String getTestResult()
	{
		return this.testResult;
	}

	public void setTestResult(String testResult)
	{
		this.testResult = testResult;
	}

	@Column(name="STANDARD_VALUE")
	public String getStandardValue()
	{
		return this.standardValue;
	}

	public void setStandardValue(String standardValue)
	{
		this.standardValue = standardValue;
	}

	@Column(name="DETECTION_MECHANISM")
	public String getDetectionMechanism()
	{
		return this.detectionMechanism;
	}

	public void setDetectionMechanism(String detectionMechanism)
	{
		this.detectionMechanism = detectionMechanism;
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

	@Column(name="SAMPLE_ENCODING")
	public String getSampleEncoding()
	{
		return this.sampleEncoding;
	}

	public void setSampleEncoding(String sampleEncoding)
	{
		this.sampleEncoding = sampleEncoding;
	}

}
