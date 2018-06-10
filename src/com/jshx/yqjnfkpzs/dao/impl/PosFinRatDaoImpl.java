package com.jshx.yqjnfkpzs.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.yqjnfkpzs.entity.PosFinRat;
import com.jshx.yqjnfkpzs.dao.PosFinRatDao;

@Component("posFinRatDao")
public class PosFinRatDaoImpl extends BaseDaoImpl implements PosFinRatDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPosFinRatByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PosFinRat> findPosFinRat(Map<String, Object> paraMap){
		return this.findListByHqlId("findPosFinRatByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PosFinRat getById(String id)
	{
		return (PosFinRat)this.getObjectById(PosFinRat.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PosFinRat posFinRat)
	{
		posFinRat.setId(null);
		this.saveOrUpdateObject(posFinRat);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PosFinRat posFinRat)
	{
		this.saveOrUpdateObject(posFinRat);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PosFinRat.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PosFinRat posFinRat = (PosFinRat)this.getObjectById(PosFinRat.class, id);
		posFinRat.setDelFlag(1);
		this.saveObject(posFinRat);
	}
}
