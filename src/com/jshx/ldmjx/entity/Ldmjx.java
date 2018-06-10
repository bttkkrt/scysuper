package com.jshx.ldmjx.entity;

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
@Table(name="LDMJX")
public class Ldmjx extends BaseModel
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
	 * 检查数
	 */
	private String jcs;

	/**
	 * 出动检查数
	 */
	private String cdjcs;

	/**
	 * 发现隐患数
	 */
	private String fxyhs;

	/**
	 * 整改隐患数
	 */
	private String zgyhs;

	/**
	 * 处罚数
	 */
	private String cfs;

	/**
	 * 整治关闭数
	 */
	private String zzgbs;

	/**
	 * 达到安全标准化数
	 */
	private String ddaqbzhs;

	/**
	 * 月份
	 */
	private Date monthTime;

	/**
	 * 区域
	 */
	private String areaName;

	/**
	 * 现有企业数
	 */
	private String xyqys;

	/**
	 * 录入监管信息系统数
	 */
	private String lrjgxxx;

	/**
	 * 建立安全总监数
	 */
	private String jlaqzjs;

	/**
	 * 投入安全责任险数
	 */
	private String traqzr;
	
	private String  areaId;

	
	public Ldmjx(){
	}
	
	public Ldmjx(String id, Date monthTime, String areaName){
this.id = id;

this.monthTime = monthTime;

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

	
	@Column(name="JCS")
	public String getJcs()
	{
		return this.jcs;
	}

	public void setJcs(String jcs)
	{
		this.jcs = jcs;
	}

	@Column(name="CDJCS")
	public String getCdjcs()
	{
		return this.cdjcs;
	}

	public void setCdjcs(String cdjcs)
	{
		this.cdjcs = cdjcs;
	}

	@Column(name="FXYHS")
	public String getFxyhs()
	{
		return this.fxyhs;
	}

	public void setFxyhs(String fxyhs)
	{
		this.fxyhs = fxyhs;
	}

	@Column(name="ZGYHS")
	public String getZgyhs()
	{
		return this.zgyhs;
	}

	public void setZgyhs(String zgyhs)
	{
		this.zgyhs = zgyhs;
	}

	@Column(name="CFS")
	public String getCfs()
	{
		return this.cfs;
	}

	public void setCfs(String cfs)
	{
		this.cfs = cfs;
	}

	@Column(name="ZZGBS")
	public String getZzgbs()
	{
		return this.zzgbs;
	}

	public void setZzgbs(String zzgbs)
	{
		this.zzgbs = zzgbs;
	}

	@Column(name="DDAQBZHS")
	public String getDdaqbzhs()
	{
		return this.ddaqbzhs;
	}

	public void setDdaqbzhs(String ddaqbzhs)
	{
		this.ddaqbzhs = ddaqbzhs;
	}

	@Column(name="MONTH_TIME")
	public Date getMonthTime()
	{
		return this.monthTime;
	}

	public void setMonthTime(Date monthTime)
	{
		this.monthTime = monthTime;
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

	@Column(name="XYQYS")
	public String getXyqys()
	{
		return this.xyqys;
	}

	public void setXyqys(String xyqys)
	{
		this.xyqys = xyqys;
	}

	@Column(name="LRJGXXX")
	public String getLrjgxxx()
	{
		return this.lrjgxxx;
	}

	public void setLrjgxxx(String lrjgxxx)
	{
		this.lrjgxxx = lrjgxxx;
	}

	@Column(name="JLAQZJS")
	public String getJlaqzjs()
	{
		return this.jlaqzjs;
	}

	public void setJlaqzjs(String jlaqzjs)
	{
		this.jlaqzjs = jlaqzjs;
	}

	@Column(name="TRAQZR")
	public String getTraqzr()
	{
		return this.traqzr;
	}

	public void setTraqzr(String traqzr)
	{
		this.traqzr = traqzr;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
}
