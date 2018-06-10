package com.jshx.qyfjfl.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.companyScore.entity.CheckItem;
import com.jshx.companyScore.entity.CompanyScore;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyfjfl.entity.EnterpriseGrade;
import com.jshx.qyfjfl.service.EnterpriseGradeService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class EnterpriseGradeAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private EnterpriseGrade enterpriseGrade = new EnterpriseGrade();

	/**
	 * 业务类
	 */
	@Autowired
	private EnterpriseGradeService enterpriseGradeService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private CodeService codeService;
	

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String areaId;
	
	private String companyName;
	
	private String roleName;
	
	private Date queryStartTimeStart;

	private Date queryStartTimeEnd;
	
	private Date queryEndTimeStart;

	private Date queryEndTimeEnd;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	public String init(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				setRoleName("0");
				break;
			}
			else if(ur.getRole().getRoleCode().equals("A24")) 
			{
				setRoleName("1");
				break;
			}
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();

			if(pagination==null)
			    pagination = new Pagination(this.getRequest());
			    
			if(null != enterpriseGrade){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != enterpriseGrade.getAreaId()) && (0 < enterpriseGrade.getAreaId().trim().length())){
					paraMap.put("areaId",  enterpriseGrade.getAreaId().trim() );
				}
				if ((null != enterpriseGrade.getCompanyName()) && (0 < enterpriseGrade.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + enterpriseGrade.getCompanyName().trim() + "%");
				}
				if ((null != enterpriseGrade.getCompanyId()) && (0 < enterpriseGrade.getCompanyId().trim().length())){
					paraMap.put("companyId",   enterpriseGrade.getCompanyId().trim() );
				}
			}
			if(null != queryStartTimeStart)
			{
				paraMap.put("startStartTime",  queryStartTimeStart);
			}

			if(null != queryStartTimeEnd)
			{
				paraMap.put("endStartTime",  queryStartTimeEnd);
			}

			if(null != queryEndTimeStart)
			{
				paraMap.put("startEndTime",  queryEndTimeStart);
			}

			if(null != queryEndTimeEnd)
			{
				paraMap.put("endEndTime",  queryEndTimeEnd);
			}

			
			String userId=this.getLoginUser().getId();
			if(httpService.judgeRoleCode(userId, "A23")){
				Map map = new HashMap();
				map.put("loginId", this.getLoginUser().getLoginId());
				EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
				paraMap.put("companyId", e.getId());
			}
			else
			{
				paraMap.put("state", "0");
			}
			if(httpService.judgeRoleCode(userId, "A24")){//网格管理人员
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
				paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			
			config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
			final String filter = "id|areaName|startTime|endTime|companyName|state|zpzf|ajzf|";
			if (filter != null && filter.length() > 1) {
				config.setJsonPropertyFilter(new PropertyFilter() {
					public boolean apply(Object source, String name, Object value) {
						if (filter.indexOf(name + "|") != -1)
							return false;
						else
							return true;
					}
				});
			}
			pagination = enterpriseGradeService.findByPage(pagination, paraMap);
			
			convObjectToJson(pagination, config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		String root = this.getRequest().getRealPath("/"); 
		root = root.replaceAll("\\\\", "/");
		String fileName = "";
		if("add".equals(flag)){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
			enterpriseGrade.setCompanyId(e.getId());
			enterpriseGrade.setCompanyName(e.getEnterpriseName());
			enterpriseGrade.setAreaId(e.getEnterprisePossession());
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", e.getEnterprisePossession());
			enterpriseGrade.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		}else{
			enterpriseGrade = enterpriseGradeService.getById(enterpriseGrade.getId());
		}
		return VIEW;
	}

	public String ArrayToStr(String[] s,int i)
	{
		String a = "";
		if(i < s.length)
		{
			if(s[i] != null && !"".equals(s[i]))
			{
				a = s[i];
			}
		}
		return a;
	}
	
	public String ArrayToStr1(String[] s,int i)
	{
		String a = "1";
		if(i < s.length)
		{
			if(s[i] != null && !"".equals(s[i]))
			{
				a = s[i];
			}
		}
		return a;
	}
	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		if("add".equals(flag)){
			//计算系统评分
			calSystemScore();
		}
		else
		{
			enterpriseGrade.setAjScore(enterpriseGrade.getCheckScore());
		}
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			return "qyEdit";
		}else{
			return "ajEdit";
		}
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		try {
			if ("add".equalsIgnoreCase(this.flag)){
				enterpriseGrade.setDeptId(this.getLoginUserDepartmentId());
				enterpriseGrade.setDelFlag(0);
				String[] checkScores=enterpriseGrade.getCheckScore().replaceAll(" ", "").split(",");
				String[] qyremarks = enterpriseGrade.getQyremark().replaceAll(" ", "").split(",");
				double df = 0;
				String checkScore = "";
				String qyremark = "";
				for(int i=0;i<40;i++)
				{
					String pf = ArrayToNum(checkScores,i);
					checkScore += pf +",";
					df += Double.valueOf(pf);
					qyremark += ArrayToStr(qyremarks,i) +",";
				}
				checkScore = checkScore.substring(0,checkScore.length()-1);
				qyremark = qyremark.substring(0,qyremark.length()-1);
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				String strFloat = myFormat.format(df);
				enterpriseGrade.setZpzf(strFloat);
				enterpriseGrade.setQyremark(qyremark);
				enterpriseGrade.setCheckScore(checkScore);
				enterpriseGradeService.save(enterpriseGrade);
			}else if ("mod".equalsIgnoreCase(this.flag)){
				String[] checkScores=enterpriseGrade.getCheckScore().replaceAll(" ", "").split(",");
				String[] qyremarks = enterpriseGrade.getQyremark().replaceAll(" ", "").split(",");
				double df = 0;
				String checkScore = "";
				String qyremark = "";
				for(int i=0;i<40;i++)
				{
					String pf = ArrayToNum(checkScores,i);
					checkScore += pf +",";
					df += Double.valueOf(pf);
					qyremark += ArrayToStr(qyremarks,i) +",";
				}
				checkScore = checkScore.substring(0,checkScore.length()-1);
				qyremark = qyremark.substring(0,qyremark.length()-1);
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				String strFloat = myFormat.format(df);
				enterpriseGrade.setZpzf(strFloat);
				enterpriseGrade.setQyremark(qyremark);
				enterpriseGrade.setCheckScore(checkScore);
				enterpriseGradeService.update(enterpriseGrade);
			}
			else{
				String[] checkScores=enterpriseGrade.getAjScore().replaceAll(" ", "").split(",");
				String[] qyremarks = enterpriseGrade.getRemarks().replaceAll(" ", "").split(",");
				double df = 0;
				String checkScore = "";
				String qyremark = "";
				for(int i=0;i<40;i++)
				{
					String pf = ArrayToNum(checkScores,i);
					checkScore += pf +",";
					df += Double.valueOf(pf);
					qyremark += ArrayToStr(qyremarks,i) +",";
				}
				checkScore = checkScore.substring(0,checkScore.length()-1);
				qyremark = qyremark.substring(0,qyremark.length()-1);
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				String strFloat = myFormat.format(df);
				enterpriseGrade.setAjzf(strFloat);
				enterpriseGrade.setAjScore(checkScore);
				enterpriseGrade.setRemarks(qyremark);
				enterpriseGrade.setState("2");
				enterpriseGradeService.update(enterpriseGrade);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}
	
	public String ArrayToNum(String[] s,int i)
	{
		String a = "0";
		if(i < s.length)
		{
			if(s[i] != null && !"".equals(s[i]))
			{
				a = s[i];
			}
		}
		return a;
	}
	
	public void calSystemScore(){
		int[] sysScore=new int[40];
		
		String s="";
		for(int i:sysScore){
			s+="0,";
		}
		if(s.endsWith(",")){
			s=s.substring(0, s.length()-1);
		}
		enterpriseGrade.setSystemScore(s);
		enterpriseGrade.setCheckScore(s);
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			enterpriseGradeService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public String getScore() throws IOException
	{
		String[] checkScores = null;
		double df = 0;
		String strFloat = "";
		try{
			if ("add".equalsIgnoreCase(this.flag) || "mod".equalsIgnoreCase(this.flag)){
				checkScores=enterpriseGrade.getCheckScore().replaceAll(" ", "").split(",");
				
			}else{
				checkScores=enterpriseGrade.getAjScore().replaceAll(" ", "").split(",");
			}	
			for(int i=0;i<40;i++)
			{
				String pf = ArrayToNum(checkScores,i);
				df += Double.valueOf(pf);
			}
			DecimalFormat myFormat = new DecimalFormat("0.0"); 
			strFloat = myFormat.format(df);
			this.getResponse().getWriter().println("{\"result\":true,\"score\":" + strFloat + "}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String zwtInit(){
		
//		String url="http://172.25.127.9/services/authorization/validateredirect";
//    	Map<String,String> dataMap = new HashMap<String,String>();
//        dataMap.put("token", "2dbe362a536d4311931a7ea22b4b2095");
//        dataMap.put("appId", "1DBE552B23D440128A12BA5D6ECE72B2");
//        JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//        if("200".equals(j.get("code").toString())){
//        	String loginName=j.get("username").toString();
//        	User u=userService.findUserByLoginId(loginName);
//			setSessionAttribute("curr_user", u);
//        	ids=u.getId();
//        	String deptCode =u.getDeptCode();
//    		if(httpService.judgeRoleCode(ids, "A17")){
//    			userType="1";
//    		}else if(httpService.judgeRoleCode(ids, "A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
//    			userType= "2";
//    		}else if(httpService.judgeRoleCode(ids, "A11")){
//    			userType= "3";
//    		}else if(httpService.judgeRoleCode(ids, "A09")){
//    			userType= "4";
//    		}else if(httpService.judgeRoleCode(ids, "A02")){
//    			userType= "5";
//    		}else if(deptCode.startsWith("002")&&deptCode.length()==6&&!"002001".equals(deptCode)){
//    			userType= "6";
//    		}else{
//    			userType= "7";
//    		}
//        	return SUCCESS;
//        }else{
//        	return ERROR;
//        }
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			setRoleName("0");
		}else if(httpService.judgeRoleCode(userId, "A24")){
			setRoleName("1");
		}else{
		}
		return SUCCESS;
	}


	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != enterpriseGrade){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != enterpriseGrade.getAreaId()) && (0 < enterpriseGrade.getAreaId().trim().length())){
				paraMap.put("areaId",  enterpriseGrade.getAreaId().trim() );
			}
			if ((null != enterpriseGrade.getCompanyName()) && (0 < enterpriseGrade.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + enterpriseGrade.getCompanyName().trim() + "%");
			}
			if ((null != enterpriseGrade.getCompanyId()) && (0 < enterpriseGrade.getCompanyId().trim().length())){
				paraMap.put("companyId",   enterpriseGrade.getCompanyId().trim() );
			}
		}
		if(null != queryStartTimeStart)
		{
			paraMap.put("startStartTime",  queryStartTimeStart);
		}

		if(null != queryStartTimeEnd)
		{
			paraMap.put("endStartTime",  queryStartTimeEnd);
		}

		if(null != queryEndTimeStart)
		{
			paraMap.put("startEndTime",  queryEndTimeStart);
		}

		if(null != queryEndTimeEnd)
		{
			paraMap.put("endEndTime",  queryEndTimeEnd);
		}

		
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", e.getId());
		}else{
			paraMap.put("state", "0");
		}
		if(httpService.judgeRoleCode(userId, "A24")){//网格管理人员
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
			paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			for(int i=0;i< ents.size();i++){
				companmyIds+=ents.get(i).get("row_id")+",";
			}
			if("".equals(companmyIds)){
				companmyIds="0";
			}
			paraMap.put("companmyIds", companmyIds);
		}
			
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
			
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|startTime|endTime|companyName|state|zpzf|ajzf|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = enterpriseGradeService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public String chooseCompanyAndYear(){
		
		return SUCCESS;
	}
	
	
	public String fjflpf(){
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				setRoleName("0");
				break;
			}
			else if(ur.getRole().getRoleCode().equals("A24")) 
			{
				setRoleName("1");
				break;
			}
		}
		return SUCCESS;
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

	public EnterpriseGrade getEnterpriseGrade(){
		return this.enterpriseGrade;
	}

	public void setEnterpriseGrade(EnterpriseGrade enterpriseGrade){
		this.enterpriseGrade = enterpriseGrade;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
    public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String scoreTrim(String score){
		String[] arr=score.split(",");
		String result="";
		for(String s:arr){
			if(!" ".endsWith(s)){
				s=s.trim();
			}
			if("".equals(s)){
				s=" ";
			}
			result+=s+",";
		}
		if(result.endsWith(",")){
			result=result.substring(0, result.length()-1);
		}
		return result;
	}

	public static void main(String[] args){
    	Object[] sysScore=new Object[]{1,2,3};
    	String str1=StringUtils.join(sysScore, ",");
    	org.apache.commons.lang.StringUtils.join(sysScore, ',');
    	System.out.println(str1);
    }
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getQueryStartTimeStart() {
		return queryStartTimeStart;
	}
	public void setQueryStartTimeStart(Date queryStartTimeStart) {
		this.queryStartTimeStart = queryStartTimeStart;
	}
	public Date getQueryStartTimeEnd() {
		return queryStartTimeEnd;
	}
	public void setQueryStartTimeEnd(Date queryStartTimeEnd) {
		this.queryStartTimeEnd = queryStartTimeEnd;
	}
	public Date getQueryEndTimeStart() {
		return queryEndTimeStart;
	}
	public void setQueryEndTimeStart(Date queryEndTimeStart) {
		this.queryEndTimeStart = queryEndTimeStart;
	}
	public Date getQueryEndTimeEnd() {
		return queryEndTimeEnd;
	}
	public void setQueryEndTimeEnd(Date queryEndTimeEnd) {
		this.queryEndTimeEnd = queryEndTimeEnd;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchLike() {
		return searchLike;
	}
	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}
    
}
