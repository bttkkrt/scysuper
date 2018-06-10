package com.jshx.dangerUsed.entity;


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
@Table(name="dangerUsed")
public class DangerUsed extends BaseModel
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
	 * 所在街道
	 */
	private String szz;
	/**
	 * 危险化学品名称
	 */
	private String dangername;
	/**
	 * 危规号
	 */
	private String dannum;
	/**
	 * 年使用量(t)
	 */
	private String yearuser;
	/**
	 * 最大贮存量(t)
	 */
	private String maxstorge;
	/**
	 * 贮存方式
	 */
	private String storgerway;
	/**
	 * 贮存地点
	 */
	private String storgeaddress;
	/**
	 * 包装方式
	 */
	private String packingway;
	/**
	 * 是否是易制毒化学品
	 */
	private String sfsyzdhxp;
	/**
	 * 是否是重点监管化学品
	 */
	private String sfzdjghxp;
	/**
	 * 是否是易制爆化学品
	 */
	private String sfyzbhxp;
	/**
	 * 是否是剧毒化学品
	 */
	private String sfjdhxp;
	/**
	 * 企业类型
	 */
	private String qylx;
	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * 企业id
	 */
	private String comid;
	/**
	 * 企业类型 
	 */
	private String qylxc;
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

	public String getQylxc() {
		return qylxc;
	}

	public void setQylxc(String qylxc) {
		this.qylxc = qylxc;
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

	
	@Column(name="SZZ")
	public String getSzz()
	{
		return this.szz;
	}

	public void setSzz(String szz)
	{
		this.szz = szz;
	}

	@Column(name="DANGERNAME")
	public String getDangername()
	{
		return this.dangername;
	}

	public void setDangername(String dangername)
	{
		this.dangername = dangername;
	}

	@Column(name="DANNUM")
	public String getDannum()
	{
		return this.dannum;
	}

	public void setDannum(String dannum)
	{
		this.dannum = dannum;
	}

	@Column(name="YEARUSER")
	public String getYearuser()
	{
		return this.yearuser;
	}

	public void setYearuser(String yearuser)
	{
		this.yearuser = yearuser;
	}

	@Column(name="MAXSTORGE")
	public String getMaxstorge()
	{
		return this.maxstorge;
	}

	public void setMaxstorge(String maxstorge)
	{
		this.maxstorge = maxstorge;
	}

	@Column(name="STORGERWAY")
	public String getStorgerway()
	{
		return this.storgerway;
	}

	public void setStorgerway(String storgerway)
	{
		this.storgerway = storgerway;
	}

	@Column(name="STORGEADDRESS")
	public String getStorgeaddress()
	{
		return this.storgeaddress;
	}

	public void setStorgeaddress(String storgeaddress)
	{
		this.storgeaddress = storgeaddress;
	}

	@Column(name="PACKINGWAY")
	public String getPackingway()
	{
		return this.packingway;
	}

	public void setPackingway(String packingway)
	{
		this.packingway = packingway;
	}

	@Column(name="SFSYZDHXP")
	public String getSfsyzdhxp()
	{
		return this.sfsyzdhxp;
	}

	public void setSfsyzdhxp(String sfsyzdhxp)
	{
		this.sfsyzdhxp = sfsyzdhxp;
	}

	@Column(name="SFZDJGHXP")
	public String getSfzdjghxp()
	{
		return this.sfzdjghxp;
	}

	public void setSfzdjghxp(String sfzdjghxp)
	{
		this.sfzdjghxp = sfzdjghxp;
	}

	@Column(name="SFYZBHXP")
	public String getSfyzbhxp()
	{
		return this.sfyzbhxp;
	}

	public void setSfyzbhxp(String sfyzbhxp)
	{
		this.sfyzbhxp = sfyzbhxp;
	}

	@Column(name="SFJDHXP")
	public String getSfjdhxp()
	{
		return this.sfjdhxp;
	}

	public void setSfjdhxp(String sfjdhxp)
	{
		this.sfjdhxp = sfjdhxp;
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
