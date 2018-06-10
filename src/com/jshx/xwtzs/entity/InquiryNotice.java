package com.jshx.xwtzs.entity;

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
@Table(name="INQUIRY_NOTICE")
public class InquiryNotice extends BaseModel
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
	 * 联系电话
	 */
	private String tele;

	/**
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 询问时间
	 */
	private Date inquiryTime;

	/**
	 * 询问地点
	 */
	private String inquiryAddress;

	/**
	 * 证件材料
	 */
	private String docMaterial;

	/**
	 * 联系人
	 */
	private String contact;
	
	/**
	 * 身份证
	 */
	private String sfz;
	
	/**
	 *营业执照
	 */
	private String yyzz;
	
	/**
	 * 法定代表人身份证明或者委托书
	 */
	private String fddbzm;
	/**
	 * 其他
	 */
	private String qt;
	
	private String askPerson;
	
	public InquiryNotice(){
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

	
	@Column(name="TELE")
	public String getTele()
	{
		return this.tele;
	}

	public void setTele(String tele)
	{
		this.tele = tele;
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

	@Column(name="INQUIRY_TIME")
	public Date getInquiryTime()
	{
		return this.inquiryTime;
	}

	public void setInquiryTime(Date inquiryTime)
	{
		this.inquiryTime = inquiryTime;
	}

	@Column(name="INQUIRY_ADDRESS")
	public String getInquiryAddress()
	{
		return this.inquiryAddress;
	}

	public void setInquiryAddress(String inquiryAddress)
	{
		this.inquiryAddress = inquiryAddress;
	}

	@Column(name="DOC_MATERIAL")
	public String getDocMaterial()
	{
		return this.docMaterial;
	}

	public void setDocMaterial(String docMaterial)
	{
		this.docMaterial = docMaterial;
	}

	@Column(name="CONTACT")
	public String getContact()
	{
		return this.contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}
	@Column(name="SFZ")
	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	@Column(name="YYZZ")
	public String getYyzz() {
		return yyzz;
	}

	public void setYyzz(String yyzz) {
		this.yyzz = yyzz;
	}
	@Column(name="FDDBZM")
	public String getFddbzm() {
		return fddbzm;
	}

	public void setFddbzm(String fddbzm) {
		this.fddbzm = fddbzm;
	}
	@Column(name="QT")
	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	@Column(name="ASK_PERSON")
	public String getAskPerson() {
		return askPerson;
	}


	public void setAskPerson(String askPerson) {
		this.askPerson = askPerson;
	}

}
