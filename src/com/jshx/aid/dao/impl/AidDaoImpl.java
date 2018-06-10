package com.jshx.aid.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aid.entity.Aid;
import com.jshx.aid.dao.AidDao;

@Component("aidDao")
public class AidDaoImpl extends BaseDaoImpl implements AidDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findAidByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAid(Map<String, Object> paraMap){
		return this.findListByHqlId("findAidByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aid getById(String id)
	{
		return (Aid)this.getObjectById(Aid.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Aid aid)
	{
		aid.setId(null);
		this.saveOrUpdateObject(aid);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Aid aid)
	{
		this.saveOrUpdateObject(aid);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Aid.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Aid aid = (Aid)this.getObjectById(Aid.class, id);
		aid.setDelFlag(1);
		this.saveObject(aid);
	}
}
