package com.jshx.jdjcjg.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdjcjg.entity.SupCheRes;
import com.jshx.jdjcjg.dao.SupCheResDao;

@Component("supCheResDao")
public class SupCheResDaoImpl extends BaseDaoImpl implements SupCheResDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSupCheResByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSupCheRes(Map<String, Object> paraMap){
		return this.findListByHqlId("findSupCheResByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupCheRes getById(String id)
	{
		return (SupCheRes)this.getObjectById(SupCheRes.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SupCheRes supCheRes)
	{
		supCheRes.setId(null);
		this.saveOrUpdateObject(supCheRes);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SupCheRes supCheRes)
	{
		this.saveOrUpdateObject(supCheRes);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SupCheRes.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SupCheRes supCheRes = (SupCheRes)this.getObjectById(SupCheRes.class, id);
		supCheRes.setDelFlag(1);
		this.saveObject(supCheRes);
	}
}
