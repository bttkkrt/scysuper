package com.jshx.prosafedesign.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.prosafedesign.entity.ProSafDes;
import com.jshx.prosafedesign.dao.ProSafDesDao;

@Component("proSafDesDao")
public class ProSafDesDaoImpl extends BaseDaoImpl implements ProSafDesDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findProSafDesByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findProSafDes(Map<String, Object> paraMap){
		return this.findListByHqlId("findProSafDesByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ProSafDes getById(String id)
	{
		return (ProSafDes)this.getObjectById(ProSafDes.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(ProSafDes proSafDes)
	{
		proSafDes.setId(null);
		this.saveOrUpdateObject(proSafDes);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(ProSafDes proSafDes)
	{
		this.saveOrUpdateObject(proSafDes);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(ProSafDes.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		ProSafDes proSafDes = (ProSafDes)this.getObjectById(ProSafDes.class, id);
		proSafDes.setDelFlag(1);
		this.saveObject(proSafDes);
	}
}
