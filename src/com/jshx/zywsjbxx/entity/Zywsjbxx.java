package com.jshx.zywsjbxx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author 高强
 *  createtime 13年11月20
 *  desc 职业卫生基本信息
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ZYWSJBXX")
public class Zywsjbxx extends BaseModel
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
	 * 所在镇
	 */
	private String szzname;

	/**
	 * 所在镇id
	 */
	private String szzid;

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
	
	private String tbbm;
	
	private String tbr;

	/**
	 * 女职工人数
	 */
	private String nzgs;

	/**
	 * 接触职业病危害因素人数
	 */
	private String jcrs;

	/**
	 * 企业职业病危害类别
	 */
	private String whlb;

	/**
	 * 职业病危害行业类别
	 */
	private String hylb;

	/**
	 * 接触职业病危害因素女工人数
	 */
	private String jcngrs;

	/**
	 * 历年职业病人数
	 */
	private String lnzgrs;

	/**
	 * 是否为年度专项整治企业
	 */
	private String sfzzqy;

	/**
	 * 职业病危害岗位数
	 */
	private String gws;

	/**
	 * 联系电话
	 */
	private String lxdh;

	/**
	 * 职业卫生分管负责人姓名
	 */
	private String fgxm;

	/**
	 * 职业卫生分管负责人职务
	 */
	private String fgzw;

	/**
	 * 职业卫生分管负责人办公电话
	 */
	private String fgdh;

	/**
	 * 职业卫生分管负责人手机
	 */
	private String fgsj;

	/**
	 * 职业卫生分管负责人文化程度
	 */
	private String fgwhcd;

	/**
	 * 职业卫生分管负责人专业
	 */
	private String fgzy;

	/**
	 * 职业卫生管理机构负责人姓名
	 */
	private String fzrxm;

	/**
	 * 职业卫生管理机构负责人职务
	 */
	private String fzrzw;

	/**
	 * 职业卫生管理机构负责人办公电话
	 */
	private String fzrdh;

	/**
	 * 职业卫生管理机构负责人手机
	 */
	private String fzrsj;

	/**
	 * 职业卫生管理机构负责人文化程度
	 */
	private String fzrwhcd;

	/**
	 * 职业卫生管理机构负责人专业
	 */
	private String fzrzy;


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

	@Column(name="SZZID")
	public String getSzzid()
	{
		return this.szzid;
	}

	public void setSzzid(String szzid)
	{
		this.szzid = szzid;
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

	@Column(name="NZGS")
	public String getNzgs()
	{
		return this.nzgs;
	}

	public void setNzgs(String nzgs)
	{
		this.nzgs = nzgs;
	}

	@Column(name="JCRS")
	public String getJcrs()
	{
		return this.jcrs;
	}

	public void setJcrs(String jcrs)
	{
		this.jcrs = jcrs;
	}

	@Column(name="WHLB")
	public String getWhlb()
	{
		return this.whlb;
	}

	public void setWhlb(String whlb)
	{
		this.whlb = whlb;
	}

	@Column(name="HYLB")
	public String getHylb()
	{
		return this.hylb;
	}

	public void setHylb(String hylb)
	{
		this.hylb = hylb;
	}

	@Column(name="JCNGRS")
	public String getJcngrs()
	{
		return this.jcngrs;
	}

	public void setJcngrs(String jcngrs)
	{
		this.jcngrs = jcngrs;
	}

	@Column(name="LNZGRS")
	public String getLnzgrs()
	{
		return this.lnzgrs;
	}

	public void setLnzgrs(String lnzgrs)
	{
		this.lnzgrs = lnzgrs;
	}

	@Column(name="SFZZQY")
	public String getSfzzqy()
	{
		return this.sfzzqy;
	}

	public void setSfzzqy(String sfzzqy)
	{
		this.sfzzqy = sfzzqy;
	}

	@Column(name="GWS")
	public String getGws()
	{
		return this.gws;
	}

	public void setGws(String gws)
	{
		this.gws = gws;
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

	@Column(name="FGXM")
	public String getFgxm()
	{
		return this.fgxm;
	}

	public void setFgxm(String fgxm)
	{
		this.fgxm = fgxm;
	}

	@Column(name="FGZW")
	public String getFgzw()
	{
		return this.fgzw;
	}

	public void setFgzw(String fgzw)
	{
		this.fgzw = fgzw;
	}

	@Column(name="FGDH")
	public String getFgdh()
	{
		return this.fgdh;
	}

	public void setFgdh(String fgdh)
	{
		this.fgdh = fgdh;
	}

	@Column(name="FGSJ")
	public String getFgsj()
	{
		return this.fgsj;
	}

	public void setFgsj(String fgsj)
	{
		this.fgsj = fgsj;
	}

	@Column(name="FGWHCD")
	public String getFgwhcd()
	{
		return this.fgwhcd;
	}

	public void setFgwhcd(String fgwhcd)
	{
		this.fgwhcd = fgwhcd;
	}

	@Column(name="FGZY")
	public String getFgzy()
	{
		return this.fgzy;
	}

	public void setFgzy(String fgzy)
	{
		this.fgzy = fgzy;
	}

	@Column(name="FZRXM")
	public String getFzrxm()
	{
		return this.fzrxm;
	}

	public void setFzrxm(String fzrxm)
	{
		this.fzrxm = fzrxm;
	}

	@Column(name="FZRZW")
	public String getFzrzw()
	{
		return this.fzrzw;
	}

	public void setFzrzw(String fzrzw)
	{
		this.fzrzw = fzrzw;
	}

	@Column(name="FZRDH")
	public String getFzrdh()
	{
		return this.fzrdh;
	}

	public void setFzrdh(String fzrdh)
	{
		this.fzrdh = fzrdh;
	}

	@Column(name="FZRSJ")
	public String getFzrsj()
	{
		return this.fzrsj;
	}

	public void setFzrsj(String fzrsj)
	{
		this.fzrsj = fzrsj;
	}

	@Column(name="FZRWHCD")
	public String getFzrwhcd()
	{
		return this.fzrwhcd;
	}

	public void setFzrwhcd(String fzrwhcd)
	{
		this.fzrwhcd = fzrwhcd;
	}

	@Column(name="FZRZY")
	public String getFzrzy()
	{
		return this.fzrzy;
	}

	public void setFzrzy(String fzrzy)
	{
		this.fzrzy = fzrzy;
	}
	@Column
	public String getTbbm() {
		return tbbm;
	}

	public void setTbbm(String tbbm) {
		this.tbbm = tbbm;
	}
	@Column
	public String getTbr() {
		return tbr;
	}

	public void setTbr(String tbr) {
		this.tbr = tbr;
	}

}
