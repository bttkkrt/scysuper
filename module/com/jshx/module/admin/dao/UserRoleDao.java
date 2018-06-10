/**
 * 
 */
package com.jshx.module.admin.dao;

import java.util.List;
import java.util.Map;



import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.UserRole;

/**
 * @author f_cheng
 *
 */
public interface UserRoleDao extends BaseDao {
	
	/**
	 * 根据上层角色编码获得最大编码
	 * 
	 * @Title: getMaxModuleCodeByParent 
	 * @Description: 
	 * @param parentRoleCode
	 * @return Integer   
	 */
	public Integer getMaxModuleCodeByParent(String parentRoleCode);
	
	/**
	 * 通过主键查找用户权限
	 * 
	 * @param roleId
	 * @return
	 */
	public UserRole findUserRoleById(String roleId);
		
	/**
	 * 查找用户权限列表
	 * @param paraMap
	 * @return
	 */
	public List<UserRole> findUserRoleList(Map<String, Object> paraMap);
	
	/**
	 * 分页查找用户权限
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination findUserRoleByPage(Pagination page,Map<String, Object> paraMap);
	
	/**
	 * 查找此角色名的角色
	 * @param roleName
	 * @return
	 */
	public UserRole findUserRoleByName(String roleName);
	/**
	 * 查找用户权限列表
	 * @param paraMap
	 * @return
	 */
	public List<UserRole> findLeafRole(Map<String, Object> paraMap);
	
	/**
	 * 获取角色下的用户数
	 * @param ids
	 * @return
	 */
	public Integer cntUsersinRole(String[] ids);
}
