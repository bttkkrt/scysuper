package com.jshx.ndzybfzjf.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ndzybfzjf.entity.OccDisPre;
import com.jshx.ndzybfzjf.dao.OccDisPreDao;

@Component("occDisPreDao")
public class OccDisPreDaoImpl extends BaseDaoImpl implements OccDisPreDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccDisPreByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccDisPre(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccDisPreByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccDisPre getById(String id)
	{
		return (OccDisPre)this.getObjectById(OccDisPre.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccDisPre occDisPre)
	{
		occDisPre.setId(null);
		this.saveOrUpdateObject(occDisPre);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccDisPre occDisPre)
	{
		this.saveOrUpdateObject(occDisPre);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccDisPre.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccDisPre occDisPre = (OccDisPre)this.getObjectById(OccDisPre.class, id);
		occDisPre.setDelFlag(1);
		this.saveObject(occDisPre);
	}
}
