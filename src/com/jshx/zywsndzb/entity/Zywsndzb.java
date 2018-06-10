package com.jshx.zywsndzb.entity;

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
@Table(name="ZYWSNDZB")
public class Zywsndzb extends BaseModel
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
	 * 年度
	 */
	private Date yearTime;

	/**
	 * 区域
	 */
	private String areaName;

	/**
	 * 网上申报目标数
	 */
	private String wssbmbs;

	/**
	 * 网上申报完成数
	 */
	private String wssbwcs;

	/**
	 * 处罚案例目标数
	 */
	private String cfalmbs;

	/**
	 * 处罚案例完成数
	 */
	private String cfalwcs;

	/**
	 * 定期检测目标数
	 */
	private String dqjcmbs;

	/**
	 * 定期检测完成数
	 */
	private String dqjcwcs;

	/**
	 * 健康监护目标数
	 */
	private String jkjhmbs;

	/**
	 * 健康监护完成数
	 */
	private String jkjhwcs;

	/**
	 * 企业培训目标数
	 */
	private String qypxmbs;

	/**
	 * 企业培训完成数
	 */
	private String qypxwcs;

	/**
	 * 基础建设示范企业目标数
	 */
	private String jcjsmbs;

	/**
	 * 基础建设示范企业完成数
	 */
	private String jcjswcs;
	
	
	private String areaId;

	
	public Zywsndzb(){
	}
	
	public Zywsndzb(String id, Date yearTime, String areaName){
this.id = id;

this.yearTime = yearTime;

this.areaName = areaName;
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

	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="WSSBMBS")
	public String getWssbmbs()
	{
		return this.wssbmbs;
	}

	public void setWssbmbs(String wssbmbs)
	{
		this.wssbmbs = wssbmbs;
	}

	@Column(name="WSSBWCS")
	public String getWssbwcs()
	{
		return this.wssbwcs;
	}

	public void setWssbwcs(String wssbwcs)
	{
		this.wssbwcs = wssbwcs;
	}

	@Column(name="CFALMBS")
	public String getCfalmbs()
	{
		return this.cfalmbs;
	}

	public void setCfalmbs(String cfalmbs)
	{
		this.cfalmbs = cfalmbs;
	}

	@Column(name="CFALWCS")
	public String getCfalwcs()
	{
		return this.cfalwcs;
	}

	public void setCfalwcs(String cfalwcs)
	{
		this.cfalwcs = cfalwcs;
	}

	@Column(name="DQJCMBS")
	public String getDqjcmbs()
	{
		return this.dqjcmbs;
	}

	public void setDqjcmbs(String dqjcmbs)
	{
		this.dqjcmbs = dqjcmbs;
	}

	@Column(name="DQJCWCS")
	public String getDqjcwcs()
	{
		return this.dqjcwcs;
	}

	public void setDqjcwcs(String dqjcwcs)
	{
		this.dqjcwcs = dqjcwcs;
	}

	@Column(name="JKJHMBS")
	public String getJkjhmbs()
	{
		return this.jkjhmbs;
	}

	public void setJkjhmbs(String jkjhmbs)
	{
		this.jkjhmbs = jkjhmbs;
	}

	@Column(name="JKJHWCS")
	public String getJkjhwcs()
	{
		return this.jkjhwcs;
	}

	public void setJkjhwcs(String jkjhwcs)
	{
		this.jkjhwcs = jkjhwcs;
	}

	@Column(name="QYPXMBS")
	public String getQypxmbs()
	{
		return this.qypxmbs;
	}

	public void setQypxmbs(String qypxmbs)
	{
		this.qypxmbs = qypxmbs;
	}

	@Column(name="QYPXWCS")
	public String getQypxwcs()
	{
		return this.qypxwcs;
	}

	public void setQypxwcs(String qypxwcs)
	{
		this.qypxwcs = qypxwcs;
	}

	@Column(name="JCJSMBS")
	public String getJcjsmbs()
	{
		return this.jcjsmbs;
	}

	public void setJcjsmbs(String jcjsmbs)
	{
		this.jcjsmbs = jcjsmbs;
	}

	@Column(name="JCJSWCS")
	public String getJcjswcs()
	{
		return this.jcjswcs;
	}

	public void setJcjswcs(String jcjswcs)
	{
		this.jcjswcs = jcjswcs;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
