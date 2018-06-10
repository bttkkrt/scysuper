package com.jshx.gpdb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gpdb.dao.GpdbDao;
import com.jshx.gpdb.entity.Gpdb;

@Component("gpdbDao")
public class GpdbDaoImpl extends BaseDaoImpl implements GpdbDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findGpdbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGpdb(Map<String, Object> paraMap){
		return this.findListByHqlId("findGpdbByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gpdb getById(String id)
	{
		return (Gpdb)this.getObjectById(Gpdb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Gpdb gpdb)
	{
		gpdb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(gpdb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Gpdb gpdb)
	{
		this.saveOrUpdateObject(gpdb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Gpdb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Gpdb gpdb = (Gpdb)this.getObjectById(Gpdb.class, id);
		gpdb.setDelFlag(1);
		this.saveObject(gpdb);
	}
}
