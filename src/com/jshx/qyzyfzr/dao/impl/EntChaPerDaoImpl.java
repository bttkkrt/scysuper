package com.jshx.qyzyfzr.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzr.dao.EntChaPerDao;

@Component("entChaPerDao")
public class EntChaPerDaoImpl extends BaseDaoImpl implements EntChaPerDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEntChaPerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEntChaPer(Map<String, Object> paraMap){
		return this.findListByHqlId("findEntChaPerByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EntChaPer getById(String id)
	{
		return (EntChaPer)this.getObjectById(EntChaPer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EntChaPer entChaPer)
	{
		entChaPer.setId(null);
		this.saveOrUpdateObject(entChaPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EntChaPer entChaPer)
	{
		this.saveOrUpdateObject(entChaPer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EntChaPer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EntChaPer entChaPer = (EntChaPer)this.getObjectById(EntChaPer.class, id);
		entChaPer.setDelFlag(1);
		this.saveObject(entChaPer);
	}
}
