package com.jshx.feedback.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.feedback.entity.Feedback;
import com.jshx.feedback.dao.FeedbackDao;

@Component("feedbackDao")
public class FeedbackDaoImpl extends BaseDaoImpl implements FeedbackDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findFeedbackByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findFeedback(Map<String, Object> paraMap){
		return this.findListByHqlId("findFeedbackByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Feedback getById(String id)
	{
		return (Feedback)this.getObjectById(Feedback.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Feedback feedback)
	{
		feedback.setId(null);
		this.saveOrUpdateObject(feedback);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Feedback feedback)
	{
		this.saveOrUpdateObject(feedback);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Feedback.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Feedback feedback = (Feedback)this.getObjectById(Feedback.class, id);
		feedback.setDelFlag(1);
		this.saveObject(feedback);
	}
}
