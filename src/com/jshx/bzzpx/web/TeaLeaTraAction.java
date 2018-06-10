package com.jshx.bzzpx.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qytzrypx.entity.SpeJobTra;
import com.jshx.sccjgl.entity.Workshop;
import com.jshx.sccjgl.service.WorkshopService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.bzzpx.entity.TeaLeaTra;
import com.jshx.bzzpx.service.TeaLeaTraService;

public class TeaLeaTraAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private TeaLeaTra teaLeaTra = new TeaLeaTra();

	/**
	 * 业务类
	 */
	@Autowired
	private TeaLeaTraService teaLeaTraService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryTrainingTimeStart;

	private Date queryTrainingTimeEnd;

	private Date queryTrainingTimeEndStart;

	private Date queryTrainingTimeEndEnd;
	@Autowired
	private HttpService httpService;

	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private WorkshopService workshopService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	private File userFile;
	private String message;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	/**
	 * 初始化 用于判断审核角色
	 */
	private String roleName;
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
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != teaLeaTra){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != teaLeaTra.getAreaId()) && (0 < teaLeaTra.getAreaId().trim().length())){
				paraMap.put("areaId", teaLeaTra.getAreaId().trim() );
			}

			if ((null != teaLeaTra.getCompanyName()) && (0 < teaLeaTra.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + teaLeaTra.getCompanyName().trim() + "%");
			}
			
			if ((null != teaLeaTra.getCompanyId()) && (0 < teaLeaTra.getCompanyId().trim().length())){
				paraMap.put("companyId",  teaLeaTra.getCompanyId().trim() );
			}

			if (null != queryTrainingTimeStart){
				paraMap.put("startTrainingTime", queryTrainingTimeStart);
			}

			if (null != queryTrainingTimeEnd){
				paraMap.put("endTrainingTime", queryTrainingTimeEnd);
			}
			if (null != queryTrainingTimeEndStart){
				paraMap.put("startTrainingTimeEnd", queryTrainingTimeEndStart);
			}

			if (null != queryTrainingTimeEndEnd){
				paraMap.put("endTrainingTimeEnd", queryTrainingTimeEndEnd);
			}
			if ((null != teaLeaTra.getTrainingName()) && (0 < teaLeaTra.getTrainingName().trim().length())){
				paraMap.put("trainingName", "%" + teaLeaTra.getTrainingName().trim() + "%");
			}

			if ((null != teaLeaTra.getTrainingCardnum()) && (0 < teaLeaTra.getTrainingCardnum().trim().length())){
				paraMap.put("trainingCardnum", "%" + teaLeaTra.getTrainingCardnum().trim() + "%");
			}

			if ((null != teaLeaTra.getTrainingWorkshopName()) && (0 < teaLeaTra.getTrainingWorkshopName().trim().length())){
				paraMap.put("trainingWorkshopName",  "%"+teaLeaTra.getTrainingWorkshopName().trim()+ "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|trainingWorkshopName|trainingName|trainingCardnum|trainingTime|trainingTimeEnd|";
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
		pagination = teaLeaTraService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != teaLeaTra)&&(null != teaLeaTra.getId()))
			teaLeaTra = teaLeaTraService.getById(teaLeaTra.getId());
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		teaLeaTra.setCompanyId(enBaseInfo.getId());
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		teaLeaTra.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		teaLeaTra.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		teaLeaTra.setCompanyId(enBaseInfo.getId());
		teaLeaTra.setCompanyName(enBaseInfo.getEnterpriseName());
		Workshop workshop = workshopService.getById(teaLeaTra.getTrainingWorkshopId());
		teaLeaTra.setTrainingWorkshopName(workshop.getWorkshopName());
		if ("add".equalsIgnoreCase(this.flag)){
			teaLeaTra.setDeptId(this.getLoginUserDepartmentId());
			teaLeaTra.setDelFlag(0);
			teaLeaTraService.save(teaLeaTra);
		}else{
			teaLeaTraService.update(teaLeaTra);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != teaLeaTra)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到teaLeaTra中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			teaLeaTraService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 导入初始化，需要传递参数
	 * fq 2016-3-4
	 */
	public String initImport(){
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		teaLeaTra.setCompanyId(enBaseInfo.getId());
		return SUCCESS;
	}
	
	
	/**
	 * 导入特种作业人员培训
	 * fq 2016-3-4
	 */
	public String importTeaLeaTra(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		Workshop workshop = workshopService.getById(ids);
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(userFile);
		} catch (Exception e) {
			e.printStackTrace();
			message = "导入失败，请使用系统模板！";
		}
		Sheet sheet = workbook.getSheet(0);
		
		int column = sheet.getColumns();
		int row = sheet.getRows();
		List<TeaLeaTra> teaLeaTraList = new ArrayList<TeaLeaTra>();
		for (int i = 1; i < row; i++) {
			
			try {
				Cell[] cells = new Cell[column];
				Cell[] cellsTmp = sheet.getRow(i);
				for (int j = 0; j < cellsTmp.length; j++) {
					cells[j] = cellsTmp[j];
				}
				
				if ((cells[0]==null ||cells[0].getContents().equals(""))&&(cells[1]==null ||cells[1].getContents().equals(""))&&(cells[2]==null ||cells[2].getContents().equals(""))&&(cells[3]==null ||cells[3].getContents().equals("")) ){
					workbook.close();
					break;
				}
				TeaLeaTra tlt = new TeaLeaTra();
				tlt.setAreaId(enBaseInfo.getEnterprisePossession());
				tlt.setAreaName(codeService.findCodeValueByMap(m).getItemText());
				tlt.setCompanyId(enBaseInfo.getId());
				tlt.setCompanyName(enBaseInfo.getEnterpriseName());
				tlt.setDeptId(this.getLoginUserDepartmentId());
				tlt.setDelFlag(0);
				tlt.setTrainingWorkshopId(ids);
				tlt.setTrainingWorkshopName(workshop.getWorkshopName());
				StringBuffer colError = null;
				//姓名
				if (cells[0]==null || cells[0].getContents().equals("")) {
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：姓名输入有误，不能为空，请检查!</span><br>");
				}else{
					tlt.setTrainingName(cells[0].getContents().trim());
				}
				//职位
				if (cells[1]==null ||cells[1].getContents().equals("")) {
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：职位输入有误，不能为空，请检查!</span><br>");
				}else{
					tlt.setTrainingPosition(cells[1].getContents().trim());
				}
				
				//培训开始时间
				if (cells[2]==null || cells[2].getContents().equals("")) {
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：培训开始时间输入有误，不能为空，请检查!</span><br>");
				}else{
					try {
						if(cells[2].getType() == CellType.DATE)
						{
							 DateCell c00 = (DateCell)cells[2]; 
						     TimeZone tz = TimeZone.getTimeZone("GMT"); 
						     sdf.setTimeZone(tz) ; 
						     tlt.setTrainingTime(c00.getDate());
						}
				        else
						{
				        	tlt.setTrainingTime(sdf.parse(cells[2].getContents().trim()));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：培训开始时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
					}
				}
				//培训结束时间
				if (cells[3]==null || cells[3].getContents().equals("")) {
					if(colError==null)
						colError = new StringBuffer();
					colError.append("<span style='color:red'>错误：培训结束时间输入有误，不能为空，请检查!</span><br>");
				}else{
					try {
						if(cells[3].getType() == CellType.DATE)
						{
							 DateCell c00 = (DateCell)cells[3]; 
						     TimeZone tz = TimeZone.getTimeZone("GMT"); 
						     sdf.setTimeZone(tz) ; 
						     tlt.setTrainingTimeEnd(c00.getDate());
						}
				        else
						{
				        	tlt.setTrainingTimeEnd(sdf.parse(cells[3].getContents().trim()));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：培训结束时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
					}
				}
				//性别
				if (cells[4] != null)
					if(!"".equals(cells[4].getContents().trim())){
						Map codeMap = new HashMap();
						codeMap.put("codeName", "性别");
						codeMap.put("itemText", cells[4].getContents().trim());
						tlt.setTrainingSex(null==codeService.findCodeValueByMap(codeMap).getItemValue()?"":codeService.findCodeValueByMap(codeMap).getItemValue());
					}
				
				//电话
				if (cells[5] != null){
					tlt.setTrainingPhone(cells[5].getContents().trim());
				}
				
				
				//培训学时
				if (cells[6]==null || cells[6].getContents().equals("")) {
					tlt.setTrainingTeacheTime(cells[6].getContents().trim());
				}
				
				//培训单位
				if (cells[7] != null){
					tlt.setTrainingAddress(cells[7].getContents().trim());
				}
				
				//授课人
				if (cells[8] != null){
					tlt.setTrainingTeacher(cells[8].getContents().trim());
				}
				
				
				//培训内容
				if (cells[9] != null){
					tlt.setTrainingContent(cells[9].getContents().trim());
				}
				
				if(colError==null){
					teaLeaTraList.add(tlt);
					errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
				}else{
					errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
					errorInfo.append(colError).append("<br>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		message = errorInfo.toString();
		try {
			for(TeaLeaTra s:teaLeaTraList){
				teaLeaTraService.save(s);
			}
		} catch (Exception e) {
			workbook.close();
				message = "导入失败！";
			BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
			throw ex;
		}
		workbook.close();
		if(message.equals("") && teaLeaTraList.size() == 0)
		{
			message = "导入失败，未读取到数据，请使用系统模板！";
		}
		if(message.contains("失败")){
			return ERROR;
		}else{
			message="";
			return SUCCESS;
		}
		
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
			roleName = "0";
		}
		return SUCCESS;
	}


	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != teaLeaTra){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != teaLeaTra.getAreaId()) && (0 < teaLeaTra.getAreaId().trim().length())){
				paraMap.put("areaId",  teaLeaTra.getAreaId().trim());
			}

			if ((null != teaLeaTra.getCompanyName()) && (0 < teaLeaTra.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + teaLeaTra.getCompanyName().trim() + "%");
			}

			if (null != queryTrainingTimeStart){
				paraMap.put("startTrainingTime", queryTrainingTimeStart);
			}

			if (null != queryTrainingTimeEnd){
				paraMap.put("endTrainingTime", queryTrainingTimeEnd);
			}
			if (null != queryTrainingTimeEndStart){
				paraMap.put("startTrainingTimeEnd", queryTrainingTimeEndStart);
			}

			if (null != queryTrainingTimeEndEnd){
				paraMap.put("endTrainingTimeEnd", queryTrainingTimeEndEnd);
			}
			if ((null != teaLeaTra.getTrainingName()) && (0 < teaLeaTra.getTrainingName().trim().length())){
				paraMap.put("trainingName", "%" + teaLeaTra.getTrainingName().trim() + "%");
			}

			if ((null != teaLeaTra.getTrainingCardnum()) && (0 < teaLeaTra.getTrainingCardnum().trim().length())){
				paraMap.put("trainingCardnum", "%" + teaLeaTra.getTrainingCardnum().trim() + "%");
			}

			if ((null != teaLeaTra.getTrainingWorkshopName()) && (0 < teaLeaTra.getTrainingWorkshopName().trim().length())){
				paraMap.put("trainingWorkshopName",  "%"+teaLeaTra.getTrainingWorkshopName().trim()+ "%");
			}

		}
		

		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、姓名或车间名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|trainingWorkshopName|trainingName|trainingCardnum|trainingTime|trainingTimeEnd|";
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
		pagination = teaLeaTraService.findByPage(pagination, paraMap);
		
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

	public TeaLeaTra getTeaLeaTra(){
		return this.teaLeaTra;
	}

	public void setTeaLeaTra(TeaLeaTra teaLeaTra){
		this.teaLeaTra = teaLeaTra;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryTrainingTimeStart(){
		return this.queryTrainingTimeStart;
	}

	public void setQueryTrainingTimeStart(Date queryTrainingTimeStart){
		this.queryTrainingTimeStart = queryTrainingTimeStart;
	}

	public Date getQueryTrainingTimeEnd(){
		return this.queryTrainingTimeEnd;
	}

	public void setQueryTrainingTimeEnd(Date queryTrainingTimeEnd){
		this.queryTrainingTimeEnd = queryTrainingTimeEnd;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleName() {
		return roleName;
	}
	public File getUserFile() {
		return userFile;
	}
	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getQueryTrainingTimeEndStart() {
		return queryTrainingTimeEndStart;
	}
	public void setQueryTrainingTimeEndStart(Date queryTrainingTimeEndStart) {
		this.queryTrainingTimeEndStart = queryTrainingTimeEndStart;
	}
	public Date getQueryTrainingTimeEndEnd() {
		return queryTrainingTimeEndEnd;
	}
	public void setQueryTrainingTimeEndEnd(Date queryTrainingTimeEndEnd) {
		this.queryTrainingTimeEndEnd = queryTrainingTimeEndEnd;
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
