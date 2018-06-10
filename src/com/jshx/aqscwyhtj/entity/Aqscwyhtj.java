package com.jshx.aqscwyhtj.entity;

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
@Table(name="AQSCWYHTJ")
public class Aqscwyhtj extends BaseModel
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
	 * 年份
	 */
	private Date yearTime;

	/**
	 * 其他领导带队安全检查年度计划次数
	 */
	private String qtndjhcs;

	/**
	 * 召开安委会全体成员会议年度计划次数
	 */
	private String zkndjhs;

	/**
	 * 主要领导带队安全检查年度计划次数
	 */
	private String zyndjhcs;

	/**
	 * 分管领导带队安全检查年度计划次数
	 */
	private String fgndjhcs;

	/**
	 * 主要领导批示安全生产工作次数
	 */
	private String zyaqsccs;

	/**
	 * 工委会研究安全生产年度计划次数
	 */
	private String gwndjhcs;

	
	public Aqscwyhtj(){
	}
	
	public Aqscwyhtj(String id, Date yearTime){
this.id = id;

this.yearTime = yearTime;
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

	
	@Column(name="YEAR_TIME")
	public Date getYearTime()
	{
		return this.yearTime;
	}

	public void setYearTime(Date yearTime)
	{
		this.yearTime = yearTime;
	}

	@Column(name="QTNDJHCS")
	public String getQtndjhcs()
	{
		return this.qtndjhcs;
	}

	public void setQtndjhcs(String qtndjhcs)
	{
		this.qtndjhcs = qtndjhcs;
	}

	@Column(name="ZKNDJHS")
	public String getZkndjhs()
	{
		return this.zkndjhs;
	}

	public void setZkndjhs(String zkndjhs)
	{
		this.zkndjhs = zkndjhs;
	}

	@Column(name="ZYNDJHCS")
	public String getZyndjhcs()
	{
		return this.zyndjhcs;
	}

	public void setZyndjhcs(String zyndjhcs)
	{
		this.zyndjhcs = zyndjhcs;
	}

	@Column(name="FGNDJHCS")
	public String getFgndjhcs()
	{
		return this.fgndjhcs;
	}

	public void setFgndjhcs(String fgndjhcs)
	{
		this.fgndjhcs = fgndjhcs;
	}

	@Column(name="ZYAQSCCS")
	public String getZyaqsccs()
	{
		return this.zyaqsccs;
	}

	public void setZyaqsccs(String zyaqsccs)
	{
		this.zyaqsccs = zyaqsccs;
	}

	@Column(name="GWNDJHCS")
	public String getGwndjhcs()
	{
		return this.gwndjhcs;
	}

	public void setGwndjhcs(String gwndjhcs)
	{
		this.gwndjhcs = gwndjhcs;
	}

}
