package com.jshx.mobile.customer.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.mobile.customer.entity.Customer;
import com.jshx.mobile.customer.dao.CustomerDao;

@Component("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCustomerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCustomer(Map<String, Object> paraMap){
		return this.findListByHqlId("findCustomerByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Customer getById(String id)
	{
		return (Customer)this.getObjectById(Customer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Customer customer)
	{
		customer.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(customer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Customer customer)
	{
		this.saveOrUpdateObject(customer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Customer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Customer customer = (Customer)this.getObjectById(Customer.class, id);
		customer.setDelFlag(1);
		this.saveObject(customer);
	}
}
