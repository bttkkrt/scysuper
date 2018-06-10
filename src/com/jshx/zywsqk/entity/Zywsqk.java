package com.jshx.zywsqk.entity;

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
@Table(name="ZYWSQK")
public class Zywsqk extends BaseModel
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
	 * 兼职职业卫生管理人数
	 */
	private String jzrs;

	/**
	 * 应职业病危害预评价项目数
	 */
	private String yzxms;

	/**
	 * 实际职业病危害预评价项目数
	 */
	private String sjxms;

	/**
	 * 应职业病危害控制效果评价项目数
	 */
	private String yzpjxms;

	/**
	 * 实际职业病危害控制效果评价项目数
	 */
	private String sjpjxms;

	/**
	 * 职业病危害申报
	 */
	private String zybwhsb;

	/**
	 * 主要负责人职业卫生培训
	 */
	private String zyfzrzywspx;

	/**
	 * 岗前应职业健康检查人数
	 */
	private String gqyzjcrs;

	/**
	 * 在岗应职业健康检查人数
	 */
	private String zgyzjcrs;

	/**
	 * 离岗应职业健康检查人数
	 */
	private String lgyzjcrs;

	/**
	 * 岗前实际职业健康检查人数
	 */
	private String gqsjjcrs;

	/**
	 * 在岗实际职业健康检查人数
	 */
	private String zgsjjcrs;

	/**
	 * 离岗实际职业健康检查人数
	 */
	private String lgsjjcrs;

	/**
	 * 新发职业病病例数合计
	 */
	private String xfhj;

	/**
	 * 新发职业病病例数尘肺
	 */
	private String xfcf;

	/**
	 * 新发职业病病例数职业中毒	
	 */
	private String xfzd;

	/**
	 * 新发职业病病例数噪声聋
	 */
	private String xfsz;

	/**
	 * 新发职业病病例数职业性皮肤病
	 */
	private String xfpfb;

	/**
	 * 累计职业病病例数合计
	 */
	private String ljhj;

	/**
	 * 累计职业病病例数尘肺
	 */
	private String ljcf;

	/**
	 * 累计职业病病例数职业中毒
	 */
	private String ljzd;

	/**
	 * 累计职业病病例数噪声聋
	 */
	private String ljsl;

	/**
	 * 累计职业病病例数职业性皮肤病
	 */
	private String ljpfb;

	/**
	 * 负责人
	 */
	private String fzr;

	/**
	 * 填表人
	 */
	private String tbr;

	/**
	 * 联系电话
	 */
	private String tbrlxdh;

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
	 * 组织机构代码
	 */
	private String zzjgdm;

	/**
	 * 注册地址
	 */
	private String zcdz;

	/**
	 * 工作场所地址
	 */
	private String gzcsdz;

	/**
	 * 法定代表人
	 */
	private String fddbr;

	/**
	 * 联系电话
	 */
	private String fddbrlxdh;

	/**
	 * 联系人
	 */
	private String lxr;

	/**
	 * 联系电话
	 */
	private String lxrlxdh;

	/**
	 * 从业人员数
	 */
	private String cyrys;

	/**
	 * 统计年份
	 */
	private String tjnf;

	/**
	 * 接触职业病危害因素人数
	 */
	private String jcrs;

	/**
	 * 合同告知职业病危害人数
	 */
	private String htrs;

	/**
	 * 建立职业健康监护档案人数
	 */
	private String jdrs;

	/**
	 * 职业病危害作业岗位数
	 */
	private String zygws;

	/**
	 * 设置警示标识岗位数
	 */
	private String szgws;

	/**
	 * 应职业卫生培训人数
	 */
	private String yzrs;

	/**
	 * 实际职业卫生培训人数
	 */
	private String sjrs;

	/**
	 * 专职职业卫生管理人数
	 */
	private String zzrs;

	/**
	 * 企业注册类型
	 */
	private String qyzclx;
	
	private String fcjcrshj;
	
	private String fcjcdshj;
	
	private String fcdbdshj;
	
	private String hxjcrshj;
	
	private String hxjcdshj;
	
	private String hxdbdshj;
	
	private String wljcrshj;
	
	private String wljcdshj;
	
	private String wldbdshj;
	
	private String swjcrshj;
	
	private String swjcdshj;
	
	private String swdbdshj;
	
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

	
	@Column(name="JZRS")
	public String getJzrs()
	{
		return this.jzrs;
	}

	public void setJzrs(String jzrs)
	{
		if(jzrs == null || "".equals(jzrs))
		{
			this.jzrs = "0";
		}
		else
		{
			this.jzrs = jzrs;
		}
	}

	@Column(name="YZXMS")
	public String getYzxms()
	{
		return this.yzxms;
	}

	public void setYzxms(String yzxms)
	{
		if(yzxms == null || "".equals(yzxms))
		{
			this.yzxms = "0";
		}
		else
		{
			this.yzxms = yzxms;
		}
	}

	@Column(name="SJXMS")
	public String getSjxms()
	{
		return this.sjxms;
	}

	public void setSjxms(String sjxms)
	{
		if(sjxms == null || "".equals(sjxms))
		{
			this.sjxms = "0";
		}
		else
		{
			this.sjxms = sjxms;
		}
	}

	@Column(name="YZPJXMS")
	public String getYzpjxms()
	{
		return this.yzpjxms;
	}

	public void setYzpjxms(String yzpjxms)
	{
		if(yzpjxms == null || "".equals(yzpjxms))
		{
			this.yzpjxms = "0";
		}
		else
		{
			this.yzpjxms = yzpjxms;
		}
	}

	@Column(name="SJPJXMS")
	public String getSjpjxms()
	{
		return this.sjpjxms;
	}

	public void setSjpjxms(String sjpjxms)
	{
		if(sjpjxms == null || "".equals(sjpjxms))
		{
			this.sjpjxms = "0";
		}
		else
		{
			this.sjpjxms = sjpjxms;
		}
	}

	@Column(name="ZYBWHSB")
	public String getZybwhsb()
	{
		return this.zybwhsb;
	}

	public void setZybwhsb(String zybwhsb)
	{
		this.zybwhsb = zybwhsb;
	}

	@Column(name="ZYFZRZYWSPX")
	public String getZyfzrzywspx()
	{
		return this.zyfzrzywspx;
	}

	public void setZyfzrzywspx(String zyfzrzywspx)
	{
		this.zyfzrzywspx = zyfzrzywspx;
	}

	@Column(name="GQYZJCRS")
	public String getGqyzjcrs()
	{
		return this.gqyzjcrs;
	}

	public void setGqyzjcrs(String gqyzjcrs)
	{
		if(gqyzjcrs == null || "".equals(gqyzjcrs))
		{
			this.gqyzjcrs = "0";
		}
		else
		{
			this.gqyzjcrs = gqyzjcrs;
		}
	}

	@Column(name="ZGYZJCRS")
	public String getZgyzjcrs()
	{
		return this.zgyzjcrs;
	}

	public void setZgyzjcrs(String zgyzjcrs)
	{
		if(zgyzjcrs == null || "".equals(zgyzjcrs))
		{
			this.zgyzjcrs = "0";
		}
		else
		{
			this.zgyzjcrs = zgyzjcrs;
		}
	}

	@Column(name="LGYZJCRS")
	public String getLgyzjcrs()
	{
		return this.lgyzjcrs;
	}

	public void setLgyzjcrs(String lgyzjcrs)
	{
		if(lgyzjcrs == null || "".equals(lgyzjcrs))
		{
			this.lgyzjcrs = "0";
		}
		else
		{
			this.lgyzjcrs = lgyzjcrs;
		}
	}

	@Column(name="GQSJJCRS")
	public String getGqsjjcrs()
	{
		return this.gqsjjcrs;
	}

	public void setGqsjjcrs(String gqsjjcrs)
	{
		if(gqsjjcrs == null || "".equals(gqsjjcrs))
		{
			this.gqsjjcrs = "0";
		}
		else
		{
			this.gqsjjcrs = gqsjjcrs;
		}
	}

	@Column(name="ZGSJJCRS")
	public String getZgsjjcrs()
	{
		return this.zgsjjcrs;
	}

	public void setZgsjjcrs(String zgsjjcrs)
	{
		if(zgsjjcrs == null || "".equals(zgsjjcrs))
		{
			this.zgsjjcrs = "0";
		}
		else
		{
			this.zgsjjcrs = zgsjjcrs;
		}
	}

	@Column(name="LGSJJCRS")
	public String getLgsjjcrs()
	{
		return this.lgsjjcrs;
	}

	public void setLgsjjcrs(String lgsjjcrs)
	{
		if(lgsjjcrs == null || "".equals(lgsjjcrs))
		{
			this.lgsjjcrs = "0";
		}
		else
		{
			this.lgsjjcrs = lgsjjcrs;
		}
	}

	@Column(name="XFHJ")
	public String getXfhj()
	{
		return this.xfhj;
	}

	public void setXfhj(String xfhj)
	{
		if(xfhj == null || "".equals(xfhj))
		{
			this.xfhj = "0";
		}
		else
		{
			this.xfhj = xfhj;
		}
	}

	@Column(name="XFCF")
	public String getXfcf()
	{
		return this.xfcf;
	}

	public void setXfcf(String xfcf)
	{
		if(xfcf == null || "".equals(xfcf))
		{
			this.xfcf = "0";
		}
		else
		{
			this.xfcf = xfcf;
		}
	}

	@Column(name="XFZD")
	public String getXfzd()
	{
		return this.xfzd;
	}

	public void setXfzd(String xfzd)
	{
		if(xfzd == null || "".equals(xfzd))
		{
			this.xfzd = "0";
		}
		else
		{
			this.xfzd = xfzd;
		}
	}

	@Column(name="XFSZ")
	public String getXfsz()
	{
		return this.xfsz;
	}

	public void setXfsz(String xfsz)
	{
		if(xfsz == null || "".equals(xfsz))
		{
			this.xfsz = "0";
		}
		else
		{
			this.xfsz = xfsz;
		}
	}

	@Column(name="XFPFB")
	public String getXfpfb()
	{
		return this.xfpfb;
	}

	public void setXfpfb(String xfpfb)
	{
		if(xfpfb == null || "".equals(xfpfb))
		{
			this.xfpfb = "0";
		}
		else
		{
			this.xfpfb = xfpfb;
		}
	}

	@Column(name="LJHJ")
	public String getLjhj()
	{
		return this.ljhj;
	}

	public void setLjhj(String ljhj)
	{
		if(ljhj == null || "".equals(ljhj))
		{
			this.ljhj = "0";
		}
		else
		{
			this.ljhj = ljhj;
		}
	}

	@Column(name="LJCF")
	public String getLjcf()
	{
		return this.ljcf;
	}

	public void setLjcf(String ljcf)
	{
		if(ljcf == null || "".equals(ljcf))
		{
			this.ljcf = "0";
		}
		else
		{
			this.ljcf = ljcf;
		}
	}

	@Column(name="LJZD")
	public String getLjzd()
	{
		return this.ljzd;
	}

	public void setLjzd(String ljzd)
	{
		if(ljzd == null || "".equals(ljzd))
		{
			this.ljzd = "0";
		}
		else
		{
			this.ljzd = ljzd;
		}
	}

	@Column(name="LJSL")
	public String getLjsl()
	{
		return this.ljsl;
	}

	public void setLjsl(String ljsl)
	{
		if(ljsl == null || "".equals(ljsl))
		{
			this.ljsl = "0";
		}
		else
		{
			this.ljsl = ljsl;
		}
	}

	@Column(name="LJPFB")
	public String getLjpfb()
	{
		return this.ljpfb;
	}

	public void setLjpfb(String ljpfb)
	{
		if(ljpfb == null || "".equals(ljpfb))
		{
			this.ljpfb = "0";
		}
		else
		{
			this.ljpfb = ljpfb;
		}
	}

	@Column(name="FZR")
	public String getFzr()
	{
		return this.fzr;
	}

	public void setFzr(String fzr)
	{
		this.fzr = fzr;
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

	@Column(name="TBRLXDH")
	public String getTbrlxdh()
	{
		return this.tbrlxdh;
	}

	public void setTbrlxdh(String tbrlxdh)
	{
		this.tbrlxdh = tbrlxdh;
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

	@Column(name="ZZJGDM")
	public String getZzjgdm()
	{
		return this.zzjgdm;
	}

	public void setZzjgdm(String zzjgdm)
	{
		this.zzjgdm = zzjgdm;
	}

	@Column(name="ZCDZ")
	public String getZcdz()
	{
		return this.zcdz;
	}

	public void setZcdz(String zcdz)
	{
		this.zcdz = zcdz;
	}

	@Column(name="GZCSDZ")
	public String getGzcsdz()
	{
		return this.gzcsdz;
	}

	public void setGzcsdz(String gzcsdz)
	{
		this.gzcsdz = gzcsdz;
	}

	@Column(name="FDDBR")
	public String getFddbr()
	{
		return this.fddbr;
	}

	public void setFddbr(String fddbr)
	{
		this.fddbr = fddbr;
	}

	@Column(name="FDDBRLXDH")
	public String getFddbrlxdh()
	{
		return this.fddbrlxdh;
	}

	public void setFddbrlxdh(String fddbrlxdh)
	{
		this.fddbrlxdh = fddbrlxdh;
	}

	@Column(name="LXR")
	public String getLxr()
	{
		return this.lxr;
	}

	public void setLxr(String lxr)
	{
		this.lxr = lxr;
	}

	@Column(name="LXRLXDH")
	public String getLxrlxdh()
	{
		return this.lxrlxdh;
	}

	public void setLxrlxdh(String lxrlxdh)
	{
		this.lxrlxdh = lxrlxdh;
	}

	@Column(name="CYRYS")
	public String getCyrys()
	{
		return this.cyrys;
	}

	public void setCyrys(String cyrys)
	{
		if(cyrys == null || "".equals(cyrys))
		{
			this.cyrys = "0";
		}
		else
		{
			this.cyrys = cyrys;
		}
	}

	@Column(name="TJNF")
	public String getTjnf()
	{
		return this.tjnf;
	}

	public void setTjnf(String tjnf)
	{
		this.tjnf = tjnf;
	}

	@Column(name="JCRS")
	public String getJcrs()
	{
		return this.jcrs;
	}

	public void setJcrs(String jcrs)
	{
		if(jcrs == null || "".equals(jcrs))
		{
			this.jcrs = "0";
		}
		else
		{
			this.jcrs = jcrs;
		}
	}

	@Column(name="HTRS")
	public String getHtrs()
	{
		return this.htrs;
	}

	public void setHtrs(String htrs)
	{
		if(htrs == null || "".equals(htrs))
		{
			this.htrs = "0";
		}
		else
		{
			this.htrs = htrs;
		}
	}

	@Column(name="JDRS")
	public String getJdrs()
	{
		return this.jdrs;
	}

	public void setJdrs(String jdrs)
	{
		if(jdrs == null || "".equals(jdrs))
		{
			this.jdrs = "0";
		}
		else
		{
			this.jdrs = jdrs;
		}
	}

	@Column(name="ZYGWS")
	public String getZygws()
	{
		return this.zygws;
	}

	public void setZygws(String zygws)
	{
		if(zygws == null || "".equals(zygws))
		{
			this.zygws = "0";
		}
		else
		{
			this.zygws = zygws;
		}
	}

	@Column(name="SZGWS")
	public String getSzgws()
	{
		return this.szgws;
	}

	public void setSzgws(String szgws)
	{
		if(szgws == null || "".equals(szgws))
		{
			this.szgws = "0";
		}
		else
		{
			this.szgws = szgws;
		}
	}

	@Column(name="YZRS")
	public String getYzrs()
	{
		return this.yzrs;
	}

	public void setYzrs(String yzrs)
	{
		if(yzrs == null || "".equals(yzrs))
		{
			this.yzrs = "0";
		}
		else
		{
			this.yzrs = yzrs;
		}
	}

	@Column(name="SJRS")
	public String getSjrs()
	{
		return this.sjrs;
	}

	public void setSjrs(String sjrs)
	{
		if(sjrs == null || "".equals(sjrs))
		{
			this.sjrs = "0";
		}
		else
		{
			this.sjrs = sjrs;
		}
	}

	@Column(name="ZZRS")
	public String getZzrs()
	{
		return this.zzrs;
	}

	public void setZzrs(String zzrs)
	{
		if(zzrs == null || "".equals(zzrs))
		{
			this.zzrs = "0";
		}
		else
		{
			this.zzrs = zzrs;
		}
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
	@Column
	public String getFcjcrshj() {
		return fcjcrshj;
	}

	public void setFcjcrshj(String fcjcrshj) {
		if(fcjcrshj == null || "".equals(fcjcrshj))
		{
			this.fcjcrshj = "0";
		}
		else
		{
			this.fcjcrshj = fcjcrshj;
		}
	}
	@Column
	public String getFcjcdshj() {
		return fcjcdshj;
	}

	public void setFcjcdshj(String fcjcdshj) {
		if(fcjcdshj == null || "".equals(fcjcdshj))
		{
			this.fcjcdshj = "0";
		}
		else
		{
			this.fcjcdshj = fcjcdshj;
		}
	}
	@Column
	public String getFcdbdshj() {
		return fcdbdshj;
	}

	public void setFcdbdshj(String fcdbdshj) {
		if(fcdbdshj == null || "".equals(fcdbdshj))
		{
			this.fcdbdshj = "0";
		}
		else
		{
			this.fcdbdshj = fcdbdshj;
		}
	}
	@Column
	public String getHxjcrshj() {
		return hxjcrshj;
	}

	public void setHxjcrshj(String hxjcrshj) {
		if(hxjcrshj == null || "".equals(hxjcrshj))
		{
			this.hxjcrshj = "0";
		}
		else
		{
			this.hxjcrshj = hxjcrshj;
		}
	}
	@Column
	public String getHxjcdshj() {
		return hxjcdshj;
	}

	public void setHxjcdshj(String hxjcdshj) {
		if(hxjcdshj == null || "".equals(hxjcdshj))
		{
			this.hxjcdshj = "0";
		}
		else
		{
			this.hxjcdshj = hxjcdshj;
		}
	}
	@Column
	public String getHxdbdshj() {
		return hxdbdshj;
	}

	public void setHxdbdshj(String hxdbdshj) {
		if(hxdbdshj == null || "".equals(hxdbdshj))
		{
			this.hxdbdshj = "0";
		}
		else
		{
			this.hxdbdshj = hxdbdshj;
		}
	}
	@Column
	public String getWljcrshj() {
		return wljcrshj;
	}

	public void setWljcrshj(String wljcrshj) {
		if(wljcrshj == null || "".equals(wljcrshj))
		{
			this.wljcrshj = "0";
		}
		else
		{
			this.wljcrshj = wljcrshj;
		}
	}
	@Column
	public String getWljcdshj() {
		return wljcdshj;
	}

	public void setWljcdshj(String wljcdshj) {
		if(wljcdshj == null || "".equals(wljcdshj))
		{
			this.wljcdshj = "0";
		}
		else
		{
			this.wljcdshj = wljcdshj;
		}
	}
	@Column
	public String getWldbdshj() {
		return wldbdshj;
	}

	public void setWldbdshj(String wldbdshj) {
		if(wldbdshj == null || "".equals(wldbdshj))
		{
			this.wldbdshj = "0";
		}
		else
		{
			this.wldbdshj = wldbdshj;
		}
	}
	@Column
	public String getSwjcrshj() {
		return swjcrshj;
	}

	public void setSwjcrshj(String swjcrshj) {
		if(swjcrshj == null || "".equals(swjcrshj))
		{
			this.swjcrshj = "0";
		}
		else
		{
			this.swjcrshj = swjcrshj;
		}
	}
	@Column
	public String getSwjcdshj() {
		return swjcdshj;
	}

	public void setSwjcdshj(String swjcdshj) {
		if(swjcdshj == null || "".equals(swjcdshj))
		{
			this.swjcdshj = "0";
		}
		else
		{
			this.swjcdshj = swjcdshj;
		}
	}
	@Column
	public String getSwdbdshj() {
		return swdbdshj;
	}

	public void setSwdbdshj(String swdbdshj) {
		if(swdbdshj == null || "".equals(swdbdshj))
		{
			this.swdbdshj = "0";
		}
		else
		{
			this.swdbdshj = swdbdshj;
		}
	}

}
