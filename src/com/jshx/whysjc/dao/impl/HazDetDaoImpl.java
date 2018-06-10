package com.jshx.whysjc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whysjc.entity.HazDet;
import com.jshx.whysjc.dao.HazDetDao;

@Component("hazDetDao")
public class HazDetDaoImpl extends BaseDaoImpl implements HazDetDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHazDetByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findHazDet(Map<String, Object> paraMap){
		return this.findListByHqlId("findHazDetByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HazDet getById(String id)
	{
		return (HazDet)this.getObjectById(HazDet.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(HazDet hazDet)
	{
		hazDet.setId(null);
		this.saveOrUpdateObject(hazDet);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(HazDet hazDet)
	{
		this.saveOrUpdateObject(hazDet);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(HazDet.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		HazDet hazDet = (HazDet)this.getObjectById(HazDet.class, id);
		hazDet.setDelFlag(1);
		this.saveObject(hazDet);
	}
}
