package com.jshx.whpglb.entity;

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
@Table(name="WHPGLB")
public class Whpglb extends BaseModel
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
	 * 重大危险源id
	 */
	private String zdwxyid;

	/**
	 * 危险化学品名称
	 */
	private String wxhxpmc;

	/**
	 * 危险性类别
	 */
	private String wxxlb;

	/**
	 * UN编号
	 */
	private String unbh;

	/**
	 * 生产用途
	 */
	private String scyt;

	/**
	 * 生产工艺
	 */
	private String scgy;

	/**
	 * 单个最大容器物理状态
	 */
	private String wlzt;

	/**
	 * 单个最大容器操作温度
	 */
	private String czwd;

	/**
	 * 单个最大容器操作压力
	 */
	private String czyl;

	/**
	 * 单个最大容器存量
	 */
	private String zdrqcl;

	/**
	 * 单元内危险化学品存量
	 */
	private String wxhxpcl;

	/**
	 * 临界量
	 */
	private String ljl;

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

	@Column(name="QYGM")
	public String getQygm()
	{
		return this.qygm;
	}

	public void setQygm(String qygm)
	{
		this.qygm = qygm;
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

	@Column(name="ZDWXYID")
	public String getZdwxyid()
	{
		return this.zdwxyid;
	}

	public void setZdwxyid(String zdwxyid)
	{
		this.zdwxyid = zdwxyid;
	}

	@Column(name="WXHXPMC")
	public String getWxhxpmc()
	{
		return this.wxhxpmc;
	}

	public void setWxhxpmc(String wxhxpmc)
	{
		this.wxhxpmc = wxhxpmc;
	}

	@Column(name="WXXLB")
	public String getWxxlb()
	{
		return this.wxxlb;
	}

	public void setWxxlb(String wxxlb)
	{
		this.wxxlb = wxxlb;
	}

	@Column(name="UNBH")
	public String getUnbh()
	{
		return this.unbh;
	}

	public void setUnbh(String unbh)
	{
		this.unbh = unbh;
	}

	@Column(name="SCYT")
	public String getScyt()
	{
		return this.scyt;
	}

	public void setScyt(String scyt)
	{
		this.scyt = scyt;
	}

	@Column(name="SCGY")
	public String getScgy()
	{
		return this.scgy;
	}

	public void setScgy(String scgy)
	{
		this.scgy = scgy;
	}

	@Column(name="WLZT")
	public String getWlzt()
	{
		return this.wlzt;
	}

	public void setWlzt(String wlzt)
	{
		this.wlzt = wlzt;
	}

	@Column(name="CZWD")
	public String getCzwd()
	{
		return this.czwd;
	}

	public void setCzwd(String czwd)
	{
		this.czwd = czwd;
	}

	@Column(name="CZYL")
	public String getCzyl()
	{
		return this.czyl;
	}

	public void setCzyl(String czyl)
	{
		this.czyl = czyl;
	}

	@Column(name="ZDRQCL")
	public String getZdrqcl()
	{
		return this.zdrqcl;
	}

	public void setZdrqcl(String zdrqcl)
	{
		this.zdrqcl = zdrqcl;
	}

	@Column(name="WXHXPCL")
	public String getWxhxpcl()
	{
		return this.wxhxpcl;
	}

	public void setWxhxpcl(String wxhxpcl)
	{
		this.wxhxpcl = wxhxpcl;
	}

	@Column(name="LJL")
	public String getLjl()
	{
		return this.ljl;
	}

	public void setLjl(String ljl)
	{
		this.ljl = ljl;
	}

}
