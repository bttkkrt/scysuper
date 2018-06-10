package com.jshx.safeleader.entity;

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
@Table(name="SAFELEADER")
public class Safeleader extends BaseModel
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
	 * 类别
	 */
	private String basetype;
	/**
	 * 类别1
	 */
	private String basetype1;
	/**
	 * 类别2
	 */
	private String basetype2;
	/**
	 * 类别3
	 */
	private String basetype3;
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
	 * 台账类型
	 */
	private String leadgertype;

	/**
	 * 设施名称
	 */
	private String ssmc;

	/**
	 * 型号技术
	 */
	private String xhjs;

	/**
	 * 数量
	 */
	private String bumber;

	/**
	 * 使用部门
	 */
	private String userdept;

	/**
	 * 安装地点
	 */
	private String address;
	/**
	 * 使用时间
	 */
	private String usetime;
	/**
	 * 责任人
	 */
	private String zrr;
	/**
	 * 制造商
	 */
	private String zzs;
	/**
	 * 附件上传通用关联的模块中记录的id
	 */
	private String linkid;
	/**
	 * 街道名称
	 */
	private String deptName;
	/**
	 * 公司名称
	 */
	private String comName;
	/**
	 * 公司id
	 */
	private String comId;
	
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
	@Column
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}
	@Column
	public String getIfzywhqylx() {
		return ifzywhqylx;
	}

	public void setIfzywhqylx(String ifzywhqylx) {
		this.ifzywhqylx = ifzywhqylx;
	}
	@Column
	public String getIfyhbzjyqy() {
		return ifyhbzjyqy;
	}

	public void setIfyhbzjyqy(String ifyhbzjyqy) {
		this.ifyhbzjyqy = ifyhbzjyqy;
	}
	@Column
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	@Column
	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	@Column
	public String getQygm() {
		return qygm;
	}

	public void setQygm(String qygm) {
		this.qygm = qygm;
	}
	@Column
	public String getQyzclx() {
		return qyzclx;
	}

	public void setQyzclx(String qyzclx) {
		this.qyzclx = qyzclx;
	}

	@Transient
	public String getBasetype1() {
		return basetype1;
	}

	public void setBasetype1(String basetype1) {
		this.basetype1 = basetype1;
	}
	@Transient
	public String getBasetype2() {
		return basetype2;
	}

	public void setBasetype2(String basetype2) {
		this.basetype2 = basetype2;
	}
	@Transient
	public String getBasetype3() {
		return basetype3;
	}

	public void setBasetype3(String basetype3) {
		this.basetype3 = basetype3;
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

	
	@Column(name="BASETYPE")
	public String getBasetype()
	{
		return this.basetype;
	}

	public void setBasetype(String basetype)
	{
		this.basetype = basetype;
	}

	@Column(name="LEADGERTYPE")
	public String getLeadgertype()
	{
		return this.leadgertype;
	}

	public void setLeadgertype(String leadgertype)
	{
		this.leadgertype = leadgertype;
	}

	@Column(name="SSMC")
	public String getSsmc()
	{
		return this.ssmc;
	}

	public void setSsmc(String ssmc)
	{
		this.ssmc = ssmc;
	}

	@Column(name="XHJS")
	public String getXhjs()
	{
		return this.xhjs;
	}

	public void setXhjs(String xhjs)
	{
		this.xhjs = xhjs;
	}

	@Column(name="BUMBER")
	public String getBumber()
	{
		return this.bumber;
	}

	public void setBumber(String bumber)
	{
		this.bumber = bumber;
	}

	@Column(name="USERDEPT")
	public String getUserdept()
	{
		return this.userdept;
	}

	public void setUserdept(String userdept)
	{
		this.userdept = userdept;
	}

	@Column(name="ADDRESS")
	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Column(name="USETIME")
	public String getUsetime()
	{
		return this.usetime;
	}

	public void setUsetime(String usetime)
	{
		this.usetime = usetime;
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

	@Column(name="ZZS")
	public String getZzs()
	{
		return this.zzs;
	}

	public void setZzs(String zzs)
	{
		this.zzs = zzs;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
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
