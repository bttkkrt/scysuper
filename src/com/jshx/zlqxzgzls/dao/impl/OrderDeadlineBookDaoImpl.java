package com.jshx.zlqxzgzls.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zlqxzgzls.entity.OrderDeadlineBook;
import com.jshx.zlqxzgzls.dao.OrderDeadlineBookDao;

@Component("orderDeadlineBookDao")
public class OrderDeadlineBookDaoImpl extends BaseDaoImpl implements OrderDeadlineBookDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOrderDeadlineBookByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<OrderDeadlineBook> findOrderDeadlineBook(Map<String, Object> paraMap){
		return this.findListByHqlId("findOrderDeadlineBookByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OrderDeadlineBook getById(String id)
	{
		return (OrderDeadlineBook)this.getObjectById(OrderDeadlineBook.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OrderDeadlineBook orderDeadlineBook)
	{
		orderDeadlineBook.setId(null);
		this.saveOrUpdateObject(orderDeadlineBook);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OrderDeadlineBook orderDeadlineBook)
	{
		this.saveOrUpdateObject(orderDeadlineBook);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OrderDeadlineBook.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OrderDeadlineBook orderDeadlineBook = (OrderDeadlineBook)this.getObjectById(OrderDeadlineBook.class, id);
		orderDeadlineBook.setDelFlag(1);
		this.saveObject(orderDeadlineBook);
	}
}
