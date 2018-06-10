package com.jshx.chesafecondition.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.chesafecondition.entity.CheSafCon;
import com.jshx.chesafecondition.dao.CheSafConDao;

@Component("cheSafConDao")
public class CheSafConDaoImpl extends BaseDaoImpl implements CheSafConDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findCheSafConByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findCheSafCon(Map<String, Object> paraMap){
		return this.findListByHqlId("findCheSafConByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CheSafCon getById(String id)
	{
		return (CheSafCon)this.getObjectById(CheSafCon.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CheSafCon cheSafCon)
	{
		cheSafCon.setId(null);
		this.saveOrUpdateObject(cheSafCon);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CheSafCon cheSafCon)
	{
		this.saveOrUpdateObject(cheSafCon);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(CheSafCon.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		CheSafCon cheSafCon = (CheSafCon)this.getObjectById(CheSafCon.class, id);
		cheSafCon.setDelFlag(1);
		this.saveObject(cheSafCon);
	}
}
