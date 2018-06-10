package com.jshx.zcyhsb.entity;

import java.util.Date;

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
@Table(name="JSHX_ZCYHSB")
public class JshxZcyhsb extends BaseModel
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
	 * 检查时间
	 */
	private Date jcsj;

	/**
	 * 检查人员
	 */
	private String jcry;

	/**
	 * 检查类别
	 */
	private String jclb = "0";

	/**
	 * 计划完成时间
	 */
	private Date jhwcsj;

	/**
	 * 目前状态
	 */
	private String mqzt;

	/**
	 * 复查时间
	 */
	private String fcsj;

	/**
	 * 复查人
	 */
	private String fcr;

	/**
	 * 级别
	 */
	private String jb;

	/**
	 * 整改投入资金
	 */
	private String zgtrzj;

	/**
	 * 复查验收情况
	 */
	private String fcysqk;

	/**
	 * 具体情况及整改措施方案
	 */
	private String csfa;

	/**
	 * 存在问题和隐患
	 */
	private String wtyh;
	/**
	 * 添加图片关联id
	 */
	private String linkId;
	
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
	/**
	 * 隐患数量
	 */
	private String yhsl;
	
	/**
	 * 0表示手机端上报
	 */
	private String type;
	
private String szc;
	
	private String szcname;
	
	private String yy;
	
	private String whpqylx;
	
	private String aqbzdbjb;
	
	private String renwuList;
	private String yinhuanList;
	private String zhenggaiList;
	private String fuchaList;
	
	private String hyflTwoLevel;
	
	@Transient
	public String getAqbzdbjb() {
		return aqbzdbjb;
	}

	public void setAqbzdbjb(String aqbzdbjb) {
		this.aqbzdbjb = aqbzdbjb;
	}

	@Transient
	public String getWhpqylx() {
		return whpqylx;
	}

	public void setWhpqylx(String whpqylx) {
		this.whpqylx = whpqylx;
	}
	
	

	@Column
	public String getYy() {
		return yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
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


	@Column(name="JCSJ")
	public Date getJcsj()
	{
		return this.jcsj;
	}

	public void setJcsj(Date jcsj)
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

	@Column(name="JCLB")
	public String getJclb()
	{
		return this.jclb;
	}

	public void setJclb(String jclb)
	{
		this.jclb = jclb;
	}

	@Column(name="JHWCSJ")
	public Date getJhwcsj()
	{
		return this.jhwcsj;
	}

	public void setJhwcsj(Date jhwcsj)
	{
		this.jhwcsj = jhwcsj;
	}

	@Column(name="MQZT")
	public String getMqzt()
	{
		return this.mqzt;
	}

	public void setMqzt(String mqzt)
	{
		this.mqzt = mqzt;
	}

	@Column(name="FCSJ")
	public String getFcsj()
	{
		return this.fcsj;
	}

	public void setFcsj(String fcsj)
	{
		this.fcsj = fcsj;
	}

	@Column(name="FCR")
	public String getFcr()
	{
		return this.fcr;
	}

	public void setFcr(String fcr)
	{
		this.fcr = fcr;
	}

	@Column(name="JB")
	public String getJb()
	{
		return this.jb;
	}

	public void setJb(String jb)
	{
		this.jb = jb;
	}

	@Column(name="ZGTRZJ")
	public String getZgtrzj()
	{
		return this.zgtrzj;
	}

	public void setZgtrzj(String zgtrzj)
	{
		this.zgtrzj = zgtrzj;
	}

	@Column(name="FCYSQK")
	public String getFcysqk()
	{
		return this.fcysqk;
	}

	public void setFcysqk(String fcysqk)
	{
		this.fcysqk = fcysqk;
	}

	@Column(name="CSFA")
	public String getCsfa()
	{
		return this.csfa;
	}

	public void setCsfa(String csfa)
	{
		this.csfa = csfa;
	}

	@Column(name="WTYH")
	public String getWtyh()
	{
		return this.wtyh;
	}

	public void setWtyh(String wtyh)
	{
		this.wtyh = wtyh;
	}
	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	@Column
	public String getYhsl() {
		return yhsl;
	}

	public void setYhsl(String yhsl) {
		this.yhsl = yhsl;
	}
	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	//hanxc 20141210 修改 start
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
	//hanxc 20141210 修改 end

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

	@Column(name="renwulist")
	public String getRenwuList() {
		return renwuList;
	}

	public void setRenwuList(String renwuList) {
		this.renwuList = renwuList;
	}

	@Column(name="yinhuanlist")
	public String getYinhuanList() {
		return yinhuanList;
	}

	public void setYinhuanList(String yinhuanList) {
		this.yinhuanList = yinhuanList;
	}

	@Column(name="zhenggailist")
	public String getZhenggaiList() {
		return zhenggaiList;
	}

	public void setZhenggaiList(String zhenggaiList) {
		this.zhenggaiList = zhenggaiList;
	}

	@Column(name="fuchalist")
	public String getFuchaList() {
		return fuchaList;
	}

	public void setFuchaList(String fuchaList) {
		this.fuchaList = fuchaList;
	}

	@Column(name="hyfltwolevel")
	public String getHyflTwoLevel() {
		return hyflTwoLevel;
	}

	public void setHyflTwoLevel(String hyflTwoLevel) {
		this.hyflTwoLevel = hyflTwoLevel;
	}
	
	
}
