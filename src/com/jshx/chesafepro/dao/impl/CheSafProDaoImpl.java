package com.jshx.chesafepro.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.chesafepro.entity.CheSafPro;
import com.jshx.chesafepro.dao.CheSafProDao;

@Component("cheSafProDao")
public class CheSafProDaoImpl extends BaseDaoImpl implements CheSafProDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheSafProByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheSafPro(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheSafProByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheSafPro getById(String id)
	{
		return (CheSafPro)this.getObjectById(CheSafPro.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheSafPro cheSafPro)
	{
		cheSafPro.setId(null);
		this.saveOrUpdateObject(cheSafPro);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheSafPro cheSafPro)
	{
		this.saveOrUpdateObject(cheSafPro);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheSafPro.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheSafPro cheSafPro = (CheSafPro)this.getObjectById(CheSafPro.class, id);
		cheSafPro.setDelFlag(1);
		this.saveObject(cheSafPro);
	}
}
