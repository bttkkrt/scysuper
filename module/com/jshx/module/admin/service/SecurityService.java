/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-24        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.config.Ini;

import com.jshx.core.base.service.BaseService;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.QuicklyStart;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-24 下午03:26:14 权限控制Service
 */
public interface SecurityService extends BaseService {

	/**
	 * 查找当前用户可以查看的模块
	 * 
	 * @param user
	 * @return List<Module>
	 */
	public List<Module> findMyModule(User user);

	/**
	 * 获得数据访问权限
	 * 
	 * @param userId
	 * @return
	 * @return List
	 * @throws
	 */
//	List findDataAccessAuth(String userId);

	/**
	 * 查找用户所能访问的所有模块的编码
	 * 
	 * @param userId
	 * @return List<String>
	 */
	public List<String> findMyModuleCode(String userId);

	/**
	 * 获得用户角色
	 * 
	 * @param paraMap
	 * @return List
	 * @throws
	 */
	List<UserRole> findUserRole(Map<String, Object> paraMap);

	/**
	 * 查找快捷菜单，返回树型节点
	 * 
	 * @param param
	 * @return List
	 */
	public List<QuicklyStart> findQuicklyStart(Map<String, Object> param);
	
	/**
	 * 配置模块与角色间的映射
	 * 
	 */
	public void loadUrlAuth();
	
	/**
	 * 保存角色可访问的模块权限
	 * 
	 * @param roleIds
	 * @param moduleCode
	 */
	public void saveModulePermissions(String[] roleIds, String moduleCode);
	
	/**
	 * 保存角色可访问的模块权限
	 * 
	 * @param roleId
	 * @param moduleCodes
	 */
	public void saveModulePermissions(String roleId, String[] moduleCodes);
	
	/**
	 * 保存权限
	 * 
	 * @param permission
	 * @return Permission
	 */
	public Permission savePermission(Permission permission);
	
	/**
	 * 修改权限
	 * 
	 * @param permission
	 * @return Permission
	 */
	public Permission updatePermission(Permission permission);
	
	/**
	 * 删除权限
	 * 
	 * @param permission
	 */
	public void delPermission(Permission permission);
	
	/**
	 * 查找权限
	 * 
	 * @param userId 用户ID
	 * @param roleId 角色ID
	 * @param permissionType 权限资源类型
	 * @param permissionExpression 权限表达式
	 * @return List<Permission>
	 */
	public List<Permission> findPermission(String userId, String roleId, Integer permissionType, String permissionExpression);
	
	/**
	 * 从数据库中获取权限
	 * 
	 * @param section
	 * @return
	 */
	public Ini.Section getFilterChainDefinitionFromDb(Ini.Section section);
	
	/**
	 * 根据角色保存功能点的权限
	 * 
	 * @param roleIds
	 * @param permissionExpression
	 */
	public void saveFuncPermissions(String[] roleIds, String permissionExpression);
	
	/**
	 * 根据用户保存功能点的权限
	 * 
	 * @param userIds
	 * @param permissionExpression
	 */
	public void saveFuncPermissionsByUsers(String[] userIds, String permissionExpression);
	
	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @param permissionType
	 * @param permissionExpression
	 * @return
	 */
	public List<Permission> findStrictPermission(String userId, String roleId, Integer permissionType, String permissionExpression);
}
