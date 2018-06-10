package com.jshx.diggingsinfo.entity;

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
@Table(name="DIGGINGSINFO")
public class Diggingsinfo extends BaseModel
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
	 * 所属县区
	 */
	private String county;

	/**
	 * 主矿种
	 */
	private String mainoreType;

	/**
	 * 开采方式
	 */
	private String exploitType;

	/**
	 * 生产能力
	 */
	private String produceAbility;

	/**
	 * 投产日期
	 */
	private Date produceStartdate;

	/**
	 * 设计服务年限
	 */
	private String designServeAgelimit;

	/**
	 * 验收文号
	 */
	private String checkNo;

	/**
	 * 投产审查单位
	 */
	private String checkUnit;

	/**
	 * 持证特业人数
	 */
	private String certificateSum;

	/**
	 * 注安工程师人数
	 */
	private String engineerSum;

	/**
	 * 采矿许可证有效期开始日期
	 */
	private Date exploitCertificateStartdate;

	/**
	 * 采矿许可证有效期结束日期
	 */
	private Date exploitCertificateEnddate;

	/**
	 * 采矿许可证号
	 */
	private String exploitCertificateNo;

	/**
	 * 采矿权登记机关
	 */
	private String exploitCertificateUnit;

	/**
	 * 安全生产许可证有效期开始日期
	 */
	private Date safeCertificateStartdate;

	/**
	 * 安全生产许可证有效期结束日期
	 */
	private Date safeCertificateEnddate;

	/**
	 * 安全生产许可证号
	 */
	private String safeCertificateNo;
	
	
	/**
	 * 审核状态 0:乡镇审核 , 1:县级审核 , 2:市级审核 , 3:已办结 ,4:不合格
	 */
	private String state; 
	
	/**
	 * 审核备注
	 */
	private String shbs;
	/**
	 * 审核记录
	 */
	private String shenhe;
	
	
	/**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 直属等级
	 */
	private String zsqytype;
	
	private String dutyFlag;


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

	
	@Column(name="COUNTY")
	public String getCounty()
	{
		return this.county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	@Column(name="MAINORE_TYPE")
	public String getMainoreType()
	{
		return this.mainoreType;
	}

	public void setMainoreType(String mainoreType)
	{
		this.mainoreType = mainoreType;
	}

	@Column(name="EXPLOIT_TYPE")
	public String getExploitType()
	{
		return this.exploitType;
	}

	public void setExploitType(String exploitType)
	{
		this.exploitType = exploitType;
	}

	@Column(name="PRODUCE_ABILITY")
	public String getProduceAbility()
	{
		return this.produceAbility;
	}

	public void setProduceAbility(String produceAbility)
	{
		this.produceAbility = produceAbility;
	}

	@Column(name="PRODUCE_STARTDATE")
	public Date getProduceStartdate()
	{
		return this.produceStartdate;
	}

	public void setProduceStartdate(Date produceStartdate)
	{
		this.produceStartdate = produceStartdate;
	}

	@Column(name="DESIGN_SERVE_AGELIMIT")
	public String getDesignServeAgelimit()
	{
		return this.designServeAgelimit;
	}

	public void setDesignServeAgelimit(String designServeAgelimit)
	{
		this.designServeAgelimit = designServeAgelimit;
	}

	@Column(name="CHECK_NO")
	public String getCheckNo()
	{
		return this.checkNo;
	}

	public void setCheckNo(String checkNo)
	{
		this.checkNo = checkNo;
	}

	@Column(name="CHECK_UNIT")
	public String getCheckUnit()
	{
		return this.checkUnit;
	}

	public void setCheckUnit(String checkUnit)
	{
		this.checkUnit = checkUnit;
	}

	@Column(name="CERTIFICATE_SUM")
	public String getCertificateSum()
	{
		return this.certificateSum;
	}

	public void setCertificateSum(String certificateSum)
	{
		this.certificateSum = certificateSum;
	}

	@Column(name="ENGINEER_SUM")
	public String getEngineerSum()
	{
		return this.engineerSum;
	}

	public void setEngineerSum(String engineerSum)
	{
		this.engineerSum = engineerSum;
	}

	@Column(name="EXPLOIT_CERTIFICATE_STARTDATE")
	public Date getExploitCertificateStartdate()
	{
		return this.exploitCertificateStartdate;
	}

	public void setExploitCertificateStartdate(Date exploitCertificateStartdate)
	{
		this.exploitCertificateStartdate = exploitCertificateStartdate;
	}

	@Column(name="EXPLOIT_CERTIFICATE_ENDDATE")
	public Date getExploitCertificateEnddate()
	{
		return this.exploitCertificateEnddate;
	}

	public void setExploitCertificateEnddate(Date exploitCertificateEnddate)
	{
		this.exploitCertificateEnddate = exploitCertificateEnddate;
	}

	@Column(name="EXPLOIT_CERTIFICATE_NO")
	public String getExploitCertificateNo()
	{
		return this.exploitCertificateNo;
	}

	public void setExploitCertificateNo(String exploitCertificateNo)
	{
		this.exploitCertificateNo = exploitCertificateNo;
	}

	@Column(name="EXPLOIT_CERTIFICATE_UNIT")
	public String getExploitCertificateUnit()
	{
		return this.exploitCertificateUnit;
	}

	public void setExploitCertificateUnit(String exploitCertificateUnit)
	{
		this.exploitCertificateUnit = exploitCertificateUnit;
	}

	@Column(name="SAFE_CERTIFICATE_STARTDATE")
	public Date getSafeCertificateStartdate()
	{
		return this.safeCertificateStartdate;
	}

	public void setSafeCertificateStartdate(Date safeCertificateStartdate)
	{
		this.safeCertificateStartdate = safeCertificateStartdate;
	}

	@Column(name="SAFE_CERTIFICATE_ENDDATE")
	public Date getSafeCertificateEnddate()
	{
		return this.safeCertificateEnddate;
	}

	public void setSafeCertificateEnddate(Date safeCertificateEnddate)
	{
		this.safeCertificateEnddate = safeCertificateEnddate;
	}

	@Column(name="SAFE_CERTIFICATE_NO")
	public String getSafeCertificateNo()
	{
		return this.safeCertificateNo;
	}

	public void setSafeCertificateNo(String safeCertificateNo)
	{
		this.safeCertificateNo = safeCertificateNo;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="SHBZ")
	public String getShbs() {
		return shbs;
	}

	public void setShbs(String shbs) {
		this.shbs = shbs;
	}
	
	@Column(name="SHENHE")
	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}


	@Column(name="IFZSQY")
	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}
	@Column(name="ZSQYTYPE")
	public String getZsqytype() {
		return zsqytype;
	}

	public void setZsqytype(String zsqytype) {
		this.zsqytype = zsqytype;
	}
	@Column(name="DUTY_FLAG")
	public String getDutyFlag() {
		return dutyFlag;
	}

	public void setDutyFlag(String dutyFlag) {
		this.dutyFlag = dutyFlag;
	}
	
	

}
