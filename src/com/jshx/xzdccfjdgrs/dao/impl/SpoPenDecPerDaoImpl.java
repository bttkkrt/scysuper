package com.jshx.xzdccfjdgrs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xzdccfjdgrs.entity.SpoPenDecPer;
import com.jshx.xzdccfjdgrs.dao.SpoPenDecPerDao;

@Component("spoPenDecPerDao")
public class SpoPenDecPerDaoImpl extends BaseDaoImpl implements SpoPenDecPerDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSpoPenDecPerByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SpoPenDecPer> findSpoPenDecPer(Map<String, Object> paraMap){
		return this.findListByHqlId("findSpoPenDecPerByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SpoPenDecPer getById(String id)
	{
		return (SpoPenDecPer)this.getObjectById(SpoPenDecPer.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SpoPenDecPer spoPenDecPer)
	{
		spoPenDecPer.setId(null);
		this.saveOrUpdateObject(spoPenDecPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SpoPenDecPer spoPenDecPer)
	{
		this.saveOrUpdateObject(spoPenDecPer);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SpoPenDecPer.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SpoPenDecPer spoPenDecPer = (SpoPenDecPer)this.getObjectById(SpoPenDecPer.class, id);
		spoPenDecPer.setDelFlag(1);
		this.saveObject(spoPenDecPer);
	}
}
