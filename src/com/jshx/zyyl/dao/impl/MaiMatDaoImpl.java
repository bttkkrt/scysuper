package com.jshx.zyyl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zyyl.entity.MaiMat;
import com.jshx.zyyl.dao.MaiMatDao;

@Component("maiMatDao")
public class MaiMatDaoImpl extends BaseDaoImpl implements MaiMatDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMaiMatByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMaiMat(Map<String, Object> paraMap){
		return this.findListByHqlId("findMaiMatByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MaiMat getById(String id)
	{
		return (MaiMat)this.getObjectById(MaiMat.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MaiMat maiMat)
	{
		maiMat.setId(null);
		this.saveOrUpdateObject(maiMat);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MaiMat maiMat)
	{
		this.saveOrUpdateObject(maiMat);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MaiMat.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MaiMat maiMat = (MaiMat)this.getObjectById(MaiMat.class, id);
		maiMat.setDelFlag(1);
		this.saveObject(maiMat);
	}
}
