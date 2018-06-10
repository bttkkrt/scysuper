package com.jshx.pxjcxx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.pxjcxx.entity.TraInf;
import com.jshx.pxjcxx.dao.TraInfDao;

@Component("traInfDao")
public class TraInfDaoImpl extends BaseDaoImpl implements TraInfDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findTraInfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTraInf(Map<String, Object> paraMap){
		return this.findListByHqlId("findTraInfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TraInf getById(String id)
	{
		return (TraInf)this.getObjectById(TraInf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TraInf traInf)
	{
		traInf.setId(null);
		this.saveOrUpdateObject(traInf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TraInf traInf)
	{
		this.saveOrUpdateObject(traInf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(TraInf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		TraInf traInf = (TraInf)this.getObjectById(TraInf.class, id);
		traInf.setDelFlag(1);
		this.saveObject(traInf);
	}
}
