/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-22        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.ModuleRight;
import com.jshx.module.admin.entity.UserRole;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-22 下午06:47:32  
 * 类说明  
 */
public interface ModuleRightDao extends BaseDao {
	
	/**
	 * 查找某模块的权限
	 * 
	 * @Title: findRightByModule 
	 * @Description: 
	 * @param user
	 * @return List<ModuleRight> 
	 */
	public List<ModuleRight> findRightByModule(Module module);
	
	/**
	 * 查找某角色的模块权限
	 * 
	 * @Title: findRightByRole 
	 * @Description: 
	 * @param role
	 * @return List<ModuleRight>   
	 */
	public List<ModuleRight> findRightByRole(UserRole role);
	
	/**
	 * 根据角色删除某块权限
	 * 
	 * @Title: removeByRole 
	 * @Description: 
	 * @param role
	 * @return void   
	 */
	public void removeByRole(UserRole role);
    
	/**
	 * 模块管理中角色设置中的角色树
	 */
	public List<ModuleRight> findRightForModule(Map<String, Object> paraMap);
}
