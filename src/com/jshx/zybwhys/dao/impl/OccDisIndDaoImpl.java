package com.jshx.zybwhys.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybwhys.entity.OccDisInd;
import com.jshx.zybwhys.dao.OccDisIndDao;

@Component("occDisIndDao")
public class OccDisIndDaoImpl extends BaseDaoImpl implements OccDisIndDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccDisIndByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccDisInd(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccDisIndByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccDisInd getById(String id)
	{
		return (OccDisInd)this.getObjectById(OccDisInd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccDisInd occDisInd)
	{
		occDisInd.setId(null);
		this.saveOrUpdateObject(occDisInd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccDisInd occDisInd)
	{
		this.saveOrUpdateObject(occDisInd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccDisInd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccDisInd occDisInd = (OccDisInd)this.getObjectById(OccDisInd.class, id);
		occDisInd.setDelFlag(1);
		this.saveObject(occDisInd);
	}
}
