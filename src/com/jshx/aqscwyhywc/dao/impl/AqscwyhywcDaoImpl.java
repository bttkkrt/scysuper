package com.jshx.aqscwyhywc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscwyhywc.entity.Aqscwyhywc;
import com.jshx.aqscwyhywc.dao.AqscwyhywcDao;

@Component("aqscwyhywcDao")
public class AqscwyhywcDaoImpl extends BaseDaoImpl implements AqscwyhywcDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findAqscwyhywcByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAqscwyhywc(Map<String, Object> paraMap){
		return this.findListByHqlId("findAqscwyhywcByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscwyhywc getById(String id)
	{
		return (Aqscwyhywc)this.getObjectById(Aqscwyhywc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aqscwyhywc aqscwyhywc)
	{
		aqscwyhywc.setId(null);
		this.saveOrUpdateObject(aqscwyhywc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aqscwyhywc aqscwyhywc)
	{
		this.saveOrUpdateObject(aqscwyhywc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aqscwyhywc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aqscwyhywc aqscwyhywc = (Aqscwyhywc)this.getObjectById(Aqscwyhywc.class, id);
		aqscwyhywc.setDelFlag(1);
		this.saveObject(aqscwyhywc);
	}
}
