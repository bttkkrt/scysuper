package com.jshx.zywsxzxk.entity;

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
@Table(name="ZYWSXZXK")
public class Zywsxzxk extends BaseModel
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
	 * 状态
	 */
	private String state;
	/**
	 * 附件关联id
	 */
	private String linkid;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 审核记录
	 */
	private String shenhe;
	
	/**
	 * 审核人
	 */
	private String shuserid;
	
	/**
	 * 审核人名称
	 */
	private String shusername;
	
	/**
	 * 是否完结 
	 */
	private String isfinish;
	
	private String szc;
	
	private String szcname;
	
	
	/**
	 * 申请材料是否齐全
	 */
	private String isCaiLiao;
	
	/**
	 * 签字领导
	 */
	private String qzld;     	

	/**
	 * 乡镇预审意见
	 */
	private String xzysyj;     	

	/**
	 * 现场核查部门
	 */
	private String xchcbm;     	
	
	/**
	 * 核查结论
	 */
	private String hcjl;       
	 
	/**
	 * 是否储存涉及
	 */
	private String isCunChu;     
	
	/**
	 * 材料审查情况
	 */
	private String clscqk;      
	
	/**
	 * 本次领证情况
	 */
	private String bclzqk;      
	 
	/**
	 * 行政许可建议
	 */
	private String xzxkjy;      
	 
	/**
	 * 局会审记录
	 */
	private String jhsjl;      
	
	/**
	 * 新增字段 lj 2014-06-30
	 * @return start
	 */
	private String projectContent;//项目内容
	private String pjjg;//评价单位
	private String yszj;//验收专家
	private String ysrq;//验收日期
    private  String xmxz;//项目性质（新建、改建、扩建）
    private String sprq;//审批日期
    private String spbh;//审批编号 
    private String fxfl ;//职业病危害风险分类
    private String scjg;//审查结果
    private String jsrq;//材料接受日期
    private String sjrq;//材料上报市局日期
	/**
	 * end 
	 * @return
	 */
    
    /**
	 * 材料接收人员
	 */
    private String cljsry;
    
    /**
	 * 材料审查人员
	 */
    private String clscry;
    
    /**
	 * 档案编号
	 */
    private String fileId;
    
    
    /**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 直属等级
	 */
	private String zsqytype;
	
	private String dutyFlag;
	
	private String county;
    
    
    
	@Column
    public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	@Column
    public String getCljsry() {
		return cljsry;
	}
	public void setCljsry(String cljsry) {
		this.cljsry = cljsry;
	}
	
	@Column
	public String getClscry() {
		return clscry;
	}
	public void setClscry(String clscry) {
		this.clscry = clscry;
	}
	@Column
	public String getSzc() {
		return szc;
	}
    @Column
    public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	@Column
	public String getSjrq() {
		return sjrq;
	}
	public void setSjrq(String sjrq) {
		this.sjrq = sjrq;
	}
	@Column
    public String getScjg() {
		return scjg;
	}

	public void setScjg(String scjg) {
		this.scjg = scjg;
	}

	@Column
    public String getFxfl() {
		return fxfl;
	}
	public void setFxfl(String fxfl) {
		this.fxfl = fxfl;
	}
	@Column
	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	@Column
	public String getPjjg() {
		return pjjg;
	}

	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}
	@Column
	public String getYszj() {
		return yszj;
	}

	public void setYszj(String yszj) {
		this.yszj = yszj;
	}
	@Column
	public String getYsrq() {
		return ysrq;
	}

	public void setYsrq(String ysrq) {
		this.ysrq = ysrq;
	}
	@Column
	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}
	@Column
	public String getSprq() {
		return sprq;
	}

	public void setSprq(String sprq) {
		this.sprq = sprq;
	}
	@Column
	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
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
	public String getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(String isfinish) {
		this.isfinish = isfinish;
	}
	@Column
	public String getShuserid() {
		return shuserid;
	}

	public void setShuserid(String shuserid) {
		this.shuserid = shuserid;
	}
	@Column
	public String getShusername() {
		return shusername;
	}

	public void setShusername(String shusername) {
		this.shusername = shusername;
	}
	@Column(name="SHENHE")
	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column
	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
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

	@Column(name="STATE")
	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name="ISCAILIAO")
	public String getIsCaiLiao() {
		return isCaiLiao;
	}

	public void setIsCaiLiao(String isCaiLiao) {
		this.isCaiLiao = isCaiLiao;
	}

	@Column(name="QZLD")
	public String getQzld() {
		return qzld;
	}

	public void setQzld(String qzld) {
		this.qzld = qzld;
	}

	@Column(name="XZYSYJ")
	public String getXzysyj() {
		return xzysyj;
	}

	public void setXzysyj(String xzysyj) {
		this.xzysyj = xzysyj;
	}

	@Column(name="XCHCBM")
	public String getXchcbm() {
		return xchcbm;
	}

	public void setXchcbm(String xchcbm) {
		this.xchcbm = xchcbm;
	}

	@Column(name="HCJL")
	public String getHcjl() {
		return hcjl;
	}

	public void setHcjl(String hcjl) {
		this.hcjl = hcjl;
	}

	@Column(name="ISCUNCHU")
	public String getIsCunChu() {
		return isCunChu;
	}

	public void setIsCunChu(String isCunChu) {
		this.isCunChu = isCunChu;
	}

	@Column(name="CLSCQK")
	public String getClscqk() {
		return clscqk;
	}

	public void setClscqk(String clscqk) {
		this.clscqk = clscqk;
	}

	@Column(name="BCLZQK")
	public String getBclzqk() {
		return bclzqk;
	}

	public void setBclzqk(String bclzqk) {
		this.bclzqk = bclzqk;
	}

	@Column(name="XZXKJY")
	public String getXzxkjy() {
		return xzxkjy;
	}

	public void setXzxkjy(String xzxkjy) {
		this.xzxkjy = xzxkjy;
	}

	@Column(name="JHSJL")
	public String getJhsjl() {
		return jhsjl;
	}

	public void setJhsjl(String jhsjl) {
		this.jhsjl = jhsjl;
	}
	
	
	@Column(name="IFZSQY")
	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}
	@Column(name="ZSQYTYPE")
	public String getZsqytype() {
		return zsqytype;
	}

	public void setZsqytype(String zsqytype) {
		this.zsqytype = zsqytype;
	}
	@Column(name="DUTY_FLAG")
	public String getDutyFlag() {
		return dutyFlag;
	}

	public void setDutyFlag(String dutyFlag) {
		this.dutyFlag = dutyFlag;
	}
	@Column(name="COUNTY")
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	
}
