package com.jshx.wpwxxzsk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wpwxxzsk.entity.RisKnoBas;
import com.jshx.wpwxxzsk.dao.RisKnoBasDao;

@Component("risKnoBasDao")
public class RisKnoBasDaoImpl extends BaseDaoImpl implements RisKnoBasDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findRisKnoBasByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRisKnoBas(Map<String, Object> paraMap){
		return this.findListByHqlId("findRisKnoBasByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RisKnoBas getById(String id)
	{
		return (RisKnoBas)this.getObjectById(RisKnoBas.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(RisKnoBas risKnoBas)
	{
		risKnoBas.setId(null);
		this.saveOrUpdateObject(risKnoBas);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(RisKnoBas risKnoBas)
	{
		this.saveOrUpdateObject(risKnoBas);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(RisKnoBas.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		RisKnoBas risKnoBas = (RisKnoBas)this.getObjectById(RisKnoBas.class, id);
		risKnoBas.setDelFlag(1);
		this.saveObject(risKnoBas);
	}
}
