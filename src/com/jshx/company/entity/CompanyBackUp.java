package com.jshx.company.entity;

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
@Table(name="CompanyBackUp")
public class CompanyBackUp extends BaseModel
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
	 * 企业类型
	 */
	private String qylx;

	/**
	 * 行业分类
	 */
	private String hyfl;
	
	/**
	 * 行业一级分类
	 */
	private String hyflOneLevel;
	/**
	 * 行业二级分类
	 */
	private String hyflTwoLevel;
	/**
	 * 行业三级分类
	 */
	private String hyflThreeLevel;
	/**
	 * 行业四级分类
	 */
	private String hyflFourLevel;

	/**
	 * 安全管理员
	 */
	private String lxr;
	
	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 联系方式
	 */
	private String lxfs;

	/**
	 * 验证码
	 */
	private String yzm;

	/**
	 * 企业邮箱
	 */
	private String qyyx;

	/**
	 * 企业规模
	 */
	private String qygm;

	/**
	 * 企业注册类型
	 */
	private String qyzclx;

	/**
	 * 注册资金（万元）
	 */
	private String zczj;

	/**
	 * 危化品企业类型
	 */
	private String whpqylx;
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
	 * 烟花爆竹经营企业
	 */
	private String yhbzjyqy;
	/**
	 * 职业危害企业类型
	 */
	private String zywhqylx;

	/**
	 * 法人代码
	 */
	private String frdm;

	/**
	 * 企业成立时间
	 */
	private Date qyclsj;

	/**
	 * 固定电话
	 */
	private String gddh;

	/**
	 * 传       真
	 */
	private String cz;

	/**
	 * 邮政编码
	 */
	private String yzbm;

	/**
	 * 上年销售收入（万元）
	 */
	private String snxssr;

	/**
	 * 上年上交税收（万元）
	 */
	private String snsjss;

	/**
	 * 上年固定资产
	 */
	private String sngdzc;

	/**
	 * 上年安全投入
	 */
	private String snwqtr;

	/**
	 * 上年安全生产费用提取数（万元）
	 */
	private String snaqscf;

	/**
	 * 是否设立安全管理机构
	 */
	private String sfaqjg;

	/**
	 * 安全管理员（人）
	 */
	private Long aqglr;

	/**
	 * 是否设立职业卫生管理机构
	 */
	private String sfzywsjg;

	/**
	 * 职业卫生管理人员（人）
	 */
	private Long zywsglry;

	/**
	 * 否为专职或兼职职业卫生管理员
	 */
	private String sfqzwsgly;

	/**
	 * 占地面积（m2）
	 */
	private String zdmj;

	/**
	 * 建筑面积（m2）
	 */
	private String jzmj;

	/**
	 * 从业人员（人）
	 */
	private Long cyry;

	/**
	 * 注册用户名
	 */
	private String loginname;

	/**
	 * 注册密码
	 */
	private String loginword;

	/**
	 * 单位名称
	 */
	private String companyname;

	/**
	 * 法定代表人
	 */
	private String fddbr;
	
	/**
	 * 法定代表人联系方式
	 */
	private String fddbrlxhm;

	/**
	 * 单位地址
	 */
	private String dwdz;
	/**
	 * 镇，开发区等
	 */
	private String dwdz1;
	/**
	 * 村
	 */
	private String dwdz2;

	/**
	 * 组织机构代码
	 */
	private String zzjgdm;

	/**
	 * 工商注册号
	 */
	private String gszch;

	/**
	 * 是否有员工宿舍
	 */
	private String sfyygss;

	/**
	 * 安全生产标准化达标级别
	 */
	private String aqbzdbjb;

	/**
	 * 经营范围
	 */
	private String jyfw;
	 /**
     * 基本信息是否审核通过  0:待审核  1:通过 2:未通过 3:修改过上报待审核
     * @return
     */
	private int basePass=0;
	/**
	 * 基本信息的未通过备注
	 */
	private String baseRemark;
	
	private String url;
	
	private String ifurl;
	
	private String companyId;
	
	private String szc;
	
	private String szcname;
	
	private String binduserid;
	
	private String bindusername;
	
	private String longitude;
	
	private String latitude;
	
	//hanxc 20141210 修改 start
	/**
	 * 是否非煤矿山企业
	 */
	private String iffmksjyqy;
	/**
	 * 非煤矿山企业
	 */
	private String fmksjyqy;
	//hanxc 20141210 修改 end
	//hanxc 20141212 为抚顺版本添加字段添加 start
	/**
	 * 矿山类型
	 */
	private String diggingstype;
	/**
	 * 金属属性
	 */
	private String metal;
	/**
	 * 通风方式
	 */
	private String ventilate;
	/**
	 * 运输方式
	 */
	private String transport;
	/**
	 *  提升方式
	 */
	private String raisetype;
	/**
	 * 六大系统情况
	 */
	private String sixsys;
	
	/**
	 * 所在县
	 */
	private String county;
	/**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 直属等级
	 */
	private String zsqytype;
	
	/**
	 * 注册编码
	 */
	private String zcCode;
	
	/**
	 * 审核岗位标记
	 */
	private String dutyFlag;
	/**
	 * 企业所属监管类型
	 */
	private String companyType;

	/**
	 * 安全生产许可证号
	 */
	private String aqscxkzh;

	/**
	 * 企业特征
	 */
	private String feature;

	//hanxc 20141212 为抚顺版本添加字段添加 end
	/**
	 * 数据上报id
	 */
	private String guid;
	
	public String getAqscxkzh() {
		return aqscxkzh;
	}
	
	public void setAqscxkzh(String aqscxkzh) {
		this.aqscxkzh = aqscxkzh;
	}
	
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	@Column
	public String getBaseRemark() {
		return baseRemark;
	}

	public void setBaseRemark(String baseRemark) {
		this.baseRemark = baseRemark;
	}

	@Column
	public int getBasePass() {
		return basePass;
	}

	public void setBasePass(int basePass) {
		this.basePass = basePass;
	}

	@Column
	public String getDeptId()
	{
		return deptId;
	}
	@Column
	public String getDwdz1() {
		return dwdz1;
	}

	public void setDwdz1(String dwdz1) {
		this.dwdz1 = dwdz1;
	}
	@Column
	public String getDwdz2() {
		return dwdz2;
	}

	public void setDwdz2(String dwdz2) {
		this.dwdz2 = dwdz2;
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

	@Column(name="LXR")
	public String getLxr()
	{
		return this.lxr;
	}

	public void setLxr(String lxr)
	{
		this.lxr = lxr;
	}

	@Column(name="LXFS")
	public String getLxfs()
	{
		return this.lxfs;
	}

	public void setLxfs(String lxfs)
	{
		this.lxfs = lxfs;
	}

	@Column(name="YZM")
	public String getYzm()
	{
		return this.yzm;
	}

	public void setYzm(String yzm)
	{
		this.yzm = yzm;
	}

	@Column(name="QYYX")
	public String getQyyx()
	{
		return this.qyyx;
	}

	public void setQyyx(String qyyx)
	{
		this.qyyx = qyyx;
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

	@Column(name="ZCZJ")
	public String getZczj()
	{
		return this.zczj;
	}

	public void setZczj(String zczj)
	{
		this.zczj = zczj;
	}

	@Column(name="WHPQYLX")
	public String getWhpqylx()
	{
		return this.whpqylx;
	}

	public void setWhpqylx(String whpqylx)
	{
		this.whpqylx = whpqylx;
	}

	@Column(name="ZYWHQYLX")
	public String getZywhqylx()
	{
		return this.zywhqylx;
	}

	public void setZywhqylx(String zywhqylx)
	{
		this.zywhqylx = zywhqylx;
	}

	@Column(name="FRDM")
	public String getFrdm()
	{
		return this.frdm;
	}

	public void setFrdm(String frdm)
	{
		this.frdm = frdm;
	}

	@Column(name="QYCLSJ")
	public Date getQyclsj()
	{
		return this.qyclsj;
	}

	public void setQyclsj(Date qyclsj)
	{
		this.qyclsj = qyclsj;
	}

	@Column(name="GDDH")
	public String getGddh()
	{
		return this.gddh;
	}

	public void setGddh(String gddh)
	{
		this.gddh = gddh;
	}

	@Column(name="CZ")
	public String getCz()
	{
		return this.cz;
	}

	public void setCz(String cz)
	{
		this.cz = cz;
	}

	@Column(name="YZBM")
	public String getYzbm()
	{
		return this.yzbm;
	}

	public void setYzbm(String yzbm)
	{
		this.yzbm = yzbm;
	}

	@Column(name="SNXSSR")
	public String getSnxssr()
	{
		return this.snxssr;
	}

	public void setSnxssr(String snxssr)
	{
		this.snxssr = snxssr;
	}

	@Column(name="SNSJSS")
	public String getSnsjss()
	{
		return this.snsjss;
	}

	public void setSnsjss(String snsjss)
	{
		this.snsjss = snsjss;
	}

	@Column(name="SNGDZC")
	public String getSngdzc()
	{
		return this.sngdzc;
	}

	public void setSngdzc(String sngdzc)
	{
		this.sngdzc = sngdzc;
	}

	@Column(name="SNWQTR")
	public String getSnwqtr()
	{
		return this.snwqtr;
	}

	public void setSnwqtr(String snwqtr)
	{
		this.snwqtr = snwqtr;
	}

	@Column(name="SNAQSCF")
	public String getSnaqscf()
	{
		return this.snaqscf;
	}

	public void setSnaqscf(String snaqscf)
	{
		this.snaqscf = snaqscf;
	}

	@Column(name="SFAQJG")
	public String getSfaqjg()
	{
		return this.sfaqjg;
	}

	public void setSfaqjg(String sfaqjg)
	{
		this.sfaqjg = sfaqjg;
	}

	@Column(name="AQGLR")
	public Long getAqglr()
	{
		return this.aqglr;
	}

	public void setAqglr(Long aqglr)
	{
		this.aqglr = aqglr;
	}

	@Column(name="SFZYWSJG")
	public String getSfzywsjg()
	{
		return this.sfzywsjg;
	}

	public void setSfzywsjg(String sfzywsjg)
	{
		this.sfzywsjg = sfzywsjg;
	}

	@Column(name="ZYWSGLRY")
	public Long getZywsglry()
	{
		return this.zywsglry;
	}

	public void setZywsglry(Long zywsglry)
	{
		this.zywsglry = zywsglry;
	}

	@Column(name="SFQZWSGLY")
	public String getSfqzwsgly()
	{
		return this.sfqzwsgly;
	}

	public void setSfqzwsgly(String sfqzwsgly)
	{
		this.sfqzwsgly = sfqzwsgly;
	}

	@Column(name="ZDMJ")
	public String getZdmj()
	{
		return this.zdmj;
	}

	public void setZdmj(String zdmj)
	{
		this.zdmj = zdmj;
	}

	@Column(name="JZMJ")
	public String getJzmj()
	{
		return this.jzmj;
	}

	public void setJzmj(String jzmj)
	{
		this.jzmj = jzmj;
	}

	@Column(name="CYRY")
	public Long getCyry()
	{
		return this.cyry;
	}

	public void setCyry(Long cyry)
	{
		this.cyry = cyry;
	}

	@Column(name="LOGINNAME")
	public String getLoginname()
	{
		return this.loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}

	@Column(name="LOGINWORD")
	public String getLoginword()
	{
		return this.loginword;
	}

	public void setLoginword(String loginword)
	{
		this.loginword = loginword;
	}

	@Column(name="COMPANYNAME")
	public String getCompanyname()
	{
		return this.companyname;
	}

	public void setCompanyname(String companyname)
	{
		this.companyname = companyname;
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

	@Column(name="DWDZ")
	public String getDwdz()
	{
		return this.dwdz;
	}

	public void setDwdz(String dwdz)
	{
		this.dwdz = dwdz;
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

	@Column(name="GSZCH")
	public String getGszch()
	{
		return this.gszch;
	}

	public void setGszch(String gszch)
	{
		this.gszch = gszch;
	}

	@Column(name="SFYYGSS")
	public String getSfyygss()
	{
		return this.sfyygss;
	}

	public void setSfyygss(String sfyygss)
	{
		this.sfyygss = sfyygss;
	}

	@Column(name="AQBZDBJB")
	public String getAqbzdbjb()
	{
		return this.aqbzdbjb;
	}

	public void setAqbzdbjb(String aqbzdbjb)
	{
		this.aqbzdbjb = aqbzdbjb;
	}

	@Column(name="JYFW")
	public String getJyfw()
	{
		return this.jyfw;
	}

	public void setJyfw(String jyfw)
	{
		this.jyfw = jyfw;
	}
	@Column(name="FDDBRLXHM")
	public String getFddbrlxhm() {
		return fddbrlxhm;
	}

	public void setFddbrlxhm(String fddbrlxhm) {
		this.fddbrlxhm = fddbrlxhm;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	@Column(name="URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="IFURL")
	public String getIfurl() {
		return ifurl;
	}

	public void setIfurl(String ifurl) {
		this.ifurl = ifurl;
	}
	@Column(name="COMPANYID")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getIfyhbzjyqy() {
		return ifyhbzjyqy;
	}

	public void setIfyhbzjyqy(String ifyhbzjyqy) {
		this.ifyhbzjyqy = ifyhbzjyqy;
	}

	public String getYhbzjyqy() {
		return yhbzjyqy;
	}

	public void setYhbzjyqy(String yhbzjyqy) {
		this.yhbzjyqy = yhbzjyqy;
	}
	@Column(name="SZC")
	public String getSzc() {
		return szc;
	}

	public void setSzc(String szc) {
		this.szc = szc;
	}
	@Column(name="SZCNAME")
	public String getSzcname() {
		return szcname;
	}

	public void setSzcname(String szcname) {
		this.szcname = szcname;
	}
	@Column
	public String getBinduserid() {
		return binduserid;
	}

	public void setBinduserid(String binduserid) {
		this.binduserid = binduserid;
	}
	@Column
	public String getBindusername() {
		return bindusername;
	}

	public void setBindusername(String bindusername) {
		this.bindusername = bindusername;
	}
	@Column
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	
	@Column(name="IFFMKSJYQY")
	public String getIffmksjyqy() {
		return iffmksjyqy;
	}

	public void setIffmksjyqy(String iffmksjyqy) {
		this.iffmksjyqy = iffmksjyqy;
	}
	
	@Column(name="FMKSJYQY")
	public String getFmksjyqy() {
		return fmksjyqy;
	}

	public void setFmksjyqy(String fmksjyqy) {
		this.fmksjyqy = fmksjyqy;
	}
	

	public String getDiggingstype() {
		return diggingstype;
	}

	public void setDiggingstype(String diggingstype) {
		this.diggingstype = diggingstype;
	}

	public String getMetal() {
		return metal;
	}

	public void setMetal(String metal) {
		this.metal = metal;
	}

	public String getVentilate() {
		return ventilate;
	}

	public void setVentilate(String ventilate) {
		this.ventilate = ventilate;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getRaisetype() {
		return raisetype;
	}

	public void setRaisetype(String raisetype) {
		this.raisetype = raisetype;
	}

	public String getSixsys() {
		return sixsys;
	}

	public void setSixsys(String sixsys) {
		this.sixsys = sixsys;
	}
	@Column(name="county")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	@Column(name="ifzsqy")
	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}

	@Column(name="zsqytype")
	public String getZsqytype() {
		return zsqytype;
	}

	public void setZsqytype(String zsqytype) {
		this.zsqytype = zsqytype;
	}

	@Column(name="zc_CODE")
	public String getZcCode() {
		return zcCode;
	}

	public void setZcCode(String zcCode) {
		this.zcCode = zcCode;
	}

	@Column(name="DUTY_FLAG")
	public String getDutyFlag() {
		return dutyFlag;
	}

	public void setDutyFlag(String dutyFlag) {
		this.dutyFlag = dutyFlag;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	@Column(name="GUID")
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	@Column(name="HYFLONELEVEL")
	public String getHyflOneLevel() {
		return hyflOneLevel;
	}

	public void setHyflOneLevel(String hyflOneLevel) {
		this.hyflOneLevel = hyflOneLevel;
	}

	@Column(name="HYFLTWOLEVEL")
	public String getHyflTwoLevel() {
		return hyflTwoLevel;
	}

	public void setHyflTwoLevel(String hyflTwoLevel) {
		this.hyflTwoLevel = hyflTwoLevel;
	}

	@Column(name="HYFLTHREELEVEL")
	public String getHyflThreeLevel() {
		return hyflThreeLevel;
	}

	public void setHyflThreeLevel(String hyflThreeLevel) {
		this.hyflThreeLevel = hyflThreeLevel;
	}

	@Column(name="HYFLFOURLEVEL")
	public String getHyflFourLevel() {
		return hyflFourLevel;
	}

	public void setHyflFourLevel(String hyflFourLevel) {
		this.hyflFourLevel = hyflFourLevel;
	}
}
