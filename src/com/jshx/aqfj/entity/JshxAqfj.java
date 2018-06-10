package com.jshx.aqfj.entity;

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
@Table(name="JSHX_AQFJ")
public class JshxAqfj extends BaseModel
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
	 * 安全附件名称
	 */
	private String aqfjmc;
	/**
	 * 型号
	 */
	private String xh;
	/**
	 * 所属设备名称
	 */
	private String sssbmc;
	/**
	 * 所属特种设备出厂编号
	 */
	private String sstzsbccbh;
	/**
	 * 投用日期
	 */
	private Date tyrq;
	/**
	 * 制造单位名称
	 */
	private String zzdwmc;
	/**
	 * 使用部门
	 */
	private String sybm;
	/**
	 * 设备安装地点
	 */
	private String sbazdd;
	/**
	 * 使用状态
	 */
	private String syzt;
	/**
	 * 上次检测日期
	 */
	private Date scjcsj;
	/**
	 * 下次检测日期
	 */
	private Date xcjcsj;
	/**
	 * 安全责任人
	 */
	private String aqzrr;
	/**
	 * 检验情况记录
	 */
	private String jyqkjl;
	/**
	 * 检验报告编号
	 */
	private String jybgbh;
	/**
	 * 检验结论
	 */
	private String jyjl;
	/**
	 * 检测单位
	 */
	private String jcdw;
	/**
	 * 整改情况
	 */
	private String zgqk;
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
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	@Column(name="HYFL")
	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	@Column(name="QYGM")
	public String getQygm() {
		return qygm;
	}

	public void setQygm(String qygm) {
		this.qygm = qygm;
	}
	@Column(name="QYZCLX")
	public String getQyzclx() {
		return qyzclx;
	}

	public void setQyzclx(String qyzclx) {
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

	@Column(name="AQFJMC")
	public String getAqfjmc()
	{
		return this.aqfjmc;
	}

	public void setAqfjmc(String aqfjmc)
	{
		this.aqfjmc = aqfjmc;
	}

	@Column(name="XH")
	public String getXh()
	{
		return this.xh;
	}

	public void setXh(String xh)
	{
		this.xh = xh;
	}

	@Column(name="SSSBMC")
	public String getSssbmc()
	{
		return this.sssbmc;
	}

	public void setSssbmc(String sssbmc)
	{
		this.sssbmc = sssbmc;
	}

	@Column(name="SSTZSBCCBH")
	public String getSstzsbccbh()
	{
		return this.sstzsbccbh;
	}

	public void setSstzsbccbh(String sstzsbccbh)
	{
		this.sstzsbccbh = sstzsbccbh;
	}

	@Column(name="TYRQ")
	public Date getTyrq()
	{
		return this.tyrq;
	}

	public void setTyrq(Date tyrq)
	{
		this.tyrq = tyrq;
	}

	@Column(name="ZZDWMC")
	public String getZzdwmc()
	{
		return this.zzdwmc;
	}

	public void setZzdwmc(String zzdwmc)
	{
		this.zzdwmc = zzdwmc;
	}

	@Column(name="SYBM")
	public String getSybm()
	{
		return this.sybm;
	}

	public void setSybm(String sybm)
	{
		this.sybm = sybm;
	}

	@Column(name="SBAZDD")
	public String getSbazdd()
	{
		return this.sbazdd;
	}

	public void setSbazdd(String sbazdd)
	{
		this.sbazdd = sbazdd;
	}

	@Column(name="SYZT")
	public String getSyzt()
	{
		return this.syzt;
	}

	public void setSyzt(String syzt)
	{
		this.syzt = syzt;
	}

	@Column(name="SCJCSJ")
	public Date getScjcsj()
	{
		return this.scjcsj;
	}

	public void setScjcsj(Date scjcsj)
	{
		this.scjcsj = scjcsj;
	}

	@Column(name="XCJCSJ")
	public Date getXcjcsj()
	{
		return this.xcjcsj;
	}

	public void setXcjcsj(Date xcjcsj)
	{
		this.xcjcsj = xcjcsj;
	}

	@Column(name="AQZRR")
	public String getAqzrr()
	{
		return this.aqzrr;
	}

	public void setAqzrr(String aqzrr)
	{
		this.aqzrr = aqzrr;
	}

	@Column(name="JYQKJL")
	public String getJyqkjl()
	{
		return this.jyqkjl;
	}

	public void setJyqkjl(String jyqkjl)
	{
		this.jyqkjl = jyqkjl;
	}

	@Column(name="JYBGBH")
	public String getJybgbh()
	{
		return this.jybgbh;
	}

	public void setJybgbh(String jybgbh)
	{
		this.jybgbh = jybgbh;
	}

	@Column(name="JYJL")
	public String getJyjl()
	{
		return this.jyjl;
	}

	public void setJyjl(String jyjl)
	{
		this.jyjl = jyjl;
	}

	@Column(name="JCDW")
	public String getJcdw()
	{
		return this.jcdw;
	}

	public void setJcdw(String jcdw)
	{
		this.jcdw = jcdw;
	}

	@Column(name="ZGQK")
	public String getZgqk()
	{
		return this.zgqk;
	}

	public void setZgqk(String zgqk)
	{
		this.zgqk = zgqk;
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
