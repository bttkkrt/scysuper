package com.jshx.qynbqypx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qynbqypx.entity.AllMenTra;
import com.jshx.qynbqypx.dao.AllMenTraDao;

@Component("allMenTraDao")
public class AllMenTraDaoImpl extends BaseDaoImpl implements AllMenTraDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findAllMenTraByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findAllMenTra(Map<String, Object> paraMap){
		return this.findListByHqlId("findAllMenTraByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public AllMenTra getById(String id)
	{
		return (AllMenTra)this.getObjectById(AllMenTra.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(AllMenTra allMenTra)
	{
		allMenTra.setId(null);
		this.saveOrUpdateObject(allMenTra);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(AllMenTra allMenTra)
	{
		this.saveOrUpdateObject(allMenTra);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(AllMenTra.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		AllMenTra allMenTra = (AllMenTra)this.getObjectById(AllMenTra.class, id);
		allMenTra.setDelFlag(1);
		this.saveObject(allMenTra);
	}
}
