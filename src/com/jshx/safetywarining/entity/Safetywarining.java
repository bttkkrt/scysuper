package com.jshx.safetywarining.entity;

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
@Table(name="SAFETYWARINING")
public class Safetywarining extends BaseModel
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
	 * 附件上传通用关联的模块中记录的id
	 */
	private String linkid;
	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * 公司id
	 */
	private String comId;
	/**
	 * 所在镇
	 */
	private String szz;
	/**
	 * 危险岗位名称
	 */
	private String wxmc;
	/**
	 * 危险有害因素
	 */
	private String wxys;
	/**
	 * 警示标志总数
	 */
	private String jsnum;
	/**
	 * 禁止标志数
	 */
	private String jznum;
	/**
	 * 警告标志数
	 */
	private String jgnum;
	/**
	 * 指示灯标志数
	 */
	private String zsdnum;
	/**
	 * 提示标志数
	 */
	private String tsnum;
	/**
	 * 告知卡数量
	 */
	private String gznum;
	/**
	 * 安全操作规程数量
	 */
	private String aqnum;
	/**
	 * 上传时间
	 */
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

	@Column(name="SZZ")
	public String getSzz()
	{
		return this.szz;
	}

	public void setSzz(String szz)
	{
		this.szz = szz;
	}

	@Column(name="WXMC")
	public String getWxmc()
	{
		return this.wxmc;
	}

	public void setWxmc(String wxmc)
	{
		this.wxmc = wxmc;
	}

	@Column(name="WXYS")
	public String getWxys()
	{
		return this.wxys;
	}

	public void setWxys(String wxys)
	{
		this.wxys = wxys;
	}

	@Column(name="JSNUM")
	public String getJsnum()
	{
		return this.jsnum;
	}

	public void setJsnum(String jsnum)
	{
		this.jsnum = jsnum;
	}

	@Column(name="JZNUM")
	public String getJznum()
	{
		return this.jznum;
	}

	public void setJznum(String jznum)
	{
		this.jznum = jznum;
	}

	@Column(name="JGNUM")
	public String getJgnum()
	{
		return this.jgnum;
	}

	public void setJgnum(String jgnum)
	{
		this.jgnum = jgnum;
	}

	@Column(name="ZSDNUM")
	public String getZsdnum()
	{
		return this.zsdnum;
	}

	public void setZsdnum(String zsdnum)
	{
		this.zsdnum = zsdnum;
	}

	@Column(name="TSNUM")
	public String getTsnum()
	{
		return this.tsnum;
	}

	public void setTsnum(String tsnum)
	{
		this.tsnum = tsnum;
	}

	@Column(name="GZNUM")
	public String getGznum()
	{
		return this.gznum;
	}

	public void setGznum(String gznum)
	{
		this.gznum = gznum;
	}

	@Column(name="AQNUM")
	public String getAqnum()
	{
		return this.aqnum;
	}

	public void setAqnum(String aqnum)
	{
		this.aqnum = aqnum;
	}

	@Column(name="UPLOADTIME")
	public String getUploadtime()
	{
		return this.uploadtime;
	}

	public void setUploadtime(String uploadtime)
	{
		this.uploadtime = uploadtime;
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

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
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
