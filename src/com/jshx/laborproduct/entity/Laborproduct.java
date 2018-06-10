package com.jshx.laborproduct.entity;

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
@Table(name="LABORPRODUCT")
public class Laborproduct extends BaseModel
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
	 * 所在镇
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
	/**
	 * 公司id
	 */
	private String comId;
	/**
	 * 附件上传通用关联的模块中记录的id
	 */
	private String linkid;
	/**
	 *  岗位名称
	 */
	private String gwmc;
	/**
	 * 普通防护服期限
	 */
	private String ptfhqx;
	/**
	 * 普通工作帽
	 */
	private String gzmqx;
	/**
	 * 普通工作鞋
	 */
	private String gzxqx;
	/**
	 * 劳动防护手套
	 */
	private String ldstqx;
	/**
	 * 防寒服
	 */
	private String fhfqx;
	/**
	 * 雨衣
	 */
	private String yyqx;
	/**
	 * 胶鞋
	 */
	private String jxqx;
	/**
	 * 防噪声耳塞
	 */
	private String fzesqx;
	/**
	 * 防护足趾安全鞋
	 */
	private String zcaqxqx;
	/**
	 * 防刺穿鞋
	 */
	private String fccxqx;
	/**
	 * 电绝缘鞋
	 */
	private String djyxqx;
	/**
	 * 防静电鞋
	 */
	private String fjdxqx;
	/**
	 * 耐酸碱皮鞋
	 */
	private String nsjpxqx;
	/**
	 * 耐酸碱胶靴
	 */
	private String nsjjxqx;
	/**
	 * 胶面防砸安全靴
	 */
	private String jmxqx;
	/**
	 * 防静电工作服
	 */
	private String jdgzfqx;
	/**
	 * 防酸工作服
	 */
	private String fsgzfqx;
	/**
	 * 阻燃防护服
	 */
	private String zrfhfqx;
	/**
	 * 安全带
	 */
	private String aqdqx;
	/**
	 * 安全帽
	 */
	private String aqmqx;
	/**
	 * 焊接眼面防护具
	 */
	private String hjmjqx;
	/**
	 * 防冲击眼护具
	 */
	private String fcjyqx;
	/**
	 * 防尘口罩
	 */
	private String fckz;
	/**
	 * 过滤式防毒面具
	 */
	private String fdmjqx;
	/**
	 * 其他
	 */
	private String qita;
	
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

	@Column(name="GWMC")
	public String getGwmc()
	{
		return this.gwmc;
	}

	public void setGwmc(String gwmc)
	{
		this.gwmc = gwmc;
	}

	@Column(name="PTFHQX")
	public String getPtfhqx()
	{
		return this.ptfhqx;
	}

	public void setPtfhqx(String ptfhqx)
	{
		this.ptfhqx = ptfhqx;
	}

	@Column(name="GZMQX")
	public String getGzmqx()
	{
		return this.gzmqx;
	}

	public void setGzmqx(String gzmqx)
	{
		this.gzmqx = gzmqx;
	}

	@Column(name="GZXQX")
	public String getGzxqx()
	{
		return this.gzxqx;
	}

	public void setGzxqx(String gzxqx)
	{
		this.gzxqx = gzxqx;
	}

	@Column(name="LDSTQX")
	public String getLdstqx()
	{
		return this.ldstqx;
	}

	public void setLdstqx(String ldstqx)
	{
		this.ldstqx = ldstqx;
	}

	@Column(name="FHFQX")
	public String getFhfqx()
	{
		return this.fhfqx;
	}

	public void setFhfqx(String fhfqx)
	{
		this.fhfqx = fhfqx;
	}

	@Column(name="YYQX")
	public String getYyqx()
	{
		return this.yyqx;
	}

	public void setYyqx(String yyqx)
	{
		this.yyqx = yyqx;
	}

	@Column(name="JXQX")
	public String getJxqx()
	{
		return this.jxqx;
	}

	public void setJxqx(String jxqx)
	{
		this.jxqx = jxqx;
	}

	@Column(name="FZESQX")
	public String getFzesqx()
	{
		return this.fzesqx;
	}

	public void setFzesqx(String fzesqx)
	{
		this.fzesqx = fzesqx;
	}

	@Column(name="ZCAQXQX")
	public String getZcaqxqx()
	{
		return this.zcaqxqx;
	}

	public void setZcaqxqx(String zcaqxqx)
	{
		this.zcaqxqx = zcaqxqx;
	}

	@Column(name="FCCXQX")
	public String getFccxqx()
	{
		return this.fccxqx;
	}

	public void setFccxqx(String fccxqx)
	{
		this.fccxqx = fccxqx;
	}

	@Column(name="DJYXQX")
	public String getDjyxqx()
	{
		return this.djyxqx;
	}

	public void setDjyxqx(String djyxqx)
	{
		this.djyxqx = djyxqx;
	}

	@Column(name="FJDXQX")
	public String getFjdxqx()
	{
		return this.fjdxqx;
	}

	public void setFjdxqx(String fjdxqx)
	{
		this.fjdxqx = fjdxqx;
	}

	@Column(name="NSJPXQX")
	public String getNsjpxqx()
	{
		return this.nsjpxqx;
	}

	public void setNsjpxqx(String nsjpxqx)
	{
		this.nsjpxqx = nsjpxqx;
	}

	@Column(name="NSJJXQX")
	public String getNsjjxqx()
	{
		return this.nsjjxqx;
	}

	public void setNsjjxqx(String nsjjxqx)
	{
		this.nsjjxqx = nsjjxqx;
	}

	@Column(name="JMXQX")
	public String getJmxqx()
	{
		return this.jmxqx;
	}

	public void setJmxqx(String jmxqx)
	{
		this.jmxqx = jmxqx;
	}

	@Column(name="JDGZFQX")
	public String getJdgzfqx()
	{
		return this.jdgzfqx;
	}

	public void setJdgzfqx(String jdgzfqx)
	{
		this.jdgzfqx = jdgzfqx;
	}

	@Column(name="FSGZFQX")
	public String getFsgzfqx()
	{
		return this.fsgzfqx;
	}

	public void setFsgzfqx(String fsgzfqx)
	{
		this.fsgzfqx = fsgzfqx;
	}

	@Column(name="ZRFHFQX")
	public String getZrfhfqx()
	{
		return this.zrfhfqx;
	}

	public void setZrfhfqx(String zrfhfqx)
	{
		this.zrfhfqx = zrfhfqx;
	}

	@Column(name="AQDQX")
	public String getAqdqx()
	{
		return this.aqdqx;
	}

	public void setAqdqx(String aqdqx)
	{
		this.aqdqx = aqdqx;
	}

	@Column(name="AQMQX")
	public String getAqmqx()
	{
		return this.aqmqx;
	}

	public void setAqmqx(String aqmqx)
	{
		this.aqmqx = aqmqx;
	}

	@Column(name="HJMJQX")
	public String getHjmjqx()
	{
		return this.hjmjqx;
	}

	public void setHjmjqx(String hjmjqx)
	{
		this.hjmjqx = hjmjqx;
	}

	@Column(name="FCJYQX")
	public String getFcjyqx()
	{
		return this.fcjyqx;
	}

	public void setFcjyqx(String fcjyqx)
	{
		this.fcjyqx = fcjyqx;
	}

	@Column(name="FCKZ")
	public String getFckz()
	{
		return this.fckz;
	}

	public void setFckz(String fckz)
	{
		this.fckz = fckz;
	}

	@Column(name="FDMJQX")
	public String getFdmjqx()
	{
		return this.fdmjqx;
	}

	public void setFdmjqx(String fdmjqx)
	{
		this.fdmjqx = fdmjqx;
	}

	@Column(name="QITA")
	public String getQita()
	{
		return this.qita;
	}

	public void setQita(String qita)
	{
		this.qita = qita;
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
