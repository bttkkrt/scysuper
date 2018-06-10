package com.jshx.qyaqsccn.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyaqsccn.entity.ProCom;
import com.jshx.qyaqsccn.dao.ProComDao;

@Component("proComDao")
public class ProComDaoImpl extends BaseDaoImpl implements ProComDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProComByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProCom(Map<String, Object> paraMap){
		return this.findListByHqlId("findProComByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProCom getById(String id)
	{
		return (ProCom)this.getObjectById(ProCom.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProCom proCom)
	{
		proCom.setId(null);
		this.saveOrUpdateObject(proCom);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProCom proCom)
	{
		this.saveOrUpdateObject(proCom);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProCom.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProCom proCom = (ProCom)this.getObjectById(ProCom.class, id);
		proCom.setDelFlag(1);
		this.saveObject(proCom);
	}
}
