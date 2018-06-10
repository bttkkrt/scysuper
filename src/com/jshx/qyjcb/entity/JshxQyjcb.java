package com.jshx.qyjcb.entity;

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
@Table(name="JSHX_QYJCB")
public class JshxQyjcb extends BaseModel
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
	 * 填报月份
	 */
	private String jshxMonth;
	/**
	 * 企业负责人
	 */
	private String manager;
	/**
	 * 填表人
	 */
	private String tbr;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 填表时间
	 */
	private Date tbsj;
	/**
	 * 数据字段 1
	 * @return
	 */
	private String count_1;
	/**
	 * 数据字段 2
	 * @return
	 */
	private String count_2;
	/**
	 * 数据字段 3
	 * @return
	 */
	private String count_3;
	/**
	 * 数据字段 4
	 * @return
	 */
	private String count_4;
	/**
	 * 数据字段 5
	 * @return
	 */
	private String count_5;
	/**
	 * 数据字段 6
	 * @return
	 */
	private String count_6;
	/**
	 * 数据字段 7
	 * @return
	 */
	private String count_7;
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

	
	@Column(name="JSHX_MONTH")
	public String getJshxMonth()
	{
		return this.jshxMonth;
	}

	public void setJshxMonth(String jshxMonth)
	{
		this.jshxMonth = jshxMonth;
	}

	@Column(name="MANAGER")
	public String getManager()
	{
		return this.manager;
	}

	public void setManager(String manager)
	{
		this.manager = manager;
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

	@Column(name="TBSJ")
	public Date getTbsj()
	{
		return this.tbsj;
	}

	public void setTbsj(Date tbsj)
	{
		this.tbsj = tbsj;
	}
	@Column
	public String getCount_1() {
		return count_1;
	}

	public void setCount_1(String count_1) {
		if(count_1 == null || "".equals(count_1))
		{
			this.count_1 = "0";
		}
		else
		{
			this.count_1 = count_1;
		}
	}
	@Column
	public String getCount_2() {
		return count_2;
	}

	public void setCount_2(String count_2) {
		if(count_2 == null || "".equals(count_2))
		{
			this.count_2 = "0";
		}
		else
		{
			this.count_2 = count_2;
		}
	}
	@Column
	public String getCount_3() {
		return count_3;
	}

	public void setCount_3(String count_3) {
		if(count_3 == null || "".equals(count_3))
		{
			this.count_3 = "0";
		}
		else
		{
			this.count_3 = count_3;
		}
	}
	@Column
	public String getCount_4() {
		return count_4;
	}

	public void setCount_4(String count_4) {
		if(count_4 == null || "".equals(count_4))
		{
			this.count_4 = "0";
		}
		else
		{
			this.count_4 = count_4;
		}
	}
	@Column
	public String getCount_5() {
		return count_5;
	}

	public void setCount_5(String count_5) {
		if(count_5 == null || "".equals(count_5))
		{
			this.count_5 = "0";
		}
		else
		{
			this.count_5 = count_5;
		}
	}
	@Column
	public String getCount_6() {
		return count_6;
	}

	public void setCount_6(String count_6) {
		if(count_6 == null || "".equals(count_6))
		{
			this.count_6 = "0";
		}
		else
		{
			this.count_6 = count_6;
		}
	}
	@Column
	public String getCount_7() {
		return count_7;
	}

	public void setCount_7(String count_7) {
		if(count_7 == null || "".equals(count_7))
		{
			this.count_7 = "0";
		}
		else
		{
			this.count_7 = count_7;
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
