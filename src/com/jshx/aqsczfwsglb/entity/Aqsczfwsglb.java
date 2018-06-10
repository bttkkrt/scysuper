package com.jshx.aqsczfwsglb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="AQSCZFWSGLB")
public class Aqsczfwsglb extends BaseModel
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
	 * 项目内容
	 */
	private String xmrr;

	/**
	 * 计量单位
	 */
	private String jldw;

	/**
	 * 合计
	 */
	private String hj;

	/**
	 * 危险化学品
	 */
	private String wxp;

	/**
	 * 烟花爆竹
	 */
	private String yhbz;

	/**
	 * 冶金
	 */
	private String yj;

	/**
	 * 有色
	 */
	private String youse;

	/**
	 * 其它
	 */
	private String qt;

	/**
	 * 关联id
	 */
	private String proid;

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

	
	@Column(name="XMRR")
	public String getXmrr()
	{
		return this.xmrr;
	}

	public void setXmrr(String xmrr)
	{
		this.xmrr = xmrr;
	}

	@Column(name="JLDW")
	public String getJldw()
	{
		return this.jldw;
	}

	public void setJldw(String jldw)
	{
		this.jldw = jldw;
	}

	@Column(name="HJ")
	public String getHj()
	{
		return this.hj;
	}

	public void setHj(String hj)
	{
		this.hj = hj;
	}

	@Column(name="WXP")
	public String getWxp()
	{
		return this.wxp;
	}

	public void setWxp(String wxp)
	{
		this.wxp = wxp;
	}

	@Column(name="YHBZ")
	public String getYhbz()
	{
		return this.yhbz;
	}

	public void setYhbz(String yhbz)
	{
		this.yhbz = yhbz;
	}

	@Column(name="YJ")
	public String getYj()
	{
		return this.yj;
	}

	public void setYj(String yj)
	{
		this.yj = yj;
	}

	@Column(name="YOUSE")
	public String getYouse()
	{
		return this.youse;
	}

	public void setYouse(String youse)
	{
		this.youse = youse;
	}

	@Column(name="QT")
	public String getQt()
	{
		return this.qt;
	}

	public void setQt(String qt)
	{
		this.qt = qt;
	}

	@Column(name="PROID")
	public String getProid()
	{
		return this.proid;
	}

	public void setProid(String proid)
	{
		this.proid = proid;
	}

}
