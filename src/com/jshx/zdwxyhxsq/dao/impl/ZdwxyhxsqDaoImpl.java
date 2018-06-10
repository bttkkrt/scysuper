package com.jshx.zdwxyhxsq.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxyhxsq.entity.Zdwxyhxsq;
import com.jshx.zdwxyhxsq.dao.ZdwxyhxsqDao;

@Component("zdwxyhxsqDao")
public class ZdwxyhxsqDaoImpl extends BaseDaoImpl implements ZdwxyhxsqDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZdwxyhxsqByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZdwxyhxsq(Map<String, Object> paraMap){
		return this.findListByHqlId("findZdwxyhxsqByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxyhxsq getById(String id)
	{
		return (Zdwxyhxsq)this.getObjectById(Zdwxyhxsq.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zdwxyhxsq zdwxyhxsq)
	{
		zdwxyhxsq.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zdwxyhxsq);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zdwxyhxsq zdwxyhxsq)
	{
		this.saveOrUpdateObject(zdwxyhxsq);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zdwxyhxsq.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zdwxyhxsq zdwxyhxsq = (Zdwxyhxsq)this.getObjectById(Zdwxyhxsq.class, id);
		zdwxyhxsq.setDelFlag(1);
		this.saveObject(zdwxyhxsq);
	}
}
