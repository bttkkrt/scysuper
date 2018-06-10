package com.jshx.scxcgl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.scxcgl.entity.ProductionManage;
import com.jshx.scxcgl.dao.ProductionManageDao;

@Component("productionManageDao")
public class ProductionManageDaoImpl extends BaseDaoImpl implements ProductionManageDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProductionManageByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProductionManage(Map<String, Object> paraMap){
		return this.findListByHqlId("findProductionManageByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProductionManage getById(String id)
	{
		return (ProductionManage)this.getObjectById(ProductionManage.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProductionManage productionManage)
	{
		productionManage.setId(null);
		this.saveOrUpdateObject(productionManage);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProductionManage productionManage)
	{
		this.saveOrUpdateObject(productionManage);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProductionManage.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProductionManage productionManage = (ProductionManage)this.getObjectById(ProductionManage.class, id);
		productionManage.setDelFlag(1);
		this.saveObject(productionManage);
	}
}
