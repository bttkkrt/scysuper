package com.jshx.jsxmzybfhssba.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jsxmzybfhssba.entity.ProComNot;
import com.jshx.jsxmzybfhssba.dao.ProComNotDao;

@Component("proComNotDao")
public class ProComNotDaoImpl extends BaseDaoImpl implements ProComNotDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProComNotByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProComNot(Map<String, Object> paraMap){
		return this.findListByHqlId("findProComNotByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProComNot getById(String id)
	{
		return (ProComNot)this.getObjectById(ProComNot.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProComNot proComNot)
	{
		proComNot.setId(null);
		this.saveOrUpdateObject(proComNot);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProComNot proComNot)
	{
		this.saveOrUpdateObject(proComNot);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProComNot.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProComNot proComNot = (ProComNot)this.getObjectById(ProComNot.class, id);
		proComNot.setDelFlag(1);
		this.saveObject(proComNot);
	}
}
