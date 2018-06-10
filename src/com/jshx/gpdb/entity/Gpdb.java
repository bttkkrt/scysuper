package com.jshx.gpdb.entity;

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
@Table(name="GPDB")
public class Gpdb extends BaseModel
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
	 * 企业注册类型
	 */
	private String qyzclx;

	/**
	 * 隐患名称
	 */
	private String yhmc;

	/**
	 * 挂牌时间
	 */
	private Date gpsj;

	/**
	 * 隐患类别
	 */
	private String yhlb;

	/**
	 * 隐患数
	 */
	private String yhs;

	/**
	 * 责任人
	 */
	private String zrr;

	/**
	 * 整改资金（万元）
	 */
	private String zgzj;

	/**
	 * 整改完成时间
	 */
	private Date zgwcsj;

	/**
	 * 验收时间
	 */
	private Date yssj;

	/**
	 * 隐患整改数
	 */
	private String yhzgs;

	/**
	 * 附件关联id
	 */
	private String linkid;

	/**
	 * 状态
	 */
	private String state;
	
	private String remark;
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
	
	private String username;
	
	private String shenhe;
	
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

	@Column(name="YHMC")
	public String getYhmc()
	{
		return this.yhmc;
	}

	public void setYhmc(String yhmc)
	{
		this.yhmc = yhmc;
	}

	@Column(name="GPSJ")
	public Date getGpsj()
	{
		return this.gpsj;
	}

	public void setGpsj(Date gpsj)
	{
		this.gpsj = gpsj;
	}

	@Column(name="YHLB")
	public String getYhlb()
	{
		return this.yhlb;
	}

	public void setYhlb(String yhlb)
	{
		this.yhlb = yhlb;
	}

	@Column(name="YHS")
	public String getYhs()
	{
		return this.yhs;
	}

	public void setYhs(String yhs)
	{
		if(yhs == null || "".equals(yhs))
		{
			this.yhs = "0";
		}
		else
		{
			this.yhs = yhs;
		}
	}

	@Column(name="ZRR")
	public String getZrr()
	{
		return this.zrr;
	}

	public void setZrr(String zrr)
	{
		this.zrr = zrr;
	}

	@Column(name="ZGZJ")
	public String getZgzj()
	{
		return this.zgzj;
	}

	public void setZgzj(String zgzj)
	{
		if(zgzj == null || "".equals(zgzj))
		{
			this.zgzj = "0";
		}
		else
		{
			this.zgzj = zgzj;
		}
	}

	@Column(name="ZGWCSJ")
	public Date getZgwcsj()
	{
		return this.zgwcsj;
	}

	public void setZgwcsj(Date zgwcsj)
	{
		this.zgwcsj = zgwcsj;
	}

	@Column(name="YSSJ")
	public Date getYssj()
	{
		return this.yssj;
	}

	public void setYssj(Date yssj)
	{
		this.yssj = yssj;
	}

	@Column(name="YHZGS")
	public String getYhzgs()
	{
		return this.yhzgs;
	}

	public void setYhzgs(String yhzgs)
	{
		if(yhzgs == null || "".equals(yhzgs))
		{
			this.yhzgs = "0";
		}
		else
		{
			this.yhzgs = yhzgs;
		}
	}

	@Column(name="LINKID")
	public String getLinkid()
	{
		return this.linkid;
	}

	public void setLinkid(String linkid)
	{
		this.linkid = linkid;
	}

	@Column(name="STATE")
	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="SHENHE")
	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

}
