package com.jshx.zybwhysfbqk.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybwhysfbqk.entity.OccDis;
import com.jshx.zybwhysfbqk.dao.OccDisDao;

@Component("occDisDao")
public class OccDisDaoImpl extends BaseDaoImpl implements OccDisDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccDisByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccDis(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccDisByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccDis getById(String id)
	{
		return (OccDis)this.getObjectById(OccDis.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccDis occDis)
	{
		occDis.setId(null);
		this.saveOrUpdateObject(occDis);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccDis occDis)
	{
		this.saveOrUpdateObject(occDis);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccDis.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccDis occDis = (OccDis)this.getObjectById(OccDis.class, id);
		occDis.setDelFlag(1);
		this.saveObject(occDis);
	}
}
