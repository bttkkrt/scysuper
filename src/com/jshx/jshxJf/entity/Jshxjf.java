package com.jshx.jshxJf.entity;

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
@Table(name="JSHXJF")
public class Jshxjf extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	private String zczj1;//自查自纠1月
	private String zczj2;//自查自纠2月
	private String zczj3;//自查自纠3月
	private String zczj4;//自查自纠4月
	private String zczj5;//自查自纠5月
	private String zczj6;//自查自纠6月
	private String zczj7;//自查自纠7月
	private String zczj8;//自查自纠8月
	private String zczj9;//自查自纠9月
	private String zczj10;//自查自纠10月
	private String zczj11;//自查自纠11月
	private String zczj12;//自查自纠12月
	private String bzhdb;//标准化达标
	private String qyjbqk;//企业基本情况 
	private String xzxk;//行政许可
	private String cqtp;//厂区图片
	private String gzwz;//关键装置和重点部位
	private String aqjs;//安全警示标志设置情况
	private String ldfh;//劳动防护用品配备情况
	private String aqscjf;//安全生产经费投入
	private String bzh;//安全生产标准化达标情况
	private String sgtj;//事故统计
	private String aqsczrx;//安全生产责任险
	private String xygpx;//新入厂员工培训
	private String zzxgpx;//员工再培训及转岗、下岗、脱岗培训
	private String gwbm;//岗位和职能部门责任制
	private String aqsczd;//安全生产规章制度
	private String gwaq;//岗位安全操作规程
	private String whpaqgl;//危化品企业安全管理制度
	private String zywsaqgl;//职业卫生安全管理制度
	private String qysclc;//企业生产流程图
	private String tzsb;//特种设备情况
	private String qysb;//企业设备一览表
	private String aqfj;//特种设备安全附件情况
	private String xfss;//消防设施分布图
	private String aqss;//安全设施台帐
	private String whpxzxk;//危险化学品建设项目行政许可
	private String lzbg;//主要负责人履职报告
	private String whpbagzs;//危险化学品事故应急救援预案及备案告知书
	private String yjwz;//应急装备、物资和药品
	private String whpba;//危险化学品重大危险源备案
	private String whphx;//危险化学品重大危险源核销
	private String aqpj;//安全评价报告
	private String zybtjbg;//职业病健康体检报告
	private String zycswhys;//作业场所危害因素检测报告
	private String zywsjbxx;//职业卫生基本信息
	private String whys;//危害因素基本情况
	private String zycs;//作业场所情况
	private String zyjkjh;//职业健康监护
	private String zybwhys;//职业病危害因素监测
	private String ndzyb;//年度职业病防治经费
	private String aqscsg;//安全生产事故和行政处罚
	private String sgyhjb;//员工举报事故隐患奖励
	private String wjyd;//文件及法规收阅
	private String type;//表示初审、复审
	private String jfkfxm;//加分扣分项目
	private String jfkfly;//加分扣分理由
	private String zf;//总分
	private String companyId;//企业id
	private String year;//年份

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
	@Column
	public String getZczj1() {
		return zczj1;
	}

	public void setZczj1(String zczj1) {
		this.zczj1 = zczj1;
	}
	@Column
	public String getZczj2() {
		return zczj2;
	}

	public void setZczj2(String zczj2) {
		this.zczj2 = zczj2;
	}
	@Column
	public String getZczj3() {
		return zczj3;
	}

	public void setZczj3(String zczj3) {
		this.zczj3 = zczj3;
	}
	@Column
	public String getZczj4() {
		return zczj4;
	}

	public void setZczj4(String zczj4) {
		this.zczj4 = zczj4;
	}
	@Column
	public String getZczj5() {
		return zczj5;
	}

	public void setZczj5(String zczj5) {
		this.zczj5 = zczj5;
	}
	@Column
	public String getZczj6() {
		return zczj6;
	}

	public void setZczj6(String zczj6) {
		this.zczj6 = zczj6;
	}
	@Column
	public String getZczj7() {
		return zczj7;
	}

	public void setZczj7(String zczj7) {
		this.zczj7 = zczj7;
	}
	@Column
	public String getZczj8() {
		return zczj8;
	}

	public void setZczj8(String zczj8) {
		this.zczj8 = zczj8;
	}
	@Column
	public String getZczj9() {
		return zczj9;
	}

	public void setZczj9(String zczj9) {
		this.zczj9 = zczj9;
	}
	@Column
	public String getZczj10() {
		return zczj10;
	}

	public void setZczj10(String zczj10) {
		this.zczj10 = zczj10;
	}
	@Column
	public String getZczj11() {
		return zczj11;
	}

	public void setZczj11(String zczj11) {
		this.zczj11 = zczj11;
	}
	@Column
	public String getZczj12() {
		return zczj12;
	}

	public void setZczj12(String zczj12) {
		this.zczj12 = zczj12;
	}
	@Column
	public String getBzhdb() {
		return bzhdb;
	}

	public void setBzhdb(String bzhdb) {
		this.bzhdb = bzhdb;
	}
	@Column
	public String getQyjbqk() {
		return qyjbqk;
	}

	public void setQyjbqk(String qyjbqk) {
		this.qyjbqk = qyjbqk;
	}
	@Column
	public String getXzxk() {
		return xzxk;
	}

	public void setXzxk(String xzxk) {
		this.xzxk = xzxk;
	}
	@Column
	public String getCqtp() {
		return cqtp;
	}

	public void setCqtp(String cqtp) {
		this.cqtp = cqtp;
	}
	@Column
	public String getGzwz() {
		return gzwz;
	}

	public void setGzwz(String gzwz) {
		this.gzwz = gzwz;
	}
	@Column
	public String getAqjs() {
		return aqjs;
	}

	public void setAqjs(String aqjs) {
		this.aqjs = aqjs;
	}
	@Column
	public String getLdfh() {
		return ldfh;
	}

	public void setLdfh(String ldfh) {
		this.ldfh = ldfh;
	}
	@Column
	public String getAqscjf() {
		return aqscjf;
	}

	public void setAqscjf(String aqscjf) {
		this.aqscjf = aqscjf;
	}
	@Column
	public String getBzh() {
		return bzh;
	}

	public void setBzh(String bzh) {
		this.bzh = bzh;
	}
	@Column
	public String getSgtj() {
		return sgtj;
	}

	public void setSgtj(String sgtj) {
		this.sgtj = sgtj;
	}
	@Column
	public String getAqsczrx() {
		return aqsczrx;
	}

	public void setAqsczrx(String aqsczrx) {
		this.aqsczrx = aqsczrx;
	}
	@Column
	public String getXygpx() {
		return xygpx;
	}

	public void setXygpx(String xygpx) {
		this.xygpx = xygpx;
	}
	@Column
	public String getZzxgpx() {
		return zzxgpx;
	}

	public void setZzxgpx(String zzxgpx) {
		this.zzxgpx = zzxgpx;
	}
	@Column
	public String getGwbm() {
		return gwbm;
	}

	public void setGwbm(String gwbm) {
		this.gwbm = gwbm;
	}
	@Column
	public String getAqsczd() {
		return aqsczd;
	}

	public void setAqsczd(String aqsczd) {
		this.aqsczd = aqsczd;
	}
	@Column
	public String getGwaq() {
		return gwaq;
	}

	public void setGwaq(String gwaq) {
		this.gwaq = gwaq;
	}
	@Column
	public String getWhpaqgl() {
		return whpaqgl;
	}

	public void setWhpaqgl(String whpaqgl) {
		this.whpaqgl = whpaqgl;
	}
	@Column
	public String getZywsaqgl() {
		return zywsaqgl;
	}

	public void setZywsaqgl(String zywsaqgl) {
		this.zywsaqgl = zywsaqgl;
	}
	@Column
	public String getQysclc() {
		return qysclc;
	}

	public void setQysclc(String qysclc) {
		this.qysclc = qysclc;
	}
	@Column
	public String getTzsb() {
		return tzsb;
	}

	public void setTzsb(String tzsb) {
		this.tzsb = tzsb;
	}
	@Column
	public String getQysb() {
		return qysb;
	}

	public void setQysb(String qysb) {
		this.qysb = qysb;
	}
	@Column
	public String getAqfj() {
		return aqfj;
	}

	public void setAqfj(String aqfj) {
		this.aqfj = aqfj;
	}
	@Column
	public String getXfss() {
		return xfss;
	}

	public void setXfss(String xfss) {
		this.xfss = xfss;
	}
	@Column
	public String getAqss() {
		return aqss;
	}

	public void setAqss(String aqss) {
		this.aqss = aqss;
	}
	@Column
	public String getWhpxzxk() {
		return whpxzxk;
	}

	public void setWhpxzxk(String whpxzxk) {
		this.whpxzxk = whpxzxk;
	}
	@Column
	public String getLzbg() {
		return lzbg;
	}

	public void setLzbg(String lzbg) {
		this.lzbg = lzbg;
	}
	@Column
	public String getWhpbagzs() {
		return whpbagzs;
	}

	public void setWhpbagzs(String whpbagzs) {
		this.whpbagzs = whpbagzs;
	}
	@Column
	public String getYjwz() {
		return yjwz;
	}

	public void setYjwz(String yjwz) {
		this.yjwz = yjwz;
	}
	@Column
	public String getWhpba() {
		return whpba;
	}

	public void setWhpba(String whpba) {
		this.whpba = whpba;
	}
	@Column
	public String getWhphx() {
		return whphx;
	}

	public void setWhphx(String whphx) {
		this.whphx = whphx;
	}
	@Column
	public String getAqpj() {
		return aqpj;
	}

	public void setAqpj(String aqpj) {
		this.aqpj = aqpj;
	}
	@Column
	public String getZybtjbg() {
		return zybtjbg;
	}

	public void setZybtjbg(String zybtjbg) {
		this.zybtjbg = zybtjbg;
	}
	@Column
	public String getZycswhys() {
		return zycswhys;
	}

	public void setZycswhys(String zycswhys) {
		this.zycswhys = zycswhys;
	}
	@Column
	public String getZywsjbxx() {
		return zywsjbxx;
	}

	public void setZywsjbxx(String zywsjbxx) {
		this.zywsjbxx = zywsjbxx;
	}
	@Column
	public String getWhys() {
		return whys;
	}

	public void setWhys(String whys) {
		this.whys = whys;
	}
	@Column
	public String getZycs() {
		return zycs;
	}

	public void setZycs(String zycs) {
		this.zycs = zycs;
	}
	@Column
	public String getZyjkjh() {
		return zyjkjh;
	}

	public void setZyjkjh(String zyjkjh) {
		this.zyjkjh = zyjkjh;
	}
	@Column
	public String getZybwhys() {
		return zybwhys;
	}

	public void setZybwhys(String zybwhys) {
		this.zybwhys = zybwhys;
	}
	@Column
	public String getNdzyb() {
		return ndzyb;
	}

	public void setNdzyb(String ndzyb) {
		this.ndzyb = ndzyb;
	}
	@Column
	public String getAqscsg() {
		return aqscsg;
	}

	public void setAqscsg(String aqscsg) {
		this.aqscsg = aqscsg;
	}
	@Column
	public String getSgyhjb() {
		return sgyhjb;
	}

	public void setSgyhjb(String sgyhjb) {
		this.sgyhjb = sgyhjb;
	}
	@Column
	public String getWjyd() {
		return wjyd;
	}

	public void setWjyd(String wjyd) {
		this.wjyd = wjyd;
	}
	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column
	public String getJfkfxm() {
		return jfkfxm;
	}

	public void setJfkfxm(String jfkfxm) {
		this.jfkfxm = jfkfxm;
	}
	@Column
	public String getJfkfly() {
		return jfkfly;
	}

	public void setJfkfly(String jfkfly) {
		this.jfkfly = jfkfly;
	}
	@Column
	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}
	@Column
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Column
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
