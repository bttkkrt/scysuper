package com.jshx.hmd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.hmd.entity.Hmd;
import com.jshx.hmd.dao.HmdDao;

@Component("hmdDao")
public class HmdDaoImpl extends BaseDaoImpl implements HmdDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHmdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findHmd(Map<String, Object> paraMap){
		return this.findListByHqlId("findHmdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Hmd getById(String id)
	{
		return (Hmd)this.getObjectById(Hmd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Hmd hmd)
	{
		hmd.setId(null);
		this.saveOrUpdateObject(hmd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Hmd hmd)
	{
		this.saveOrUpdateObject(hmd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Hmd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Hmd hmd = (Hmd)this.getObjectById(Hmd.class, id);
		hmd.setDelFlag(1);
		this.saveObject(hmd);
	}
}
