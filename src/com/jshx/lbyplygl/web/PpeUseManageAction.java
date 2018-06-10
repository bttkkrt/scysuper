package com.jshx.lbyplygl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.http.service.HttpService;
import com.jshx.lbypgl.service.PpeManagService;
import com.jshx.lbyplygl.entity.PpeUseManage;
import com.jshx.lbyplygl.service.PpeUseManageService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.tzzyry.entity.SpeJobPer;

public class PpeUseManageAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private PpeUseManage ppeUseManage = new PpeUseManage();

	/**
	 * 业务类
	 */
	@Autowired
	private PpeUseManageService ppeUseManageService;
	@Autowired
	private PpeManagService ppeManagService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryPpeUseTimeStart;

	private Date queryPpeUseTimeEnd;

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private CodeService codeService;

	@Autowired
	private HttpService httpService;
	
	private File userFile;
	private String message;
	
	private String loginUserId;
	private String roleName;
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	
	
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
	
	
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String init(){
		loginUserId = this.getLoginUser().getId();
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "1";
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
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
		    
		if(null != ppeUseManage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != ppeUseManage.getAreaId()) && (0 < ppeUseManage.getAreaId().trim().length())){
				paraMap.put("areaId",  ppeUseManage.getAreaId() );
			}

			if ((null != ppeUseManage.getPpeUsePeople()) && (0 < ppeUseManage.getPpeUsePeople().trim().length())){
				paraMap.put("ppeUsePeople", "%" + ppeUseManage.getPpeUsePeople().trim() + "%");
			}

			if ((null != ppeUseManage.getPpeName()) && (0 < ppeUseManage.getPpeName().trim().length())){
				paraMap.put("ppeName", "%" + ppeUseManage.getPpeName().trim() + "%");
			}
			if ((null != ppeUseManage.getPpeId()) && (0 < ppeUseManage.getPpeId().trim().length())){
				paraMap.put("ppeId",  ppeUseManage.getPpeId() );
			}

			if ((null != ppeUseManage.getCompanyName()) && (0 < ppeUseManage.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + ppeUseManage.getCompanyName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|ppeName|ppeUseNum|ppeUsePeople|";
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
		pagination = ppeUseManageService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != ppeUseManage)&&(null != ppeUseManage.getId())){
			ppeUseManage = ppeUseManageService.getById(ppeUseManage.getId());
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			ppeUseManage.setAreaId(enBaseInfo.getEnterprisePossession());
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			ppeUseManage.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			ppeUseManage.setCompanyId(enBaseInfo.getId());
			ppeUseManage.setCompanyName(enBaseInfo.getEnterpriseName());
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
		if(null!=ppeUseManage.getPpeId()&&!"".equals(ppeUseManage.getPpeId())){
			ppeUseManage.setPpeName(ppeManagService.getById(ppeUseManage.getPpeId()).getPpeName());
		}
	
		if ("add".equalsIgnoreCase(this.flag)){
			ppeUseManage.setDeptId(this.getLoginUserDepartmentId());
			ppeUseManage.setDelFlag(0);
			ppeUseManageService.save(ppeUseManage);
		}else{
			ppeUseManageService.update(ppeUseManage);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != ppeUseManage)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到ppeUseManage中去
				
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
			ppeUseManageService.deleteWithFlag(ids);
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
		return SUCCESS;
	}
	
	
	/**
	 * 导入特种作业人员
	 * fq 2016-3-4
	 */
	public String importPpeUseManage(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
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
			List<PpeUseManage> ppeUseManageList = new ArrayList<PpeUseManage>();
			for (int i = 1; i < row; i++) {
				
				try {
					Cell[] cells = new Cell[column];
					Cell[] cellsTmp = sheet.getRow(i);
					for (int j = 0; j < cellsTmp.length; j++) {
						cells[j] = cellsTmp[j];
					}
					
					if ((cells[0]==null ||cells[0].getContents().equals(""))&&(cells[1]==null ||cells[1].getContents().equals(""))){
						workbook.close();
						break;
					}
					PpeUseManage sjb = new PpeUseManage();
					sjb.setAreaId(enBaseInfo.getEnterprisePossession());
					sjb.setAreaName(codeService.findCodeValueByMap(m).getItemText());
					sjb.setCompanyId(enBaseInfo.getId());
					sjb.setCompanyName(enBaseInfo.getEnterpriseName());
					sjb.setDeptId(this.getLoginUserDepartmentId());
					sjb.setDelFlag(0);
					
					StringBuffer colError = null;
					//用品名称
					if (cells[0]==null || cells[0].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：用品名称输入有误，不能为空，请检查!</span><br>");
					}else{
						sjb.setPpeName(cells[0].getContents().trim());
					}
					//领用人
					if (cells[1]==null ||cells[1].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：领用人输入有误，不能为空，请检查!</span><br>");
					}else{
						sjb.setPpeUsePeople(cells[1].getContents().trim());
					}
					//领用数量
					if (cells[2] != null) {
						sjb.setPpeUseNum(cells[2].getContents().trim());
					}
					//领用时间
					if (cells[3] != null){
						if(!"".equals(cells[3].getContents().trim())){
							try {
								if(cells[3].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[3]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjb.setPpeUseTime(c00.getDate());
								}
					            else
								{
					            	sjb.setPpeUseTime(sdf.parse(cells[3].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：领用时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					//备注
					if (cells[4] != null){
						sjb.setPpeUseRemark(cells[4].getContents().trim());
					}
					
					if(colError==null){
						ppeUseManageList.add(sjb);
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
				ppeUseManageService.saveList(ppeUseManageList);
			} catch (Exception e) {
				workbook.close();
					message = "导入失败！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && ppeUseManageList.size() == 0)
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
		roleName = "1";
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
		return SUCCESS;
	}

	public String lbyply(){
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
		    
		if(null != ppeUseManage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != ppeUseManage.getAreaId()) && (0 < ppeUseManage.getAreaId().trim().length())){
				paraMap.put("areaId",  ppeUseManage.getAreaId() );
			}

			if ((null != ppeUseManage.getPpeUsePeople()) && (0 < ppeUseManage.getPpeUsePeople().trim().length())){
				paraMap.put("ppeUsePeople", "%" + ppeUseManage.getPpeUsePeople().trim() + "%");
			}

			if ((null != ppeUseManage.getPpeName()) && (0 < ppeUseManage.getPpeName().trim().length())){
				paraMap.put("ppeName", "%" + ppeUseManage.getPpeName().trim() + "%");
			}
			if ((null != ppeUseManage.getPpeId()) && (0 < ppeUseManage.getPpeId().trim().length())){
				paraMap.put("ppeId",  ppeUseManage.getPpeId() );
			}

			if ((null != ppeUseManage.getCompanyName()) && (0 < ppeUseManage.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + ppeUseManage.getCompanyName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|ppeName|ppeUseNum|ppeUsePeople|";
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
		
		
		if(null!=searchLike&&!"".equals(searchLike)){
			paraMap.put("companyName", "%" + searchLike.trim() + "%");
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = ppeUseManageService.findByPage(pagination, paraMap);
		
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

	public PpeUseManage getPpeUseManage(){
		return this.ppeUseManage;
	}

	public void setPpeUseManage(PpeUseManage ppeUseManage){
		this.ppeUseManage = ppeUseManage;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryPpeUseTimeStart(){
		return this.queryPpeUseTimeStart;
	}

	public void setQueryPpeUseTimeStart(Date queryPpeUseTimeStart){
		this.queryPpeUseTimeStart = queryPpeUseTimeStart;
	}

	public Date getQueryPpeUseTimeEnd(){
		return this.queryPpeUseTimeEnd;
	}

	public void setQueryPpeUseTimeEnd(Date queryPpeUseTimeEnd){
		this.queryPpeUseTimeEnd = queryPpeUseTimeEnd;
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

}
