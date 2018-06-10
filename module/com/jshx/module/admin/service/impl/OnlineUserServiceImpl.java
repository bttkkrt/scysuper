/**
 * 
 */
package com.jshx.module.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.OnlineUserDao;
import com.jshx.module.admin.entity.OnlineUser;
import com.jshx.module.admin.service.OnlineUserService;

/**
 * @author f_cheng
 *
 */
@Service("onlineUserService") 
public class OnlineUserServiceImpl implements OnlineUserService{
	 
	@Autowired() 
	@Qualifier("OnlineUserDao")
	private OnlineUserDao onlineUserDao;

	@Transactional
	public void save(OnlineUser user){
		onlineUserDao.saveOrUpdateObject(user);
	}
	
	@Transactional
	public void del(String sessionId){
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("sessionId", sessionId);
		OnlineUser user = (OnlineUser) onlineUserDao.findObjectByFieldsMap(OnlineUser.class, paraMap);
		if(null != user){
			onlineUserDao.removeObject(user);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OnlineUser> list(){
		return onlineUserDao.getAllObject(OnlineUser.class);
	}
	
	public Pagination findOnlineUserByPage(Pagination pagination){
		return onlineUserDao.findPageByHqlId("findOnlineUser",null, pagination);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void clear(){
		List<OnlineUser> list = onlineUserDao.getAllObject(OnlineUser.class);
		if(list.size()>0)
			onlineUserDao.removeAll(list);
	}
	
	public boolean isUserLogon(String userId){
		if(onlineUserDao.getObjectByProperty(OnlineUser.class, "user.id", userId)!=null){
			return true;
		}
		return false;
	}
	
	public OnlineUser getOnlineUserByUserId(String userId){
		if(onlineUserDao.getObjectByProperty(OnlineUser.class, "user.id", userId)!=null){
			return (OnlineUser) onlineUserDao.getObjectByProperty(OnlineUser.class, "user.id", userId);
		}
		return null;
	}
}
