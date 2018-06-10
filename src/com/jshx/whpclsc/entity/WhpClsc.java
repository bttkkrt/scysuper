package com.jshx.whpclsc.entity;

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
@Table(name="WHP_CLSC")
public class WhpClsc extends BaseModel
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
	 * 类型标示
	 */
	private String sclx;
	
	/**
	 * 审查状态
	 */
	private String state;
	/**
	 * 审核备注
	 */
	private String remark;
	
	/**
	 * 附件关联id
	 */
	private String linkId;
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
	 *  评价机构 
	 */
	private String pjjg;  
	
	/**
	 *   材料接收日期 
	 */
	private Date cljsrq;
	/**
	 *  材料上报市局日期 
	 */
	private Date clsbsjrq;
	 
	
	
	/**
	 *  受理材料日期
	 */
	private Date slclrq;
	/**
	 *  许可证有效期
	 */
	private Date xkzyxq;
	/**
	 * 发证单位
	 */
	private String fzdw;  
	
	
	
	
	/**
	 * 项目内容
	 */
	private String xmnr;  
	/**
	 * 审查专家
	 */
	private String sczj;  
	/**
	 * 审查结果
	 */
	private String scjg;  
	/**
	 * 审批编号
	 */
	private String spbh;  
	/**
	 * 项目类型
	 */
	private String xmlx;  
	/**
	 * 设计单位
	 */
	private String sjdw;  
	/**
	 * 备案编号
	 */
	private String babh;  
	/**
	 * 评价单位
	 */
	private String pjdw;  
	/**
	 * 验收专家
	 */
	private String yszj;  
	/**
	 * 项目性质	
	 */
	private String xmxz;  
	
	
	/**
	 *  审查日期
	 */
	private Date scrq; 
	/**
	 *  审批日期
	 */
	private Date sprq; 
	/**
	 *  备案日期
	 */
	private Date barq; 
	/**
	 *  验收日期 
	 */
	private Date ysrq; 
	 
	
	/**
	 *  有效期
	 */
	private Date yxq; 
	/**
	 * 发证机关
	 */
	private String fzjg;  
	/**
	 * 易制毒化学品
	 */
	private String yzdhxp;  
	
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
	 * 生产证或使用证
	 */
	private String sczhsyz;
	
	/**
	 * 经营方式
	 */
	private String jyfs;
	
	/**
	 * 经营类型
	 */
	private String jylx;
	
	
	
	
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
	public String getJyfs() {
		return jyfs;
	}

	public void setJyfs(String jyfs) {
		this.jyfs = jyfs;
	}

	@Column
	public String getJylx() {
		return jylx;
	}

	public void setJylx(String jylx) {
		this.jylx = jylx;
	}

	@Column
	public String getSczhsyz() {
		return sczhsyz;
	}

	public void setSczhsyz(String sczhsyz) {
		this.sczhsyz = sczhsyz;
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
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	
	@Column(name="STATE")
	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column
	public String getSclx() {
		return sclx;
	}

	public void setSclx(String sclx) {
		this.sclx = sclx;
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

	@Column(name="PJJG")
	public String getPjjg() {
		return pjjg;
	}

	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}

	@Column(name="CLJSRQ")
	public Date getCljsrq() {
		return cljsrq;
	}

	public void setCljsrq(Date cljsrq) {
		this.cljsrq = cljsrq;
	}

	@Column(name="CLSBSJRQ")
	public Date getClsbsjrq() {
		return clsbsjrq;
	}

	public void setClsbsjrq(Date clsbsjrq) {
		this.clsbsjrq = clsbsjrq;
	}

	@Column(name="SLCLRQ")
	public Date getSlclrq() {
		return slclrq;
	}

	public void setSlclrq(Date slclrq) {
		this.slclrq = slclrq;
	}

	@Column(name="XKZYXQ")
	public Date getXkzyxq() {
		return xkzyxq;
	}

	public void setXkzyxq(Date xkzyxq) {
		this.xkzyxq = xkzyxq;
	}

	@Column(name="FZDW")
	public String getFzdw() {
		return fzdw;
	}

	public void setFzdw(String fzdw) {
		this.fzdw = fzdw;
	}
	
	
	@Column(name="XMNR")
	public String getXmnr() {
		return xmnr;
	}

	public void setXmnr(String xmnr) {
		this.xmnr = xmnr;
	}

	@Column(name="SCZJ")
	public String getSczj() {
		return sczj;
	}

	public void setSczj(String sczj) {
		this.sczj = sczj;
	}

	@Column(name="SCJG")
	public String getScjg() {
		return scjg;
	}

	public void setScjg(String scjg) {
		this.scjg = scjg;
	}

	@Column(name="SPBH")
	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	@Column(name="XMLX")
	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	@Column(name="SJDW")
	public String getSjdw() {
		return sjdw;
	}

	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}

	@Column(name="BABH")
	public String getBabh() {
		return babh;
	}

	public void setBabh(String babh) {
		this.babh = babh;
	}

	@Column(name="PJDW")
	public String getPjdw() {
		return pjdw;
	}

	public void setPjdw(String pjdw) {
		this.pjdw = pjdw;
	}

	@Column(name="YSZJ")
	public String getYszj() {
		return yszj;
	}

	public void setYszj(String yszj) {
		this.yszj = yszj;
	}

	@Column(name="SCRQ")
	public Date getScrq() {
		return scrq;
	}

	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}

	@Column(name="SPRQ")
	public Date getSprq() {
		return sprq;
	}

	public void setSprq(Date sprq) {
		this.sprq = sprq;
	}

	@Column(name="BARQ")
	public Date getBarq() {
		return barq;
	}

	public void setBarq(Date barq) {
		this.barq = barq;
	}

	@Column(name="YSRQ")
	public Date getYsrq() {
		return ysrq;
	}

	public void setYsrq(Date ysrq) {
		this.ysrq = ysrq;
	}

	@Column(name="XMXZ")
	public String getXmxz() {
		return xmxz;
	}

	public void setXmxz(String xmxz) {
		this.xmxz = xmxz;
	}
	@Column
	public Date getYxq() {
		return yxq;
	}

	public void setYxq(Date yxq) {
		this.yxq = yxq;
	}
	@Column
	public String getFzjg() {
		return fzjg;
	}

	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}
	@Column
	public String getYzdhxp() {
		return yzdhxp;
	}

	public void setYzdhxp(String yzdhxp) {
		this.yzdhxp = yzdhxp;
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
