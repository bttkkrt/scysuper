package com.jshx.zdwxyyjcz.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxyyjcz.entity.ComDanEme;
import com.jshx.zdwxyyjcz.dao.ComDanEmeDao;

@Component("comDanEmeDao")
public class ComDanEmeDaoImpl extends BaseDaoImpl implements ComDanEmeDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findComDanEmeByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findComDanEme(Map<String, Object> paraMap){
		return this.findListByHqlId("findComDanEmeByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ComDanEme getById(String id)
	{
		return (ComDanEme)this.getObjectById(ComDanEme.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ComDanEme comDanEme)
	{
		comDanEme.setId(null);
		this.saveOrUpdateObject(comDanEme);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ComDanEme comDanEme)
	{
		this.saveOrUpdateObject(comDanEme);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ComDanEme.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ComDanEme comDanEme = (ComDanEme)this.getObjectById(ComDanEme.class, id);
		comDanEme.setDelFlag(1);
		this.saveObject(comDanEme);
	}
}
