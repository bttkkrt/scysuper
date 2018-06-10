package com.jshx.sccjgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sccjgl.entity.Workshop;
import com.jshx.sccjgl.dao.WorkshopDao;

@Component("workshopDao")
public class WorkshopDaoImpl extends BaseDaoImpl implements WorkshopDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWorkshopByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWorkshop(Map<String, Object> paraMap){
		return this.findListByHqlId("findWorkshopByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Workshop getById(String id)
	{
		return (Workshop)this.getObjectById(Workshop.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Workshop workshop)
	{
		workshop.setId(null);
		this.saveOrUpdateObject(workshop);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Workshop workshop)
	{
		this.saveOrUpdateObject(workshop);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Workshop.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Workshop workshop = (Workshop)this.getObjectById(Workshop.class, id);
		workshop.setDelFlag(1);
		this.saveObject(workshop);
	}
}
