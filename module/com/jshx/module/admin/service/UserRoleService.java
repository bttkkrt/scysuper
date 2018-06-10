package com.jshx.module.admin.service;

import java.util.List;
import java.util.Map;


import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;

public interface UserRoleService extends BaseService {
	
	public List<UserRole> getRoleByUser(String roleCode, String roleName,User user);
	
	/**
	 * 得到所有的用户角色
	 * @return
	 */
	public List<UserRole> getAll();
	
	/**
	 * 判断角色名是否已经注册
	 * 
	 * @param id
	 * @param roleName
	 * @param roleCode
	 * 
	 * @return
	 */
	public Integer isReg(String id, String roleName, String roleCode);
	
	/**
	 * 新增用户角色
	 * @param userRole
	 */
	public UserRole save(UserRole userRole);
	
	/**
	 * 通过Id删除用户角色
	 * @param userRoleId
	 */
	public void delete(String userRoleId);
	
	/**
	 * 通过Id批量删除用户角色
	 * @param roleIds
	 */
	public void deletes(String[] roleIds);
	
	/**
	 * 修改用户角色
	 * @param userRole
	 */
	public UserRole modify(UserRole userRole);
	
	/**
	 * 分页获得所有用户角色
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination listUserRoleByPage(Pagination page,
			Map<String, Object> paraMap);
	
	/**
	 * 根据序号查找角色
	 *  
	 * @param id
	 * @return UserRole  
	 */
	public UserRole findRoleById(String id);
	
	/**
	 * 查找权限
	 * 
	 * @param isLower
	 * @return List<UserRole> 
	 */
	public List<UserRole> findRole(Integer isLower);
		
	/**
	 * 生成角色编号
	 * 
	 * @param parentRoleCode
	 * @return String   
	 */
	public String createRoleCode(String parentRoleCode);
	
	/**
	 * 根据编号查找角色
	 * 
	 * @param roleCode
	 * @return UserRole   
	 */
	public UserRole findRoleByCode(String roleCode);

	/**
	 * 根据roleCode查找最底层的角色
	 * 
	 * @param roleType
	 * @param selNode
	 * @return
	 */
	public List<UserRole> findLeafRole(String roleType, String selNode);
	
	/**
	 * ext树换成easyui树，用户管理中角色设置，显示所有角色
	 */
	public List findUserRoleList(Map<String, Object> paraMap);
	
	/**
	 * 根据条件查找角色
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<UserRole> findUserRole(Map<String, Object> paraMap); 
	
	/**
	 * 根据角色ID查找角色
	 * 
	 * @param roleId
	 * @return
	 */
	public UserRole findUserRoleById(String roleId);
	
	/**
	 * 根据角色类型、roleCode、角色名称以及创建者查找角色
	 * 
	 * @param roleType
	 * @param roleCode
	 * @param roleName
	 * @param user
	 * @return
	 */
	public List<UserRole> getRoleByUserForList(String roleType, String roleCode,String roleName, User user);
	
	/**
	 * 获取角色下的用户数
	 * @param ids
	 * @return
	 */
	public Integer cntUsersinRole(String[] ids);
}	
