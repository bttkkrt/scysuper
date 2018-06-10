package com.jshx.wfwgcompany.entity;

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
@Table(name="WFWGCOMPANY")
public class Wfwgcompany extends BaseModel
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
	 * 单位名称
	 */
	private String companyname;
	
	/**
	 * 镇，开发区等
	 */
	private String szzid;
	
	private String szzname;
	
	private String szc;
	
	private String szcname;
	
	/**
	 * 
	 * 负责人
	 */
	private String fzr;
	
	/**
	 * 身份证
	 */
	private String sfz;
	
	/**
	 * 行业分类
	 */
	private String hyfl;
	
	/**
	 * 违规违法类别
	 */
	private String wgwflb;
	
	/**
	 * 危化品企业类型
	 */
	private String whpqylx;
	/**
	 * 是否危化品企业类型
	 */
	private String ifwhpqylx;
	
	/**
	 * 是否为五小企业
	 */
	private String ifwxqy;
	
	/**
	 * 行政强制措施
	 */
	private String xzqzcs;

	/**
	 * 立案日期
	 */
	private String larq;
	
	/**
	 * 结案日期
	 */
	private String jarq;
	
	/**
	 * 是否有营业执照
	 */
	private String ifyyzz;
	
	/**
	 * 是否属举报
	 */
	private String ifjb;
	
	/**
	 * 是否实施经济处罚
	 */
	private String ifssjjcf;
	
	/**
	 * 处罚金额
	 */
	private String cfje;
	
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
	@Column
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	@Column
	public String getSzzid() {
		return szzid;
	}

	public void setSzzid(String szzid) {
		this.szzid = szzid;
	}
	@Column
	public String getSzzname() {
		return szzname;
	}

	public void setSzzname(String szzname) {
		this.szzname = szzname;
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
	@Column
	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	@Column
	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	@Column
	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	@Column
	public String getWgwflb() {
		return wgwflb;
	}

	public void setWgwflb(String wgwflb) {
		this.wgwflb = wgwflb;
	}
	@Column
	public String getWhpqylx() {
		return whpqylx;
	}

	public void setWhpqylx(String whpqylx) {
		this.whpqylx = whpqylx;
	}
	@Column
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}
	@Column
	public String getIfwxqy() {
		return ifwxqy;
	}

	public void setIfwxqy(String ifwxqy) {
		this.ifwxqy = ifwxqy;
	}
	@Column
	public String getXzqzcs() {
		return xzqzcs;
	}

	public void setXzqzcs(String xzqzcs) {
		this.xzqzcs = xzqzcs;
	}
	@Column
	public String getLarq() {
		return larq;
	}

	public void setLarq(String larq) {
		this.larq = larq;
	}
	@Column
	public String getJarq() {
		return jarq;
	}

	public void setJarq(String jarq) {
		this.jarq = jarq;
	}
	@Column
	public String getIfyyzz() {
		return ifyyzz;
	}

	public void setIfyyzz(String ifyyzz) {
		this.ifyyzz = ifyyzz;
	}
	@Column
	public String getIfjb() {
		return ifjb;
	}

	public void setIfjb(String ifjb) {
		this.ifjb = ifjb;
	}
	@Column
	public String getIfssjjcf() {
		return ifssjjcf;
	}

	public void setIfssjjcf(String ifssjjcf) {
		this.ifssjjcf = ifssjjcf;
	}
	@Column
	public String getCfje() {
		return cfje;
	}

	public void setCfje(String cfje) {
		this.cfje = cfje;
	}

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
	private String countyName;

	@Column(name="countyName")
	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}
