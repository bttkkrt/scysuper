package com.jshx.jdjcjg.entity;

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
@Table(name="SUP_CHE_RES")
public class SupCheRes extends BaseModel
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
	 * 所在区域编号
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 关联任务编号
	 */
	private String taskId;

	/**
	 * 检查时间
	 */
	private Date checkTime;

	/**
	 * 检查人员编号
	 */
	private String checkUserid;

	/**
	 * 检查人员姓名
	 */
	private String checkUsername;

	/**
	 * 检查文书号
	 */
	private String checkInstrumentNum;

	/**
	 * 检查内容
	 */
	private String checkContent;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public SupCheRes(){
	}
	
	public SupCheRes(String id, String areaName, String companyName, Date checkTime, String checkUsername, String checkInstrumentNum){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.checkTime = checkTime;

this.checkUsername = checkUsername;

this.checkInstrumentNum = checkInstrumentNum;
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

	@Column(name="TASK_ID")
	public String getTaskId()
	{
		return this.taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	@Column(name="CHECK_TIME")
	public Date getCheckTime()
	{
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}

	@Column(name="CHECK_USERID")
	public String getCheckUserid()
	{
		return this.checkUserid;
	}

	public void setCheckUserid(String checkUserid)
	{
		this.checkUserid = checkUserid;
	}

	@Column(name="CHECK_USERNAME")
	public String getCheckUsername()
	{
		return this.checkUsername;
	}

	public void setCheckUsername(String checkUsername)
	{
		this.checkUsername = checkUsername;
	}

	@Column(name="CHECK_INSTRUMENT_NUM")
	public String getCheckInstrumentNum()
	{
		return this.checkInstrumentNum;
	}

	public void setCheckInstrumentNum(String checkInstrumentNum)
	{
		this.checkInstrumentNum = checkInstrumentNum;
	}

	@Column(name="CHECK_CONTENT")
	public String getCheckContent()
	{
		return this.checkContent;
	}

	public void setCheckContent(String checkContent)
	{
		this.checkContent = checkContent;
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
