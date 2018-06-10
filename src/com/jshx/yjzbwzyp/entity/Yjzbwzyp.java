package com.jshx.yjzbwzyp.entity;

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
@Table(name="YJZBWZYP")
public class Yjzbwzyp extends BaseModel
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
	 * 应急类型
	 */
	private String yjlx;
	/**
	 * 装备名称
	 */
	private String zbmc;
	/**
	 * 储存地点
	 */
	private String ccdd;
	/**
	 * 储备数量
	 */
	private String cbsl;
	/**
	 * 主要用途
	 */
	private String zyyt;
	/**
	 * 保管人
	 */
	private String bgr;
	/**
	 * 联系电话
	 */
	private String lxdh;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 填报人
	 */
	private String tbr;
	/**
	 * 填报时间
	 */
	private String tbsj;
	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * qiye id
	 */
	private String comid;
	/**
	 * 所在街道
	 */
	private String szz;
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
	
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}

	public String getIfzywhqylx() {
		return ifzywhqylx;
	}

	public void setIfzywhqylx(String ifzywhqylx) {
		this.ifzywhqylx = ifzywhqylx;
	}

	public String getIfyhbzjyqy() {
		return ifyhbzjyqy;
	}

	public void setIfyhbzjyqy(String ifyhbzjyqy) {
		this.ifyhbzjyqy = ifyhbzjyqy;
	}

	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}

	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}

	public String getQygm() {
		return qygm;
	}

	public void setQygm(String qygm) {
		this.qygm = qygm;
	}

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

	
	@Column(name="YJLX")
	public String getYjlx()
	{
		return this.yjlx;
	}

	public void setYjlx(String yjlx)
	{
		this.yjlx = yjlx;
	}

	@Column(name="ZBMC")
	public String getZbmc()
	{
		return this.zbmc;
	}

	public void setZbmc(String zbmc)
	{
		this.zbmc = zbmc;
	}

	@Column(name="CCDD")
	public String getCcdd()
	{
		return this.ccdd;
	}

	public void setCcdd(String ccdd)
	{
		this.ccdd = ccdd;
	}

	@Column(name="CBSL")
	public String getCbsl()
	{
		return this.cbsl;
	}

	public void setCbsl(String cbsl)
	{
		this.cbsl = cbsl;
	}

	@Column(name="ZYYT")
	public String getZyyt()
	{
		return this.zyyt;
	}

	public void setZyyt(String zyyt)
	{
		this.zyyt = zyyt;
	}

	@Column(name="BGR")
	public String getBgr()
	{
		return this.bgr;
	}

	public void setBgr(String bgr)
	{
		this.bgr = bgr;
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

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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

	@Column(name="TBSJ")
	public String getTbsj()
	{
		return this.tbsj;
	}

	public void setTbsj(String tbsj)
	{
		this.tbsj = tbsj;
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

	@Column(name="COMID")
	public String getComid()
	{
		return this.comid;
	}

	public void setComid(String comid)
	{
		this.comid = comid;
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
