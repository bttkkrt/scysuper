package com.jshx.qzzxsqs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qzzxsqs.entity.EnfApp;
import com.jshx.qzzxsqs.dao.EnfAppDao;

@Component("enfAppDao")
public class EnfAppDaoImpl extends BaseDaoImpl implements EnfAppDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findEnfAppByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<EnfApp> findEnfApp(Map<String, Object> paraMap){
		return this.findListByHqlId("findEnfAppByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EnfApp getById(String id)
	{
		return (EnfApp)this.getObjectById(EnfApp.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EnfApp enfApp)
	{
		enfApp.setId(null);
		this.saveOrUpdateObject(enfApp);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EnfApp enfApp)
	{
		this.saveOrUpdateObject(enfApp);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(EnfApp.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		EnfApp enfApp = (EnfApp)this.getObjectById(EnfApp.class, id);
		enfApp.setDelFlag(1);
		this.saveObject(enfApp);
	}
}
