package com.jshx.bzzpx.entity;

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
@Table(name="TEA_LEA_TRA")
public class TeaLeaTra extends BaseModel
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
	 * 姓名
	 */
	private String trainingName;

	/**
	 * 性别
	 */
	private String trainingSex;

	/**
	 * 职位
	 */
	private String trainingPosition;

	/**
	 * 电话
	 */
	private String trainingPhone;

	/**
	 * 身份证
	 */
	private String trainingCardnum;

	/**
	 * 培训开始时间
	 */
	private Date trainingTime;
	/**
	 * 培训结束时间
	 */
	private Date trainingTimeEnd;

	/**
	 * 培训内容
	 */
	private String trainingContent;

	/**
	 * 授课人
	 */
	private String trainingTeacher;

	/**
	 * 培训地点
	 */
	private String trainingAddress;

	/**
	 * 培训学时
	 */
	private String trainingTeacheTime;
	
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

	
	public TeaLeaTra(){
	}
	
	public TeaLeaTra(String id, String areaName, String companyName, String trainingWorkshopName, String trainingName, String trainingCardnum, Date trainingTime,Date trainingTimeEnd){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.trainingWorkshopName = trainingWorkshopName;

this.trainingName = trainingName;

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

	@Column(name="TRAINING_NAME")
	public String getTrainingName()
	{
		return this.trainingName;
	}

	public void setTrainingName(String trainingName)
	{
		this.trainingName = trainingName;
	}

	@Column(name="TRAINING_SEX")
	public String getTrainingSex()
	{
		return this.trainingSex;
	}

	public void setTrainingSex(String trainingSex)
	{
		this.trainingSex = trainingSex;
	}

	@Column(name="TRAINING_POSITION")
	public String getTrainingPosition()
	{
		return this.trainingPosition;
	}

	public void setTrainingPosition(String trainingPosition)
	{
		this.trainingPosition = trainingPosition;
	}

	@Column(name="TRAINING_PHONE")
	public String getTrainingPhone()
	{
		return this.trainingPhone;
	}

	public void setTrainingPhone(String trainingPhone)
	{
		this.trainingPhone = trainingPhone;
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

	@Column(name="TRAINING_TIME")
	public Date getTrainingTime()
	{
		return this.trainingTime;
	}

	public void setTrainingTime(Date trainingTime)
	{
		this.trainingTime = trainingTime;
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

	public void setTrainingTimeEnd(Date trainingTimeEnd) {
		this.trainingTimeEnd = trainingTimeEnd;
	}
	@Column(name="TRAINING_TIME_END")
	public Date getTrainingTimeEnd() {
		return trainingTimeEnd;
	}

}
