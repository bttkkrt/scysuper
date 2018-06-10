package com.jshx.zycsqk.entity;

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
@Table(name="ZYCSQK")
public class Zycsqk extends BaseModel
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
	 * 所在镇id
	 */
	private String szzid;

	/**
	 * 所在镇
	 */
	private String szzname;

	/**
	 * 企业id
	 */
	private String qyid;

	/**
	 * 企业名称
	 */
	private String qymc;

	/**
	 * 企业类型
	 */
	private String qylx;

	/**
	 * 行业分类
	 */
	private String hyfl;

	/**
	 * 企业注册类型
	 */
	private String qyzclx;

	/**
	 * 车间名称
	 */
	private String cjmc;

	/**
	 * 无尘车间
	 */
	private String wccj;

	/**
	 * 岗位
	 */
	private String gw;

	/**
	 * 职业病危害因素id
	 */
	private String zywhysmcid;

	/**
	 * 职业病危害因素名称
	 */
	private String zywhysmc;

	/**
	 * 设备状态
	 */
	private String sbzt;

	/**
	 * 操作方式
	 */
	private String czfs;

	/**
	 * 操作接触人数
	 */
	private String jcrs;

	/**
	 * 当班累积接触时间
	 */
	private String ljjcsj;

	/**
	 * 防护设施名称
	 */
	private String fhssmc;

	/**
	 * 个人防护用品
	 */
	private String grfhyp;
	
	private String linkid;
	
	private String szc;
	
	private String szcname;
	
	@Column
	public String getSzc() {
		return szc;
	}

	public void setSzc(String szc) {
		this.szc = szc;
	}
	@Column
	public String getSzcname() {
		return szcname;
	}

	public void setSzcname(String szcname) {
		this.szcname = szcname;
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

	
	@Column(name="SZZID")
	public String getSzzid()
	{
		return this.szzid;
	}

	public void setSzzid(String szzid)
	{
		this.szzid = szzid;
	}

	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
	}

	@Column(name="QYID")
	public String getQyid()
	{
		return this.qyid;
	}

	public void setQyid(String qyid)
	{
		this.qyid = qyid;
	}

	@Column(name="QYMC")
	public String getQymc()
	{
		return this.qymc;
	}

	public void setQymc(String qymc)
	{
		this.qymc = qymc;
	}

	@Column(name="QYLX")
	public String getQylx()
	{
		return this.qylx;
	}

	public void setQylx(String qylx)
	{
		this.qylx = qylx;
	}

	@Column(name="HYFL")
	public String getHyfl()
	{
		return this.hyfl;
	}

	public void setHyfl(String hyfl)
	{
		this.hyfl = hyfl;
	}

	@Column(name="QYZCLX")
	public String getQyzclx()
	{
		return this.qyzclx;
	}

	public void setQyzclx(String qyzclx)
	{
		this.qyzclx = qyzclx;
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

	@Column(name="WCCJ")
	public String getWccj()
	{
		return this.wccj;
	}

	public void setWccj(String wccj)
	{
		this.wccj = wccj;
	}

	@Column(name="GW")
	public String getGw()
	{
		return this.gw;
	}

	public void setGw(String gw)
	{
		this.gw = gw;
	}

	@Column(name="ZYWHYSMCID")
	public String getZywhysmcid()
	{
		return this.zywhysmcid;
	}

	public void setZywhysmcid(String zywhysmcid)
	{
		this.zywhysmcid = zywhysmcid;
	}

	@Column(name="ZYWHYSMC")
	public String getZywhysmc()
	{
		return this.zywhysmc;
	}

	public void setZywhysmc(String zywhysmc)
	{
		this.zywhysmc = zywhysmc;
	}

	@Column(name="SBZT")
	public String getSbzt()
	{
		return this.sbzt;
	}

	public void setSbzt(String sbzt)
	{
		this.sbzt = sbzt;
	}

	@Column(name="CZFS")
	public String getCzfs()
	{
		return this.czfs;
	}

	public void setCzfs(String czfs)
	{
		this.czfs = czfs;
	}

	@Column(name="JCRS")
	public String getJcrs()
	{
		return this.jcrs;
	}

	public void setJcrs(String jcrs)
	{
		this.jcrs = jcrs;
	}

	@Column(name="LJJCSJ")
	public String getLjjcsj()
	{
		return this.ljjcsj;
	}

	public void setLjjcsj(String ljjcsj)
	{
		this.ljjcsj = ljjcsj;
	}

	@Column(name="FHSSMC")
	public String getFhssmc()
	{
		return this.fhssmc;
	}

	public void setFhssmc(String fhssmc)
	{
		this.fhssmc = fhssmc;
	}

	@Column(name="GRFHYP")
	public String getGrfhyp()
	{
		return this.grfhyp;
	}

	public void setGrfhyp(String grfhyp)
	{
		this.grfhyp = grfhyp;
	}
	@Column
	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

}
