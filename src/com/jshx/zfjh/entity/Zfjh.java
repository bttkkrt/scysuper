package com.jshx.zfjh.entity;

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
@Table(name="ZFJH")
public class Zfjh extends BaseModel
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
	 * 关联编号
	 */
	private String proId;

	/**
	 * 年
	 */
	private String zfjhYear;

	/**
	 * 周次
	 */
	private String zfjhWeek;

	/**
	 * 直管企业名称
	 */
	private String zgqyName;

	/**
	 * 直管企业工作日
	 */
	private String zgqyDay;

	/**
	 * 直管企业检查内容
	 */
	private String zgqyContent;

	/**
	 * 行业领域名称
	 */
	private String hylyName;

	/**
	 * 行业领域工作日
	 */
	private String hylyDay;

	/**
	 * 行业领域检查内容
	 */
	private String hylyContent;

	/**
	 * 综合监管名称
	 */
	private String zhjgName;

	/**
	 * 综合监管工作日
	 */
	private String zhjgDay;

	/**
	 * 综合监管检查内容
	 */
	private String zhjgContent;

	/**
	 * 县区政府名称
	 */
	private String xqzfName;

	/**
	 * 县区政府工作日
	 */
	private String xqzfDay;

	/**
	 * 县区政府检查内容
	 */
	private String xqzfContent;


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

	
	@Column(name="PRO_ID")
	public String getProId()
	{
		return this.proId;
	}

	public void setProId(String proId)
	{
		this.proId = proId;
	}

	@Column(name="ZFJH_YEAR")
	public String getZfjhYear()
	{
		return this.zfjhYear;
	}

	public void setZfjhYear(String zfjhYear)
	{
		this.zfjhYear = zfjhYear;
	}

	@Column(name="ZFJH_WEEK")
	public String getZfjhWeek()
	{
		return this.zfjhWeek;
	}

	public void setZfjhWeek(String zfjhWeek)
	{
		this.zfjhWeek = zfjhWeek;
	}

	@Column(name="ZGQY_NAME")
	public String getZgqyName()
	{
		return this.zgqyName;
	}

	public void setZgqyName(String zgqyName)
	{
		this.zgqyName = zgqyName;
	}

	@Column(name="ZGQY_DAY")
	public String getZgqyDay()
	{
		return this.zgqyDay;
	}

	public void setZgqyDay(String zgqyDay)
	{
		this.zgqyDay = zgqyDay;
	}

	@Column(name="ZGQY_CONTENT")
	public String getZgqyContent()
	{
		return this.zgqyContent;
	}

	public void setZgqyContent(String zgqyContent)
	{
		this.zgqyContent = zgqyContent;
	}

	@Column(name="HYLY_NAME")
	public String getHylyName()
	{
		return this.hylyName;
	}

	public void setHylyName(String hylyName)
	{
		this.hylyName = hylyName;
	}

	@Column(name="HYLY_DAY")
	public String getHylyDay()
	{
		return this.hylyDay;
	}

	public void setHylyDay(String hylyDay)
	{
		this.hylyDay = hylyDay;
	}

	@Column(name="HYLY_CONTENT")
	public String getHylyContent()
	{
		return this.hylyContent;
	}

	public void setHylyContent(String hylyContent)
	{
		this.hylyContent = hylyContent;
	}

	@Column(name="ZHJG_NAME")
	public String getZhjgName()
	{
		return this.zhjgName;
	}

	public void setZhjgName(String zhjgName)
	{
		this.zhjgName = zhjgName;
	}

	@Column(name="ZHJG_DAY")
	public String getZhjgDay()
	{
		return this.zhjgDay;
	}

	public void setZhjgDay(String zhjgDay)
	{
		this.zhjgDay = zhjgDay;
	}

	@Column(name="ZHJG_CONTENT")
	public String getZhjgContent()
	{
		return this.zhjgContent;
	}

	public void setZhjgContent(String zhjgContent)
	{
		this.zhjgContent = zhjgContent;
	}

	@Column(name="XQZF_NAME")
	public String getXqzfName()
	{
		return this.xqzfName;
	}

	public void setXqzfName(String xqzfName)
	{
		this.xqzfName = xqzfName;
	}

	@Column(name="XQZF_DAY")
	public String getXqzfDay()
	{
		return this.xqzfDay;
	}

	public void setXqzfDay(String xqzfDay)
	{
		this.xqzfDay = xqzfDay;
	}

	@Column(name="XQZF_CONTENT")
	public String getXqzfContent()
	{
		return this.xqzfContent;
	}

	public void setXqzfContent(String xqzfContent)
	{
		this.xqzfContent = xqzfContent;
	}

}
