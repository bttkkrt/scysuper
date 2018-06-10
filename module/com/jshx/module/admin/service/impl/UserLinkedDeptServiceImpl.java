package com.jshx.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.module.admin.dao.UserLinkedDeptDao;
import com.jshx.module.admin.entity.UserLinkedDept;
import com.jshx.module.admin.service.UserLinkedDeptService;
/**
 * 
 * @author Chenjian
 * @see com.jshx.module.admin.service.UserLinkedDeptService
 *
 */
@Service("userLinkedDeptService")
public class UserLinkedDeptServiceImpl extends BaseServiceImpl implements
		UserLinkedDeptService {
	
	@Autowired() 
	@Qualifier("userLinkedDeptDao")
	private UserLinkedDeptDao userLinkedDeptDao;

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserLinkedDeptService#saveLinkedDept(java.lang.String, java.util.List)
	 */
	@Override
	@Transactional
	public void saveLinkedDept(String userId, String[] linkedDeptIds) {
		userLinkedDeptDao.saveLinkedDept(userId, linkedDeptIds);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserLinkedDeptService#getLinkedDeptByUser(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserLinkedDept> getLinkedDeptByUser(String userId) {
		return userLinkedDeptDao.getLinkedDeptByUser(userId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserLinkedDeptService#getLinkedDeptByUser(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserLinkedDept> getLinkedDeptByUser(String userId, String deptCode){
		return userLinkedDeptDao.getLinkedDeptByUser(userId, deptCode);
	}
		

}
