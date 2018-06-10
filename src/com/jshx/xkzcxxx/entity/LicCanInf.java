package com.jshx.xkzcxxx.entity;

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
@Table(name="LIC_CAN_INF")
public class LicCanInf extends BaseModel
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
	 * 许可证名称
	 */
	private String licenseName;

	/**
	 * 许可证编号
	 */
	private String licenseNumber;

	/**
	 * 注销文号
	 */
	private String cancellationNumber;

	/**
	 * 注销原因
	 */
	private String cancelReason;

	/**
	 * 批准机关名称
	 */
	private String approvalAuthority;

	/**
	 * 批准日期
	 */
	private Date approvalDate;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 审核状态
	 */
	private String auditState;

	
	public LicCanInf(){
	}
	
	public LicCanInf(String id, String areaName, String companyName, String licenseName, String licenseNumber,String auditState,String createUserID){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.licenseName = licenseName;

this.licenseNumber = licenseNumber;

this.auditState = auditState;
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

	@Column(name="LICENSE_NAME")
	public String getLicenseName()
	{
		return this.licenseName;
	}

	public void setLicenseName(String licenseName)
	{
		this.licenseName = licenseName;
	}

	@Column(name="LICENSE_NUMBER")
	public String getLicenseNumber()
	{
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber)
	{
		this.licenseNumber = licenseNumber;
	}

	@Column(name="CANCELLATION_NUMBER")
	public String getCancellationNumber()
	{
		return this.cancellationNumber;
	}

	public void setCancellationNumber(String cancellationNumber)
	{
		this.cancellationNumber = cancellationNumber;
	}

	@Column(name="CANCEL_REASON")
	public String getCancelReason()
	{
		return this.cancelReason;
	}

	public void setCancelReason(String cancelReason)
	{
		this.cancelReason = cancelReason;
	}

	@Column(name="APPROVAL_AUTHORITY")
	public String getApprovalAuthority()
	{
		return this.approvalAuthority;
	}

	public void setApprovalAuthority(String approvalAuthority)
	{
		this.approvalAuthority = approvalAuthority;
	}

	@Column(name="APPROVAL_DATE")
	public Date getApprovalDate()
	{
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate)
	{
		this.approvalDate = approvalDate;
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

	@Column(name="AUDIT_STATE")
	public String getAuditState()
	{
		return this.auditState;
	}

	public void setAuditState(String auditState)
	{
		this.auditState = auditState;
	}

}
