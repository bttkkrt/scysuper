package com.jshx.qyzyfzrlzqk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyzyfzrlzqk.entity.MaiPerRep;
import com.jshx.qyzyfzrlzqk.dao.MaiPerRepDao;

@Component("maiPerRepDao")
public class MaiPerRepDaoImpl extends BaseDaoImpl implements MaiPerRepDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMaiPerRepByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMaiPerRep(Map<String, Object> paraMap){
		return this.findListByHqlId("findMaiPerRepByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MaiPerRep getById(String id)
	{
		return (MaiPerRep)this.getObjectById(MaiPerRep.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MaiPerRep maiPerRep)
	{
		maiPerRep.setId(null);
		this.saveOrUpdateObject(maiPerRep);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MaiPerRep maiPerRep)
	{
		this.saveOrUpdateObject(maiPerRep);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MaiPerRep.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MaiPerRep maiPerRep = (MaiPerRep)this.getObjectById(MaiPerRep.class, id);
		maiPerRep.setDelFlag(1);
		this.saveObject(maiPerRep);
	}
}
