package com.jshx.tzzyPxb.entity;

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
@Table(name="JSHX_TZZY_PXB")
public class JshxTzzyPxb extends BaseModel
{
	/**
	 * 新增字段 理论成绩 llcj
	 */
	private String llcj;
	/**
	 * 新增字段 实践成绩  sjcj
	 */
	private String sjcj;
	/**
	 * 新增字段 培训时间 sj
	 * @return
	 */
	private String sj;
	/**
	 * 新增字段 特种证号 tzzh
	 * @return
	 */
	private String tzzh;
	/**
	 * 新增字段 备注 bz
	 * @return
	 */
	private String bz;
	/**
	 * 新增字段 有效时间
	 * @return
	 */
	private String yxsj;
	
	
	
	@Column
	public String getYxsj() {
		return yxsj;
	}

	public void setYxsj(String yxsj) {
		this.yxsj = yxsj;
	}

	@Column
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	@Column
	public String getTzzh() {
		return tzzh;
	}

	public void setTzzh(String tzzh) {
		this.tzzh = tzzh;
	}

	@Column
	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	@Column
	public String getLlcj() {
		return llcj;
	}

	public void setLlcj(String llcj) {
		this.llcj = llcj;
	}
	@Column
	public String getSjcj() {
		return sjcj;
	}

	public void setSjcj(String sjcj) {
		this.sjcj = sjcj;
	}
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 所在镇名称
	 */
	private String szzname;

	/**
	 * 企业名称
	 */
	private String qymc;

	/**
	 * 培训人姓名
	 */
	private String personName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 身份证
	 */
	private String sfz;

	/**
	 * 工种
	 */
	private String gz;
	
	private String gzxl;

	/**
	 * 学历
	 */
	private String xl;
	
	private String lzrq;
	
	private String qylx;

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

	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
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

	@Column(name="PERSON_NAME")
	public String getPersonName()
	{
		return this.personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}

	@Column(name="SEX")
	public String getSex()
	{
		return this.sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	@Column(name="SFZ")
	public String getSfz()
	{
		return this.sfz;
	}

	public void setSfz(String sfz)
	{
		this.sfz = sfz;
	}


	@Column(name="GZ")
	public String getGz()
	{
		return this.gz;
	}

	public void setGz(String gz)
	{
		this.gz = gz;
	}

	@Column(name="XL")
	public String getXl()
	{
		return this.xl;
	}

	public void setXl(String xl)
	{
		this.xl = xl;
	}

	@Column(name="GZXL")
	public String getGzxl() {
		return gzxl;
	}

	public void setGzxl(String gzxl) {
		this.gzxl = gzxl;
	}
	@Column
	public String getLzrq() {
		return lzrq;
	}

	public void setLzrq(String lzrq) {
		this.lzrq = lzrq;
	}
	@Column
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}

}
