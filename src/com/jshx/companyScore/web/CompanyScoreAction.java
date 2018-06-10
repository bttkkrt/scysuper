package com.jshx.companyScore.web;

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
import com.jshx.companyScore.service.CompanyScoreService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

public class CompanyScoreAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CompanyScore companyScore = new CompanyScore();

	/**
	 * 业务类
	 */
	@Autowired
	private CompanyScoreService companyScoreService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private CodeService codeService;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;

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
	
	private List<CheckItem> list = new ArrayList<CheckItem>();
	
	private String roleName;
	
	private Date queryStartTimeStart;

	private Date queryStartTimeEnd;
	
	private Date queryEndTimeStart;

	private Date queryEndTimeEnd;
	
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
			    
			if(null != companyScore){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != companyScore.getAreaId()) && (0 < companyScore.getAreaId().trim().length())){
					paraMap.put("areaId",  companyScore.getAreaId().trim() );
				}
				if ((null != companyScore.getCompanyName()) && (0 < companyScore.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + companyScore.getCompanyName().trim() + "%");
				}
				if ((null != companyScore.getCompanyId()) && (0 < companyScore.getCompanyId().trim().length())){
					paraMap.put("companyId",   companyScore.getCompanyId().trim() );
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
			pagination = companyScoreService.findByPage(pagination, paraMap);
			
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
		companyScore = companyScoreService.getById(companyScore.getId());
		if(companyScore.getType().equals("wh"))//危化企业
		{
			fileName = "whpf.xls";
		}
		else if(companyScore.getType().equals("wx") || companyScore.getType().equals("xx"))//微型企业
		{
			fileName = "xwpf.xls";
		}
		else
		{
			fileName = "gmpf.xls";
		}
		InputStream is= new FileInputStream(root + fileName);
		Workbook wb = Workbook.getWorkbook(is);
		Sheet sheet = wb.getSheet(0);
		int row = sheet.getRows();
		String[] qypf = companyScore.getCheckScore().split(",");
		String[] qybz = new String[]{};
		String[] ajpf = new String[]{};
		String[] ajbz = new String[]{};
		String[] sfcp = new String[]{};
		if(companyScore.getIfcp() != null && !"".equals(companyScore.getIfcp()))
		{
			sfcp = companyScore.getIfcp().replaceAll(" ", "").split(",");
		}
		if(companyScore.getQyremark() != null && !"".equals(companyScore.getQyremark()))
		{
			qybz = companyScore.getQyremark().replaceAll(" ", "").split(",");
		}
		if(companyScore.getAjScore() != null && !"".equals(companyScore.getAjScore()))
		{
			ajpf = companyScore.getAjScore().replaceAll(" ", "").split(",");
		}
		if(companyScore.getRemarks() != null && !"".equals(companyScore.getRemarks()))
		{
			ajbz = companyScore.getRemarks().replaceAll(" ", "").split(",");
		}
		
		for (int i = 1; i < row; i++) {
			Cell[] cells = sheet.getRow(i);
			CheckItem check = new CheckItem();
			check.setDbbz(cells[0].getContents().trim());
			check.setBzfz(cells[1].getContents().trim());
			check.setPffs(cells[2].getContents().trim());
			check.setQypf(ArrayToStr(qypf,i-1));
			check.setQybz(ArrayToStr(qybz,i-1));
			check.setAjpf(ArrayToStr(ajpf,i-1));
			check.setAjbz(ArrayToStr(ajbz,i-1));
			check.setSfcp(ArrayToStr1(sfcp,i-1));
			list.add(check);
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
		String root = this.getRequest().getRealPath("/"); 
		root = root.replaceAll("\\\\", "/");
		String fileName = "";
		if("add".equals(flag)){
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo e = entBaseInfoService.findEntBaseInfoByMap(map);
			if(e.getEnterpriseScale().equals("01"))//微型企业
			{
				companyScore.setType("wx");
			}
			else if(e.getEnterpriseScale().equals("02"))//小型企业
			{
				companyScore.setType("xx");
			}
			else if(e.getEnterpriseType().contains("2"))//危化企业
			{
				companyScore.setType("wh");
			}
			else
			{
				companyScore.setType("gm");
			}
			companyScore.setCompanyId(e.getId());
			companyScore.setCompanyName(e.getEnterpriseName());
			companyScore.setAreaId(e.getEnterprisePossession());
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", e.getEnterprisePossession());
			companyScore.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			//计算系统评分
			calSystemScore(companyScore.getType());
		}else{
			companyScore = companyScoreService.getById(companyScore.getId());
			companyScore.setAjScore(companyScore.getCheckScore());
		}
		if(companyScore.getType().equals("wh"))//危化企业
		{
			fileName = "whpf.xls";
		}
		else if(companyScore.getType().equals("wx") || companyScore.getType().equals("xx"))//微型企业
		{
			fileName = "xwpf.xls";
		}
		else
		{
			fileName = "gmpf.xls";
		}
		InputStream is= new FileInputStream(root + fileName);
		Workbook wb = Workbook.getWorkbook(is);
		Sheet sheet = wb.getSheet(0);
		int row = sheet.getRows();
		String[] qypf = companyScore.getCheckScore().split(",");
		String[] qybz = new String[]{};
		String[] ajpf = new String[]{};
		String[] ajbz = new String[]{};
		String[] sfcp = new String[]{};
		if(companyScore.getIfcp() != null && !"".equals(companyScore.getIfcp()))
		{
			sfcp = companyScore.getIfcp().replaceAll(" ", "").split(",");
		}
		if(companyScore.getQyremark() != null && !"".equals(companyScore.getQyremark()))
		{
			qybz = companyScore.getQyremark().replaceAll(" ", "").split(",");
		}
		if(companyScore.getAjScore() != null && !"".equals(companyScore.getAjScore()))
		{
			ajpf = companyScore.getAjScore().replaceAll(" ", "").split(",");
		}
		if(companyScore.getRemarks() != null && !"".equals(companyScore.getRemarks()))
		{
			ajbz = companyScore.getRemarks().replaceAll(" ", "").split(",");
		}
		
		for (int i = 1; i < row; i++) {
			Cell[] cells = sheet.getRow(i);
			CheckItem check = new CheckItem();
			check.setDbbz(cells[0].getContents().trim());
			check.setBzfz(cells[1].getContents().trim());
			check.setPffs(cells[2].getContents().trim());
			check.setQypf(ArrayToStr(qypf,i-1));
			check.setQybz(ArrayToStr(qybz,i-1));
			check.setAjpf(ArrayToStr(ajpf,i-1));
			check.setAjbz(ArrayToStr(ajbz,i-1));
			check.setSfcp(ArrayToStr1(sfcp,i-1));
			list.add(check);
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
		if ("add".equalsIgnoreCase(this.flag)){
			companyScore.setDeptId(this.getLoginUserDepartmentId());
			companyScore.setDelFlag(0);
			String[] checkScores=companyScore.getCheckScore().replaceAll(" ", "").split(",");
			String[] ifcps = companyScore.getIfcp().replaceAll(" ", "").split(",");
			String[] bzfzs = companyScore.getBzScore().replaceAll(" ", "").split(",");
			String[] qyremarks = companyScore.getQyremark().replaceAll(" ", "").split(",");
			int cpzf = 0;
			int df = 0;
			String checkScore = "";
			String qyremark = "";
			String ifcp = "";
			for(int i=0;i<ifcps.length;i++)
			{
				if(ifcps[i] != null && "1".equals(ifcps[i]))
				{
					String pf = ArrayToNum(checkScores,i);
					checkScore += pf +",";
					df += Integer.valueOf(pf);
					cpzf += Integer.valueOf(bzfzs[i]);
					qyremark += ArrayToStr(qyremarks,i) +",";
					ifcp += "1,";
				}
				else
				{
					checkScore += "0" +",";
					qyremark += " " +",";
					ifcp += "0,";
				}
			}
			checkScore = checkScore.substring(0,checkScore.length()-1);
			qyremark = qyremark.substring(0,qyremark.length()-1);
			ifcp = ifcp.substring(0,ifcp.length()-1);
			String strFloat = "0";
			if(cpzf != 0)
			{
				double qypf = df*100/cpzf;
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				strFloat = myFormat.format(qypf);
			}
			companyScore.setZpzf(strFloat);
			companyScore.setQyremark(qyremark);
			companyScore.setCheckScore(checkScore);
			companyScoreService.save(companyScore);
		}else if ("mod".equalsIgnoreCase(this.flag)){
			String[] checkScores=companyScore.getCheckScore().replaceAll(" ", "").split(",");
			String[] ifcps = companyScore.getIfcp().replaceAll(" ", "").split(",");
			String[] bzfzs = companyScore.getBzScore().replaceAll(" ", "").split(",");
			String[] qyremarks = companyScore.getQyremark().replaceAll(" ", "").split(",");
			int cpzf = 0;
			int df = 0;
			String checkScore = "";
			String qyremark = "";
			String ifcp = "";
			for(int i=0;i<ifcps.length;i++)
			{
				if(ifcps[i] != null && "1".equals(ifcps[i]))
				{
					String pf = ArrayToNum(checkScores,i);
					checkScore += pf +",";
					df += Integer.valueOf(pf);
					cpzf += Integer.valueOf(bzfzs[i]);
					qyremark += ArrayToStr(qyremarks,i) +",";
					ifcp += "1,";
				}
				else
				{
					checkScore += "0" +",";
					qyremark += " " +",";
					ifcp += "0,";
				}
			}
			checkScore = checkScore.substring(0,checkScore.length()-1);
			qyremark = qyremark.substring(0,qyremark.length()-1);
			ifcp = ifcp.substring(0,ifcp.length()-1);
			String strFloat = "0";
			if(cpzf != 0)
			{
				double qypf = df*100/cpzf;
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				strFloat = myFormat.format(qypf);
			}
			companyScore.setIfcp(ifcp);
			companyScore.setZpzf(strFloat);
			companyScore.setQyremark(qyremark);
			companyScore.setCheckScore(checkScore);
			companyScoreService.update(companyScore);
		}else{
			String[] checkScores=companyScore.getAjScore().replaceAll(" ", "").split(",");
			String[] ifcps = companyScore.getIfcp().replaceAll(" ", "").split(",");
			String[] bzfzs = companyScore.getBzScore().replaceAll(" ", "").split(",");
			String[] qyremarks = companyScore.getRemarks().replaceAll(" ", "").split(",");
			int cpzf = 0;
			int df = 0;
			String checkScore = "";
			String qyremark = "";
			for(int i=0;i<ifcps.length;i++)
			{
				if(ifcps[i] != null && "1".equals(ifcps[i]))
				{
					String pf = ArrayToNum(checkScores,i);
					checkScore += pf +",";
					df += Integer.valueOf(pf);
					cpzf += Integer.valueOf(bzfzs[i]);
					qyremark += ArrayToStr(qyremarks,i) +",";
				}
				else
				{
					checkScore += "0" +",";
					qyremark += " " +",";
				}
			}
			checkScore = checkScore.substring(0,checkScore.length()-1);
			qyremark = qyremark.substring(0,qyremark.length()-1);
			String strFloat = "0";
			if(cpzf != 0)
			{
				double qypf = df*100/cpzf;
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				strFloat = myFormat.format(qypf);
			}
			companyScore.setAjzf(strFloat);
			companyScore.setAjScore(checkScore);
			companyScore.setRemarks(qyremark);
			companyScore.setState("2");
			companyScoreService.update(companyScore);
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

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			companyScoreService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public void calSystemScore(String type){
		Date startTime = companyScore.getStartTime();
		Date endTime = companyScore.getEndTime();
		String companyId=companyScore.getCompanyId();
		Map map = new HashMap();
		map.put("companyId", companyId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		int[] sysScore=new int[194];
		//是否具有年度安全生产目标
		map.put("sqlID", "queryScore2ByMap");
		int score2 = httpService.getListCountbyMap(map);
		if(score2>0){
			if(type.equals("wh"))
			{
				sysScore[2]=20;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[0]=4;
			}
			else if(type.equals("gm"))
			{
				sysScore[1]=6;
			}
			
		}
		
		//是否设置安全管理机构
		map.put("sqlID", "queryScore7ByMap");
		int score7 = httpService.getListCountbyMap(map);
		if(score7>0){
			if(type.equals("wh"))
			{
				sysScore[5]=20;
			}
			else if(type.equals("gm"))
			{
				sysScore[6]=3;
			}
		}else{
			
			if(type.equals("gm"))
			{
				sysScore[6]=-6;
			}
		}
		
		//是否设置安全管理人员
		map.put("sqlID", "queryScore4ByMap");
		int score4 = httpService.getListCountbyMap(map);
		if(score4>0){
			if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[3]=6;
			}
		}
		
		//是否设置安全生产领导机构
		map.put("sqlID", "queryScore8ByMap");
		int score8 = httpService.getListCountbyMap(map);
		if(score8>0){
			if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[2]=6;
			}
			else if(type.equals("gm"))
			{
				sysScore[7]=2;
			}
			
		}
		
		//是否建立安全生产费用使用台账
		map.put("sqlID", "queryScore16ByMap");
		int score16 = httpService.getListCountbyMap(map);
		if(score16>0){
			if(type.equals("wh"))
			{
				sysScore[6]=10;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[5]=8;
			}
			else if(type.equals("gm"))
			{
				sysScore[15]=12;
			}
			
		}else{
			if(type.equals("gm"))
			{
				sysScore[15]=-8;
			}
			
		}
		
		//是否发布法律清单
		map.put("sqlID", "queryScore23ByMap");
		int score23 = httpService.getListCountbyMap(map);
		if(score23>0){
			if(type.equals("wh"))
			{
				sysScore[0]=50;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[7]=4;
			}
			else if(type.equals("gm"))
			{
				sysScore[22]=4;
			}
			
		}
		
		//是否发布安全生产规章制度
		map.put("sqlID", "queryScore27ByMap");
		int score27 = httpService.getListCountbyMap(map);
		if(score27>0){
			if(type.equals("wh"))
			{
				sysScore[15]=40;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[8]=12;
			}
			else if(type.equals("gm"))
			{
				sysScore[26]=8;
			}
			
		}
		
		
		//是否安全设施台账
		map.put("sqlID", "queryScore26ByMap");
		int score26 = httpService.getListCountbyMap(map);
		if(score26>0){
			if(type.equals("wh"))
			{
				sysScore[25]=20;
			}
		}
		
		//是否操作规程
		map.put("sqlID", "queryScore17ByMap");
		int score17 = httpService.getListCountbyMap(map);
		if(score17>0){
			if(type.equals("wh"))
			{
				sysScore[16]=40;
			}else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[9]=10;
			}
		}
		
		//是否生产现场管理
		map.put("sqlID", "queryScore32ByMap");
		int score32 = httpService.getListCountbyMap(map);
		if(score32>0){
			if(type.equals("wh"))
			{
				sysScore[31]=20;
			}else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[48]=20;
			}
		}
		
		//是否相关方管理
		map.put("sqlID", "queryScore123ByMap");
		int score123 = httpService.getListCountbyMap(map);
		if(score123>0){
			if(type.equals("wh"))
			{
				sysScore[14]=5;
				sysScore[34]=25;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[53]=12;
			}
			else if(type.equals("gm"))
			{
				sysScore[122]=6;
			}
			
		}
		
		//是否对隐患登记建档
		map.put("sqlID", "queryScore131ByMap");
		int score131 = httpService.getListCountbyMap(map);
		if(score131>0){
			if(type.equals("wh"))
			{
				sysScore[10]=20;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[55]=12;
			}
			else if(type.equals("gm"))
			{
				sysScore[130]=4;
			}
			
		}
		
		//是否建立危险源的管理制度
		map.put("sqlID", "queryScore141ByMap");
		map.put("systemType", "1");
		int score141 = httpService.getListCountbyMap(map);
		if(score141>0){
			if(type.equals("gm"))
			{
				sysScore[140]=4;
			}
			
		}
		
		//是否明确重点危险源
		map.put("sqlID", "queryScore142ByMap");
		int score142 = httpService.getListCountbyMap(map);
		if(score142>0){
			if(type.equals("wh"))
			{
				sysScore[11]=20;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[16]=6;
			}
			else if(type.equals("gm"))
			{
				sysScore[142]=6;
			}
			
		}
		
		//是否建立职业健康的管理制度
		map.put("sqlID", "queryScore149ByMap");
		int score149 = httpService.getListCountbyMap(map);
		if(score149>0){
			if(type.equals("gm"))
			{
				sysScore[148]=4;
			}
			
		}
		
		//是否职业健康体检
		map.put("sqlID", "queryScore152ByMap");
		int score152 = httpService.getListCountbyMap(map);
		if(score152>0){
			if(type.equals("wh"))
			{
				sysScore[36]=50;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[60]=8;
			}
			else if(type.equals("gm"))
			{
				sysScore[151]=3;
			}
			
		}
		
		//是否危害因素检测
		map.put("sqlID", "queryScore154ByMap");
		int score154 = httpService.getListCountbyMap(map);
		if(score154>0){
			if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[62]=10;
			}
			else if(type.equals("gm"))
			{
				sysScore[153]=2;
			}
			
		}
		
		//是否上报职业危害基本因素
		map.put("sqlID", "queryScore165ByMap");
		int score165 = httpService.getListCountbyMap(map);
		if(score165>0){
			if(type.equals("wh"))
			{
				sysScore[35]=25;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[59]=6;
			}
			else if(type.equals("gm"))
			{
				sysScore[164]=3;
			}
			
		}
		
		//是否应急机构
		map.put("sqlID", "queryScore168ByMap");
		int score168 = httpService.getListCountbyMap(map);
		if(score168>0){
			if(type.equals("wh"))
			{
				sysScore[45]=10;
			}
			else if(type.equals("gm"))
			{
				sysScore[167]=2;
				sysScore[168]=2;
			}
			
		}
		
		//是否应急预案
		map.put("sqlID", "queryScore171ByMap");
		int score171 = httpService.getListCountbyMap(map);
		if(score171>0){
			if(type.equals("wh"))
			{
				sysScore[47]=25;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[20]=6;
			}
			else if(type.equals("gm"))
			{
				sysScore[170]=2;
			}
		}
		else
		{
			sysScore[170]=-2;
		}
		
		//是否应急装备设施
		map.put("sqlID", "queryScore175ByMap");
		int score175 = httpService.getListCountbyMap(map);
		if(score175>0){
			if(type.equals("wh"))
			{
				sysScore[46]=15;
			}
			else if(type.equals("gm"))
			{
				sysScore[174]=2;
			}
		}
		
		//是否应急演练
		map.put("sqlID", "queryScore177ByMap");
		int score177 = httpService.getListCountbyMap(map);
		if(score177>0){
			if(type.equals("gm"))
			{
				sysScore[176]=4;
			}
		}
		else
		{
			if(type.equals("gm"))
			{
				sysScore[176]=-8;
			}
		}
		
		
		
		//是否对事故进行登记建档管理
		map.put("sqlID", "queryScore184ByMap");
		int score184 = httpService.getListCountbyMap(map);
		if(score184>0){
			if(type.equals("wh"))
			{
				sysScore[50]=15;
			}
			else if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[18]=4;
			}
			else if(type.equals("gm"))
			{
				sysScore[183]=2;
			}
			
		}
		
		//是否危化品登记
		map.put("sqlID", "queryScore39ByMap");
		int score39 = httpService.getListCountbyMap(map);
		if(score39>0){
			if(type.equals("wh"))
			{
				sysScore[38]=10;
				sysScore[42]=40;
			}
		}
		
		//是否巡查计划
		map.put("sqlID", "queryScore52ByMap");
		int score52 = httpService.getListCountbyMap(map);
		if(score52>0){
			if(type.equals("wh"))
			{
				sysScore[51]=25;
			}
		}
		
		//是否巡查任务
		map.put("sqlID", "queryScore53ByMap");
		int score53 = httpService.getListCountbyMap(map);
		if(score53>0){
			if(type.equals("wh"))
			{
				sysScore[52]=25;
			}
		}
		
		//是否生产设施
		map.put("sqlID", "queryScore43ByMap");
		int score43= httpService.getListCountbyMap(map);
		if(score43>0){
			if(type.equals("xx") || type.equals("wx"))
			{
				sysScore[42]=16;
			}
		
		}
		
		String s="";
		for(int i:sysScore){
			s+=i+",";
		}
		if(s.endsWith(",")){
			s=s.substring(0, s.length()-1);
		}
		companyScore.setSystemScore(s);
		companyScore.setCheckScore(s);
	}
	
	public String getScore() throws IOException
	{
		String[] ifcps = companyScore.getIfcp().replaceAll(" ", "").split(",");
		String[] bzfzs = companyScore.getBzScore().replaceAll(" ", "").split(",");
		String[] checkScores = null;
		int cpzf = 0;
		int df = 0;
		String strFloat = "0";
		try{
			if ("add".equalsIgnoreCase(this.flag) || "mod".equalsIgnoreCase(this.flag)){
				checkScores=companyScore.getCheckScore().replaceAll(" ", "").split(",");
				
			}else{
				checkScores=companyScore.getAjScore().replaceAll(" ", "").split(",");
			}	
			for(int i=0;i<ifcps.length;i++)
			{
				if(ifcps[i] != null && "1".equals(ifcps[i]))
				{
					String pf = ArrayToNum(checkScores,i);
					df += Integer.valueOf(pf);
					cpzf += Integer.valueOf(bzfzs[i]);
				}
			}
			if(cpzf != 0)
			{
				double qypf = df*100/cpzf;
				DecimalFormat myFormat = new DecimalFormat("0.0"); 
				strFloat = myFormat.format(qypf);
			}
			this.getResponse().getWriter().println("{\"result\":true,\"score\":" + strFloat + "}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String bzhpf(){
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
	
	public String chooseCompanyAndYear(){
		
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

	public CompanyScore getCompanyScore(){
		return this.companyScore;
	}

	public void setCompanyScore(CompanyScore companyScore){
		this.companyScore = companyScore;
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
		User user=userService.findUserByLoginId("wgglry1");
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
		    
		if(null != companyScore){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != companyScore.getAreaId()) && (0 < companyScore.getAreaId().trim().length())){
				paraMap.put("areaId",  companyScore.getAreaId().trim() );
			}
			if ((null != companyScore.getCompanyName()) && (0 < companyScore.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + companyScore.getCompanyName().trim() + "%");
			}
			if ((null != companyScore.getCompanyId()) && (0 < companyScore.getCompanyId().trim().length())){
				paraMap.put("companyId",   companyScore.getCompanyId().trim() );
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
		pagination = companyScoreService.findByPage(pagination, paraMap);
		
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

	public static void main(String[] args){
    	Object[] sysScore=new Object[]{1,2,3};
    	String str1=StringUtils.join(sysScore, ",");
    	org.apache.commons.lang.StringUtils.join(sysScore, ',');
    	System.out.println(str1);
    }
	public List<CheckItem> getList() {
		return list;
	}
	public void setList(List<CheckItem> list) {
		this.list = list;
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
