package com.jshx.jshxJf.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jshxJf.entity.Jshxjf;
import com.jshx.jshxJf.dao.JshxjfDao;

@Component("jshxjfDao")
public class JshxjfDaoImpl extends BaseDaoImpl implements JshxjfDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findJshxjfByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findJshxjf(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxjfByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Jshxjf getById(String id)
	{
		return (Jshxjf)this.getObjectById(Jshxjf.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Jshxjf jshxjf)
	{
		jshxjf.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxjf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Jshxjf jshxjf)
	{
		this.saveOrUpdateObject(jshxjf);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Jshxjf.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Jshxjf jshxjf = (Jshxjf)this.getObjectById(Jshxjf.class, id);
		jshxjf.setDelFlag(1);
		this.saveObject(jshxjf);
	}
}
