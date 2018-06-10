package com.jshx.xzcfjttljl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xzcfjttljl.entity.PenBraRec;
import com.jshx.xzcfjttljl.dao.PenBraRecDao;

@Component("penBraRecDao")
public class PenBraRecDaoImpl extends BaseDaoImpl implements PenBraRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPenBraRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PenBraRec> findPenBraRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findPenBraRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PenBraRec getById(String id)
	{
		return (PenBraRec)this.getObjectById(PenBraRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PenBraRec penBraRec)
	{
		penBraRec.setId(null);
		this.saveOrUpdateObject(penBraRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PenBraRec penBraRec)
	{
		this.saveOrUpdateObject(penBraRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PenBraRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PenBraRec penBraRec = (PenBraRec)this.getObjectById(PenBraRec.class, id);
		penBraRec.setDelFlag(1);
		this.saveObject(penBraRec);
	}
}
