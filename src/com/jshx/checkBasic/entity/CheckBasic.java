package com.jshx.checkBasic.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.jshx.checkLawEnforce.entity.CheckLawEnforce;
import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkSituation.entity.CheckSituation;
import com.jshx.company.entity.Company;
import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.entity.User;
import com.jshx.rectifyOpinion.entity.RectifyOpinion;
import com.jshx.yhqd.entity.Yhqd;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CHECK_BASIC")
public class CheckBasic extends BaseModel
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
	 * 企业
	 */
	private Company company;

	/**
	 * 检查时间
	 */
	private Date checkTime;

	/**
	 * 检查人
	 */
	private User checker;

	/**
	 * 检查结果
	 */
	private List<CheckResult> resultList;
	
	//现场检查记录对应部分内容
	
	/**
	 * 检查场所
	 */
	private String checkPlace;
	
	/**
	 * 检查时间起
	 */
	private Date checkSta;
	
	/**
	 * 检查时间止
	 */
	private Date checkEnd;
	
	
	/**
	 * 检查机构 
	 */
	private String checkOrganization;
	
	/**
	 * 执法人员集合
	 */
	private List<CheckLawEnforce> enforceList;
	
	/**
	 * 检查情况
	 */
	private List<CheckSituation> situationList;
	/**
	 * 隐患清单
	 */
	private List<Yhqd> yhqdList;
	
	
	//责令限期整改指令书
	
	/**
	 * 整改项
	 */
	private String rectifyTerm;
	
	/**
	 * 整改日期
	 */
	private Date rectifyDate;
	
	/**
	 * 整改问题
	 */
	private String rectifyState;
	
	/**
	 * 整改提出日期
	 */
	private Date rectifyBeginTime;
	
	/**
	 * 整改问题
	 */
	private List<CheckSituation> rectifyList;
	
	
	//整改复查意见书
	
	/**
	 * 整改执法文书号
	 */
	private String rectifyNum;
	
	/**
	 * 整改执法文书年号
	 */
	private String rectifyYear;
	
	/**
	 * 复查内容
	 */
	private String reviewContent;
	
	/**
	 * 整改完成时间
	 */
	private Date rectifyDoneTime;
	
	/**
	 * 整改标记位
	 * 0 无整改项；1 有待整改项；2 已完成整改
	 */
	private String rectifyFlag;
	
	/**
	 * 整改意见
	 */
	private List<RectifyOpinion> rectifyOpinionList;
	
	/**
	 * 监管部门主管领导
	 */
	private String jgld;
	/**
	 * 被检查公司领导
	 */
	private String gsld;
	/**
	 * 参检专家
	 */
	private String cjzj;
	
	private String glfm;
	private String xwfm;
	private String zyhjsb;
	
	
	
	/**  
	 * 获取jgld  
	 * @return jgld jgld  
	 */
	@Column
	public String getJgld() {
		return jgld;
	}

	/**  
	 * 设置jgld  
	 * @param jgld jgld  
	 */
	public void setJgld(String jgld) {
		this.jgld = jgld;
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

	
	@Column(name="CHECK_TIME")
	public Date getCheckTime()
	{
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime)
	{
		this.checkTime = checkTime;
	}
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=User.class)
    @JoinColumn(name="CHECKER")
	public User getChecker()
	{
		return this.checker;
	}

	public void setChecker(User checker)
	{
		this.checker = checker;
	}

	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Company.class)
    @JoinColumn(name="COMPANY_ID")
	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	@OneToMany(mappedBy="basic", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	@Where(clause="delFlag=0")
	public List<CheckResult> getResultList()
	{
		return resultList;
	}

	public void setResultList(List<CheckResult> resultList)
	{
		this.resultList = resultList;
	}

	@Column(name="CHECKER_PLACE")
	public String getCheckPlace()
	{
		return checkPlace;
	}

	public void setCheckPlace(String checkPlace)
	{
		this.checkPlace = checkPlace;
	}

	@Column(name="CHECK_STA")
	public Date getCheckSta()
	{
		return checkSta;
	}

	public void setCheckSta(Date checkSta)
	{
		this.checkSta = checkSta;
	}

	@Column(name="CHECK_END")
	public Date getCheckEnd()
	{
		return checkEnd;
	}

	public void setCheckEnd(Date checkEnd)
	{
		this.checkEnd = checkEnd;
	}

	@Column(name="CHECK_ORGANIZATION")
	public String getCheckOrganization()
	{
		return checkOrganization;
	}

	public void setCheckOrganization(String checkOrganization)
	{
		this.checkOrganization = checkOrganization;
	}

	@Column(name="RECTIFY_TERM")
	public String getRectifyTerm()
	{
		return rectifyTerm;
	}

	public void setRectifyTerm(String rectifyTerm)
	{
		this.rectifyTerm = rectifyTerm;
	}

	@Column(name="RECTIFY_DATE")
	public Date getRectifyDate()
	{
		return rectifyDate;
	}

	public void setRectifyDate(Date rectifyDate)
	{
		this.rectifyDate = rectifyDate;
	}

	@Column(name="RECTIFY_STATE")
	public String getRectifyState()
	{
		return rectifyState;
	}

	public void setRectifyState(String rectifyState)
	{
		this.rectifyState = rectifyState;
	}

	@Column(name="RECTIFY_NUM")
	public String getRectifyNum()
	{
		return rectifyNum;
	}

	public void setRectifyNum(String rectifyNum)
	{
		this.rectifyNum = rectifyNum;
	}

	@OneToMany(mappedBy="basic", cascade={javax.persistence.CascadeType.ALL})
	@Where(clause="delFlag=0")
	public List<CheckLawEnforce> getEnforceList()
	{
		return enforceList;
	}

	public void setEnforceList(List<CheckLawEnforce> enforceList)
	{
		this.enforceList = enforceList;
	}

	@OneToMany(mappedBy="basic", cascade={javax.persistence.CascadeType.ALL})
	@Where(clause="delFlag=0")
	public List<CheckSituation> getSituationList()
	{
		return situationList;
	}

	public void setSituationList(List<CheckSituation> situationList)
	{
		this.situationList = situationList;
	}
	
	@OneToMany(mappedBy="basic", cascade={javax.persistence.CascadeType.ALL})
	@Where(clause="delFlag=0")
	public List<Yhqd> getYhqdList()
	{
		return yhqdList;
	}
	
	public void setYhqdList(List<Yhqd> yhqdList)
	{
		this.yhqdList = yhqdList;
	}

	@Column(name="RECTIFY_FLAG")
	public String getRectifyFlag()
	{
		return rectifyFlag;
	}

	public void setRectifyFlag(String rectifyFlag)
	{
		this.rectifyFlag = rectifyFlag;
	}

	@Column(name="RECTIFY_DONE_TIME")
	public Date getRectifyDoneTime()
	{
		return rectifyDoneTime;
	}

	public void setRectifyDoneTime(Date rectifyDoneTime)
	{
		this.rectifyDoneTime = rectifyDoneTime;
	}

	@OneToMany(mappedBy="basic", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	@Where(clause="delFlag=0")
	public List<RectifyOpinion> getRectifyOpinionList()
	{
		return rectifyOpinionList;
	}

	public void setRectifyOpinionList(List<RectifyOpinion> rectifyOpinionList)
	{
		this.rectifyOpinionList = rectifyOpinionList;
	}

	@Column(name="RECTIFY_BEGIN_TIME")
	public Date getRectifyBeginTime()
	{
		return rectifyBeginTime;
	}

	public void setRectifyBeginTime(Date rectifyBeginTime)
	{
		this.rectifyBeginTime = rectifyBeginTime;
	}

	@Column(name="REVIEW_CONTENT")
	public String getReviewContent()
	{
		return reviewContent;
	}

	public void setReviewContent(String reviewContent)
	{
		this.reviewContent = reviewContent;
	}

	@OneToMany(mappedBy="basic", cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY)
	@Where(clause="delFlag=0 and checkFlag=1")
	public List<CheckSituation> getRectifyList()
	{
		return rectifyList;
	}

	public void setRectifyList(List<CheckSituation> rectifyList)
	{
		this.rectifyList = rectifyList;
	}

	@Column(name="RECTIFY_YEAR")
	public String getRectifyYear()
	{
		return rectifyYear;
	}

	public void setRectifyYear(String rectifyYear)
	{
		this.rectifyYear = rectifyYear;
	}

	/**  
	 * 获取管理方面  
	 * @return glfm    
	 */
	@Column(name="GLFM")
	public String getGlfm() {
		return glfm;
	}

	/**  
	 * 设置glfm  
	 * @param glfm    
	 */
	public void setGlfm(String glfm) {
		this.glfm = glfm;
	}

	/**  
	 * 获取行为方面  
	 * @return xwfm    
	 */
	@Column(name="XWFM")
	public String getXwfm() {
		return xwfm;
	}

	/**  
	 * 设置xwfm  
	 * @param xwfm    
	 */
	public void setXwfm(String xwfm) {
		this.xwfm = xwfm;
	}

	/**  
	 * 获取作业环境及设备设施  
	 * @return zyhjsb    
	 */
	@Column(name="ZYHJSB")
	public String getZyhjsb() {
		return zyhjsb;
	}

	/**  
	 * 设置zyhjsb  
	 * @param zyhjsb    
	 */
	public void setZyhjsb(String zyhjsb){
		this.zyhjsb = zyhjsb;
	}

	/**  
	 * 获取gsld  
	 * @return gsld    
	 */
	@Column(name="GSLD")
	public String getGsld() {
		return gsld;
	}

	/**  
	 * 设置gsld  
	 * @param gsld    
	 */
	public void setGsld(String gsld) {
		this.gsld = gsld;
	}

	/**  
	 * 获取cjzj  
	 * @return cjzj    
	 */
	@Column(name="CJZJ")
	public String getCjzj() {
		return cjzj;
	}

	/**  
	 * 设置cjzj  
	 * @param cjzj    
	 */
	public void setCjzj(String cjzj) {
		this.cjzj = cjzj;
	}
	
	
}
