package com.jshx.docReview.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.docReview.dao.DocReviewDao;
import com.jshx.docReview.entity.DocReview;
@Transactional
@Component("docReviewDao")
public class DocReviewDaoImpl extends BaseDaoImpl implements DocReviewDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDocReviewByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDocReview(Map<String, Object> paraMap){
		return this.findListByHqlId("findDocReviewByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DocReview getById(String id)
	{
		return (DocReview)this.getObjectById(DocReview.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DocReview docReview)
	{
		docReview.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(docReview);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DocReview docReview)
	{
		getHibernateTemplate().setFlushMode(2);
		this.saveOrUpdateObject(docReview);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DocReview.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DocReview docReview = (DocReview)this.getObjectById(DocReview.class, id);
		docReview.setDelFlag(1);
		this.saveObject(docReview);
	}
}
