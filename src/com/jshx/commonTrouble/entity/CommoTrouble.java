package com.jshx.commonTrouble.entity;

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
@Table(name="COMMO_TROUBLE")
public class CommoTrouble extends BaseModel
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
	 * 检查企业名称
	 */
	private String qymc;
	/**
	 * 新增企业名称id 2013-08-05
	 */
	private String qymcId;

	/**
	 * 检查时间
	 */
	private String jcsj;

	/**
	 * 检查人员
	 */
	private String jcry;

	/**
	 * 出动人次
	 */
	private String cdcs;

	/**
	 * 隐患数
	 */
	private String yhs;

	/**
	 * 重大隐患数
	 */
	private String zdyhs;

	/**
	 * 执法文书
	 */
	private String zfws;

	/**
	 * 执法文书号
	 */
	private String zfwsh;

	/**
	 * 执法文书或图片
	 */
	private String zftp;

	/**
	 * 隐患整改数
	 */
	private String yhzgs;

	/**
	 * 重大隐患整改数
	 */
	private String zdyhzgs;

	/**
	 * 整改资金
	 */
	private String zgzj;

	/**
	 * 整改完成时间
	 */
	private String zgwcsj;

	/**
	 * 整改情况
	 */
	private String zgqk;

	/**
	 * 审核状态
	 */
	private String shzt;

	/**
	 * 审核备注
	 */
	private String remark;
	/**
	 * 新增字段 lj 2013-07-18 用于关联 图片、 执法文书 、整改情况的材料
	 * @return
	 */
	private String linkId;
	
	/**
	 *  添加是否是有企业上报整改情况
	 * @return 默认 是
	 */
	private String isqysb;
	
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
	
	
	/**
	 * 是否烟花爆竹经营企业
	 */
	private String ifyhbzjyqy;
	
	private String shenhe;
	
	private String yhnr;
	
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
	public String getIsqysb() {
		return isqysb;
	}

	public void setIsqysb(String isqysb) {
		this.isqysb = isqysb;
	}

	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	@Column(name="JCSJ")
	public String getJcsj()
	{
		return this.jcsj;
	}

	public void setJcsj(String jcsj)
	{
		this.jcsj = jcsj;
	}

	@Column(name="JCRY")
	public String getJcry()
	{
		return this.jcry;
	}

	public void setJcry(String jcry)
	{
		this.jcry = jcry;
	}

	@Column(name="CDCS")
	public String getCdcs()
	{
		return this.cdcs;
	}

	public void setCdcs(String cdcs)
	{
		if(cdcs == null || "".equals(cdcs))
		{
			this.cdcs = "0";
		}
		else
		{
			this.cdcs = cdcs;
		}
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

	@Column(name="ZDYHS")
	public String getZdyhs()
	{
		return this.zdyhs;
	}

	public void setZdyhs(String zdyhs)
	{
		if(zdyhs == null || "".equals(zdyhs))
		{
			this.zdyhs = "0";
		}
		else
		{
			this.zdyhs = zdyhs;
		}
	}

	@Column(name="ZFWS")
	public String getZfws()
	{
		return this.zfws;
	}

	public void setZfws(String zfws)
	{
		this.zfws = zfws;
	}

	@Column(name="ZFWSH")
	public String getZfwsh()
	{
		return this.zfwsh;
	}

	public void setZfwsh(String zfwsh)
	{
		this.zfwsh = zfwsh;
	}

	@Column(name="ZFTP")
	public String getZftp()
	{
		return this.zftp;
	}

	public void setZftp(String zftp)
	{
		this.zftp = zftp;
	}

	@Column(name="YHZGS")
	public String getYhzgs()
	{
		return this.yhzgs;
	}

	public void setYhzgs(String yhzgs)
	{
		if(yhzgs == null || "".equals(yhzgs))
		{
			this.yhzgs = "0";
		}
		else
		{
			this.yhzgs = yhzgs;
		}
	}

	@Column(name="ZDYHZGS")
	public String getZdyhzgs()
	{
		return this.zdyhzgs;
	}

	public void setZdyhzgs(String zdyhzgs)
	{
		if(zdyhzgs == null || "".equals(zdyhzgs))
		{
			this.zdyhzgs = "0";
		}
		else
		{
			this.zdyhzgs = zdyhzgs;
		}
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

	@Column(name="ZGWCSJ")
	public String getZgwcsj()
	{
		return this.zgwcsj;
	}

	public void setZgwcsj(String zgwcsj)
	{
		this.zgwcsj = zgwcsj;
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

	@Column(name="SHZT")
	public String getShzt()
	{
		return this.shzt;
	}

	public void setShzt(String shzt)
	{
		this.shzt = shzt;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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
	@Column
	public String getYhnr() {
		return yhnr;
	}

	public void setYhnr(String yhnr) {
		this.yhnr = yhnr;
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
