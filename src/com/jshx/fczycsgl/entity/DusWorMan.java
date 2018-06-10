package com.jshx.fczycsgl.entity;

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
@Table(name="DUS_WOR_MAN")
public class DusWorMan extends BaseModel
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
	 * 所在区域id
	 */
	private String areaId;

	/**
	 * 所在区域名称
	 */
	private String areaName;

	/**
	 * 企业id
	 */
	private String companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 作业场所名称
	 */
	private String workplaceName;

	/**
	 * 所属行业
	 */
	private String agencyResponsible;

	/**
	 * 是否合格
	 */
	private String isQualified;

	/**
	 * 粉尘种类
	 */
	private String industryType;

	/**
	 * 除尘器种类
	 */
	private String dustWiperType;

	/**
	 * 企业规模
	 */
	private String enterpriseScale;

	/**
	 * 从业人数
	 */
	private String employeeNumber;

	/**
	 * 车间结构
	 */
	private String workshopStructure;

	/**
	 * 车间布局
	 */
	private String workshopLayout;

	/**
	 * 最近一次粉尘检测的日期
	 */
	private Date recentlyDustDetectTime;

	/**
	 * 检测值
	 */
	private String testValue;

	/**
	 * 作业方式
	 */
	private String operationMode;

	/**
	 * 是否有除尘器
	 */
	private String hasDustWiper;

	/**
	 * 除尘形式
	 */
	private String dustRemovalForm;

	/**
	 * 除尘器数量
	 */
	private String dustWiperNumber;

	/**
	 * 投入使用时间
	 */
	private Date wiperInUseTime;

	/**
	 * 除尘器是否经环保部门验收
	 */
	private String isWiperAccepted;

	/**
	 * 除尘系统是否设置隔爆阀
	 */
	private String hasExplosionProof;

	/**
	 * 除尘器是否有泄爆口
	 */
	private String hasVentPort;

	/**
	 * 泄爆口位置
	 */
	private String ventPortPosition;

	/**
	 * 除尘器是否在负压下工作
	 */
	private String isWorkUnderNegative;

	/**
	 * 除尘器是否安装于室外
	 */
	private String isInstalledOutdoor;

	/**
	 * 是否有自动卸灰锁气装置
	 */
	private String hasAutoUnloadLock;

	/**
	 * 除尘器目前状态
	 */
	private String dustWiperCurrentStatus;

	/**
	 * 企业目前状态
	 */
	private String enterpriseCurrentStatus;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件关联id
	 */
	private String linkId;

	
	public DusWorMan(){
	}
	
	public DusWorMan(String id, String areaName, String companyName, String workplaceName, String agencyResponsible, String industryType){
this.id = id;

this.areaName = areaName;

this.companyName = companyName;

this.workplaceName = workplaceName;

this.agencyResponsible = agencyResponsible;

this.industryType = industryType;
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

	
	@Column(name="AREA_ID")
	public String getAreaId()
	{
		return this.areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="COMPANY_ID")
	public String getCompanyId()
	{
		return this.companyId;
	}

	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}

	@Column(name="COMPANY_NAME")
	public String getCompanyName()
	{
		return this.companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	@Column(name="WORKPLACE_NAME")
	public String getWorkplaceName()
	{
		return this.workplaceName;
	}

	public void setWorkplaceName(String workplaceName)
	{
		this.workplaceName = workplaceName;
	}

	@Column(name="AGENCY_RESPONSIBLE")
	public String getAgencyResponsible()
	{
		return this.agencyResponsible;
	}

	public void setAgencyResponsible(String agencyResponsible)
	{
		this.agencyResponsible = agencyResponsible;
	}

	@Column(name="IS_QUALIFIED")
	public String getIsQualified()
	{
		return this.isQualified;
	}

	public void setIsQualified(String isQualified)
	{
		this.isQualified = isQualified;
	}

	@Column(name="INDUSTRY_TYPE")
	public String getIndustryType()
	{
		return this.industryType;
	}

	public void setIndustryType(String industryType)
	{
		this.industryType = industryType;
	}

	@Column(name="DUST_WIPER_TYPE")
	public String getDustWiperType()
	{
		return this.dustWiperType;
	}

	public void setDustWiperType(String dustWiperType)
	{
		this.dustWiperType = dustWiperType;
	}

	@Column(name="ENTERPRISE_SCALE")
	public String getEnterpriseScale()
	{
		return this.enterpriseScale;
	}

	public void setEnterpriseScale(String enterpriseScale)
	{
		this.enterpriseScale = enterpriseScale;
	}

	@Column(name="EMPLOYEE_NUMBER")
	public String getEmployeeNumber()
	{
		return this.employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber)
	{
		this.employeeNumber = employeeNumber;
	}

	@Column(name="WORKSHOP_STRUCTURE")
	public String getWorkshopStructure()
	{
		return this.workshopStructure;
	}

	public void setWorkshopStructure(String workshopStructure)
	{
		this.workshopStructure = workshopStructure;
	}

	@Column(name="WORKSHOP_LAYOUT")
	public String getWorkshopLayout()
	{
		return this.workshopLayout;
	}

	public void setWorkshopLayout(String workshopLayout)
	{
		this.workshopLayout = workshopLayout;
	}

	@Column(name="RECENTLY_DUST_DETECT_TIME")
	public Date getRecentlyDustDetectTime()
	{
		return this.recentlyDustDetectTime;
	}

	public void setRecentlyDustDetectTime(Date recentlyDustDetectTime)
	{
		this.recentlyDustDetectTime = recentlyDustDetectTime;
	}

	@Column(name="TEST_VALUE")
	public String getTestValue()
	{
		return this.testValue;
	}

	public void setTestValue(String testValue)
	{
		this.testValue = testValue;
	}

	@Column(name="OPERATION_MODE")
	public String getOperationMode()
	{
		return this.operationMode;
	}

	public void setOperationMode(String operationMode)
	{
		this.operationMode = operationMode;
	}

	@Column(name="HAS_DUST_WIPER")
	public String getHasDustWiper()
	{
		return this.hasDustWiper;
	}

	public void setHasDustWiper(String hasDustWiper)
	{
		this.hasDustWiper = hasDustWiper;
	}

	@Column(name="DUST_REMOVAL_FORM")
	public String getDustRemovalForm()
	{
		return this.dustRemovalForm;
	}

	public void setDustRemovalForm(String dustRemovalForm)
	{
		this.dustRemovalForm = dustRemovalForm;
	}

	@Column(name="DUST_WIPER_NUMBER")
	public String getDustWiperNumber()
	{
		return this.dustWiperNumber;
	}

	public void setDustWiperNumber(String dustWiperNumber)
	{
		this.dustWiperNumber = dustWiperNumber;
	}

	@Column(name="WIPER_IN_USE_TIME")
	public Date getWiperInUseTime()
	{
		return this.wiperInUseTime;
	}

	public void setWiperInUseTime(Date wiperInUseTime)
	{
		this.wiperInUseTime = wiperInUseTime;
	}

	@Column(name="IS_WIPER_ACCEPTED")
	public String getIsWiperAccepted()
	{
		return this.isWiperAccepted;
	}

	public void setIsWiperAccepted(String isWiperAccepted)
	{
		this.isWiperAccepted = isWiperAccepted;
	}

	@Column(name="HAS_EXPLOSION_PROOF")
	public String getHasExplosionProof()
	{
		return this.hasExplosionProof;
	}

	public void setHasExplosionProof(String hasExplosionProof)
	{
		this.hasExplosionProof = hasExplosionProof;
	}

	@Column(name="HAS_VENT_PORT")
	public String getHasVentPort()
	{
		return this.hasVentPort;
	}

	public void setHasVentPort(String hasVentPort)
	{
		this.hasVentPort = hasVentPort;
	}

	@Column(name="VENT_PORT_POSITION")
	public String getVentPortPosition()
	{
		return this.ventPortPosition;
	}

	public void setVentPortPosition(String ventPortPosition)
	{
		this.ventPortPosition = ventPortPosition;
	}

	@Column(name="IS_WORK_UNDER_NEGATIVE")
	public String getIsWorkUnderNegative()
	{
		return this.isWorkUnderNegative;
	}

	public void setIsWorkUnderNegative(String isWorkUnderNegative)
	{
		this.isWorkUnderNegative = isWorkUnderNegative;
	}

	@Column(name="IS_INSTALLED_OUTDOOR")
	public String getIsInstalledOutdoor()
	{
		return this.isInstalledOutdoor;
	}

	public void setIsInstalledOutdoor(String isInstalledOutdoor)
	{
		this.isInstalledOutdoor = isInstalledOutdoor;
	}

	@Column(name="HAS_AUTO_UNLOAD_LOCK")
	public String getHasAutoUnloadLock()
	{
		return this.hasAutoUnloadLock;
	}

	public void setHasAutoUnloadLock(String hasAutoUnloadLock)
	{
		this.hasAutoUnloadLock = hasAutoUnloadLock;
	}

	@Column(name="DUST_WIPER_CURRENT_STATUS")
	public String getDustWiperCurrentStatus()
	{
		return this.dustWiperCurrentStatus;
	}

	public void setDustWiperCurrentStatus(String dustWiperCurrentStatus)
	{
		this.dustWiperCurrentStatus = dustWiperCurrentStatus;
	}

	@Column(name="ENTERPRISE_CURRENT_STATUS")
	public String getEnterpriseCurrentStatus()
	{
		return this.enterpriseCurrentStatus;
	}

	public void setEnterpriseCurrentStatus(String enterpriseCurrentStatus)
	{
		this.enterpriseCurrentStatus = enterpriseCurrentStatus;
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

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

}
