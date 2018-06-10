package com.jshx.xcclcsjds.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xcclcsjds.entity.LiveActionDecision;
import com.jshx.xcclcsjds.dao.LiveActionDecisionDao;

@Component("liveActionDecisionDao")
public class LiveActionDecisionDaoImpl extends BaseDaoImpl implements LiveActionDecisionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLiveActionDecisionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<LiveActionDecision> findLiveActionDecision(Map<String, Object> paraMap){
		return this.findListByHqlId("findLiveActionDecisionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LiveActionDecision getById(String id)
	{
		return (LiveActionDecision)this.getObjectById(LiveActionDecision.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LiveActionDecision liveActionDecision)
	{
		liveActionDecision.setId(null);
		this.saveOrUpdateObject(liveActionDecision);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LiveActionDecision liveActionDecision)
	{
		this.saveOrUpdateObject(liveActionDecision);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LiveActionDecision.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LiveActionDecision liveActionDecision = (LiveActionDecision)this.getObjectById(LiveActionDecision.class, id);
		liveActionDecision.setDelFlag(1);
		this.saveObject(liveActionDecision);
	}
}
