package com.jshx.tzsb.entity;

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
@Table(name="JSHX_TZSB")
public class JshxTzsb extends BaseModel
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
	 * 设备位号
	 */
	private String sbwh;
	/**
	 * 设备名称
	 */
	private String sbmc;
	/**
	 * 使用证编号
	 */
	private String syzbh;
	/**
	 * 规格型号
	 */
	private String ggxh;
	/**
	 * 制造单位名称
	 */
	private String zzdwmc;
	/**
	 * 使用状态
	 */
	private String syzt;
	/**
	 * 检测单位
	 */
	private String jcdw;
	/**
	 * 设备安装地点
	 */
	private String sbazdd;
	/**
	 * 上次检测日期
	 */
	private Date scjcrq;
	/**
	 * 下次检验日期
	 */
	private Date xcjcrq;
	/**
	 * 安全责任人
	 */
	private String aqzrr;
	/**
	 * 备注
	 */
	private String bz;
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
	 * 安全附件
	 */
	private String aqfj;
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
	 * 设备类别
	 */
	private String sblx;
	
	/**
	 * 添加操作人员
	 */
	private String operStaff;
	
	/**
	 * 操作人员持证情况
	 */
	private String cerOfOperStaff;
	
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

	@Column(name="SBWH")
	public String getSbwh()
	{
		return this.sbwh;
	}

	public void setSbwh(String sbwh)
	{
		this.sbwh = sbwh;
	}

	@Column(name="SBMC")
	public String getSbmc()
	{
		return this.sbmc;
	}

	public void setSbmc(String sbmc)
	{
		this.sbmc = sbmc;
	}

	@Column(name="SYZBH")
	public String getSyzbh()
	{
		return this.syzbh;
	}

	public void setSyzbh(String syzbh)
	{
		this.syzbh = syzbh;
	}

	@Column(name="GGXH")
	public String getGgxh()
	{
		return this.ggxh;
	}

	public void setGgxh(String ggxh)
	{
		this.ggxh = ggxh;
	}

	@Column(name="ZZDWMC")
	public String getZzdwmc()
	{
		return this.zzdwmc;
	}

	public void setZzdwmc(String zzdwmc)
	{
		this.zzdwmc = zzdwmc;
	}

	@Column(name="SYZT")
	public String getSyzt()
	{
		return this.syzt;
	}

	public void setSyzt(String syzt)
	{
		this.syzt = syzt;
	}

	@Column(name="JCDW")
	public String getJcdw()
	{
		return this.jcdw;
	}

	public void setJcdw(String jcdw)
	{
		this.jcdw = jcdw;
	}

	@Column(name="SBAZDD")
	public String getSbazdd()
	{
		return this.sbazdd;
	}

	public void setSbazdd(String sbazdd)
	{
		this.sbazdd = sbazdd;
	}

	@Column(name="SCJCRQ")
	public Date getScjcrq()
	{
		return this.scjcrq;
	}

	public void setScjcrq(Date scjcrq)
	{
		this.scjcrq = scjcrq;
	}

	@Column(name="XCJCRQ")
	public Date getXcjcrq()
	{
		return this.xcjcrq;
	}

	public void setXcjcrq(Date xcjcrq)
	{
		this.xcjcrq = xcjcrq;
	}

	@Column(name="AQZRR")
	public String getAqzrr()
	{
		return this.aqzrr;
	}

	public void setAqzrr(String aqzrr)
	{
		this.aqzrr = aqzrr;
	}

	@Column(name="BZ")
	public String getBz()
	{
		return this.bz;
	}

	public void setBz(String bz)
	{
		this.bz = bz;
	}
	@Column(name="AQFJ")
	public String getAqfj() {
		return aqfj;
	}

	public void setAqfj(String aqfj) {
		this.aqfj = aqfj;
	}
	@Column(name="SBLX")
	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	@Column(name="CEROFOPERASTAFF")
	public void setCerOfOperStaff(String cerOfOperStaff) {
		this.cerOfOperStaff = cerOfOperStaff;
	}

	public String getCerOfOperStaff() {
		return cerOfOperStaff;
	}

	@Column(name="OPERSTAFF")
	public void setOperStaff(String operStaff) {
		this.operStaff = operStaff;
	}

	public String getOperStaff() {
		return operStaff;
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
