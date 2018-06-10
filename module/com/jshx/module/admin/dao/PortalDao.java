/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 25, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.dao;   

import java.util.List;
import com.jshx.core.base.dao.BaseDao;
import com.jshx.module.admin.entity.Portal;
import com.jshx.module.admin.entity.User;

/**  
 * @author   john.zhang
 * @version 创建时间：Jan 25, 2011 2:38:46 PM  
 * 类说明  
 */
public interface PortalDao extends BaseDao{
	
	/**
	 * 根据用户的角色查找Portal
	 * 
	 * @Title: findPortalByUser 
	 * @Description: 
	 * @param user
	 * @return
	 * @return List<Portal>   
	 * @throws
	 */
	public List<Portal> findPortalByUser(User user);
	
	/**
	 * 查找所有的Portal
	 * 
	 * @Title: findAllPotals 
	 * @Description: 
	 * @return List<Portal>  
	 */
	public List<Portal> findAllPotals();
	/**
	 * 逻辑删除Portal
	 */
	public void deleteWithFlag(String id);
	
}

   


