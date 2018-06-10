package com.jshx.module.admin.dao;

import java.util.List;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.module.admin.entity.Permission;
/**
 * 权限DAO
 * 
 * @author Chenjian
 *
 */
public interface PermissionDao extends BaseDao {
	
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
	 * 删除权限
	 * 
	 * @param userId 用户ID
	 * @param roleId 角色ID
	 * @param permissionType 权限资源类型
	 * @param permissionExpression 权限表达式
	 */
	public void removePermission(String userId, String roleId, Integer permissionType, String permissionExpression);

	public List<Permission> findStrictPermission(String userId, String roleId,
			Integer permissionType, String permissionExpression);
}
