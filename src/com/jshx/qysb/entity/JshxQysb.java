package com.jshx.qysb.entity;

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
@Table(name="JSHX_QYSB")
public class JshxQysb extends BaseModel
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
	 * 设备名称
	 */
	private String sbmc;
	/**
	 * 规格型号及材质
	 */
	private String ggxh;
	/**
	 * 数量
	 */
	private String jshxSl;
	/**
	 * 工作参数
	 */
	private String gzcs;
	/**
	 * 使用场所
	 */
	private String sycs;
	/**
	 * 使用日期
	 */
	private Date syrq;
	/**
	 * 供应商
	 */
	private String gys;
	/**
	 * 完好状态
	 */
	private String whzt;
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
	 * 是否属于特种设备
	 */
	private String iftzsb;
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
	/**
	 * 附件关联id
	 */
	private String linkid;
	
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
	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
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

	@Column(name="SBMC")
	public String getSbmc()
	{
		return this.sbmc;
	}

	public void setSbmc(String sbmc)
	{
		this.sbmc = sbmc;
	}

	@Column(name="GGXH")
	public String getGgxh()
	{
		return this.ggxh;
	}

	public void setGgxh(String ggxh)
	{
		this.ggxh = ggxh;
	}

	@Column(name="JSHX_SL")
	public String getJshxSl()
	{
		return this.jshxSl;
	}

	public void setJshxSl(String jshxSl)
	{
		this.jshxSl = jshxSl;
	}

	@Column(name="GZCS")
	public String getGzcs()
	{
		return this.gzcs;
	}

	public void setGzcs(String gzcs)
	{
		this.gzcs = gzcs;
	}

	@Column(name="SYCS")
	public String getSycs()
	{
		return this.sycs;
	}

	public void setSycs(String sycs)
	{
		this.sycs = sycs;
	}

	@Column(name="SYRQ")
	public Date getSyrq()
	{
		return this.syrq;
	}

	public void setSyrq(Date syrq)
	{
		this.syrq = syrq;
	}

	@Column(name="GYS")
	public String getGys()
	{
		return this.gys;
	}

	public void setGys(String gys)
	{
		this.gys = gys;
	}

	@Column(name="WHZT")
	public String getWhzt()
	{
		return this.whzt;
	}

	public void setWhzt(String whzt)
	{
		this.whzt = whzt;
	}
	@Column(name="IFTZSB")
	public String getIftzsb() {
		return iftzsb;
	}

	public void setIftzsb(String iftzsb) {
		this.iftzsb = iftzsb;
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
