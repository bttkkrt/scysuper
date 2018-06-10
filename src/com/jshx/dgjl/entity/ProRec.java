package com.jshx.dgjl.entity;

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
@Table(name="PRO_REC")
public class ProRec extends BaseModel
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
	 * 调岗时间
	 */
	private Date jshxTime;

	/**
	 * 调岗事由
	 */
	private String causing;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 姓名
	 */
	private String jshxName;

	/**
	 * 体检不合格记录关联id
	 */
	private String unqualifiedId;
	/**
	 * 身份证
	 */
	
	private String identification;
	
	public ProRec(){
	}
	
	public ProRec(String id, String areaName,String companyName, Date jshxTime,String jshxName,String identification){
this.id = id;


this.areaName = areaName;
this.companyName = companyName;

this.jshxTime = jshxTime;
this.jshxName = jshxName;
this.identification = identification;
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

	@Column(name="JSHX_TIME")
	public Date getJshxTime()
	{
		return this.jshxTime;
	}

	public void setJshxTime(Date jshxTime)
	{
		this.jshxTime = jshxTime;
	}

	@Column(name="CAUSING")
	public String getCausing()
	{
		return this.causing;
	}

	public void setCausing(String causing)
	{
		this.causing = causing;
	}

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
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
	@Column(name="JSHX_NAME")
	public String getJshxName() {
		return jshxName;
	}

	public void setJshxName(String jshxName) {
		this.jshxName = jshxName;
	}
	@Column(name="UNQUALIFIED_ID")
	public String getUnqualifiedId() {
		return unqualifiedId;
	}

	public void setUnqualifiedId(String unqualifiedId) {
		this.unqualifiedId = unqualifiedId;
	}
	@Column(name="IDENTIFICATION")
	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

}
