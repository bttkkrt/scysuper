package com.jshx.zybfb.entity;

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
@Table(name="JSHX_ZYBFB")
public class JshxZybfb extends BaseModel
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
	 * 作业场所名称
	 */
	private String workName;

	/**
	 * 职业病危害因素名称
	 */
	private String zybName;

	/**
	 * 接触人数(可重复)
	 */
	private String repeatCount;

	/**
	 * 接触人数(不可重复)
	 */
	private String noRepeatCount;

	/**
	 * 女工数
	 */
	private String womanCount;

	/**
	 * 填报人
	 */
	private String tbr;

	/**
	 * 联系电话
	 */
	private String telephone;

	/**
	 * 接触人数（不可重复）统计
	 */
	private String noRepeatTotal;

	/**
	 * 女工数 统计
	 */
	private String womanTotal;

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

	
	@Column(name="WORK_NAME")
	public String getWorkName()
	{
		return this.workName;
	}

	public void setWorkName(String workName)
	{
		this.workName = workName;
	}

	@Column(name="ZYB_NAME")
	public String getZybName()
	{
		return this.zybName;
	}

	public void setZybName(String zybName)
	{
		this.zybName = zybName;
	}

	@Column(name="REPEAT_COUNT")
	public String getRepeatCount()
	{
		return this.repeatCount;
	}

	public void setRepeatCount(String repeatCount)
	{
		if(repeatCount == null || "".equals(repeatCount))
		{
			this.repeatCount = "0";
		}
		else
		{
			this.repeatCount = repeatCount;
		}
	}

	@Column(name="NO_REPEAT_COUNT")
	public String getNoRepeatCount()
	{
		return this.noRepeatCount;
	}

	public void setNoRepeatCount(String noRepeatCount)
	{
		if(noRepeatCount == null || "".equals(noRepeatCount))
		{
			this.noRepeatCount = "0";
		}
		else
		{
			this.noRepeatCount = noRepeatCount;
		}
	}

	@Column(name="WOMAN_COUNT")
	public String getWomanCount()
	{
		return this.womanCount;
	}

	public void setWomanCount(String womanCount)
	{
		if(womanCount == null || "".equals(womanCount))
		{
			this.womanCount = "0";
		}
		else
		{
			this.womanCount = womanCount;
		}
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

	@Column(name="NO_REPEAT_TOTAL")
	public String getNoRepeatTotal()
	{
		return this.noRepeatTotal;
	}

	public void setNoRepeatTotal(String noRepeatTotal)
	{
		if(noRepeatTotal == null || "".equals(noRepeatTotal))
		{
			this.noRepeatTotal = "0";
		}
		else
		{
			this.noRepeatTotal = noRepeatTotal;
		}
	}

	@Column(name="WOMAN_TOTAL")
	public String getWomanTotal()
	{
		return this.womanTotal;
	}

	public void setWomanTotal(String womanTotal)
	{
		if(womanTotal == null || "".equals(womanTotal))
		{
			this.womanTotal = "0";
		}
		else
		{
			this.womanTotal = womanTotal;
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
