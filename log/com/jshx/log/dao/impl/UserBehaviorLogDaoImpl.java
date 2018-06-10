package com.jshx.log.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.dao.UserBehaviorLogDao;
import com.jshx.log.entity.UserBehaviorLog;

@Component("userBehaviorLogDao")
public class UserBehaviorLogDaoImpl extends BaseDaoImpl implements UserBehaviorLogDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findUserBehaviorLogByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * 
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@SuppressWarnings("unchecked")
	public List<UserBehaviorLog> findUserBehaviorLog(Map<String, Object> paraMap){
		return this.findListByHqlId("findUserBehaviorLogByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public UserBehaviorLog getById(String id)
	{
		return (UserBehaviorLog)this.getObjectById(UserBehaviorLog.class, id);
	}

	/**
	 * 保存信息
	 * @param userBehaviorLog 信息
	 */
	public void save(UserBehaviorLog userBehaviorLog)
	{
		userBehaviorLog.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(userBehaviorLog);
	}

	/**
	 * 修改信息
	 * @param userBehaviorLog 信息
	 */
	public void update(UserBehaviorLog userBehaviorLog)
	{
		this.saveOrUpdateObject(userBehaviorLog);
	}

	/**
	 * 物理删除信息
	 * @param id 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(UserBehaviorLog.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param id 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		UserBehaviorLog userBehaviorLog = (UserBehaviorLog)this.getObjectById(UserBehaviorLog.class, id);
		userBehaviorLog.setDelFlag(1);
		this.saveObject(userBehaviorLog);
	}
}
