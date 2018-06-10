package com.jshx.scsbss.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.scsbss.entity.ProDev;
import com.jshx.scsbss.dao.ProDevDao;

@Component("proDevDao")
public class ProDevDaoImpl extends BaseDaoImpl implements ProDevDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProDevByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProDev(Map<String, Object> paraMap){
		return this.findListByHqlId("findProDevByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProDev getById(String id)
	{
		return (ProDev)this.getObjectById(ProDev.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProDev proDev)
	{
		proDev.setId(null);
		this.saveOrUpdateObject(proDev);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProDev proDev)
	{
		this.saveOrUpdateObject(proDev);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProDev.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProDev proDev = (ProDev)this.getObjectById(ProDev.class, id);
		proDev.setDelFlag(1);
		this.saveObject(proDev);
	}
}
