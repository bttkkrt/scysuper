package com.jshx.diggingsinfo.web;

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
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;
import com.jshx.sbshcl.entity.JshxSbshcl;
import com.jshx.diggingsinfo.entity.Diggingsinfo;
import com.jshx.diggingsinfo.service.DiggingsinfoService;

public class DiggingsinfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Diggingsinfo diggingsinfo = new Diggingsinfo();

	/**
	 * 业务类
	 */
	@Autowired
	private DiggingsinfoService diggingsinfoService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryProduceStartdateStart;

	private Date queryProduceStartdateEnd;

	private Date queryExploitCertificateStartdateStart;

	private Date queryExploitCertificateStartdateEnd;

	private Date queryExploitCertificateEnddateStart;

	private Date queryExploitCertificateEnddateEnd;

	private Date querySafeCertificateStartdateStart;

	private Date querySafeCertificateStartdateEnd;

	private Date querySafeCertificateEnddateStart;

	private Date querySafeCertificateEnddateEnd;

	
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
	
	public String initList() throws Exception{
		deptflag="99";
		String deptCode = this.getLoginUserDepartment().getDeptCode();

		String deptRole = this.getLoginUser().getDeptRole();
		if(null != deptRole && deptRole.equals("21")){//企业人员
			deptflag = "2";
		}else{
			//根据部门角色进行查询条件过滤
			if(null != deptRole && (deptRole.contains(SysPropertiesUtil.getProperty("fmksCode"))||deptRole.contains(SysPropertiesUtil.getProperty("whpCode"))
					||deptRole.contains(SysPropertiesUtil.getProperty("fmgmCode"))||deptRole.contains(SysPropertiesUtil.getProperty("zywhCode")))){//部门如果是监管一处（非煤矿山）
				deptflag = "1";
			} else {
				deptflag = "0";
			}
		}
		
		deptcode = deptCode.length()+"";
		if(!"1".equals(type) || (this.getLoginUserDepartment().getDeptName().contains("安监中队") && deptCode.length() != 9))
		{
			diggingsinfo.setState("3");
		}
		if(this.getLoginUser().getLoginId().equals("admin"))
		{
			flag = "1";
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != diggingsinfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != diggingsinfo.getCounty()) && (0 < diggingsinfo.getCounty().trim().length())){
				paraMap.put("county", "%" + diggingsinfo.getCounty().trim() + "%");
			}

			if ((null != diggingsinfo.getMainoreType()) && (0 < diggingsinfo.getMainoreType().trim().length())){
				paraMap.put("mainoreType", "%" + diggingsinfo.getMainoreType().trim() + "%");
			}

			if ((null != diggingsinfo.getExploitType()) && (0 < diggingsinfo.getExploitType().trim().length())){
				paraMap.put("exploitType", "%" + diggingsinfo.getExploitType().trim() + "%");
			}

			if (null != queryProduceStartdateStart){
				paraMap.put("startProduceStartdate", queryProduceStartdateStart);
			}

			if (null != queryProduceStartdateEnd){
				paraMap.put("endProduceStartdate", queryProduceStartdateEnd);
			}
			if ((null != diggingsinfo.getDesignServeAgelimit()) && (0 < diggingsinfo.getDesignServeAgelimit().trim().length())){
				paraMap.put("designServeAgelimit", "%" + diggingsinfo.getDesignServeAgelimit().trim() + "%");
			}

			if ((null != diggingsinfo.getCheckNo()) && (0 < diggingsinfo.getCheckNo().trim().length())){
				paraMap.put("checkNo", "%" + diggingsinfo.getCheckNo().trim() + "%");
			}

			if ((null != diggingsinfo.getCheckUnit()) && (0 < diggingsinfo.getCheckUnit().trim().length())){
				paraMap.put("checkUnit", "%" + diggingsinfo.getCheckUnit().trim() + "%");
			}

			if ((null != diggingsinfo.getCertificateSum()) && (0 < diggingsinfo.getCertificateSum().trim().length())){
				paraMap.put("certificateSum", "%" + diggingsinfo.getCertificateSum().trim() + "%");
			}

			if ((null != diggingsinfo.getEngineerSum()) && (0 < diggingsinfo.getEngineerSum().trim().length())){
				paraMap.put("engineerSum", "%" + diggingsinfo.getEngineerSum().trim() + "%");
			}

			if (null != queryExploitCertificateStartdateStart){
				paraMap.put("startExploitCertificateStartdate", queryExploitCertificateStartdateStart);
			}

			if (null != queryExploitCertificateStartdateEnd){
				paraMap.put("endExploitCertificateStartdate", queryExploitCertificateStartdateEnd);
			}
			if (null != queryExploitCertificateEnddateStart){
				paraMap.put("startExploitCertificateEnddate", queryExploitCertificateEnddateStart);
			}

			if (null != queryExploitCertificateEnddateEnd){
				paraMap.put("endExploitCertificateEnddate", queryExploitCertificateEnddateEnd);
			}
			if ((null != diggingsinfo.getExploitCertificateNo()) && (0 < diggingsinfo.getExploitCertificateNo().trim().length())){
				paraMap.put("exploitCertificateNo", "%" + diggingsinfo.getExploitCertificateNo().trim() + "%");
			}

			if ((null != diggingsinfo.getExploitCertificateUnit()) && (0 < diggingsinfo.getExploitCertificateUnit().trim().length())){
				paraMap.put("exploitCertificateUnit", "%" + diggingsinfo.getExploitCertificateUnit().trim() + "%");
			}

			if (null != querySafeCertificateStartdateStart){
				paraMap.put("startSafeCertificateStartdate", querySafeCertificateStartdateStart);
			}

			if (null != querySafeCertificateStartdateEnd){
				paraMap.put("endSafeCertificateStartdate", querySafeCertificateStartdateEnd);
			}
			if (null != querySafeCertificateEnddateStart){
				paraMap.put("startSafeCertificateEnddate", querySafeCertificateEnddateStart);
			}

			if (null != querySafeCertificateEnddateEnd){
				paraMap.put("endSafeCertificateEnddate", querySafeCertificateEnddateEnd);
			}
			if ((null != diggingsinfo.getSafeCertificateNo()) && (0 < diggingsinfo.getSafeCertificateNo().trim().length())){
				paraMap.put("safeCertificateNo", "%" + diggingsinfo.getSafeCertificateNo().trim() + "%");
			}

		}
		
		pagination = diggingsinfoService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	//状态
	public void findState(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String str= "{\"state\":\"4\"}";
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		if(ids.equals("402881ee4a586836014a586a518c0003")){
			System.out.println("--------------");
			
		}
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
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != diggingsinfo)&&(null != diggingsinfo.getId()))
			diggingsinfo = diggingsinfoService.getById(diggingsinfo.getId());
		
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
		if(diggingsinfo.getIfzsqy() != null && diggingsinfo.getIfzsqy().equals("1")){
			ifzsqy = "1";
		}else{
			ifzsqy = "0";
		}
		
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String dutyFlag = "";

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		paraMap.put("itemId", diggingsinfo.getId());
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

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			//Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			diggingsinfo.setDeptId(this.getLoginUserDepartmentId());
			diggingsinfo.setDelFlag(0);
			diggingsinfo.setState("0");
			diggingsinfo.setDutyFlag("0");
			diggingsinfo.setIfzsqy(company.getIfzsqy());
			diggingsinfo.setZsqytype(company.getZsqytype());
			diggingsinfoService.save(diggingsinfo);
			//hanxc 20141211 生成待审核任务 start
			ReviewLog newReviewLog = new ReviewLog();
			if(null != diggingsinfo.getIfzsqy() && diggingsinfo.getIfzsqy().equals("1")){
				newReviewLog.setDutyFlag("1");//市级部门审核任务
			}else{
				newReviewLog.setDutyFlag("2");//县级部门审核任务
			}
			newReviewLog.setState("0");
			newReviewLog.setItemId(diggingsinfo.getId());
			newReviewLog.setItemType("1");//企业信息类型：type=1 
			newReviewLog.setStartTime(new Date());
			reviewLogService.saveNewTask(newReviewLog);
		}else{
			diggingsinfoService.update(diggingsinfo);
		}
		return RELOAD;
	}
	
	/**
	 * 保存审核信息
	 */
	public String shenhesave() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(null != diggingsinfo && null != diggingsinfo.getId()){
			String state = diggingsinfo.getState();
			String shbz = diggingsinfo.getShbs();
			String a = "";
			Diggingsinfo digging = diggingsinfoService.getById(diggingsinfo.getId());
			digging.setState(state);
			digging.setShbs(shbz);
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
			digging.setDutyFlag(dutyFlag);
			
			Pagination page = new Pagination(this.getRequest());
			Map<String, Object> tempParaMap = new HashMap<String, Object>();
			tempParaMap.put("itemId", digging.getId());
			tempParaMap.put("dutyFlag", dutyFlag);
			page = reviewLogService.findByPage(page, tempParaMap);
			List rlList = page.getList();
			if(!rlList.isEmpty()){ 
				ReviewLog reviewLog = (ReviewLog)rlList.get(0);
				reviewLog.setItemType("1");
				reviewLog.setState(digging.getState());
				reviewLog.setUserId(this.getLoginUserId());
				reviewLog.setUserName(this.getLoginUser().getDisplayName());
				reviewLog.setUserDeptCode(deptCode);
				reviewLog.setEndTime(new Date());
				reviewLog.setRecord("");
				reviewLog.setRemark(digging.getShbs());
				reviewLogService.saveReviewLogAndSetNextTask(reviewLog);
			}
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 end
			
			diggingsinfoService.update(digging);
		}
		
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			diggingsinfoService.deleteWithFlag(ids);
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

	public Diggingsinfo getDiggingsinfo(){
		return this.diggingsinfo;
	}

	public void setDiggingsinfo(Diggingsinfo diggingsinfo){
		this.diggingsinfo = diggingsinfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryProduceStartdateStart(){
		return this.queryProduceStartdateStart;
	}

	public void setQueryProduceStartdateStart(Date queryProduceStartdateStart){
		this.queryProduceStartdateStart = queryProduceStartdateStart;
	}

	public Date getQueryProduceStartdateEnd(){
		return this.queryProduceStartdateEnd;
	}

	public void setQueryProduceStartdateEnd(Date queryProduceStartdateEnd){
		this.queryProduceStartdateEnd = queryProduceStartdateEnd;
	}

	public Date getQueryExploitCertificateStartdateStart(){
		return this.queryExploitCertificateStartdateStart;
	}

	public void setQueryExploitCertificateStartdateStart(Date queryExploitCertificateStartdateStart){
		this.queryExploitCertificateStartdateStart = queryExploitCertificateStartdateStart;
	}

	public Date getQueryExploitCertificateStartdateEnd(){
		return this.queryExploitCertificateStartdateEnd;
	}

	public void setQueryExploitCertificateStartdateEnd(Date queryExploitCertificateStartdateEnd){
		this.queryExploitCertificateStartdateEnd = queryExploitCertificateStartdateEnd;
	}

	public Date getQueryExploitCertificateEnddateStart(){
		return this.queryExploitCertificateEnddateStart;
	}

	public void setQueryExploitCertificateEnddateStart(Date queryExploitCertificateEnddateStart){
		this.queryExploitCertificateEnddateStart = queryExploitCertificateEnddateStart;
	}

	public Date getQueryExploitCertificateEnddateEnd(){
		return this.queryExploitCertificateEnddateEnd;
	}

	public void setQueryExploitCertificateEnddateEnd(Date queryExploitCertificateEnddateEnd){
		this.queryExploitCertificateEnddateEnd = queryExploitCertificateEnddateEnd;
	}

	public Date getQuerySafeCertificateStartdateStart(){
		return this.querySafeCertificateStartdateStart;
	}

	public void setQuerySafeCertificateStartdateStart(Date querySafeCertificateStartdateStart){
		this.querySafeCertificateStartdateStart = querySafeCertificateStartdateStart;
	}

	public Date getQuerySafeCertificateStartdateEnd(){
		return this.querySafeCertificateStartdateEnd;
	}

	public void setQuerySafeCertificateStartdateEnd(Date querySafeCertificateStartdateEnd){
		this.querySafeCertificateStartdateEnd = querySafeCertificateStartdateEnd;
	}

	public Date getQuerySafeCertificateEnddateStart(){
		return this.querySafeCertificateEnddateStart;
	}

	public void setQuerySafeCertificateEnddateStart(Date querySafeCertificateEnddateStart){
		this.querySafeCertificateEnddateStart = querySafeCertificateEnddateStart;
	}

	public Date getQuerySafeCertificateEnddateEnd(){
		return this.querySafeCertificateEnddateEnd;
	}

	public void setQuerySafeCertificateEnddateEnd(Date querySafeCertificateEnddateEnd){
		this.querySafeCertificateEnddateEnd = querySafeCertificateEnddateEnd;
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
