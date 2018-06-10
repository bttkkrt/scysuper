package com.jshx.zjjtzsbzyry.entity;

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
@Table(name="ZJJTZSBZYRY")
public class Zjjtzsbzyry extends BaseModel
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
	 * 姓名
	 */
	private String mc;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 乡镇
	 */
	private String szzname;
	
	
	/**
	 * 文化程度
	 */
	private String xl;
	
	/**
	 * 身份证
	 */
	private String sfz;
	
	/**
	 * 联系电话
	 */
	private String lxdh;

	/**
	 * 证书类型
	 */
	private String xm;
	
	/**
	 * 用人单位
	 */
	private String pydw;
	
	/**
	 * 单位地址
	 */
	private String dwdz;
	
	/**
	 * 单位联系电话
	 */
	private String dwlxdh;
	
	/**
	 * 首次领证日期
	 */
	private String pzrq;

	/**
	 * 当前证书有效期
	 */
	private String yxrq;

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

	
	@Column(name="MC")
	public String getMc()
	{
		return this.mc;
	}

	public void setMc(String mc)
	{
		this.mc = mc;
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

	@Column(name="XM")
	public String getXm()
	{
		return this.xm;
	}

	public void setXm(String xm)
	{
		this.xm = xm;
	}

	@Column(name="PZRQ")
	public String getPzrq()
	{
		return this.pzrq;
	}

	public void setPzrq(String pzrq)
	{
		this.pzrq = pzrq;
	}

	@Column(name="YXRQ")
	public String getYxrq()
	{
		return this.yxrq;
	}

	public void setYxrq(String yxrq)
	{
		this.yxrq = yxrq;
	}

	@Column(name="PYDW")
	public String getPydw()
	{
		return this.pydw;
	}

	public void setPydw(String pydw)
	{
		this.pydw = pydw;
	}

	@Column(name="LXDH")
	public String getLxdh()
	{
		return this.lxdh;
	}

	public void setLxdh(String lxdh)
	{
		this.lxdh = lxdh;
	}
	@Column
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column
	public String getSzzname() {
		return szzname;
	}

	public void setSzzname(String szzname) {
		this.szzname = szzname;
	}
	@Column
	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}
	@Column
	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	@Column
	public String getDwlxdh() {
		return dwlxdh;
	}

	public void setDwlxdh(String dwlxdh) {
		this.dwlxdh = dwlxdh;
	}

}
