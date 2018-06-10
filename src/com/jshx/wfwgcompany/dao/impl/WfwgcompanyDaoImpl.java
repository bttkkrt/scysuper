package com.jshx.wfwgcompany.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wfwgcompany.entity.Wfwgcompany;
import com.jshx.wfwgcompany.dao.WfwgcompanyDao;

@Component("wfwgcompanyDao")
public class WfwgcompanyDaoImpl extends BaseDaoImpl implements WfwgcompanyDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findWfwgcompanyByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWfwgcompany(Map<String, Object> paraMap){
		return this.findListByHqlId("findWfwgcompanyByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Wfwgcompany getById(String id)
	{
		return (Wfwgcompany)this.getObjectById(Wfwgcompany.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Wfwgcompany wfwgcompany)
	{
		wfwgcompany.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(wfwgcompany);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Wfwgcompany wfwgcompany)
	{
		this.saveOrUpdateObject(wfwgcompany);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Wfwgcompany.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Wfwgcompany wfwgcompany = (Wfwgcompany)this.getObjectById(Wfwgcompany.class, id);
		wfwgcompany.setDelFlag(1);
		this.saveObject(wfwgcompany);
	}
}
