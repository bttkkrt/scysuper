package com.jshx.hyfl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.hyfl.entity.Hyfl;
import com.jshx.hyfl.dao.HyflDao;

@Component("hyflDao")
public class HyflDaoImpl extends BaseDaoImpl implements HyflDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findHyflByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findHyfl(Map<String, Object> paraMap){
		return this.findListByHqlId("findHyflByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Hyfl getById(String id)
	{
		return (Hyfl)this.getObjectById(Hyfl.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Hyfl hyfl)
	{
		hyfl.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(hyfl);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Hyfl hyfl)
	{
		this.saveOrUpdateObject(hyfl);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Hyfl.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Hyfl hyfl = (Hyfl)this.getObjectById(Hyfl.class, id);
		hyfl.setDelFlag(1);
		this.saveObject(hyfl);
	}
}
