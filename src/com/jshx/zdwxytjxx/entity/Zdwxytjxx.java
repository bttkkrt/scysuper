package com.jshx.zdwxytjxx.entity;

import java.util.Date;

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
@Table(name="ZDWXYTJXX")
public class Zdwxytjxx extends BaseModel
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
	 * 一级危险化学品重大危险源个数
	 */
	private String yjgs;

	/**
	 * 二级危险化学品重大危险源个数
	 */
	private String ejgs;

	/**
	 * 三级危险化学品重大危险源个数
	 */
	private String sjgs;

	/**
	 * 四级危险化学品重大危险源个数
	 */
	private String sijigs;

	/**
	 * 重大危险源总数
	 */
	private String zdwxyzs;
	
	private String tbr;

	/**
	 * 联系电话
	 */
	private String lxdh;

	/**
	 * 填表日期
	 */
	private Date tbrq;


	/**
	 * 企业类型
	 */
	private String qylx;

	/**
	 * 行业分类
	 */
	private String hyfl;

	/**
	 * 企业规模
	 */
	private String qygm;

	/**
	 * 企业注册类型
	 */
	private String qyzclx;
	
	/**
	 * 是否危化品企业类型
	 */
	private String ifwhpqylx;
	/**
	 * 是否职业危害企业类型
	 */
	private String ifzywhqylx;
	
	/**
	 * 是否烟花爆竹经营企业
	 */
	private String ifyhbzjyqy;
	
	private String szc;
	
	private String szcname;
	
	//heyc 20141210 修改 start
	/**
	 * 是否非煤矿山企业
	 */
	private String iffmksjyqy;

	@Column(name="IFFMKSJYQY")
	public String getIffmksjyqy() {
		return iffmksjyqy;
	}

	public void setIffmksjyqy(String iffmksjyqy) {
		this.iffmksjyqy = iffmksjyqy;
	}
	//heyc 20141210 修改 end

	
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
	
	@Column(name="IFWHPQYLX")
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}
	@Column(name="IFZYWHQYLX")
	public String getIfzywhqylx() {
		return ifzywhqylx;
	}

	public void setIfzywhqylx(String ifzywhqylx) {
		this.ifzywhqylx = ifzywhqylx;
	}
	@Column(name="IFYHBZJYQY")
	public String getIfyhbzjyqy() {
		return ifyhbzjyqy;
	}

	public void setIfyhbzjyqy(String ifyhbzjyqy) {
		this.ifyhbzjyqy = ifyhbzjyqy;
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

	@Column(name="QYGM")
	public String getQygm()
	{
		return this.qygm;
	}

	public void setQygm(String qygm)
	{
		this.qygm = qygm;
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

	@Column(name="YJGS")
	public String getYjgs()
	{
		return this.yjgs;
	}

	public void setYjgs(String yjgs)
	{
		if(yjgs == null || "".equals(yjgs))
		{
			this.yjgs = "0";
		}
		else
		{
			this.yjgs = yjgs;
		}
	}

	@Column(name="EJGS")
	public String getEjgs()
	{
		return this.ejgs;
	}

	public void setEjgs(String ejgs)
	{
		if(ejgs == null || "".equals(ejgs))
		{
			this.ejgs = "0";
		}
		else
		{
			this.ejgs = ejgs;
		}
	}

	@Column(name="SJGS")
	public String getSjgs()
	{
		return this.sjgs;
	}

	public void setSjgs(String sjgs)
	{
		if(sjgs == null || "".equals(sjgs))
		{
			this.sjgs = "0";
		}
		else
		{
			this.sjgs = sjgs;
		}
	}

	@Column(name="SIJIGS")
	public String getSijigs()
	{
		return this.sijigs;
	}

	public void setSijigs(String sijigs)
	{
		if(sijigs == null || "".equals(sijigs))
		{
			this.sijigs = "0";
		}
		else
		{
			this.sijigs = sijigs;
		}
	}

	@Column(name="ZDWXYZS")
	public String getZdwxyzs()
	{
		return this.zdwxyzs;
	}

	public void setZdwxyzs(String zdwxyzs)
	{
		this.zdwxyzs = zdwxyzs;
	}

	@Column(name="LXDH")
	public String getLxdh()
	{
		return this.lxdh;
	}

	public void setLxdh(String lxdh)
	{
		this.lxdh = lxdh;
	}

	@Column(name="TBRQ")
	public Date getTbrq()
	{
		return this.tbrq;
	}

	public void setTbrq(Date tbrq)
	{
		this.tbrq = tbrq;
	}
	@Column(name="TBR")
	public String getTbr() {
		return tbr;
	}

	public void setTbr(String tbr) {
		this.tbr = tbr;
	}

	/**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 企业所属监管类型
	 */
	private String companyType;
	
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name="ifzsqy")
	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}
}
