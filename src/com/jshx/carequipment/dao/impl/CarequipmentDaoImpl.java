package com.jshx.carequipment.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.carequipment.entity.Carequipment;
import com.jshx.carequipment.dao.CarequipmentDao;

@Component("carequipmentDao")
public class CarequipmentDaoImpl extends BaseDaoImpl implements CarequipmentDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCarequipmentByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCarequipment(Map<String, Object> paraMap){
		return this.findListByHqlId("findCarequipmentByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Carequipment getById(String id)
	{
		return (Carequipment)this.getObjectById(Carequipment.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Carequipment carequipment)
	{
		carequipment.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(carequipment);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Carequipment carequipment)
	{
		this.saveOrUpdateObject(carequipment);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Carequipment.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Carequipment carequipment = (Carequipment)this.getObjectById(Carequipment.class, id);
		carequipment.setDelFlag(1);
		this.saveObject(carequipment);
	}
}
