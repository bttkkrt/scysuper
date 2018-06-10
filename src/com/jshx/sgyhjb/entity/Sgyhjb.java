package com.jshx.sgyhjb.entity;

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
@Table(name="SGYHJB")
public class Sgyhjb extends BaseModel
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
	 * 处理时间
	 */
	private String clsj;

	/**
	 * 整改完成时间
	 */
	private String zgwcsj;

	/**
	 * 奖励金额
	 */
	private String jlje;

	/**
	 * 交办部门意见
	 */
	private String jbbmyj;

	/**
	 * 交办部门负责人
	 */
	private String jbbmfzr;

	/**
	 * 时间
	 */
	private String jbbmyjsj;

	/**
	 * 审查结果
	 */
	private String scjg;

	/**
	 * 得分
	 */
	private String df;

	/**
	 * 举报编号
	 */
	private String jbbh;

	/**
	 * 举报所在镇
	 */
	private String jbszz;

	/**
	 * 举报企业或其它
	 */
	private String jbqy;
	
	private String jbqyid;

	/**
	 * 首次举报
	 */
	private String scjb;

	/**
	 * 举报次数
	 */
	private String jbcs;

	/**
	 * 举报人
	 */
	private String jbr;

	/**
	 * 举报人电话
	 */
	private String jbrdh;

	/**
	 * 举报所在地
	 */
	private String jbszd;

	/**
	 * 收信时间
	 */
	private String sxsj;

	/**
	 * 来电类别
	 */
	private String ldlb;

	/**
	 * 隐患等级
	 */
	private String yhjb;

	/**
	 * 记录人
	 */
	private String jlr;

	/**
	 * 来自何部门
	 */
	private String lzbm;

	/**
	 * 交办时间
	 */
	private String jbsj;

	/**
	 * 要求办结时间
	 */
	private String yqbjsj;

	/**
	 * 举报内容
	 */
	private String jbnr;

	/**
	 * 领导批示
	 */
	private String ldps;

	/**
	 * 领导批示时间
	 */
	private String ldpssj;

	/**
	 * 交办部门接收时间
	 */
	private String jbbmjssj;

	/**
	 * 交办部门
	 */
	private String jbbm;
	
	private String jbbmname;

	/**
	 * 分管领导意见
	 */
	private String fgldyj;

	/**
	 * 分管领导批示时间
	 */
	private String fgldpssj;

	/**
	 * 交办部门处理情况
	 */
	private String jbbmclqk;

	/**
	 * 交办部门处理时间
	 */
	private String jbbmclsj;

	/**
	 * 处理部门
	 */
	private String clbm;
	
	private String clbmname;

	/**
	 * 处理部门接收时间
	 */
	private String clbmjssj;

	/**
	 * 处理结果
	 */
	private String cljg;

	/**
	 * 处理人
	 */
	private String clr;
	
	private String state;//0表示待处理，1表示交办部门，2表示处理部门，3表示交办部门审核，4表示最终审查，5表示已审核

	private String jbbmclfh;

	private String linkId;
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

	
	@Column(name="CLSJ")
	public String getClsj()
	{
		return this.clsj;
	}

	public void setClsj(String clsj)
	{
		this.clsj = clsj;
	}

	@Column(name="ZGWCSJ")
	public String getZgwcsj()
	{
		return this.zgwcsj;
	}

	public void setZgwcsj(String zgwcsj)
	{
		this.zgwcsj = zgwcsj;
	}

	@Column(name="JLJE")
	public String getJlje()
	{
		return this.jlje;
	}

	public void setJlje(String jlje)
	{
		this.jlje = jlje;
	}

	@Column(name="JBBMYJ")
	public String getJbbmyj()
	{
		return this.jbbmyj;
	}

	public void setJbbmyj(String jbbmyj)
	{
		this.jbbmyj = jbbmyj;
	}

	@Column(name="JBBMFZR")
	public String getJbbmfzr()
	{
		return this.jbbmfzr;
	}

	public void setJbbmfzr(String jbbmfzr)
	{
		this.jbbmfzr = jbbmfzr;
	}

	@Column(name="JBBMYJSJ")
	public String getJbbmyjsj()
	{
		return this.jbbmyjsj;
	}

	public void setJbbmyjsj(String jbbmyjsj)
	{
		this.jbbmyjsj = jbbmyjsj;
	}

	@Column(name="SCJG")
	public String getScjg()
	{
		return this.scjg;
	}

	public void setScjg(String scjg)
	{
		this.scjg = scjg;
	}

	@Column(name="DF")
	public String getDf()
	{
		return this.df;
	}

	public void setDf(String df)
	{
		this.df = df;
	}

	@Column(name="JBBH")
	public String getJbbh()
	{
		return this.jbbh;
	}

	public void setJbbh(String jbbh)
	{
		this.jbbh = jbbh;
	}

	@Column(name="JBSZZ")
	public String getJbszz()
	{
		return this.jbszz;
	}

	public void setJbszz(String jbszz)
	{
		this.jbszz = jbszz;
	}

	@Column(name="JBQY")
	public String getJbqy()
	{
		return this.jbqy;
	}

	public void setJbqy(String jbqy)
	{
		this.jbqy = jbqy;
	}

	@Column(name="SCJB")
	public String getScjb()
	{
		return this.scjb;
	}

	public void setScjb(String scjb)
	{
		this.scjb = scjb;
	}

	@Column(name="JBCS")
	public String getJbcs()
	{
		return this.jbcs;
	}

	public void setJbcs(String jbcs)
	{
		this.jbcs = jbcs;
	}

	@Column(name="JBR")
	public String getJbr()
	{
		return this.jbr;
	}

	public void setJbr(String jbr)
	{
		this.jbr = jbr;
	}

	@Column(name="JBRDH")
	public String getJbrdh()
	{
		return this.jbrdh;
	}

	public void setJbrdh(String jbrdh)
	{
		this.jbrdh = jbrdh;
	}

	@Column(name="JBSZD")
	public String getJbszd()
	{
		return this.jbszd;
	}

	public void setJbszd(String jbszd)
	{
		this.jbszd = jbszd;
	}

	@Column(name="SXSJ")
	public String getSxsj()
	{
		return this.sxsj;
	}

	public void setSxsj(String sxsj)
	{
		this.sxsj = sxsj;
	}

	@Column(name="LDLB")
	public String getLdlb()
	{
		return this.ldlb;
	}

	public void setLdlb(String ldlb)
	{
		this.ldlb = ldlb;
	}

	@Column(name="YHJB")
	public String getYhjb()
	{
		return this.yhjb;
	}

	public void setYhjb(String yhjb)
	{
		this.yhjb = yhjb;
	}

	@Column(name="JLR")
	public String getJlr()
	{
		return this.jlr;
	}

	public void setJlr(String jlr)
	{
		this.jlr = jlr;
	}

	@Column(name="LZBM")
	public String getLzbm()
	{
		return this.lzbm;
	}

	public void setLzbm(String lzbm)
	{
		this.lzbm = lzbm;
	}

	@Column(name="JBSJ")
	public String getJbsj()
	{
		return this.jbsj;
	}

	public void setJbsj(String jbsj)
	{
		this.jbsj = jbsj;
	}

	@Column(name="YQBJSJ")
	public String getYqbjsj()
	{
		return this.yqbjsj;
	}

	public void setYqbjsj(String yqbjsj)
	{
		this.yqbjsj = yqbjsj;
	}

	@Column(name="JBNR")
	public String getJbnr()
	{
		return this.jbnr;
	}

	public void setJbnr(String jbnr)
	{
		this.jbnr = jbnr;
	}

	@Column(name="LDPS")
	public String getLdps()
	{
		return this.ldps;
	}

	public void setLdps(String ldps)
	{
		this.ldps = ldps;
	}

	@Column(name="LDPSSJ")
	public String getLdpssj()
	{
		return this.ldpssj;
	}

	public void setLdpssj(String ldpssj)
	{
		this.ldpssj = ldpssj;
	}

	@Column(name="JBBMJSSJ")
	public String getJbbmjssj()
	{
		return this.jbbmjssj;
	}

	public void setJbbmjssj(String jbbmjssj)
	{
		this.jbbmjssj = jbbmjssj;
	}

	@Column(name="JBBM")
	public String getJbbm()
	{
		return this.jbbm;
	}

	public void setJbbm(String jbbm)
	{
		this.jbbm = jbbm;
	}

	@Column(name="FGLDYJ")
	public String getFgldyj()
	{
		return this.fgldyj;
	}

	public void setFgldyj(String fgldyj)
	{
		this.fgldyj = fgldyj;
	}

	@Column(name="FGLDPSSJ")
	public String getFgldpssj()
	{
		return this.fgldpssj;
	}

	public void setFgldpssj(String fgldpssj)
	{
		this.fgldpssj = fgldpssj;
	}

	@Column(name="JBBMCLQK")
	public String getJbbmclqk()
	{
		return this.jbbmclqk;
	}

	public void setJbbmclqk(String jbbmclqk)
	{
		this.jbbmclqk = jbbmclqk;
	}

	@Column(name="JBBMCLSJ")
	public String getJbbmclsj()
	{
		return this.jbbmclsj;
	}

	public void setJbbmclsj(String jbbmclsj)
	{
		this.jbbmclsj = jbbmclsj;
	}

	@Column(name="CLBM")
	public String getClbm()
	{
		return this.clbm;
	}

	public void setClbm(String clbm)
	{
		this.clbm = clbm;
	}

	@Column(name="CLBMJSSJ")
	public String getClbmjssj()
	{
		return this.clbmjssj;
	}

	public void setClbmjssj(String clbmjssj)
	{
		this.clbmjssj = clbmjssj;
	}

	@Column(name="CLJG")
	public String getCljg()
	{
		return this.cljg;
	}

	public void setCljg(String cljg)
	{
		this.cljg = cljg;
	}

	@Column(name="CLR")
	public String getClr()
	{
		return this.clr;
	}

	public void setClr(String clr)
	{
		this.clr = clr;
	}
	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column
	public String getJbqyid() {
		return jbqyid;
	}

	public void setJbqyid(String jbqyid) {
		this.jbqyid = jbqyid;
	}
	@Column
	public String getJbbmname() {
		return jbbmname;
	}

	public void setJbbmname(String jbbmname) {
		this.jbbmname = jbbmname;
	}
	@Column
	public String getClbmname() {
		return clbmname;
	}

	public void setClbmname(String clbmname) {
		this.clbmname = clbmname;
	}
	@Column
	public String getJbbmclfh() {
		return jbbmclfh;
	}

	public void setJbbmclfh(String jbbmclfh) {
		this.jbbmclfh = jbbmclfh;
	}
	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

}
