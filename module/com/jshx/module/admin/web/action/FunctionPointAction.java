package com.jshx.module.admin.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.FunctionPoint;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.security.EdpShiroFilterFactoryBean;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.FunctionPointService;
import com.jshx.module.admin.service.ModuleService;
import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserService;

public class FunctionPointAction extends BaseAction
{
	private static final long serialVersionUID = -8217858532342255077L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private FunctionPoint functionPoint = new FunctionPoint();

	/**
	 * 业务类
	 */
	@Autowired
	private FunctionPointService functionPointService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	@Qualifier("securityServiceImpl")
	private SecurityService securityService;
	
	@Autowired
	private DeptService deptService;
	
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
	
	private List<Permission> funcRightList;
	
	private String selDept;
	
	private String roleCodes;
	
	/**
	 * 执行功能点查询，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"funcName":"","funcPermission":"","id":"","isBandingModule":"","moduleId":"","moduleName":""}]}
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != functionPoint){
			if ((null != functionPoint.getFuncName()) && (0 < functionPoint.getFuncName().trim().length())){
				paraMap.put("funcName", "%" + functionPoint.getFuncName().trim() + "%");
			}

			if ((null != functionPoint.getIsBandingModule()) && (0 < functionPoint.getIsBandingModule().trim().length())){
				paraMap.put("isBandingModule", functionPoint.getIsBandingModule().trim());
			}

			if ((null != functionPoint.getModuleName()) && (0 < functionPoint.getModuleName().trim().length())){
				paraMap.put("moduleName", "%" + functionPoint.getModuleName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		codeMap.put("isBandingModule","402809812e7f8c28012e7fa82239000c");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|funcName|funcPermission|isBandingModule|moduleId|moduleName|";
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
		pagination = functionPointService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看功能点详细信息
	 */
	public String view() throws Exception{
		if((null != functionPoint)&&(null != functionPoint.getId()))
			functionPoint = functionPointService.getById(functionPoint.getId());
		
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
		Module module = moduleService.findModuleByModuleCode(functionPoint.getModuleId());
		if(module!=null)
			functionPoint.setModuleId(module.getId());
		else
			functionPoint.setModuleId(null);
		if ("add".equalsIgnoreCase(this.flag)){
			functionPoint.setDeptId(this.getLoginUserDepartmentId());
			functionPoint.setDelFlag(0);
			functionPointService.save(functionPoint);
		}else{
			functionPointService.update(functionPoint);
		}
		return RELOAD;
	}

	/**
	 * 删除功能点，返回json字符串：{"result":"true|false"}
	 */
	public String delete() throws Exception{
	    try{
			functionPointService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 返回功能点设置角色页面
	 */
	public String editFuncRole(){
		if (functionPoint != null && functionPoint.getId()!=null) {
			functionPoint = functionPointService.getById(functionPoint.getId());
		}
			
		String permissionExpression = functionPoint.getFuncPermission();
		funcRightList = securityService.findPermission(null, null, 1, permissionExpression);
		
		return SUCCESS;
	}
	/**
	 * 保存功能点角色设置，返回json字符串：{"result":"true|false"}
	 */
	public void saveFuncRole(){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			functionPoint = functionPointService.getById(functionPoint.getId());
			String[] roleIds = roleCodes.split("\\|");
			securityService.saveFuncPermissions(roleIds, functionPoint.getFuncPermission());
			EdpShiroFilterFactoryBean.SpringShiroFilter shiroFilter = (EdpShiroFilterFactoryBean.SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
			shiroFilter.updateFilterChainDefinitionMap();
			result.put("result", true);
		}catch(Exception e){
			result.put("result", false);
			result.put("error", e.getMessage());
		}
		this.outputJson(null, result);
	}
	
	/**
	 * 部门用户树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 * 
	 */
	public void findUserTreeForFunc() {
		functionPoint = functionPointService.getById(functionPoint.getId());
		if (null == selDept) {
			// 初始化部门树
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "D");
			root.put("text", "组织机构");
			root.put("state", "opened");
			root.put("iconCls", "icon-treeNodeParent");
			selDept="";
			List<Map<String, Object>> items = findUserTreeForFunc(selDept);
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else{
			selDept = selDept.substring(1, selDept.length());	
			List<Map<String, Object>> items = findUserTreeForFunc(selDept);
			writerJSONArray(items);
		}
	}
		
	private List<Map<String, Object>> findUserTreeForFunc(String deptCode) {
		//TODO
		List<Department> deptList = deptService.findDeptByParentDeptCode(deptCode);

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for(Department dept:deptList){
			if(dept.getDelFlag()==1)
				continue;
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", "D"+dept.getDeptCode());
			item.put("text",dept.getDeptName());
			item.put("state", "closed");	
			item.put("iconCls", "icon-treeNodeParent");
			//if(dept.getHasChild()>0&&deptService.findDeptByParentDeptCode(dept.getDeptCode()).size()>0)
			//	item.put("state", "closed");
			items.add(item);
		}
		
		if(!deptCode.equals("")){
			List<User> userList = userService.findAllUsersByDept(deptCode);
			for(User user : userList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", user.getId());
				map.put("text", user.getDisplayName());
				//map.put("state", "closed");
				String permissionExpression = functionPoint.getFuncPermission();
				funcRightList = securityService.findPermission(user.getId(), null, 2, permissionExpression);
				if(funcRightList!=null && funcRightList.size()>0)
					map.put("checked", true);
				else
					map.put("checked", false);
				items.add(map);
			}
		}

		return items;
	}
	/**
	 * 返回功能点用户设置页面
	 */
	public String editFuncUser(){
		if (functionPoint != null && functionPoint.getId()!=null) {
			functionPoint = functionPointService.getById(functionPoint.getId());
		}
			
		String permissionExpression = functionPoint.getFuncPermission();
		funcRightList = securityService.findPermission(null, null, 2, permissionExpression);
		
		return SUCCESS;
	}
	/**
	 * 保存功能点用户设置，返回json字符串：{"result":"true|false"}
	 */
	public void saveFuncUser(){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			functionPoint = functionPointService.getById(functionPoint.getId());
			String[] roleIds = roleCodes.split("\\|");
			securityService.saveFuncPermissionsByUsers(roleIds, functionPoint.getFuncPermission());
			EdpShiroFilterFactoryBean.SpringShiroFilter shiroFilter = (EdpShiroFilterFactoryBean.SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
			shiroFilter.updateFilterChainDefinitionMap();
			result.put("result", true);
		}catch(Exception e){
			result.put("result", false);
			result.put("error", e.getMessage());
		}
		this.outputJson(null, result);
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

	public FunctionPoint getFunctionPoint(){
		return this.functionPoint;
	}

	public void setFunctionPoint(FunctionPoint functionPoint){
		this.functionPoint = functionPoint;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<Permission> getFuncRightList() {
		return funcRightList;
	}

	public void setFuncRightList(List<Permission> funcRightList) {
		this.funcRightList = funcRightList;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getSelDept() {
		return selDept;
	}

	public void setSelDept(String selDept) {
		this.selDept = selDept;
	}
       
    
}
