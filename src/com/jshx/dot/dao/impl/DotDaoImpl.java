package com.jshx.dot.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dot.entity.Dot;
import com.jshx.dot.dao.DotDao;

@Component("dotDao")
public class DotDaoImpl extends BaseDaoImpl implements DotDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDotByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDot(Map<String, Object> paraMap){
		return this.findListByHqlId("findDotByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dot getById(String id)
	{
		return (Dot)this.getObjectById(Dot.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dot dot)
	{
		dot.setId(null);
		this.saveOrUpdateObject(dot);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dot dot)
	{
		this.saveOrUpdateObject(dot);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dot.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dot dot = (Dot)this.getObjectById(Dot.class, id);
		dot.setDelFlag(1);
		this.saveObject(dot);
	}
}
