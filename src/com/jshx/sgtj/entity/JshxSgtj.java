package com.jshx.sgtj.entity;

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
@Table(name="JSHX_SGTJ")
public class JshxSgtj extends BaseModel
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
	 * 企业id
	 */
	private String qyid;
	/**
	 * 企业名称
	 */
	private String qymc;
	/**
	 * 所在镇id
	 */
	private String szzid;
	/**
	 * 所在镇
	 */
	private String szzname;
	/**
	 * 年度
	 */
	private String qynd;
	/**
	 * 企业负责人
	 */
	private String qyfzr;
	/**
	 * 填表人
	 */
	private String tbr;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 事故率
	 */
	private String sgl;
	/**
	 * 严重率
	 */
	private String yzl;
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
	
	/**
	 * 平均职工人数
	 */
	private String zgs;
	
	/**
	 * 实际工作日
	 */
	private String  sjgzr;
	
	/**
	 * 实际总工时
	 */
	private String  sjgs;
	
	/**
	 * 非计划停止作业起数
	 */
	private String fjhqs;
	
	/**
	 * 非计划停止作业小时
	 */
	private String fjhxs;
	
	/**
	 * 一般伤害事故起数
	 */
	private String shsgqs;
	
	/**
	 * 一般伤害事故小时
	 */
	private String shsgxs;
	
	/**
	 * 工伤事故起数
	 */
	private String sgsgqs;
	
	/**
	 * 工伤事故小时
	 */
	private String sgsgxs;
	
	/**
	 * 损工事故总起数
	 */
	private String sgzqs;
	
	/**
	 * 损失总工时
	 */
	private String sszgs;
	
	/**
	 * 20万事故率
	 */
	private String sgl2;
	/**
	 * 20万严重率
	 */
	private String yzl2;
	
	private String szc;
	
	private String szcname;
	
	//heyc 20141210 修改 start
	/**
	 * 是否非煤矿山企业
	 */
	private String iffmksjyqy;

	@Column(name="IFFMKSJYQY")
	public String getIffmksjyqy() {
		return iffmksjyqy;
	}

	public void setIffmksjyqy(String iffmksjyqy) {
		this.iffmksjyqy = iffmksjyqy;
	}
	//heyc 20141210 修改 end

	
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
	public String getSgzqs() {
		return sgzqs;
	}

	public void setSgzqs(String sgzqs) {
		this.sgzqs = sgzqs;
	}
	@Column
	public String getSszgs() {
		return sszgs;
	}

	public void setSszgs(String sszgs) {
		this.sszgs = sszgs;
	}

	@Column
	public String getFjhqs() {
		return fjhqs;
	}

	public void setFjhqs(String fjhqs) {
		if(fjhqs == null || "".equals(fjhqs))
		{
			this.fjhqs = "0";
		}
		else
		{
			this.fjhqs = fjhqs;
		}
	}
	@Column
	public String getFjhxs() {
		return fjhxs;
	}

	public void setFjhxs(String fjhxs) {
		if(fjhxs == null || "".equals(fjhxs))
		{
			this.fjhxs = "0";
		}
		else
		{
			this.fjhxs = fjhxs;
		}
	}
	@Column
	public String getShsgqs() {
		return shsgqs;
	}

	public void setShsgqs(String shsgqs) {
		if(shsgqs == null || "".equals(shsgqs))
		{
			this.shsgqs = "0";
		}
		else
		{
			this.shsgqs = shsgqs;
		}
	}
	@Column
	public String getShsgxs() {
		return shsgxs;
	}

	public void setShsgxs(String shsgxs) {
		if(shsgxs == null || "".equals(shsgxs))
		{
			this.shsgxs = "0";
		}
		else
		{
			this.shsgxs = shsgxs;
		}
	}
	@Column
	public String getSgsgqs() {
		return sgsgqs;
	}

	public void setSgsgqs(String sgsgqs) {
		if(sgsgqs == null || "".equals(sgsgqs))
		{
			this.sgsgqs = "0";
		}
		else
		{
			this.sgsgqs = sgsgqs;
		}
	}
	@Column
	public String getSgsgxs() {
		return sgsgxs;
	}

	public void setSgsgxs(String sgsgxs) {
		if(sgsgxs == null || "".equals(sgsgxs))
		{
			this.sgsgxs = "0";
		}
		else
		{
			this.sgsgxs = sgsgxs;
		}
	}

	@Column
	public String getSjgzr() {
		return sjgzr;
	}

	public void setSjgzr(String sjgzr) {
		if(sjgzr == null || "".equals(sjgzr))
		{
			this.sjgzr = "0";
		}
		else
		{
			this.sjgzr = sjgzr;
		}
	}
	@Column
	public String getSjgs() {
		return sjgs;
	}

	public void setSjgs(String sjgs) {
		if(sjgs == null || "".equals(sjgs))
		{
			this.sjgs = "0";
		}
		else
		{
			this.sjgs = sjgs;
		}
	}

	@Column(name="ZGS")
	public String getZgs()
	{
		return this.zgs;
	}

	public void setZgs(String zgs)
	{
		if(zgs == null || "".equals(zgs))
		{
			this.zgs = "0";
		}
		else
		{
			this.zgs = zgs;
		}
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


	@Column(name="QYLX")
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	@Column(name="HYFL")
	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	@Column(name="QYGM")
	public String getQygm() {
		return qygm;
	}

	public void setQygm(String qygm) {
		this.qygm = qygm;
	}
	@Column(name="QYZCLX")
	public String getQyzclx() {
		return qyzclx;
	}

	public void setQyzclx(String qyzclx) {
		this.qyzclx = qyzclx;
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

	@Column(name="QYND")
	public String getQynd()
	{
		return this.qynd;
	}

	public void setQynd(String qynd)
	{
		this.qynd = qynd;
	}

	@Column(name="QYFZR")
	public String getQyfzr()
	{
		return this.qyfzr;
	}

	public void setQyfzr(String qyfzr)
	{
		this.qyfzr = qyfzr;
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

	@Column(name="TELEPHONE")
	public String getTelephone()
	{
		return this.telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	@Column(name="SGL")
	public String getSgl()
	{
		return this.sgl;
	}

	public void setSgl(String sgl)
	{
		this.sgl = sgl;
	}

	@Column(name="YZL")
	public String getYzl()
	{
		return this.yzl;
	}

	public void setYzl(String yzl)
	{
		this.yzl = yzl;
	}
	
	@Column(name="SGL2")
	public String getSgl2()
	{
		return this.sgl2;
	}

	public void setSgl2(String sgl2)
	{
		this.sgl2 = sgl2;
	}

	@Column(name="YZL2")
	public String getYzl2()
	{
		return this.yzl2;
	}

	public void setYzl2(String yzl2)
	{
		this.yzl2 = yzl2;
	}
	@Column
	public String getSzzid() {
		return szzid;
	}

	public void setSzzid(String szzid) {
		this.szzid = szzid;
	}
	@Column
	public String getSzzname() {
		return szzname;
	}

	public void setSzzname(String szzname) {
		this.szzname = szzname;
	}

	/**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 企业所属监管类型
	 */
	private String companyType;
	
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name="ifzsqy")
	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}
}
