package com.jshx.zlqxzgzls.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zlqxzgzls.dao.OrderDeadlineBookDao;
import com.jshx.zlqxzgzls.entity.OrderDeadlineBook;
import com.jshx.zlqxzgzls.service.OrderDeadlineBookService;

@Service("orderDeadlineBookService")
public class OrderDeadlineBookServiceImpl extends BaseServiceImpl implements OrderDeadlineBookService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("orderDeadlineBookDao")
	private OrderDeadlineBookDao orderDeadlineBookDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return orderDeadlineBookDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OrderDeadlineBook getById(String id)
	{
		return orderDeadlineBookDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(OrderDeadlineBook orderDeadlineBook)
	{
		orderDeadlineBookDao.save(orderDeadlineBook);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(OrderDeadlineBook orderDeadlineBook)
	{
		orderDeadlineBookDao.update(orderDeadlineBook);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=orderDeadlineBookDao.findOrderDeadlineBook(paraMap);
		
		orderDeadlineBookDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    orderDeadlineBookDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<OrderDeadlineBook> findOrderDeadlineBook(
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return orderDeadlineBookDao.findOrderDeadlineBook(paraMap);
	}
}
