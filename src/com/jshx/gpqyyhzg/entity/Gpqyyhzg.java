package com.jshx.gpqyyhzg.entity;

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
@Table(name="GPQYYHZG")
public class Gpqyyhzg extends BaseModel
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
	 * 政府与村
	 */
	private String zfyc;

	/**
	 * 村与企业
	 */
	private String cyqy;

	/**
	 * 市挂牌数
	 */
	private String sgps;

	/**
	 * 市已整改
	 */
	private String syzg;

	/**
	 * 市整改中
	 */
	private String szgz;

	/**
	 * 市未整改
	 */
	private String swzg;

	/**
	 * 区挂牌数
	 */
	private String qgps;

	/**
	 * 区已整改
	 */
	private String qyzg;

	/**
	 * 区整改中
	 */
	private String yzgz;

	/**
	 * 区未整改
	 */
	private String qwzg;

	/**
	 * 镇挂牌数
	 */
	private String zgps;

	/**
	 * 镇已整改
	 */
	private String zyzg;

	/**
	 * 镇整改中
	 */
	private String zzgz;

	/**
	 * 镇未整改
	 */
	private String zwzg;

	/**
	 * 检查企业数
	 */
	private String jcqys;

	/**
	 * 发现隐患数
	 */
	private String fxyhs;

	/**
	 * 整改隐患数
	 */
	private String zgyhs;

	/**
	 * 年度目标(人)
	 */
	private String ndmb;

	/**
	 * 完成人数（人）
	 */
	private String wcrs;

	/**
	 * 完成率%
	 */
	private String wcl;

	/**
	 * 备注
	 */
	private String bz;

	/**
	 * 统计月份
	 */
	private String tjyf;

	/**
	 * 填报人
	 */
	private String tbr;


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

	@Column(name="ZFYC")
	public String getZfyc()
	{
		return this.zfyc;
	}

	public void setZfyc(String zfyc)
	{
		if(zfyc == null || "".equals(zfyc))
		{
			this.zfyc = "0";
		}
		else
		{
			this.zfyc = zfyc;
		}
	}

	@Column(name="CYQY")
	public String getCyqy()
	{
		return this.cyqy;
	}

	public void setCyqy(String cyqy)
	{
		if(cyqy == null || "".equals(cyqy))
		{
			this.cyqy = "0";
		}
		else
		{
			this.cyqy = cyqy;
		}
	}

	@Column(name="SGPS")
	public String getSgps()
	{
		return this.sgps;
	}

	public void setSgps(String sgps)
	{
		if(sgps == null || "".equals(sgps))
		{
			this.sgps = "0";
		}
		else
		{
			this.sgps = sgps;
		}
	}

	@Column(name="SYZG")
	public String getSyzg()
	{
		return this.syzg;
	}

	public void setSyzg(String syzg)
	{
		if(syzg == null || "".equals(syzg))
		{
			this.syzg = "0";
		}
		else
		{
			this.syzg = syzg;
		}
	}

	@Column(name="SZGZ")
	public String getSzgz()
	{
		return this.szgz;
	}

	public void setSzgz(String szgz)
	{
		if(szgz == null || "".equals(szgz))
		{
			this.szgz = "0";
		}
		else
		{
			this.szgz = szgz;
		}
	}

	@Column(name="SWZG")
	public String getSwzg()
	{
		return this.swzg;
	}

	public void setSwzg(String swzg)
	{
		if(swzg == null || "".equals(swzg))
		{
			this.swzg = "0";
		}
		else
		{
			this.swzg = swzg;
		}
	}

	@Column(name="QGPS")
	public String getQgps()
	{
		return this.qgps;
	}

	public void setQgps(String qgps)
	{
		if(qgps == null || "".equals(qgps))
		{
			this.qgps = "0";
		}
		else
		{
			this.qgps = qgps;
		}
	}

	@Column(name="QYZG")
	public String getQyzg()
	{
		return this.qyzg;
	}

	public void setQyzg(String qyzg)
	{
		if(qyzg == null || "".equals(qyzg))
		{
			this.qyzg = "0";
		}
		else
		{
			this.qyzg = qyzg;
		}
	}

	@Column(name="YZGZ")
	public String getYzgz()
	{
		return this.yzgz;
	}

	public void setYzgz(String yzgz)
	{
		if(yzgz == null || "".equals(yzgz))
		{
			this.yzgz = "0";
		}
		else
		{
			this.yzgz = yzgz;
		}
	}

	@Column(name="QWZG")
	public String getQwzg()
	{
		return this.qwzg;
	}

	public void setQwzg(String qwzg)
	{
		if(qwzg == null || "".equals(qwzg))
		{
			this.qwzg = "0";
		}
		else
		{
			this.qwzg = qwzg;
		}
	}

	@Column(name="ZGPS")
	public String getZgps()
	{
		return this.zgps;
	}

	public void setZgps(String zgps)
	{
		if(zgps == null || "".equals(zgps))
		{
			this.zgps = "0";
		}
		else
		{
			this.zgps = zgps;
		}
	}

	@Column(name="ZYZG")
	public String getZyzg()
	{
		return this.zyzg;
	}

	public void setZyzg(String zyzg)
	{
		if(zyzg == null || "".equals(zyzg))
		{
			this.zyzg = "0";
		}
		else
		{
			this.zyzg = zyzg;
		}
	}

	@Column(name="ZZGZ")
	public String getZzgz()
	{
		return this.zzgz;
	}

	public void setZzgz(String zzgz)
	{
		if(zzgz == null || "".equals(zzgz))
		{
			this.zzgz = "0";
		}
		else
		{
			this.zzgz = zzgz;
		}
	}

	@Column(name="ZWZG")
	public String getZwzg()
	{
		return this.zwzg;
	}

	public void setZwzg(String zwzg)
	{
		if(zwzg == null || "".equals(zwzg))
		{
			this.zwzg = "0";
		}
		else
		{
			this.zwzg = zwzg;
		}
	}

	@Column(name="JCQYS")
	public String getJcqys()
	{
		return this.jcqys;
	}

	public void setJcqys(String jcqys)
	{
		if(jcqys == null || "".equals(jcqys))
		{
			this.jcqys = "0";
		}
		else
		{
			this.jcqys = jcqys;
		}
	}

	@Column(name="FXYHS")
	public String getFxyhs()
	{
		return this.fxyhs;
	}

	public void setFxyhs(String fxyhs)
	{
		if(fxyhs == null || "".equals(fxyhs))
		{
			this.fxyhs = "0";
		}
		else
		{
			this.fxyhs = fxyhs;
		}
	}

	@Column(name="ZGYHS")
	public String getZgyhs()
	{
		return this.zgyhs;
	}

	public void setZgyhs(String zgyhs)
	{
		if(zgyhs == null || "".equals(zgyhs))
		{
			this.zgyhs = "0";
		}
		else
		{
			this.zgyhs = zgyhs;
		}
	}

	@Column(name="NDMB")
	public String getNdmb()
	{
		return this.ndmb;
	}

	public void setNdmb(String ndmb)
	{
		if(ndmb == null || "".equals(ndmb))
		{
			this.ndmb = "0";
		}
		else
		{
			this.ndmb = ndmb;
		}
	}

	@Column(name="WCRS")
	public String getWcrs()
	{
		return this.wcrs;
	}

	public void setWcrs(String wcrs)
	{
		if(wcrs == null || "".equals(wcrs))
		{
			this.wcrs = "0";
		}
		else
		{
			this.wcrs = ndmb;
		}
	}

	@Column(name="WCL")
	public String getWcl()
	{
		return this.wcl;
	}

	public void setWcl(String wcl)
	{
		if(wcl == null || "".equals(wcl))
		{
			this.wcl = "0.00";
		}
		else
		{
			this.wcl = wcl;
		}
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

	@Column(name="TJYF")
	public String getTjyf()
	{
		return this.tjyf;
	}

	public void setTjyf(String tjyf)
	{
		this.tjyf = tjyf;
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

}
