package com.jshx.reviewLog.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.dao.ReviewLogDao;

@Component("reviewLogDao")
public class ReviewLogDaoImpl extends BaseDaoImpl implements ReviewLogDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findReviewLogByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findReviewLog(Map<String, Object> paraMap){
		return this.findListByHqlId("findReviewLogByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReviewLog getById(String id)
	{
		return (ReviewLog)this.getObjectById(ReviewLog.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ReviewLog reviewLog)
	{
		reviewLog.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(reviewLog);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ReviewLog reviewLog)
	{
		this.saveOrUpdateObject(reviewLog);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ReviewLog.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ReviewLog reviewLog = (ReviewLog)this.getObjectById(ReviewLog.class, id);
		reviewLog.setDelFlag(1);
		this.saveObject(reviewLog);
	}
}
