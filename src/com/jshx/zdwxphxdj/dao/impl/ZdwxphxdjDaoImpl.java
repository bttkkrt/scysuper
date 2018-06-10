package com.jshx.zdwxphxdj.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxphxdj.entity.Zdwxphxdj;
import com.jshx.zdwxphxdj.dao.ZdwxphxdjDao;

@Component("zdwxphxdjDao")
public class ZdwxphxdjDaoImpl extends BaseDaoImpl implements ZdwxphxdjDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZdwxphxdjByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZdwxphxdj(Map<String, Object> paraMap){
		return this.findListByHqlId("findZdwxphxdjByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxphxdj getById(String id)
	{
		return (Zdwxphxdj)this.getObjectById(Zdwxphxdj.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zdwxphxdj zdwxphxdj)
	{
		zdwxphxdj.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zdwxphxdj);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zdwxphxdj zdwxphxdj)
	{
		this.saveOrUpdateObject(zdwxphxdj);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zdwxphxdj.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zdwxphxdj zdwxphxdj = (Zdwxphxdj)this.getObjectById(Zdwxphxdj.class, id);
		zdwxphxdj.setDelFlag(1);
		this.saveObject(zdwxphxdj);
	}
}
