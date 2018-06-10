/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-11        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.List;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.module.admin.entity.User;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-3-11 下午03:24:26  
 * 类说明  
 */
public interface QuicklyStartDao extends BaseDao {
	
	/**
	 * 根据用户查找快捷菜单
	 * 
	 * @Title: findByUser 
	 * @Description: 
	 * @param userId   用户
	 * @return List<QuickStartDao>  
	 */
	public List<QuicklyStartDao> findByUser(User user);

}
