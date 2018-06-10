package com.jshx.zdwxyczgc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxyczgc.entity.ComOpeRul;
import com.jshx.zdwxyczgc.dao.ComOpeRulDao;

@Component("comOpeRulDao")
public class ComOpeRulDaoImpl extends BaseDaoImpl implements ComOpeRulDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findComOpeRulByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findComOpeRul(Map<String, Object> paraMap){
		return this.findListByHqlId("findComOpeRulByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ComOpeRul getById(String id)
	{
		return (ComOpeRul)this.getObjectById(ComOpeRul.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ComOpeRul comOpeRul)
	{
		comOpeRul.setId(null);
		this.saveOrUpdateObject(comOpeRul);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ComOpeRul comOpeRul)
	{
		this.saveOrUpdateObject(comOpeRul);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ComOpeRul.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ComOpeRul comOpeRul = (ComOpeRul)this.getObjectById(ComOpeRul.class, id);
		comOpeRul.setDelFlag(1);
		this.saveObject(comOpeRul);
	}
}
