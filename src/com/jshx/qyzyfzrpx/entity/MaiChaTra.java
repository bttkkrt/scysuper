package com.jshx.qyzyfzrpx.entity;

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
@Table(name="MAI_CHA_TRA")
public class MaiChaTra extends BaseModel
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
	 * 主要负责人ID
	 */
	private String trainingPersonId;

	/**
	 * 主要负责人姓名
	 */
	private String trainingPersonName;

	/**
	 * 培训开始时间
	 */
	private Date trainingTime;
	/**
	 * 培训结束时间
	 **/
	private Date trainingTimeEnd;

	/**
	 * 培训地点
	 */
	private String trainingAddress;

	/**
	 * 培训学时
	 */
	private String trainingTeacheTime;

	/**
	 * 授课人
	 */
	private String trainingTeacher;

	/**
	 * 培训内容
	 */
	private String trainingContent;

	/**
	 * 证书号码
	 */
	private String trainingCardnum;

	/**
	 * 证书发证日期
	 */
	private Date trainingCardPickDate;

	/**
	 * 有效期日期
	 */
	private Date trainingCardValidity;

	/**
	 * 关联附件id
	 */
	private String linkId;
	
	/**
	 *发证单位
	 */
	
	private String fzdw;
	

	@Column
	public String getFzdw() {
		return fzdw;
	}

	public void setFzdw(String fzdw) {
		this.fzdw = fzdw;
	}
	
	public MaiChaTra(){
	}
	
	public MaiChaTra(String id, String areaName, String companyName, String trainingPersonName,String trainingCardnum, Date trainingTime,Date trainingTimeEnd){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.trainingPersonName = trainingPersonName;
this.trainingCardnum = trainingCardnum;
this.trainingTime = trainingTime;
this.trainingTimeEnd = trainingTimeEnd;
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

	@Column(name="TRAINING_PERSON_ID")
	public String getTrainingPersonId()
	{
		return this.trainingPersonId;
	}

	public void setTrainingPersonId(String trainingPersonId)
	{
		this.trainingPersonId = trainingPersonId;
	}

	@Column(name="TRAINING_PERSON_NAME")
	public String getTrainingPersonName()
	{
		return this.trainingPersonName;
	}

	public void setTrainingPersonName(String trainingPersonName)
	{
		this.trainingPersonName = trainingPersonName;
	}

	@Column(name="TRAINING_TIME")
	public Date getTrainingTime()
	{
		return this.trainingTime;
	}

	public void setTrainingTime(Date trainingTime)
	{
		this.trainingTime = trainingTime;
	}

	@Column(name="TRAINING_ADDRESS")
	public String getTrainingAddress()
	{
		return this.trainingAddress;
	}

	public void setTrainingAddress(String trainingAddress)
	{
		this.trainingAddress = trainingAddress;
	}

	@Column(name="TRAINING_TEACHE_TIME")
	public String getTrainingTeacheTime()
	{
		return this.trainingTeacheTime;
	}

	public void setTrainingTeacheTime(String trainingTeacheTime)
	{
		this.trainingTeacheTime = trainingTeacheTime;
	}

	@Column(name="TRAINING_TEACHER")
	public String getTrainingTeacher()
	{
		return this.trainingTeacher;
	}

	public void setTrainingTeacher(String trainingTeacher)
	{
		this.trainingTeacher = trainingTeacher;
	}

	@Column(name="TRAINING_CONTENT")
	public String getTrainingContent()
	{
		return this.trainingContent;
	}

	public void setTrainingContent(String trainingContent)
	{
		this.trainingContent = trainingContent;
	}

	@Column(name="TRAINING_CARDNUM")
	public String getTrainingCardnum()
	{
		return this.trainingCardnum;
	}

	public void setTrainingCardnum(String trainingCardnum)
	{
		this.trainingCardnum = trainingCardnum;
	}

	@Column(name="TRAINING_CARD_PICK_DATE")
	public Date getTrainingCardPickDate()
	{
		return this.trainingCardPickDate;
	}

	public void setTrainingCardPickDate(Date trainingCardPickDate)
	{
		this.trainingCardPickDate = trainingCardPickDate;
	}

	@Column(name="TRAINING_CARD_VALIDITY")
	public Date getTrainingCardValidity()
	{
		return this.trainingCardValidity;
	}

	public void setTrainingCardValidity(Date trainingCardValidity)
	{
		this.trainingCardValidity = trainingCardValidity;
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

	@Column(name="TRAINING_TIME_END")
	public Date getTrainingTimeEnd() {
		return trainingTimeEnd;
	}
	public void setTrainingTimeEnd(Date trainingTimeEnd) {
		this.trainingTimeEnd = trainingTimeEnd;
	}

}
