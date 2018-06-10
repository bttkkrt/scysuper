package com.jshx.zzxtPxb.entity;

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
@Table(name="JSHX_ZZXT_PXB")
public class JshxZzxtPxb extends BaseModel
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
	 * 所在镇名称
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
	 * 培训人id
	 */
	private String personId;

	/**
	 * 培训人姓名
	 */
	private String personName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 文化程度
	 */
	private String whcd;

	/**
	 * 从事岗位
	 */
	private String csgw;

	/**
	 * 培训时间
	 */
	private Date pxsj;

	/**
	 * 培训内容
	 */
	private String pxnr;

	/**
	 * 授课人
	 */
	private String skr;

	/**
	 * 考核成绩
	 */
	private String khcj;

	/**
	 * 培训学时
	 */
	private String pxxs;

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

	@Column(name="PERSON_NAME")
	public String getPersonName()
	{
		return this.personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}

	@Column(name="SEX")
	public String getSex()
	{
		return this.sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	@Column(name="WHCD")
	public String getWhcd()
	{
		return this.whcd;
	}

	public void setWhcd(String whcd)
	{
		this.whcd = whcd;
	}

	@Column(name="CSGW")
	public String getCsgw()
	{
		return this.csgw;
	}

	public void setCsgw(String csgw)
	{
		this.csgw = csgw;
	}

	@Column(name="PXSJ")
	public Date getPxsj()
	{
		return this.pxsj;
	}

	public void setPxsj(Date pxsj)
	{
		this.pxsj = pxsj;
	}

	@Column(name="PXNR")
	public String getPxnr()
	{
		return this.pxnr;
	}

	public void setPxnr(String pxnr)
	{
		this.pxnr = pxnr;
	}

	@Column(name="SKR")
	public String getSkr()
	{
		return this.skr;
	}

	public void setSkr(String skr)
	{
		this.skr = skr;
	}

	@Column(name="KHCJ")
	public String getKhcj()
	{
		return this.khcj;
	}

	public void setKhcj(String khcj)
	{
		this.khcj = khcj;
	}

	@Column(name="PXXS")
	public String getPxxs()
	{
		return this.pxxs;
	}

	public void setPxxs(String pxxs)
	{
		this.pxxs = pxxs;
	}
	@Column(name="PERSONID")
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
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
