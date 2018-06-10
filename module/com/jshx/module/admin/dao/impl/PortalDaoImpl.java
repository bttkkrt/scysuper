/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 25, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.PortalDao;
import com.jshx.module.admin.entity.Portal;
import com.jshx.module.admin.entity.User;
import com.jshx.module.mobile.version.entity.Version;

/**
 * @author john.zhang
 * @version 创建时间：Jan 25, 2011 2:39:35 PM 类说明
 */
@Component("portalDao")
public class PortalDaoImpl extends BaseDaoImpl implements PortalDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PortalDao#findPortalByUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Portal> findPortalByUser(User user) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		return findListByHqlId("findPortalByUser", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PortalDao#findAllProperty()
	 */
	@SuppressWarnings("unchecked")
	public List<Portal> findAllPotals() {
		return findListByHqlId("findPortalByUser", new HashMap<String, Object>());
	}	
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		this.removeObjectById(Portal.class, id);
	}
}
