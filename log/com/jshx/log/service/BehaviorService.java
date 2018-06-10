package com.jshx.log.service;

import com.jshx.log.entity.UserBehaviorLog;

/**
 * 用户行为记录服务
 * 
 * @author Chenjian
 *
 */
public interface BehaviorService {
	
	public void logUserBehavior(UserBehaviorLog log);

}
