package com.jshx.wxhxpzdwxyba.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.wxhxpzdwxyba.entity.MajSouRec;
import com.jshx.wxhxpzdwxyba.dao.MajSouRecDao;

@Component("majSouRecDao")
public class MajSouRecDaoImpl extends BaseDaoImpl implements MajSouRecDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findMajSouRecByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findMajSouRec(Map<String, Object> paraMap){
		return this.findListByHqlId("findMajSouRecByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public MajSouRec getById(String id)
	{
		return (MajSouRec)this.getObjectById(MajSouRec.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(MajSouRec majSouRec)
	{
		majSouRec.setId(null);
		this.saveOrUpdateObject(majSouRec);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(MajSouRec majSouRec)
	{
		this.saveOrUpdateObject(majSouRec);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(MajSouRec.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		MajSouRec majSouRec = (MajSouRec)this.getObjectById(MajSouRec.class, id);
		majSouRec.setDelFlag(1);
		this.saveObject(majSouRec);
	}
}
