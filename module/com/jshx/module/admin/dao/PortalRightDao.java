/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.util.List;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.module.admin.entity.Portal;
import com.jshx.module.admin.entity.PortalRight;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午10:16:17  
 * 类说明  
 */
public interface PortalRightDao extends BaseDao {
	
	/**
	 * 根据Portal查找权限
	 * 
	 * @Title: findRightByPortal 
	 * @Description: 
	 * @param portal
	 * @return List<PortalRight>   
	 */
	public List<PortalRight> findRightByPortal(Portal portal);
	
	/**
	 * 根据Portal删除权限
	 * 
	 * @Title: delRightByPortal 
	 * @Description: 
	 * @param portal
	 * @return void  
	 */
	public void delRightByPortal(Portal portal);

}
