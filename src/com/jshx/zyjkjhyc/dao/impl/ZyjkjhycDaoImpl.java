package com.jshx.zyjkjhyc.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zyjkjhyc.entity.Zyjkjhyc;
import com.jshx.zyjkjhyc.dao.ZyjkjhycDao;

@Component("zyjkjhycDao")
public class ZyjkjhycDaoImpl extends BaseDaoImpl implements ZyjkjhycDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZyjkjhycByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZyjkjhyc(Map<String, Object> paraMap){
		return this.findListByHqlId("findZyjkjhycByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zyjkjhyc getById(String id)
	{
		return (Zyjkjhyc)this.getObjectById(Zyjkjhyc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zyjkjhyc zyjkjhyc)
	{
		zyjkjhyc.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zyjkjhyc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zyjkjhyc zyjkjhyc)
	{
		this.saveOrUpdateObject(zyjkjhyc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zyjkjhyc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zyjkjhyc zyjkjhyc = (Zyjkjhyc)this.getObjectById(Zyjkjhyc.class, id);
		zyjkjhyc.setDelFlag(1);
		this.saveObject(zyjkjhyc);
	}
}
