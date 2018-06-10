package com.jshx.aqscfytqqk.entity;

import java.sql.Blob;
import java.text.DecimalFormat;
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
@Table(name="SEC_PRO_FEE_EXT")
public class SecProFeeExt extends BaseModel
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
	 * 所在区域
	 */
	private String areaId;

	/**
	 * 所在区域
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
	 * 提取费用
	 */
	private String feeExtractFee;

	/**
	 * 提取时间
	 */
	private Date feeExtractTime;

	/**
	 * 备注
	 */
	private String feeExtractRemark;
	
	/**
	 * 使用月份
	 */
	private String feeAccountMonth;
	
	/**
	 * 发票号
	 */
	private String fpNum;

	
	public SecProFeeExt(){
	}
	
	public SecProFeeExt(String id, String areaName, String companyName, String feeExtractFee, Date feeExtractTime,String feeExtractRemark){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.feeExtractFee = feeExtractFee;

this.feeExtractTime = feeExtractTime;

this.feeExtractRemark = feeExtractRemark;
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

	@Column(name="FEE_EXTRACT_FEE")
	public String getFeeExtractFee()
	{
		String strFloat = "";
		if(this.feeExtractFee != null && !"".equals(this.feeExtractFee))
		{
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			strFloat = myFormat.format(Double.valueOf(this.feeExtractFee));
		}
		return strFloat;
	}

	public void setFeeExtractFee(String feeExtractFee)
	{
		this.feeExtractFee = feeExtractFee;
	}

	@Column(name="FEE_EXTRACT_TIME")
	public Date getFeeExtractTime()
	{
		return this.feeExtractTime;
	}

	public void setFeeExtractTime(Date feeExtractTime)
	{
		this.feeExtractTime = feeExtractTime;
	}

	@Column(name="FEE_EXTRACT_REMARK")
	public String getFeeExtractRemark()
	{
		return this.feeExtractRemark;
	}

	public void setFeeExtractRemark(String feeExtractRemark)
	{
		this.feeExtractRemark = feeExtractRemark;
	}
	
	@Column(name="FEE_ACCOUNT_MONTH")
	public String getFeeAccountMonth()
	{
		return this.feeAccountMonth;
	}

	public void setFeeAccountMonth(String feeAccountMonth)
	{
		this.feeAccountMonth = feeAccountMonth;
	}
	@Column(name="FP_NUM")
	public String getFpNum() {
		return fpNum;
	}

	public void setFpNum(String fpNum) {
		this.fpNum = fpNum;
	}

}
