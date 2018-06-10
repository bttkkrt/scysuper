/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-11        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.module.admin.dao.ModuleDao;
import com.jshx.module.admin.dao.QuicklyStartDao;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.QuicklyStart;
import com.jshx.module.admin.entity.User;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-3-11 下午03:56:05  
 * 类说明  
 */
@Service("quicklyStartService")
public class QuicklyStartServiceImpl extends BaseServiceImpl implements
		com.jshx.module.admin.service.QuicklyStartService {
	
	@Autowired() 
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;

	@Autowired() 
	@Qualifier("moduleDao")
	private ModuleDao moduleDao;
	
	@Autowired() 
	@Qualifier("quicklyStartDao")
	private QuicklyStartDao quicklyStartDao;
	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.QuickStartService#delQuicklyStartByUser(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public void delQuicklyStartByUser(String userId) {
		User user = userDAO.findUserById(userId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		quicklyStartDao.executeUpdateByHqlId("delQuickStart", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.QuickStartService#saveQuicklyStart(java.lang.String, java.util.List)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public List<QuicklyStart> saveQuicklyStart(String userId,
			String[] moduleIds) {
		User user = userDAO.findUserById(userId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		quicklyStartDao.executeUpdateByHqlId("delQuickStart", paraMap);
		List<QuicklyStart> startList = new ArrayList<QuicklyStart>();
		
		for(String moduleId : moduleIds){
			if(moduleId==null || moduleId.trim().equals(""))
				continue;
			Module module = moduleDao.findById(moduleId);
			QuicklyStart quickStart = new QuicklyStart();
			quickStart.setModule(module);
			quickStart.setUser(user);
			quicklyStartDao.saveBaseModelObject(quickStart);
			startList.add(quickStart);
		}
		return startList;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.QuicklyStartService#findQuicklyStart(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<QuicklyStart> findQuicklyStart(String userId) {
		User user = userDAO.findUserById(userId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		return quicklyStartDao.findListByHqlId("queryQuickStart", paraMap);
	}
	
	

	/**
	 * 删除传入的id对应的快捷方式
	 * @param qsidList
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public void deleteQSbyIDs(List<String> qsidList)
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("useridlist", qsidList);
		List<QuicklyStart> qsList =(List<QuicklyStart>) quicklyStartDao.findListByHqlId("queryQuickStart", paraMap);
		quicklyStartDao.removeAll(qsList);
		
	}
}
