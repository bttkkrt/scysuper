package com.jshx.activiti.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jshx.activiti.service.BusinessEntityService;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.module.admin.dao.UserDAO;

@Service("businessEntityService")
public class BusinessEntityServiceImpl extends BaseServiceImpl implements BusinessEntityService{
	@Autowired
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	@Transactional
	public void saveBusinessEntity(Object businessEntity) {
		userDAO.saveOrUpdateObject(businessEntity);
	}
	
	public Object getBusinessEntity(Class<?> clazz, String id){
		return userDAO.getObjectById(clazz, id);
	}
}
