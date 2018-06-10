package com.jshx.majorTrouble.entity;

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
@Table(name="MAJOR_TROUBLE")
public class MajorTrouble extends BaseModel
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
	 * 所在镇
	 */
	private String szz;

	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * 添加企业名称 id 李军 2013-08-05
	 */
	private String qymcId;

	/**
	 * 挂牌时间
	 */
	private String gpsj;

	/**
	 * 隐患名称
	 */
	private String yhmc;

	/**
	 * 隐患数
	 */
	private String yhs;

	/**
	 * 整改数
	 */
	private String zgs;

	/**
	 * 责任人
	 */
	private String zrr;

	/**
	 * 整改资金
	 */
	private String zgzj;

	/**
	 * 整改完成日期
	 */
	private String zgwcrq;

	/**
	 * 执法文书1
	 */
	private String zfws01;

	/**
	 * 文书文号01
	 */
	private String wswh01;

	/**
	 * 验收时间
	 */
	private String yssj;

	/**
	 * 验收人
	 */
	private String ysr;

	/**
	 * 执法文书2
	 */
	private String zfws02;

	/**
	 * 文书文号02
	 */
	private String wswh02;

	/**
	 * 整改方案
	 */
	private String zgfa;

	/**
	 * 监控措施
	 */
	private String jkcs;

	/**
	 * 整改前图片
	 */
	private String zgqtp;

	/**
	 * 整改后图片
	 */
	private String zghtp;

	/**
	 * 填报部门
	 */
	private String tbbm;
	/**
	 * 新增字段 lj 2013-07-19 用于关联 图片、 执法文书 、整改情况的材料
	 * @return
	 */
	private String linkId;

	/**
	 * 审核状态
	 */
	private String shzt;

	/**
	 * 审核备注
	 */
	private String remark;
	
	private String szzname;
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
	
	private String shenhe;
	
	
	/**
	 * 是否烟花爆竹经营企业
	 */
	private String ifyhbzjyqy;
	
	private String szc;
	
	private String szcname;

	/**
	 * 所在县
	 */
	private String county;

	@Column(name="county")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
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

	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
	}
	
	@Column
	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	
	@Column(name="SZZ")
	public String getSzz()
	{
		return this.szz;
	}

	public void setSzz(String szz)
	{
		this.szz = szz;
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

	@Column(name="GPSJ")
	public String getGpsj()
	{
		return this.gpsj;
	}

	public void setGpsj(String gpsj)
	{
		this.gpsj = gpsj;
	}

	@Column(name="YHMC")
	public String getYhmc()
	{
		return this.yhmc;
	}

	public void setYhmc(String yhmc)
	{
		this.yhmc = yhmc;
	}

	@Column(name="YHS")
	public String getYhs()
	{
		return this.yhs;
	}

	public void setYhs(String yhs)
	{
		if(yhs == null || "".equals(yhs))
		{
			this.yhs = "0";
		}
		else
		{
			this.yhs = yhs;
		}
	}

	@Column(name="ZGS")
	public String getZgs()
	{
		return this.zgs;
	}

	public void setZgs(String zgs)
	{
		if(zgs == null || "".equals(zgs))
		{
			this.zgs = "0";
		}
		else
		{
			this.zgs = zgs;
		}
	}

	@Column(name="ZRR")
	public String getZrr()
	{
		return this.zrr;
	}

	public void setZrr(String zrr)
	{
		this.zrr = zrr;
	}

	@Column(name="ZGZJ")
	public String getZgzj()
	{
		return this.zgzj;
	}

	public void setZgzj(String zgzj)
	{
		if(zgzj == null || "".equals(zgzj))
		{
			this.zgzj = "0";
		}
		else
		{
			this.zgzj = zgzj;
		}
	}

	@Column(name="ZGWCRQ")
	public String getZgwcrq()
	{
		return this.zgwcrq;
	}

	public void setZgwcrq(String zgwcrq)
	{
		this.zgwcrq = zgwcrq;
	}

	@Column(name="ZFWS01")
	public String getZfws01()
	{
		return this.zfws01;
	}

	public void setZfws01(String zfws01)
	{
		this.zfws01 = zfws01;
	}

	@Column(name="WSWH01")
	public String getWswh01()
	{
		return this.wswh01;
	}

	public void setWswh01(String wswh01)
	{
		this.wswh01 = wswh01;
	}

	@Column(name="YSSJ")
	public String getYssj()
	{
		return this.yssj;
	}

	public void setYssj(String yssj)
	{
		this.yssj = yssj;
	}

	@Column(name="YSR")
	public String getYsr()
	{
		return this.ysr;
	}

	public void setYsr(String ysr)
	{
		this.ysr = ysr;
	}

	@Column(name="ZFWS02")
	public String getZfws02()
	{
		return this.zfws02;
	}

	public void setZfws02(String zfws02)
	{
		this.zfws02 = zfws02;
	}

	@Column(name="WSWH02")
	public String getWswh02()
	{
		return this.wswh02;
	}

	public void setWswh02(String wswh02)
	{
		this.wswh02 = wswh02;
	}

	@Column(name="ZGFA")
	public String getZgfa()
	{
		return this.zgfa;
	}

	public void setZgfa(String zgfa)
	{
		this.zgfa = zgfa;
	}

	@Column(name="JKCS")
	public String getJkcs()
	{
		return this.jkcs;
	}

	public void setJkcs(String jkcs)
	{
		this.jkcs = jkcs;
	}

	@Column(name="ZGQTP")
	public String getZgqtp()
	{
		return this.zgqtp;
	}

	public void setZgqtp(String zgqtp)
	{
		this.zgqtp = zgqtp;
	}

	@Column(name="ZGHTP")
	public String getZghtp()
	{
		return this.zghtp;
	}

	public void setZghtp(String zghtp)
	{
		this.zghtp = zghtp;
	}

	@Column(name="TBBM")
	public String getTbbm()
	{
		return this.tbbm;
	}

	public void setTbbm(String tbbm)
	{
		this.tbbm = tbbm;
	}
	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	@Column
	public String getQymcId() {
		return qymcId;
	}

	public void setQymcId(String qymcId) {
		this.qymcId = qymcId;
	}
	@Column(name="SHENHE")
	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
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
