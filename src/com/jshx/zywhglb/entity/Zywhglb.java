package com.jshx.zywhglb.entity;

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
@Table(name="ZYWHGLB")
public class Zywhglb extends BaseModel
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
	 * 企业规模
	 */
	private String qygm;

	/**
	 * 职业卫生id
	 */
	private String zywsid;

	/**
	 * 职业病危害因素id
	 */
	private String zywhid;

	/**
	 * 职业病危害因素名称
	 */
	private String zywhmc;

	/**
	 * 接触人数
	 */
	private String jcrs;

	/**
	 * 检测点数
	 */
	private String jcds;

	/**
	 * 达标点数
	 */
	private String dbds;
	
	private String type;
	
	private String tjnf;

	
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

	@Column(name="ZYWSID")
	public String getZywsid()
	{
		return this.zywsid;
	}

	public void setZywsid(String zywsid)
	{
		this.zywsid = zywsid;
	}

	@Column(name="ZYWHID")
	public String getZywhid()
	{
		return this.zywhid;
	}

	public void setZywhid(String zywhid)
	{
		this.zywhid = zywhid;
	}

	@Column(name="ZYWHMC")
	public String getZywhmc()
	{
		return this.zywhmc;
	}

	public void setZywhmc(String zywhmc)
	{
		this.zywhmc = zywhmc;
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

	@Column(name="JCDS")
	public String getJcds()
	{
		return this.jcds;
	}

	public void setJcds(String jcds)
	{
		this.jcds = jcds;
	}

	@Column(name="DBDS")
	public String getDbds()
	{
		return this.dbds;
	}

	public void setDbds(String dbds)
	{
		this.dbds = dbds;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name="TJNF")
	public String getTjnf() {
		return tjnf;
	}

	public void setTjnf(String tjnf) {
		this.tjnf = tjnf;
	}

}
