package com.jshx.occconcomplete.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.occconcomplete.entity.OccConCom;
import com.jshx.occconcomplete.dao.OccConComDao;

@Component("occConComDao")
public class OccConComDaoImpl extends BaseDaoImpl implements OccConComDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findOccConComByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccConCom(Map<String, Object> paraMap){
		return this.findListByHqlId("findOccConComByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccConCom getById(String id)
	{
		return (OccConCom)this.getObjectById(OccConCom.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(OccConCom occConCom)
	{
		occConCom.setId(null);
		this.saveOrUpdateObject(occConCom);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(OccConCom occConCom)
	{
		this.saveOrUpdateObject(occConCom);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(OccConCom.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		OccConCom occConCom = (OccConCom)this.getObjectById(OccConCom.class, id);
		occConCom.setDelFlag(1);
		this.saveObject(occConCom);
	}
}
