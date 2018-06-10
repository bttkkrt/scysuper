package com.jshx.qynbqypx.entity;

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
@Table(name="ALL_MEN_TRA")
public class AllMenTra extends BaseModel
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
	 * 车间ID
	 */
	private String trainingWorkshopId;

	/**
	 * 车间名称
	 */
	private String trainingWorkshopName;

	/**
	 * 岗位名称
	 */
	private String trainingPost;

	/**
	 * 培训开始时间
	 */
	private Date trainingTime;
	/**
	 * 培训结束时间
	 */
	private Date trainingTimeEnd;

	/**
	 * 培训人数
	 */
	private String trainingPersons;

	/**
	 * 培训计划
	 */
	private String trainingContent;

	/**
	 * 培训讲师
	 */
	private String trainingTeacher;

	/**
	 * 备注
	 */
	private String trainingRemark;

	/**
	 * 关联附件id
	 */
	private String linkId;
	
	/**
	 * 培训班名称
	 */
    private String trainingName;

	
	public AllMenTra(){
	}
	
	public AllMenTra(String id, String areaName, String companyName,  Date trainingTime,String trainingName,Date trainingTimeEnd){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.trainingName=trainingName;

this.trainingTime = trainingTime;

this.trainingTimeEnd= trainingTimeEnd;
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

	@Column(name="TRAINING_WORKSHOP_ID")
	public String getTrainingWorkshopId()
	{
		return this.trainingWorkshopId;
	}

	public void setTrainingWorkshopId(String trainingWorkshopId)
	{
		this.trainingWorkshopId = trainingWorkshopId;
	}

	@Column(name="TRAINING_WORKSHOP_NAME")
	public String getTrainingWorkshopName()
	{
		return this.trainingWorkshopName;
	}

	public void setTrainingWorkshopName(String trainingWorkshopName)
	{
		this.trainingWorkshopName = trainingWorkshopName;
	}

	@Column(name="TRAINING_POST")
	public String getTrainingPost()
	{
		return this.trainingPost;
	}

	public void setTrainingPost(String trainingPost)
	{
		this.trainingPost = trainingPost;
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

	@Column(name="TRAINING_PERSONS")
	public String getTrainingPersons()
	{
		return this.trainingPersons;
	}

	public void setTrainingPersons(String trainingPersons)
	{
		this.trainingPersons = trainingPersons;
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

	@Column(name="TRAINING_TEACHER")
	public String getTrainingTeacher()
	{
		return this.trainingTeacher;
	}

	public void setTrainingTeacher(String trainingTeacher)
	{
		this.trainingTeacher = trainingTeacher;
	}

	@Column(name="TRAINING_REMARK")
	public String getTrainingRemark()
	{
		return this.trainingRemark;
	}

	public void setTrainingRemark(String trainingRemark)
	{
		this.trainingRemark = trainingRemark;
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

	public void setTrainingTimeEnd(Date trainingTimeEnd) {
		this.trainingTimeEnd = trainingTimeEnd;
	}
	@Column(name="TRAINING_TIME_END")
	public Date getTrainingTimeEnd() {
		return trainingTimeEnd;
	}
	@Column(name="TRAINING_NAME")
	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

}
