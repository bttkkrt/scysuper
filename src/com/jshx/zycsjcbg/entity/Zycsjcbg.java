package com.jshx.zycsjcbg.entity;

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
@Table(name="ZYCSJCBG")
public class Zycsjcbg extends BaseModel
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
	 * 企业名称
	 */
	private String qymc;

	/**
	 * 企业id
	 */
	private String comid;

	/**
	 * 所在街道
	 */
	private String szz;

	/**
	 * 报告名称
	 */
	private String smjmc;

	//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
	private String qylx;
	private String hyfl;
	private String qygm;
	private String qyzclx;
	private String linkid;
	private String uploadtime;
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
	 * 检测危害因素
	 * @return
	 */
	
	private String jcwz;
	
	/**
	 * 检测地点
	 * @return
	 */
	
	private String jcdd;
	
	/**
	 * 检测日期
	 * @return
	 */
	
	private String jcsj;
	
	/**
	 * 检测机构
	 * @return
	 */
	
	private String jcdwcode;
	
	private String jcdwname;
	
	private String szc;
	
	private String szcname;
	
	/**
	 * 检测点数
	 * @return
	 */
	private String jcds;
	
	/**
	 * 不合格点数
	 * @return
	 */
	private String bhgds;
	/**
	 * 不合格点的危害因素名称
	 * @return
	 */
	private String bggName;
	
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
	public String getJcds() {
		return jcds;
	}

	public void setJcds(String jcds) {
		this.jcds = jcds;
	}
	@Column
	public String getBhgds() {
		return bhgds;
	}

	public void setBhgds(String bhgds) {
		this.bhgds = bhgds;
	}
	@Column
	public String getBggName() {
		return bggName;
	}

	public void setBggName(String bggName) {
		this.bggName = bggName;
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
	public String getJcwz() {
		return jcwz;
	}

	public void setJcwz(String jcwz) {
		this.jcwz = jcwz;
	}
	@Column
	public String getJcdd() {
		return jcdd;
	}

	public void setJcdd(String jcdd) {
		this.jcdd = jcdd;
	}
	@Column
	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	@Column
	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
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
	@Column
	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
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

	@Column(name="SMJMC")
	public String getSmjmc()
	{
		return this.smjmc;
	}

	public void setSmjmc(String smjmc)
	{
		this.smjmc = smjmc;
	}
	@Column
	public String getJcdwcode() {
		return jcdwcode;
	}

	public void setJcdwcode(String jcdwcode) {
		this.jcdwcode = jcdwcode;
	}
	@Column
	public String getJcdwname() {
		return jcdwname;
	}

	public void setJcdwname(String jcdwname) {
		this.jcdwname = jcdwname;
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
