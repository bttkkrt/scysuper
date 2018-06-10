/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-24        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.dao.ModuleDao;
import com.jshx.module.admin.dao.ModuleRightDao;
import com.jshx.module.admin.dao.PermissionDao;
import com.jshx.module.admin.dao.QuicklyStartDao;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.dao.UserRightDao;
import com.jshx.module.admin.dao.UserRoleDao;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.QuicklyStart;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.SecurityService;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-24 下午03:28:22 类说明
 */
@Service("securityServiceImpl")
public class SecurityServiceImpl extends BaseServiceImpl implements
		SecurityService {

	@Autowired() 
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	@Autowired() 
	@Qualifier("moduleDao")
	private ModuleDao moduleDao;

	@Autowired() 
	@Qualifier("userRightDao")
	private UserRightDao userRightDao;

	@Autowired() 
	@Qualifier("quicklyStartDao")
	private QuicklyStartDao quicklyStartDao;

	@Autowired() 
	@Qualifier("moduleRightDao")
	private ModuleRightDao moduleRightDao;

	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private UserRoleDao userRoleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.SecurityService#findMyModule(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Module> findMyModule(User user) {
		List<Module> list = null;
		if(user.getIsSuperAdmin()!=null && user.getIsSuperAdmin()){
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("isVisible",1);
			paraMap.put("length","M00");
			list = moduleDao.findListByHqlId("searchModule", paraMap);
		}else{
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("userId",user.getId());
			list = moduleDao.findListByHqlId("findModules", paraMap);
		}
		
		return list;
	}

	// public List<Department> findDataAccessAuth(String userId) {
	// Map<String, Object> paraMap = new HashMap<String, Object>();
	// User user = userDAO.findUserById(userId);
	// Department department = (Department) user.getDept();
	// List<Department> deptList = new ArrayList<Department>();
	// paraMap.put("userId", user.getId());
	// List<UserRole> roleList = userRightDao.findUserRole(paraMap);
	// boolean lowerFlag = false;
	// boolean equalFlag = false;
	// for (UserRole role : roleList) {
	// if (Constants.ROLE_DEPT_ACCESS_LOWER_TRUE.equals(role.getIsLower())) {
	// lowerFlag = true;
	// }
	// if (Constants.ROLE_DEPT_ACCESS_EQUAL_TRUE.equals(role
	// .getAccessControl())) {
	// equalFlag = true;
	// }
	// }
	// if (lowerFlag && equalFlag) {
	// deptList = getLowerDepartment(department.getDeptCode());
	// if (null == deptList || 0 != deptList.size()) {
	// deptList = getEqualDepartment(department);
	// } else {
	// deptList.addAll(getEqualDepartment(department));
	// }
	// } else if (lowerFlag) {
	// deptList = getLowerDepartment(department.getDeptCode());
	// } else if (equalFlag) {
	// deptList = getEqualDepartment(department);
	// }
	// return deptList;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.SecurityService#findMyModuleCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<String> findMyModuleCode(String userId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId.trim());
		return moduleDao.findListByHqlId("findMyModuleCodes", paraMap);
	}
	
	public List<UserRole> findUserRole(Map<String, Object> paraMap) {
		return userRightDao.findUserRole(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.QuicklyStartService#findQuicklyStartTree(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<QuicklyStart> findQuicklyStartTree(String userId) {
		User user = userDAO.findUserById(userId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		List<QuicklyStart> startList = quicklyStartDao.findListByHqlId(
				"queryQuickStart", paraMap);
		return startList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.QuicklyStartService#findQuicklyStartTree(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<QuicklyStart> findQuicklyStart(Map<String, Object> param) {
		List<QuicklyStart> startList = quicklyStartDao.findListByHqlId(
				"queryQuickStart", param);
		return startList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#loadUrlAuth()
	 */
	@Transactional(readOnly = true)
	public void loadUrlAuth() {
		//修改模块与角色的对照表
		List<Permission> permissionList = this.findPermission(null, null, 0, "{module:");
		for(Permission permission : permissionList){
			UserRole role = permission.getRole();
			String permissionExpression = permission.getPermissionExpression();
			permissionExpression = permissionExpression.replace("{module:", "");
			permissionExpression = permissionExpression.replace("}", "");
			Module module = (Module)moduleRightDao.getObjectByProperty(Module.class, "moduleCode", permissionExpression);
			if(module!=null){
				String roles = Constants.RESOURCE_AUTH.get(module.getModuleAddr());
				if (roles == null || roles.trim().equals("")) {
					Constants.RESOURCE_AUTH.put(module.getModuleAddr(), role.getRoleName());
				} else {
					roles += role.getRoleName() + ",";
					Constants.RESOURCE_AUTH.put(module.getModuleAddr(), roles);
				}
			}			
		}
		
		/**
		List<ModuleRight> rightList = moduleRightDao
				.findListByHql("from ModuleRight");
		for (ModuleRight right : rightList) {
			Module module = right.getModule();
			if (module.getIsPublic() == 1)
				continue;
			UserRole role = right.getRole();
			String roles = Constants.RESOURCE_AUTH.get(module.getModuleAddr());
			if (roles == null || roles.trim().equals("")) {
				Constants.RESOURCE_AUTH.put(module.getModuleAddr(), role
						.getId());
			} else {
				roles += role.getId() + ",";
				Constants.RESOURCE_AUTH.put(module.getModuleAddr(), roles);
			}
		}
		if (SysPropertiesUtil.getBoolean("DEFAULT_URL_AUTH", true)) {
			List<Module> moduleList = moduleDao
					.findModuleByList(new HashMap<String, Object>());
			for (Module module : moduleList) {
				if (module.getIsPublic() == 1)
					continue;
				String roles = Constants.RESOURCE_AUTH.get(module
						.getModuleAddr());
				if (roles == null || roles.trim().equals("")) {
					Constants.RESOURCE_AUTH.put(module.getModuleAddr(),
							SysPropertiesUtil.getProperty("SUPER_ADMIN"));
				}
			}
		}
		
		Iterator<String> keyIt = Constants.RESOURCE_AUTH.keySet().iterator();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			String roles = Constants.RESOURCE_AUTH.get(key);
		}
		*/
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#savePermission(com.jshx.module.admin.entity.Permission)
	 */
	@Override
	@Transactional
	public Permission savePermission(Permission permission) {
		permission = permissionDao.savePermission(permission);
		
		return permission;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#updatePermission(com.jshx.module.admin.entity.Permission)
	 */
	@Override
	@Transactional
	public Permission updatePermission(Permission permission) {
		permission = permissionDao.updatePermission(permission);
		
		return permission;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#delPermission(com.jshx.module.admin.entity.Permission)
	 */
	@Override
	@Transactional
	public void delPermission(Permission permission) {
		permissionDao.delPermission(permission);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#findPermissionByUser(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NESTED)
	public List<Permission> findPermission(String userId, String roleId, Integer permissionType, String permissionExpression) {
		return permissionDao.findPermission(userId, roleId, permissionType, permissionExpression);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#saveModulePermissions(java.lang.String[], java.lang.String)
	 */
	@Override
	@Transactional
	public void saveModulePermissions(String[] roleIds, String moduleCode) {
		
		if(roleIds!=null && roleIds.length>0){
			String permissionExpression = "module:" + moduleCode;
			
			permissionDao.removePermission(null, null, 0, permissionExpression);
			UserRole role;
			for(String roleId : roleIds){
				Permission permission = new Permission();
				role = (UserRole)userRoleDao.getObjectByProperty(UserRole.class, "roleCode", roleId);
				if(role==null)
					continue;
				permission.setId(null);
				permission.setRoleId(role.getId());
				permission.setPermissionType(0);
				permission.setPermissionExpression(permissionExpression);
				permissionDao.savePermission(permission);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#saveModulePermissions(java.lang.String, java.lang.String[])
	 */
	@Override
	@Transactional
	public void saveModulePermissions(String roleId, String[] moduleCodes){
		if(moduleCodes!=null && moduleCodes.length>0){
			String permissionExpression = "module:";
			
			permissionDao.removePermission(null, roleId, 0, permissionExpression);
			//UserRole role;
			for(String moduleCode : moduleCodes){
				if(moduleCode.length()==0 || moduleCode.trim().equals(""))
					continue;
				Permission permission = new Permission();
				//role = (UserRole)userRoleDao.getObjectByProperty(UserRole.class, "roleCode", roleId);
				//if(role==null)
				//	continue;
				permissionExpression = "module:" + moduleCode;
				permission.setId(null);
				permission.setRoleId(roleId);
				permission.setPermissionType(0);
				permission.setPermissionExpression(permissionExpression);
				permissionDao.savePermission(permission);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#saveFuncPermissions(java.lang.String[], java.lang.String)
	 */
	@Override
	@Transactional
	public void saveFuncPermissions(String[] roleIds, String permissionExpression) {
		
		if(roleIds!=null && roleIds.length>0){
			permissionDao.removePermission(null, null, 1, permissionExpression);
			UserRole role;
			for(String roleId : roleIds){
				Permission permission = new Permission();
				role = (UserRole)userRoleDao.getObjectByProperty(UserRole.class, "roleCode", roleId);
				if(role==null)
					continue;
				permission.setId(null);
				permission.setRoleId(role.getId());
				permission.setPermissionType(1);
				permission.setPermissionExpression(permissionExpression);
				permissionDao.savePermission(permission);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#saveFuncPermissionsByUsers(java.lang.String[], java.lang.String)
	 */
	@Override
	@Transactional
	public void saveFuncPermissionsByUsers(String[] userIds, String permissionExpression) {
		
		if(userIds!=null && userIds.length>0){
			permissionDao.removePermission(null, null, 2, permissionExpression);
			for(String userId : userIds){
				Permission permission = new Permission();
				
				permission.setId(null);
				permission.setUserId(userId);
				permission.setPermissionType(2);
				permission.setPermissionExpression(permissionExpression);
				permissionDao.savePermission(permission);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.SecurityService#getFilterChainDefinitionFromDb(org.apache.shiro.config.Ini.Section)
	 */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	public Ini.Section getFilterChainDefinitionFromDb(Ini.Section section){
    	List<Module> moduleList = moduleDao.findListBy(Module.class, "isVisible", 1);
    	for(Module module : moduleList){
    		if(module.getIsPublic()==1)
    			continue;
    		if(module.getModuleAddr()==null || module.getModuleAddr().trim().equals("") || module.getModuleAddr().startsWith("http:"))
    			continue;
    		section.put("/"+module.getModuleAddr()+"*", "authc, roleOrFilter[系统管理员]");
    	}
    	List<Permission> list = findPermission(null, null, null, null);
    	for(Permission permission : list){
    		if(permission.getPermissionType()==0){
    			//模块权限
    			String permissionExpression = permission.getPermissionExpression();
    			if(permissionExpression.indexOf(":")!=-1){
    				
    				String[] ptemp = permissionExpression.split(":");
    				if(ptemp.length<2){
    					continue;
    				}
    				String moduleCode = ptemp[1];
    				
    				Module module = (Module)moduleDao.getObjectByProperty(Module.class, "moduleCode", moduleCode);
    				if(module==null || module.getIsPublic()==1)
    					continue;
    				if(module.getModuleAddr()==null || module.getModuleAddr().trim().equals("") || module.getModuleAddr().startsWith("http:"))
    	    			continue;
    				String perm = section.get("/"+module.getModuleAddr()+"*");
    				
    				if(perm==null){
    					perm = "authc";
    					if(permission.getRole()!=null)
    						perm += ", roleOrFilter[" + permission.getRole().getRoleName() + "]";
    					//perm += ", perms[" + permissionExpression + "]";
    				}else{
    					if(permission.getRoleId()!=null){
    						UserRole role = userRoleDao.findUserRoleById(permission.getRoleId());
    						if(perm.indexOf("roleOrFilter[")!=-1){
    							perm = perm.replace("roleOrFilter[", "roleOrFilter[" + role.getRoleName() + ",");
    						}else{
    							perm += ", roleOrFilter[" + role.getRoleName() + "]";
    						}
    					}
    					/**
    					if(perm.indexOf("perms[")!=-1){
							perm = perm.replace("perms[", "perms[" + permissionExpression + ",");
						}else{
							perm += ", perms[" + permissionExpression + "]";
						}
						*/
    				}
    				section.put("/"+module.getModuleAddr()+"*", perm);
    			}
    		}else if(permission.getPermissionType()==1){
    			//功能点权限
    			
    		}
        	//TODO 扩展其他资源权限
    	}
    	return section;
    }

	@Override
	public List<Permission> findStrictPermission(String userId, String roleId,
			Integer permissionType, String permissionExpression) {
		return permissionDao.findStrictPermission(userId, roleId, permissionType, permissionExpression);
	}
}
