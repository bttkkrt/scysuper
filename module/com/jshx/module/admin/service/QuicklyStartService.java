/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-11        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.List;

import com.jshx.core.base.service.BaseService;
import com.jshx.module.admin.entity.QuicklyStart;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-3-11 下午03:50:32  
 * 类说明  
 */
public interface QuicklyStartService extends BaseService {
	
	/**
	 * 保存快捷菜单
	 * 
	 * @param userId
	 * @param moduleIds
	 * @return List<QuickStart>
	 */
	public List<QuicklyStart> saveQuicklyStart(String userId, String[] moduleIds);
	
	/**
	 * 删除用户的快捷菜单
	 * 
	 * @param userId
	 * @return void   
	 */
	public void delQuicklyStartByUser(String userId);
	
	/**
	 * 查询用户的快捷菜单
	 * 
	 * @param userId
	 * @return List<QuicklyStart> 
	 */
	public List<QuicklyStart> findQuicklyStart(String userId);
	
	/**
	 * 删除传入的id对应的快捷方式
	 * @param qsidList
	 */
	public void deleteQSbyIDs(List<String> qsidList);

}
