package com.jshx.zxzzjh.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zxzzjh.entity.RecPla;
import com.jshx.zxzzjh.dao.RecPlaDao;

@Component("recPlaDao")
public class RecPlaDaoImpl extends BaseDaoImpl implements RecPlaDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findRecPlaByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRecPla(Map<String, Object> paraMap){
		return this.findListByHqlId("findRecPlaByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RecPla getById(String id)
	{
		return (RecPla)this.getObjectById(RecPla.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(RecPla recPla)
	{
		recPla.setId(null);
		this.saveOrUpdateObject(recPla);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(RecPla recPla)
	{
		this.saveOrUpdateObject(recPla);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(RecPla.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		RecPla recPla = (RecPla)this.getObjectById(RecPla.class, id);
		recPla.setDelFlag(1);
		this.saveObject(recPla);
	}
}
