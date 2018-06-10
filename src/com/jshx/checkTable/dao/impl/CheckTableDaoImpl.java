package com.jshx.checkTable.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.checkTable.entity.CheckTable;
import com.jshx.checkTable.dao.CheckTableDao;

@Component("checkTableDao")
public class CheckTableDaoImpl extends BaseDaoImpl implements CheckTableDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheckTableByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheckTable(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheckTableByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheckTable getById(String id)
	{
		return (CheckTable)this.getObjectById(CheckTable.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheckTable checkTable)
	{
		checkTable.setId(null);
		this.saveOrUpdateObject(checkTable);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheckTable checkTable)
	{
		this.saveOrUpdateObject(checkTable);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheckTable.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheckTable checkTable = (CheckTable)this.getObjectById(CheckTable.class, id);
		checkTable.setDelFlag(1);
		this.saveObject(checkTable);
	}
}
