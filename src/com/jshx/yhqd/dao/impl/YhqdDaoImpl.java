package com.jshx.yhqd.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.dao.YhqdDao;

@Component("yhqdDao")
public class YhqdDaoImpl extends BaseDaoImpl implements YhqdDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findYhqdByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findYhqd(Map<String, Object> paraMap){
		return this.findListByHqlId("findYhqdByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Yhqd getById(String id)
	{
		return (Yhqd)this.getObjectById(Yhqd.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Yhqd yhqd)
	{
		yhqd.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(yhqd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Yhqd yhqd)
	{
		this.saveOrUpdateObject(yhqd);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Yhqd.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Yhqd yhqd = (Yhqd)this.getObjectById(Yhqd.class, id);
		yhqd.setDelFlag(1);
		this.saveObject(yhqd);
	}
}
