package com.jshx.distributeItem.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.dao.DistributeItemDao;

@Component("distributeItemDao")
public class DistributeItemDaoImpl extends BaseDaoImpl implements DistributeItemDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		paraMap.put("delFlag", 0);//过滤逻辑删除 GY-UPDATE 2015-01-07
		return this.findPageByHqlId("findDistributeItemByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDistributeItem(Map<String, Object> paraMap){
		paraMap.put("delFlag", 0);//过滤逻辑删除 GY-UPDATE 2015-01-07
		return this.findListByHqlId("findDistributeItemByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DistributeItem getById(String id)
	{
		return (DistributeItem)this.getObjectById(DistributeItem.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(DistributeItem distributeItem)
	{
		distributeItem.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(distributeItem);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(DistributeItem distributeItem)
	{
		this.saveOrUpdateObject(distributeItem);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(DistributeItem.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		DistributeItem distributeItem = (DistributeItem)this.getObjectById(DistributeItem.class, id);
		distributeItem.setDelFlag(1);
		this.saveObject(distributeItem);
	}

	/**
	 *	查找所有数据，包含已逻辑删除数据 
	 */
	@Override
	public List<DistributeItem> findAllDistributeItem(Map<String, Object> paraMap)
	{
		return this.findListByHqlId("findDistributeItemByMap", paraMap);
	}
}
