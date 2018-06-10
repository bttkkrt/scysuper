package com.jshx.zxzzjh.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.zxzzjh.entity.RecPla;
import com.jshx.zxzzjh.service.RecPlaService;

public class RecPlaAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;
	private int pageNo;
	
	private int pageSize;
	/**
	 * 实体类
	 */
	private RecPla recPla = new RecPla();
	private String searchLike;
	/**
	 * 业务类
	 */
	@Autowired
	private RecPlaService recPlaService;
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
	
	
	private Date queryWorkTimeStart;

	private Date queryWorkTimeEnd;

	private String addRight="";
	
	

	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private HttpService httpService;
	
	public String init(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A03")||httpService.judgeRoleCode(userId, "A05")||httpService.judgeRoleCode(userId, "A07")||httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A11")){
			addRight="y";
		}else{
			addRight="n";
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
		    
		if(null != recPla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != recPla.getWorkGoal()) && (0 < recPla.getWorkGoal().trim().length())){
				paraMap.put("workGoal", "%" + recPla.getWorkGoal().trim() + "%");
			}
			if ((null != recPla.getPlanName()) && (0 < recPla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + recPla.getPlanName().trim() + "%");
			}
			if ((null != recPla.getStatus()) && (0 < recPla.getStatus().trim().length())){
				paraMap.put("status",  recPla.getStatus().trim() );
			}
			if (null != queryWorkTimeStart){
				paraMap.put("startWorkTime", queryWorkTimeStart);
			}

			if (null != queryWorkTimeEnd){
				paraMap.put("endWorkTime", queryWorkTimeEnd);
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|workGoal|workTimeStart|workTimeEnd|planName|status|createUserID|";
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
		pagination = recPlaService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != recPla)&&(null != recPla.getId()))
			recPla = recPlaService.getById(recPla.getId());
		
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
		recPla.setStatus("0");
		if ("add".equalsIgnoreCase(this.flag)){
			recPla.setDeptId(this.getLoginUserDepartmentId());
			recPla.setDelFlag(0);
			recPlaService.save(recPla);
		}else{
			recPlaService.update(recPla);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != recPla)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到recPla中去
				
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
			recPlaService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
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
		if(httpService.judgeRoleCode(userId, "A03")||httpService.judgeRoleCode(userId, "A05")||httpService.judgeRoleCode(userId, "A07")||httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A11")){
			addRight="y";
		}else{
			addRight="n";
		}
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A11")||httpService.judgeRoleCode(this.getLoginUser().getId(), "A12")){
			List<String> deptIds=new ArrayList<String>();
			deptIds.add(this.getLoginUserDepartment().getId());
			paraMap.put("deptCodes", deptIds);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != recPla){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != recPla.getWorkGoal()) && (0 < recPla.getWorkGoal().trim().length())){
				paraMap.put("workGoal", "%" + recPla.getWorkGoal().trim() + "%");
			}
			if ((null != recPla.getPlanName()) && (0 < recPla.getPlanName().trim().length())){
				paraMap.put("planName", "%" + recPla.getPlanName().trim() + "%");
			}
			if ((null != recPla.getStatus()) && (0 < recPla.getStatus().trim().length())){
				paraMap.put("status",  recPla.getStatus().trim() );
			}
			if (null != queryWorkTimeStart){
				paraMap.put("startWorkTime", queryWorkTimeStart);
			}

			if (null != queryWorkTimeEnd){
				paraMap.put("endWorkTime", queryWorkTimeEnd);
			}
		}

		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索计划名称".equals(searchLike)){
			paraMap.put("planName", "%" + searchLike.trim() + "%");
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|workGoal|workTimeStart|workTimeEnd|planName|status|createUserID|";
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
		pagination = recPlaService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage=(pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String zxzzjh(){
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A03")||httpService.judgeRoleCode(userId, "A05")||httpService.judgeRoleCode(userId, "A07")||httpService.judgeRoleCode(userId, "A09")||httpService.judgeRoleCode(userId, "A11")){
			addRight="y";
		}else{
			addRight="n";
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

	public RecPla getRecPla(){
		return this.recPla;
	}

	public void setRecPla(RecPla recPla){
		this.recPla = recPla;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryWorkTimeStart(){
		return this.queryWorkTimeStart;
	}

	public void setQueryWorkTimeStart(Date queryWorkTimeStart){
		this.queryWorkTimeStart = queryWorkTimeStart;
	}

	public Date getQueryWorkTimeEnd(){
		return this.queryWorkTimeEnd;
	}

	public void setQueryWorkTimeEnd(Date queryWorkTimeEnd){
		this.queryWorkTimeEnd = queryWorkTimeEnd;
	}
	public String getAddRight() {
		return addRight;
	}
	public void setAddRight(String addRight) {
		this.addRight = addRight;
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
