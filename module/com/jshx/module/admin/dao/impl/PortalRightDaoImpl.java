/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.PortalRightDao;
import com.jshx.module.admin.entity.Portal;
import com.jshx.module.admin.entity.PortalRight;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午10:25:43  
 * 类说明  
 */
@Component("portalRightDao")
public class PortalRightDaoImpl extends BaseDaoImpl implements PortalRightDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PortalRightDao#delRightByPortal(com.jshx.module.admin.entity.Portal)
	 */
	public void delRightByPortal(Portal portal) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("portal", portal);
		executeUpdateByHqlId("delRightByPortal", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PortalRightDao#findRightByPortal(com.jshx.module.admin.entity.Portal)
	 */
	@SuppressWarnings("unchecked")
	public List<PortalRight> findRightByPortal(Portal portal) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("portal", portal);
		return super.findListByHqlId("findRightByPortal", paraMap);
	}

}
