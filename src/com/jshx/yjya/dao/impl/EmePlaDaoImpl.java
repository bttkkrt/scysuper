package com.jshx.yjya.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yjya.entity.EmePla;
import com.jshx.yjya.dao.EmePlaDao;

@Component("emePlaDao")
public class EmePlaDaoImpl extends BaseDaoImpl implements EmePlaDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEmePlaByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEmePla(Map<String, Object> paraMap){
		return this.findListByHqlId("findEmePlaByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EmePla getById(String id)
	{
		return (EmePla)this.getObjectById(EmePla.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EmePla emePla)
	{
		emePla.setId(null);
		this.saveOrUpdateObject(emePla);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EmePla emePla)
	{
		this.saveOrUpdateObject(emePla);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EmePla.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EmePla emePla = (EmePla)this.getObjectById(EmePla.class, id);
		emePla.setDelFlag(1);
		this.saveObject(emePla);
	}
}
