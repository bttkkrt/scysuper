package com.jshx.ygtjbghzb.entity;

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
@Table(name="YGTJBGHZB")
public class Ygtjbghzb extends BaseModel
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
	 * 企业名称
	 */
	private String qymc;

	/**
	 * 企业id
	 */
	private String comid;

	/**
	 * 所在街道
	 */
	private String szz;

	/**
	 * 员工体检情况汇总表名称
	 */
	private String hzname;
	//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
	private String qylx;
	private String hyfl;
	private String qygm;
	private String qyzclx;
	private String linkid;
	private String uploadtime;
	
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
	
	
	/**
	 * 总人数
	 */
	private String zrs;

	/**
	 * 体检时间
	 */
	private String tjsj;
	
	/**
	 * 体检单位
	 */
	private String tjdw;
	
	private String zcrs;
	
	/**
	 * 疑似职业病人数
	 */
	private String yszybrs;
	
	
	/**
	 * 职业病人数
	 */
	private String zybrs;
	
	/**
	 * 职业禁忌人数
	 */
	private String zyjjrs;
	/**
	 * 体检类型（上岗、在岗、离岗、应急
	 * 新增字段 lj 2014-07-02
	 * start
	 * @return
	 */
	
	private String tjlx;
	
	/**
	 * 体检结果（正常、职业相关异常、职业禁忌、疑似职业病人）
	 * @return lj 2014-07-02
	 */
	private String result;
	/**
	 * 体检机构 lj 2014-07-02
	 * 
	 * @return
	 */
	private String tjjg;
	
	/**
	 * end
	 * @return
	 */
	
	
	
	
	
	public String getZrs() {
		return zrs;
	}
	@Column
	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	@Column
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	@Column
	public String getTjjg() {
		return tjjg;
	}

	public void setTjjg(String tjjg) {
		this.tjjg = tjjg;
	}

	public void setZrs(String zrs) {
		this.zrs = zrs;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getTjdw() {
		return tjdw;
	}

	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}

	public String getYszybrs() {
		return yszybrs;
	}

	public void setYszybrs(String yszybrs) {
		this.yszybrs = yszybrs;
	}

	public String getZybrs() {
		return zybrs;
	}

	public void setZybrs(String zybrs) {
		this.zybrs = zybrs;
	}

	public String getZyjjrs() {
		return zyjjrs;
	}

	public void setZyjjrs(String zyjjrs) {
		this.zyjjrs = zyjjrs;
	}

	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}

	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}

	public String getQygm() {
		return qygm;
	}

	public void setQygm(String qygm) {
		this.qygm = qygm;
	}

	public String getQyzclx() {
		return qyzclx;
	}

	public void setQyzclx(String qyzclx) {
		this.qyzclx = qyzclx;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
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

	
	@Column(name="QYMC")
	public String getQymc()
	{
		return this.qymc;
	}

	public void setQymc(String qymc)
	{
		this.qymc = qymc;
	}

	@Column(name="COMID")
	public String getComid()
	{
		return this.comid;
	}

	public void setComid(String comid)
	{
		this.comid = comid;
	}

	@Column(name="SZZ")
	public String getSzz()
	{
		return this.szz;
	}

	public void setSzz(String szz)
	{
		this.szz = szz;
	}

	@Column(name="HZNAME")
	public String getHzname()
	{
		return this.hzname;
	}

	public void setHzname(String hzname)
	{
		this.hzname = hzname;
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
	@Column
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}
	@Column
	public String getIfzywhqylx() {
		return ifzywhqylx;
	}

	public void setIfzywhqylx(String ifzywhqylx) {
		this.ifzywhqylx = ifzywhqylx;
	}
	@Column
	public String getIfyhbzjyqy() {
		return ifyhbzjyqy;
	}

	public void setIfyhbzjyqy(String ifyhbzjyqy) {
		this.ifyhbzjyqy = ifyhbzjyqy;
	}
	@Column
	public String getZcrs() {
		return zcrs;
	}
	public void setZcrs(String zcrs) {
		this.zcrs = zcrs;
	}


}
