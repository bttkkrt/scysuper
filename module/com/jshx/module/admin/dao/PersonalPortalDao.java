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
import com.jshx.module.admin.entity.PasonnelPortal;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午11:00:56  
 * 个人Portal的DAO
 */
public interface PersonalPortalDao extends BaseDao {
	
	/**
	 * 
	 * 
	 * @Title: findMyPortal 
	 * @Description: 
	 * @param userCode
	 * @return List<PersonalPortal>   
	 */
	public List<PasonnelPortal> findMyPortal(String userCode);

}
