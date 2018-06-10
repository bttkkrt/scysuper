package com.jshx.inwentorydecision.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.inwentorydecision.entity.InventoryDecision;
import com.jshx.inwentorydecision.dao.InventoryDecisionDao;

@Component("inventoryDecisionDao")
public class InventoryDecisionDaoImpl extends BaseDaoImpl implements InventoryDecisionDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findInventoryDecisionByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<InventoryDecision> findInventoryDecision(Map<String, Object> paraMap){
		return this.findListByHqlId("findInventoryDecisionByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public InventoryDecision getById(String id)
	{
		return (InventoryDecision)this.getObjectById(InventoryDecision.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(InventoryDecision inventoryDecision)
	{
		inventoryDecision.setId(null);
		this.saveOrUpdateObject(inventoryDecision);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(InventoryDecision inventoryDecision)
	{
		this.saveOrUpdateObject(inventoryDecision);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(InventoryDecision.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		InventoryDecision inventoryDecision = (InventoryDecision)this.getObjectById(InventoryDecision.class, id);
		inventoryDecision.setDelFlag(1);
		this.saveObject(inventoryDecision);
	}
}
