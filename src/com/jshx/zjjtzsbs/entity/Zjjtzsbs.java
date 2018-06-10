package com.jshx.zjjtzsbs.entity;

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
@Table(name="ZJJTZSBS")
public class Zjjtzsbs extends BaseModel
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
	 * 注册代码
	 */
	private String zcdm;
	
	/**
	 * 注册登记日期
	 */
	private String zcdjrq;
	
	/**
	 * 设备档案号
	 */
	private String sbdah;
	
	/**
	 * 设备类别
	 */
	private String sblb;
	
	/**
	 * 新设备类别
	 */
	private String xsblb;
	
	/**
	 * 单位名称
	 */
	private String qymc;
	
	/**
	 * 所属乡镇名称
	 */
	private String szzname;

	/**
	 * 设备所在地点
	 */
	private String szdd;
	
	/**
	 * 设备状态
	 */
	private String sbzt;
	
	/**
	 * 单位联系人
	 */
	private String dwlxr;

	/**
	 * 电话
	 */
	private String dh;
	
	/**
	 * 使用单位地址
	 */
	private String dwdz;
	
	/**
	 * 出厂日期
	 */
	private String ccrq;
	
	/**
	 * 出厂编号
	 */
	private String ccbh;

	/**
	 * 检验日期
	 */
	private String jyrq;
	
	/**
	 * 下次检验日期
	 */
	private String xcjyrq;

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

	
	@Column(name="DWDZ")
	public String getDwdz()
	{
		return this.dwdz;
	}

	public void setDwdz(String dwdz)
	{
		this.dwdz = dwdz;
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

	@Column(name="SBLB")
	public String getSblb()
	{
		return this.sblb;
	}

	public void setSblb(String sblb)
	{
		this.sblb = sblb;
	}

	@Column(name="SBDAH")
	public String getSbdah()
	{
		return this.sbdah;
	}

	public void setSbdah(String sbdah)
	{
		this.sbdah = sbdah;
	}

	@Column(name="ZCDM")
	public String getZcdm()
	{
		return this.zcdm;
	}

	public void setZcdm(String zcdm)
	{
		this.zcdm = zcdm;
	}

	@Column(name="JYRQ")
	public String getJyrq()
	{
		return this.jyrq;
	}

	public void setJyrq(String jyrq)
	{
		this.jyrq = jyrq;
	}

	@Column(name="DWLXR")
	public String getDwlxr()
	{
		return this.dwlxr;
	}

	public void setDwlxr(String dwlxr)
	{
		this.dwlxr = dwlxr;
	}

	@Column(name="DH")
	public String getDh()
	{
		return this.dh;
	}

	public void setDh(String dh)
	{
		this.dh = dh;
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
	public String getZcdjrq() {
		return zcdjrq;
	}

	public void setZcdjrq(String zcdjrq) {
		this.zcdjrq = zcdjrq;
	}
	@Column
	public String getXsblb() {
		return xsblb;
	}

	public void setXsblb(String xsblb) {
		this.xsblb = xsblb;
	}
	@Column
	public String getSzdd() {
		return szdd;
	}

	public void setSzdd(String szdd) {
		this.szdd = szdd;
	}
	@Column
	public String getSbzt() {
		return sbzt;
	}

	public void setSbzt(String sbzt) {
		this.sbzt = sbzt;
	}
	@Column
	public String getCcrq() {
		return ccrq;
	}

	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	@Column
	public String getCcbh() {
		return ccbh;
	}

	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	@Column
	public String getXcjyrq() {
		return xcjyrq;
	}

	public void setXcjyrq(String xcjyrq) {
		this.xcjyrq = xcjyrq;
	}
}
