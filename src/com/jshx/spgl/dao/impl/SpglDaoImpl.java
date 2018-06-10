package com.jshx.spgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.spgl.entity.Spgl;
import com.jshx.spgl.dao.SpglDao;

@Component("spglDao")
public class SpglDaoImpl extends BaseDaoImpl implements SpglDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSpglByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSpgl(Map<String, Object> paraMap){
		return this.findListByHqlId("findSpglByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Spgl getById(String id)
	{
		return (Spgl)this.getObjectById(Spgl.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Spgl spgl)
	{
		spgl.setId(null);
		this.saveOrUpdateObject(spgl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Spgl spgl)
	{
		this.saveOrUpdateObject(spgl);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Spgl.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Spgl spgl = (Spgl)this.getObjectById(Spgl.class, id);
		spgl.setDelFlag(1);
		this.saveObject(spgl);
	}
}
