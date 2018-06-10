package com.jshx.shjl.entity;

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
@Table(name="CHECK_RECORD")
public class CheckRecord extends BaseModel
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
	 * 关联id
	 */
	private String infoId;

	/**
	 * 审核人id
	 */
	private String checkUserid;

	/**
	 * 审核人名称
	 */
	private String checkUsername;

	/**
	 * 审核结果
	 */
	private String checkResult;

	/**
	 * 审核时间
	 */
	private Date checkTime;

	/**
	 * 审核备注
	 */
	private String checkRemark;

	
	public CheckRecord(){
	}
	
	public CheckRecord(String id,String checkUserid,String infoId,String checkResult,String checkUsername,String checkRemark,Date createTime,Date checkTime){
this.id = id;
this.checkUserid=checkUserid;
this.infoId=infoId;
this.checkResult=checkResult;
this.checkUsername=checkUsername;
this.checkRemark=checkRemark;
this.createTime=createTime;
this.checkTime = checkTime;
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

	
	@Column(name="INFO_ID")
	public String getInfoId()
	{
		return this.infoId;
	}

	public void setInfoId(String infoId)
	{
		this.infoId = infoId;
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

	@Column(name="CHECK_RESULT")
	public String getCheckResult()
	{
		return this.checkResult;
	}

	public void setCheckResult(String checkResult)
	{
		this.checkResult = checkResult;
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

	@Column(name="CHECK_REMARK")
	public String getCheckRemark()
	{
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark)
	{
		this.checkRemark = checkRemark;
	}

}
