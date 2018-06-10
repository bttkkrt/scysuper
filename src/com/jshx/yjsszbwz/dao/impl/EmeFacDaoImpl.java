package com.jshx.yjsszbwz.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yjsszbwz.entity.EmeFac;
import com.jshx.yjsszbwz.dao.EmeFacDao;

@Component("emeFacDao")
public class EmeFacDaoImpl extends BaseDaoImpl implements EmeFacDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEmeFacByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findEmeFac(Map<String, Object> paraMap){
		return this.findListByHqlId("findEmeFacByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EmeFac getById(String id)
	{
		return (EmeFac)this.getObjectById(EmeFac.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EmeFac emeFac)
	{
		emeFac.setId(null);
		this.saveOrUpdateObject(emeFac);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EmeFac emeFac)
	{
		this.saveOrUpdateObject(emeFac);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EmeFac.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EmeFac emeFac = (EmeFac)this.getObjectById(EmeFac.class, id);
		emeFac.setDelFlag(1);
		this.saveObject(emeFac);
	}
}
