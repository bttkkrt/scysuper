package com.jshx.whysjbqk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WHYSJBQK")
public class Whysjbqk extends BaseModel
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
	 * 粉尘
	 */
	private String fc;

	/**
	 * 化学毒物
	 */
	private String hxdw;

	/**
	 * 物理因素
	 */
	private String wlys;

	/**
	 * 生物性因素
	 */
	private String swxys;

	/**
	 * 危害因素组合id
	 */
	private String whysid;

	/**
	 * 危害因素组合名称
	 */
	private String whysmc;
	/**
	 * 
	 * qtids
	 */
	private String qtids;
	/**
	 * qtnames;
	 * @return
	 */
	private String qtnames;
	
	/**
	 * 企业id
	 */
	private String qyid;
	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * 所在镇id
	 */
	private String szzid;
	/**
	 * 所在镇
	 */
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
	
	private String whyssl;
	
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

	@Column(name="QYLX")
	public String getQylx()
	{
		return this.qylx;
	}

	public void setQylx(String qylx)
	{
		this.qylx = qylx;
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
	

	@Transient
	public String getQtids() {
		return qtids;
	}

	public void setQtids(String qtids) {
		this.qtids = qtids;
	}
	@Transient
	public String getQtnames() {
		return qtnames;
	}

	public void setQtnames(String qtnames) {
		this.qtnames = qtnames;
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

	
	@Column(name="FC")
	public String getFc()
	{
		return this.fc;
	}

	public void setFc(String fc)
	{
		this.fc = fc;
	}

	@Column(name="HXDW")
	public String getHxdw()
	{
		return this.hxdw;
	}

	public void setHxdw(String hxdw)
	{
		this.hxdw = hxdw;
	}

	@Column(name="WLYS")
	public String getWlys()
	{
		return this.wlys;
	}

	public void setWlys(String wlys)
	{
		this.wlys = wlys;
	}

	@Column(name="SWXYS")
	public String getSwxys()
	{
		return this.swxys;
	}

	public void setSwxys(String swxys)
	{
		this.swxys = swxys;
	}

	@Column(name="WHYSID")
	public String getWhysid()
	{
		return this.whysid;
	}

	public void setWhysid(String whysid)
	{
		this.whysid = whysid;
	}

	@Column(name="WHYSMC")
	public String getWhysmc()
	{
		return this.whysmc;
	}

	public void setWhysmc(String whysmc)
	{
		this.whysmc = whysmc;
	}
	@Column
	public String getWhyssl() {
		return whyssl;
	}

	public void setWhyssl(String whyssl) {
		this.whyssl = whyssl;
	}

}
