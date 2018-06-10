package com.jshx.lddbqk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.lddbqk.entity.LeaCla;
import com.jshx.lddbqk.dao.LeaClaDao;

@Component("leaClaDao")
public class LeaClaDaoImpl extends BaseDaoImpl implements LeaClaDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLeaClaByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLeaCla(Map<String, Object> paraMap){
		return this.findListByHqlId("findLeaClaByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LeaCla getById(String id)
	{
		return (LeaCla)this.getObjectById(LeaCla.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LeaCla leaCla)
	{
		leaCla.setId(null);
		this.saveOrUpdateObject(leaCla);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LeaCla leaCla)
	{
		this.saveOrUpdateObject(leaCla);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LeaCla.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LeaCla leaCla = (LeaCla)this.getObjectById(LeaCla.class, id);
		leaCla.setDelFlag(1);
		this.saveObject(leaCla);
	}
}
