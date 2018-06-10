package com.jshx.inspectItem.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.inspectItem.entity.InspectItem;
import com.jshx.inspectItem.dao.InspectItemDao;

@Component("inspectItemDao")
public class InspectItemDaoImpl extends BaseDaoImpl implements InspectItemDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findInspectItemByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findInspectItem(Map<String, Object> paraMap){
		return this.findListByHqlId("findInspectItemByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InspectItem getById(String id)
	{
		return (InspectItem)this.getObjectById(InspectItem.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InspectItem inspectItem)
	{
		inspectItem.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(inspectItem);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InspectItem inspectItem)
	{
		this.saveOrUpdateObject(inspectItem);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InspectItem.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InspectItem inspectItem = (InspectItem)this.getObjectById(InspectItem.class, id);
		inspectItem.setDelFlag(1);
		this.saveObject(inspectItem);
	}
}
