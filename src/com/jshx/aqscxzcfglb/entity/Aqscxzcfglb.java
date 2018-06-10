package com.jshx.aqscxzcfglb.entity;

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
@Table(name="AQSCXZCFGLB")
public class Aqscxzcfglb extends BaseModel
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
	 * 行政处罚次数
	 */
	private String xzcfcs;

	/**
	 * 行政处罚对生产经营单位
	 */
	private String scjydwcs;

	/**
	 * 行政处罚对生产经营单位主要负责人
	 */
	private String zyfzrcs;

	/**
	 * 责令停产停业整顿生产经营单位
	 */
	private String zltcgs;

	/**
	 * 请关闭生产经营单位个数
	 */
	private String tqgbgs;

	/**
	 * 实际关闭生产经营单位
	 */
	private String sjgbgs;

	/**
	 * 暂扣安全生产许可证企业
	 */
	private String zkgs;

	/**
	 * 吊销安全生产许可证企业
	 */
	private String dxgs;

	/**
	 * 关联id
	 */
	private String proid;

	/**
	 * 类型
	 */
	private String linktype;

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

	
	@Column(name="XZCFCS")
	public String getXzcfcs()
	{
		return this.xzcfcs;
	}

	public void setXzcfcs(String xzcfcs)
	{
		if(xzcfcs == null || "".equals(xzcfcs))
		{
			this.xzcfcs = "0";
		}
		else
		{
			this.xzcfcs = xzcfcs;
		}
	}

	@Column(name="SCJYDWCS")
	public String getScjydwcs()
	{
		return this.scjydwcs;
	}

	public void setScjydwcs(String scjydwcs)
	{
		if(scjydwcs == null || "".equals(scjydwcs))
		{
			this.scjydwcs = "0";
		}
		else
		{
			this.scjydwcs = scjydwcs;
		}
	}

	@Column(name="ZYFZRCS")
	public String getZyfzrcs()
	{
		return this.zyfzrcs;
	}

	public void setZyfzrcs(String zyfzrcs)
	{
		if(zyfzrcs == null || "".equals(zyfzrcs))
		{
			this.zyfzrcs = "0";
		}
		else
		{
			this.zyfzrcs = zyfzrcs;
		}
	}

	@Column(name="ZLTCGS")
	public String getZltcgs()
	{
		return this.zltcgs;
	}

	public void setZltcgs(String zltcgs)
	{
		if(zltcgs == null || "".equals(zltcgs))
		{
			this.zltcgs = "0";
		}
		else
		{
			this.zltcgs = zltcgs;
		}
	}

	@Column(name="TQGBGS")
	public String getTqgbgs()
	{
		return this.tqgbgs;
	}

	public void setTqgbgs(String tqgbgs)
	{
		if(tqgbgs == null || "".equals(tqgbgs))
		{
			this.tqgbgs = "0";
		}
		else
		{
			this.tqgbgs = tqgbgs;
		}
	}

	@Column(name="SJGBGS")
	public String getSjgbgs()
	{
		return this.sjgbgs;
	}

	public void setSjgbgs(String sjgbgs)
	{
		if(sjgbgs == null || "".equals(sjgbgs))
		{
			this.sjgbgs = "0";
		}
		else
		{
			this.sjgbgs = sjgbgs;
		}
	}

	@Column(name="ZKGS")
	public String getZkgs()
	{
		return this.zkgs;
	}

	public void setZkgs(String zkgs)
	{
		if(zkgs == null || "".equals(zkgs))
		{
			this.zkgs = "0";
		}
		else
		{
			this.zkgs = zkgs;
		}
	}

	@Column(name="DXGS")
	public String getDxgs()
	{
		return this.dxgs;
	}

	public void setDxgs(String dxgs)
	{
		if(dxgs == null || "".equals(dxgs))
		{
			this.dxgs = "0";
		}
		else
		{
			this.dxgs = dxgs;
		}
	}

	@Column(name="PROID")
	public String getProid()
	{
		return this.proid;
	}

	public void setProid(String proid)
	{
		this.proid = proid;
	}

	@Column(name="LINKTYPE")
	public String getLinktype()
	{
		return this.linktype;
	}

	public void setLinktype(String linktype)
	{
		this.linktype = linktype;
	}
}
