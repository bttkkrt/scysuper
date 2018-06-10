package com.jshx.zazPxb.entity;

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
@Table(name="JSHX_ZAZ_PXB")
public class JshxZazPxb extends BaseModel
{
	/**
	 * 新增字段 地址 address 
	 */
	private String address;
	/**
	 * 新增 初培时间 cpsj
	 */
	private String cpsj;
	/**
	 * 新增字段 备注 bz
	 */
	private String bz;
	
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 所在镇名称
	 */
	private String szzname;

	/**
	 * 企业名称
	 */
	private String qymc;

	/**
	 * 姓名
	 */
	private String personName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 身份证
	 */
	private String sfz;

	/**
	 * 联系方式
	 */
	private String lxfs;

	/**
	 * 职务
	 */
	private String zw;

	/**
	 * 学历
	 */
	private String xl;

	/**
	 * 考试成绩
	 */
	private String kscj;

	/**
	 * 培训证号
	 */
	private String pxzh;
	
	private String whpqylx;

	
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


	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
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

	@Column(name="SFZ")
	public String getSfz()
	{
		return this.sfz;
	}

	public void setSfz(String sfz)
	{
		this.sfz = sfz;
	}

	@Column(name="LXFS")
	public String getLxfs()
	{
		return this.lxfs;
	}

	public void setLxfs(String lxfs)
	{
		this.lxfs = lxfs;
	}

	@Column(name="ZW")
	public String getZw()
	{
		return this.zw;
	}

	public void setZw(String zw)
	{
		this.zw = zw;
	}

	@Column(name="XL")
	public String getXl()
	{
		return this.xl;
	}

	public void setXl(String xl)
	{
		this.xl = xl;
	}


	@Column(name="KSCJ")
	public String getKscj()
	{
		return this.kscj;
	}

	public void setKscj(String kscj)
	{
		this.kscj = kscj;
	}

	@Column(name="PXZH")
	public String getPxzh()
	{
		return this.pxzh;
	}

	public void setPxzh(String pxzh)
	{
		this.pxzh = pxzh;
	}

	@Column
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column
	public String getCpsj() {
		return cpsj;
	}

	public void setCpsj(String cpsj) {
		this.cpsj = cpsj;
	}
	@Column
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	@Column
	public String getWhpqylx() {
		return whpqylx;
	}

	public void setWhpqylx(String whpqylx) {
		this.whpqylx = whpqylx;
	}
	
}
