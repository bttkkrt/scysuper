package com.jshx.jdwts.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdwts.entity.IdentifyAttorney;
import com.jshx.jdwts.dao.IdentifyAttorneyDao;

@Component("identifyAttorneyDao")
public class IdentifyAttorneyDaoImpl extends BaseDaoImpl implements IdentifyAttorneyDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findIdentifyAttorneyByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<IdentifyAttorney> findIdentifyAttorney(Map<String, Object> paraMap){
		return this.findListByHqlId("findIdentifyAttorneyByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public IdentifyAttorney getById(String id)
	{
		return (IdentifyAttorney)this.getObjectById(IdentifyAttorney.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(IdentifyAttorney identifyAttorney)
	{
		identifyAttorney.setId(null);
		this.saveOrUpdateObject(identifyAttorney);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(IdentifyAttorney identifyAttorney)
	{
		this.saveOrUpdateObject(identifyAttorney);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(IdentifyAttorney.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		IdentifyAttorney identifyAttorney = (IdentifyAttorney)this.getObjectById(IdentifyAttorney.class, id);
		identifyAttorney.setDelFlag(1);
		this.saveObject(identifyAttorney);
	}
}
