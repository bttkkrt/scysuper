package com.jshx.tailinglibinfo.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.diggingsinfo.entity.Diggingsinfo;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;
import com.jshx.tailinglibinfo.entity.TailingLibInfo;
import com.jshx.tailinglibinfo.service.TailingLibInfoService;

public class TailingLibInfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private TailingLibInfo tailingLibInfo = new TailingLibInfo();

	/**
	 * 业务类
	 */
	@Autowired
	private TailingLibInfoService tailingLibInfoService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryBaseProduceStartdateStart;

	private Date queryBaseProduceStartdateEnd;

	private Date queryBaseCompanyStart;

	private Date queryBaseCompanyEnd;

	private Date queryBaseLegalPersonStart;

	private Date queryBaseLegalPersonEnd;

	private Date queryBaseMainbodyStart;

	private Date queryBaseMainbodyEnd;

	private Date queryBaseSafetyLicStartdateStart;

	private Date queryBaseSafetyLicStartdateEnd;
	
	private String actionurl;
	private String tabid;
	private String tabname;
	
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ReviewLogService reviewLogService;
	private String type;//type为0表示只能看，type为1表示能审核
	private String deptCodeLenth;
	private String ifzsqy;
	private String xjshState;//县级审核状态
	private String xjBack;//县级审核备注
	private String sjshState;//市级审核状态
	private String sjBack;//市级审核备注
	private String deptflag;
	private String deptcode;
	private String deptrole;
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != tailingLibInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != tailingLibInfo.getBaseLocal()) && (0 < tailingLibInfo.getBaseLocal().trim().length())){
				paraMap.put("baseLocal", "%" + tailingLibInfo.getBaseLocal().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTrade()) && (0 < tailingLibInfo.getBaseTrade().trim().length())){
				paraMap.put("baseTrade", "%" + tailingLibInfo.getBaseTrade().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingType()) && (0 < tailingLibInfo.getBaseTailingType().trim().length())){
				paraMap.put("baseTailingType", "%" + tailingLibInfo.getBaseTailingType().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingLevel()) && (0 < tailingLibInfo.getBaseTailingLevel().trim().length())){
				paraMap.put("baseTailingLevel", "%" + tailingLibInfo.getBaseTailingLevel().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseSafetyDegree()) && (0 < tailingLibInfo.getBaseSafetyDegree().trim().length())){
				paraMap.put("baseSafetyDegree", "%" + tailingLibInfo.getBaseSafetyDegree().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseBuildDamType()) && (0 < tailingLibInfo.getBaseBuildDamType().trim().length())){
				paraMap.put("baseBuildDamType", "%" + tailingLibInfo.getBaseBuildDamType().trim() + "%");
			}

			if (null != queryBaseProduceStartdateStart){
				paraMap.put("startBaseProduceStartdate", queryBaseProduceStartdateStart);
			}

			if (null != queryBaseProduceStartdateEnd){
				paraMap.put("endBaseProduceStartdate", queryBaseProduceStartdateEnd);
			}
			
			if (null != tailingLibInfo.getBaseCompany()){
				paraMap.put("baseCompany", tailingLibInfo.getBaseCompany());
			}
			if (null != tailingLibInfo.getBaseLegalPerson()){
				paraMap.put("baseLegalPerson", tailingLibInfo.getBaseLegalPerson());
			}
			if (null != tailingLibInfo.getBaseMainbody()){
				paraMap.put("baseMainbody", tailingLibInfo.getBaseMainbody());
			}
			
			
			
			if ((null != tailingLibInfo.getBaseTailingHead()) && (0 < tailingLibInfo.getBaseTailingHead().trim().length())){
				paraMap.put("baseTailingHead", "%" + tailingLibInfo.getBaseTailingHead().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingHeadTel()) && (0 < tailingLibInfo.getBaseTailingHeadTel().trim().length())){
				paraMap.put("baseTailingHeadTel", "%" + tailingLibInfo.getBaseTailingHeadTel().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseCountyHead()) && (0 < tailingLibInfo.getBaseCountyHead().trim().length())){
				paraMap.put("baseCountyHead", "%" + tailingLibInfo.getBaseCountyHead().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseCountyHeadTel()) && (0 < tailingLibInfo.getBaseCountyHeadTel().trim().length())){
				paraMap.put("baseCountyHeadTel", "%" + tailingLibInfo.getBaseCountyHeadTel().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTownHead()) && (0 < tailingLibInfo.getBaseTownHead().trim().length())){
				paraMap.put("baseTownHead", "%" + tailingLibInfo.getBaseTownHead().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTownHeadTel()) && (0 < tailingLibInfo.getBaseTownHeadTel().trim().length())){
				paraMap.put("baseTownHeadTel", "%" + tailingLibInfo.getBaseTownHeadTel().trim() + "%");
			}

			if (null != queryBaseSafetyLicStartdateStart){
				paraMap.put("startBaseSafetyLicStartdate", queryBaseSafetyLicStartdateStart);
			}

			if (null != queryBaseSafetyLicStartdateEnd){
				paraMap.put("endBaseSafetyLicStartdate", queryBaseSafetyLicStartdateEnd);
			}
			if ((null != tailingLibInfo.getBaseSafetyLicValidity()) && (0 < tailingLibInfo.getBaseSafetyLicValidity().trim().length())){
				paraMap.put("baseSafetyLicValidity", "%" + tailingLibInfo.getBaseSafetyLicValidity().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseStandardLevel()) && (0 < tailingLibInfo.getBaseStandardLevel().trim().length())){
				paraMap.put("baseStandardLevel", "%" + tailingLibInfo.getBaseStandardLevel().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingBelong()) && (0 < tailingLibInfo.getBaseTailingBelong().trim().length())){
				paraMap.put("baseTailingBelong", "%" + tailingLibInfo.getBaseTailingBelong().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingLong()) && (0 < tailingLibInfo.getBaseTailingLong().trim().length())){
				paraMap.put("baseTailingLong", "%" + tailingLibInfo.getBaseTailingLong().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingLat()) && (0 < tailingLibInfo.getBaseTailingLat().trim().length())){
				paraMap.put("baseTailingLat", "%" + tailingLibInfo.getBaseTailingLat().trim() + "%");
			}

			if ((null != tailingLibInfo.getBaseTailingImg()) && (0 < tailingLibInfo.getBaseTailingImg().trim().length())){
				paraMap.put("baseTailingImg", "%" + tailingLibInfo.getBaseTailingImg().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaDesignTime()) && (0 < tailingLibInfo.getParaDesignTime().trim().length())){
				paraMap.put("paraDesignTime", "%" + tailingLibInfo.getParaDesignTime().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaFactTime()) && (0 < tailingLibInfo.getParaFactTime().trim().length())){
				paraMap.put("paraFactTime", "%" + tailingLibInfo.getParaFactTime().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaDesignTotal()) && (0 < tailingLibInfo.getParaDesignTotal().trim().length())){
				paraMap.put("paraDesignTotal", "%" + tailingLibInfo.getParaDesignTotal().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaAlready()) && (0 < tailingLibInfo.getParaAlready().trim().length())){
				paraMap.put("paraAlready", "%" + tailingLibInfo.getParaAlready().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaFinalHigh()) && (0 < tailingLibInfo.getParaFinalHigh().trim().length())){
				paraMap.put("paraFinalHigh", "%" + tailingLibInfo.getParaFinalHigh().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaSafetyHigh()) && (0 < tailingLibInfo.getParaSafetyHigh().trim().length())){
				paraMap.put("paraSafetyHigh", "%" + tailingLibInfo.getParaSafetyHigh().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaMoistenHigh()) && (0 < tailingLibInfo.getParaMoistenHigh().trim().length())){
				paraMap.put("paraMoistenHigh", "%" + tailingLibInfo.getParaMoistenHigh().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaLength()) && (0 < tailingLibInfo.getParaLength().trim().length())){
				paraMap.put("paraLength", "%" + tailingLibInfo.getParaLength().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaStackType()) && (0 < tailingLibInfo.getParaStackType().trim().length())){
				paraMap.put("paraStackType", "%" + tailingLibInfo.getParaStackType().trim() + "%");
			}

			if ((null != tailingLibInfo.getParaDrainfloods()) && (0 < tailingLibInfo.getParaDrainfloods().trim().length())){
				paraMap.put("paraDrainfloods", "%" + tailingLibInfo.getParaDrainfloods().trim() + "%");
			}

			if ((null != tailingLibInfo.getPrimeDamHigh()) && (0 < tailingLibInfo.getPrimeDamHigh().trim().length())){
				paraMap.put("primeDamHigh", "%" + tailingLibInfo.getPrimeDamHigh().trim() + "%");
			}

			if ((null != tailingLibInfo.getPrimeDamLength()) && (0 < tailingLibInfo.getPrimeDamLength().trim().length())){
				paraMap.put("primeDamLength", "%" + tailingLibInfo.getPrimeDamLength().trim() + "%");
			}

			if ((null != tailingLibInfo.getPrimeDamWidth()) && (0 < tailingLibInfo.getPrimeDamWidth().trim().length())){
				paraMap.put("primeDamWidth", "%" + tailingLibInfo.getPrimeDamWidth().trim() + "%");
			}

			if ((null != tailingLibInfo.getPrimeInnerSlope()) && (0 < tailingLibInfo.getPrimeInnerSlope().trim().length())){
				paraMap.put("primeInnerSlope", "%" + tailingLibInfo.getPrimeInnerSlope().trim() + "%");
			}

			if ((null != tailingLibInfo.getPrimeOutSlope()) && (0 < tailingLibInfo.getPrimeOutSlope().trim().length())){
				paraMap.put("primeOutSlope", "%" + tailingLibInfo.getPrimeOutSlope().trim() + "%");
			}

			if ((null != tailingLibInfo.getAccumulateDesignHigh()) && (0 < tailingLibInfo.getAccumulateDesignHigh().trim().length())){
				paraMap.put("accumulateDesignHigh", "%" + tailingLibInfo.getAccumulateDesignHigh().trim() + "%");
			}

			if ((null != tailingLibInfo.getAccumulateFactHigh()) && (0 < tailingLibInfo.getAccumulateFactHigh().trim().length())){
				paraMap.put("accumulateFactHigh", "%" + tailingLibInfo.getAccumulateFactHigh().trim() + "%");
			}

			if ((null != tailingLibInfo.getAccumulateDamLength()) && (0 < tailingLibInfo.getAccumulateDamLength().trim().length())){
				paraMap.put("accumulateDamLength", "%" + tailingLibInfo.getAccumulateDamLength().trim() + "%");
			}

			if ((null != tailingLibInfo.getAccumulateInnerSlope()) && (0 < tailingLibInfo.getAccumulateInnerSlope().trim().length())){
				paraMap.put("accumulateInnerSlope", "%" + tailingLibInfo.getAccumulateInnerSlope().trim() + "%");
			}

			if ((null != tailingLibInfo.getAccumulateOutSlope()) && (0 < tailingLibInfo.getAccumulateOutSlope().trim().length())){
				paraMap.put("accumulateOutSlope", "%" + tailingLibInfo.getAccumulateOutSlope().trim() + "%");
			}

			if ((null != tailingLibInfo.getAccumulateDamWidth()) && (0 < tailingLibInfo.getAccumulateDamWidth().trim().length())){
				paraMap.put("accumulateDamWidth", "%" + tailingLibInfo.getAccumulateDamWidth().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckDanger()) && (0 < tailingLibInfo.getCheckDanger().trim().length())){
				paraMap.put("checkDanger", "%" + tailingLibInfo.getCheckDanger().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckPercolation()) && (0 < tailingLibInfo.getCheckPercolation().trim().length())){
				paraMap.put("checkPercolation", "%" + tailingLibInfo.getCheckPercolation().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckBeautyspot()) && (0 < tailingLibInfo.getCheckBeautyspot().trim().length())){
				paraMap.put("checkBeautyspot", "%" + tailingLibInfo.getCheckBeautyspot().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckFamilyNo()) && (0 < tailingLibInfo.getCheckFamilyNo().trim().length())){
				paraMap.put("checkFamilyNo", "%" + tailingLibInfo.getCheckFamilyNo().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckPersonNo()) && (0 < tailingLibInfo.getCheckPersonNo().trim().length())){
				paraMap.put("checkPersonNo", "%" + tailingLibInfo.getCheckPersonNo().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckRoadNo()) && (0 < tailingLibInfo.getCheckRoadNo().trim().length())){
				paraMap.put("checkRoadNo", "%" + tailingLibInfo.getCheckRoadNo().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckRailwayNo()) && (0 < tailingLibInfo.getCheckRailwayNo().trim().length())){
				paraMap.put("checkRailwayNo", "%" + tailingLibInfo.getCheckRailwayNo().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckSchoolNo()) && (0 < tailingLibInfo.getCheckSchoolNo().trim().length())){
				paraMap.put("checkSchoolNo", "%" + tailingLibInfo.getCheckSchoolNo().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckFactoryNo()) && (0 < tailingLibInfo.getCheckFactoryNo().trim().length())){
				paraMap.put("checkFactoryNo", "%" + tailingLibInfo.getCheckFactoryNo().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckDrainfloodsState()) && (0 < tailingLibInfo.getCheckDrainfloodsState().trim().length())){
				paraMap.put("checkDrainfloodsState", "%" + tailingLibInfo.getCheckDrainfloodsState().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckMonitorEquipment()) && (0 < tailingLibInfo.getCheckMonitorEquipment().trim().length())){
				paraMap.put("checkMonitorEquipment", "%" + tailingLibInfo.getCheckMonitorEquipment().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckAqxzUnit()) && (0 < tailingLibInfo.getCheckAqxzUnit().trim().length())){
				paraMap.put("checkAqxzUnit", "%" + tailingLibInfo.getCheckAqxzUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckYpjbgbzUnit()) && (0 < tailingLibInfo.getCheckYpjbgbzUnit().trim().length())){
				paraMap.put("checkYpjbgbzUnit", "%" + tailingLibInfo.getCheckYpjbgbzUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckYpjbaUnit()) && (0 < tailingLibInfo.getCheckYpjbaUnit().trim().length())){
				paraMap.put("checkYpjbaUnit", "%" + tailingLibInfo.getCheckYpjbaUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckCbsjbzUnit()) && (0 < tailingLibInfo.getCheckCbsjbzUnit().trim().length())){
				paraMap.put("checkCbsjbzUnit", "%" + tailingLibInfo.getCheckCbsjbzUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckCbsjspUnit()) && (0 < tailingLibInfo.getCheckCbsjspUnit().trim().length())){
				paraMap.put("checkCbsjspUnit", "%" + tailingLibInfo.getCheckCbsjspUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckJgyspjbgbzUnit()) && (0 < tailingLibInfo.getCheckJgyspjbgbzUnit().trim().length())){
				paraMap.put("checkJgyspjbgbzUnit", "%" + tailingLibInfo.getCheckJgyspjbgbzUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckJgyspjspUnit()) && (0 < tailingLibInfo.getCheckJgyspjspUnit().trim().length())){
				paraMap.put("checkJgyspjspUnit", "%" + tailingLibInfo.getCheckJgyspjspUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckTdsyspUnit()) && (0 < tailingLibInfo.getCheckTdsyspUnit().trim().length())){
				paraMap.put("checkTdsyspUnit", "%" + tailingLibInfo.getCheckTdsyspUnit().trim() + "%");
			}

			if ((null != tailingLibInfo.getCheckHbysspUnit()) && (0 < tailingLibInfo.getCheckHbysspUnit().trim().length())){
				paraMap.put("checkHbysspUnit", "%" + tailingLibInfo.getCheckHbysspUnit().trim() + "%");
			}

		}
		
		pagination = tailingLibInfoService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != tailingLibInfo)&&(null != tailingLibInfo.getId()))
			tailingLibInfo = tailingLibInfoService.getById(tailingLibInfo.getId());
		
		setType(type);
		/**
		 * hyc 2014-12-11
		 * 获得登陆人的部门长度，方便jsp页面判断审核是市级还是县级还是镇级
		 * 获得登陆用户名
		 */
		deptCodeLenth = this.getLoginUserDepartment().getDeptCode().length()+"";
		/**
		 * end
		 */
		/**
		 * 查询审核日志表
		 */
		if(tailingLibInfo.getIfzsqy() != null && tailingLibInfo.getIfzsqy().equals("1")){
			ifzsqy = "1";
		}else{
			ifzsqy = "0";
		}
		
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String dutyFlag = "";

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		paraMap.put("itemId", tailingLibInfo.getId());
		paraMap.put("itemType", "1");
		pagination = reviewLogService.findByPage(pagination, paraMap);
		List reviewList = pagination.getList();
		if(reviewList != null && reviewList.size() > 0){
			for(int i=0;i<reviewList.size();i++){
				ReviewLog reviewLog = (ReviewLog) reviewList.get(i);
				dutyFlag = reviewLog.getDutyFlag();
				if(dutyFlag.equals("2")){//县级领导
					xjshState = reviewLog.getState();
					xjBack = reviewLog.getRemark();
				}else if(dutyFlag.equals("1")){//市级领导
					sjshState = reviewLog.getState();
					sjBack = reviewLog.getRemark();
				}
			}
		}
		return VIEW;
	}
	
	/**
	 * 查看详细信息
	 */
	public String initList() throws Exception{
		deptflag="99";
		String deptCode = this.getLoginUserDepartment().getDeptCode();

		String deptRole = this.getLoginUser().getDeptRole();
		if(null != deptRole && deptRole.equals("21")){//企业人员
			deptflag = "2";
		}else{
			//根据部门角色进行查询条件过滤
			if(null != deptRole && (deptRole.contains(SysPropertiesUtil.getProperty("fmksCode"))||deptRole.contains(SysPropertiesUtil.getProperty("whpCode"))
					||deptRole.contains(SysPropertiesUtil.getProperty("fmgmCode"))||deptRole.contains(SysPropertiesUtil.getProperty("zywhCode")))){
				deptflag = "1";
			} else {
				deptflag = "0";
			}
		}
		
		deptcode = deptCode.length()+"";
		if(!"1".equals(type) || (this.getLoginUserDepartment().getDeptName().contains("安监中队") && deptCode.length() != 9))
		{
			tailingLibInfo.setState("3");
		}
		if(this.getLoginUser().getLoginId().equals("admin"))
		{
			flag = "1";
		}
		return SUCCESS;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 初始化审核信息
	 */
	public String shenhe() throws Exception{
		view();
	    return SUCCESS;
	}
	
	//状态
	public void findState(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String str= "{\"state\":\"4\"}";
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != ids ){
			if (ids != null){
				paraMap.put("itemId", ids);
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String dutyFlag = "99";
			if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"))){//市级部门
				dutyFlag = "1";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"))){//县级部门
				dutyFlag = "2";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"))){//镇级部门
				dutyFlag = "3";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("villageDeptCodeLength"))){//村级部门
				dutyFlag = "4";
			}
			String deptRole = this.getLoginUser().getDeptRole();
			if(deptRole.equals("21")){
				paraMap.put("dutyFlag", "1");
			}else{
				paraMap.put("dutyFlag", dutyFlag);
			}
			
			pagination = reviewLogService.findByPage(pagination, paraMap);
			List list = pagination.getList();
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					ReviewLog reviewLog = (ReviewLog) list.get(0);
					str = "{\"state\":\""+reviewLog.getState()+"\"}";
				}
			}
		}
		try {
			this.getResponse().getWriter().println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			tailingLibInfo.setDeptId(this.getLoginUserDepartmentId());
			tailingLibInfo.setDelFlag(0);
			tailingLibInfo.setState("0");
			tailingLibInfo.setDutyFlag("0");
			tailingLibInfo.setIfzsqy(company.getIfzsqy());
			tailingLibInfo.setZsqytype(company.getZsqytype());
			tailingLibInfoService.save(tailingLibInfo);
			
			//hanxc 20141211 生成待审核任务 start
			ReviewLog newReviewLog = new ReviewLog();
			if(null != tailingLibInfo.getIfzsqy() && tailingLibInfo.getIfzsqy().equals("1")){
				newReviewLog.setDutyFlag("1");//市级部门审核任务
			}else{
				newReviewLog.setDutyFlag("2");//县级部门审核任务
			}
			newReviewLog.setState("0");
			newReviewLog.setItemId(tailingLibInfo.getId());
			newReviewLog.setItemType("1");//企业信息类型：type=1 
			newReviewLog.setStartTime(new Date());
			reviewLogService.saveNewTask(newReviewLog);
		}else{
			tailingLibInfoService.update(tailingLibInfo);
		}
		return RELOAD;
	}

	/**
	 * 保存审核信息
	 */
	public String shenhesave() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(null != tailingLibInfo && null != tailingLibInfo.getId()){
			String state = tailingLibInfo.getState();
			String shbz = tailingLibInfo.getShbs();
			String a = "";
			TailingLibInfo tailing = tailingLibInfoService.getById(tailingLibInfo.getId());
			tailing.setState(state);
			tailing.setShbs(shbz);
			
			/**
			 * 安监领导审批信息
			 */
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 start
			String dutyFlag = "0";
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"))){//市级部门
				dutyFlag = "1";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"))){//县级部门
				dutyFlag = "2";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"))){//镇级部门
				dutyFlag = "3";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("villageDeptCodeLength"))){//村级部门
				dutyFlag = "4";
			}
			tailing.setDutyFlag(dutyFlag);
			
			Pagination page = new Pagination(this.getRequest());
			Map<String, Object> tempParaMap = new HashMap<String, Object>();
			tempParaMap.put("itemId", tailing.getId());
			tempParaMap.put("dutyFlag", dutyFlag);
			page = reviewLogService.findByPage(page, tempParaMap);
			List rlList = page.getList();
			if(!rlList.isEmpty()){ 
				ReviewLog reviewLog = (ReviewLog)rlList.get(0);
				reviewLog.setItemType("1");
				reviewLog.setState(tailing.getState());
				reviewLog.setUserId(this.getLoginUserId());
				reviewLog.setUserName(this.getLoginUser().getDisplayName());
				reviewLog.setUserDeptCode(deptCode);
				reviewLog.setEndTime(new Date());
				reviewLog.setRecord("");
				reviewLog.setRemark(tailing.getShbs());
				reviewLogService.saveReviewLogAndSetNextTask(reviewLog);
			}
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 end
			
			tailingLibInfoService.update(tailing);
		}
		
		return RELOAD;
	}
	
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			tailingLibInfoService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public TailingLibInfo getTailingLibInfo(){
		return this.tailingLibInfo;
	}

	public void setTailingLibInfo(TailingLibInfo tailingLibInfo){
		this.tailingLibInfo = tailingLibInfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryBaseProduceStartdateStart(){
		return this.queryBaseProduceStartdateStart;
	}

	public void setQueryBaseProduceStartdateStart(Date queryBaseProduceStartdateStart){
		this.queryBaseProduceStartdateStart = queryBaseProduceStartdateStart;
	}

	public Date getQueryBaseProduceStartdateEnd(){
		return this.queryBaseProduceStartdateEnd;
	}

	public void setQueryBaseProduceStartdateEnd(Date queryBaseProduceStartdateEnd){
		this.queryBaseProduceStartdateEnd = queryBaseProduceStartdateEnd;
	}

	public Date getQueryBaseCompanyStart(){
		return this.queryBaseCompanyStart;
	}

	public void setQueryBaseCompanyStart(Date queryBaseCompanyStart){
		this.queryBaseCompanyStart = queryBaseCompanyStart;
	}

	public Date getQueryBaseCompanyEnd(){
		return this.queryBaseCompanyEnd;
	}

	public void setQueryBaseCompanyEnd(Date queryBaseCompanyEnd){
		this.queryBaseCompanyEnd = queryBaseCompanyEnd;
	}

	public Date getQueryBaseLegalPersonStart(){
		return this.queryBaseLegalPersonStart;
	}

	public void setQueryBaseLegalPersonStart(Date queryBaseLegalPersonStart){
		this.queryBaseLegalPersonStart = queryBaseLegalPersonStart;
	}

	public Date getQueryBaseLegalPersonEnd(){
		return this.queryBaseLegalPersonEnd;
	}

	public void setQueryBaseLegalPersonEnd(Date queryBaseLegalPersonEnd){
		this.queryBaseLegalPersonEnd = queryBaseLegalPersonEnd;
	}

	public Date getQueryBaseMainbodyStart(){
		return this.queryBaseMainbodyStart;
	}

	public void setQueryBaseMainbodyStart(Date queryBaseMainbodyStart){
		this.queryBaseMainbodyStart = queryBaseMainbodyStart;
	}

	public Date getQueryBaseMainbodyEnd(){
		return this.queryBaseMainbodyEnd;
	}

	public void setQueryBaseMainbodyEnd(Date queryBaseMainbodyEnd){
		this.queryBaseMainbodyEnd = queryBaseMainbodyEnd;
	}

	public Date getQueryBaseSafetyLicStartdateStart(){
		return this.queryBaseSafetyLicStartdateStart;
	}

	public void setQueryBaseSafetyLicStartdateStart(Date queryBaseSafetyLicStartdateStart){
		this.queryBaseSafetyLicStartdateStart = queryBaseSafetyLicStartdateStart;
	}

	public Date getQueryBaseSafetyLicStartdateEnd(){
		return this.queryBaseSafetyLicStartdateEnd;
	}

	public void setQueryBaseSafetyLicStartdateEnd(Date queryBaseSafetyLicStartdateEnd){
		this.queryBaseSafetyLicStartdateEnd = queryBaseSafetyLicStartdateEnd;
	}

	public String getActionurl() {
		return actionurl;
	}

	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}

	public String getTabid() {
		return tabid;
	}

	public void setTabid(String tabid) {
		this.tabid = tabid;
	}

	public String getTabname() {
		return tabname;
	}

	public void setTabname(String tabname) {
		this.tabname = tabname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeptCodeLenth() {
		return deptCodeLenth;
	}

	public void setDeptCodeLenth(String deptCodeLenth) {
		this.deptCodeLenth = deptCodeLenth;
	}

	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}

	public String getXjshState() {
		return xjshState;
	}

	public void setXjshState(String xjshState) {
		this.xjshState = xjshState;
	}

	public String getXjBack() {
		return xjBack;
	}

	public void setXjBack(String xjBack) {
		this.xjBack = xjBack;
	}

	public String getSjshState() {
		return sjshState;
	}

	public void setSjshState(String sjshState) {
		this.sjshState = sjshState;
	}

	public String getSjBack() {
		return sjBack;
	}

	public void setSjBack(String sjBack) {
		this.sjBack = sjBack;
	}

	public String getDeptflag() {
		return deptflag;
	}

	public void setDeptflag(String deptflag) {
		this.deptflag = deptflag;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptrole() {
		return deptrole;
	}

	public void setDeptrole(String deptrole) {
		this.deptrole = deptrole;
	}

	
	
	
}
