package com.jshx.xzcfjdsdw.entity;

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
@Table(name="PEN_DEC_COM")
public class PenDecCom extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 银行名称
	 */
	private String bankName;

	/**
	 * 银行账户
	 */
	private String bankAccount;

	/**
	 * 执法人员
	 */
	private String lawOfficer;
	
	/**
	 * 违法事实
	 */
	private String wfss;
	
	/**
	 * 规定
	 */
	private String provision;
	
	private String proName;

	/**
	 * 执法依据
	 */
	private String lawBasic;
	
	private String lawName;

	/**
	 * 行政处罚
	 */
	private String adminPenalties;
	
	/**
	 * 处罚种类
	 */
	private String punishedSpecies;
	
	/**
	 * 没收违法所得
	 */
	private String illegalIncome;

	/**
	 * 罚款金额
	 */
	private String fines;
	
	/**
	 * 执法人员
	 */
	private String lawOfficerName;

	
	public PenDecCom(){
	}
	
	public PenDecCom(String id, String relatedId, String bankName, String bankAccount, String lawOfficer){
this.id = id;

this.relatedId = relatedId;

this.bankName = bankName;

this.bankAccount = bankAccount;

this.lawOfficer = lawOfficer;
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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="BANK_NAME")
	public String getBankName()
	{
		return this.bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	@Column(name="BANK_ACCOUNT")
	public String getBankAccount()
	{
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount)
	{
		this.bankAccount = bankAccount;
	}

	@Column(name="LAW_OFFICER")
	public String getLawOfficer()
	{
		return this.lawOfficer;
	}

	public void setLawOfficer(String lawOfficer)
	{
		this.lawOfficer = lawOfficer;
	}
	
	@Column(name="PUNISHED_SPECIES")
	public String getPunishedSpecies()
	{
		return this.punishedSpecies;
	}

	public void setPunishedSpecies(String punishedSpecies)
	{
		this.punishedSpecies = punishedSpecies;
	}
	@Column(name="ILLEGAL_INCOME")
	public String getIllegalIncome()
	{
		return this.illegalIncome;
	}

	public void setIllegalIncome(String illegalIncome)
	{
		this.illegalIncome = illegalIncome;
	}

	@Column(name="FINES")
	public String getFines()
	{
		return this.fines;
	}

	public void setFines(String fines)
	{
		this.fines = fines;
	}
	@Column(name="WFSS")
	public String getWfss() {
		return wfss;
	}

	public void setWfss(String wfss) {
		this.wfss = wfss;
	}
	@Column(name="PROVISION")
	public String getProvision() {
		return provision;
	}

	public void setProvision(String provision) {
		this.provision = provision;
	}
	@Column(name="PRO_NAME")
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	@Column(name="LAW_BASIC")
	public String getLawBasic() {
		return lawBasic;
	}

	public void setLawBasic(String lawBasic) {
		this.lawBasic = lawBasic;
	}
	@Column(name="LAW_NAME")
	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}
	@Column(name="ADMIN_PENALTIES")
	public String getAdminPenalties() {
		return adminPenalties;
	}

	public void setAdminPenalties(String adminPenalties) {
		this.adminPenalties = adminPenalties;
	}
	@Column
	public String getLawOfficerName() {
		return lawOfficerName;
	}

	public void setLawOfficerName(String lawOfficerName) {
		this.lawOfficerName = lawOfficerName;
	}

}
