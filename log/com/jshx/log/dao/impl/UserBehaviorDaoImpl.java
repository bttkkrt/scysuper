package com.jshx.log.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.dao.UserBehaviorDao;
import com.jshx.log.entity.UserBehavior;

@Component("userBehaviorDao")
public class UserBehaviorDaoImpl extends BaseDaoImpl implements UserBehaviorDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findUserBehaviorByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 *
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@SuppressWarnings("unchecked")
	public List<UserBehavior> findUserBehavior(Map<String, Object> paraMap){
		return this.findListByHqlId("findUserBehaviorByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public UserBehavior getById(String id)
	{
		return (UserBehavior)this.getObjectById(UserBehavior.class, id);
	}

	/**
	 * 保存信息
	 * @param userBehavior 信息
	 */
	public void save(UserBehavior userBehavior)
	{
		userBehavior.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(userBehavior);
	}

	/**
	 * 修改信息
	 * @param userBehavior 信息
	 */
	public void update(UserBehavior userBehavior)
	{
		this.saveOrUpdateObject(userBehavior);
	}

	/**
	 * 物理删除信息
	 * @param id 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(UserBehavior.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param id 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		UserBehavior userBehavior = (UserBehavior)this.getObjectById(UserBehavior.class, id);
		userBehavior.setDelFlag(1);
		this.saveObject(userBehavior);
	}
}
