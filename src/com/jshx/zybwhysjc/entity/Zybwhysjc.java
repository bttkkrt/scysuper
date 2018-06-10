package com.jshx.zybwhysjc.entity;

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
@Table(name="ZYBWHYSJC")
public class Zybwhysjc extends BaseModel
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
	 * 车检id
	 */
	private String cjid;
	
	/**
	 * 无尘车间
	 */
	private String wccj;
	/**
	 * 岗位
	 */
	private String gw;

	/**
	 * 车间名称
	 */
	private String cjmc;

	/**
	 * 职业病危害因素id
	 */
	private String zybwhysid;

	/**
	 * 职业病危害因素名称
	 */
	private String zybwhysmc;

	/**
	 * 检测点数
	 */
	private String jcds;

	/**
	 * 不合格点数
	 */
	private String bhgds;

	/**
	 * 填报人员
	 */
	private String tbry;

	/**
	 * 检测机构
	 */
	private String jcjg;


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

	
	@Column(name="WCCJ")
	public String getWccj()
	{
		return this.wccj;
	}

	public void setWccj(String wccj)
	{
		this.wccj = wccj;
	}

	@Column(name="CJMC")
	public String getCjmc()
	{
		return this.cjmc;
	}

	public void setCjmc(String cjmc)
	{
		this.cjmc = cjmc;
	}

	@Column(name="ZYBWHYSID")
	public String getZybwhysid()
	{
		return this.zybwhysid;
	}

	public void setZybwhysid(String zybwhysid)
	{
		this.zybwhysid = zybwhysid;
	}

	@Column(name="ZYBWHYSMC")
	public String getZybwhysmc()
	{
		return this.zybwhysmc;
	}

	public void setZybwhysmc(String zybwhysmc)
	{
		this.zybwhysmc = zybwhysmc;
	}

	@Column(name="JCDS")
	public String getJcds()
	{
		return this.jcds;
	}

	public void setJcds(String jcds)
	{
		this.jcds = jcds;
	}

	@Column(name="BHGDS")
	public String getBhgds()
	{
		return this.bhgds;
	}

	public void setBhgds(String bhgds)
	{
		this.bhgds = bhgds;
	}

	@Column(name="TBRY")
	public String getTbry()
	{
		return this.tbry;
	}

	public void setTbry(String tbry)
	{
		this.tbry = tbry;
	}

	@Column(name="JCJG")
	public String getJcjg()
	{
		return this.jcjg;
	}

	public void setJcjg(String jcjg)
	{
		this.jcjg = jcjg;
	}
	@Column()
	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}
	@Column()
	public String getCjid() {
		return cjid;
	}

	public void setCjid(String cjid) {
		this.cjid = cjid;
	}

}
