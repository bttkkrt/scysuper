package com.jshx.gpdb.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gpdb.entity.Supervice;
import com.jshx.gpdb.dao.SuperviceDao;

@Component("superviceDao")
public class SuperviceDaoImpl extends BaseDaoImpl implements SuperviceDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSuperviceByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSupervice(Map<String, Object> paraMap){
		return this.findListByHqlId("findSuperviceByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Supervice getById(String id)
	{
		return (Supervice)this.getObjectById(Supervice.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Supervice supervice)
	{
		supervice.setId(null);
		this.saveOrUpdateObject(supervice);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Supervice supervice)
	{
		this.saveOrUpdateObject(supervice);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Supervice.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Supervice supervice = (Supervice)this.getObjectById(Supervice.class, id);
		supervice.setDelFlag(1);
		this.saveObject(supervice);
	}
}
