/**
 * 
 */
package com.jshx.module.admin.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.entity.FunctionPoint;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.security.EdpShiroFilterFactoryBean;
import com.jshx.module.admin.service.FunctionPointService;
import com.jshx.module.admin.service.ModuleService;
import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserRoleService;

/**
 * @author f_cheng
 * 
 */
@SuppressWarnings("serial")
public class UserRoleAction extends BaseAction {

	@Autowired() 
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

	private UserRole userRole;
	
	@Autowired() 
	@Qualifier("moduleService")
	private ModuleService moduleService;
	
	@Autowired
	@Qualifier("securityServiceImpl")
	private SecurityService securityService;
	
	@Autowired
	private FunctionPointService functionPointService;

	private Pagination pagination;

	private Module module;

	private String selNode;
	
	private String userId;
	
	private String moduleId;
	
	private String funcId;
	
	private String[] ids;
	
	/**
	 * 删除角色前判断角色下面有没有用户
	 */
	public void checkRole(){
		if(ids!=null && ids.length!=0){
			
			Integer cnt = userRoleService.cntUsersinRole(ids);
			if(cnt==0){
				try{
					getResponse().getWriter().println("{\"result\":true}");
				}catch(Exception e){
				}
			}else{
				try{
					getResponse().getWriter().println("{\"result\":false}");
				}catch(Exception e){
				}
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":false}");
			}catch(Exception e){
			}
		}
	}
	
	/**
	 * 删除角色，返回json字符串：{"result":"true|false"}
	 */
	public void deleteRole() {
		if(ids!=null && ids.length!=0){
			for(String item : ids){
				userRoleService.delete(item);
			}
			try{
				getResponse().getWriter().println("{\"result\":true}");
			}catch(Exception e){
			}
		}else{
			try{
				getResponse().getWriter().println("{\"result\":false}");
			}catch(Exception e){
			}
		}
	}
	/**
	 * 添加/修改角色
	 * 
	 * @return String 
	 */
	public String editRole() {
		if(userRole==null)
			return EDIT;
		
		if (userRole.getId()!=null){ //修改角色
			userRole = userRoleService.findRoleById(userRole.getId());
		}else{ //添加新角色
			String parentRoleCode=userRole.getRoleCode();
			if(!"".equals(parentRoleCode)){ //非顶层节点，有父节点
				//parentRoleCode=parentRoleCode.substring(0, userRole.getRoleCode().length()-2);
				userRole.setRoleCode(userRoleService.createRoleCode(parentRoleCode));
				
				UserRole parentRole = userRoleService.findRoleByCode(parentRoleCode);
				if(parentRole!=null){
					userRole.setParentRoleId(parentRole.getId());
					userRole.setParentRole(parentRole);
				}
			}else{ //顶层节点，无父节点
				if(this.getLoginUser().getIsSuperAdmin()){
					userRole.setRoleCode(userRoleService.createRoleCode("A"));
				}else{
					userRole.setRoleCode(userRoleService.createRoleCode("U"));
				}
			}
		}
			
		return EDIT;
	}
	
	/**
	 * 分页查询角色信息，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"createUserID":"","id":"","roleCode":"","roleName":"","roleType":"","sortSq":0}]}
	 * @return String 
	 */
	public void listRole() {
		List<UserRole> userRoleList = userRoleService.getRoleByUserForList(null,userRole!=null?userRole.getRoleCode():"",userRole!=null?userRole.getRoleName():"", getLoginUser());
		pagination = new Pagination(super.getRequest());
		int pageNumber = pagination.getPageNumber();
		int pageSize = pagination.getPageSize();
		int total = userRoleList.size();
		pagination.setList(userRoleList.subList(pageSize*(pageNumber-1), total));
		pagination.setTotalCount(total);
		
		JsonConfig config = new JsonConfig();
		Map<String, String> codeMap = new HashMap<String, String>();
		codeMap.put("roleType","8a8180553eac23cd013eac26770a001f");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|roleName|isPublic|sortSq|roleCode|createUserID|roleType|delFlag|";
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
		convObjectToJson(pagination, config);
		//outputJsonList(userRoleList.size(), "id|roleName|isPublic|sortSq|roleCode|createUserID|", pagination.getListOfObject());
	}
	/**
	 * 保存角色信息
	 * 
	 * @return String  
	 */
	public void saveRole() {
		try {
			if(userRole.getParentRoleId()!=null && userRole.getParentRoleId().trim().equals(""))
				userRole.setParentRoleId(null);
			if (userRole.getId() == null) {
				int status=userRoleService.isReg(null, userRole.getRoleName(), userRole.getRoleCode());
				if(status==1){
					userRole.setRoleCode(userRoleService.createRoleCode(userRole.getRoleCode().substring(0,userRole.getRoleCode().length()-2)));
				}
				userRoleService.save(userRole);
			} else
				userRoleService.modify(userRole);
			EdpShiroFilterFactoryBean.SpringShiroFilter shiroFilter = (EdpShiroFilterFactoryBean.SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
			shiroFilter.updateFilterChainDefinitionMap();
			getResponse().getWriter().println("{\"status\":\"y\",\"info\":\"\"}");	
		} catch (Exception e) {
			try {
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"新增角色失败！\"}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}		
			e.printStackTrace();
		}
	}

	/**
	 * 角色树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	public void findChildNode() {
		if (null == selNode) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "A");
			root.put("text", "角色");
			List<UserRole> userRoleList = userRoleService.findLeafRole(userRole.getRoleType(), selNode);
			if (userRoleList.size() > 0)
				root.put("state", "opened");
			else
				root.put("state", "closed");
			List<Map<String, Object>> items = getChildren(selNode);
			root.put("children", items);			
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			List<Map<String, Object>> items = getChildren(selNode);
			writerJSONArray(items);
		}
	}

	private List<Map<String, Object>> getChildren(String selNode) {
		List<UserRole> userRoleList = userRoleService.getRoleByUser(selNode, "",getLoginUser());
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (UserRole role : userRoleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", role.getRoleCode());
			item.put("text", role.getRoleName());
			if (userRoleService.findLeafRole(role.getRoleType(), role.getRoleCode()).size() > 0) {
				item.put("state", "closed");
			}

			items.add(item);
		}
		return items;
	}
	
	/**
	 * 用户管理中的角色设置，角色树由ext变为easyui
	 * 
	 * @Title: findChildNode
	 * @Description:
	 * @return
	 */
	public void findRoleTreeForUser() {
		if (null == selNode) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "A");
			root.put("text", "角色");
			List<UserRole> userRoleList = userRoleService.findLeafRole(userRole.getRoleType(), selNode);
			if (userRoleList.size() > 0)
				root.put("state", "opened");
			else
				root.put("state", "closed");
			//selNode="A";
			List<Map<String, Object>> items = getChildrenForUser(selNode);
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			List<Map<String, Object>> items = getChildrenForUser(selNode);
			writerJSONArray(items);
		}
	}

	private List<Map<String, Object>> getChildrenForUser(String selNode) {
		///获得当前selNode下所有的子节点
		Map<String, Object> paraMap = new HashMap<String, Object>();
		//paraMap.put("roleCode", selNode+"%");
		//paraMap.put("length",(long) (selNode.length()+2));
		//List<UserRole> roleList = userRoleService.findUserRoleList(paraMap);
		List<UserRole> userRoleList = userRoleService.getRoleByUser(selNode,"", getLoginUser());
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (UserRole role : userRoleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", role.getRoleCode());
			item.put("text", role.getRoleName());
			if (userRoleService.findLeafRole(userRole.getRoleType(), role.getRoleCode()).size() > 0) {
				item.put("state", "closed");
			}
			paraMap = new HashMap<String,Object>();
			if (userId != null && !userId.trim().equals("")) {
				paraMap.put("roleId", role.getId());
				paraMap.put("userId", userId);
				List<UserRole> list = userRoleService.findUserRole(paraMap);
				if(list != null && list.size()>0)
					item.put("checked", true);
			}
			items.add(item);
		}
		return items;
	}
	
	/**
	 * 模块管理中的角色设置，角色树由ext变为easyui
	 * 
	 * @Title: findChildNode
	 * @Description:
	 * @return
	 */
	public void findRoleTreeForModule() {
		if (null == selNode) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "A");
			root.put("text", "角色");
			List<UserRole> userRoleList = userRoleService.findLeafRole(userRole.getRoleType(), selNode);
			if (userRoleList.size() > 0)
				root.put("state", "opened");
			else
				root.put("state", "closed");
			//selNode="A";
			List<Map<String, Object>> items = getChildrenForModule(selNode);
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			List<Map<String, Object>> items = getChildrenForModule(selNode);
			writerJSONArray(items);
		}
	}
	

	private List<Map<String, Object>> getChildrenForModule(String selNode) {
		///获得当前selNode下所有的子节点
		Map<String, Object> paraMap = null;
		//paraMap.put("roleCode", selNode+"%");
		//paraMap.put("length",(long) (selNode.length()+2));
		//List<UserRole> roleList = userRoleService.findUserRoleList(paraMap);
		List<UserRole> userRoleList = userRoleService.getRoleByUser(selNode,"", getLoginUser());
		Module moduleTemp = moduleService.findModuleById(moduleId);
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (UserRole role : userRoleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", role.getRoleCode());
			item.put("text", role.getRoleName());
			if (userRoleService.findLeafRole(userRole.getRoleType(), role.getRoleCode()).size() > 0) {
				item.put("state", "closed");
			}
			paraMap = new HashMap<String,Object>();
			if (moduleId != null && !moduleId.trim().equals("")) {
				/** 变更平台的权限管理    
				 *  Chenjian 2013/04/16
				paraMap.put("role", role);
				paraMap.put("module", moduleTemp);
				List<ModuleRight> list = moduleService.findRightForModule(paraMap);
				*/
				//TODO
				String permissionExpression = "module:" + moduleTemp.getModuleCode() ;
				List<Permission> list = securityService.findPermission(null, role.getId(), 0, permissionExpression);
				if(list != null && list.size()>0)
					item.put("checked", true);
			}
			items.add(item);
		}
		return items;
	}
	/**
	 * 功能点角色设置树的节点查询
	 */
	public void findRoleTreeForFunc(){
		if (null == selNode) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "A");
			root.put("text", "角色");
			List<UserRole> userRoleList = userRoleService.findLeafRole(userRole.getRoleType(), selNode);
			if (userRoleList.size() > 0)
				root.put("state", "opened");
			else
				root.put("state", "closed");
			//selNode="A";
			List<Map<String, Object>> items = getChildrenForFunc(selNode);
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			List<Map<String, Object>> items = getChildrenForFunc(selNode);
			writerJSONArray(items);
		}
	}
	
	private List<Map<String, Object>> getChildrenForFunc(String selNode) {
		///获得当前selNode下所有的子节点
		Map<String, Object> paraMap = new HashMap<String, Object>();
		//paraMap.put("roleCode", selNode+"%");
		//paraMap.put("length",(long) (selNode.length()+2));
		//List<UserRole> roleList = userRoleService.findUserRoleList(paraMap);
		List<UserRole> userRoleList = userRoleService.getRoleByUser(selNode,"", getLoginUser());
		FunctionPoint funcPoint = functionPointService.getById(funcId);
		
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (UserRole role : userRoleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", role.getRoleCode());
			item.put("text", role.getRoleName());
			if (userRoleService.findLeafRole(userRole.getRoleType(), role.getRoleCode()).size() > 0) {
				item.put("state", "closed");
			}
			paraMap = new HashMap<String,Object>();
			if (funcId != null && !funcId.trim().equals("")) {
				
				//TODO
				String permissionExpression = funcPoint.getFuncPermission();
				List<Permission> list = securityService.findPermission(null, role.getId(), 1, permissionExpression);
				if(list != null && list.size()>0)
					item.put("checked", true);
			}
			items.add(item);
		}
		return items;
	}
	/**
	 * 判断功能点角色名称是否存在
	 */
	public void roleIsReg(){
		int status=userRoleService.isReg(userRole.getId(),this.getRequestParameter("param"),userRole.getRoleCode());
		
		try{
			if(status==0){
				getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"角色名称重复，请重新输入！\"}");
			}else{
				getResponse().getWriter().println("{\"status\":\"y\"}");
			}
		}catch(Exception e){
		}

	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getSelNode() {
		return selNode;
	}

	public void setSelNode(String selNode) {
		this.selNode = selNode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
class SortByCodeLength implements Comparator<UserRole>{
	public int compare(UserRole obj1, UserRole obj2) {
		//if (Integer.parseInt(code1.getItemValue()) > Integer.parseInt(code2.getItemValue()))
		if(obj1.getRoleCode().length() > obj2.getRoleCode().length())
			return 1;
		else
			return 0;
	}
}