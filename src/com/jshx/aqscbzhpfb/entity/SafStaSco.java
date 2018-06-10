package com.jshx.aqscbzhpfb.entity;

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
@Table(name="SAF_STA_SCO")
public class SafStaSco extends BaseModel
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
	 * 总分
	 */
	private String totalScore;

	/**
	 * 评分人
	 */
	private String ratingUserId;

	/**
	 * 评分时间
	 */
	private Date patingDate;

	
	public SafStaSco(){
	}
	
	public SafStaSco(String id, String areaName, String companyName, String totalScore, String ratingUserId, Date patingDate){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.totalScore = totalScore;

this.ratingUserId = ratingUserId;

this.patingDate = patingDate;
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

	@Column(name="TOTAL_SCORE")
	public String getTotalScore()
	{
		return this.totalScore;
	}

	public void setTotalScore(String totalScore)
	{
		this.totalScore = totalScore;
	}

	@Column(name="RATING_USER_ID")
	public String getRatingUserId()
	{
		return this.ratingUserId;
	}

	public void setRatingUserId(String ratingUserId)
	{
		this.ratingUserId = ratingUserId;
	}

	@Column(name="PATING_DATE")
	public Date getPatingDate()
	{
		return this.patingDate;
	}

	public void setPatingDate(Date patingDate)
	{
		this.patingDate = patingDate;
	}

}
