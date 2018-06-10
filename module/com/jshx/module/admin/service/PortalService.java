/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 25, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.service;   

import java.util.List;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.PasonnelPortal;
import com.jshx.module.admin.entity.Portal;

/**  
 * @author   john.zhang
 * @version 创建时间：Jan 25, 2011 2:50:14 PM  
 * 类说明  
 */
public interface PortalService {

	/**
	 * 删除Portal
	 * 
	 * @param id   
	 */
	public void deletePortal(String id);

	/**
	 * 保存Portal
	 *  
	 * @param entity
	 * @param roleIds
	 */
	public Portal savePortal(Portal entity, String[] roleIds) ;
	
	/**
	 * 查找所有的Portal
	 * 
	 * @return List<Portal>   
	 */
	public List<Portal> getAllPortals();
	
	/**
	 * 查找个人设置的Portal
	 * 
	 * @param userCode
	 * @return List<PasonnelPortal> 
	 */
	public PasonnelPortal findMyPortal(String userCode);
	
	/**
	 * 根据序号查找Portal
	 *  
	 * @param id
	 * @return Portal 
	 */
	public Portal getPortalById(String id);
	
	/**
	 * 删除个人Portal
	 *  
	 * @param id
	 */
	public void delPasonnelPortal(String id);
	
	/**
	 * 保存个人Portal
	 * 
	 * @param myPortal
	 * @return PasonnelPortal 
	 */
	public PasonnelPortal savePasonnelPortal(PasonnelPortal myPortal);
	
	/**
	 * 根据序号查找个人设置的Portal
	 * 
	 * @param id
	 * @return
	 * @return PasonnelPortal   
	 * @throws
	 */
	public PasonnelPortal findPasonnelPortalById(String id);
	
	/**
	 * 根据用户的角色查找Portal
	 * 
	 * @param userId
	 * @return List<Portal>  
	 */
	public List<Portal> findPortalByUser(String userId);
	
	/**
	 * 分页查找页面布局
	 * 
	 * @return Pagination 
	 */
	public Pagination findPortalByPage(Pagination page);
	/**
	 * 逻辑删除个人Portal
	 *  
	 * @param id
	 */
	public void deleteWithFlag(String ids);

}
   


