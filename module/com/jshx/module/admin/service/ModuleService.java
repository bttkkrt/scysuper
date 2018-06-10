/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.ModuleRight;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-18 上午10:16:14  
 * 类说明  
 */
public interface ModuleService extends BaseService {
	/**
	 * 生成模块编号
	 * 
	 * @param parentModuleCode
	 * @return String   
	 */
	public String createModuleCode(String parentModuleCode);

	/**
	 * 根据模块编号查找模块
	 * 
	 * @param moduleCode
	 * @return Module 
	 */
	public Module findModuleByModuleCode(String moduleCode);
	
	/**
	 * 修改模块
	 * 
	 * @param module
	 * @param roleIds
	 * @return Module  
	 */
	public Module modify(Module module, String[] roleIds);
	
	/**
	 * 保存模块
	 *  
	 * @param module
	 * @param roleIds
	 * @return Module  
	 */
	public Module save(Module module, String[] roleIds);
	
	/**
	 * 根据上层模块编码查找下层模块
	 * 
	 * @param parentModuleCode
	 * @return List<Module>   
	 */
	public List<Module> findChildModulesByModuleCode(String parentModuleCode);
	
	/**
	 * 根据上层模块序号查找下层模块
	 * 
	 * @param parentModuleCode
	 * @return List<Module>   
	 */
	public List<Module> findChildModulesById(String parentModuleId);
	
	/**
	 * 激活被禁用的模块
	 * 
	 * @param id
	 * @return void  
	 */
	public Module activeModule(String id);
	
	/**
	 * 禁用模块
	 * 
	 * @param id
	 * @return void  
	 */
	public Module inactiveModule(String id);
	
	/**
	 * 根据模块序号查找
	 * 
	 * @param id
	 * @return Module  
	 */
	public Module findModuleById(String id);
	
	/**
	 * 分页形式查找模块
	 * 
	 * @param page
	 * @param paraMap
	 * @return Pagination 
	 */
	public Pagination findModuleByPage(Pagination page, Map<String, Object> paraMap);
		
	/**
	 * 根据角色查找模块权限
	 * 
	 * @param roleId
	 * @return List<ModuleRight> 
	 */
	public List<ModuleRight> findModuleRightByRole(String roleId);
	
	/**
	 * 根据角色删除模块权限
	 *  
	 * @param roleId
	 * @return void  
	 */
	public void delModuleRightByRole(String roleId);
	
	/**
	 * 保存模块权限
	 *  
	 * @param right
	 * @return void
	 */
	public ModuleRight saveRight(ModuleRight right);
	
	/**
	 * 查找上层模块
	 * 
	 * @param moduleCode
	 * @return List<Module> 
	 */
	public List<Module> findModuleTrace(String moduleCode);
		
	/**
	 * 判断模块编号是否已存在
	 * 
	 * @param moduleCode
	 * @return Boolean  
	 */
	public Boolean checkModuleCode(String moduleCode);
	
	/**
	 * 根据模块ID删除模块下的所有角色
	 * 
	 * @param paraMap
	 */
	public void deleteRoleByModule(Map<String, Object> paraMap);

	/**
	 * 为左侧菜单树
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<Module> findModuleForLeft(Map<String, Object> paraMap);
	/**
	 * 管理员权限全部菜单树
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<Module> findModuleForAdmin(Map<String, Object> paraMap);
	/**
	 * 查找某模块是否有某角色 
	 */
	public List findModuleRight(Map<String, Object> paraMap);
	/**
	 * 查找模块权限
	 */
	public List<ModuleRight> findRightForModule(Map<String, Object> paraMap);
	/**
	 * 查找快捷菜单
	 */
	public List findQuicklyStart(Map<String, Object> paraMap);
	
	/**
	 * 根据角色查找模块
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<Module> findModuleByRole(Map<String, Object> paraMap);
	
	public int findButton(String moduleCode, String[] roleIds, int num);
}
