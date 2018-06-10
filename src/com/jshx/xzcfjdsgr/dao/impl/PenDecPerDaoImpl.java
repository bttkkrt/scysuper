package com.jshx.xzcfjdsgr.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xzcfjdsgr.entity.PenDecPer;
import com.jshx.xzcfjdsgr.dao.PenDecPerDao;

@Component("penDecPerDao")
public class PenDecPerDaoImpl extends BaseDaoImpl implements PenDecPerDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPenDecPerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PenDecPer> findPenDecPer(Map<String, Object> paraMap){
		return this.findListByHqlId("findPenDecPerByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PenDecPer getById(String id)
	{
		return (PenDecPer)this.getObjectById(PenDecPer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PenDecPer penDecPer)
	{
		penDecPer.setId(null);
		this.saveOrUpdateObject(penDecPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PenDecPer penDecPer)
	{
		this.saveOrUpdateObject(penDecPer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PenDecPer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PenDecPer penDecPer = (PenDecPer)this.getObjectById(PenDecPer.class, id);
		penDecPer.setDelFlag(1);
		this.saveObject(penDecPer);
	}
}
