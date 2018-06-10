package com.jshx.zycsjcry.entity;

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
@Table(name="ZYCSJCRY")
public class Zycsjcry extends BaseModel
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
	 * 健康监护删除标记
	 */
	private Integer delFlags;

	
	/**
	 * 所在镇id
	 */
	private String szzid;

	/**
	 * 所在镇
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
	 * 企业类型
	 */
	private String qylx;

	/**
	 * 行业分类
	 */
	private String hyfl;

	/**
	 * 企业注册类型
	 */
	private String qyzclx;

	/**
	 * 体检类型
	 */
	private String tjlx;

	/**
	 * 身份证
	 */
	private String sfz;

	/**
	 * 性别
	 */
	private String xb;

	/**
	 * 姓名
	 */
	private String xm;

	/**
	 * 上岗时间
	 */
	private String sgsj;

	/**
	 * 体检日期
	 */
	private String tjrq;

	/**
	 * 体检机构
	 */
	private String tjjg;

	/**
	 * 体检结果
	 */
	private String tjjguo;

	/**
	 * 作业场所id
	 */
	private String zycsid;
	
	private String szc;
	
	private String szcname;
	
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

	@Column
	public Integer getDelFlags()
	{
		return delFlags;
	}

	public void setDelFlags(Integer delFlags)
	{
		this.delFlags = delFlags;
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

	@Column(name="QYLX")
	public String getQylx()
	{
		return this.qylx;
	}

	public void setQylx(String qylx)
	{
		this.qylx = qylx;
	}

	@Column(name="HYFL")
	public String getHyfl()
	{
		return this.hyfl;
	}

	public void setHyfl(String hyfl)
	{
		this.hyfl = hyfl;
	}

	@Column(name="QYZCLX")
	public String getQyzclx()
	{
		return this.qyzclx;
	}

	public void setQyzclx(String qyzclx)
	{
		this.qyzclx = qyzclx;
	}

	@Column(name="TJLX")
	public String getTjlx()
	{
		return this.tjlx;
	}

	public void setTjlx(String tjlx)
	{
		this.tjlx = tjlx;
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

	@Column(name="XB")
	public String getXb()
	{
		return this.xb;
	}

	public void setXb(String xb)
	{
		this.xb = xb;
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

	@Column(name="SGSJ")
	public String getSgsj()
	{
		return this.sgsj;
	}

	public void setSgsj(String sgsj)
	{
		this.sgsj = sgsj;
	}

	@Column(name="TJRQ")
	public String getTjrq()
	{
		return this.tjrq;
	}

	public void setTjrq(String tjrq)
	{
		this.tjrq = tjrq;
	}

	@Column(name="TJJG")
	public String getTjjg()
	{
		return this.tjjg;
	}

	public void setTjjg(String tjjg)
	{
		this.tjjg = tjjg;
	}

	@Column(name="TJJGUO")
	public String getTjjguo()
	{
		return this.tjjguo;
	}

	public void setTjjguo(String tjjguo)
	{
		this.tjjguo = tjjguo;
	}

	@Column(name="ZYCSID")
	public String getZycsid()
	{
		return this.zycsid;
	}

	public void setZycsid(String zycsid)
	{
		this.zycsid = zycsid;
	}

}
