package com.jshx.zgfcyjs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zgfcyjs.entity.ReviewSubmission;
import com.jshx.zgfcyjs.dao.ReviewSubmissionDao;

@Component("reviewSubmissionDao")
public class ReviewSubmissionDaoImpl extends BaseDaoImpl implements ReviewSubmissionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findReviewSubmissionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<ReviewSubmission> findReviewSubmission(Map<String, Object> paraMap){
		return this.findListByHqlId("findReviewSubmissionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ReviewSubmission getById(String id)
	{
		return (ReviewSubmission)this.getObjectById(ReviewSubmission.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ReviewSubmission reviewSubmission)
	{
		reviewSubmission.setId(null);
		this.saveOrUpdateObject(reviewSubmission);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ReviewSubmission reviewSubmission)
	{
		this.saveOrUpdateObject(reviewSubmission);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ReviewSubmission.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ReviewSubmission reviewSubmission = (ReviewSubmission)this.getObjectById(ReviewSubmission.class, id);
		reviewSubmission.setDelFlag(1);
		this.saveObject(reviewSubmission);
	}
}
