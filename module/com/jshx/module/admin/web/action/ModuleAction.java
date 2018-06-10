/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.ModuleRight;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.extend.IModuleExtendInfo;
import com.jshx.module.admin.security.EdpShiroFilterFactoryBean;
import com.jshx.module.admin.service.ModuleService;
import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserRoleService;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-18 上午11:01:48 类说明
 */
public class ModuleAction extends BaseAction {

	private static final long serialVersionUID = -7144175788611828086L;

	@Autowired() 
	@Qualifier("moduleService")
	private ModuleService moduleService;

	@Autowired() 
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired() 
	@Qualifier("securityServiceImpl")
	private SecurityService securityService;
	
	private Pagination pagination;

	private Module module;

	private String moduleCode;

	private String imageType;

	private String[] fileNames;

	private String[] roleIds;

	private List<UserRole> roleList;

	private List<CodeValue> valueList;

	private String moduleId;

	private String codeId;

	private String selModule;
	
	private String callback;
	
	private String roleid;
	
	private String roleCodes;
	
	private String roleId;
	
	private String parentModuleName;
	
	private String parentModuleCode;

	/**
	 * 查看模块信息
	 * 
	 * @return String
	 */
	public String viewModule() {
		if (module != null && module.getId() != null) {
			module = moduleService.findModuleById(module.getId());
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.MODULE_NULL_EXCEPTION);
			throw ex;
		}
		return VIEW;
	}

	/**
	 * 返回创建/修改模块页面
	 * 
	 * @return String
	 */
	public String editModule() {
		if (module != null && module.getId() != null) {
			//修改
			module = moduleService.findModuleById(module.getId());
			if(null!=module && null!=module.getParentModule() 
				&& null!=module.getParentModule().getModuleCode() 
				&& null!=module.getParentModule().getModuleName()){
				this.parentModuleCode = module.getParentModule().getModuleCode();
				this.parentModuleName = module.getParentModule().getModuleName();
			}else{
				this.parentModuleCode = "所有模块";
				this.parentModuleName = "M";					
			}
		}else{
			//新建
			if(null!=moduleCode){
				Module parentModule = moduleService.findModuleByModuleCode(moduleCode);
				if(null!=parentModule){
					this.parentModuleCode = parentModule.getModuleCode();
					this.parentModuleName = parentModule.getModuleName();
				}else{
					this.parentModuleCode = "所有模块";
					this.parentModuleName = "M";				
				}
			}
			this.module = new Module();
			this.module.setModuleCode(moduleService.createModuleCode(moduleCode));
		}
		
		roleList = userRoleService.getAll();
		return EDIT;
	}

	/**
	 * 保存模块信息
	 * 
	 * @return String
	 */
	public String saveModule() {
		if (module != null) {
			Module parentModule = moduleService
					.findModuleByModuleCode(moduleCode);
			module.setParentModule(parentModule);
			// by 张天明 - 将模块地址trim一下，防止出现后面多一个空格的情况
			module.setModuleAddr(module.getModuleAddr().trim());
			IModuleExtendInfo moduleExtendInfo = (IModuleExtendInfo)createExtendInfo();
			module.setModuleExtendInfo(moduleExtendInfo);
			if (module.getId() == null)
				moduleService.save(module, roleIds);
			else
				moduleService.modify(module, roleIds);
			//修改模块后重新加载模块的权限设置
			if(SysPropertiesUtil.getBoolean("NEED_URL_AUTH", true))
				securityService.loadUrlAuth();
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.MODULE_NULL_EXCEPTION);
			throw ex;
		}
		return RELOAD;
	}

	/**
	 * 激活被禁用的模块，返回json字符串：{"result":"true|false"}
	 * 
	 * @return String
	 */
	public String activeModule() {
		if (module != null && module.getId() != null) {
			module = moduleService.activeModule(module.getId());
			try {
				getResponse().getWriter().println("{\"result\":\"true\"}");
			} catch (Exception e) {
			}
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.MODULE_NULL_EXCEPTION);
			throw ex;
		}
		return null;
	}

	/**
	 * 禁用模块，返回json字符串：{"result":"true|false"}
	 * 
	 * @return String
	 */
	public String inactiveModule() {
		if (module != null && module.getId() != null) {
			module = moduleService.inactiveModule(module.getId());
			try {
				getResponse().getWriter().println("{\"result\":\"true\"}");
			} catch (Exception e) {
			}
		} else {
			BasalException ex = new BasalException(BasalException.NO, Constants.MODULE_NULL_EXCEPTION);
			try {
				getResponse().getWriter().println("{\"result\":\"false\"}");
			} catch (Exception e) {

			}
			throw ex;
		}
		return null;
	}

	public String list() {
		return LIST;
	}

	/**
	 * 执行模块查询，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"id":"","isVisible":1,"moduleAddr":"","moduleCode":"","moduleFullName":"","moduleName":"","sortSq":0,"target":""}]}
	 * @return String
	 */
	public void listModule() {
		pagination = new Pagination(super.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (module != null) {
			// by Nils
			// 传回来为""和”0“一样对待
			if (module.getModuleCode() != null
					&& !module.getModuleCode().equals("0")
					&& !module.getModuleCode().equals("")) {

				paraMap.put("parentModuleCode", module.getModuleCode().trim()
						+ "%");
				paraMap.put("length",module.getModuleCode()+"00");
				if (module.getModuleName() != null)
					paraMap.put("moduleName", module.getModuleName() + "%");
				if (module.getIsVisible() == null || module.getIsVisible() == 0) {
					paraMap.put("isVisible", 1);
				}
				this.setSessionAttribute("moduleCode", module.getModuleCode()
						.trim());
			} else {
				paraMap.put("length", "000");

				// by 张天明 如果点击“所有模块” 将当前模块置为M
				this.setSessionAttribute("moduleCode", "M");
			}

			if (module.getIsVisible() == null || module.getIsVisible() == 0) {
				paraMap.put("isVisible", 1);
			}
			if (module.getModuleName() != null) {
				paraMap.put("moduleName", module.getModuleName().trim() + "%");
			}
		} else {
			if (moduleCode != null) {
				paraMap.put("parentModuleCode", moduleCode + "%");
				paraMap.put("length", moduleCode+"00");
			} else {
				moduleCode = (String) this.getSessionAttribute("moduleCode");
				if (moduleCode != null) {
					paraMap.put("parentModuleCode", moduleCode + "%");
					paraMap.put("length", moduleCode+"00");
					this.setSessionAttribute("moduleCode", moduleCode);
				} else
					paraMap.put("length", "000");
			}
		}
		pagination = moduleService.findModuleByPage(pagination, paraMap);

		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(
				",\n");
		data.append("  \"rows\":\n");

		final String colNames = new String(
				"moduleName|moduleFullName|moduleCode|sortSq|moduleAddr|target|id|isVisible|");
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (colNames.indexOf(name + "|") != -1)
					return false;
				else
					return true;
			}
		});
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		data.append(json.toString());
		data.append("  \n").append("}");

		try {
			this.getResponse().getWriter().println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *返回模块角色设置页面
	 */
	public String editModuleRole(){
		if (moduleId != null) {
			module = moduleService.findModuleById(moduleId);
		}
		//setSessionAttribute("moduleRightList", module.getRightList());
		
		String permissionExpression = "module:" + module.getModuleCode();
		List<Permission> list = securityService.findPermission(null, null, 0, permissionExpression);
		setSessionAttribute("moduleRightList", list);
		setSessionAttribute("moduleId", moduleId);
		roleList = userRoleService.getAll();
		return SUCCESS;
	}
	
	
	/**
	 *保存模块角色设置
	 */
	public String saveModuleRight(){
		
		if(moduleId==null)
			moduleId=(String)this.getSessionAttribute("moduleId");
		
		Module module1 = moduleService.findModuleById(moduleId);
		String[] roleIds = roleCodes.split("\\|");
		securityService.saveModulePermissions(roleIds, module1.getModuleCode());
		EdpShiroFilterFactoryBean.SpringShiroFilter shiroFilter = (EdpShiroFilterFactoryBean.SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
		shiroFilter.updateFilterChainDefinitionMap();
		
		//修改模块后重新加载模块的权限设置
		if(SysPropertiesUtil.getBoolean("NEED_URL_AUTH", true))
			securityService.loadUrlAuth();
		
		return SUCCESS;

	}

	/**
	 * 显示管理端的模块树形菜单
	 * 
	 * @return String
	 */
	public String moduleTree() {
		String func = this.getRequestParameter("func");
		if(func!=null && func.equals("setModule"))
			return "setModule";
		else
			return SUCCESS;
	}

	/**
	 * 模块树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	public void findChildModule() {
		if (null == selModule) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "M");
			root.put("text", "所有模块");
			root.put("iconCls", "icon-treeNodeParent");
			List<Module> moduleList = moduleService
					.findChildModulesByModuleCode(selModule);
			if (moduleList.size() > 0)
				root.put("state", "opened");
			else
				root.put("state", "closed");
			List<Map<String, Object>> items = getChildren(selModule);
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			List<Map<String, Object>> items = getChildren(selModule);
			writerJSONArray(items);
		}

	}

	private List<Map<String, Object>> getChildren(String modulecode) {
		List<Module> moduleList = moduleService
				.findChildModulesByModuleCode(modulecode);
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (Module m : moduleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", m.getModuleCode());
			item.put("text", m.getModuleName());
			if (moduleService.findChildModulesByModuleCode(m.getModuleCode())
					.size() > 0) {
				item.put("state", "closed");
				item.put("iconCls", "icon-treeNodeParent");
			} else {
				item.put("iconCls", "icon-module");
			}

			if (roleId != null && !roleId.trim().equals("")) {
				
				List list = securityService.findStrictPermission(null, roleId, 0, "module:" + m.getModuleCode());
				if (list!=null && list.size()>0)
					item.put("checked", true);
			}

			items.add(item);
		}
		return items;
	}
	
//	/**
//	 * 左侧菜单树的显示
//	 * @param modulecode
//	 * @return
//	 */
//	public void findModuleTree() {
//		   List<Map<String, Object>> items = getChildrenForLeft(selModule);
//		   writerJSONArray(items);
//	}
//
//	private List<Map<String, Object>> getChildrenForLeft(String modulecode) {
//		//取得当前用户
//		User user=this.getLoginUser();
//		List<Module> moduleList = null;
//		if(user.getIsSuperAdmin()!=null && user.getIsSuperAdmin()){
//			
//			moduleList = moduleService.findChildModulesByModuleCode(modulecode);
//		}else{
//			Map<String, Object> paraMap = new HashMap<String, Object>();
//			paraMap.put("userId", user.getId());
//			paraMap.put("moduleCode", modulecode.trim() + "%");
//			paraMap.put("length", modulecode.length()+2);
//			moduleList = moduleService.findModuleForLeft(paraMap);
//		}
//		
//		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
//		for (Module m : moduleList) {
//			Map<String, Object> item = new HashMap<String, Object>();
//			item.put("id", m.getModuleCode());
//			item.put("text", m.getModuleName());
//			if (moduleService.findChildModulesByModuleCode(m.getModuleCode())
//					.size() > 0) {
//				item.put("state", "closed");
//			} else {
//				item.put("attributes", m.getModuleAddr());
//
//			}
//			if(m.getSmallIconAddr()!=null&&m.getSmallIconAddr().length()>0)
//				item.put("iconCls", "icon-"+m.getSmallIconAddr().trim().substring(0,m.getSmallIconAddr().trim().length()-4));
//			if (m.getTarget() == null || m.getTarget().equals("框架"))
//				item.put("data", "frm");
//			else
//				item.put("data","_blank");
//			
//			item.put("isAutoExpand", m.getIsAutoExpand()); //2013.3.6 李淮如 修改
//			
//			items.add(item);
//		}
//		return items;
//	}
	
	/**
	 * 模块树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	public void findModuleTreeForRight() {
		
		//roleId;
		
		//roleService.
		UserRole role = userRoleService.findRoleById(roleId);
		UserRole parentRole = role.getParentRole();
		UserRole findRoleById = userRoleService.findRoleById(roleId);
		
		String roleCode = findRoleById.getRoleCode();
		
		String substring = roleCode.substring(0,roleCode.length()-2);
		
		boolean needCheck = true;
		
		String rightIdList = "";
		
		if(substring.length()==1)
		{
			needCheck = false;
		}
		
		if(needCheck)
			
		{
		
			UserRole findRoleByCode = userRoleService.findRoleByCode(substring);
			
			if(findRoleByCode==null)
			{
				String[] roleIds2 = getLoginUser().getRoleIds();
				
				String string = roleIds2[0];
				
				findRoleByCode = userRoleService.findRoleById(string);
			}
			
			
			List<ModuleRight> findModuleRightByRole = moduleService.findModuleRightByRole(findRoleByCode.getId());
			
			for (ModuleRight moduleRight : findModuleRightByRole) {
				rightIdList+=moduleRight.getModule().getModuleCode()+",";
			}
		}
		
		else
		{
			rightIdList = "0";
		}
		User currUser = getLoginUser();
		if (null == selModule) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "M");
			root.put("text", "所有模块");
			root.put("iconCls", "icon-treeNodeParent");
			List<Module> moduleList = moduleService.findChildModulesByModuleCode(selModule);
			if (moduleList.size() > 0)
				root.put("state", "opened");
			else
				root.put("state", "closed");
			selModule="M";
			
			List<Map<String, Object>> items = null;
			if(parentRole==null){
				if(currUser.getIsSuperAdmin()){
					items = this.getChildren(selModule);
				}else
					items = getChildrenForRight(rightIdList,selModule);
			}else{
				items = getChildrenForRightByParent(parentRole.getId(), selModule);
			}
			
			
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			List<Map<String, Object>> items = null;
			if(parentRole==null){
				if(currUser.getIsSuperAdmin()){
					items = this.getChildren(selModule);
				}else
					items = getChildrenForRight(rightIdList,selModule);
			}else{
				items = getChildrenForRightByParent(parentRole.getId(), selModule);
			}
			
			writerJSONArray(items);
		}

	}
	
	private List<Map<String, Object>> getChildrenForRightByParent(String parentRoleId, String modulecode){
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roleId", parentRoleId);
		paraMap.put("moduleCode", modulecode.trim() + "%");
		if(modulecode.length()<3)
			paraMap.put("length", 3);
		else
			paraMap.put("length", (modulecode.length() + 2));
		List<Module> moduleList = moduleService.findModuleByRole(paraMap);
		for (Module m : moduleList) {
					
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", m.getModuleCode());
			item.put("text", m.getModuleName());
			if (moduleService.findChildModulesByModuleCode(m.getModuleCode())
					.size() > 0) {
				item.put("state", "closed");
				item.put("iconCls", "icon-treeNodeParent");
			} else {
				item.put("iconCls", "icon-module");
			}
			if (m.getModuleCode().equals(modulecode)
					|| m.getIsVisible() == 0)
				continue;
			paraMap = new HashMap<String, Object>();
			if (roleId != null && !roleId.trim().equals("")) {
				
				List list = securityService.findStrictPermission(null, roleId, 0, "module:" + m.getModuleCode());
				if (list!=null && list.size()>0)
					item.put("checked", true);
			}

			items.add(item);
		}
		//TODO
		return items;
	}

	private List<Map<String, Object>> getChildrenForRight(String rightIdList ,String modulecode) {
		User user=this.getLoginUser();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", user.getId());
		paraMap.put("moduleCode", modulecode.trim() + "%");
		if(modulecode.length()<3)
			paraMap.put("length", 3);
		else
			paraMap.put("length", (modulecode.length() + 2));
		List<Module> moduleList = moduleService.findModuleForLeft(paraMap);

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (Module m : moduleList) {
			
			/**
			if(!rightIdList.equals("0")&&!rightIdList.contains(m.getModuleCode()))
			{
				continue;
			}
			*/
			
			
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", m.getModuleCode());
			item.put("text", m.getModuleName());
			if (moduleService.findChildModulesByModuleCode(m.getModuleCode())
					.size() > 0) {
				item.put("state", "closed");
				item.put("iconCls", "icon-treeNodeParent");
			} else {
				item.put("iconCls", "icon-module");
			}
			if (m.getModuleCode().equals(modulecode)
					|| m.getIsVisible() == 0)
				continue;
			paraMap = new HashMap<String, Object>();
			if (roleId != null && !roleId.trim().equals("")) {
				/**
				UserRole role = userRoleService.findUserRoleById(roleId);
				paraMap.put("role", role);
				paraMap.put("module", m);
				List<Object> list = moduleService.findModuleRight(paraMap);
				Long cnt = Long.valueOf(list.get(0).toString());
				*/
				List list = securityService.findStrictPermission(null, roleId, 0, "module:" + m.getModuleCode());
				if (list!=null && list.size()>0)
					item.put("checked", true);
			}

			items.add(item);
		}
		return items;
	}

	

	/**
	 * 返回模块图片选择页面
	 * 
	 * @Title: chooseImg
	 * @Description:
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public String chooseImg() {
		String baseRealPath = this.getRequest().getRealPath("//");
		String filePath = baseRealPath + "//webResources//images//" + imageType
				+ "Icon";
		File imgFolder = new File(filePath);
		fileNames = imgFolder.list();
		return SUCCESS;
	}

	
	
	/**
	 * 查找移动终端的菜单 add by max
	 */
	public void findMobileModule(){
		if(selModule==null || "".equals(selModule)){
			selModule="M03";
		}
		List<Module> moduleList = moduleService.findChildModulesByModuleCode(selModule);
		List<Map<String, String>> modules=new ArrayList<Map<String, String>>();
		for(int i=0;i<moduleList.size();i++){
			Map<String, String> module=new HashMap<String,String>();
			Integer child=moduleService.findChildModulesByModuleCode(moduleList.get(i).getModuleCode()).size();
			module.put("code", moduleList.get(i).getModuleCode());
			module.put("name", moduleList.get(i).getModuleName());
			module.put("icon",moduleList.get(i).getSmallIconAddr());
			module.put("addr", moduleList.get(i).getModuleAddr());
			module.put("child", child.toString());
			modules.add(module);
		}
		
		try {
			JSONArray json = JSONArray.fromObject(modules);
			this.getResponse().getWriter().println(callback+"("+json.toString()+")");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @return the moduleCode
	 */
	public String getModuleCode() {
		return moduleCode;
	}

	/**
	 * @param moduleCode
	 *            the moduleCode to set
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**
	 * @return the imageType
	 */
	public String getImageType() {
		return imageType;
	}

	/**
	 * @param imageType
	 *            the imageType to set
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	/**
	 * @return the fileNames
	 */
	public String[] getFileNames() {
		return fileNames;
	}

	/**
	 * @param fileNames
	 *            the fileNames to set
	 */
	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	/**
	 * @return the roleIds
	 */
	public String[] getRoleIds() {
		return roleIds;
	}

	/**
	 * @param roleIds
	 *            the roleIds to set
	 */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the roleList
	 */
	public List<UserRole> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
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

	/**
	 * @return the valueList
	 */
	public List<CodeValue> getValueList() {
		return valueList;
	}

	/**
	 * @param valueList
	 *            the valueList to set
	 */
	public void setValueList(List<CodeValue> valueList) {
		this.valueList = valueList;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId
	 *            the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * @param codeId
	 *            the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getSelModule() {
		return selModule;
	}

	public void setSelModule(String selModule) {
		this.selModule = selModule;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String roleCodes) {
		this.roleCodes = roleCodes;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getParentModuleName() {
		return parentModuleName;
	}

	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}

	public String getParentModuleCode() {
		return parentModuleCode;
	}

	public void setParentModuleCode(String parentModuleCode) {
		this.parentModuleCode = parentModuleCode;
	}
}
