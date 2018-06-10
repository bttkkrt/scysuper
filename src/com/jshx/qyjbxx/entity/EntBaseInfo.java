package com.jshx.qyjbxx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ENT_BASE_INFO")
public class EntBaseInfo extends BaseModel
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
	private String enterpriseName;

	/**
	 * 组织机构代码/统一社会信用代码
	 */
	private String enterpriseCode;
	
	/**
	 * 工商注册号
	 */
	private String registrationNumber;
	
	/**
	 * 注册地址
	 */
	private String enterpriseAddress;
	
	/**
	 * 生产经营地址
	 */
	private String factoryAddress;
	
	/**
	 * 企业属地
	 */
	private String enterprisePossession;
	
	/**
	 * 企业属地名称
	 */
	private String enterprisePossessionName;
	
	/**
	 * 邮政编码
	 */
	private String enterpriseZipcode;
	
	/**
	 * 投资方国籍
	 */
	private String enterpriseNationnality;
	
	/**
	 * 法人姓名
	 */
	private String enterpriseLegalName;
	
	/**
	 * 法人性别
	 */
	private String enterpriseLegalSex;
	
	/**
	 * 法人年龄
	 */
	private String enterpriseLegalAge;

	/**
	 * 法人联系电话
	 */
	private String enterpriseLegalPhone;

	/**
	 * 法人证件号码
	 */
	private String enterpriseLegalCardnum;
	
	/**
	 * 法人职务
	 */
	private String enterpriseLegalZw;

	/**
	 * 法人电子邮箱
	 */
	private String enterpriseLegalEmail;
	
	/**
	 * 安全生产负责人
	 */
	private String safetySupervisor;
	
	/**
	 * 安全生产负责人手机
	 */
	private String safetySupervisorMobilephone;
	
	/**
	 * 安全生产负责人固话
	 */
	private String safetySupervisorTelephone;
	
	
	/**
	 * 企业性质
	 */
	private String enterpriseNature;
	
	/**
	 * 企业规模
	 */
	private String enterpriseScale;
	
	/**
	 * 经营范围
	 */
	private String enterpriseScope;
	
	/**
	 * 行业类别
	 */
	private String enterpriseCategory;
	
	/**
	 * 行业类别名称
	 */
	private String enterpriseCategoryName;
	
	/**
	 * 企业设立日期
	 */
	private Date enterpriseFoundDate;
	
	/**
	 * 企业投产日期
	 */
	private Date enterpriseProductDate;
	
	/**
	 * 注册资本
	 */
	private String enterpriseRegisterMoney;
	
	/**
	 * 注册资本单位
	 */
	private String enterpriseRegisterMoneyDw;

	/**
	 * 投资总额
	 */
	private String enterpriseInvestMoney;
	
	/**
	 * 投资总额单位
	 */
	private String enterpriseInvestMoneyDw;

	/**
	 * 固定资产总额
	 */
	private String enterpriseFixedassetMoney;
	
	/**
	 * 固定资产总额单位
	 */
	private String enterpriseFixedassetMoneyDw;

	/**
	 * 占地面积
	 */
	private String enterpriseFloorArea;

	/**
	 * 建筑面积
	 */
	private String enterpriseBuildArea;

	/**
	 * 办公楼面积
	 */
	private String enterpriseOfficeArea;

	/**
	 * 车间厂房面积
	 */
	private String enterpriseWorkshopArea;

	/**
	 * 仓库面积
	 */
	private String enterpriseWearhouseArea;
	
	/**
	 * 厂房归属
	 */
	private String enterprisWorkshopOwn;

	/**
	 * 员工总数
	 */
	private String enterpriseStaffCount;

	/**
	 * 管理人员数
	 */
	private String enterpriseManagerCount;

	/**
	 * 工人数
	 */
	private String enterpriseWorkerCount;
	
	
	/**
	 * 企业分类
	 */
	private String enterpriseType;

	/**
	 * 网格管理中队ID
	 */
	private String gridManageteamCode;


	/**
	 * 网格管理中队
	 */
	private String gridManageteamName;
	
	/**
	 * 网格管理人员ID
	 */
	private String gridManageId;


	/**
	 * 网格管理人员
	 */
	private String gridManageName;



	/**
	 * 所属网格ID
	 */
	private String grid;

	/**
	 * 所属网格
	 */
	private String gridName;

	/**
	 * 附件关联id
	 */
	private String linkId;

	/**
	 * 备注
	 */
	private String remark;

	
	/**
	 * 用户名
	 */
	private String loginId;
	
	/**
	 * 密码
	 */
	private String password;
	
	
	/**
	 * 审核状态 0:待审核 1审核通过 2审核未通过
	 */
	private String basePass;
	
	/**
	 * 法人库是否存在该企业信息 0 不存在 1存在
	 */
	private String ifCz;

	
	/**
	 * 企业级别
	 */
	private String enterpriseLevel;
	
	
	private String ownerTel;
	
	/**
	 * 行业小类
	 */
	private String jjlx;
	
	private String jjlxname;
	/**
	 * 经度
	 */
	private String latitude;
	/**
	 * 纬度
	 */
	private String longitude;
	/**
	 * 出租方
	 */
	private String houseOwner;
	
	/**
	 * 企业注册类型
	 */
	private String qyzclx;
	
	/**
	 * 直管部门级别
	 */
	private String departmentalLevel;
	/**
	 * 营业执照类型
	 */
	private String businessLicence;
	

	public EntBaseInfo(){
	}
	
	public EntBaseInfo(String id,String enterpriseName,String registrationNumber,String enterpriseCode,String enterpriseLegalName,String enterpriseNature,String gridName,String enterprisePossession,String enterprisePossessionName,String basePass,String gridManageId,String grid,String ifCz,String gridManageteamCode){
this.id = id;

this.enterpriseName = enterpriseName;

this.enterpriseCode = enterpriseCode;

this.registrationNumber = registrationNumber;
this.enterpriseLegalName=enterpriseLegalName;
this.enterpriseNature=enterpriseNature;
this.gridName=gridName;
this.enterprisePossession = enterprisePossession;
this.enterprisePossessionName = enterprisePossessionName;
this.basePass=basePass;
this.gridManageId = gridManageId;
this.grid = grid;
this.ifCz = ifCz;
this.gridManageteamCode = gridManageteamCode;
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

	
	@Column(name="ENTERPRISE_REGISTER_MONEY")
	public String getEnterpriseRegisterMoney()
	{
		return this.enterpriseRegisterMoney;
	}

	public void setEnterpriseRegisterMoney(String enterpriseRegisterMoney)
	{
		this.enterpriseRegisterMoney = enterpriseRegisterMoney;
	}

	@Column(name="ENTERPRISE_INVEST_MONEY")
	public String getEnterpriseInvestMoney()
	{
		return this.enterpriseInvestMoney;
	}

	public void setEnterpriseInvestMoney(String enterpriseInvestMoney)
	{
		this.enterpriseInvestMoney = enterpriseInvestMoney;
	}

	@Column(name="ENTERPRISE_FIXEDASSET_MONEY")
	public String getEnterpriseFixedassetMoney()
	{
		return this.enterpriseFixedassetMoney;
	}

	public void setEnterpriseFixedassetMoney(String enterpriseFixedassetMoney)
	{
		this.enterpriseFixedassetMoney = enterpriseFixedassetMoney;
	}

	@Column(name="ENTERPRISE_FLOOR_AREA")
	public String getEnterpriseFloorArea()
	{
		return this.enterpriseFloorArea;
	}

	public void setEnterpriseFloorArea(String enterpriseFloorArea)
	{
		this.enterpriseFloorArea = enterpriseFloorArea;
	}

	@Column(name="ENTERPRISE_BUILD_AREA")
	public String getEnterpriseBuildArea()
	{
		return this.enterpriseBuildArea;
	}

	public void setEnterpriseBuildArea(String enterpriseBuildArea)
	{
		this.enterpriseBuildArea = enterpriseBuildArea;
	}

	@Column(name="ENTERPRISE_OFFICE_AREA")
	public String getEnterpriseOfficeArea()
	{
		return this.enterpriseOfficeArea;
	}

	public void setEnterpriseOfficeArea(String enterpriseOfficeArea)
	{
		this.enterpriseOfficeArea = enterpriseOfficeArea;
	}

	@Column(name="ENTERPRISE_WORKSHOP_AREA")
	public String getEnterpriseWorkshopArea()
	{
		return this.enterpriseWorkshopArea;
	}

	public void setEnterpriseWorkshopArea(String enterpriseWorkshopArea)
	{
		this.enterpriseWorkshopArea = enterpriseWorkshopArea;
	}

	@Column(name="ENTERPRISE_WEARHOUSE_AREA")
	public String getEnterpriseWearhouseArea()
	{
		return this.enterpriseWearhouseArea;
	}

	public void setEnterpriseWearhouseArea(String enterpriseWearhouseArea)
	{
		this.enterpriseWearhouseArea = enterpriseWearhouseArea;
	}

	@Column(name="ENTERPRISE_STAFF_COUNT")
	public String getEnterpriseStaffCount()
	{
		if(this.enterpriseStaffCount != null)
		{
			if(this.enterpriseStaffCount.indexOf(".") != -1)
			{
				return this.enterpriseStaffCount.substring(0,this.enterpriseStaffCount.indexOf("."));
			}
			else
			{
				return this.enterpriseStaffCount;
			}
		}
		else
		{
			return "";
		}
	}

	public void setEnterpriseStaffCount(String enterpriseStaffCount)
	{
		this.enterpriseStaffCount = enterpriseStaffCount;
	}

	@Column(name="ENTERPRISE_MANAGER_COUNT")
	public String getEnterpriseManagerCount()
	{
		return this.enterpriseManagerCount;
	}

	public void setEnterpriseManagerCount(String enterpriseManagerCount)
	{
		this.enterpriseManagerCount = enterpriseManagerCount;
	}

	@Column(name="ENTERPRISE_WORKER_COUNT")
	public String getEnterpriseWorkerCount()
	{
		return this.enterpriseWorkerCount;
	}

	public void setEnterpriseWorkerCount(String enterpriseWorkerCount)
	{
		this.enterpriseWorkerCount = enterpriseWorkerCount;
	}

	@Column(name="GRID_MANAGETEAM_CODE")
	public String getGridManageteamCode()
	{
		return this.gridManageteamCode;
	}

	public void setGridManageteamCode(String gridManageteamCode)
	{
		this.gridManageteamCode = gridManageteamCode;
	}

	@Column(name="ENTERPRISE_ZIPCODE")
	public String getEnterpriseZipcode()
	{
		return this.enterpriseZipcode;
	}

	public void setEnterpriseZipcode(String enterpriseZipcode)
	{
		this.enterpriseZipcode = enterpriseZipcode;
	}

	@Column(name="ENTERPRISE_LEGAL_NAME")
	public String getEnterpriseLegalName()
	{
		return this.enterpriseLegalName;
	}

	public void setEnterpriseLegalName(String enterpriseLegalName)
	{
		this.enterpriseLegalName = enterpriseLegalName;
	}

	@Column(name="ENTERPRISE_LEGAL_PHONE")
	public String getEnterpriseLegalPhone()
	{
		return this.enterpriseLegalPhone;
	}

	public void setEnterpriseLegalPhone(String enterpriseLegalPhone)
	{
		this.enterpriseLegalPhone = enterpriseLegalPhone;
	}

	@Column(name="ENTERPRISE_LEGAL_CARDNUM")
	public String getEnterpriseLegalCardnum()
	{
		return this.enterpriseLegalCardnum;
	}

	public void setEnterpriseLegalCardnum(String enterpriseLegalCardnum)
	{
		this.enterpriseLegalCardnum = enterpriseLegalCardnum;
	}

	@Column(name="ENTERPRISE_LEGAL_EMAIL")
	public String getEnterpriseLegalEmail()
	{
		return this.enterpriseLegalEmail;
	}

	public void setEnterpriseLegalEmail(String enterpriseLegalEmail)
	{
		this.enterpriseLegalEmail = enterpriseLegalEmail;
	}

	@Column(name="GRID_MANAGETEAM_NAME")
	public String getGridManageteamName()
	{
		return this.gridManageteamName;
	}

	public void setGridManageteamName(String gridManageteamName)
	{
		this.gridManageteamName = gridManageteamName;
	}

	@Column(name="ENTERPRISE_NAME")
	public String getEnterpriseName()
	{
		return this.enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName)
	{
		this.enterpriseName = enterpriseName;
	}

	@Column(name="ENTERPRISE_CODE")
	public String getEnterpriseCode()
	{
		return this.enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode)
	{
		this.enterpriseCode = enterpriseCode;
	}

	@Column(name="ENTERPRISE_ADDRESS")
	public String getEnterpriseAddress()
	{
		return this.enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress)
	{
		this.enterpriseAddress = enterpriseAddress;
	}

	@Column(name="GRID")
	public String getGrid()
	{
		return this.grid;
	}

	public void setGrid(String grid)
	{
		this.grid = grid;
	}

	@Column(name="GRID_NAME")
	public String getGridName()
	{
		return this.gridName;
	}

	public void setGridName(String gridName)
	{
		this.gridName = gridName;
	}

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name="REGISTRATION_NUMBER")
	public String getRegistrationNumber()
	{
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber)
	{
		this.registrationNumber = registrationNumber;
	}
	@Column(name="LOGIN_ID")
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	@Column(name="PASS_WORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="ENTERPRISE_POSSESSION")
	public String getEnterprisePossession() {
		return enterprisePossession;
	}

	public void setEnterprisePossession(String enterprisePossession) {
		this.enterprisePossession = enterprisePossession;
	}
	@Column(name="ENTERPRISE_NATIONNALITY")
	public String getEnterpriseNationnality() {
		return enterpriseNationnality;
	}

	public void setEnterpriseNationnality(String enterpriseNationnality) {
		this.enterpriseNationnality = enterpriseNationnality;
	}
	@Column(name="ENTERPRISE_NATURE")
	public String getEnterpriseNature() {
		return enterpriseNature;
	}

	public void setEnterpriseNature(String enterpriseNature) {
		this.enterpriseNature = enterpriseNature;
	}
	@Column(name="ENTERPRISE_SCALE")
	public String getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(String enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}
	@Column(name="ENTERPRISE_SCOPE")
	public String getEnterpriseScope() {
		return enterpriseScope;
	}

	public void setEnterpriseScope(String enterpriseScope) {
		this.enterpriseScope = enterpriseScope;
	}
	@Column(name="ENTERPRISE_CATEGORY")
	public String getEnterpriseCategory() {
		return enterpriseCategory;
	}

	public void setEnterpriseCategory(String enterpriseCategory) {
		this.enterpriseCategory = enterpriseCategory;
	}
	@Column(name="ENTERPRISE_CATEGORY_NAME")
	public String getEnterpriseCategoryName() {
		return enterpriseCategoryName;
	}

	public void setEnterpriseCategoryName(String enterpriseCategoryName) {
		this.enterpriseCategoryName = enterpriseCategoryName;
	}
	@Column(name="ENTERPRISE_FOUND_DATE")
	public Date getEnterpriseFoundDate() {
		return enterpriseFoundDate;
	}

	public void setEnterpriseFoundDate(Date enterpriseFoundDate) {
		this.enterpriseFoundDate = enterpriseFoundDate;
	}
	@Column(name="ENTERPRISE_PRODUCT_DATE")
	public Date getEnterpriseProductDate() {
		return enterpriseProductDate;
	}

	public void setEnterpriseProductDate(Date enterpriseProductDate) {
		this.enterpriseProductDate = enterpriseProductDate;
	}
	@Column(name="ENTERPRISE_WORKSHOP_OWN")
	public String getEnterprisWorkshopOwn() {
		return enterprisWorkshopOwn;
	}

	public void setEnterprisWorkshopOwn(String enterprisWorkshopOwn) {
		this.enterprisWorkshopOwn = enterprisWorkshopOwn;
	}
	@Column(name="ENTERPRISE_TYPE")
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	@Column(name="BASE_PASS")
	public String getBasePass() {
		return basePass;
	}

	public void setBasePass(String basePass) {
		this.basePass = basePass;
	}
	@Column(name="ENTERPRISE_POSSESSION_NAME")
	public String getEnterprisePossessionName() {
		return enterprisePossessionName;
	}

	public void setEnterprisePossessionName(String enterprisePossessionName) {
		this.enterprisePossessionName = enterprisePossessionName;
	}
	@Column(name="ENTERPRISE_LEGAL_SEX")
	public String getEnterpriseLegalSex() {
		return enterpriseLegalSex;
	}

	public void setEnterpriseLegalSex(String enterpriseLegalSex) {
		this.enterpriseLegalSex = enterpriseLegalSex;
	}
	@Column(name="ENTERPRISE_LEGAL_AGE")
	public String getEnterpriseLegalAge() {
		return enterpriseLegalAge;
	}

	public void setEnterpriseLegalAge(String enterpriseLegalAge) {
		this.enterpriseLegalAge = enterpriseLegalAge;
	}
	@Column(name="ENTERPRISE_LEGAL_ZW")
	public String getEnterpriseLegalZw() {
		return enterpriseLegalZw;
	}

	public void setEnterpriseLegalZw(String enterpriseLegalZw) {
		this.enterpriseLegalZw = enterpriseLegalZw;
	}
	@Column(name="GRID_MANAGER_ID")
	public String getGridManageId() {
		return gridManageId;
	}

	public void setGridManageId(String gridManageId) {
		this.gridManageId = gridManageId;
	}
	@Column(name="GRID_MANAGER_NAME")
	public String getGridManageName() {
		return gridManageName;
	}

	public void setGridManageName(String gridManageName) {
		this.gridManageName = gridManageName;
	}
	@Column(name="IF_CZ")
	public String getIfCz() {
		return ifCz;
	}

	public void setIfCz(String ifCz) {
		this.ifCz = ifCz;
	}
	@Column(name="ENTERPRISE_LEVEl")
	public String getEnterpriseLevel() {
		return enterpriseLevel;
	}

	public void setEnterpriseLevel(String enterpriseLevel) {
		this.enterpriseLevel = enterpriseLevel;
	}
	@Column(name="FACTORY_ADDRESS")
	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}
	@Column(name="ENTERPRISE_REGISTER_MONEY_DW")
	public String getEnterpriseRegisterMoneyDw()
	{
		return this.enterpriseRegisterMoneyDw;
	}

	public void setEnterpriseRegisterMoneyDw(String enterpriseRegisterMoneyDw)
	{
		this.enterpriseRegisterMoneyDw = enterpriseRegisterMoneyDw;
	}

	@Column(name="ENTERPRISE_INVEST_MONEY_DW")
	public String getEnterpriseInvestMoneyDw()
	{
		return this.enterpriseInvestMoneyDw;
	}

	public void setEnterpriseInvestMoneyDw(String enterpriseInvestMoneyDw)
	{
		this.enterpriseInvestMoneyDw = enterpriseInvestMoneyDw;
	}

	@Column(name="ENTERPRISE_FIXEDASSET_MONEY_DW")
	public String getEnterpriseFixedassetMoneyDw()
	{
		return this.enterpriseFixedassetMoneyDw;
	}

	public void setEnterpriseFixedassetMoneyDw(String enterpriseFixedassetMoneyDw)
	{
		this.enterpriseFixedassetMoneyDw = enterpriseFixedassetMoneyDw;
	}
	@Column(name="HOUSEOWNER")
	public String getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}
	@Column(name="OWNERTEL")
	public String getOwnerTel() {
		return ownerTel;
	}

	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}
	@Column(name="JJLX")
	public String getJjlx() {
		return jjlx;
	}

	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	@Column(name="QYZCLX")
	public String getQyzclx() {
		return qyzclx;
	}

	public void setQyzclx(String qyzclx) {
		this.qyzclx = qyzclx;
	}
	@Transient
	public String getJjlxname() {
		return jjlxname;
	}

	public void setJjlxname(String jjlxname) {
		this.jjlxname = jjlxname;
	}

	@Column(name="LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name="LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="SAFETYSUPERVISOR")
	public String getSafetySupervisor() {
		return safetySupervisor;
	}

	public void setSafetySupervisor(String safetySupervisor) {
		this.safetySupervisor = safetySupervisor;
	}

	@Column(name="SAFETYSUPERVISORMOBILEPHONE")
	public String getSafetySupervisorMobilephone() {
		return safetySupervisorMobilephone;
	}

	public void setSafetySupervisorMobilephone(String safetySupervisorMobilephone) {
		this.safetySupervisorMobilephone = safetySupervisorMobilephone;
	}

	@Column(name="SAFETYSUPERVISORTELEPHONE")
	public String getSafetySupervisorTelephone() {
		return safetySupervisorTelephone;
	}

	public void setSafetySupervisorTelephone(String safetySupervisorTelephone) {
		this.safetySupervisorTelephone = safetySupervisorTelephone;
	}

	@Column(name="DEPARTMENTALLEVEL")
	public String getDepartmentalLevel() {
		return departmentalLevel;
	}

	public void setDepartmentalLevel(String departmentalLevel) {
		this.departmentalLevel = departmentalLevel;
	}

	@Column(name="BUSINESSLICENCE")
	public String getBusinessLicence() {
		return businessLicence;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}

}
