package com.jshx.qyzyfzrpx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyzyfzrpx.entity.MaiChaTra;
import com.jshx.qyzyfzrpx.dao.MaiChaTraDao;

@Component("maiChaTraDao")
public class MaiChaTraDaoImpl extends BaseDaoImpl implements MaiChaTraDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMaiChaTraByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<MaiChaTra> findMaiChaTra(Map<String, Object> paraMap){
		return this.findListByHqlId("findMaiChaTraByMaps", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MaiChaTra getById(String id)
	{
		return (MaiChaTra)this.getObjectById(MaiChaTra.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MaiChaTra maiChaTra)
	{
		maiChaTra.setId(null);
		this.saveOrUpdateObject(maiChaTra);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MaiChaTra maiChaTra)
	{
		this.saveOrUpdateObject(maiChaTra);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MaiChaTra.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MaiChaTra maiChaTra = (MaiChaTra)this.getObjectById(MaiChaTra.class, id);
		maiChaTra.setDelFlag(1);
		this.saveObject(maiChaTra);
	}
}
