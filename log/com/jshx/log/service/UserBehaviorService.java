package com.jshx.log.service;

import java.util.HashMap;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.log.entity.UserBehavior;

public interface UserBehaviorService extends BaseService
{
	/** 用户行为缓存区 */
	public static Map<String, UserBehavior> behaviorMap = new HashMap<String, UserBehavior>();

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public UserBehavior getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(UserBehavior model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(UserBehavior model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteWithFlag(String ids);
	
	/**
	 * 获得所有的用户行为，并更新用户行为缓存区
	 * 
	 * @return
	 */
	public void updateUserBehaviors();
}
