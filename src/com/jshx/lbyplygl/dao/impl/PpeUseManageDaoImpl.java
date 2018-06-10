package com.jshx.lbyplygl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.lbyplygl.entity.PpeUseManage;
import com.jshx.lbyplygl.dao.PpeUseManageDao;

@Component("ppeUseManageDao")
public class PpeUseManageDaoImpl extends BaseDaoImpl implements PpeUseManageDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPpeUseManageByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPpeUseManage(Map<String, Object> paraMap){
		return this.findListByHqlId("findPpeUseManageByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PpeUseManage getById(String id)
	{
		return (PpeUseManage)this.getObjectById(PpeUseManage.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PpeUseManage ppeUseManage)
	{
		ppeUseManage.setId(null);
		this.saveOrUpdateObject(ppeUseManage);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PpeUseManage ppeUseManage)
	{
		this.saveOrUpdateObject(ppeUseManage);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PpeUseManage.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PpeUseManage ppeUseManage = (PpeUseManage)this.getObjectById(PpeUseManage.class, id);
		ppeUseManage.setDelFlag(1);
		this.saveObject(ppeUseManage);
	}
}
