package com.jshx.whpqybzh.entity;

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
@Table(name="WHPQYBZH")
public class Whpqybzh extends BaseModel
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
	 * 区域
	 */
	private String areaName;

	/**
	 * 计划核查数合计
	 */
	private String jhhcshj;

	/**
	 * 计划核查数生产
	 */
	private String jhhcssc;

	/**
	 * 计划核查数使用
	 */
	private String jhhcssy;

	/**
	 * 计划核查数仓储
	 */
	private String jhhcscc;

	/**
	 * 计划核查数加油站
	 */
	private String jhhxsjyz;

	/**
	 * 已完成核查数合计
	 */
	private String ywchcshj;

	/**
	 * 已完成核查数生产
	 */
	private String ywchcssc;

	/**
	 * 已完成核查数使用
	 */
	private String ywchcssy;

	/**
	 * 已完成核查数仓储
	 */
	private String ywchcscc;

	/**
	 * 已完成核查数经营带储存
	 */
	private String ywchcsjysc;

	/**
	 * 计划核查数经营带储存
	 */
	private String jhhcsjydsc;

	/**
	 * 已完成核查数加油站
	 */
	private String ywchcsjyz;

	/**
	 * 月份
	 */
	private Date monthTime;
	
	
	private String areaId;

	
	public Whpqybzh(){
	}
	
	public Whpqybzh(String id, String areaName, Date monthTime){
this.id = id;

this.areaName = areaName;

this.monthTime = monthTime;
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

	
	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="JHHCSHJ")
	public String getJhhcshj()
	{
		return this.jhhcshj;
	}

	public void setJhhcshj(String jhhcshj)
	{
		this.jhhcshj = jhhcshj;
	}

	@Column(name="JHHCSSC")
	public String getJhhcssc()
	{
		return this.jhhcssc;
	}

	public void setJhhcssc(String jhhcssc)
	{
		this.jhhcssc = jhhcssc;
	}

	@Column(name="JHHCSSY")
	public String getJhhcssy()
	{
		return this.jhhcssy;
	}

	public void setJhhcssy(String jhhcssy)
	{
		this.jhhcssy = jhhcssy;
	}

	@Column(name="JHHCSCC")
	public String getJhhcscc()
	{
		return this.jhhcscc;
	}

	public void setJhhcscc(String jhhcscc)
	{
		this.jhhcscc = jhhcscc;
	}

	@Column(name="JHHXSJYZ")
	public String getJhhxsjyz()
	{
		return this.jhhxsjyz;
	}

	public void setJhhxsjyz(String jhhxsjyz)
	{
		this.jhhxsjyz = jhhxsjyz;
	}

	@Column(name="YWCHCSHJ")
	public String getYwchcshj()
	{
		return this.ywchcshj;
	}

	public void setYwchcshj(String ywchcshj)
	{
		this.ywchcshj = ywchcshj;
	}

	@Column(name="YWCHCSSC")
	public String getYwchcssc()
	{
		return this.ywchcssc;
	}

	public void setYwchcssc(String ywchcssc)
	{
		this.ywchcssc = ywchcssc;
	}

	@Column(name="YWCHCSSY")
	public String getYwchcssy()
	{
		return this.ywchcssy;
	}

	public void setYwchcssy(String ywchcssy)
	{
		this.ywchcssy = ywchcssy;
	}

	@Column(name="YWCHCSCC")
	public String getYwchcscc()
	{
		return this.ywchcscc;
	}

	public void setYwchcscc(String ywchcscc)
	{
		this.ywchcscc = ywchcscc;
	}

	@Column(name="YWCHCSJYSC")
	public String getYwchcsjysc()
	{
		return this.ywchcsjysc;
	}

	public void setYwchcsjysc(String ywchcsjysc)
	{
		this.ywchcsjysc = ywchcsjysc;
	}

	@Column(name="JHHCSJYDSC")
	public String getJhhcsjydsc()
	{
		return this.jhhcsjydsc;
	}

	public void setJhhcsjydsc(String jhhcsjydsc)
	{
		this.jhhcsjydsc = jhhcsjydsc;
	}

	@Column(name="YWCHCSJYZ")
	public String getYwchcsjyz()
	{
		return this.ywchcsjyz;
	}

	public void setYwchcsjyz(String ywchcsjyz)
	{
		this.ywchcsjyz = ywchcsjyz;
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
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}
