package com.jshx.sxxw.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sxxw.entity.Dishonesty;
import com.jshx.sxxw.dao.DishonestyDao;

@Component("dishonestyDao")
public class DishonestyDaoImpl extends BaseDaoImpl implements DishonestyDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findDishonestyByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDishonesty(Map<String, Object> paraMap){
		return this.findListByHqlId("findDishonestyByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dishonesty getById(String id)
	{
		return (Dishonesty)this.getObjectById(Dishonesty.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Dishonesty dishonesty)
	{
		dishonesty.setId(null);
		this.saveOrUpdateObject(dishonesty);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Dishonesty dishonesty)
	{
		this.saveOrUpdateObject(dishonesty);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Dishonesty.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Dishonesty dishonesty = (Dishonesty)this.getObjectById(Dishonesty.class, id);
		dishonesty.setDelFlag(1);
		this.saveObject(dishonesty);
	}
}
