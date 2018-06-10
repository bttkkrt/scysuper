/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-11        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.QuicklyStartDao;
import com.jshx.module.admin.entity.User;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-3-11 下午03:28:59  
 * 类说明  
 */
@Component("quicklyStartDao")
public class QuicklyStartDaoImpl extends BaseDaoImpl implements QuicklyStartDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.QuickStartDao#findByUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<QuicklyStartDao> findByUser(User user) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		return super.findListByHqlId("queryQuickStart", paraMap);
	}

}
