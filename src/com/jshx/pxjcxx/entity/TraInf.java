package com.jshx.pxjcxx.entity;

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
@Table(name="TRA_INF")
public class TraInf extends BaseModel
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
	 * 培训主题
	 */
	private String trainTheme;

	/**
	 * 培训开始时间
	 */
	private Date startTime;

	/**
	 * 培训结束时间
	 */
	private Date endTime;

	/**
	 * 培训地点
	 */
	private String trainAddress;

	/**
	 * 培训单位
	 */
	private String trainTeacher;

	/**
	 * 培训方式
	 */
	private String trainMethod;

	/**
	 * 发证数
	 */
	private String certificateNum;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public TraInf(){
	}
	
	public TraInf(String id, String trainTheme, Date startTime, Date endTime, String trainMethod, String certificateNum,String createUserID){
this.id = id;

this.trainTheme = trainTheme;

this.startTime = startTime;

this.endTime = endTime;

this.trainMethod = trainMethod;

this.certificateNum = certificateNum;
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

	
	@Column(name="TRAIN_THEME")
	public String getTrainTheme()
	{
		return this.trainTheme;
	}

	public void setTrainTheme(String trainTheme)
	{
		this.trainTheme = trainTheme;
	}

	@Column(name="START_TIME")
	public Date getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	@Column(name="END_TIME")
	public Date getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	@Column(name="TRAIN_ADDRESS")
	public String getTrainAddress()
	{
		return this.trainAddress;
	}

	public void setTrainAddress(String trainAddress)
	{
		this.trainAddress = trainAddress;
	}

	@Column(name="TRAIN_TEACHER")
	public String getTrainTeacher()
	{
		return this.trainTeacher;
	}

	public void setTrainTeacher(String trainTeacher)
	{
		this.trainTeacher = trainTeacher;
	}

	@Column(name="TRAIN_METHOD")
	public String getTrainMethod()
	{
		return this.trainMethod;
	}

	public void setTrainMethod(String trainMethod)
	{
		this.trainMethod = trainMethod;
	}

	@Column(name="CERTIFICATE_NUM")
	public String getCertificateNum()
	{
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum)
	{
		this.certificateNum = certificateNum;
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

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

}
