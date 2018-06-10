package com.jshx.zjjcb.entity;

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
@Table(name="JSHX_ZJJCB")
public class JshxZjjcb extends BaseModel
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
	 * 专家姓名
	 */
	private String zjName;
	/**
	 * 职称
	 */
	private String zc;
	/**
	 * 从事专业
	 */
	private String zy;
	/**
	 * 检查日期
	 */
	private Date jcrq;
	/**
	 * 本次检查存在隐患及问题
	 */
	private String question;
	/**
	 * 整改建议
	 */
	private String advice;
	/**
	 * 关联附件id
	 */
	private String linkId;
	/**
	 * 上次隐患整改情况
	 */
	private String zgqk;
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
	 * 专家人数
	 */
	private String zjsl;
	/**
	 * 隐患数
	 */
	private String yhs;
	/**
	 * 其中重大隐患数
	 */
	private String zdyhs;
	/**
	 * 隐患整改数
	 */
	private String yhzgs;
	/**
	 * 其中重大隐患整改数
	 */
	private String zdyhzgs;
	/**
	 * 整改费用
	 */
	private String zgfy;
	
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

	
	@Column(name="ZJ_NAME")
	public String getZjName()
	{
		return this.zjName;
	}

	public void setZjName(String zjName)
	{
		this.zjName = zjName;
	}

	@Column(name="ZC")
	public String getZc()
	{
		return this.zc;
	}

	public void setZc(String zc)
	{
		this.zc = zc;
	}

	@Column(name="ZY")
	public String getZy()
	{
		return this.zy;
	}

	public void setZy(String zy)
	{
		this.zy = zy;
	}

	@Column(name="JCRQ")
	public Date getJcrq()
	{
		return this.jcrq;
	}

	public void setJcrq(Date jcrq)
	{
		this.jcrq = jcrq;
	}

	@Column(name="QUESTION")
	public String getQuestion()
	{
		return this.question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	@Column(name="ADVICE")
	public String getAdvice()
	{
		return this.advice;
	}

	public void setAdvice(String advice)
	{
		this.advice = advice;
	}

	@Column(name="ZGQK")
	public String getZgqk()
	{
		return this.zgqk;
	}

	public void setZgqk(String zgqk)
	{
		this.zgqk = zgqk;
	}
	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	@Column
	public String getZjsl() {
		return zjsl;
	}

	public void setZjsl(String zjsl) {
		if(zjsl == null || "".equals(zjsl))
		{
			this.zjsl = "0";
		}
		else
		{
			this.zjsl = zjsl;
		}
	}
	@Column
	public String getYhs() {
		return yhs;
	}

	public void setYhs(String yhs) {
		if(yhs == null || "".equals(yhs))
		{
			this.yhs = "0";
		}
		else
		{
			this.yhs = yhs;
		}
	}
	@Column
	public String getZdyhs() {
		return zdyhs;
	}

	public void setZdyhs(String zdyhs) {
		if(zdyhs == null || "".equals(zdyhs))
		{
			this.zdyhs = "0";
		}
		else
		{
			this.zdyhs = zdyhs;
		}
	}
	@Column
	public String getYhzgs() {
		return yhzgs;
	}

	public void setYhzgs(String yhzgs) {
		if(yhzgs == null || "".equals(yhzgs))
		{
			this.yhzgs = "0";
		}
		else
		{
			this.yhzgs = yhzgs;
		}
	}
	@Column
	public String getZdyhzgs() {
		return zdyhzgs;
	}

	public void setZdyhzgs(String zdyhzgs) {
		if(zdyhzgs == null || "".equals(zdyhzgs))
		{
			this.zdyhzgs = "0";
		}
		else
		{
			this.zdyhzgs = zdyhzgs;
		}
	}
	@Column
	public String getZgfy() {
		return zgfy;
	}

	public void setZgfy(String zgfy) {
		if(zgfy == null || "".equals(zgfy))
		{
			this.zgfy = "0";
		}
		else
		{
			this.zgfy = zgfy;
		}
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
