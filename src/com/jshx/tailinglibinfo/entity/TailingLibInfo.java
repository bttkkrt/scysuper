package com.jshx.tailinglibinfo.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="TAILING_LIB_INFO")
public class TailingLibInfo extends BaseModel
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
	 * 具体位置
	 */
	private String baseLocal;

	/**
	 * 所属行业
	 */
	private String baseTrade;

	/**
	 * 库类型
	 */
	private String baseTailingType;

	/**
	 * 库级别
	 */
	private String baseTailingLevel;

	/**
	 * 安全度
	 */
	private String baseSafetyDegree;

	/**
	 * 筑坝方式
	 */
	private String baseBuildDamType;

	/**
	 * 投产日期
	 */
	private Date baseProduceStartdate;

	/**
	 * 所属企业
	 */
	private String baseCompany;

	/**
	 * 企业法人
	 */
	private String baseLegalPerson;

	/**
	 * 是否有主体矿山
	 */
	private String baseMainbody;

	/**
	 * 尾矿库负责人
	 */
	private String baseTailingHead;

	/**
	 * 尾矿库负责人联系方式
	 */
	private String baseTailingHeadTel;

	/**
	 * 尾矿库县负责人
	 */
	private String baseCountyHead;

	/**
	 * 尾矿库县负责人联系电话
	 */
	private String baseCountyHeadTel;

	/**
	 * 尾矿库乡负责人
	 */
	private String baseTownHead;

	/**
	 * 尾矿库乡负责人联系电话
	 */
	private String baseTownHeadTel;

	/**
	 * 安全生产许可证发证时间
	 */
	private Date baseSafetyLicStartdate;

	/**
	 * 安全生产许可证有效期
	 */
	private String baseSafetyLicValidity;

	/**
	 * 标准化等级
	 */
	private String baseStandardLevel;

	/**
	 * 尾矿库所属
	 */
	private String baseTailingBelong;

	/**
	 * 尾矿库中心点经度
	 */
	private String baseTailingLong;

	/**
	 * 尾矿库中心点纬度
	 */
	private String baseTailingLat;

	/**
	 * 尾矿库中心点图片
	 */
	private String baseTailingImg;

	/**
	 * 设计服务年限
	 */
	private String paraDesignTime;

	/**
	 * 实际服务年限
	 */
	private String paraFactTime;

	/**
	 * 设计总库容
	 */
	private String paraDesignTotal;

	/**
	 * 已堆积尾矿量
	 */
	private String paraAlready;

	/**
	 * 最终堆积高度
	 */
	private String paraFinalHigh;

	/**
	 * 最小安全超高
	 */
	private String paraSafetyHigh;

	/**
	 * 最终侵润高度
	 */
	private String paraMoistenHigh;

	/**
	 * 最小干滩长度
	 */
	private String paraLength;

	/**
	 * 放矿方式
	 */
	private String paraStackType;

	/**
	 * 排洪方式
	 */
	private String paraDrainfloods;

	/**
	 * 坝高
	 */
	private String primeDamHigh;

	/**
	 * 坝长
	 */
	private String primeDamLength;

	/**
	 * 坝宽
	 */
	private String primeDamWidth;

	/**
	 * 内坡比
	 */
	private String primeInnerSlope;

	/**
	 * 外坡比
	 */
	private String primeOutSlope;

	/**
	 * 设计坝高
	 */
	private String accumulateDesignHigh;

	/**
	 * 实际坝高
	 */
	private String accumulateFactHigh;

	/**
	 * 坝长
	 */
	private String accumulateDamLength;

	/**
	 * 内坡比
	 */
	private String accumulateInnerSlope;

	/**
	 * 外坡比
	 */
	private String accumulateOutSlope;

	/**
	 * 坝宽
	 */
	private String accumulateDamWidth;

	/**
	 * 是否重大危险源
	 */
	private String checkDanger;

	/**
	 * 是否渗漏
	 */
	private String checkPercolation;

	/**
	 * 是否自然风景区
	 */
	private String checkBeautyspot;

	/**
	 * 下游居民户数
	 */
	private String checkFamilyNo;

	/**
	 * 下游居民人数
	 */
	private String checkPersonNo;

	/**
	 * 下游公路数
	 */
	private String checkRoadNo;

	/**
	 * 下游铁路数
	 */
	private String checkRailwayNo;

	/**
	 * 下游学校数
	 */
	private String checkSchoolNo;

	/**
	 * 下游工厂数
	 */
	private String checkFactoryNo;

	/**
	 * 排洪设施运行情况
	 */
	private String checkDrainfloodsState;

	/**
	 * 有无安全监测设施
	 */
	private String checkMonitorEquipment;

	/**
	 * 安全现状评价报告编制单位
	 */
	private String checkAqxzUnit;

	/**
	 * 预评价报告编制单位
	 */
	private String checkYpjbgbzUnit;

	/**
	 * 预评价备案单位
	 */
	private String checkYpjbaUnit;

	/**
	 * 初步设计《安全专篇》编制单位
	 */
	private String checkCbsjbzUnit;

	/**
	 * 初步设计《安全专篇》审批单位
	 */
	private String checkCbsjspUnit;

	/**
	 * 竣工验收评价报告编制单位
	 */
	private String checkJgyspjbgbzUnit;

	/**
	 * 竣工验收评价审批单位
	 */
	private String checkJgyspjspUnit;

	/**
	 * 有无土地使用手续审批单位
	 */
	private String checkTdsyspUnit;

	/**
	 * 有无环保验收审批单位
	 */
	private String checkHbysspUnit;
	
	/**
	 * 审核状态 0:乡镇审核 , 1:县级审核 , 2:市级审核 , 3:已办结 ,4:不合格
	 */
	private String state; 
	
	/**
	 * 审核备注
	 */
	private String shbs;
	/**
	 * 审核记录
	 */
	private String shenhe;
	
	/**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 直属等级
	 */
	private String zsqytype;
	
	private String dutyFlag;


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

	
	@Column(name="BASE_LOCAL")
	public String getBaseLocal()
	{
		return this.baseLocal;
	}

	public void setBaseLocal(String baseLocal)
	{
		this.baseLocal = baseLocal;
	}

	@Column(name="BASE_TRADE")
	public String getBaseTrade()
	{
		return this.baseTrade;
	}

	public void setBaseTrade(String baseTrade)
	{
		this.baseTrade = baseTrade;
	}

	@Column(name="BASE_TAILING_TYPE")
	public String getBaseTailingType()
	{
		return this.baseTailingType;
	}

	public void setBaseTailingType(String baseTailingType)
	{
		this.baseTailingType = baseTailingType;
	}

	@Column(name="BASE_TAILING_LEVEL")
	public String getBaseTailingLevel()
	{
		return this.baseTailingLevel;
	}

	public void setBaseTailingLevel(String baseTailingLevel)
	{
		this.baseTailingLevel = baseTailingLevel;
	}

	@Column(name="BASE_SAFETY_DEGREE")
	public String getBaseSafetyDegree()
	{
		return this.baseSafetyDegree;
	}

	public void setBaseSafetyDegree(String baseSafetyDegree)
	{
		this.baseSafetyDegree = baseSafetyDegree;
	}

	@Column(name="BASE_BUILD_DAM_TYPE")
	public String getBaseBuildDamType()
	{
		return this.baseBuildDamType;
	}

	public void setBaseBuildDamType(String baseBuildDamType)
	{
		this.baseBuildDamType = baseBuildDamType;
	}

	@Column(name="BASE_PRODUCE_STARTDATE")
	public Date getBaseProduceStartdate()
	{
		return this.baseProduceStartdate;
	}

	public void setBaseProduceStartdate(Date baseProduceStartdate)
	{
		this.baseProduceStartdate = baseProduceStartdate;
	}

	@Column(name="BASE_COMPANY")
	public String getBaseCompany()
	{
		return this.baseCompany;
	}

	public void setBaseCompany(String baseCompany)
	{
		this.baseCompany = baseCompany;
	}

	@Column(name="BASE_LEGAL_PERSON")
	public String getBaseLegalPerson()
	{
		return this.baseLegalPerson;
	}

	public void setBaseLegalPerson(String baseLegalPerson)
	{
		this.baseLegalPerson = baseLegalPerson;
	}

	@Column(name="BASE_MAINBODY")
	public String getBaseMainbody()
	{
		return this.baseMainbody;
	}

	public void setBaseMainbody(String baseMainbody)
	{
		this.baseMainbody = baseMainbody;
	}

	@Column(name="BASE_TAILING_HEAD")
	public String getBaseTailingHead()
	{
		return this.baseTailingHead;
	}

	public void setBaseTailingHead(String baseTailingHead)
	{
		this.baseTailingHead = baseTailingHead;
	}

	@Column(name="BASE_TAILING_HEAD_TEL")
	public String getBaseTailingHeadTel()
	{
		return this.baseTailingHeadTel;
	}

	public void setBaseTailingHeadTel(String baseTailingHeadTel)
	{
		this.baseTailingHeadTel = baseTailingHeadTel;
	}

	@Column(name="BASE_COUNTY_HEAD")
	public String getBaseCountyHead()
	{
		return this.baseCountyHead;
	}

	public void setBaseCountyHead(String baseCountyHead)
	{
		this.baseCountyHead = baseCountyHead;
	}

	@Column(name="BASE_COUNTY_HEAD_TEL")
	public String getBaseCountyHeadTel()
	{
		return this.baseCountyHeadTel;
	}

	public void setBaseCountyHeadTel(String baseCountyHeadTel)
	{
		this.baseCountyHeadTel = baseCountyHeadTel;
	}

	@Column(name="BASE_TOWN_HEAD")
	public String getBaseTownHead()
	{
		return this.baseTownHead;
	}

	public void setBaseTownHead(String baseTownHead)
	{
		this.baseTownHead = baseTownHead;
	}

	@Column(name="BASE_TOWN_HEAD_TEL")
	public String getBaseTownHeadTel()
	{
		return this.baseTownHeadTel;
	}

	public void setBaseTownHeadTel(String baseTownHeadTel)
	{
		this.baseTownHeadTel = baseTownHeadTel;
	}

	@Column(name="BASE_SAFETY_LIC_STARTDATE")
	public Date getBaseSafetyLicStartdate()
	{
		return this.baseSafetyLicStartdate;
	}

	public void setBaseSafetyLicStartdate(Date baseSafetyLicStartdate)
	{
		this.baseSafetyLicStartdate = baseSafetyLicStartdate;
	}

	@Column(name="BASE_SAFETY_LIC_VALIDITY")
	public String getBaseSafetyLicValidity()
	{
		return this.baseSafetyLicValidity;
	}

	public void setBaseSafetyLicValidity(String baseSafetyLicValidity)
	{
		this.baseSafetyLicValidity = baseSafetyLicValidity;
	}

	@Column(name="BASE_STANDARD_LEVEL")
	public String getBaseStandardLevel()
	{
		return this.baseStandardLevel;
	}

	public void setBaseStandardLevel(String baseStandardLevel)
	{
		this.baseStandardLevel = baseStandardLevel;
	}

	@Column(name="BASE_TAILING_BELONG")
	public String getBaseTailingBelong()
	{
		return this.baseTailingBelong;
	}

	public void setBaseTailingBelong(String baseTailingBelong)
	{
		this.baseTailingBelong = baseTailingBelong;
	}

	@Column(name="BASE_TAILING_LONG")
	public String getBaseTailingLong()
	{
		return this.baseTailingLong;
	}

	public void setBaseTailingLong(String baseTailingLong)
	{
		this.baseTailingLong = baseTailingLong;
	}

	@Column(name="BASE_TAILING_LAT")
	public String getBaseTailingLat()
	{
		return this.baseTailingLat;
	}

	public void setBaseTailingLat(String baseTailingLat)
	{
		this.baseTailingLat = baseTailingLat;
	}

	@Column(name="BASE_TAILING_IMG")
	public String getBaseTailingImg()
	{
		return this.baseTailingImg;
	}

	public void setBaseTailingImg(String baseTailingImg)
	{
		this.baseTailingImg = baseTailingImg;
	}

	@Column(name="PARA_DESIGN_TIME")
	public String getParaDesignTime()
	{
		return this.paraDesignTime;
	}

	public void setParaDesignTime(String paraDesignTime)
	{
		this.paraDesignTime = paraDesignTime;
	}

	@Column(name="PARA_FACT_TIME")
	public String getParaFactTime()
	{
		return this.paraFactTime;
	}

	public void setParaFactTime(String paraFactTime)
	{
		this.paraFactTime = paraFactTime;
	}

	@Column(name="PARA_DESIGN_TOTAL")
	public String getParaDesignTotal()
	{
		return this.paraDesignTotal;
	}

	public void setParaDesignTotal(String paraDesignTotal)
	{
		this.paraDesignTotal = paraDesignTotal;
	}

	@Column(name="PARA_ALREADY")
	public String getParaAlready()
	{
		return this.paraAlready;
	}

	public void setParaAlready(String paraAlready)
	{
		this.paraAlready = paraAlready;
	}

	@Column(name="PARA_FINAL_HIGH")
	public String getParaFinalHigh()
	{
		return this.paraFinalHigh;
	}

	public void setParaFinalHigh(String paraFinalHigh)
	{
		this.paraFinalHigh = paraFinalHigh;
	}

	@Column(name="PARA_SAFETY_HIGH")
	public String getParaSafetyHigh()
	{
		return this.paraSafetyHigh;
	}

	public void setParaSafetyHigh(String paraSafetyHigh)
	{
		this.paraSafetyHigh = paraSafetyHigh;
	}

	@Column(name="PARA_MOISTEN_HIGH")
	public String getParaMoistenHigh()
	{
		return this.paraMoistenHigh;
	}

	public void setParaMoistenHigh(String paraMoistenHigh)
	{
		this.paraMoistenHigh = paraMoistenHigh;
	}

	@Column(name="PARA_LENGTH")
	public String getParaLength()
	{
		return this.paraLength;
	}

	public void setParaLength(String paraLength)
	{
		this.paraLength = paraLength;
	}

	@Column(name="PARA_STACK_TYPE")
	public String getParaStackType()
	{
		return this.paraStackType;
	}

	public void setParaStackType(String paraStackType)
	{
		this.paraStackType = paraStackType;
	}

	@Column(name="PARA_DRAINFLOODS")
	public String getParaDrainfloods()
	{
		return this.paraDrainfloods;
	}

	public void setParaDrainfloods(String paraDrainfloods)
	{
		this.paraDrainfloods = paraDrainfloods;
	}

	@Column(name="PRIME_DAM_HIGH")
	public String getPrimeDamHigh()
	{
		return this.primeDamHigh;
	}

	public void setPrimeDamHigh(String primeDamHigh)
	{
		this.primeDamHigh = primeDamHigh;
	}

	@Column(name="PRIME_DAM_LENGTH")
	public String getPrimeDamLength()
	{
		return this.primeDamLength;
	}

	public void setPrimeDamLength(String primeDamLength)
	{
		this.primeDamLength = primeDamLength;
	}

	@Column(name="PRIME_DAM_WIDTH")
	public String getPrimeDamWidth()
	{
		return this.primeDamWidth;
	}

	public void setPrimeDamWidth(String primeDamWidth)
	{
		this.primeDamWidth = primeDamWidth;
	}

	@Column(name="PRIME_INNER_SLOPE")
	public String getPrimeInnerSlope()
	{
		return this.primeInnerSlope;
	}

	public void setPrimeInnerSlope(String primeInnerSlope)
	{
		this.primeInnerSlope = primeInnerSlope;
	}

	@Column(name="PRIME_OUT_SLOPE")
	public String getPrimeOutSlope()
	{
		return this.primeOutSlope;
	}

	public void setPrimeOutSlope(String primeOutSlope)
	{
		this.primeOutSlope = primeOutSlope;
	}

	@Column(name="ACCUMULATE_DESIGN_HIGH")
	public String getAccumulateDesignHigh()
	{
		return this.accumulateDesignHigh;
	}

	public void setAccumulateDesignHigh(String accumulateDesignHigh)
	{
		this.accumulateDesignHigh = accumulateDesignHigh;
	}

	@Column(name="ACCUMULATE_FACT_HIGH")
	public String getAccumulateFactHigh()
	{
		return this.accumulateFactHigh;
	}

	public void setAccumulateFactHigh(String accumulateFactHigh)
	{
		this.accumulateFactHigh = accumulateFactHigh;
	}

	@Column(name="ACCUMULATE_DAM_LENGTH")
	public String getAccumulateDamLength()
	{
		return this.accumulateDamLength;
	}

	public void setAccumulateDamLength(String accumulateDamLength)
	{
		this.accumulateDamLength = accumulateDamLength;
	}

	@Column(name="ACCUMULATE_INNER_SLOPE")
	public String getAccumulateInnerSlope()
	{
		return this.accumulateInnerSlope;
	}

	public void setAccumulateInnerSlope(String accumulateInnerSlope)
	{
		this.accumulateInnerSlope = accumulateInnerSlope;
	}

	@Column(name="ACCUMULATE_OUT_SLOPE")
	public String getAccumulateOutSlope()
	{
		return this.accumulateOutSlope;
	}

	public void setAccumulateOutSlope(String accumulateOutSlope)
	{
		this.accumulateOutSlope = accumulateOutSlope;
	}

	@Column(name="ACCUMULATE_DAM_WIDTH")
	public String getAccumulateDamWidth()
	{
		return this.accumulateDamWidth;
	}

	public void setAccumulateDamWidth(String accumulateDamWidth)
	{
		this.accumulateDamWidth = accumulateDamWidth;
	}

	@Column(name="CHECK_DANGER")
	public String getCheckDanger()
	{
		return this.checkDanger;
	}

	public void setCheckDanger(String checkDanger)
	{
		this.checkDanger = checkDanger;
	}

	@Column(name="CHECK_PERCOLATION")
	public String getCheckPercolation()
	{
		return this.checkPercolation;
	}

	public void setCheckPercolation(String checkPercolation)
	{
		this.checkPercolation = checkPercolation;
	}

	@Column(name="CHECK_BEAUTYSPOT")
	public String getCheckBeautyspot()
	{
		return this.checkBeautyspot;
	}

	public void setCheckBeautyspot(String checkBeautyspot)
	{
		this.checkBeautyspot = checkBeautyspot;
	}

	@Column(name="CHECK_FAMILY_NO")
	public String getCheckFamilyNo()
	{
		return this.checkFamilyNo;
	}

	public void setCheckFamilyNo(String checkFamilyNo)
	{
		this.checkFamilyNo = checkFamilyNo;
	}

	@Column(name="CHECK_PERSON_NO")
	public String getCheckPersonNo()
	{
		return this.checkPersonNo;
	}

	public void setCheckPersonNo(String checkPersonNo)
	{
		this.checkPersonNo = checkPersonNo;
	}

	@Column(name="CHECK_ROAD_NO")
	public String getCheckRoadNo()
	{
		return this.checkRoadNo;
	}

	public void setCheckRoadNo(String checkRoadNo)
	{
		this.checkRoadNo = checkRoadNo;
	}

	@Column(name="CHECK_RAILWAY_NO")
	public String getCheckRailwayNo()
	{
		return this.checkRailwayNo;
	}

	public void setCheckRailwayNo(String checkRailwayNo)
	{
		this.checkRailwayNo = checkRailwayNo;
	}

	@Column(name="CHECK_SCHOOL_NO")
	public String getCheckSchoolNo()
	{
		return this.checkSchoolNo;
	}

	public void setCheckSchoolNo(String checkSchoolNo)
	{
		this.checkSchoolNo = checkSchoolNo;
	}

	@Column(name="CHECK__FACTORY_NO")
	public String getCheckFactoryNo()
	{
		return this.checkFactoryNo;
	}

	public void setCheckFactoryNo(String checkFactoryNo)
	{
		this.checkFactoryNo = checkFactoryNo;
	}

	@Column(name="CHECK_DRAINFLOODS_STATE")
	public String getCheckDrainfloodsState()
	{
		return this.checkDrainfloodsState;
	}

	public void setCheckDrainfloodsState(String checkDrainfloodsState)
	{
		this.checkDrainfloodsState = checkDrainfloodsState;
	}

	@Column(name="CHECK_MONITOR_EQUIPMENT")
	public String getCheckMonitorEquipment()
	{
		return this.checkMonitorEquipment;
	}

	public void setCheckMonitorEquipment(String checkMonitorEquipment)
	{
		this.checkMonitorEquipment = checkMonitorEquipment;
	}

	@Column(name="CHECK_AQXZ_UNIT")
	public String getCheckAqxzUnit()
	{
		return this.checkAqxzUnit;
	}

	public void setCheckAqxzUnit(String checkAqxzUnit)
	{
		this.checkAqxzUnit = checkAqxzUnit;
	}

	@Column(name="CHECK_YPJBGBZ_UNIT")
	public String getCheckYpjbgbzUnit()
	{
		return this.checkYpjbgbzUnit;
	}

	public void setCheckYpjbgbzUnit(String checkYpjbgbzUnit)
	{
		this.checkYpjbgbzUnit = checkYpjbgbzUnit;
	}

	@Column(name="CHECK_YPJBA_UNIT")
	public String getCheckYpjbaUnit()
	{
		return this.checkYpjbaUnit;
	}

	public void setCheckYpjbaUnit(String checkYpjbaUnit)
	{
		this.checkYpjbaUnit = checkYpjbaUnit;
	}

	@Column(name="CHECK_CBSJBZ_UNIT")
	public String getCheckCbsjbzUnit()
	{
		return this.checkCbsjbzUnit;
	}

	public void setCheckCbsjbzUnit(String checkCbsjbzUnit)
	{
		this.checkCbsjbzUnit = checkCbsjbzUnit;
	}

	@Column(name="CHECK_CBSJSP_UNIT")
	public String getCheckCbsjspUnit()
	{
		return this.checkCbsjspUnit;
	}

	public void setCheckCbsjspUnit(String checkCbsjspUnit)
	{
		this.checkCbsjspUnit = checkCbsjspUnit;
	}

	@Column(name="CHECK_JGYSPJBGBZ_UNIT")
	public String getCheckJgyspjbgbzUnit()
	{
		return this.checkJgyspjbgbzUnit;
	}

	public void setCheckJgyspjbgbzUnit(String checkJgyspjbgbzUnit)
	{
		this.checkJgyspjbgbzUnit = checkJgyspjbgbzUnit;
	}

	@Column(name="CHECK_JGYSPJSP_UNIT")
	public String getCheckJgyspjspUnit()
	{
		return this.checkJgyspjspUnit;
	}

	public void setCheckJgyspjspUnit(String checkJgyspjspUnit)
	{
		this.checkJgyspjspUnit = checkJgyspjspUnit;
	}

	@Column(name="CHECK_TDSYSP_UNIT")
	public String getCheckTdsyspUnit()
	{
		return this.checkTdsyspUnit;
	}

	public void setCheckTdsyspUnit(String checkTdsyspUnit)
	{
		this.checkTdsyspUnit = checkTdsyspUnit;
	}

	@Column(name="CHECK_HBYSSP_UNIT")
	public String getCheckHbysspUnit()
	{
		return this.checkHbysspUnit;
	}

	public void setCheckHbysspUnit(String checkHbysspUnit)
	{
		this.checkHbysspUnit = checkHbysspUnit;
	}
	
	@Column(name="STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="SHBZ")
	public String getShbs() {
		return shbs;
	}

	public void setShbs(String shbs) {
		this.shbs = shbs;
	}
	
	@Column(name="SHENHE")
	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
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

}
