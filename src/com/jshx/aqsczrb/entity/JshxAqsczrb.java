package com.jshx.aqsczrb.entity;

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
@Table(name="JSHX_AQSCZRB")
public class JshxAqsczrb extends BaseModel
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
	 * 企业总人数
	 */
	private String count;
	/**
	 * 一线员工数
	 */
	private String onecount;
	/**
	 * 参保人数
	 */
	private String cbcount;
	/**
	 * 总保费
	 */
	private String totalfee;
	/**
	 * 总保额
	 */
	private String totalmoney;
	/**
	 * 参保人数
	 */
	private String personCount;
	/**
	 * 总保费
	 */
	private String totalfee02;
	/**
	 * 总保额
	 */
	private String totalmoney02;
	/**
	 * 总保费
	 */
	private String totalfee03;
	/**
	 * 总保额
	 */
	private String totalmoney03;
	/**
	 * 总保费
	 */
	private String totalfee04;
	/**
	 * 总保额
	 */
	private String totalmoney04;
	/**
	 * 保险公司
	 */
	private String bxgs;
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
	 * 新增关联责任险发票扫描件关联id
	 * @return
	 */
	private String linkId;
	
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
	
	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	@Column(name="COUNT")
	public String getCount()
	{
		return this.count;
	}

	public void setCount(String count)
	{
		if(count == null || "".equals(count))
		{
			this.count = "0";
		}
		else
		{
			this.count = count;
		}
	}

	@Column(name="ONECOUNT")
	public String getOnecount()
	{
		return this.onecount;
	}

	public void setOnecount(String onecount)
	{
		if(onecount == null || "".equals(onecount))
		{
			this.onecount = "0";
		}
		else
		{
			this.onecount = onecount;
		}
	}

	@Column(name="CBCOUNT")
	public String getCbcount()
	{
		return this.cbcount;
	}

	public void setCbcount(String cbcount)
	{
		if(cbcount == null || "".equals(cbcount))
		{
			this.cbcount = "0";
		}
		else
		{
			this.cbcount = cbcount;
		}
	}

	@Column(name="TOTALFEE")
	public String getTotalfee()
	{
		return this.totalfee;
	}

	public void setTotalfee(String totalfee)
	{
		if(totalfee == null || "".equals(totalfee))
		{
			this.totalfee = "0";
		}
		else
		{
			this.totalfee = totalfee;
		}
	}

	@Column(name="TOTALMONEY")
	public String getTotalmoney()
	{
		return this.totalmoney;
	}

	public void setTotalmoney(String totalmoney)
	{
		if(totalmoney == null || "".equals(totalmoney))
		{
			this.totalmoney = "0";
		}
		else
		{
			this.totalmoney = totalmoney;
		}
	}

	@Column(name="PERSON_COUNT")
	public String getPersonCount()
	{
		return this.personCount;
	}

	public void setPersonCount(String personCount)
	{
		if(personCount == null || "".equals(personCount))
		{
			this.personCount = "0";
		}
		else
		{
			this.personCount = personCount;
		}
	}

	@Column(name="TOTALFEE02")
	public String getTotalfee02()
	{
		return this.totalfee02;
	}

	public void setTotalfee02(String totalfee02)
	{
		if(totalfee02 == null || "".equals(totalfee02))
		{
			this.totalfee02 = "0";
		}
		else
		{
			this.totalfee02 = totalfee02;
		}
	}

	@Column(name="TOTALMONEY02")
	public String getTotalmoney02()
	{
		return this.totalmoney02;
	}

	public void setTotalmoney02(String totalmoney02)
	{
		if(totalmoney02 == null || "".equals(totalmoney02))
		{
			this.totalmoney02 = "0";
		}
		else
		{
			this.totalmoney02 = totalmoney02;
		}
	}

	@Column(name="TOTALFEE03")
	public String getTotalfee03()
	{
		return this.totalfee03;
	}

	public void setTotalfee03(String totalfee03)
	{
		if(totalfee03 == null || "".equals(totalfee03))
		{
			this.totalfee03 = "0";
		}
		else
		{
			this.totalfee03 = totalfee03;
		}
	}

	@Column(name="TOTALMONEY03")
	public String getTotalmoney03()
	{
		return this.totalmoney03;
	}

	public void setTotalmoney03(String totalmoney03)
	{
		if(totalmoney03 == null || "".equals(totalmoney03))
		{
			this.totalmoney03 = "0";
		}
		else
		{
			this.totalmoney03 = totalmoney03;
		}
	}

	@Column(name="TOTALFEE04")
	public String getTotalfee04()
	{
		return this.totalfee04;
	}

	public void setTotalfee04(String totalfee04)
	{
		if(totalfee04 == null || "".equals(totalfee04))
		{
			this.totalfee04 = "0";
		}
		else
		{
			this.totalfee04 = totalfee04;
		}
	}

	@Column(name="TOTALMONEY04")
	public String getTotalmoney04()
	{
		return this.totalmoney04;
	}

	public void setTotalmoney04(String totalmoney04)
	{
		if(totalmoney04 == null || "".equals(totalmoney04))
		{
			this.totalmoney04 = "0";
		}
		else
		{
			this.totalmoney04 = totalmoney04;
		}
	}

	@Column(name="BXGS")
	public String getBxgs()
	{
		return this.bxgs;
	}

	public void setBxgs(String bxgs)
	{
		this.bxgs = bxgs;
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
