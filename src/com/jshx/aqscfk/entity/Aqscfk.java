package com.jshx.aqscfk.entity;

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
@Table(name="AQSCFK")
public class Aqscfk extends BaseModel
{
	/**
	 * 企业类型
	 */
	private String companyType;
	/**
	 * 行政处罚对象 
	 */
	private String objectType;
	/**
	 * 执法文书类型
	 */
	private String fileType;
	
	
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
	 * 处罚事由
	 */
	private String cfsy;

	/**
	 * 执法文书号
	 */
	private String zfwsh;

	/**
	 * 处罚金额（万元）
	 */
	private String cfje;

	/**
	 * 罚款分类
	 */
	private String fkfl;

	/**
	 * 结案时间
	 */
	private Date jasj;

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
	
	private String linkId;
	
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 审核记录
	 */
	private String shenhe;
	
	/**
	 * 审核状态
	 */
	private String state;
	
private String szc;
	
	private String szcname;
	/**
	 * 事故分类
	 */
	private String sgfl;
	
	private String sjfk;
	
	private String zltc;
	
	@Column
	public String getSgfl() {
		return sgfl;
	}

	public void setSgfl(String sgfl) {
		this.sgfl = sgfl;
	}

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
	
	@Column(name="LINKID")
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	@Column(name="CFSY")
	public String getCfsy()
	{
		return this.cfsy;
	}

	public void setCfsy(String cfsy)
	{
		this.cfsy = cfsy;
	}

	@Column(name="ZFWSH")
	public String getZfwsh()
	{
		return this.zfwsh;
	}

	public void setZfwsh(String zfwsh)
	{
		this.zfwsh = zfwsh;
	}

	@Column(name="CFJE")
	public String getCfje()
	{
		return this.cfje;
	}

	public void setCfje(String cfje)
	{
		this.cfje = cfje;
	}

	@Column(name="FKFL")
	public String getFkfl()
	{
		return this.fkfl;
	}

	public void setFkfl(String fkfl)
	{
		this.fkfl = fkfl;
	}

	@Column(name="JASJ")
	public Date getJasj()
	{
		return this.jasj;
	}

	public void setJasj(Date jasj)
	{
		this.jasj = jasj;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="SHENHE")
	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="COMPANY_TYPE")
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name="OBJECT_TYPE")
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Column(name="FILE_TYPE")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column
	public String getSjfk() {
		return sjfk;
	}

	public void setSjfk(String sjfk) {
		this.sjfk = sjfk;
	}
	@Column
	public String getZltc() {
		return zltc;
	}

	public void setZltc(String zltc) {
		this.zltc = zltc;
	}

}
