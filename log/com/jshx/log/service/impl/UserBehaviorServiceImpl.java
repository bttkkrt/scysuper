package com.jshx.log.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.dao.UserBehaviorDao;
import com.jshx.log.entity.UserBehavior;
import com.jshx.log.service.UserBehaviorService;

@Service("userBehaviorService")
public class UserBehaviorServiceImpl extends BaseServiceImpl implements UserBehaviorService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("userBehaviorDao")
	private UserBehaviorDao userBehaviorDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@Transactional(readOnly = true)
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return userBehaviorDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	@Transactional(readOnly = true)
	public UserBehavior getById(String id)
	{
		return userBehaviorDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(UserBehavior userBehavior)
	{
		userBehaviorDao.save(userBehavior);
		updateUserBehaviors();
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(UserBehavior userBehavior)
	{
		userBehaviorDao.update(userBehavior);
		updateUserBehaviors();
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List<?> list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List<?> objects=userBehaviorDao.findUserBehavior(paraMap);
		
		userBehaviorDao.removeAll(objects);
		updateUserBehaviors();
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    userBehaviorDao.deleteWithFlag(id);
			}
		}
		updateUserBehaviors();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public void updateUserBehaviors() {
		//清除缓存区
		behaviorMap.clear();
		List<UserBehavior> behaviorList = userBehaviorDao.findListBy(UserBehavior.class, "delFlag", 0);
		for(UserBehavior behavior : behaviorList){
			behaviorMap.put(behavior.getBehaviorUrl(), behavior);
		}
	}
}
