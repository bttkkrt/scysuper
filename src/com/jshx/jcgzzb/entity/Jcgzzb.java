package com.jshx.jcgzzb.entity;

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
@Table(name="JCGZZB")
public class Jcgzzb extends BaseModel
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
	 * 填报单位id
	 */
	private String szzid;
	/**
	 * 填报单位
	 */
	private String szzname;

	/**
	 * 统计开始日期
	 */
	private Date tjkssj;

	/**
	 * 统计结束日期
	 */
	private Date tjjssj;

	/**
	 * 计划执法检查次数
	 */
	private String jhzfjccs;

	/**
	 * 计划执法检查家数
	 */
	private String jhzfjcjs;

	/**
	 * 组织执法检查
	 */
	private String zzzfjc;

	/**
	 * 检查单位
	 */
	private String jcdw;

	/**
	 * 发现安全隐患
	 */
	private String fxaqyh;

	/**
	 * 督促整改隐患
	 */
	private String dczgxx;

	/**
	 * 重大安全隐患
	 */
	private String zdaqyh;

	/**
	 * 整改重大安全隐患
	 */
	private String zgzdaqyh;

	/**
	 * 现场检查记录
	 */
	private String xcjcjl;

	/**
	 * 整改复查意见书
	 */
	private String zgfcyjs;

	/**
	 * 责令限期整改指令书
	 */
	private String zlxqzgzls;

	/**
	 * 强制措施决定书
	 */
	private String qzcsjds;

	/**
	 * 建议实施行政处罚
	 */
	private String jyssxzcf;

	/**
	 * 主要安全隐患
	 */
	private String zyaqyh;

	/**
	 * 其它
	 */
	private String qt;

	/**
	 * 填报人
	 */
	private String tbr;

	/**
	 * 填报日期
	 */
	private Date tbrq;


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
	@Column(name="TJKSSJ")
	public Date getTjkssj()
	{
		return this.tjkssj;
	}

	public void setTjkssj(Date tjkssj)
	{
		this.tjkssj = tjkssj;
	}

	@Column(name="TJJSSJ")
	public Date getTjjssj()
	{
		return this.tjjssj;
	}

	public void setTjjssj(Date tjjssj)
	{
		this.tjjssj = tjjssj;
	}

	@Column(name="JHZFJCCS")
	public String getJhzfjccs()
	{
		return this.jhzfjccs;
	}

	public void setJhzfjccs(String jhzfjccs)
	{
		if(jhzfjccs == null || "".equals(jhzfjccs))
		{
			this.jhzfjccs = "0";
		}
		else
		{
			this.jhzfjccs = jhzfjccs;
		}
	}

	@Column(name="JHZFJCJS")
	public String getJhzfjcjs()
	{
		return this.jhzfjcjs;
	}

	public void setJhzfjcjs(String jhzfjcjs)
	{
		if(jhzfjcjs == null || "".equals(jhzfjcjs))
		{
			this.jhzfjcjs = "0";
		}
		else
		{
			this.jhzfjcjs = jhzfjcjs;
		}
	}

	@Column(name="ZZZFJC")
	public String getZzzfjc()
	{
		return this.zzzfjc;
	}

	public void setZzzfjc(String zzzfjc)
	{
		if(zzzfjc == null || "".equals(zzzfjc))
		{
			this.zzzfjc = "0";
		}
		else
		{
			this.zzzfjc = zzzfjc;
		}
	}

	@Column(name="JCDW")
	public String getJcdw()
	{
		return this.jcdw;
	}

	public void setJcdw(String jcdw)
	{
		if(jcdw == null || "".equals(jcdw))
		{
			this.jcdw = "0";
		}
		else
		{
			this.jcdw = jcdw;
		}
	}

	@Column(name="FXAQYH")
	public String getFxaqyh()
	{
		return this.fxaqyh;
	}

	public void setFxaqyh(String fxaqyh)
	{
		if(fxaqyh == null || "".equals(fxaqyh))
		{
			this.fxaqyh = "0";
		}
		else
		{
			this.fxaqyh = fxaqyh;
		}
	}

	@Column(name="DCZGXX")
	public String getDczgxx()
	{
		return this.dczgxx;
	}

	public void setDczgxx(String dczgxx)
	{
		if(dczgxx == null || "".equals(dczgxx))
		{
			this.dczgxx = "0";
		}
		else
		{
			this.dczgxx = dczgxx;
		}
	}

	@Column(name="ZDAQYH")
	public String getZdaqyh()
	{
		return this.zdaqyh;
	}

	public void setZdaqyh(String zdaqyh)
	{
		if(zdaqyh == null || "".equals(zdaqyh))
		{
			this.zdaqyh = "0";
		}
		else
		{
			this.zdaqyh = zdaqyh;
		}
	}

	@Column(name="ZGZDAQYH")
	public String getZgzdaqyh()
	{
		return this.zgzdaqyh;
	}

	public void setZgzdaqyh(String zgzdaqyh)
	{
		if(zgzdaqyh == null || "".equals(zgzdaqyh))
		{
			this.zgzdaqyh = "0";
		}
		else
		{
			this.zgzdaqyh = zgzdaqyh;
		}
	}

	@Column(name="XCJCJL")
	public String getXcjcjl()
	{
		return this.xcjcjl;
	}

	public void setXcjcjl(String xcjcjl)
	{
		if(xcjcjl == null || "".equals(xcjcjl))
		{
			this.xcjcjl = "0";
		}
		else
		{
			this.xcjcjl = xcjcjl;
		}
	}

	@Column(name="ZGFCYJS")
	public String getZgfcyjs()
	{
		return this.zgfcyjs;
	}

	public void setZgfcyjs(String zgfcyjs)
	{
		if(zgfcyjs == null || "".equals(zgfcyjs))
		{
			this.zgfcyjs = "0";
		}
		else
		{
			this.zgfcyjs = zgfcyjs;
		}
	}

	@Column(name="ZLXQZGZLS")
	public String getZlxqzgzls()
	{
		return this.zlxqzgzls;
	}

	public void setZlxqzgzls(String zlxqzgzls)
	{
		if(zlxqzgzls == null || "".equals(zlxqzgzls))
		{
			this.zlxqzgzls = "0";
		}
		else
		{
			this.zlxqzgzls = zlxqzgzls;
		}
	}

	@Column(name="QZCSJDS")
	public String getQzcsjds()
	{
		return this.qzcsjds;
	}

	public void setQzcsjds(String qzcsjds)
	{
		if(qzcsjds == null || "".equals(qzcsjds))
		{
			this.qzcsjds = "0";
		}
		else
		{
			this.qzcsjds = qzcsjds;
		}
	}

	@Column(name="JYSSXZCF")
	public String getJyssxzcf()
	{
		return this.jyssxzcf;
	}

	public void setJyssxzcf(String jyssxzcf)
	{
		this.jyssxzcf = jyssxzcf;
	}

	@Column(name="ZYAQYH")
	public String getZyaqyh()
	{
		return this.zyaqyh;
	}

	public void setZyaqyh(String zyaqyh)
	{
		this.zyaqyh = zyaqyh;
	}

	@Column(name="QT")
	public String getQt()
	{
		return this.qt;
	}

	public void setQt(String qt)
	{
		this.qt = qt;
	}

	@Column(name="TBR")
	public String getTbr()
	{
		return this.tbr;
	}

	public void setTbr(String tbr)
	{
		this.tbr = tbr;
	}

	@Column(name="TBRQ")
	public Date getTbrq()
	{
		return this.tbrq;
	}

	public void setTbrq(Date tbrq)
	{
		this.tbrq = tbrq;
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

}
