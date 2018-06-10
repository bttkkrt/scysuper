package com.jshx.zdwxyjbtz.entity;

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
@Table(name="ZDWXYJBTZ")
public class Zdwxyjbtz extends BaseModel
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
	 * R值
	 */
	private String rz;
	/**
	 * 单元内主要装置、设施及生产（储存）规模
	 */
	private String zzssscgm;
	/**
	 * 是否位于化工（工业）园区
	 */
	private String sfwyhgyq;
	/**
	 * 园区名称
	 */
	private String yqmc;
	/**
	 * 重大危险源与周边重点防护目标最近距离情况（m）
	 */
	private String zzjl;
	/**
	 * 厂区边界外500m范围内人数估算值
	 */
	private String rsgs;
	/**
	 * 近三年内危险化学品事故情况
	 */
	private String sgqk;
	/**
	 * 附件关联id
	 */
	private String linkid;
	/**
	 * 填表人
	 */
	private String tbr;
	/**
	 * 联系电话
	 */
	private String lxdh;
	/**
	 * 所在镇id
	 */
	private String szzid;
	/**
	 * 所在镇名称
	 */
	private String szzname;
	/**
	 * 企业id
	 */
	private String qyid;
	/**
	 * 填报单位名称
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
	 * 企业规模
	 */
	private String qygm;
	/**
	 * 企业注册类型
	 */
	private String qyzclx;
	/**
	 * 重大危险源名称
	 */
	private String zdwxymc;
	/**
	 * 重大危险源所在地址
	 */
	private String zdwxyszdz;
	/**
	 * 重大危险源投用时间
	 */
	private Date zdwxytysj;
	/**
	 * 重大危险源级别
	 */
	private String zdwxyjb;
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

	
	@Column(name="RZ")
	public String getRz()
	{
		return this.rz;
	}

	public void setRz(String rz)
	{
		this.rz = rz;
	}

	@Column(name="ZZSSSCGM")
	public String getZzssscgm()
	{
		return this.zzssscgm;
	}

	public void setZzssscgm(String zzssscgm)
	{
		this.zzssscgm = zzssscgm;
	}

	@Column(name="SFWYHGYQ")
	public String getSfwyhgyq()
	{
		return this.sfwyhgyq;
	}

	public void setSfwyhgyq(String sfwyhgyq)
	{
		this.sfwyhgyq = sfwyhgyq;
	}

	@Column(name="YQMC")
	public String getYqmc()
	{
		return this.yqmc;
	}

	public void setYqmc(String yqmc)
	{
		this.yqmc = yqmc;
	}

	@Column(name="ZZJL")
	public String getZzjl()
	{
		return this.zzjl;
	}

	public void setZzjl(String zzjl)
	{
		this.zzjl = zzjl;
	}

	@Column(name="RSGS")
	public String getRsgs()
	{
		return this.rsgs;
	}

	public void setRsgs(String rsgs)
	{
		this.rsgs = rsgs;
	}

	@Column(name="SGQK")
	public String getSgqk()
	{
		return this.sgqk;
	}

	public void setSgqk(String sgqk)
	{
		this.sgqk = sgqk;
	}

	@Column(name="TBR")
	public String getTbr()
	{
		return this.tbr;
	}

	public void setTbr(String tbr)
	{
		this.tbr = tbr;
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

	@Column(name="ZDWXYMC")
	public String getZdwxymc()
	{
		return this.zdwxymc;
	}

	public void setZdwxymc(String zdwxymc)
	{
		this.zdwxymc = zdwxymc;
	}

	@Column(name="ZDWXYSZDZ")
	public String getZdwxyszdz()
	{
		return this.zdwxyszdz;
	}

	public void setZdwxyszdz(String zdwxyszdz)
	{
		this.zdwxyszdz = zdwxyszdz;
	}

	@Column(name="ZDWXYTYSJ")
	public Date getZdwxytysj()
	{
		return this.zdwxytysj;
	}

	public void setZdwxytysj(Date zdwxytysj)
	{
		this.zdwxytysj = zdwxytysj;
	}

	@Column(name="ZDWXYJB")
	public String getZdwxyjb()
	{
		return this.zdwxyjb;
	}

	public void setZdwxyjb(String zdwxyjb)
	{
		this.zdwxyjb = zdwxyjb;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
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
