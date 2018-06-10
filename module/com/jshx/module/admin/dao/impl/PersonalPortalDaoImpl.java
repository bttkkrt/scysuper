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
import com.jshx.module.admin.dao.PersonalPortalDao;
import com.jshx.module.admin.entity.PasonnelPortal;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-18 上午11:06:06  
 * 类说明  
 */
@Component("personalPortalDao")
public class PersonalPortalDaoImpl extends BaseDaoImpl implements
		PersonalPortalDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.PersonalPortalDao#findMyPortal(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<PasonnelPortal> findMyPortal(String userCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userCode", userCode);
		return super.findListByHqlId("findMyPortal", paraMap);
	}

}
