package com.jshx.safeleader.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safeleader.entity.Safeleader;
import com.jshx.safeleader.dao.SafeleaderDao;

@Component("safeleaderDao")
public class SafeleaderDaoImpl extends BaseDaoImpl implements SafeleaderDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSafeleaderByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSafeleader(Map<String, Object> paraMap){
		return this.findListByHqlId("findSafeleaderByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Safeleader getById(String id)
	{
		return (Safeleader)this.getObjectById(Safeleader.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Safeleader safeleader)
	{
		safeleader.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(safeleader);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Safeleader safeleader)
	{
		this.saveOrUpdateObject(safeleader);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Safeleader.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Safeleader safeleader = (Safeleader)this.getObjectById(Safeleader.class, id);
		safeleader.setDelFlag(1);
		this.saveObject(safeleader);
	}
}
