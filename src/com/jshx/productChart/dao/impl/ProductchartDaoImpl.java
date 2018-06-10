package com.jshx.productChart.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.productChart.entity.Productchart;
import com.jshx.productChart.dao.ProductchartDao;

@Component("productchartDao")
public class ProductchartDaoImpl extends BaseDaoImpl implements ProductchartDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProductchartByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProductchart(Map<String, Object> paraMap){
		return this.findListByHqlId("findProductchartByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Productchart getById(String id)
	{
		return (Productchart)this.getObjectById(Productchart.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Productchart productchart)
	{
		productchart.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(productchart);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Productchart productchart)
	{
		this.saveOrUpdateObject(productchart);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Productchart.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Productchart productchart = (Productchart)this.getObjectById(Productchart.class, id);
		productchart.setDelFlag(1);
		this.saveObject(productchart);
	}
}
