package com.jshx.zxzzjcjl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zxzzjcjl.entity.RecRes;
import com.jshx.zxzzjcjl.dao.RecResDao;

@Component("recResDao")
public class RecResDaoImpl extends BaseDaoImpl implements RecResDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findRecResByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRecRes(Map<String, Object> paraMap){
		return this.findListByHqlId("findRecResByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RecRes getById(String id)
	{
		return (RecRes)this.getObjectById(RecRes.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(RecRes recRes)
	{
		recRes.setId(null);
		this.saveOrUpdateObject(recRes);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(RecRes recRes)
	{
		this.saveOrUpdateObject(recRes);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(RecRes.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		RecRes recRes = (RecRes)this.getObjectById(RecRes.class, id);
		recRes.setDelFlag(1);
		this.saveObject(recRes);
	}
}
