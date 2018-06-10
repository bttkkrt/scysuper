package com.jshx.log.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.log.dao.UserBehaviorLogDao;
import com.jshx.log.entity.UserBehaviorLog;
import com.jshx.log.service.BehaviorService;



@Service("defaultBehaviorService")
public class DefaultUserBehaviorService implements BehaviorService {
	
	@Autowired() 
	@Qualifier("userBehaviorLogDao")
	private UserBehaviorLogDao userBehaviorLogDao;

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void logUserBehavior(UserBehaviorLog log) {
		userBehaviorLogDao.saveBaseModelObject(log);
	}

}
