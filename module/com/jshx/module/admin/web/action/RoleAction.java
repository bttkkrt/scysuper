/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-20         Carson.Yu          create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.ModuleRight;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.security.EdpShiroFilterFactoryBean;
import com.jshx.module.admin.service.ModuleService;
import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserRoleService;
import com.jshx.module.admin.service.UserService;

/**  
 * @author  Carson.Yu
 * @version 创建时间：2011-1-20 上午11:08:23  
 * 类说明  
 */

public class RoleAction extends BaseAction {

	private static final long serialVersionUID = -8394307072090817344L;
	
	@Autowired() 
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired() 
	@Qualifier("moduleService")
	private ModuleService moduleService;
	
	@Autowired() 
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired() 
	@Qualifier("securityServiceImpl")
	private SecurityService securityService;
	
	private Pagination pagination;
	
	private Module module;
	
	//private List<ModuleRight> rightList;
	private List<Permission> rightList;
	
	private List<UserRight> userRightList;
	
	private String roleId;
	
	private List<UserRole> roleList;
	
	private String moduleCodes;
	
	private String userIds;
	
	private String adminRoleId;
	
	private String deptCode;
	
	private List<User> users;
	
	private String userid;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * 返回模块角色设置页面
	 */
	@SuppressWarnings("unchecked")
	public String listModuleRight(){
		pagination = new Pagination(super.getRequest());
		pagination.setPageSize(100);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		
		pagination = userRoleService.listUserRoleByPage(pagination, paraMap);
		roleList = pagination.getListOfObject();
		if(roleId==null && roleList!=null &&roleList.size()>0 )
			roleId = roleList.get(0).getId();
		//TODO
		if(roleId!=null){
			//rightList = moduleService.findModuleRightByRole(roleId);
			rightList = securityService.findPermission(null, roleId, 0, "module:");
			if(rightList!=null && rightList.size()>0){
				for(Permission permission : rightList){
					try{
						String moduleCode = permission.getPermissionExpression().split(":")[1];
						Module module = moduleService.findModuleByModuleCode(moduleCode);
						permission.setModule(module);
					}catch(Exception e){
						
					}
				}
			}
		}
		return SUCCESS;
	}
	/**
	 * 保存用户角色设置
	 */
	@SuppressWarnings("unchecked")
	public String saveUserRight(){
		//先删除该角色下所有用户的权限
		if(roleId==null)
			roleId=(String)getSessionAttribute("roleId");
		userService.delByRole(roleId);
		
		//添加权限
		List<UserRight> list = (List<UserRight>) this.getSessionAttribute("userRightList");
		if(list!= null){
			for (int i = 0; i < list.size(); i++) {
				UserRight temp = (UserRight) list.get(i);
				UserRight ur = new UserRight();
				ur.setUser(temp.getUser());
				ur.setRole(temp.getRole());
				userService.saveRight(ur);
			}
			
		}
		return SUCCESS;
	}
	/**
	 * 返回用户角色设置页面
	 */
	public String listUserRight(){
		try {
			setSessionAttribute("roleId", roleId);
			
			User user = this.getLoginUser();
			if(roleId==null)
				roleId=(String)getSessionAttribute("roleId");
			if(roleId==null && roleList!=null && roleList.size()>0)
				roleId = roleList.get(0).getId();
			
			//userRightList = (List) getSessionAttribute("userRightList");
			if(userRightList==null){
				if(user.getIsSuperAdmin()!=null && user.getIsSuperAdmin())
					userRightList = userService.findByRole(roleId,null);
			}
			setSessionAttribute("userRightList", userRightList);
			if((deptCode==null || deptCode.equals("")) && !getLoginUser().getIsSuperAdmin())
				deptCode = getLoginUser().getDeptCode();
			users=userService.findAllUsersByDept(deptCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 对用户角色设置的列表进行临时添加处理，不做数据库保存
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveUserRole() {
		HttpServletResponse response = getResponse();
		if(roleId==null)
			roleId=(String)getSessionAttribute("roleId");
		try {
			List list = (List) this.getSessionAttribute("userRightList");
			
			int j = -1;
			if(list !=null){
				for (int i = 0; i < list.size(); i++) {
					UserRight ur = (UserRight) list.get(i);
					if (ur.getUser().getId().equals(userid)) {
						j = i;
						break;
					}
				}
			}
			
			if (list == null)
				list = new ArrayList();
			
			if (j == -1) {
				UserRight ur = new UserRight();
				ur.setUser(userService.findUserById(userid));
				ur.setRole(userRoleService.findRoleById(roleId));
				list.add(ur);
				this.setSessionAttribute("userRightList", list);
				response.getWriter().print(userid);
			} else {
				response.getWriter().print("-1");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	/**
	 * 对用户角色设置的列表进行临时删除处理，不做数据库保存
	 */
	@SuppressWarnings("rawtypes")
	public void delUserRole() {
		HttpServletResponse response = getResponse();
		try {
			List list = (List) this.getSessionAttribute("userRightList");
			if (list == null)
				list = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				UserRight ur = (UserRight) list.get(i);
				if (ur.getUser().getId().equals(userid)) {
					list.remove(ur);
					break;
				}
			}
			this.setSessionAttribute("userRightList", list);
			response.getWriter().print("S");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 保存模块角色设置
	 */
	public String saveModuleRight(){
		if(moduleCodes!=null||moduleCodes.trim().equals("")){
			String[] moduleIds = moduleCodes.split("\\|");
			securityService.saveModulePermissions(roleId, moduleIds);
			EdpShiroFilterFactoryBean.SpringShiroFilter shiroFilter = (EdpShiroFilterFactoryBean.SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
			shiroFilter.updateFilterChainDefinitionMap();
		}
		
		return SUCCESS;
	}

	public String index(){
		return SUCCESS;
	}
	
	public String listRole(){
		
		return LIST;
	}
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * @return the rightList
	 */
	public List<Permission> getRightList() {
		return rightList;
	}

	/**
	 * @param rightList the rightList to set
	 */
	public void setRightList(List<Permission> rightList) {
		this.rightList = rightList;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleList
	 */
	public List<UserRole> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the moduleCodes
	 */
	public String getModuleCodes() {
		return moduleCodes;
	}

	/**
	 * @param moduleCodes the moduleCodes to set
	 */
	public void setModuleCodes(String moduleCodes) {
		this.moduleCodes = moduleCodes;
	}

	/**
	 * @return the userRightList
	 */
	public List<UserRight> getUserRightList() {
		return userRightList;
	}

	/**
	 * @param userRightList the userRightList to set
	 */
	public void setUserRightList(List<UserRight> userRightList) {
		this.userRightList = userRightList;
	}

	/**
	 * @return the userIds
	 */
	public String getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the adminRoleId
	 */
	public String getAdminRoleId() {
		return adminRoleId;
	}

	/**
	 * @param adminRoleId the adminRoleId to set
	 */
	public void setAdminRoleId(String adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}	
}